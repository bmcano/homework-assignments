#include <string.h>
#include <openssl/sha.h>
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <signal.h>
#include <unistd.h>

// Brandon Cano
// Operating Systems
// Homework 6

typedef struct{
    unsigned short tid;
    unsigned short challenge;
} tinput_t;


unsigned int NSOLUTIONS = 8;

//the following two global variables are used by worker threads to communicate back the found results to the main thread
unsigned short found_solutions = 0;
unsigned long* solutions;
pthread_mutex_t solutions_mutex;


unsigned short divisibility_check(unsigned long n){
    //very not efficient algorithm
    unsigned long i;
    for (i=1000000; i<=1500000; i++) {
        if (n%i == 0) {
            return 0;
        }
    }
    return 1;
}


short try_solution(unsigned short challenge, unsigned long attempted_solution){
        //check if sha256(attempted_solution) is equal to digest
        //attempted_solution is converted to an 8byte buffer preserving endianness
        //the first 2 bytes of the hash are considered as a little endian 16bit number and compared to challenge
        unsigned char digest[SHA256_DIGEST_LENGTH];
        SHA256_CTX ctx;
        SHA256_Init(&ctx);
        SHA256_Update(&ctx, &attempted_solution, 8);
        SHA256_Final(digest, &ctx);

        if ((*(unsigned short*)digest) == challenge) {
            return 1;
        } else {
            return 0;
        }
}


void* worker_thread_function(void *tinput_void){
    tinput_t* tinput = (tinput_t*) tinput_void;

    unsigned long max_val = -1UL;
    unsigned long start = tinput->tid * (max_val / tinput->challenge);
    unsigned long end = start + (max_val / tinput->challenge);

    for(unsigned long attempted_solution=start; attempted_solution<end; attempted_solution++){
        // condition1: sha256(attempted_solution) == challenge
        if(try_solution(tinput->challenge, attempted_solution)){
            // condition2: the last digit must be different in all the solutions
            short bad_solution = 0;
            for(int i=0; i<found_solutions; i++) {
                if (attempted_solution%10 == solutions[i]%10) {
                    bad_solution = 1;
                    break;
                }
            }

            if (bad_solution) {
                continue;
            }

            // condition3: no solution should be divisible by any number in the range [1000000, 1500000]
            if (!divisibility_check(attempted_solution)) {
                continue;
            }


            pthread_mutex_lock(&solutions_mutex);
            if (found_solutions < NSOLUTIONS) {
                solutions[found_solutions] = attempted_solution;
                found_solutions++;
            }
            pthread_mutex_unlock(&solutions_mutex);

            if(found_solutions==NSOLUTIONS){
                return NULL;
            }
        }
    }
}


void solve_one_challenge(unsigned short challenge, unsigned short nthread){
    pthread_t th[nthread];
    tinput_t inputs[nthread];

    found_solutions = 0;
    solutions = (unsigned long*) malloc(NSOLUTIONS * (sizeof(unsigned long)));
    for(int i=0; i<NSOLUTIONS; i++){
        solutions[i] = 0;
    }

    pthread_mutex_init(&solutions_mutex, NULL);

    for(int i=0; i<nthread; i++){
        inputs[i].tid = i;
        inputs[i].challenge = challenge;
        pthread_create(&(th[i]), NULL, worker_thread_function, &(inputs[i]));
    }

    for(int i=0; i<nthread; i++){
        pthread_join(th[i], NULL);
    }

    printf("%d ", challenge);
    for(int i=0; i<NSOLUTIONS; i++){
        printf("%ld ", solutions[i]);
    }
    printf("\n");
    free(solutions);
    pthread_mutex_destroy(&solutions_mutex);
}

int main(int argc, char* argv[]) {
    //argv[1] is the number of worker threads we must use
    //the other arguments are the challenges we must solve
    unsigned short nthread = strtol(argv[1],NULL,10);

    for(int i = 2; i<argc; i++){
        unsigned short challenge = strtol(argv[i],NULL,10);
        solve_one_challenge(challenge, nthread);
    }

    return 0;
}


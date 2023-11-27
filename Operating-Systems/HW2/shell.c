#define _GNU_SOURCE //this is needed to be able to use execvpe 
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>
#include <sys/resource.h>
#include <sys/types.h>
#include <fcntl.h>


typedef struct {
  char* binary_path;
  char* stdin;
  char* stdout;
  char* arguments;
  short wait;
} command;

//function prototypes
void print_parsed_command(command);
short parse_command(command*, char*);

// read a line from a file
short getlinedup(FILE* fp, char** value){
  char* line = NULL;
  size_t n = 0;
  //get one line
  int ret = getline(&line, &n, fp);

  if(ret == -1){
    //the file ended
    return 0;
  }
  //remove \n at the end
  line[strcspn(line, "\n")] = '\0';
  //duplicate the string and set value
  *value = strdup(line);
  free(line);

  return 1;
}

//parse a command_file and set a corresponding command data structure
short parse_command(command* parsed_command, char* cmdfile){
  FILE* fp = fopen(cmdfile, "r");
  if(!fp){
    //the file does not exist
    return 0;
  }

  char* value;
  short ret;
  int intvalue;

  ret = getlinedup(fp,&value);
  if(!ret){
    fclose(fp); return 0;
  }
  parsed_command->binary_path = value;

  ret = getlinedup(fp,&value);
  if(!ret){
    fclose(fp); return 0;
  }
  parsed_command->stdin = value;

  ret = getlinedup(fp,&value);
  if(!ret){
    fclose(fp); return 0;
  }
  parsed_command->stdout = value;

  ret = getlinedup(fp,&value);
  if(!ret){
    fclose(fp); return 0;
  }
  parsed_command->arguments = value;

  ret = getlinedup(fp,&value);
  if(!ret){
    fclose(fp); return 0;
  }
  intvalue = atoi(value);
  if(intvalue != 0 && intvalue != 1){
    fclose(fp); return 0;
  }
  parsed_command->wait = (short)intvalue;

  return 1;
}

//useful for debugging
void print_parsed_command(command parsed_command){
  printf("----------\n");
  printf("binary_path: %s\n", parsed_command.binary_path);
  printf("stdin: %s\n", parsed_command.stdin);
  printf("stdout: %s\n", parsed_command.stdout);
  printf("arguments: %s\n", parsed_command.arguments);
  printf("wait: %d\n", parsed_command.wait);
}

void free_command(command cmd){
  free(cmd.binary_path);
  free(cmd.stdin);
  free(cmd.stdout);
  free(cmd.arguments);
}


int main(int argc, char *argv[], char* env[]) {

  for(int ncommand=1; ncommand<argc; ncommand++){
    command parsed_command;
    int ret = parse_command(&parsed_command, argv[ncommand]);
    if (!ret){
      printf("command file %s is invalid\n", argv[ncommand]);
      continue;
    }
    // create child process
    int rc = fork();
    if (rc < 0) {
        fprintf(stderr, "fork failed\n");
        exit(1);
    } else if (rc == 0) { // child (new process)
        printf("New child process started %d\n", (int) getpid());
        if (strlen(parsed_command.stdin) != 0) {
          int fd_in = open(parsed_command.stdin, O_RDONLY);
          if (fd_in == -1) {
            fprintf(stderr, "open stdin\n");
            exit(1);
          }
          dup2(fd_in, STDIN_FILENO);
          close(fd_in);
        }
        if (strlen(parsed_command.stdout) != 0) {
          int fd_out = open(parsed_command.stdout, O_WRONLY | O_CREAT | O_TRUNC, 0664);
          if (fd_out == -1) {
            fprintf(stderr, "open stdout\n");
            exit(1);
          }
          dup2(fd_out, STDOUT_FILENO);
          close(fd_out);
        }

      char *myargs[3];
      myargs[0] = parsed_command.binary_path;
      myargs[1] = parsed_command.arguments;
      myargs[2] = NULL;
      execvp(myargs[0], myargs);
      printf("execv failed!\n");
    } else { // parent process
        if (parsed_command.wait == 1) {
          int status;
          waitpid(rc, &status, 0);
          printf("Child process %d terminated with exit code %d\n", rc, WEXITSTATUS(status));
        }
    }

    free_command(parsed_command);
  }

  for (int i=0; i<argc; i++) {
    int status;
    int terminated_pid = wait(&status);
    if (terminated_pid != -1) {
      printf("Child process %d terminated with exit code %d\n", terminated_pid, WEXITSTATUS(status));
    }
  }
  //remember to wait for the termination of all the child processes, regardless of the value of parsed_command.wait
  return 0;
}


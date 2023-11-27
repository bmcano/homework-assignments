#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

// Brandon Cano
// OS: Homework 4
// Task 2

int main(int argc, char* argv[]) {
  if (argc != 2) {
    fprintf(stderr, "Usage: %s <file_path>\n", argv[0]);
    return -1;
  }

  const char* file_path = argv[1];
  int fd = open(file_path, O_RDWR);
  if (fd == -1) {
    perror("Error reading file");
    return -1;
  }

  struct stat file_stat;
  if (fstat(fd, &file_stat) == -1) {
    perror("Error getting file size");
    close(fd);
    return -1;
  }

  size_t file_size = (size_t) file_stat.st_size;

  void* file_data = mmap(NULL, file_size, PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);
  if (file_data == MAP_FAILED) {
    perror("Error mapping file");
    close(fd);
    return -1;
  }

  // reverse content
  for (size_t i=0; i<file_size/2; i++) {
    char tmp = ((char*) file_data)[i];
    ((char*) file_data)[i] = ((char*) file_data)[file_size-i-1];
    ((char*) file_data)[file_size-i-1] = tmp;
  }

  if (munmap(file_data, file_size) == -1) {
    perror("Error unmapping file");
  }
  close(fd);

  printf("File content was reversed and save to %s\n", file_path);
  return 0;
}

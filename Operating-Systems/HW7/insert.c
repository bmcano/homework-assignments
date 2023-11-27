#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <dirent.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Brandon Cano
// CS:3620
// Homework 7

void insertId(const char *filename) {
 int file = open(filename, O_RDWR);
 if (file < 0) {
  printf("Error opening file.\n");
  return;
 }

 char HAWKID[] = "bmcano";
 int id_len = strlen(HAWKID);

 off_t file_len = lseek(file, 0, SEEK_END);
 char *file_content = (char*)malloc(file_len + id_len + 1);

 lseek(file, 0, SEEK_SET);
 read(file, file_content + id_len, file_len);
 memcpy(file_content, HAWKID, id_len);
 lseek(file, 0, SEEK_SET);
 write(file, file_content, file_len + id_len);
 free(file_content);
 close(file);
}

void findFiles(const char *dir_path) {
 // start with path .
 DIR* dir = opendir(dir_path);
 if (dir == NULL) {
  printf("Error opening directory.\n");
  exit(1);
 }
 struct dirent *fp;
 while ((fp = readdir(dir)) != NULL) {
  struct stat buf;
  char path[512];
  sprintf(path, "%s/%s", dir_path, fp->d_name);
  stat(path, &buf);
  // we want to ignore the . and .. directories
  if (strcmp(fp->d_name, ".") == 0 || strcmp(fp->d_name, "..") == 0) {
   continue;
  }

  if (S_ISDIR(buf.st_mode)) {
   printf("dir: %s new path: %s\n", fp->d_name, path);
   findFiles(path);
  } else {
   printf("file: %s\n", fp->d_name);
   if (strncmp(fp->d_name, "CS3620", 6) == 0) {
    insertId(path);
   }
  }
 }
 closedir(dir);
}

int main() {
 // function is recusrive
 findFiles(".");
 return 0;
}

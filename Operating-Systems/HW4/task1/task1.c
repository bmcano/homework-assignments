#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>

// Brandon Cano
// OS: HW4
// Task 1

#define BUFFER_SIZE 256

// helper function to verify a valid address
int is_address_valid(uint64_t address) {
  FILE* maps = fopen("/proc/self/maps", "r");
  if (!maps) {
    perror("Error opening /proc/self/maps");
    exit(-1);
  }

  char line[BUFFER_SIZE];
  int is_valid = 0;

  while (fgets(line, sizeof(line), maps)) {
    uint64_t start, end;
    if (sscanf(line, "%lx-%lx", &start, &end) == 2) {
      if (address >= start && address < end) {
        is_valid = 1;
        break;
      }
    }
  }

  fclose(maps);
  return is_valid;
}

int main(int argc, char* argv[]) {
  if (argc != 2) {
    fprintf(stderr, "Usage: %s <required_address>\n", argv[0]);
    return -1;
  }

  char* endptr;
  uint64_t required_address = strtoull(argv[1], &endptr, 16);

  if (*endptr != '\0') {
    fprintf(stderr, "Invalid address format\n");
    return -1;
  }

  if (!is_address_valid(required_address)) {
    return -1;
  }

  char* byte_ptr = (char*) required_address;
  printf("%02x\n", *byte_ptr);

  return 0;
}

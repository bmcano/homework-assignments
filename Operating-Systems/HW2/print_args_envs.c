#include <stdio.h>

int main(int args, char *argv[], char *env[])
{
	// args
	printf("%d\n", args);
	printf("%s\n", argv[0]);
	for (int i=1; i<args; i++) {
		printf("%s\n", argv[i]);
	}
	// env variables
	int i = 0;
	while (env[i] != NULL) {
		printf("%s\n", env[i]);
		i++;
	}
	return 0;
}

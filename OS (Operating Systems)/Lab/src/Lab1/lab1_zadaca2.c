#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char *argv[])
{

    pid_t pid;
    if ((pid = fork()) == 0)
    { /* child */
        sleep(5);
        printf("Proces dete\n");
        int a;
        while(1){
            int num;
            printf("Vnesete broj: ");
            scanf("%d", &num);
            printf("Vnesovte %d. ", num);
            if(num == 0){
                exit(0);
            }
        }
    } else {
    if (pid > 0)
        { /* parent */
            printf("Proces tatko\n");
            wait(NULL);
            printf("\nSe budam\nPovtorno proces tatko");
            exit(0);
        }
    }

}

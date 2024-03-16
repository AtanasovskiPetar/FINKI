#include <stdio.h>
#include <string.h>
#include <mpi.h>

int main(int argc, char** argv) {
    int rank;
    MPI_Status status;
    char message[100];

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    if (rank == 0) {
        strcpy(message, "Hello, Process 1!");

        MPI_Send(message, strlen(message)+1, MPI_CHAR, 1, 0, MPI_COMM_WORLD);
        printf("Process 0 sent message: %s\n", message);

        MPI_Recv(message, sizeof(message), MPI_CHAR, 1, 0, MPI_COMM_WORLD, &status);
        printf("Process 0 received message: %s\n", message);
    }
    else if (rank == 1) {

        MPI_Recv(message, sizeof(message), MPI_CHAR, 0, 0, MPI_COMM_WORLD, &status);
        printf("Process 1 received message: %s\n", message);

        strcpy(message, "Hi, Process 0!");
        MPI_Send(message, strlen(message)+1, MPI_CHAR, 0, 0, MPI_COMM_WORLD);
        printf("Process 1 sent message: %s\n", message);
    }

    MPI_Finalize();

    return 0;
}

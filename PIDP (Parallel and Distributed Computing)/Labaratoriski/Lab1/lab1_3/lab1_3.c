#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {

    MPI_Init(NULL, NULL);
    int world_size;
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);

    int processor_rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &processor_rank);

    const int BUFFER_SIZE = 128;
    char *message = (char *)malloc(BUFFER_SIZE * sizeof(char));

    for (int iter = 0; iter <= 10; iter++) {
        if (processor_rank == 0) {
            if (iter != 0) {
                MPI_Recv(message, BUFFER_SIZE, MPI_CHAR, 2, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
            }
            snprintf(message, BUFFER_SIZE, "Message %02d", iter);
            MPI_Send(message, BUFFER_SIZE, MPI_CHAR, 1, 0, MPI_COMM_WORLD);
        } else if (processor_rank == 1) {
            MPI_Recv(message, BUFFER_SIZE, MPI_CHAR, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
            MPI_Send(message, BUFFER_SIZE, MPI_CHAR, 2, 0, MPI_COMM_WORLD);
        } else if (processor_rank == 2) {
            MPI_Recv(message, BUFFER_SIZE, MPI_CHAR, 1, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
            MPI_Send(message, BUFFER_SIZE, MPI_CHAR, 0, 0, MPI_COMM_WORLD);
        } else {
            fprintf(stderr, "The maximum number of processors for this task is 3\n");
        }
        printf("[P%d]\t%s\n", processor_rank, message);
    }

    free(message);

    MPI_Finalize();
    return 0;
}

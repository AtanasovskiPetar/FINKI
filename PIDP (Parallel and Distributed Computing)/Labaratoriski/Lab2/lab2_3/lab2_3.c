#include <mpi.h>
#include <stdio.h>

int main(int argc, char **argv)
{
    MPI_Init(NULL, NULL);
    int world_size;
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);
    int rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    double local_mpi_barrier_time = 0.0;
    MPI_Barrier(MPI_COMM_WORLD);
    double startTime = MPI_Wtime();
    double localTime = MPI_Wtime() - startTime;

    printf("[%d]\tWaited\t%.20f (time units)\n", rank, localTime);

    MPI_Finalize();
    return 0;
}

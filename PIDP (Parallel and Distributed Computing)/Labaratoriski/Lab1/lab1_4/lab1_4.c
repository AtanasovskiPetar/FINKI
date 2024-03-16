#include <mpi.h>
#include <stdio.h>

int main(int argc, char **argv)
{
    int NUMBER_OF_ELEMENTS_IN_ARRAY = 12;

    MPI_Init(NULL, NULL);

    int world_size;
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);

    int processor_rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &processor_rank);

    int array[12] = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};

    int start = processor_rank * (NUMBER_OF_ELEMENTS_IN_ARRAY / 4);
    int end = start + (NUMBER_OF_ELEMENTS_IN_ARRAY / 4);
    int send_buffer[NUMBER_OF_ELEMENTS_IN_ARRAY / 4];

    int j = 0;
    for (int i = start; i < end; i++)
    {
        send_buffer[j] = array[i];
        j++;
    }

    MPI_Send(send_buffer, NUMBER_OF_ELEMENTS_IN_ARRAY / 4, MPI_INT, 3, 0, MPI_COMM_WORLD);

    if (processor_rank == (world_size - 1))
    {
        int total_sum = 0;

        for (int rFrom = 0; rFrom <= (world_size - 1); rFrom++)
        {
            int receive_buffer[NUMBER_OF_ELEMENTS_IN_ARRAY / 4];
            MPI_Recv(receive_buffer, NUMBER_OF_ELEMENTS_IN_ARRAY / 4, MPI_INT, rFrom, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

            for (int i = 0; i < NUMBER_OF_ELEMENTS_IN_ARRAY / 4; i++)
            {
                total_sum += receive_buffer[i];
            }
        }
        printf("Total sum: %d\n", total_sum);
    }

    MPI_Finalize();
    return 0;
}

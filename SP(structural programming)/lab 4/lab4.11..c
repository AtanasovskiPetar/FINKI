#include <stdio.h>
#include <stdlib.h>

int main()
{
    int N;
    scanf("%d", &N);
    if(N>2){
        for(int i=0;i<N;i++){
            for(int j=0; j<N; j++){
                if(i==N-1){
                    printf("*");
                }
                else if(j==i||j==0){
                    printf("*");
                }
                else{
                    printf(" ");
                }
            }
            printf("\n");
        }
    }
    else{
        printf("Nevaliden vlez");
    }
    return 0;
}

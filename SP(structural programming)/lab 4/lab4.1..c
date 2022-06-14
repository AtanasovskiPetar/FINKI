//Од тастатура се чита еден непарен цел број n кој е поголем или еднаков на 5.
//Со помош на знакот * да се исцрта математичкиот симбол за бесконечност, кој би се добил како во примерот подолу, за n=5:
/*
*   *
** **
* * *
** **
*   *
*/
#include <stdio.h>
#include <stdlib.h>

int main()
{
    int number,temp1,temp2;
    scanf("%d", &number);
    if(number%2!=0){
        for(int i=0; i<number; i++){
            temp1=0+i;
            temp2=number-1-i;
            for(int j=0;j<number;j++){
                if(j==temp1 || j==temp2||j==0||j==number-1){
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
        printf("Paren broj");
    }
    return 0;
}

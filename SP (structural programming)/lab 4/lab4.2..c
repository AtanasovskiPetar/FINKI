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
    int Number,Percent;
    int temp,n,sum=0,j,k=0;
    scanf("%d%d", &Number,&Percent);
    int a[5];
    for(int i=1;i<=Number;i++)
    {
        j=i;
        while(j>0){
            temp=j%10;
            sum=sum+temp;
            j=j/10;
        }
        if(sum==((Percent/100.0)*i)){
            a[k]=i;
            k++;
        }
        sum=0;
        if(k==5){
            break;
        }
    }
    for(int t=k-1;t>=0;t--){
        printf("%d\n", a[t]);
    }
	if(k==0){
        printf("NEMA");
    }
    return 0;
}

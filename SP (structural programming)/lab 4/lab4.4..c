
#include <stdio.h>
#include <stdlib.h>

int main()
{
    int a,b;
    scanf("%d%d",&a,&b);
    int a1=a,b1=b,j,temp,k=0;
    int sum=0;
    for(a1;a1<=b1;a1++){
        j=a1;
        while(j>0){
            temp=j%10;
            if(temp%2==0 && temp%3!=0){
                sum=sum+temp;
            }
            j/=10;
        }
        if(sum>0 && sum%7==0){
            printf("%d\n",a1);
            k++;
        }
        sum=0;

    }
    printf("Vkupno: %d", k);
    return 0;
}

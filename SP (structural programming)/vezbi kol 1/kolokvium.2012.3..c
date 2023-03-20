
#include<stdio.h>

int main(){
    int number,digit,n1,k=0;
    scanf("%d",&number);
    for(int i=number+1; i<10000; i++){
        n1=i;
        k=0;
        while(n1>0){
            digit=n1%10;
            if(digit!=7 && digit!=9){
                k++;
            }
            n1/=10;
        }
        if(k==0){
            printf("%d",i);
            break;
        }
    }
    return 0;
}

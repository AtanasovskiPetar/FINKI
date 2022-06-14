#include<stdio.h>

int main(){
    int n,n1,digit1,digit2,digit3,i=0,k=0;
    while(scanf("%d",&n))
    {
        if(n>=10){
            n1=n;
            i=0;
            k=0;
            if(n1>=100){
                while(n1>100){
                    i++;
                    digit1=n1%10;
                    digit2=(n1%100)/10;
                    digit3=(n1%1000)/100;
                    n1/=10;
                    if((digit1>digit2 && digit2>digit3) || (digit1==digit2) || (digit2==digit3)){
                        k++;
                    }
                    else if((digit1<=digit2 && digit2<=digit3) || (digit1==digit2) || (digit2==digit3)){
                        k++;
                    }
                }
            }
            else{
                while(n1>10){
                    digit1=n1%10;
                    digit2=(n1%100)/10;
                    n1/=10;
                    if(digit1==digit2){
                        k++;
                    }
                }
            }
            if(k==0){
                printf("%d\n",n);
            }
        }
    }
    return 0;
}

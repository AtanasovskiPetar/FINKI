#include<stdio.h>

int main(){
    int n,n1;
    int SumDigit=0,Digit,MaxDigit=0,MaxDigit1=0,FinalSum=0;
    while(scanf("%d",&n)){
        n1=n;
        SumDigit=0;
        MaxDigit=0;
        while(n1>0){
            Digit=n1%10;
            if(Digit>MaxDigit){
                MaxDigit=Digit;
            }
            SumDigit+=Digit;
            n1/=10;
        }
        FinalSum=MaxDigit1+SumDigit;
        printf("%d: %d\n", n,FinalSum);
        MaxDigit1=MaxDigit;
    }
    return 0;
}

#include<stdio.h>

int main(){
    int n,n1;
    int reverse, NumOfDigits=0, Digit,koef=1;
    scanf("%d",&n);
    if(n>9){
        for(int i=n-1; i>0; i--){
            n1=i;
            NumOfDigits=0;
            koef=1;
            reverse=0;
            while(n1>0){
                NumOfDigits++;
                koef*=10;
                n1/=10;
            }
            koef/=10;
            n1=i;
            while(n1>0){
                Digit=n1%10;
                reverse+=Digit*koef;
                n1/=10;
                koef/=10;
            }
            if(reverse%NumOfDigits==0){
                printf("%d", i);
                break;
            }
        }
    }
    else{
        printf("Brojot ne e validen");
    }
    return 0;
}

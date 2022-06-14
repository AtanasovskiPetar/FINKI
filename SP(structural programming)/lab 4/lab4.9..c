#include<stdio.h>

int main(){
    float n,n1;
    scanf("%f",&n);
    if(n<=500){
        n1=5*n;
    }
    else if(n>500){
        if(n<=500+150)
        {
            n1=(500*5)+(n-500)*7.5;
        }
        else if(n<=500+150+200){
            n1=(500*5)+(150*7.5)+(n-650)*11;
        }
        else{
            n1=(500*5)+(150*7.5)+(200*11)+(n-850)*13;
        }
    }
    if(n1<=7000){
        printf("%.2f",n1*1.1);
    }
    else{
        printf("%.2f",n1*1.18);
    }
    return 0;
}

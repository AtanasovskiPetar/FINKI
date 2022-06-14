#include<stdio.h>

int main(){
    int number, reverse,n1;
    scanf("%d",&number);
    n1=number;
    while(n1>0){
        reverse=reverse*10+(n1%10);
        n1/=10;
    }
    printf("%d %d",number,reverse);
    return 0;
}

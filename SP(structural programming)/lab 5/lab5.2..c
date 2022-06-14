#include<stdio.h>

int main(){
    int a,b;
    scanf("%d%d",&a,&b);
    int a1,b1,Digit1,Digit2,flag=1;
    a1=a;
    while(a1>0){
        Digit1=a1%10;
        b1=b;
        flag=1;
        while(b1>0){
            Digit2=b1%10;
            if(Digit1==Digit2){
                flag=0;
            }
            b1/=10;
        }
        a1/=10;
        if(flag==1){
            printf("NE");
            break;
        }
    }
    if(flag==0){
        printf("DA");
    }
    return 0;
}

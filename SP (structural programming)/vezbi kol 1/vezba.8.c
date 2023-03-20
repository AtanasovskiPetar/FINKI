#include<stdio.h>

int main(){
    int n,m,digit1,digit2,temp1,temp2,k=0;
    scanf("%d%d",&n,&m);
    for(int i=n-1;i>=0;i--){
        temp2=m;
        k=0;
        while(temp2>0){
            digit2=temp2%10;
            temp1=i;
            while(temp1>0){
                digit1=temp1%10;
                temp1/=10;
                if(digit1==digit2){
                    k++;
                }
            }
            temp2/=10;
        }
        if(k==0){
                printf("%d",i);
                break;
            }
    }
    return 0;
}

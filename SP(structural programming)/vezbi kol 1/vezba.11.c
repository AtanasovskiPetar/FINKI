#include<stdio.h>

int main(){
    int n,n1,digit1,digit2,i=0,k=0;
    while(scanf("%d",&n))
    {
        if(n>10){
            n1=n;
            i=0;
            k=0;
            while(n1>10){
                i++;
                digit1=n1%10;
                digit2=(n1%100)/10;
                n1/=10;
                if(digit1<5 && digit2>=5);
                else if(digit1>=5 && digit2<5);
                else{
                    k++;
                    break;
                }
            }
            if(k==0){
                printf("%d\n",n);
            }
        }
    }
    return 0;
}

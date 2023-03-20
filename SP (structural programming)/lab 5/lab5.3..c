#include <stdio.h>

int main()
{
    int  n,temp1;
    int flag=1;
    int Joker=0;
    int t=1000000;
    for(int i=0; i<7; i++){
        scanf("%d", &n);
        if(n<1 || n>90){
            flag=0;
            break;
        }
        temp1=n%10;
        Joker=Joker+(t*temp1);
        t/=10;
    }
    while(flag){
        scanf("%d", &n);
        if(n<1 || n>90){
            flag=0;
            break;
        }
        Joker=Joker+n;
    }
    printf("%d", Joker);

    return 0;
}

#include <stdio.h>

int main(){
    int x,n,i,a,n1,digit,NumDigits,koef=1,NewNum;
    scanf("%d%d",&x,&n);
    if(x==0){
        return 0;
    }
    for(i=0;i<n;i++){
        scanf("%d",&a);
        koef=1;
        NumDigits=0;
        if(a>9){
            n1=a;
            while(n1>0){
                digit=n1%10;
                n1/=10;
                NumDigits++;
            }
            for(int j=1;j<NumDigits;j++){
                koef*=10;
            }
            NewNum=(a%koef)*10+digit;
            if(NewNum%x==0){
                printf("DA\n");
            }
            else{
                printf("NE\n");
            }
        }
    }
    return 0;
}

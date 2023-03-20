#include <stdio.h>

int obratenBroj(int n){
    int n1=0;
    while(n>0){
        n1=10*(n1)+(n%10);
        n/=10;
    }
    return n1;
}
int sumaCifri(int n){
    int sum=0;
    while(n>0){
        sum+=n%10;
        n/=10;
    }
    return sum;
}
void pecatiVoInterval (int a, int b){
    int i,n,reverse,suma,n1;
    for(i=a;i<=b;i++){
        n=i;
        reverse=obratenBroj(n);
        n1=n+reverse;
        suma=sumaCifri(n1);
        if(n1%suma==0){
            printf("%d\n", n);
        }
    }
}

int main()
{
    int a,b;
    scanf("%d%d",&a,&b);
    pecatiVoInterval(a,b);
    return 0;
}

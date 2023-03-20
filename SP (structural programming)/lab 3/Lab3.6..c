#include <stdio.h>
#include <math.h>
int main()
{
    int n, n1, a, broj;
    int x=10000;
    scanf("%d", &n);
    n1=n;
    if(n/10000>=1&&n/10000<=9){
        for(int i=1; i<=5; i++){
        a=n%10;
        n/=10;
        broj=broj+x*a;
        x/=10;
    }
    if(broj==n1)
        printf("Palindrom");
    else
        printf("Ne e palindrom");
    }
    else
        printf("Nevaliden vlez");
    return 0;
}

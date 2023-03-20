#include <stdio.h>

int main()
{
    int n;
    printf("Vnesi eden cel broj: \n");
    scanf("%d", &n);
    int a;
    int i=10;
    while(n>0){
        a=n%10;
        n=n/10;
        printf("%d , %d -ki\n", a,i);
        i=i*10;
    }
    return 0;
}

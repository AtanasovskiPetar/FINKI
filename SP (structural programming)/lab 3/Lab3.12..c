#include <stdio.h>
#include <math.h>
int main()
{
    int n;
    scanf("%d", &n);
    if(n%3==0)
    {
        if(n%5==0)
            printf("Tik - Tak");
        else
            printf("Tik");
    }
    else if(n%5==0)
    {
        printf("Tak");
    }
    else
        printf("Losh broj");
    return 0;
}

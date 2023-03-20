#include <stdio.h>

int main(){
    int a0,a1,a2,a3,a4;
    float Average;
    scanf("%d%d%d%d%d", &a0, &a1, &a2, &a3, &a4);
    Average=(a0+a1+a2+a3+a4)/5.0;
    printf("%.2f", Average);
    return 0;
}

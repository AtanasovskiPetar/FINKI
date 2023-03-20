#include <stdio.h>

int main(){
    float Transaction, Sum;
    scanf("%f%f", &Transaction,&Sum);
    Sum=Sum*1.18;
    printf("%d", (Transaction-Sum)>=1);
    return 0;
}

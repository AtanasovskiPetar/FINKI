#include <stdio.h>

int main(){
    float Smetka;
    scanf("%f", &Smetka);
    float x1,x2,x3,x4,x5;
    scanf("%f%f%f%f%f", &x1,&x2,&x3,&x4,&x5);
    printf("%d", Smetka+(x1+x2+x3+x4+x5)>0);
    return 0;
}

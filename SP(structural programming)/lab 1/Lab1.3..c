#include <stdio.h>
#include <math.h>
int main(){
    float Mass, Height;
    scanf("%f%f", &Mass, &Height);
    float BMI;
    Height/=100;
    BMI=Mass/pow(Height,2);
    printf("%.2f", BMI);
    return 0;
}

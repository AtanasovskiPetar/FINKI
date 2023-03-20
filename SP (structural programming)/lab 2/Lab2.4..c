#include <stdio.h>

int main(){
    float C, K, P, V, B, D, M, Destination;
    scanf("%f", &Destination);
    scanf("%f%f%f", &C,&K, &P);
    scanf("%f%f%f%f", &V, &B, &D, &M);
    float Taxi1;
    Taxi1=C+(Destination-K)*P;
    float Taxi2;
    Taxi2=B+(Destination/V)*M+(Destination*D);
    printf("%.2f %.2f\n", Taxi1, Taxi2);
    printf("%d", Taxi1<=Taxi2);
    return 0;
}

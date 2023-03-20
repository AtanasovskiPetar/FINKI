#include <stdio.h>
#include <math.h>
int main(){
    float a,b,c,D;
    scanf("%f%f%f", &a,&b,&c);
    D=(1.0*b*b)-(4*a*c);
    if(D>0){
        printf("Resenija na ravenkata se: %.2f i %.2f", ((((-1)*b)-sqrt(D))/2*a), ((((-1)*b)+sqrt(D))/2*a));
    }
    else if(D==0){
        printf("Dvojno resenie na ravenkata e: %.2f", ((-1)*b)/2*a);
    }
    else{
        printf("Ravenkata nema realni resenia");
    }
    return 0;
}

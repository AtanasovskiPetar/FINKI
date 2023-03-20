#include <stdio.h>

int main(){
    int Index, a1,a2,a3,a4,a5,a6;
    scanf("%d%d%d%d%d%d%d", &Index, &a1,&a2,&a3,&a4,&a5,&a6);
    float Average = (a1+a2+a3+a4+a5+a6)/6.0;
    printf("Prosek: %.3f\n", Average);
    printf("Student: %d\n", Index);
    int Year=20-(Index/10000);
    printf("%d godina\n", Year);
    printf("Nagraden: %d\n", Average>=9.5);
    return 0;
}

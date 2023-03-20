#include <stdio.h>
#include <math.h>
int main()
{
    float x1,x2,x3,y1,y2,y3;
    scanf("%f%f", &x1,&y1);
    scanf("%f%f", &x2,&y2);
    scanf("%f%f", &x3,&y3);
    float AB,BC,AC;
    AB=sqrt(pow((x1+x2),2)+pow((y1+y2),2));
    AC=sqrt(pow((x1+x3),2)+pow((y1+y3),2));
    BC=sqrt(pow((x2+x3),2)+pow((y2+y3),2));
    printf("%f\n%f\n%f\n", AB,BC,AC);
    float max;
    if(AB>BC){
        if(AB>AC)
            printf("AB");
        else
        printf("AC");
    }
    if(AB<BC){
        if(BC>AC)
            printf("BC");
        else
            printf("AC");
    }
    return 0;
}

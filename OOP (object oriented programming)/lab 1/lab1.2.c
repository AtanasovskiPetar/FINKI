#include <stdio.h>

struct tocka{
    double x;
    double y;
};
typedef struct tocka tocka;

struct otsecka{
    tocka t1;
    tocka t2;
};
typedef struct otsecka otsecka;

int se_secat(otsecka o1, otsecka o2){
    float k1,k2;
    k1=(o1.t2.y-o1.t1.y)/(o1.t2.x-o1.t1.x);
    k2=(o2.t2.y-o2.t1.y)/(o2.t2.x-o2.t1.x);
    float x;
    x=(k1*o1.t1.x-o1.t1.y+o2.t1.y-k2*o2.t1.x)/(k1-k2);
    if(x>=o1.t1.x && x<=o1.t2.x){
        return 1;
    }else{
        return 0;
    }
}

int main() {
    double x1, y1, x2, y2;
    scanf("%f %f %f %f", &x1, &y1, &x2, &y2);
    tocka t1 = { x1, y1 };
    tocka t2 = { x2, y2 };
    otsecka o1 = { t1, t2 };
    scanf("%f %f %f %f", &x1, &y1, &x2, &y2);
    tocka t3 = { x1, y1 };
    tocka t4 = { x2, y2 };
    otsecka o2 = { t3, t4 };
    printf("%d\n", se_secat(o1, o2));
    return 0;
}
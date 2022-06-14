#include<stdio.h>

void swap(int *x, int *y){
    int a=*x;
    *x=*y;
    *y=a;
}

double max(float *a,int n){
    int i;
    double max=*a;
    for(i=0;i<n;i++){
        if(*(a+i)>max){
            max=*(a+i);
        }
    }
    return max;
}
double min(float *a, int n){
    double min=*a;
    int i;
    for(i=0;i<n;i++){
        if(*(a+i)<min){
            min=*(a+i);
        }
    }
    return min;
}

void normalize(float *niza, int n){
    double minimum=min(niza,n);
    double maximum=max(niza,n);
    int i;
    for(i=0;i<n;i++){
        *(niza+i)=(*(niza+i)-minimum)/(maximum-minimum);
    }
}

void sort(float *a, int n){
    int i,j;
    for(i=0;i<n;i++){
        for(j=0;j<n-i-1;j++){
            if(*(a+j)<*(a+j+1)){
                swap((a+j),(a+j+1));
            }
        }
    }
}

int main () {

    float niza [200];
    int i,n;

    scanf("%d", &n);

    for (i=0;i<n;i++) {
        scanf("%f", &niza[i]);

    }

    printf("MAX -> %.3f\n", max(niza,n));
    printf("MIN -> %.3f\n", min(niza,n));

    //normalize(niza,n);
    sort(niza,n);

     for (i=0;i<n;i++) {
        printf("%.3f ", niza[i]);

    }

    return 0;

}

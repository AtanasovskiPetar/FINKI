#include <stdio.h>

int main(){
    int n,a,b,c,max,min,i;
    float rez;
    scanf("%d",&n);
    for(i=0;i<n;i++){
        scanf("%d%d%d",&a,&b,&c);
        if(a>b){
            max=a;
            min=b;
        }
        else if(a<=b){
            max=b;
            min=a;
        }
        if(c>max){
            max=c;
        }
        else if(c<min){
            min=c;
        }
        rez=(2*min)-(max/2.0)+abs(min-a);
        printf("%.2f\n",rez);
    }
    return 0;
}

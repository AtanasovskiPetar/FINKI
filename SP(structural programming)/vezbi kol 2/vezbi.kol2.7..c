#include <stdio.h>
#include <math.h>
#include <ctype.h>


void furthestElement(int *a, int m){
    float x,max=0;
    float s=0;
    int i;
    for(i=0;i<m;i++){
        s+=a[i];
    }
    s=s/m;
    for(i=0;i<m;i++){
        if(a[i]>s){
            x=a[i]-s;
        }else{
            x=s-a[i];
        }
        if(x>max){
            max=x;
        }
    }
    for(i=0;i<m;i++){
        if(a[i]>s){
            if((a[i]-s)==max){
                printf("%d ",a[i]);
                break;
            }
        }else{
            if((s-a[i])==max){
                printf("%d ",a[i]);
                break;
            }
        }
    }

}

int main(){
    int m,n,i,j;
    scanf("%d%d",&n,&m);
    int a[n][m];
    for(i=0;i<n;i++){
        for(j=0;j<m;j++){
            scanf("%d",&a[i][j]);
        }
    }
    for(i=0;i<n;i++){
        furthestElement(a[i], m);
    }
    return 0;
}

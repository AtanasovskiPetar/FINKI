#include <stdio.h>

int main(){
    int n,i;
    scanf("%d",&n);
    int *a;
    for(i=0;i<n;i++){
        scanf("%d",(a+i));
    }
    int ind,sum=0;
    scanf("%d",&ind);
    if(ind>n){
        printf("%d",0);
        return 0;
    }
    for(i=ind;i<n;i++){
        sum+=(*(a+i));
    }
    printf("%d",sum);
    return 0;
}

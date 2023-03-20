#include <stdio.h>

int main()
{
    int n,i,j=0;
    scanf("%d",&n);
    int a[n];
    for(i=0;i<n;i++){
        scanf("%d",&a[i]);
    }
    for(i=0;i<n;i++){
        printf("%d ",a[i]);
    }
    printf("\n");
    for(i=n-1;i>=0;i--){
        printf("%d ",a[i]);
    }
    printf("\n");
    for(i=n-1;i>=0;i-=2){
        j++;
    }
    int a1[j];
    int j1=j;
    for(i=n-1;i>=0;i-=2){
        a1[j1-1]=a[i];
        j1--;
        printf("%d ",a[i]);
    }
    printf("\n");
    for(i=0;i<j;i++){
        printf("%d ",a1[i]);
    }
    printf("\n");
    return 0;
}

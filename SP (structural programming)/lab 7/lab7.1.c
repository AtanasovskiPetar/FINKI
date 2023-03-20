#include <stdio.h>

void printArray(int a[], int n){
    int i,j;
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
}

int main()
{
    int n,i;
    scanf("%d",&n);
    int a[n];
    for(i=0;i<n;i++){
        scanf("%d",&a[i]);
    }
    printArray(a,n);
    return 0;
}

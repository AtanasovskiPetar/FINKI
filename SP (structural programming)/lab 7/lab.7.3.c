#include <stdio.h>

void readMatrix(int a[100][100], int n, int m){
    int i,j;
    for(i=0;i<n;i++){
        for(j=0;j<m;j++){
            scanf("%d",&a[i][j]);
        }
    }
}

int main()
{
    int n,m,i,j;
    scanf("%d%d",&n,&m);
    int a[100][100];
    readMatrix(a,n,m);
    int sum,sumMin=0,h=0;
    for(i=0;i<n;i++){
        sumMin+=a[i][0];
    }
    for(j=0;j<m;j++){
        sum=0;
        for(i=0;i<n;i++){
            sum+=a[i][j];
        }
        if(sum<sumMin){
            sumMin=sum;
            h=j;
        }
    }
    printf("%d",h);
    return 0;
}

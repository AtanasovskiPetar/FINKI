#include <stdio.h>

int main()
{
    int n,i,j,k,max=0;
    scanf("%d",&n);
    if(n>100)return 0;
    int a[n][n];
    for(i=0;i<n;i++){
        for(j=0;j<n;j++){
            scanf("%d",&a[i][j]);
        }
    }
    for(i=0;i<n;i++){
        k=0;
        for(j=0;j<n;j++){
                if(((a[i][j])<(a[i][j+1]))&&j!=n-1){
                    k++;
                }
                else{
                    if(k>max){
                       max=k;
                    }
                    k=0;
                }
        }
    }
    printf("%d",max+1);
    return 0;
}

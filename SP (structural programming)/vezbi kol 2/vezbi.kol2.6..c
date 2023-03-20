#include <stdio.h>

int main(){
    int m,n,i,j;
    scanf("%d%d",&n,&m);
    int a[n][m];
    for(i=0;i<n;i++){
        for(j=0;j<m;j++){
            scanf("%d",&a[i][j]);
        }
    }
    int counter=0;
    for(i=0;i<n;i++){
        for(j=0;j<m-2;j++){
            if(a[i][j]==1){
                if(a[i][j]==a[i][j+1]){
                    if(a[i][j+1]==a[i][j+2]){
                        counter++;
                        j=m;
                    }
                }
            }
        }
    }
    for(j=0;j<m;j++){
        for(i=0;i<n-2;i++){
            if(a[i][j]==1){
                if(a[i][j]==a[i+1][j]){
                    if(a[i+1][j]==a[i+2][j]){
                        counter++;
                        i=n;
                    }
                }
            }
        }
    }
    printf("%d",counter);
    return 0;
}

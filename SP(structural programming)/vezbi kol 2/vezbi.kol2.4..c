#include <stdio.h>

int main(){
    int n,m;
    scanf("%d%d",&n,&m);
    int a[n][m];
    int i,j;
    for(i=0;i<n;i++){
        for(j=0;j<m;j++){
            scanf("%d",&a[i][j]);
        }
    }
    int indN,indM;
    scanf("%d%d",&indN,&indM);
    int sum1=0,sum2=0,sum3=0,sum4=0;
    for(i=0;i<n;i++){
        for(j=0;j<m;j++){
            if(i<indN && j>=indM){
                sum1+=a[i][j];
            }
            else if(i<indN && j<indM){
                sum2+=a[i][j];
            }
            else if(i>=indN && j<indM){
                sum3+=a[i][j];
            }
            else if(i>=indN && j>=indM){
                sum4+=a[i][j];
            }
        }
    }
    printf("%d %d %d %d\n",sum1,sum2,sum3,sum4);
    return 0;
}

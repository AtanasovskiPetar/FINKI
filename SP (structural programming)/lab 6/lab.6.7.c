#include <stdio.h>

int main()
{
    int n,i,m,j,brojac;
    int a[100];
    scanf("%d",&n);
    for(i=0;i<n;i++){
        scanf("%d",&m);
        brojac=0;
        for(j=0;j<m;j++){
            scanf("%d",&a[j]);
        }
        for(j=0;j<m;j++){
            if(a[j]==a[m-1-j]){
                brojac++;
            }
        }
        printf("%.2f%%\n", brojac*100.0/j);
    }
    return 0;
}

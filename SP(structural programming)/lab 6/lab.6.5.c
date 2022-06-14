#include <stdio.h>

int main()
{
    int n,a,m,b,k,i,temp;
    int niza[100];
    scanf("%d",&n);
    for(a=0;a<n;a++){
        scanf("%d",&m);
        for(b=0;b<m;b++){
            scanf("%d",&niza[b]);
        }
        scanf("%d",&k);
        for(i=0;i<b;i++){
            if((i+1)%k==0){
                temp=niza[i-1];
                niza[i-1]=niza[i];
                niza[i]=temp;
            }
        }
        for(i=0;i<b;i++){
            printf("%d ",niza[i]);
        }
        printf("\n");
    }
    return 0;
}

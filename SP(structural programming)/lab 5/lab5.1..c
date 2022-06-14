#include <stdio.h>

int main()
{
    int N;
    scanf("%d", &N);
    int a,b,c,d,dMin;
    int flag=1;
    for(int i=0; i<N; i++){
        scanf("%d%d%d",&a,&b,&c);
        d=abs(a-b)+abs(b-c);
        if(flag){
            dMin=d;
            flag=0;
        }
        if(d<dMin){
            dMin=d;
        }
    }
    printf("%d", dMin);
    return 0;
}

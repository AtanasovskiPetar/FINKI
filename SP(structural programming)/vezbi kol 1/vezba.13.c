#include<stdio.h>

int main(){
    int n;
    scanf("%d",&n);
    int Index,temp,cifra;
    int grupa1[10000],grupa2[10000],grupa3[10000];
    int i=0,j=0,t=0;
    for(int u=0; u<n; u++){
        scanf("%d", &Index);
        temp=Index;
        cifra=temp%10;
        if(cifra<3){
            grupa1[i]=Index;
            i++;
        }
        else if(cifra>=3&&cifra<=5){
            grupa2[j]=Index;
            j++;
        }
        else{
            grupa3[t]=Index;
            t++;
        }
    }
    printf("Grupa 1\n");
    for(int i1=0; i1<i;i1++){
        printf("%d ",grupa1[i1]);
    }
    printf("\n");
    printf("Grupa 2\n");
    for(int j1=0; j1<j; j1++){
        printf("%d ",grupa2[j1]);
    }
    printf("\n");
    printf("Grupa 3\n");
    for(int t1=0; t1<t; t1++){
        printf("%d ",grupa3[t1]);
    }
    printf("\n");
    return 0;
}

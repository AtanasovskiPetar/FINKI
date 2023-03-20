#include<stdio.h>

int main(){
    int n,n1,cifra1,CifraMax=0,k=0;
    int v0=0,v1=0,v2=0,v3=0,v4=0;
    while(scanf("%d",&n)){
        if(n/10000<=9){
            k=0;
            CifraMax=0;
            n1=n;
            for(int i=0;n1>0;i++){
                cifra1=n1%10;
                if(cifra1>CifraMax){
                    CifraMax=cifra1;
                    k=i;
                }
                n1/=10;
            }
            if(k==0){
                v0++;
            }
            else if(k==1){
                v1++;
            }
            else if(k==2){
                v2++;
            }
            else if(k==3){
                v3++;
            }
            else if(k==4){
                v4++;
            }
        }
    }
    printf("0: %d\n", v0);
    printf("1: %d\n", v1);
    printf("2: %d\n", v2);
    printf("3: %d\n", v3);
    printf("4: %d\n", v4);
    return 0;
}

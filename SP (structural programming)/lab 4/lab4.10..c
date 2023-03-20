#include<stdio.h>

int main(){
    int n;
    scanf("%d",&n);
    int posledna, pretposledna, prva, vtora;
    if(n/1000000>0&&n/1000000<10){
        posledna=n%10;
        pretposledna=(n/10)%10;
        prva=n/1000000;
        vtora=(n/100000)%10;
        if (prva==0 || vtora==0 || (posledna+pretposledna)==0){
            printf("Nevaliden broj");
        }
        else if(n%prva==0 && n%(posledna+pretposledna)!=0){
            printf("DA");
        }
        else if(n%vtora!=0 && n%(posledna+pretposledna)!=0){
            printf("DA");
        }
        else{
            printf("NE");
        }
    }
    return 0;
}

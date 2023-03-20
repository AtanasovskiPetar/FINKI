#include<stdio.h>

int main(){
    int n, suma, m,SUM,MaxSUM;
    scanf("%d",&m);
    float Tax, TaxReturn=0.0,MaxTaxReturn=0.0;
    char c;
    for(int j=0;j<m;j++){
        scanf("%d",&n);
        SUM=0;
        TaxReturn=0;
        for(int i=0;i<n;i++){
            scanf("%d %c",&suma,&c);
            if(c=='A'){
                Tax=0.18*suma;
            }
            else if(c=='B'){
                Tax=0.05*suma;
            }
            else if(c=='V'){
                Tax=0;
            }
            TaxReturn=TaxReturn+(Tax*0.15);
            SUM+=suma;
        }
        if(SUM<=30000){
            if(TaxReturn>MaxTaxReturn){
                MaxTaxReturn=TaxReturn;
                MaxSUM=SUM;
            }
            printf("Total tax return is: %.2f\n", TaxReturn);
        }
        else{
            printf("Sum %d is bigger than 30000\n",SUM);
        }
    }
    printf("Max amount of reciept: %d. Max tax return for reciepts: %.2f",MaxSUM,MaxTaxReturn);
    return 0;
}

#include <stdio.h>
#include <stdlib.h>

int main()
{
    int N,A,B,V;
    scanf("%d",&N);
    int Price;
    char TaxMark;
    float Tax;
    float TaxReturn=0;
    for(int i=0; i<N; i++){
        scanf("%d %c", &Price, &TaxMark);

        if(TaxMark=='A'){
            Tax=0.18*Price;
        }
        else if(TaxMark=='B'){
            Tax=0.05*Price;
        }
        else if(TaxMark=='V'){
            Tax=0.0*Price;
        }
        TaxReturn=TaxReturn+(Tax*0.15);
    }
    printf("Total tax return is: %.2f", TaxReturn);
    return 0;
}

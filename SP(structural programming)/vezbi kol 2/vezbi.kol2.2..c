#include <stdio.h>
#include <ctype.h>
#include <string.h>
#define MAX 100

//ne menuvaj!
void wtf() {
    FILE *f = fopen("broevi.txt", "w");
    char c;
    while((c = getchar()) != EOF) {
        fputc(c, f);
    }
    fclose(f);
}

int mostValiuableDigit(int n){
    int n1=n,counter=1;
    while(n1>0){
        counter*=10;;
        n1/=10;
    }
    counter/=10;
    return(n/counter);
}

int main()
{
    wtf();

    FILE *f1=fopen("broevi.txt", "r");

    char line[500];
    int counter=0;
    while(fgets(line,500,f1)!=NULL){
        counter++;
    }
    counter--;
    fclose(f1);

    FILE *f=fopen("broevi.txt", "r");
    int number,max=1;
    int n,i,j,k,k1;
    int a[100];
    for(j=0;j<counter;j++){
        max=0;
        k=0,k1;
        fscanf(f,"%d",&n);
        for(i=0;i<n;i++){
            fscanf(f,"%d",&number);
            if(mostValiuableDigit(number)>max){
                max=mostValiuableDigit(number);
            }
            a[k]=number;
            k++;
        }
        for(k1=0;k1<k;k1++){
            if(mostValiuableDigit(a[k1])==max){
                printf("%d\n",a[k1]);
                break;
            }
        }
    }
    fclose(f);

    return 0;
}

#include <stdio.h>
#include <math.h>
int main(){
    int n,n1;
    scanf("%d", &n);
    n1=n;
    int a,b,c,d;
    a=n%10;
    if (a==5)
        a=6;
    b=(n/10)%10;
        if(b==5)
        b=6;
    c=(n/100)%10;
    if(c==5)
        c=6;
    d=(n/1000);
    if(d==5)
        d=6;
    if(((a==6)&&((b==6)||(c==6)||(d==6)))||((b==6)&&((a==6)||(c==6)||(d==6)))||((c==6)&&((a==6)||(b==6)||(d==6)))||((d==6)&&((a==6)||(b==6)||(c==6)))){
    int Kolega = a+10*b+100*c+1000*d;
    if(Kolega>=n1){
        printf("%f %%\n", (Kolega-n1)*100.0/n1);
    }
    else {
        printf("%f %%\n", (n1-Kolega)*100.0/Kolega);
    }
    }
    else{
        printf("Brojot ne sodrzi dve petki");
    }
    return 0;
}

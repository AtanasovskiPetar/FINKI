#include<stdio.h>

int main(){
    int a, brojac=0;
    char c;
    do{
        scanf("%c", &c);
        if(c>='0' && c<='9'){
            a=c-'0';
            brojac+=a;
        }
        else if(c>='A' && c<='F'){
            a=c-55;
            brojac+=a;
        }
        else if(c>='a' && c<='f'){
            a=c-87;
            brojac+=a;
        }
    }
    while(c!='.');
    if(brojac%16==0){
        if(brojac%100==16){
            printf("Poln pogodok");
        }
        else{
            printf("Pogodok");
        }
    }
    else{
        printf("%d", brojac);
    }
    return 0;
}

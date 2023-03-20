#include <stdio.h>

int main(){
    int a;
    scanf("%d", &a);
    if (a/1000000>=1&&a/1000000<=9){
        for(int i=1; i<=7; i++){
            printf("%d\n", a%10);
            a/=10;
        }
    }
    else{
        printf("Brojot ne e 7 cifren.");
    }
    return 0;
}

#include <stdio.h>

int main(){
    char c;
    int x=0,sum=0;;
    do{
        scanf("%c", &c);
        if(c>='0' && c<='9'){
            x=x*10+(c-'0');
        }
        else{
            sum+=x;
            x=0;
        }
    }while(c!='!');
    printf("%d",sum);
    return 0;
}

/*
Neparen ekvivalent
*/
#include<stdio.h>

int main(){
    int number,type,entry;
    scanf("%d",&number);
    entry=number%3;
    type=number/100;
    if(type==1){
        printf("Vip posetitel na vlez ");
    }
    else{
        printf("Regularen posetitel na vlez ");
    }
    switch(entry){
    case(0): printf("1."); break;
    case(1): printf("2."); break;
    case(2): printf("3."); break;
    }
    return 0;
}

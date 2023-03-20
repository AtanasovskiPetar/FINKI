#include<stdio.h>

int main(){
    for(int i=1;i<1000;i++){
        if(i%3==0){
            printf("Tip");
        }
        if(i%5==0){
            printf("Top");
        }
        else if(i%3!=0 && i%5!=0){
            printf("%d",i);
        }
        printf(" ");
    }
    return 0;
}

#include <stdio.h>

int main(){
    int Sec;
    scanf("%d", &Sec);
    int Hours, Minutes;
    Hours=Sec/3600;
    Minutes=(Sec-(Hours*3600))/60;
    int Seconds=(Sec-(Hours*3600+Minutes*60));
    printf("%d sekundi se %d casovi, %d minuti i %d sekundi",Sec, Hours, Minutes, Seconds);
    return 0;
}

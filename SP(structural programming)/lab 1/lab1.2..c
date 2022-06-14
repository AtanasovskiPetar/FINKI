#include <stdio.h>

int main(){
    int a;
    scanf("%d", &a);
    int FiveThousands = a/5000;
    printf("%d*5000\n", FiveThousands);
    int OneThousands = (a-FiveThousands*5000)/1000;
    printf("%d*1000\n", OneThousands);
    int FiveHoundreds = (a-(FiveThousands*5000+OneThousands*1000))/500;
    printf("%d*500\n", FiveHoundreds);
    int OneHoundreds = (a-(FiveThousands*5000+OneThousands*1000+FiveHoundreds*500))/100;
    int Fifties = (a-(FiveThousands*5000+OneThousands*1000+FiveHoundreds*500+OneHoundreds*100))/50;
    int Tens = (a-(FiveThousands*5000+OneThousands*1000+FiveHoundreds*500+OneHoundreds*100+Fifties*50))/10;
    int Fives = (a-(FiveThousands*5000+OneThousands*1000+FiveHoundreds*500+OneHoundreds*100+Fifties*50+Tens*10))/5;
    int Twos = (a-(FiveThousands*5000+OneThousands*1000+FiveHoundreds*500+OneHoundreds*100+Fifties*50+Tens*10+Fives*5))/2;
    int Ones = (a-(FiveThousands*5000+OneThousands*1000+FiveHoundreds*500+OneHoundreds*100+Fifties*50+Tens*10+Fives*5+Twos*2))/1;
    printf("%d*100\n", OneHoundreds);
    printf("%d*50\n", Fifties);
    printf("%d*10\n", Tens);
    printf("%d*5\n", Fives);
    printf("%d*2\n", Twos);
    printf("%d*1\n", Ones);
    return 0;
}

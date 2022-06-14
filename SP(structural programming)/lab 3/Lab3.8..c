#include <stdio.h>
#include <math.h>
int main()
{
    int date, day, month, year;
    scanf("%d", &date);
    year=date%10000;
    if(year/1000>=1&&year/1000<=9)
    {
    month=(date/10000)%100;
        if(month>=1&&month<=12){
            day=(date/1000000);
                if((month==1)||(month==3)||(month==5)||(month==7)||(month==8)||(month==10)||(month==12))
                {
                    if(day>=1&&day<=31)
                        printf("DA");
                    else
                        printf("NE");
                }
                if((month==4)||(month==6)||(month==9)||(month==11))
                {
                    if(day>=1&&day<=30)
                        printf("DA");
                    else
                        printf("NE");
                }
                if(month==2)
                {
                    if((year%400==0)||(year%4==0&&year%100!=0))
                    {
                        if(day>=1&&day<=29)
                            printf("DA");
                        else
                            printf("NE");
                    }
                }
                 if(month==2)
                {
                    if(!((year%400==0)||(year%4==0&&year%100!=0)))
                    {
                        if(day>=1&&day<=28)
                            printf("DA");
                        else
                            printf("NE");
                    }
                }

    }
    else
        printf("NE");
    }
    else
        printf("NE");
    return 0;
}

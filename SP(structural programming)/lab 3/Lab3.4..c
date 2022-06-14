#include <stdio.h>

int main()
{
    int Maticen;
    scanf("%d", &Maticen);
    int den, mesec, godina;
    godina=Maticen%1000+1000;
    Maticen/=1000;
    mesec=Maticen%100;
    Maticen/=100;
    den=Maticen;
    int UserName;
    UserName=godina+mesec*1000+den*100;
    if(UserName/10000!=0)
        printf("%d", UserName);
    else{
        if(godina<=1960)
            printf("%d", 70000+UserName);
        if(godina>=1961&&godina<=1980)
            printf("%d", 80000+UserName);
        if(godina>=1981&&godina<=1999)
            printf("%d", 90000+UserName);
    }
    return 0;
}

//Od golema mala vo golema bukva i obratno

#include <stdio.h>
#include <stdlib.h>

int main()
{
    char c;
    scanf("%c", &c);
    if (c>='Z')
        printf("%c", c-('a'-'A'));
    else
        printf("%c", c+('a'-'A'));
    return 0;
}

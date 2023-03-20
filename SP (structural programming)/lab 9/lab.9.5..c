#include <stdio.h>
#include <ctype.h>
#include <string.h>

float letterFrequency(char *text, char letter){
    int i;
    float counter=0;
    for(i=0;i<strlen(text);i++){
        if(text[i]==letter){
            counter++;
        }
    }
    return counter/strlen(text)*100;
}

int main () {
    char text[1000];
    fgets(text, sizeof(text), stdin);
    char letter;
    scanf("%c",&letter);
    letter=tolower(letter);
    printf("%c -> %.3f%%\n",letter, letterFrequency(text,letter));
    letter=toupper(letter);
    printf("%c -> %.3f%%\n",letter, letterFrequency(text,letter));
    return 0;
}

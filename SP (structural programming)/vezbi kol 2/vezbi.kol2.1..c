#include <stdio.h>
#include <ctype.h>
#include <string.h>

void writeToFile() {
  FILE *f = fopen("text.txt", "w");
  char c;
  while((c = getchar()) != '#') {
    fputc(c, f);
  }
  fclose(f);
}

int isVowel(char c){
    c=tolower(c);
    if((c=='a')||(c=='e')||(c=='i')||(c=='o')||(c=='u')){
        return 1;
    }
    else{
        return 0;
    }
}

int main() {

  writeToFile();

    FILE *f=fopen("text.txt", "r");
    char curr,prev='f';
    int counter=0;
    while((curr=getc(f))!=EOF){
        if(isalpha(curr)){
            if(isVowel(curr)&&isVowel(prev)){
                printf("%c%c\n",tolower(prev),tolower(curr));
                counter++;
            }
        }
        prev=curr;
    }
    printf("%d",counter);
    fclose(f);
    return 0;
}


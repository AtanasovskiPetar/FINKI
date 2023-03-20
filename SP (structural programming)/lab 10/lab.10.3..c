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

int numberOfWords(char *line, int n){
    int sum=1;
    int i;
    for(i=0;i<n;i++){
        if(line[i]==' '){
            sum++;
        }
    }
    return sum;
}

void printReverse(char *line, int n){
    int i=0;
    while(i<n){
        if(isalpha(line[i])){
            if(line[i]>='a' && line[i]<='z'){
                printf("%c",toupper(line[i]));
            }
            else{
                printf("%c",tolower(line[i]));
            }
        }
        else{
            printf("%c",line[i]);
        }
        i++;
    }
}

int main() {
    writeToFile();
    FILE *f = fopen("text.txt", "r");
    char line [500];
    int counter=0,total=0,max=0,i;
    while(fgets(line,500,f)!=NULL){
        line[strlen(line)-1]='\0';
        counter++;
        printf("%d\n", numberOfWords(line, strlen(line)));
        total+=numberOfWords(line,strlen(line));
        if((numberOfWords(line,strlen(line)))>max){
            max=numberOfWords(line,strlen(line));
        }
    }
    printf("%.2f\n",(float)total/counter);
    fclose(f);

    FILE *f1 = fopen("text.txt", "r");
    char line1[500];
    while(fgets(line1,500,f1)!=NULL){
        if((numberOfWords(line1,strlen(line1)))==max){
            printReverse(line1, strlen(line1));
            break;
        }
    }

    fclose(f1);
    return 0;
}


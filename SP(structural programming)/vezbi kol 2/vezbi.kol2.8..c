#include <stdio.h>
#include <math.h>
#include <ctype.h>
#include <string.h>

int isValid(char *line, int n){
    int i=0;
    int counter=0;
    for(i=0;i<n;i++){
        if(line[i]>='0'&&line[i]<='9'){
            counter++;
        }
        if(counter>=2){
            return 1;
            break;
        }
    }
    return 0;
}

void wtf() {
    FILE *f = fopen("dat.txt", "w");
    char c;
    while((c = getchar()) != EOF) {
        fputc(c, f);
    }
    fclose(f);
}

void printTransformedLine(char *line, int n){
    int i;
    int startIndex=0,endIndex=9;
    for(i=0;i<n;i++){
        if(line[i]>='0' && line[i]<='9'){
            startIndex=i;
            break;
        }
    }
    for(i=n-1;i>=0;i--){
        if(line[i]>='0' && line[i]<='9'){
            endIndex=i;
            break;
        }
    }
    for(i=startIndex;i<=endIndex;i++){
        printf("%c",line[i]);
    }
}

int main() {
    wtf();
	// vasiot kod ovde
    FILE *f = fopen("dat.txt", "r");
    char line[100];
    int max=0,counter=0;
    int maxIndex;
    while(fgets(line,100,f)!=NULL){
        counter++;
        if(strlen(line)>=max && isValid(line,strlen(line))){
            max=strlen(line);
            maxIndex=counter;
        }
    }
    fclose(f);
    FILE *f1=fopen("dat.txt", "r");
    int i;
    char line1[100];
    for(i=0;i<=counter;i++){
        fgets(line1,100,f1);
        if(i==maxIndex-1){
            printTransformedLine(line1,strlen(line1));
        }
    }

    return 0;
}

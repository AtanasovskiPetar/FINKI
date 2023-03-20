
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

int main() {
    writeToFile();
    FILE *f = fopen("text.txt", "r");
    char curr;
    int lowerCounter=0,upperCounter=0,counter=0;
    while((curr=fgetc(f))!=EOF){
        printf("%c",curr);
        if(isalpha(curr)){
            if(curr==islower(curr)){
                lowerCounter++;
            }
            else{
                upperCounter++;
            }
            counter++;
        }
    }
    printf("%f\n%f",1.0*lowerCounter/counter, 1.0*upperCounter/counter);
    fclose(f);
    return 0;
}

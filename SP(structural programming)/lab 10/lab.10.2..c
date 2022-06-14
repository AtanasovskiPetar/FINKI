#include <stdio.h>
#include <ctype.h>
#include <string.h>


void writeToFile() {
    FILE *f = fopen("input.txt", "w");
    char c;
    while((c = getchar()) != '#') {
        fputc(c, f);
    }
    fclose(f);
}

void printFile() {

    FILE *f=fopen("output.txt","r");
    char line[100];
    while(!feof(f)){
        fgets(line,100,f);
        if (feof(f))
            break;
        printf("%s",line);
    }
    fclose(f);
}

int main() {
    writeToFile();
    FILE *input = fopen("input.txt","r");
    FILE *output = fopen("output.txt","w");
    int i,j,n;
    fscanf(input, "%d", &n);
    int matrix[n][n];
    for(i=0;i<n;i++){
        for(j=0;j<n;j++){
            fscanf(input,"%d", &matrix[i][j]);
        }
    }

    int sum=0;
    for(i=0;i<n;i++){
        for(j=0;j<n;j++){
            if(i==j){
                sum+=matrix[i][j];
            }
        }
    }

    for(i=0;i<n;i++){
        for(j=0;j<n;j++){
            if(i<j){
                fprintf(output,"%03d ", sum);
            }else{
                fprintf(output,"    ");
            }
        }
            fprintf(output,"\n");
    }

    fclose(input);
    fclose(output);
    printFile();
    return 0;
}

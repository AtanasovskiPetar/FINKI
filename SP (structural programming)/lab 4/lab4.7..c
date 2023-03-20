#include <stdio.h>
#include <stdlib.h>

int main()
{
    int N;
    scanf("%d", &N);
    int a[N];
    int MaxRazlika;
    for(int i=0; i<N; i++){
        scanf("%d", &a[i]);
    }
    MaxRazlika=abs(a[0]-a[1]);
    for(int j=0; j<N; j++){
        for(int k=0; k<N; k++){
            if(abs(a[j]-a[k])>MaxRazlika){
                MaxRazlika=abs(a[j]-a[k]);
            }
        }
    }
    printf("Najgolema razlika: %d", MaxRazlika);
    return 0;
}

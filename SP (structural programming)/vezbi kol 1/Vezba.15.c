#include<stdio.h>

int main(){
    int n,Poeni;
    int polozeni[1000], padnati[1000];
    int f=0, t=0;
    float VrednostPadnati=0,VrednostPolozeni=0;
    scanf("%d",&n);
    for(int i=0; i<n; i++){
        scanf("%d", &Poeni);
        if(Poeni<50){
            padnati[f]=Poeni;
            f++;
        }
        else{
            polozeni[t]=Poeni;
            t++;
        }
    }
    for(int f1=0;f1<f;f1++){
        VrednostPadnati+=padnati[f1];
    }
    printf("Sredna vrednost na padnati %.2f\n", VrednostPadnati*1.0/f);
    for(int f1=0;f1<f;f1++){
        printf("%d ",padnati[f1]);
    }
    printf("\n");
    for(int t1=0;t1<t;t1++){
        VrednostPolozeni+=polozeni[t1];
    }
    printf("Sredna vrednost na polozeni %.2f\n", VrednostPolozeni*1.0/t);
    for(int t1=0;t1<t;t1++){
        printf("%d ",polozeni[t1]);
    }
    return 0;
}

#include <stdio.h>

struct Product{
    char name[50];
    float price;
    float amount;
};
typedef struct Product Product;

void readProduct(Product *x){
    scanf("%s",x->name);
    scanf("%f",&x->price);
    scanf("%f",&x->amount);
}
void printProduct(Product x){
    printf("%s\t%.2f x %.1f = %.2f",x.name, x.price, x.amount, (x.price)*(x.amount));
}

int main(){
    int n;
    int i;
    Product product;
    scanf("%d",&n);
    float total=0.0;
    for(i=0;i<n;i++){
        readProduct(&product);
        printf("%d. ",i+1);
        printProduct(product);
        printf("\n");
        total+=(product.price*product.amount);
    }
    printf("Total: %.2f", total);
    return 0;
}
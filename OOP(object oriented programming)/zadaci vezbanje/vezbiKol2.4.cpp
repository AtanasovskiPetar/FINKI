#include <cstring>
#include <iostream>
using namespace std;

enum Size{mala,golema,familijarna};

class Pizza{
protected:
    char name[20];
    char sostojki[100];
    int cena;
public:
    Pizza(const char name[20]="", const char sostojki[100]="", int cena=0){
        strcpy(this->name,name);
        strcpy(this->sostojki,sostojki);
        this->cena = cena;
    }
    virtual double price()=0;
};

class FlatPizza:public Pizza{
private:
    Size golemina;
public:
    FlatPizza(const char name[20]="", const char sostojki[20]="", int cena=0, enum Size golemina=mala):Pizza(name,sostojki,cena){
        this->golemina=golemina;
    }
    double price(){
        switch (golemina){
            case(0): return cena*1.1;
            case(1): return cena*1.3;
            case(2): return cena*1.3;
            default: return cena;
        }
    }
    friend ostream & operator << (ostream & out, FlatPizza&f){
        out<<f.name<<": "<<f.sostojki<<", ";
        switch (f.golemina){
            case(0): out<<"small"; break;
            case(1): out<<"medium"; break;
            case(2): out<<"family"; break;
            default: break;
        }
        out<<" - "<<f.price()<<endl;
        return out;
    }
};

class FoldedPizza:public Pizza{
private:
    bool belo;
public:
    FoldedPizza(const char name[20]="", const char sostojki[20]="", int cena=0, bool belo=true):Pizza(name,sostojki,cena){
        this->belo=belo;
    }
    double price(){
        if(belo){
            return 1.1*cena;
        }
        return 1.3*cena;
    }
    friend ostream & operator << (ostream & out, FoldedPizza&f){
        out<<f.name<<": "<<f.sostojki<<", ";
        if(f.belo)
            out<<"wf";
        else   
            out<<"nwf";
        out<<" - "<<f.price()<<endl;
        return out;
    }
    void setWhiteFlour(bool t){
        belo=t;
    }
};

bool operator < (Pizza & p1, Pizza & p2){
    if(p1.price()<p2.price()){
        return true;
    }
    return false;
}

void expensivePizza(Pizza **pizza, int n){
    int index=0;
    for(int i=0;i<n;i++){
        if(*(pizza[index])<*(pizza[i]))
            index=i;
    }
    Pizza *p=dynamic_cast<FlatPizza*>(pizza[index]);
    if(p){
        FlatPizza *p1=dynamic_cast<FlatPizza*>(p);
        cout<<*(p1);
        delete [] p1;
    }
    else{
        FoldedPizza *p1=dynamic_cast<FoldedPizza*>(pizza[index]);
        cout<<*(p1);
        delete [] p1;
    }
    
}

int main() {
  int test_case;
  char name[20];
  char ingredients[100];
  float inPrice;
  Size size;
  bool whiteFlour;

  cin >> test_case;
  if (test_case == 1) {
    // Test Case FlatPizza - Constructor, operator <<, price
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    FlatPizza fp(name, ingredients, inPrice);
    cout << fp;
  } else if (test_case == 2) {
    // Test Case FlatPizza - Constructor, operator <<, price
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    int s;
    cin>>s;
    FlatPizza fp(name, ingredients, inPrice, (Size)s);
    cout << fp;
    
  } else if (test_case == 3) {
    // Test Case FoldedPizza - Constructor, operator <<, price
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    FoldedPizza fp(name, ingredients, inPrice);
    cout << fp;
  } else if (test_case == 4) {
    // Test Case FoldedPizza - Constructor, operator <<, price
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    FoldedPizza fp(name, ingredients, inPrice);
    fp.setWhiteFlour(false);
    cout << fp;

  } else if (test_case == 5) {
	// Test Cast - operator <, price
    int s;
    
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    cin>>s;
    FlatPizza *fp1 = new FlatPizza(name, ingredients, inPrice, (Size)s);
    cout << *fp1;
      
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    cin>>s;
    FlatPizza *fp2 = new FlatPizza(name, ingredients, inPrice, (Size)s);
    cout << *fp2;
      
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    FoldedPizza *fp3 = new FoldedPizza(name, ingredients, inPrice);
    cout << *fp3;
      
    cin.get();
    cin.getline(name,20);
    cin.getline(ingredients,100);
    cin >> inPrice;
    FoldedPizza *fp4 = new FoldedPizza(name, ingredients, inPrice);
    fp4->setWhiteFlour(false);
    cout << *fp4;
      
    cout<<"Lower price: "<<endl;
    if(*fp1<*fp2)
        cout<<fp1->price()<<endl;
    else cout<<fp2->price()<<endl;
        
    if(*fp1<*fp3)
        cout<<fp1->price()<<endl;
    else cout<<fp3->price()<<endl;    
        
    if(*fp4<*fp2)
        cout<<fp4->price()<<endl;
    else cout<<fp2->price()<<endl;
    
    if(*fp3<*fp4)
        cout<<fp3->price()<<endl;
    else cout<<fp4->price()<<endl;

  } else if (test_case == 6) {
	// Test Cast - expensivePizza
    int num_p;
    int pizza_type;

    cin >> num_p;
    Pizza **pi = new Pizza *[num_p];
    for (int j = 0; j < num_p; ++j) {

      cin >> pizza_type;
      if (pizza_type == 1) {
        cin.get();
        cin.getline(name,20);
          
        cin.getline(ingredients,100);
        cin >> inPrice;
        int s;
        cin>>s;
        FlatPizza *fp = new FlatPizza(name, ingredients, inPrice, (Size)s);
        cout << (*fp);
        pi[j] = fp;
      }
      if (pizza_type == 2) {

        cin.get();
        cin.getline(name,20);
        cin.getline(ingredients,100);
        cin >> inPrice;
        FoldedPizza *fp =
            new FoldedPizza (name, ingredients, inPrice);
        if(j%2)
          (*fp).setWhiteFlour(false);
        cout << (*fp);
        pi[j] = fp;

      }
    }

    cout << endl;
    cout << "The most expensive pizza:\n";
    expensivePizza(pi,num_p);


  }
  return 0;
}



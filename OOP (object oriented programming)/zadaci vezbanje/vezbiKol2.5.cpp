#include <iostream>
#include <cstring>
using namespace std;
const int MAX=50;

enum typeC
{
    standard,
    loyal,
    vip
};

class UserExistsException{
public:
    void message(){
        cout<<"The user already exists in the list!"<<endl;
    }
};

class Customer
{
protected:
    char name[50];
    char email[50];
    typeC type;
    int startSale;
    int bonusSale;
    int boughtProducts;

public:
    Customer(const char name[50] = "",
             const char email[50] = "",
             typeC type = standard, int boughtProducts = 0, int startSale = 0,
             int bonusSale = 0)
    {
        strcpy(this->name, name);
        strcpy(this->email, email);
        this->type = type;
        if(type == 0){
            this->startSale = 0;
            this->bonusSale = 0;
        }
        else if(type == 1){
            this->startSale = 10;
            this->bonusSale = 0;
        }
        else {
            this->startSale = 10;
            this->bonusSale = 20;
        }
        this->boughtProducts = boughtProducts;
    }
    friend ostream & operator << (ostream & out, Customer & c){
        out<<c.name<<endl;
        out<<c.email<<endl;
        out<<c.boughtProducts<<endl;
        switch (c.type)
        {
        case 0:
            out<<"standard ";
            break;
        case 1: 
            out<<"loyal ";
            break;
        case 2:
            out<<"vip ";
            break;
        default:
            break;
        }
        out<<c.bonusSale + c.startSale<<endl;
        return out;
    }
    void setDiscount1(int n){
        startSale=n;
    }
    char * getEmail(){
        return email;
    }
    int getBoughtProducts(){
        return boughtProducts;
    }
    typeC getType(){
        return type;
    }
    void setType(typeC ty){
        type=ty;
    }
    void setDiscount2(int n){
        bonusSale=n;
    }
};

class FINKI_bookstore {
private:
    Customer *customers;
    int numCustomers;
    void copy(const FINKI_bookstore & f){
        this->numCustomers=f.numCustomers;
        this->customers= new Customer[this->numCustomers];
        for(int i=0;i<this->numCustomers;i++){
            this->customers[i]=f.customers[i];
        }
    }
public:
    FINKI_bookstore(){
        this->customers=new Customer[0];
        this->numCustomers=0;
    }
    FINKI_bookstore(Customer * customers, int numCustomers){
        this->numCustomers=numCustomers;
        this->customers= new Customer[this->numCustomers];
        for(int i=0;i<this->numCustomers;i++){
            this->customers[i]=customers[i];
        }
    }
    FINKI_bookstore(const FINKI_bookstore & f){
        copy(f);
    }
    FINKI_bookstore & operator = (const FINKI_bookstore &f){
        if(this!=&f){
            delete [] customers;
            copy(f);
        }return *this;
    }
    ~FINKI_bookstore(){
        delete [] customers;
    }
    void setCustomers(Customer *customers, int n){
        this->numCustomers=n;
        this->customers = new Customer[this->numCustomers];
        for(int i = 0; i < this->numCustomers; i++){
            this->customers[i]=customers[i];
        }
    }
    friend ostream & operator << (ostream & out, FINKI_bookstore & f){
        for(int i=0;i<f.numCustomers;i++){
            out<<f.customers[i];
        }
        return out;
    }
    FINKI_bookstore & operator += (Customer &c){
        for(int i=0;i<this->numCustomers;i++){
            if(strcmp(c.getEmail(),customers[i].getEmail())==0)
                throw UserExistsException();
        }
        Customer *tmp = new Customer[numCustomers+1];
        for(int i=0;i<this->numCustomers;i++){
            tmp[i]=customers[i];
        }
        tmp[numCustomers++]=c;
        delete [] customers;
        customers = tmp;
        return *this;
    }
    void update(){
        for(int i=0;i<this->numCustomers;i++){
            if(customers[i].getBoughtProducts()  > 5 && customers[i].getType()==0){
                customers[i].setType(loyal);
                customers[i].setDiscount1(10);
                customers[i].setDiscount2(0);
            }
            else if(customers[i].getBoughtProducts()  > 10 && customers[i].getType()==1){
                customers[i].setType(vip);
                customers[i].setDiscount1(10);
                customers[i].setDiscount2(20);
            }
        }
    }
};

int main(){
  int testCase;
  cin >> testCase;

  char name[MAX];
  char email[MAX];
  int tC;
  int discount;
  int numProducts;


  if (testCase == 1){
    cout << "===== Test Case - Customer Class ======" << endl;
    cin.get();
    cin.getline(name,MAX);
    cin.getline(email,MAX);
    cin >> tC;
    cin >> numProducts;
    cout << "===== CONSTRUCTOR ======" << endl;
    Customer c(name, email, (typeC) tC, numProducts);
    cout << c;

  }
  if (testCase == 2){
    cout << "===== Test Case - Static Members ======" << endl;
    cin.get();
    cin.getline(name,MAX);
    cin.getline(email,MAX);
    cin >> tC;
    cin >> numProducts;
    cout << "===== CONSTRUCTOR ======" << endl;
    Customer c(name, email, (typeC) tC, numProducts);
    cout << c;

    c.setDiscount1(5);

    cout << c;
  }
  if (testCase == 3){
    cout << "===== Test Case - FINKI-bookstore ======" << endl;
    FINKI_bookstore fc;
    int n;
    cin >> n;
    Customer customers[MAX];
    for(int i = 0; i < n; ++i) {
      cin.get();
      cin.getline(name,MAX);
      cin.getline(email,MAX);
      cin >> tC;
      cin >> numProducts;
      Customer c(name, email, (typeC) tC, numProducts);
      customers[i] = c;
    }

    fc.setCustomers(customers, n);

    cout << fc <<endl;
  }
  if (testCase == 4){
    cout << "===== Test Case - operator+= ======" << endl;
    FINKI_bookstore fc;
    int n;
    cin >> n;
    Customer customers[MAX];
    for(int i = 0; i < n; ++i) {
      cin.get();
      cin.getline(name,MAX);
      cin.getline(email,MAX);
      cin >> tC;
      cin >> numProducts;
      Customer c(name, email, (typeC) tC, numProducts);
      customers[i] = c;
    }

    fc.setCustomers(customers, n);
    cout << "OPERATOR +=" << endl;
    cin.get();
    cin.getline(name,MAX);
    cin.getline(email,MAX);
    cin >> tC;
    cin >> numProducts;
    Customer c(name, email, (typeC) tC, numProducts);
    fc+=c;

    cout << fc;
  }
  if (testCase == 5){
    cout << "===== Test Case - operator+= (exception) ======" << endl;
    FINKI_bookstore fc;
    int n;
    cin >> n;
    Customer customers[MAX];
    for(int i = 0; i < n; ++i) {
      cin.get();
      cin.getline(name,MAX);
      cin.getline(email,MAX);
      cin >> tC;
      cin >> numProducts;
      Customer c(name, email, (typeC) tC, numProducts);
      customers[i] = c;
    }

    fc.setCustomers(customers, n);
    cout << "OPERATOR +=" << endl;
    cin.get();
    cin.getline(name,MAX);
    cin.getline(email,MAX);
    cin >> tC;
    cin >> numProducts;
    Customer c(name, email, (typeC) tC, numProducts);
    try{
    fc+=c;
    }
    catch (UserExistsException uee){
        uee.message();
    }
    catch (...){
        cout<<"The user already exists in the list"<<endl;
    }
    cout << fc;
  }
  if (testCase == 6){
    cout << "===== Test Case - update method  ======" << endl << endl;
    FINKI_bookstore fc;
    int n;
    cin >> n;
    Customer customers[MAX];
    for(int i = 0; i < n; ++i) {
      cin.get();
      cin.getline(name,MAX);
      cin.getline(email,MAX);
      cin >> tC;
      cin >> numProducts;
      Customer c(name, email, (typeC) tC, numProducts);
      customers[i] = c;
    }

    fc.setCustomers(customers, n);

    cout << "Update:" << endl;
    fc.update();
    cout << fc;
  }
  
  return 0;
   
}
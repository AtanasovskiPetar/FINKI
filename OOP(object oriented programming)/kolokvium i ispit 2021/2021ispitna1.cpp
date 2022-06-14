#include <cstring>
#include <iostream>

using namespace std;

class InvalidOperation{
public:
    void message(){
        cout<<"Can’t merge two different cryptocurrencies."<<endl;
    }
};

class Cryptocurrency{
private:
    char *name;
    char code[7];
    double price;
    double quantity;
    static double provision;
    void copy(const Cryptocurrency & c){
        this->name=new char[strlen(c.name)+1];
        strcpy(this->name,c.name);
        strcpy(this->code,c.code);
        this->price=c.price;
        this->quantity=c.quantity;
    }
public:
    Cryptocurrency(const char *name="", const char code[7]="",
                   double price=0, double quantity=0){
        this->name=new char[strlen(name)+1];
        strcpy(this->name,name);
        strcpy(this->code,code);
        this->price=price;
        this->quantity=quantity;
    }
    Cryptocurrency(const Cryptocurrency &c){
        copy(c);
    }
    Cryptocurrency & operator =(const Cryptocurrency &c){
        if(this!=&c){
            delete [] name;
            copy(c);
        }return *this;
    }
    ~Cryptocurrency(){
        delete [] name;
    }
    static void setProvision (double p){
        provision=p;
    }
    static double getProvision(){
        return provision;
    }
    friend ostream & operator << (ostream & out, Cryptocurrency &c){
        out<<c.code<<" "<<c.name<<" "<<c.price<<" "<<c.quantity<<" "<<c.price*c.quantity<<endl;
        return out;
    }
    Cryptocurrency & operator += (Cryptocurrency & c){
        if((strcmp(code, c.code)==0) && (strcmp(name,c.name)==0)){
            this->price=c.price;
            this->quantity+=c.quantity;
        }else{
            throw InvalidOperation();
            return *this;
        }
        return *this;
    }
    double sell (){
        double q = quantity;
        double p = 1.0*provision/100;
        this->quantity=0;
        return (1.0*price*q)-(p*q*price);
    }
    bool operator == (Cryptocurrency & c){
        if(strcmp(this->name,c.name)==0){
            if(strcmp(this->code,c.code)==0){
                return true;
            }
        }return false;
    }
};
double Cryptocurrency::provision = 2.5;

class Wallet{
private:
    char name[40];
    Cryptocurrency *crypto;
    int numCrypto;
    void copy(const Wallet & w){
        strcpy(this->name,w.name);
        this->numCrypto=w.numCrypto;
        this->crypto=new Cryptocurrency[numCrypto];
        for(int i=0;i<numCrypto;i++){
            this->crypto[i]=w.crypto[i];
        }
    }
public:
    Wallet(const char name[40]=""){
        strcpy(this->name,name);
        this->numCrypto=0;
        this->crypto=new Cryptocurrency[numCrypto];
    }
    Wallet(const Wallet & w){
        copy(w);
    }
    Wallet & operator = (const Wallet & w){
        if(this!=&w){
            delete [] crypto;
            copy(w);
        }return *this;
    }
    ~Wallet(){
        delete [] crypto;
    }
    friend ostream & operator << (ostream & out, Wallet & w){
        out<<w.name<<endl<<"Cryptocurrencies:"<<endl;
        if(w.numCrypto==0){
            cout<<"EMPTY"<<endl;
            return out;
        }
        for(int i=0;i<w.numCrypto;i++){
            out<<w.crypto[i];
        }
        return out;
    }
    Wallet & operator +=(Cryptocurrency & c){
        for(int i=0;i<numCrypto;i++){
            if(crypto[i]==c){
                crypto[i]+=c;
                return *this;
            }
        }
        Cryptocurrency * tmp = new Cryptocurrency[numCrypto+1];
        for(int i=0;i<numCrypto;i++){
            tmp[i]=crypto[i];
        }
        tmp[numCrypto++]=c;
        delete [] crypto;
        crypto=tmp;
        return *this;
    }
};

int main() {
    int testCase;
    cin >> testCase;
    if (testCase == 0) {
        cout << "Cryptocurrency constructors" << endl;
        Cryptocurrency c1("Cardano", "ADA", 1.45);
        Cryptocurrency c2("Cardano", "ADA", 1.45, 2.5);
        cout << "TEST PASSED" << endl;
    } else if (testCase == 1) {
        cout << "Cryptocurrency copy-constructor and operator =" << endl;
        Cryptocurrency c2("Cardano", "ADA", 1.45, 2.5);
        Cryptocurrency c1 = c2; //copy-constructor
        Cryptocurrency c3("BITCOIN", "BTC", 35000, 0.0001);
        c3 = c2;
        cout << "TEST PASSED" << endl;
    } else if (testCase == 2) {
        cout << "Cryptocurrency operator <<" << endl;
        Cryptocurrency c1("Cardano", "ADA", 1.45);
        Cryptocurrency c2("Cardano", "ADA", 1.45, 2.5);
        cout << c1;
        cout << c2;
    }else if (testCase == 3) {
        cout << "Cryptocurrency operator += (normal behavior)" << endl;
        Cryptocurrency c1("Cardano", "ADA", 1.45, 8);
        Cryptocurrency c2("Cardano", "ADA", 1.39, 2.5);
        cout<<c1<<"+="<<endl<<c2<<"-->"<<endl;
        cout << (c1 += c2);
    } else if (testCase == 4) {
        cout << "Cryptocurrency operator += (abnormal behavior)" << endl;
        Cryptocurrency c1("Cardano", "ADA", 1.45, 8);
        Cryptocurrency c2("Bitcoin", "BTC", 35000, 0.0008);
        cout<<c1<<"+="<<endl<<c2<<"-->"<<endl;
        try{
        cout << (c1 += c2);
        }
        catch (InvalidOperation iO){
            iO.message();
        }
    } else if (testCase == 5) {
        cout << "Cryptocurrency method sell and static members test" << endl;
        int n;
        cin >> n;
        Cryptocurrency *cryptocurrencies1 = new Cryptocurrency[n];
        Cryptocurrency *cryptocurrencies2 = new Cryptocurrency[n];
        char name[50], code[7];
        double price, quantity;
        for (int i = 0; i < n; i++) {
            cin >> name >> code >> price >> quantity;
            cryptocurrencies1[i] = Cryptocurrency(name, code, price, quantity);
            cryptocurrencies2[i] = Cryptocurrency(name, code, price, quantity);
        }
        cout << "BEFORE CHANGE OF PROVISION PERCENT" << endl;
        for (int i = 0; i < n; i++) {
            cout << "    BEFORE SELLING --> " << cryptocurrencies1[i];
            cout << "     PROFIT FROM SELLING -->" << cryptocurrencies1[i].sell() << endl;
            cout << "    AFTER SELLING --> " << cryptocurrencies1[i];
        }
        Cryptocurrency::setProvision(5.1);
        cout << "AFTER CHANGE OF PROVISION PERCENT" << endl;
        for (int i = 0; i < n; i++) {
            cout << "    BEFORE SELLING --> " << cryptocurrencies2[i];
            cout << "     PROFIT FROM SELLING -->" << cryptocurrencies2[i].sell() << endl;
            cout << "    AFTER SELLING --> " << cryptocurrencies2[i];
        }

        delete[] cryptocurrencies1;
        delete[] cryptocurrencies2;
    } else if (testCase == 6) {
        cout << "Wallet constructors" << endl;
        Wallet w1("John Doe");
        Wallet w2("John Doe");
        cout << "TEST PASSED" << endl;
    } else if (testCase == 7) {
        cout << "Wallet copy-constructor and operator =" << endl;
        Wallet w1("John Doe");
        Wallet w2("John Doe");
        Wallet w3 = w1; //copy constructor;
        w2 = w1; //operator =
        cout << "TEST PASSED" << endl;
    } else if (testCase == 8) {
        cout << "Wallet operator <<" << endl;
        Wallet w1("John Doe");
        cout << w1;
    } else if (testCase == 9) {
        cout << "Wallet += and <<" << endl;
        Wallet wallet("John Doe");
        int n;
        cin >> n;
        char name[50], code[7];
        double price, quantity;
        for (int i = 0; i < n; i++) {
            cin >> name >> code >> price >> quantity;
            Cryptocurrency c(name, code, price, quantity);
            wallet += c;
        }
        cout << wallet;
    }

    return 0;
}



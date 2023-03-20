/*
Да се развие класа Nediviznina за коjа се чуваат следниве информации:

адреса (динамички алоцирана низа од знаци)
квадратура (цел боj)
цена за квадрат (цел боj)
За оваа класа да се имплементираат соодветните конструктори и следните методи:

cena() коj ќе jа враќа цената на недвижнината (квадратура * цена-за-квадрат)
pecati() коj ќе ги испечати сите информации за истата
danokNaImot() коj го ваќа данокот што се плаќа за недвижнината, а истиот се пресметува како 5% од цената на недвижнината.
Од оваа класа да се изведе класа Vila за коjа дополнително се чува данок на луксуз (цел боj, пр. 10%). За оваа класа да се преоптоварат методите:

pecati()
danokNaImot() со тоа што пресметаниот данок се зголемува процентуално за данокот на луксуз.
И за двете класи треба да се преоптовари operator >>.
*/

#include <iostream>
#include <cstring>
using namespace std;

class Nedviznina{
protected:
    char *adress;
    int area;
    int pricePerSqM;
    void copy(const Nedviznina &n){
        this->adress=new char [strlen(n.adress)+1];
        strcpy(this->adress,n.adress);
        this->area=n.area;
        this->pricePerSqM=n.pricePerSqM;
    }
public:
    Nedviznina(char *adress="", int area=0, int pricePerSqM=0){
        this->adress=new char [strlen(adress)+1];
        strcpy(this->adress,adress);
        this->area=area;
        this->pricePerSqM=pricePerSqM;
    }
    Nedviznina(const Nedviznina &n){
        copy(n);
    }
    Nedviznina & operator =(const Nedviznina &n){
        if(this!=&n){
            delete [] adress;
            copy(n);
        }return *this;
    }
    ~Nedviznina(){
        delete [] adress;
    }
    int cena(){
        return area*pricePerSqM;
    }
    float danokNaImot(){
        return 1.0*cena()*0.05;
    }
    void pecati(){
        cout<<adress<<", Kvadratura: "<<area<<", Cena po Kvadrat: "<<pricePerSqM<<endl;
    }
    friend istream & operator >>(istream & in, Nedviznina & n){
        in>>n.adress>>n.area>>n.pricePerSqM;
        return in;
    }
    char * getAdresa()const{
        return adress;
    }
};

class Vila:public Nedviznina{
private:
    int danokNaLuksiz;
    void copy(const Vila &v){
        Nedviznina::copy(v);
        this->danokNaLuksiz=v.danokNaLuksiz;
    }
public:
    Vila():Nedviznina(){
        this->danokNaLuksiz=0;
    }
    Vila(Nedviznina &n, int danokNaLuksiz=0):Nedviznina(n){
        this->danokNaLuksiz=danokNaLuksiz;
    }
    Vila(const Vila &v){
        copy(v);
    }
    Vila & operator=(const Vila &v){
        if(this!=&v){
            delete [] adress;
            copy(v);
        }return *this;
    }
    void pecati(){
        cout<<adress<<", Kvadratura: "<<area<<", Cena po Kvadrat: "<<pricePerSqM<<", Danok na luksuz: "<<danokNaLuksiz<<endl;
    }
    double danokNaImot(){
        return Nedviznina::danokNaImot()+1.0*danokNaLuksiz/100*cena();
    }
    friend istream & operator>>(istream & in, Vila & v){
        in>>v.adress>>v.area>>v.pricePerSqM>>v.danokNaLuksiz;
        return in;
    }
};

int main(){
    Nedviznina n;
    Vila v;
    cin>>n;
    cin>>v;
    n.pecati();
    cout<<"Danok za: "<<n.getAdresa()<<", e: "<<n.danokNaImot()<<endl;
    v.pecati();
    cout<<"Danok za: "<<v.getAdresa()<<", e: "<<v.danokNaImot()<<endl;
    return 0;
}

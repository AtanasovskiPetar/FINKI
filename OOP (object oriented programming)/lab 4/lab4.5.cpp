#include <iostream>
#include <cstring>
#include <string>

using namespace std;

//Vasiot kod tuka

class Pica{
private:
    char name[15];
    int price;
    char *ingredients;
    int sale;
    void copy(const Pica & p){
        strcpy(this->name,p.name);
        this->price=p.price;
        this->ingredients=new char[strlen(p.ingredients)+1];
        strcpy(this->ingredients, p.ingredients);
        if(p.sale>=0 && p.sale<=100){
            this->sale=p.sale;
        }else{
            this->sale=0;
        }
    }
public:
    Pica(char *name="", int price=0, char *ingredients="", int sale=0){
        strcpy(this->name,name);
        this->price=price;
        this->ingredients=new char[strlen(ingredients)+1];
        strcpy(this->ingredients, ingredients);
        if(sale>=0 && sale<=100){
            this->sale=sale;
        }else{
            this->sale=0;
        }
    }
    Pica(const Pica & p){
        copy(p);
    }
    Pica & operator=(const Pica & p){
        if(this!=&p){
            delete [] ingredients;
            copy(p);
        }
        return *this;
    }
    ~Pica(){
        delete [] ingredients;
    }
    void pecati(){
        cout<<name<<" - "<<ingredients<<", "<<price<<" "<<price*(1-sale*1.0/100.0)<<endl;
    }

    bool istiSe(const Pica &p){
        if(strcmp(ingredients, p.ingredients)==0){
            return true;
        }else{
            return false;
        }
    }
    int getSale() const{
        return sale;
    }
};

class Picerija {
private:
    char name[15];
    Pica *pici;
    int numOfPizas;
    void copy(const Picerija &p){
        strcpy(this->name,name);
        this->numOfPizas=p.numOfPizas;
        this->pici = new Pica[this->numOfPizas];
        for(int i=0;i<numOfPizas;i++){
            this->pici[i]=p.pici[i];
        }
    }
public:
    Picerija(char *name=""){
        strcpy(this->name,name);
        this->numOfPizas=0;
        this->pici = new Pica[numOfPizas];
    }
    Picerija(const Picerija &p){
        copy(p);
    }
    Picerija & operator=(const Picerija &p){
        if(this!=&p){
            delete [] pici;
            copy(p);
        }
        return *this;
    }
    ~Picerija(){
        delete [] pici;
    }
    void dodadi(const Pica &p){
        for(int i=0;i<numOfPizas;i++){
            if(pici[i].istiSe(p)){
                return;
            }
        }
        Pica *tmp = new Pica[numOfPizas+1];
        for(int i=0;i<numOfPizas;i++){
            tmp[i]=pici[i];
        }
        tmp[numOfPizas++]=p;
        delete [] pici;
        pici=tmp;
    }
    void pecati(){
        cout<<name<<endl;
        for(int i=0; i<numOfPizas;i++){
            pici[i].pecati();
        }
    }
    void piciNaPromocija() {
        for(int i=0;i<numOfPizas;i++){
            if(pici[i].getSale()>0 && pici[i].getSale()<100){
                pici[i].pecati();
            }
        }
    }
    void setIme(char *n){
        strcpy(name,n);
    }
    string getIme() const{
        return name;
    }
};

int main () {

    int n;
    char ime[15];
    cin >> ime;
    cin >> n;

    Picerija p1(ime);
    for(int i = 0; i < n; i++){
        char imp[100];
        cin.get();
        cin.getline(imp,100);
        int cena;
        cin >> cena;
        char sostojki[100];
        cin.get();
        cin.getline(sostojki,100);
        int popust;
        cin >> popust;
        Pica p(imp,cena,sostojki,popust);
        p1.dodadi(p);
    }

    Picerija p2 = p1;
    cin >> ime;
    p2.setIme(ime);
    char imp[100];
    cin.get();
    cin.getline(imp,100);
    int cena;
    cin >> cena;
    char sostojki[100];
    cin.get();
    cin.getline(sostojki,100);
    int popust;
    cin >> popust;
    Pica p(imp,cena,sostojki,popust);
    p2.dodadi(p);

    cout<<p1.getIme()<<endl;
    cout<<"Pici na promocija:"<<endl;
    p1.piciNaPromocija();

    cout<<p2.getIme()<<endl;
    cout<<"Pici na promocija:"<<endl;
    p2.piciNaPromocija();

	return 0;
}

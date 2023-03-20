/*
Да се креира хиерархиjа на класи за репрезентациjа на жичани инструменти. За потребите на оваа хиерархиjа да се дефинира класа ZicanInstrument од коjа ќе бидат изведени двете класи Mandolina и Violina.

Во класата ZicanInstrument се чуваат податоци за:

името на инструментот (низа од 20 знаци)
броjот на жици
основната цена на инструментот.
За класата Mandolina дополнително се чува неjзината форма (низа од 20 знаци).

За класата Violina дополнително се чува неjзината големина (децимален броj).

За секоjа изведените класи треба да се дефинираат соодветните конструктори и следните методи:

cena() за пресметување на цената на инструментот
основната цена на мандолината се зголемува за 15% доколку таа има форма во Неаполитански стил (вредноста на променливата форма е “Neapolitan”)
основната цена на виолината се зголемува за 10% ако неjзината големина има вредност 1/4 (0.25), односно за 20% ако неjзината големина има вредност 1 (1.00)
проптоварување на операторот ==, коj ги споредува жичаните инструменти според броjот на жици што го имаат
преоптоварување на операторот << за печатење на сите податоци за жичаните инструменти.
Да се напише функциjа pecatiInstrumenti(ZicanInstrument &zi, ZicanInstrument **i, int n) коjа на влез прима жичан инструмент, низа од покажувачи кон жичани инструменти и броj на елементи во низата. Функциjата jа печати цената на сите жичани инструменти од низата кои имаат ист броj на жици како и инструментот проследен како прв аргумент на функциjата.
*/

#include <iostream>
#include <cstring>
#include <cmath>
using namespace std;

class ZicanInstrument{
protected:
    char name[20];
    int strings;
    double price;
public:
    ZicanInstrument(char name[20]="", int strings=0, double price=0.0){
        strcpy(this->name,name);
        this->strings=strings;
        this->price=price;
    }
    virtual double cena(){return this->price;}
    int getStrings()const{
        return strings;
    }
    virtual ~ZicanInstrument(){}
     char * getName(){
        return name;
    }
};

class Mandolina:public ZicanInstrument{
private:
    char forma[20];
public:
    Mandolina(char forma[20]=""):ZicanInstrument(){
        strcpy(this->forma,forma);
    }
    Mandolina(ZicanInstrument &z, char forma[20]=""):ZicanInstrument(z){
        strcpy(this->forma,forma);
    }
    Mandolina(char name[20]="", int strings=0, double price=0.0, char forma[20]=""){
        strcpy(this->name,name);
        this->strings=strings;
        this->price=price;
        strcpy(this->forma,forma);
    }
    double cena(){
        if(strcmp(forma,"Neapolitan")==0){
            return ZicanInstrument::cena()*1.15;
        }else{
            return ZicanInstrument::cena();
        }
    }
    bool operator ==(const ZicanInstrument &z){
        return(z.getStrings()==this->strings);
    }
    friend ostream & operator <<(ostream & out, const Mandolina &z){
        out<<z.name<<" "<<z.strings<<" "<<z.price<<" "<<z.forma<<endl;
        return out;
    }
    ~Mandolina(){}
};

class Violina:public ZicanInstrument{
private:
    double size;
public:
    Violina(double size=0.0):ZicanInstrument(){
        this->size=size;
    }
    Violina(ZicanInstrument &z, double size=0.0):ZicanInstrument(z){
        this->size=size;
    }
    Violina(char name[20]="", int strings=0, double price=0.0, double size=0.0){
        strcpy(this->name,name);
        this->strings=strings;
        this->price=price;
        this->size=size;
    }
    double cena(){
        if(size==0.25){
            return ZicanInstrument::cena()*1.1;
        }else if(size==1){
            return ZicanInstrument::cena()*1.2;
        }else{
            return ZicanInstrument::cena();
        }
    }
    bool operator ==(const ZicanInstrument &z){
        return(z.getStrings()==this->strings);
    }
    friend ostream & operator <<(ostream & out, const Violina &z){
        out<<z.name<<" "<<z.strings<<" "<<z.price<<" "<<z.size<<endl;
        return out;
    }
    ~Violina(){}
};

void pecatiInstrumenti(ZicanInstrument &zi, ZicanInstrument **instrument, int n){
    int zici=zi.getStrings();
    for(int i=0;i<n;i++){
        if(instrument[i]->getStrings()==zici){
            cout<<instrument[i]->cena()<<endl;
        }
    }
}

int main() {
	char ime[20];
	int brojZici;
	float cena;
	char forma[20];
	cin >> ime >> brojZici >> cena >> forma;
	Mandolina m(ime, brojZici, cena, forma);
	int n;
	cin >> n;
	ZicanInstrument **zi = new ZicanInstrument*[2 * n];
	for(int i = 0; i < n; ++i) {
		cin >> ime >> brojZici >> cena >> forma;
		zi[i] = new Mandolina(ime, brojZici, cena, forma);
	}
	for(int i = 0; i < n; ++i) {
		float golemina;
		cin >> ime >> brojZici >> cena >> golemina;
		zi[n + i] = new Violina(ime, brojZici, cena, golemina);
	}
	pecatiInstrumenti(m, zi, 2 * n);
	for(int i = 0; i < 2 * n; ++i) {
		delete zi[i];
	}
	delete [] zi;
	return 0;
}

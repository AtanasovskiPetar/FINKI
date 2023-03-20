/*
Да се дефинира класа Ekipa за коjа се чуваат следниве информации:

името на екипата (низа од наjмногу 15 знаци)
броj на порази
броj на победи
За оваа класа да се дефинира метод pecati() коjа ги печати податоците за екипаta. Од оваа класа да се изведe новa класa, FudbalskaEkipa.

За фудбалската екипа дополнително се чуваат информации за:

вкупниот броj на црвени картони
вкупниот броj жолти картони
броjот на нерешени натпревари
За фудбалската екипа да се преоптовари методот pecati(), така што покрај останатите информации, ќе се испечатат и бројот на нерешени резултати и вкупен број на поени во формат: Име на екипа, броj на победи, броj на порази, броj на нерешени натпревари и вкупен броj на поени (за победа фудбалската екипа добива 3 поени, додека за нерешен резултата, 1 поен);
*/

#include <iostream>
#include <cstring>
using namespace std;

class Ekipa{
protected:
    char name[15];
    int losses;
    int wins;
public:
    Ekipa(char name[15]="", int losses=0, int wins=0){
        strcpy(this->name,name);
        this->losses=losses;
        this->wins=wins;
    }
    void pecati(){
        cout<<"Ime: "<<name<<" Pobedi: "<<wins<<" Porazi: "<<losses;
    }
};

class FudbalskaEkipa:public Ekipa{
private:
    int yellowCards;
    int redCards;
    int draws;
public:
    FudbalskaEkipa(const Ekipa &e, int yellowCards=0, int redCards=0, int draws=0):Ekipa(e){
        this->yellowCards=yellowCards;
        this->redCards=redCards;
        this->draws=draws;
    }
    FudbalskaEkipa(char name[15]="", int wins=0, int losses=0 ,int redCards=0, int yellowCards=0, int draws=0){
        strcpy(this->name,name);
        this->losses=losses;
        this->wins=wins;
        this->yellowCards=yellowCards;
        this->redCards=redCards;
        this->draws=draws;
    }
    void pecati(){
        Ekipa::pecati();
        int points = 3*wins+draws;
        cout<<" Nereseni: "<<draws<<" Poeni: "<<points<<endl;
    }
};
int main(){
	char ime[15];
	int pob,por,ck,zk,ner;
	cin>>ime>>pob>>por>>ck>>zk>>ner;
	FudbalskaEkipa f1(ime,pob,por,ck,zk,ner);
	f1.pecati();
	return 0;
}

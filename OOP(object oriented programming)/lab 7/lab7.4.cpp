/*
Да се дефинира класа Vozilo која ќе содржи информација за неговата маса (децимален број), ширина и висина (цели броеви).

Од оваа класа да се изведе класата Автомобил во која како дополнителна информација се чува информацијата за бројот на врати (цел број).

Од класата возило да се изведе и класата Автобус во која се чуваат информации и за бројот на патници кои може да ги пренесува.

Од класата возило да се изведе класата Камион во која се чуваат информации и за максималната маса која може да се товари на него (децимална вредност).

За сите класи да се креираат погодни контруктори, како и set и get функции.

Да се дефинира класа ParkingPlac за која се чува динамичко алоцирано поле од покажувачи кон Vozilo, како и бројот на елементи во полето. Во оваа класа да се дефинираат:

конструктор
деструктор
операторот += за додавање на ново возило (аргументот е покажувач кон Vozilo)
функција float presmetajVkupnaMasa() со која се пресметува вкупната маса на сите возила во паркинг плацот
функција int brojVozilaPoshirokiOd(int l) со која се пресметува бројот на возила кои се пошироки од дадената вредност
функција void pecati() со која се печати: Brojot na avtomobili e X, brojot na avtobusi e Y i brojot na kamioni e Z.
функција int pogolemaNosivostOd(Vozilo& v) во која се враќа бројот на сите камиони кои имаат носивост поголема од масата на возилото предадено како аргумент.
Да се дефинира виртуелна функција int vratiDnevnaCena() во класата Vozilo и истата да се преоптовари во сите изведени класи. За секој автомобил со помалку од 5 врати дневната цена е 100, а инаку е 130 денари. За секој камион цената се пресметува со формулата: (masa+nosivost)*0.02. За секој автобус цената е 5 денари по лице кое може да се пренесува.

Во класата ParkingPlac да се додаде следната функција: - функција int vratiDnevnaZarabotka() со која се враќа дневната заработка од сите возила на паркингот.
*/

#include <iostream>
#include <cstring>
using namespace std;

class Vozilo{
protected:
    double weight;
    int width;
    int height;
public:
    Vozilo(double weight=0, int width=0, int height=0){
        this->weight=weight;
        this->width=width;
        this->height=height;
    }
    virtual int getType(){
        return 0;
    }
    virtual double getWeight(){
        return weight;
    }
    virtual int getHeight(){
        return height;
    }
    virtual int getWidth(){
        return width;
    }
    virtual double getAddition1(){
        return 0;
    }
    virtual int getAddition(){
        return 0;
    }
    virtual int vratiDnevnaCena(){
        return 1;
    }
};

class Avtomobil:public Vozilo{
private:
    int doors;
public:
    Avtomobil(double weight=0, int width=0, int height=0, int doors=0):Vozilo(weight, width, height){
        this->doors=doors;
    }
    int getType(){
        return 1;
    }
    int getAddition(){
        return doors;
    }
    int vratiDnevnaCena(){
        if(doors<5){
            return 100;
        }else{
            return 130;
        }
    }
};

class Avtobus:public Vozilo{
private:
    int passengers;
public:
    Avtobus(double weight=0, int width=0, int height=0, int passengers=0):Vozilo(weight, width, height){
        this->passengers=passengers;
    }
    int getType(){
        return 2;
    }
    int getAddition(){
        return passengers;
    }
    int vratiDnevnaCena(){
        return 5*passengers;
    }
};

class Kamion:public Vozilo{
private:
    double maxWeight;
public:
    Kamion(double weight=0, int width=0, int height=0, double maxWeight=0):Vozilo(weight, width, height){
        this->maxWeight=maxWeight;
    }
    int getType(){
        return 3;
    }
    double getAddition1(){
        return maxWeight;
    }
    int vratiDnevnaCena(){
        return(weight+maxWeight)*0.02;
    }
};

class ParkingPlac{
private:
    Vozilo **vehicle;
    int numOfVehicles;
    void copy(const ParkingPlac &p){
        this->numOfVehicles=p.numOfVehicles;
        for(int i=0;i<numOfVehicles;i++){
            if(p.vehicle[i]->getType()==0){
                this->vehicle[i]=new Vozilo(p.vehicle[i]->getWeight(),p.vehicle[i]->getWidth(),p.vehicle[i]->getHeight());
            }else if(p.vehicle[i]->getType()==1){
                this->vehicle[i]=new Avtomobil(p.vehicle[i]->getWeight(),p.vehicle[i]->getWidth(),p.vehicle[i]->getHeight(),p.vehicle[i]->getAddition());
            }else if(p.vehicle[i]->getType()==2){
                this->vehicle[i]=new Avtobus(p.vehicle[i]->getWeight(),p.vehicle[i]->getWidth(),p.vehicle[i]->getHeight(),p.vehicle[i]->getAddition());
            }else if(p.vehicle[i]->getType()==3){
                this->vehicle[i]=new Kamion(p.vehicle[i]->getWeight(),p.vehicle[i]->getWidth(),p.vehicle[i]->getHeight(),p.vehicle[i]->getAddition1());
            }
        }
    }
public:
    ParkingPlac(){
        this->numOfVehicles=0;
        this->vehicle=new Vozilo*[numOfVehicles];
    }
    ParkingPlac(const ParkingPlac &p){
        copy(p);
    }
    ParkingPlac & operator = (const ParkingPlac & p){
        if(this!=&p){
            delete [] vehicle;
            copy(p);
        }return *this;
    }
    ~ParkingPlac(){
        delete [] vehicle;
    }
    ParkingPlac & operator += (Vozilo *v){
        Vozilo **tmp=new Vozilo*[numOfVehicles+1];
        for(int i=0;i<numOfVehicles;i++){
            if(vehicle[i]->getType()==0){
                tmp[i]=new Vozilo(vehicle[i]->getWeight(),vehicle[i]->getWidth(),vehicle[i]->getHeight());
            }else if(vehicle[i]->getType()==1){
                tmp[i]=new Avtomobil(vehicle[i]->getWeight(),vehicle[i]->getWidth(),vehicle[i]->getHeight(),vehicle[i]->getAddition());
            }else if(vehicle[i]->getType()==2){
                tmp[i]=new Avtobus(vehicle[i]->getWeight(),vehicle[i]->getWidth(),vehicle[i]->getHeight(),vehicle[i]->getAddition());
            }else if(vehicle[i]->getType()==3){
                tmp[i]=new Kamion(vehicle[i]->getWeight(),vehicle[i]->getWidth(),vehicle[i]->getHeight(),vehicle[i]->getAddition1());
            }
        }
        if(v->getType()==0){
            tmp[numOfVehicles++]=new Vozilo(v->getWeight(),v->getWidth(),v->getHeight());
        }else if(v->getType()==1){
            tmp[numOfVehicles++]=new Avtomobil(v->getWeight(),v->getWidth(),v->getHeight(),v->getAddition());
        }else if(v->getType()==2){
            tmp[numOfVehicles++]=new Avtobus(v->getWeight(),v->getWidth(),v->getHeight(),v->getAddition());
        }else if(v->getType()==3){
            tmp[numOfVehicles++]=new Kamion(v->getWeight(),v->getWidth(),v->getHeight(),v->getAddition1());
        }
        delete [] vehicle;
        vehicle=tmp;
        return *this;
    }
    float presmetajVkupnaMasa(){
        double mass=0;
        for(int i=0;i<numOfVehicles;i++){
            mass+=vehicle[i]->getWeight();
        }return mass;
    }
    int brojVozilaPoshirokiOd(int l){
        int counter=0;
        for(int i=0;i<numOfVehicles;i++){
            if(vehicle[i]->getWidth()>l){
                counter++;
            }
        }return counter;
    }
    void pecati(){
        int counterAvtomobili=0, counterAvtobusi=0, counterKamioni=0;
        for(int i=0;i<numOfVehicles;i++){
            if(vehicle[i]->getType()==1){
                counterAvtomobili++;
            }else if(vehicle[i]->getType()==2){
                counterAvtobusi++;
            }else{
                counterKamioni++;
            }
        }
        cout<<"Brojot na avtomobili e "<<counterAvtomobili<<", brojot na avtobusi e "<<counterAvtobusi<<" i brojot na kamioni e "<<counterKamioni<<"."<<endl;
    }
    int pogolemaNosivostOd(Vozilo& v){
        int counter=0;
        for(int i=0;i<numOfVehicles;i++){
            if(vehicle[i]->getType()==3){
                if(vehicle[i]->getAddition1() > v.getAddition1()){
                    counter++;
                }
            }
        }
        return counter;
    }
    int vratiDnevnaZarabotka(){
        int zarabotka=0;
        for(int i=0;i<numOfVehicles;i++){
            zarabotka+=vehicle[i]->vratiDnevnaCena();
        }
        return zarabotka;
    }
};

int main(){
ParkingPlac p;

int n;
cin>>n;
int shirina,visina, broj;
float masa,nosivost;
for (int i=0;i<n;i++){
    int type;
    cin>>type;
    if(type==1){
        cin>>masa>>shirina>>visina>>broj;
        Avtomobil *a=new Avtomobil(masa,shirina,visina,broj);
        p+=a;
    }
    if(type==2){
        cin>>masa>>shirina>>visina>>broj;
        p+=new Avtobus(masa,shirina,visina,broj);
    }
    if(type==3){
        cin>>masa>>shirina>>visina>>nosivost;
        p+=new Kamion(masa,shirina,visina,nosivost);
    }
}

p.pecati();

cout<<"\nZarabotkata e "<<p.vratiDnevnaZarabotka()<<endl;
cout<<"Vkupnata masa e "<<p.presmetajVkupnaMasa()<<endl;
cout<<"Brojot poshiroki od 5 e "<<p.brojVozilaPoshirokiOd(5)<<endl;
Avtomobil a(1200,4,3,5);
cout<<"Brojot na kamioni so nosivost pogolema od avtomobilot e "<<p.pogolemaNosivostOd(a)<<endl;


}

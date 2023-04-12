/*
Да се напише класа Automobile во која се чуваат информации за марката на автомобилот (динамички алоцирана низа од знаци), регистрација (динамички алоцирана низа од 5 цели броја) и максимална брзина (цел број). За класата да се обезбедат set и get методите што се користат и да се преоптоварат следните оператори:

оператор == за споредување на два автомобила според регистрацијата

оператор << за печатење на податоци на автомобил во формат Marka:име Registracija:[x y z k l]

Да се напише класа RentACar за агенција за измајмување возила во која се чуваат информација за името на агенцијата (низа од 100 знци), низа од автомобили (динамички алоациана низа од објекти од класата Automobile) и број на автомобили со кој располага (цел број). Во класата RentACar да се напише конструктор со еден аргумент за иницијализација на името на агенцијата. При секое додавање на нов автомобил, динамички алоцираната низа да го зголемува капацитетот за 1 елемент. Во оваа класа да се преоптоварат операторите:

+= за додавање на нов автомобил во агенцијата и

-= за отстранување на даден автомобил од агенцијата (оној со иста регистрација).

Да се напише main функција во која се инстанцира објект од класата RentACar. Во овој објект да се додадат сите автомобили чии информации се читаат од тастатура со операторот +=. Меѓутоа, откриено е дека во внесувањето на податоците има грешка затоа што при обид да се додаде нов автомобил во агенцијата, увидено е дека таа регистрација веќе постои. Во последниот ред од влезот дадени се инфромации тој автомобил. Потребно е да се избрише автомобилот што претходно е погрешно внесен и да се додаде новиот.

На излез да се отпечатат името на агенцијата и листа на автомобили што таа ги изнајмува, а чија максимална брзина е поголема од 150. Последново да се направи со функција pecatiNadBrzina(int max) што треба да се дефинира во класата
*/

#include <iostream>
#include <cstring>
using namespace std;

class Automobile{
private:
    char *marka;
    int *registracija;
    int maxSpeed;
    friend class RentACar;
    void copy(const Automobile &a){
        this->marka=new char[strlen(a.marka)+1];
        strcpy(this->marka,a.marka);
        this->maxSpeed=a.maxSpeed;
        if(a.registracija!=0){
            this->registracija=new int[5];
            for(int i=0;i<5;i++){
                this->registracija[i]=a.registracija[i];
            }
        }else{
            this->registracija=new int[0];
        }
    }
public:
    Automobile(char *marka="", int *registracija=0, int maxSpeed=0){
        this->marka=new char[strlen(marka)+1];
        strcpy(this->marka,marka);
        this->maxSpeed=maxSpeed;
        if(registracija!=0){
            this->registracija=new int[5];
            for(int i=0;i<5;i++){
                this->registracija[i]=registracija[i];
            }
        }else{
            this->registracija=new int[0];
        }
    }
    Automobile (const Automobile &a){
        copy(a);
    }
    Automobile & operator=(const Automobile &a){
        if(this!=&a){
            delete [] registracija;
            delete [] marka;
            copy(a);
        }return *this;
    }
    friend ostream & operator <<(ostream & out, const Automobile &a){
        out<<"Marka\t"<<a.marka<<"\tRegistracija[ ";
        for(int i=0;i<4;i++){
            out<<a.registracija[i]<<" ";
        }
        out<<a.registracija[4]<<" ]"<<endl;
        return out;
    }
    bool operator ==(const Automobile &a){
        if(strcmp(a.marka, marka)==0){
            for(int i=0;i<5;i++){
                if(registracija[i]!=a.registracija[i])
                    return false;
            }
            return true;
        }return false;
    }
    void setMarka(char *name){
        strcpy(this->marka,name);
    }
    void setMaxSpeed(int n){
        this->maxSpeed=n;
    }
    void setRegistracija(int *arr){
        for(int i=0;i<5;i++){
            this->registracija[i]=arr[i];
        }
    }
    int getSpeed()const{
        return this->maxSpeed;
    }
};

class RentACar{
private:
    char name[100];
    Automobile *cars;
    int numOfCars;
    void copy(const RentACar &r){
        strcpy(this->name,r.name);
        this->numOfCars=r.numOfCars;
        this->cars=new Automobile [this->numOfCars];
        for(int i=0;i<this->numOfCars;i++){
            this->cars[i]=r.cars[i];
        }
    }
public:
    RentACar(char *name=""){
        strcpy(this->name,name);
        this->numOfCars=0;
        this->cars=new Automobile [this->numOfCars];
    }
    RentACar(const RentACar &r){
        copy(r);
    }
    RentACar & operator=(const RentACar &r){
        if(this!=&r){
            delete [] cars;
            copy(r);
        }return *this;
    }
    ~RentACar(){
        delete [] cars;
    }
     RentACar & operator += (const Automobile &a){
        Automobile *tmp=new Automobile[numOfCars+1];
        for(int i=0;i<numOfCars;i++){
            tmp[i]=cars[i];
        }
        tmp[numOfCars++]=a;
        delete [] cars;
        cars=tmp;
        return *this;
    }
    void print(){
        cout<<name<<endl;
        for(int i=0;i<numOfCars;i++){
            cout<<cars[i];
        }
    }
    void pecatiNadBrzina(int x){
        cout<<name<<endl;
        for(int i=0;i<numOfCars;i++){
            if(cars[i].getSpeed()>x){
                cout<<cars[i];
            }
        }
    }
};

int main(){
    int registracija[5] = {1,2,3,4,5};

    Avtomobil a1("Skoda", registracija, 200);
    cout<<a1;
    Avtomobil a2;
    a2.setMarka("Bently");
    cout<<a2;
    RentACar r1("Przo");
    r1+=a1;
    r1+=a2;
    r1.print();
    return 0;
}

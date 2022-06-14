/*
Да се креира класа за претставување на планинарско друштво во која ќе се чуваат информации за името на друштвото (динамички алоцирана низа од знаци), број на поминати тури (цел број) и број на членови во планинарското друштво (цел број). За оваа класа да се напише:

оператор + за собирање на две друштва што како резултат враќа друштво со број на членови еднаков на збирот од членовите од двете друштва, а останатите атрибути на резултантното друштво ги добиваат вредностите на соодветните атрибути од друштвото со поголем број на членови

оператори >, < за споредба во однос на бројот на членови во планинарските друштва

оператор << за печатење на информациите за планинарското друштво

Да се напише функција што на влез прима низа од планинарски друштва и вкупен број на друштва во низата и го печати планинарското друштво што има најголем број на членови .
*/

#include <iostream>
#include <cstring>
using namespace std;

class PlDrustvo{
private:
    char *name;
    int trips;
    int members;
    void copy(const PlDrustvo & pl){
        this->members=pl.members;
        this->trips=pl.trips;
        this->name=new char[strlen(this->name)+1];
        strcpy(this->name,pl.name);
    }
public:
    PlDrustvo(char *name="", int trips=0, int members=0){
        this->members=members;
        this->trips=trips;
        this->name=new char[strlen(name)+1];
        strcpy(this->name,name);
    }
    PlDrustvo(const PlDrustvo & pl){
        copy(pl);
    }
    PlDrustvo & operator=(const PlDrustvo &pl){
        if(this!=&pl){
            delete [] name;
            copy(pl);
        }return *this;
    }
    ~PlDrustvo(){
        delete [] name;
    }
    friend ostream & operator << (ostream & out, const PlDrustvo &pl){
        out<<"Ime: "<<pl.name<<" Turi: "<<pl.trips<<" Clenovi: "<<pl.members<<endl;
        return out;
    }
    PlDrustvo operator + (const PlDrustvo &pl){
        PlDrustvo drustvo;
        drustvo.members=members+pl.members;
        if(members>=pl.members){
            strcpy(drustvo.name,name);
            drustvo.trips=trips;
        }else{
            strcpy(drustvo.name,pl.name);
            drustvo.trips=pl.trips;
        }
        return drustvo;
    }
    bool operator > (const PlDrustvo &pl){
        if(members>pl.members){
            return true;
        }else{
            return false;
        }
    }
    bool operator < (const PlDrustvo &pl){
        if(members<pl.members){
            return true;
        }else{
            return false;
        }
    }
    int getMembers()const{
        return members;
    }
};

void najmnoguClenovi(PlDrustvo *drustva, int n){
    PlDrustvo maxDrustvo("NoName",0,0);
    for(int i=0;i<n;i++){
        if(drustva[i].getMembers()>maxDrustvo.getMembers()){
            maxDrustvo=drustva[i];
        }
    }
    cout<<"Najmnogu clenovi ima planinarskoto drustvo: ";
    cout<<maxDrustvo;
}

int main()
{
    PlDrustvo drustva[3];
    PlDrustvo pl;
    for (int i=0;i<3;i++)
   	{
    	char ime[100];
    	int brTuri;
    	int brClenovi;
    	cin>>ime;
    	cin>>brTuri;
    	cin>>brClenovi;
    	PlDrustvo p(ime,brTuri,brClenovi);
        drustva[i] = p;

   	}

    pl = drustva[0] + drustva[1];
    cout<<pl;

    najmnoguClenovi(drustva, 3);

    return 0;
}

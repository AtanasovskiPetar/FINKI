#include<iostream>
#include<string.h>
using namespace std;

class Delo{
    char name[50];
    int year;
    char country[50];
public:
    Delo(const char name[50]="", int year=2000, const char country[50]=""){
        strcpy(this->name,name);
        strcpy(this->country,country);
        this->year=year;
    }
    char *getCountry(){
        return country;
    }
    bool operator == (Delo & d){
        if(strcmp(this->name,d.name)==0){
            if(strcmp(this->country,d.country)==0){
                if(this->year==d.year){
                    return true;
                }
            }
        }return false;
    }
    char *getIme(){
        return name;
    }
    int getYear(){
        return year;
    }
};

class Pretstava{
protected:
    Delo play;
    int tickets;
    char date[15];
public:
    Pretstava(){
        this->play=Delo();
        this->tickets=0;
        strcpy(this->date,"");
    }
    Pretstava(Delo play, int tickets, const char date[15]){
        this->play=play;
        this->tickets=tickets;
        strcpy(this->date,date);
    }
    virtual double cena(){
        int N,M;
        if(play.getYear() < 1800){
            M=100;
        }else if(play.getYear()<1900){
            M=75;
        }else{
            M=50;
        }
        if(strcmp(play.getCountry(), "Italija")==0){
            N=100;
        }else if(strcmp(play.getCountry(), "Rusija")==0){
            N=150;
        }else{
            N=80;
        }
        return N+M;
    }
    Delo & getDelo(){
        return play;
    }
    int getTickets(){
        return tickets;
    }
};

class Opera:public Pretstava{
public:
    Opera(Delo play, int tickets, const char date[15])
    :Pretstava(play,tickets,date){}
    Opera():Pretstava(){}
};

class Balet:public Pretstava{
private:
    static int cenaBalet;
public:
    Balet(Delo play, int tickets, const char date[15])
    :Pretstava(play,tickets,date){}
    Balet():Pretstava(){}
    static void setCenaBalet (int cen){
        cenaBalet=cen;
    }
    double cena(){
        return Pretstava::cena()+cenaBalet;
    }
};
int Balet::cenaBalet=150;

double prihod(Pretstava **pretstavi, int n){
    double sum=0;
    for(int i=0;i<n;i++){
        sum+=pretstavi[i]->cena() * pretstavi[i]->getTickets();
    }
    return sum;
}

int brojPretstaviNaDelo(Pretstava **pretstavi, int n, Delo &d){
    int counter=0;
    for(int i=0;i<n;i++){
        if(pretstavi[i]->getDelo() == d){
            counter++;
        }
    }return counter;
}

//citanje na delo
Delo readDelo(){
    char ime[50];
    int godina;
    char zemja[50];
    cin>>ime>>godina>>zemja;
    return Delo(ime,godina,zemja);
}
//citanje na pretstava
Pretstava* readPretstava(){
    int tip; //0 za Balet , 1 za Opera
    cin>>tip;
    Delo d=readDelo();
    int brojProdadeni;
    char data[15];
    cin>>brojProdadeni>>data;
    if (tip==0)  return new Balet(d,brojProdadeni,data);
    else return new Opera(d,brojProdadeni,data);
}
int main(){
    int test_case;
    cin>>test_case;

    switch(test_case){
    case 1:
    //Testiranje na klasite Opera i Balet
    {
        cout<<"======TEST CASE 1======="<<endl;
        Pretstava* p1=readPretstava();
        cout<<p1->getDelo().getIme()<<endl;
        Pretstava* p2=readPretstava();
        cout<<p2->getDelo().getIme()<<endl;
    }break;

    case 2:
    //Testiranje na  klasite Opera i Balet so cena
    {
        cout<<"======TEST CASE 2======="<<endl;
        Pretstava* p1=readPretstava();
        cout<<p1->cena()<<endl;
        Pretstava* p2=readPretstava();
        cout<<p2->cena()<<endl;
    }break;

    case 3:
    //Testiranje na operator ==
    {
        cout<<"======TEST CASE 3======="<<endl;
        Delo f1=readDelo();
        Delo f2=readDelo();
        Delo f3=readDelo();

        if (f1==f2) cout<<"Isti se"<<endl; else cout<<"Ne se isti"<<endl;
        if (f1==f3) cout<<"Isti se"<<endl; else cout<<"Ne se isti"<<endl;

    }break;

    case 4:
    //testiranje na funkcijata prihod
    {
        cout<<"======TEST CASE 4======="<<endl;
        int n;
        cin>>n;
        Pretstava **pole=new Pretstava*[n];
        for (int i=0;i<n;i++){
            pole[i]=readPretstava();

        }
        cout<<prihod(pole,n);
    }break;

    case 5:
    //testiranje na prihod so izmena na cena za 3d proekcii
    {
        cout<<"======TEST CASE 5======="<<endl;
        int cenaBalet;
        cin>>cenaBalet;
        Balet::setCenaBalet(cenaBalet);
        int n;
        cin>>n;
        Pretstava **pole=new Pretstava*[n];
        for (int i=0;i<n;i++){
            pole[i]=readPretstava();
        }
        cout<<prihod(pole,n);
        }break;

    case 6:
    //testiranje na brojPretstaviNaDelo
    {
        cout<<"======TEST CASE 6======="<<endl;
        int n;
        cin>>n;
        Pretstava **pole=new Pretstava*[n];
        for (int i=0;i<n;i++){
            pole[i]=readPretstava();
        }

        Delo f=readDelo();
        cout<<brojPretstaviNaDelo(pole,n,f);
    }break;

    };


return 0;
}

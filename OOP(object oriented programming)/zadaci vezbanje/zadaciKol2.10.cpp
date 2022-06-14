#include <iostream>
#include <string.h>
#include <cstdlib>

using namespace std;

class NegativnaVrednost{
public:
    void message(){
        cout<<"Oglasot ima nevalidna vrednost za cenata i nema da bide evidentiran!"<<endl;
    }
};

class Oglas{
private:
    char title[50];
    char category[30];
    char description[100];
    double price;
public:
    Oglas(const char title[50]="", const char category[30]="", const char description[100]="", double price=0){
        strcpy(this->title,title);
        strcpy(this->category,category);
        strcpy(this->description,description);
        this->price=price;
    }
    bool operator > (Oglas & o){
        return (this->price > o.price);
    }
    friend ostream & operator<<(ostream & out, Oglas &o){
        out<<o.title<<endl;
        out<<o.description<<endl;
        out<<o.price<<" evra"<<endl;
        out<<endl;
        return out;
    }
    double getPrice(){
        return price;
    }
    char *getCategory(){
        return category;
    }
};

class Oglasnik{
private:
    char name[20];
    Oglas *oglasi;
    int brojOglasi;
    void copy(const Oglasnik & o){
        strcpy(this->name,o.name);
        this->brojOglasi=o.brojOglasi;
        this->oglasi=new Oglas[brojOglasi];
        for(int i=0;i<brojOglasi;i++){
            this->oglasi[i]=o.oglasi[i];
        }
    }
public:
    Oglasnik(const char name[20]=""){
        strcpy(this->name,name);
        this->brojOglasi=0;
        this->oglasi=new Oglas[0];
    }
    Oglasnik(const Oglasnik & o){
        copy(o);
    }
    Oglasnik & operator =(const Oglasnik & o){
        if(this!=&o){
            delete [] oglasi;
            copy(o);
        }return *this;
    }
    ~Oglasnik(){
        delete [] oglasi;
    }
    Oglasnik & operator +=(Oglas & o){
        if(o.getPrice() < 0){
            throw NegativnaVrednost();
        }else{
            Oglas *tmp=new Oglas[brojOglasi+1];
            for(int i=0;i<brojOglasi;i++)
                tmp[i]=oglasi[i];
            tmp[brojOglasi++]=o;
            delete [] oglasi;
            oglasi=tmp;
        }
        return *this;
    }
    friend ostream & operator<<(ostream & out, Oglasnik & o){
        out<<o.name<<endl;
        for(int i=0;i<o.brojOglasi;i++)
            out<<o.oglasi[i];
        return out;
    }
    void oglasiOdKategorija(const char *k){
        for(int i=0;i<brojOglasi;i++){
            if(strcmp(oglasi[i].getCategory(),k)==0){
                cout<<oglasi[i];
            }
        }
    }
    void najniskaCena(){
        int min=oglasi[0].getPrice();
        int index=0;
        for(int i=0;i<brojOglasi;i++){
            if(oglasi[i].getPrice() < min){
                min=oglasi[i].getPrice();
                index=i;
            }
        }cout<<oglasi[index];
    }
};

int main(){
    char naslov[50];
    char kategorija[30];
    char opis[100];
    float cena;
    char naziv[50];
    char k[30];
    int n;

    int tip;
    cin>>tip;

    if (tip==1){
        cout<<"-----Test Oglas & operator <<-----" <<endl;
        cin.get();
		cin.getline(naslov,49);
        cin.getline(kategorija,29);
        cin.getline(opis,99);
        cin>>cena;
        Oglas o(naslov, kategorija, opis, cena);
        cout<<o;
    }
    else if (tip==2){
    	cout<<"-----Test Oglas & operator > -----" <<endl;
        cin.get();
		cin.getline(naslov,49);
        cin.getline(kategorija,29);
        cin.getline(opis,99);
        cin>>cena;
        Oglas o1(naslov, kategorija, opis, cena);
        cin.get();
		cin.getline(naslov,49);
        cin.getline(kategorija,29);
        cin.getline(opis,99);
        cin>>cena;
        Oglas o2(naslov, kategorija, opis, cena);
        if (o1>o2) cout<<"Prviot oglas e poskap."<<endl;
        else cout<<"Prviot oglas ne e poskap."<<endl;
    }
    else if (tip==3){
        cout<<"-----Test Oglasnik, operator +=, operator << -----" <<endl ;
        cin.get();
		cin.getline(naziv,49);
        cin>>n;
        Oglasnik ogl(naziv);
        for (int i = 0; i < n; i++){
            cin.get();
            cin.getline(naslov,49);
            cin.getline(kategorija,29);
            cin.getline(opis,99);
            cin>>cena;
        	Oglas o(naslov, kategorija, opis, cena);
            try{
                ogl+=o;
        	}
        	catch (NegativnaVrednost &n){
                n.message();
        	}
        }
        cout<<ogl;
    }
    else if (tip==4){
      	cout<<"-----Test oglasOdKategorija -----" <<endl ;
      	cin.get();
		cin.getline(naziv,49);
        cin>>n;
        Oglasnik ogl(naziv);
        for (int i = 0; i < n; i++){
            cin.get();
            cin.getline(naslov,49);
            cin.getline(kategorija,29);
            cin.getline(opis,99);
            cin>>cena;
        	Oglas o(naslov, kategorija, opis, cena);
            try{
                ogl+=o;
        	}
        	catch (NegativnaVrednost &n){
                n.message();
        	}
        }
        cin.get();
		cin.getline(k,29);
        cout<<"Oglasi od kategorijata: " <<k<<endl;
      	ogl.oglasiOdKategorija(k);

    }
    else if (tip==5){
    	cout<<"-----Test Exception -----" <<endl ;
      	cin.get();
		cin.getline(naziv,49);
        cin>>n;
        Oglasnik ogl(naziv);
        for (int i = 0; i < n; i++){
            cin.get();
            cin.getline(naslov,49);
            cin.getline(kategorija,29);
            cin.getline(opis,99);
            cin>>cena;
        	Oglas o(naslov, kategorija, opis, cena);
			try{
                ogl+=o;
        	}
        	catch (NegativnaVrednost &n){
                n.message();
        	}
        }
        cout<<ogl;

    }
    else if (tip==6){
    	cout<<"-----Test najniskaCena -----" <<endl ;
      	cin.get();
		cin.getline(naziv,49);
        cin>>n;
        Oglasnik ogl(naziv);
        for (int i = 0; i < n; i++){
            cin.get();
            cin.getline(naslov,49);
            cin.getline(kategorija,29);
            cin.getline(opis,99);
            cin>>cena;
        	Oglas o(naslov, kategorija, opis, cena);
            try{
                ogl+=o;
        	}
        	catch (NegativnaVrednost &n){
                n.message();
        	}
        }
        cout<<"Oglas so najniska cena:"<<endl;
      	ogl.najniskaCena();

    }
    else if (tip==7){
    	cout<<"-----Test All -----" <<endl ;
      	cin.get();
		cin.getline(naziv,49);
        cin>>n;
        Oglasnik ogl(naziv);
        for (int i = 0; i < n; i++){
            cin.get();
            cin.getline(naslov,49);
            cin.getline(kategorija,29);
            cin.getline(opis,99);
            cin>>cena;
        	Oglas o(naslov, kategorija, opis, cena);
        	try{
                ogl+=o;
        	}
        	catch (NegativnaVrednost &n){
                n.message();
        	}
        }
        cout<<ogl;

        cin.get();
        cin.get();
		cin.getline(k,29);
        cout<<"Oglasi od kategorijata: " <<k<<endl;
      	ogl.oglasiOdKategorija(k);

        cout<<"Oglas so najniska cena:"<<endl;
      	ogl.najniskaCena();

    }
	return 0;
}

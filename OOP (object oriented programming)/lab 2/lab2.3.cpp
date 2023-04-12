#include <iostream>
#include <cstring>
using namespace std;

// vashiot kod ovde
class Film{
private:
    char ime[100];
    char reziser[50];
    char zanr[50];
    int godina;
public:
    Film(){}
    Film(char *ime, char *reziser, char *zanr, int godina){
        strcpy(this->ime,ime);
        strcpy(this->reziser,reziser);
        strcpy(this->zanr,zanr);
        this->godina=godina;
    }
    ~Film(){}
    Film(const Film &f){
        strcpy(this->ime,f.ime);
        strcpy(this->reziser,f.reziser);
        strcpy(this->zanr,f.zanr);
        this->godina=f.godina;
    }
    int getGodina() const{
        return godina;
    }
    void setIme(char name[100]){
        strcpy(ime,name);
    }
    void setReziser(char rezis[50]){
        strcpy(reziser,rezis);
    }
    void setZanr(char zan[50]){
        strcpy(zanr,zan);
    }
    void setGodina(int year){
        godina=year;
    }
     void print(){
        cout<<"Ime: "<<ime<<endl;
        cout<<"Reziser: "<<reziser<<endl;
        cout<<"Zanr: "<<zanr<<endl;
        cout<<"Godina: "<<godina<<endl;
    }
};

void pecati_po_godina(Film *f, int n, int godina){
    int i;
    for(i=0;i<n;i++){
        if(f[i].getGodina()==godina){
            f[i].print();
        }
    }
}

int main() {
 	int n;
 	cin >> n;
 	//da se inicijalizira niza od objekti od klasata Film
 	Film film[n];
 	for(int i = 0; i < n; ++i) {
 		char ime[100];
 		char reziser[50];
 		char zanr[50];
 		int godina;
 		cin >> ime;
 		cin >> reziser;
 		cin >> zanr;
 		cin >> godina;
 		film[i].setIme(ime);
 		film[i].setReziser(reziser);
 		film[i].setZanr(zanr);
 		film[i].setGodina(godina);
 	}
 	int godina;
 	cin >> godina;
 	//da se povika funkcijata pecati_po_godina
 	pecati_po_godina(film, n, godina);
 	
 	return 0;
 }
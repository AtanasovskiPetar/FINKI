#include<iostream>
#include<cstring>

using namespace std;

enum zanr{akcija, komedija, drama};

class Film{
private:
    char *name;
    int size;
    zanr genre;
    void copy(const Film & f){
        this->name = new char[strlen(f.name)+1];
        strcpy(this->name,f.name);
        this->size=f.size;
        this->genre=f.genre;
    }
public:
    Film(char *name="", int size=0, enum zanr genre=akcija){
        this->name = new char[strlen(name)+1];
        strcpy(this->name, name);
        this->size=size;
        this->genre=genre;
    }
    Film(const Film & f){
        copy(f);
    }
    Film & operator=(const Film &f){
        if(this!=&f){
            delete [] name;
            copy(f);
        }
        return *this;
    }
    ~Film(){
        delete [] name;
    }
    void pecati(){
        cout<<size<<"MB-\""<<name<<"\""<<endl;
    }
    zanr getZanr() const{
        return genre;
    }
    int getSize() const{
        return size;
    }
};

class DVD{
private:
    Film films[5];
    int numOfFilms;
    int memoryCapacity;
public:
    DVD(int memoryCapacity=0){
        this->memoryCapacity=memoryCapacity;
        this->numOfFilms=0;
    }
    DVD(const DVD &d){
        this->memoryCapacity=d.memoryCapacity;
        this->numOfFilms=d.numOfFilms;
        for(int i=0;i<numOfFilms;i++){
            this->films[i]=d.films[i];
        }
    }
    void dodadiFilm(const Film &f){
        memoryCapacity-=f.getSize();
        if(memoryCapacity>=0 && numOfFilms<5){
            films[numOfFilms++]=f;
        }
    }
    Film getFilm(int i){
        return films[i];
    }
    void pecatiFilmoviDrugZanr(enum zanr z){
        zanr j;
        for(int i=0;i<numOfFilms;i++){
            j=films[i].getZanr();
            if(j!=z)
            {
                films[i].pecati();
            }
        }
    }
    int getBroj(){
        return numOfFilms;
    }
    float procentNaMemorijaOdZanr(enum zanr z){
        int sum1=0, sum2=0;
        for(int i=0;i<numOfFilms;i++){
            sum2+=films[i].getSize();
            if(films[i].getZanr()==z){
                sum1+=films[i].getSize();
            }
        }
        return 1.0*sum1/sum2*100.0;
    }

};

int main() {
    // se testira zadacata modularno
    int testCase;
    cin >> testCase;

    int n, memorija, kojzanr;
    char ime[50];

    if (testCase == 1) {
        cout << "===== Testiranje na klasata Film ======" << endl;
        cin >> ime;
        cin >> memorija;
        cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
        Film f(ime, memorija, (zanr) kojzanr);
        f.pecati();
    } else if (testCase == 2) {
        cout << "===== Testiranje na klasata DVD ======" << endl;
        DVD omileno(50);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        for (int i = 0; i < n; i++)
            (omileno.getFilm(i)).pecati();
    } 
    else if (testCase == 3) {
        cout << "===== Testiranje na metodot dodadiFilm() od klasata DVD ======" << endl;
        DVD omileno(50);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        for (int i = 0; i < omileno.getBroj(); i++)
            (omileno.getFilm(i)).pecati();
    } else if (testCase == 4) {
        cout << "===== Testiranje na metodot pecatiFilmoviDrugZanr() od klasata DVD ======" << endl;
        DVD omileno(50);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        cin >> kojzanr;
        omileno.pecatiFilmoviDrugZanr((zanr) kojzanr);

    }else if (testCase == 5) {
        cout << "===== Testiranje na metodot pecatiFilmoviDrugZanr() od klasata DVD ======" << endl;
        DVD omileno(50);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        cin >> kojzanr;
        omileno.pecatiFilmoviDrugZanr((zanr) kojzanr);

    } 
    else if (testCase == 6){
		cout<<"===== Testiranje na metodot procentNaMemorijaOdZanr() od klasata DVD =====" <<endl;
		DVD omileno(40);
		cin >> n;
		for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> memorija;
            cin >> kojzanr; //se vnesuva 0 za AKCIJA,1 za KOMEDIJA i 2 za DRAMA
            Film f(ime, memorija, (zanr) kojzanr);
            omileno.dodadiFilm(f);
        }
        cin >> kojzanr;
        cout<<"Procent na filmovi od dadeniot zanr iznesuva: "<<omileno.procentNaMemorijaOdZanr((zanr) kojzanr)<<"%\n";
		
	}
    return 0;
}
/*
Со цел да се подобри системот Мој Термин, со воведување функционалност за пресметување плати за лекарите за еден месец, од Министерството за здравство на Република Македонија, ги добивате следните задачи:

Да се креира класа Lekar во која што ќе се чуваат:

факсимил на докторот (цел број)
име (низа од максимум 10 знаци)
презиме (низа од максимум 10 знаци)
почетна плата (децимален број)
За класата да се имплементираат методите:

void pecati(): Печати информации за лекарот во формат Факсимил: име презиме

double plata(): ја враќа платата на лекарот

Да се креира класа MaticenLekar која што наследува од Lekar и во неа се чуваат дополнителни информации за:

број на пациенти со којшто лекарот соработувал во текот на месецот (цел број)
котизации наплатени од пациентите во текот на месецот (динамички алоцирана низа од децимални броеви)
За класата да се препокријат методитe:

void pecati() : ги печати основните информации за лекарот, а во нов ред го печати и просекот од наплатените котизации

double plata(): ја враќа платата на матичниот лекар

Платата на матичниот лекар се пресметува со зголемување на основната плата за 30% од просекот од наплатените котизации за месецот
*/

#include <iostream>
#include <cstring>
using namespace std;

class Lekar{
protected:
    int faksimil;
    char firstName[10];
    char lastName[11];
    double startingSalary;
public:
    Lekar(int faksimil=0, char firstName[10]="", char lastName[11]="", double startingSalary=0.0){
        this->faksimil=faksimil;
        strcpy(this->firstName,firstName);
        strcpy(this->lastName,lastName);
        this->startingSalary=startingSalary;
    }
    Lekar(const Lekar &l){
        this->faksimil=l.faksimil;
        strcpy(this->firstName,l.firstName);
        strcpy(this->lastName,l.lastName);
        this->startingSalary=l.startingSalary;
    }
    void pecati(){
        cout<<faksimil<<": "<<firstName<<" "<<lastName<<endl;
    }
    double plata(){
        return startingSalary;
    }
};

class MaticenLekar:public Lekar{
private:
    int numPatients;
    double *fees;
    void copy(const MaticenLekar &m){
        this->startingSalary=m.startingSalary;
        this->faksimil=m.faksimil;
        strcpy(this->firstName,m.firstName);
        strcpy(this->lastName,m.lastName);
        this->numPatients=m.numPatients;
        this->fees=new double[this->numPatients];
        for(int i=0;i<this->numPatients;i++){
            this->fees[i]=m.fees[i];
        }
    }
public:
    MaticenLekar(){
            this->numPatients=0;
        }
    MaticenLekar(const Lekar &l, int numPatients=0, double *fees=0):Lekar(l){
        this->numPatients=numPatients;
        this->fees=new double[this->numPatients];
        for(int i=0;i<this->numPatients;i++){
            this->fees[i]=fees[i];
        }
    }
    MaticenLekar(const MaticenLekar &m){
        copy(m);
    }
    MaticenLekar & operator=(const MaticenLekar &m){
        if(this!=&m){
            delete [] fees;
            copy(m);
        }return *this;
    }
    ~MaticenLekar(){
        delete [] fees;
    }
    void pecati(){
        Lekar::pecati();
        double prosek=0;
        for(int i=0;i<numPatients;i++){
            prosek+=fees[i];
        }
        prosek=1.0*prosek/numPatients;
        cout<<"Prosek na kotizacii: "<<prosek<<endl;
    }
    double plata(){
        double prosek=0;
        for(int i=0;i<numPatients;i++){
            prosek+=fees[i];
        }
        prosek=1.0*prosek/numPatients;
        return Lekar::plata()+0.3*prosek;
    }
};

int main() {
	int n;
	cin>>n;
	int pacienti;
	double kotizacii[100];
	int faksimil;
	char ime [20];
	char prezime [20];
	double osnovnaPlata;

	Lekar * lekari = new Lekar [n];
	MaticenLekar * maticni = new MaticenLekar [n];

	for (int i=0;i<n;i++){
		cin >> faksimil >> ime >> prezime >> osnovnaPlata;
		lekari[i] = Lekar(faksimil,ime,prezime,osnovnaPlata);
	}

	for (int i=0;i<n;i++){
		cin >> pacienti;
		for (int j=0;j<pacienti;j++){
			cin >> kotizacii[j];
		}
		maticni[i]=MaticenLekar(lekari[i],pacienti,kotizacii);
	}

	int testCase;
	cin>>testCase;

	if (testCase==1){
		cout<<"===TESTIRANJE NA KLASATA LEKAR==="<<endl;
		for (int i=0;i<n;i++){
			lekari[i].pecati();
			cout<<"Osnovnata plata na gorenavedeniot lekar e: "<<lekari[i].plata()<<endl;
		}
	}
	else {
		cout<<"===TESTIRANJE NA KLASATA MATICENLEKAR==="<<endl;
		for (int i=0;i<n;i++){
			maticni[i].pecati();
			cout<<"Platata na gorenavedeniot maticen lekar e: "<<maticni[i].plata()<<endl;
		}
	}

	delete [] lekari;
	delete [] maticni;
	return 0;
}

#include <iostream>
#include <cstring>
using namespace std;

class Exception{
public:
    void message(){
        cout<<"Ne moze da se vnese dadeniot trud"<<endl;
    }
};

class Trud{
private:
    char type;
    int publicationYear;
public:
    Trud(char type='C', int publicationYear=2002){
        this->type=type;
        this->publicationYear=publicationYear;
    }
    char getTyppe(){
        return type;
    }
    int getPublicationYear(){
        return publicationYear;
    }
    friend istream & operator >> (istream & in, Trud & t){
        in>>t.type>>t.publicationYear;
        return in;
    }
};

class Student{
protected:
    char name[30];
    int index;
    int firstYear;
    int *passedSubjects;
    int numPassed;
public:
    Student(const char name[30]="", int index=0, int firstYear=0, int *passedSubjects=0, int numPassed=0){
        strcpy(this->name,name);
        this->index=index;
        this->firstYear=firstYear;
        this->numPassed=numPassed;
        this->passedSubjects=new int[numPassed];
        for(int i=0;i<numPassed;i++){
            this->passedSubjects[i]=passedSubjects[i];
        }
    }
    ~Student(){
        delete [] passedSubjects;
    }
    virtual double  rang(){
        double sum=0;
        for(int i=0;i<numPassed;i++){
            sum+=passedSubjects[i];
        }
        return 1.0*sum/numPassed;
    }
    friend ostream & operator << (ostream & out, Student & s){
        out<<s.index<<" "<<s.name<<" "<<s.firstYear<<" "<<s.rang()<<endl;
        return out;
    }
    int getFirstYear(){
        return firstYear;
    }
    int getIndex(){
        return index;
    }
};

class PhDStudent:public Student{
    Trud *trudovi;
    int brojTrudovi;
    static int bodoviC;
    static int bodoviJ;
public:
    PhDStudent(const char name[30], int index, int firstYear, int *passedSubjects, int numPassed, Trud *trudovi, int brojTrudovi)
    :Student(name,index,firstYear,passedSubjects,numPassed){
        this->brojTrudovi=brojTrudovi;
        this->trudovi=new Trud[brojTrudovi];
        for(int i=0;i<brojTrudovi;i++){
            if(trudovi[i].getPublicationYear() < firstYear){
                throw Exception();
            }
            this->trudovi[i]=trudovi[i];
        }
    }
    ~PhDStudent(){
        delete [] trudovi;
    }
    static void setBodoviC(int w){
        bodoviC=w;
    }
    static void setBodoviJ(int w){
        bodoviJ=w;
    }
    double  rang(){
        double sum=Student::rang();
        for(int i=0;i<brojTrudovi;i++){
            if(trudovi[i].getTyppe()=='C' || trudovi[i].getTyppe()=='c')
                sum+=bodoviC;
            else
                sum+=bodoviJ;
        }
        return sum;
    }
    PhDStudent & operator += (Trud t){
        if(t.getPublicationYear() == firstYear){
            throw Exception();
            return *this;
        }
        else{
            Trud *tmp=new Trud[brojTrudovi+1];
            for(int i=0;i<brojTrudovi;i++)
                tmp[i]=trudovi[i];
            tmp[brojTrudovi++]=t;
            delete [] trudovi;
            trudovi=tmp;
        }
        return *this;
    }
};
int PhDStudent::bodoviC=1;
int PhDStudent::bodoviJ=3;

int main(){
	int testCase;
	cin >> testCase;

	int god, indeks, n, god_tr, m, n_tr;
	int izbor; //0 za Student, 1 za PhDStudent
	char ime[30];
	int oceni[50];
	char tip;
	Trud trud[50];

	if (testCase == 1){
		cout << "===== Testiranje na klasite ======" << endl;
		cin >> ime;
		cin >> indeks;
		cin >> god;
		cin >> n;
		for (int j = 0; j < n; j++)
			cin >> oceni[j];
		Student s(ime, indeks, god, oceni, n);
		cout << s;

		cin >> ime;
		cin >> indeks;
		cin >> god;
		cin >> n;
		for (int j = 0; j < n; j++)
			cin >> oceni[j];
		cin >> n_tr;
		for (int j = 0; j < n_tr; j++){
			cin >> tip;
			cin >> god_tr;
			Trud t(tip, god_tr);
			trud[j] = t;
		}
		PhDStudent phd(ime, indeks, god, oceni, n, trud, n_tr);
		cout << phd;
	}
	if (testCase == 2){
		cout << "===== Testiranje na operatorot += ======" << endl;
		Student **niza;
		cin >> m;
		niza = new Student *[m];
		for (int i = 0; i<m; i++){
			cin >> izbor;
			cin >> ime;
			cin >> indeks;
			cin >> god;
			cin >> n;
			for (int j = 0; j < n; j++)
				cin >> oceni[j];

			if (izbor == 0){
				niza[i] = new Student(ime, indeks, god, oceni, n);
			}
			else{
				cin >> n_tr;
				for (int j = 0; j < n_tr; j++){
					cin >> tip;
					cin >> god_tr;
					Trud t(tip, god_tr);
					trud[j] = t;
				}
				niza[i] = new PhDStudent(ime, indeks, god, oceni, n, trud, n_tr);
			}
		}
		// pecatenje na site studenti
		cout << "\nLista na site studenti:\n";
		for (int i = 0; i < m; i++)
			cout << *niza[i];

		// dodavanje nov trud za PhD student spored indeks
		Trud t;
		cin >> indeks;
		cin >> t;

		// vmetnete go kodot za dodavanje nov trud so pomos na operatorot +=
        try{
            for(int i=0;i<m;i++){
                if(niza[i]->getIndex()==indeks){
                    PhDStudent *casted=dynamic_cast<PhDStudent*>(niza[i]);
                    if(casted){
                        *casted+=t;
                        niza[i]=casted;
                    }
                }
            }
        }
        catch (Exception e){
            e.message();
        }
		// pecatenje na site studenti
		cout << "\nLista na site studenti:\n";
		for (int i = 0; i < m; i++)
			cout << *niza[i];
	}
	if (testCase == 3){
		cout << "===== Testiranje na operatorot += ======" << endl;
		Student **niza;
		cin >> m;
		niza = new Student *[m];
		for (int i = 0; i<m; i++){
			cin >> izbor;
			cin >> ime;
			cin >> indeks;
			cin >> god;
			cin >> n;
			for (int j = 0; j < n; j++)
				cin >> oceni[j];

			if (izbor == 0){
				niza[i] = new Student(ime, indeks, god, oceni, n);
			}
			else{
				cin >> n_tr;
				for (int j = 0; j < n_tr; j++){
					cin >> tip;
					cin >> god_tr;
					Trud t(tip, god_tr);
					trud[j] = t;
				}
				niza[i] = new PhDStudent(ime, indeks, god, oceni, n, trud, n_tr);
			}
		}
		// pecatenje na site studenti
		cout << "\nLista na site studenti:\n";
		for (int i = 0; i < m; i++)
			cout << *niza[i];

		// dodavanje nov trud za PhD student spored indeks
		Trud t;
		cin >> indeks;
		cin >> t;

		// vmetnete go kodot za dodavanje nov trud so pomos na operatorot += od Testcase 2
        try{
            for(int i=0;i<m;i++){
                if(niza[i]->getIndex()==indeks){
                    PhDStudent *casted=dynamic_cast<PhDStudent*>(niza[i]);
                    if(casted){
                        *casted+=t;
                        niza[i]=casted;
                    }
                }
            }
        }
        catch (Exception e){
            e.message();
        }

		// pecatenje na site studenti
		cout << "\nLista na site studenti:\n";
		for (int i = 0; i < m; i++)
			cout << *niza[i];
	}
	if (testCase == 4){
		cout << "===== Testiranje na isklucoci ======" << endl;
		cin >> ime;
		cin >> indeks;
		cin >> god;
		cin >> n;
		for (int j = 0; j < n; j++)
			cin >> oceni[j];
		cin >> n_tr;
		for (int j = 0; j < n_tr; j++){
			cin >> tip;
			cin >> god_tr;
			Trud t(tip, god_tr);
			trud[j] = t;
		}

		Trud trud1[10];
		PhDStudent phd(ime, indeks, god, oceni, n, trud1, 0);
		try{
            phd+=trud[0];
             cout << phd;
		}
		catch (Exception e){
            e.message();
		}
		try{
            phd+=trud[1];
             cout << phd;
		}
		catch (Exception e){
            e.message();
		}
	}
	if (testCase == 5){
		cout << "===== Testiranje na isklucoci ======" << endl;
		Student **niza;
		cin >> m;
		niza = new Student *[m];
		for (int i = 0; i<m; i++){
			cin >> izbor;
			cin >> ime;
			cin >> indeks;
			cin >> god;
			cin >> n;
			for (int j = 0; j < n; j++)
				cin >> oceni[j];

			if (izbor == 0){
				niza[i] = new Student(ime, indeks, god, oceni, n);
			}
			else{
				cin >> n_tr;
				for (int j = 0; j < n_tr; j++){
					cin >> tip;
					cin >> god_tr;
					Trud t(tip, god_tr);
					trud[j] = t;
				}
				niza[i] = new PhDStudent(ime, indeks, god, oceni, n, trud, n_tr);
			}
		}
		// pecatenje na site studenti
		cout << "\nLista na site studenti:\n";
		for (int i = 0; i < m; i++)
			cout << *niza[i];

		// dodavanje nov trud za PhD student spored indeks
		Trud t;
		cin >> indeks;
		cin >> t;

		// vmetnete go kodot za dodavanje nov trud so pomos na operatorot += i spravete se so isklucokot

		// pecatenje na site studenti
		cout << "\nLista na site studenti:\n";
		for (int i = 0; i < m; i++)
			cout << *niza[i];
	}/*
	if (testCase == 6){
		cout << "===== Testiranje na static clenovi ======" << endl;
		Student **niza;
		cin >> m;
		niza = new Student *[m];
		for (int i = 0; i<m; i++){
			cin >> izbor;
			cin >> ime;
			cin >> indeks;
			cin >> god;
			cin >> n;
			for (int j = 0; j < n; j++)
				cin >> oceni[j];

			if (izbor == 0){
				niza[i] = new Student(ime, indeks, god, oceni, n);
			}
			else{
				cin >> n_tr;
				for (int j = 0; j < n_tr; j++){
					cin >> tip;
					cin >> god_tr;
					Trud t(tip, god_tr);
					trud[j] = t;
				}
				niza[i] = new PhDStudent(ime, indeks, god, oceni, n, trud, n_tr);
			}
		}
		// pecatenje na site studenti
		cout << "\nLista na site studenti:\n";
		for (int i = 0; i < m; i++)
			cout << *niza[i];

		int conf, journal;
		cin >> conf;
		cin >> journal;

        //postavete gi soodvetnite vrednosti za statickite promenlivi

		// pecatenje na site studenti
		cout << "\nLista na site studenti:\n";
		for (int i = 0; i < m; i++)
			cout << *niza[i];
	}
	*/
	return 0;
}

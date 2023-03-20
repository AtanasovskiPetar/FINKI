/*
Дадена е класа Kurs во која се чуваат информации за име на курс (низа од знаци) и број на кредити (цел број).

Дадена е класа Student што содржи инфомрации за: индекс на студентот (цел број), низа од оценки на студентот (динамички алоцирана низа на оценките кои претставуваат броеви од 5 до 10) и број на оценки.

Дадена е класа Predavach што содржи инфомрации за: име на предавачот (динамички алоцирана низа од знаци), листа од курсеви кои ги предава предавачот (низа од објекти од класата Kurs) и број на курсеви (цел број).

Да се креира класа Demonstrator, со која се претставуваат студентите држат лабораториските вежби на некои курсеви. Објектите од оваа класа треба да содржат инфомрации за: индекс на студентот, оценки на студентот, број на оценки, име на демонстраторот, листа од курсеви, број на курсеви чии лабораторисски вежби ги држи студентот и број на часови во неделата кога студентот држи лабораториски вежби (цел број). (5 поени)

За секој студент да се овозможат следните функции:

getBodovi() - која враќа цел број кој го претставува број на бодови за даден студент. Студентите кои не се демонстратори имаат бодови кои го претставуваат процентот на преодни оценки на студентот. (На пример студент со оценки: 5 6 7 ќе има 66 бодови (цел дел од 66.666...) затоа што во 66% од оценките има оценка поголема од 5 ). Кај секој демонстратор на овие бодовите од оценките се додаваат бодовите од лабораториските вежби: (20*C)/N, каде N e бројот на курсеви кои ги држи, C бројот на часови во неделата кога студентот држи лабораториски вежби. Во случај кога некој демонстратор не држи ниту еден курс се фрла исклучокот NoCourseException. Справување со исклучокот треба да реализира онаму каде што е потребно и притоа да се испечати соодветна порака за грешка "Demonstratorot so indeks XXXX ne drzi laboratoriski vezbi", каде XXXX е индексот на демонстраторот. (15 поени)

pecati()- во која се печати само индексот на студентот ако студентот не е демонстратор, а во случај кога студентот е демонстратор во продолжение се печатат информации за курсевите чии лабораториски вежби ги држи демонстраторот. (10 поени)

Форматот за печатење е:

Indeks: ime (Kurs1 Krediti1 ECTS, Kurs2 Krediti2 ECTS,...)
Да се имплементираат следните глобални функции:

Student& vratiNajdobroRangiran(Student ** studenti, int n ) што враќа референца кон студентот кој има најмногу бодови од листата на дадените n студенти (studenti). Да забележиме дека оние демонстратори кои не држат лабораториски вежби ќе земеме дека имаат 0 бодови. Да забележиме и дека во примерите секогаш има точно еден студент кој има најголем број на бодови. (15 поени)
void pecatiDemonstratoriKurs (char* kurs, Student** studenti, int n) - која од дадена листа на студенти, ќе ги испечати само оние кои држат лабораториски вежби на курсот kurs. (10 поени)
Комплетна функционалност на програмата. (5 поени)

Да забележиме дека веќе постоечките класи Kurs, Student и Predavach може да се дополнуваат и менуваат. Погледнете ги дадените класи. Во нив покрај конструкторите дадени се и други функциите кои можат да се користат.
*/

#include<iostream>
#include<string.h>
using namespace std;

class NoCourseException{
private:
    int indeks;
public:
    NoCourseException(int index=0){
        this->indeks = index;
    }
    void message(){
        cout<<"Demonstratorot so indeks "<<indeks<<" ne drzi laboratoriski vezbi"<<endl;
    }  
};

class Kurs{
private:
    char ime[20];
    int krediti;
public:
    Kurs (char *ime,int krediti){
        strcpy(this->ime,ime);
        this->krediti=krediti;
    }
    Kurs (){
        strcpy(this->ime,"");
        krediti=0;
    }
    bool operator==(const char *ime) const{
        return strcmp(this->ime,ime)==0;
    }
    char const * getIme()const{
        return ime;
    }
    void pecati ()const{cout<<ime<<" "<<krediti<<"ECTS";}
};

class Student{
protected:
    int indeks;
    int *ocenki;
    int brojOcenki;
public:
    Student(int indeks=0,int *ocenki=0, int brojOcenki=0){
        this->indeks=indeks;
        this->brojOcenki=brojOcenki;
    	this->ocenki=new int[brojOcenki];
    	for (int i=0;i<brojOcenki;i++) this->ocenki[i]=ocenki[i];
    }
    Student(const Student &k){
    	this->indeks=k.indeks;
    	this->brojOcenki=k.brojOcenki;
    	this->ocenki=new int[k.brojOcenki];
    	for (int i=0;i<k.brojOcenki;i++) 
            this->ocenki[i]=k.ocenki[i];
    }
    Student operator=(const Student &k){
	    if (&k==this) return *this;
        this->indeks=k.indeks;
    	this->brojOcenki=k.brojOcenki;
    	delete [] ocenki;
    	this->ocenki=new int[k.brojOcenki];
    	for (int i=0;i<k.brojOcenki;i++) this->ocenki[i]=k.ocenki[i];
    	return *this;
    }

    virtual ~Student(){delete [] ocenki;}

   virtual int getBodovi(){
       int couter=0;
       for(int i=0;i<brojOcenki;i++){
           if(ocenki[i]>5)
                couter++;
       }
       return 100*couter/brojOcenki;
   }
   virtual void pecati(){
       cout<<indeks;
   }
    virtual Kurs getKurs(int i){
        char n[4]="OOP";
        return Kurs(n,6);
    }
    virtual int getBrojKursevi(){return 0;}
};

class Predavach{
private:
	Kurs kursevi[10];
int brojKursevi;

protected:
	char *imeIPrezime;

public:
    Predavach(char *imeIPrezime,Kurs *kursevi,int brojKursevi){
    	this->brojKursevi=brojKursevi;
    	for (int i=0;i<brojKursevi;i++) this->kursevi[i]=kursevi[i];
    	this->imeIPrezime=new char[strlen(imeIPrezime)+1];
    	strcpy(this->imeIPrezime,imeIPrezime);
    }
    Predavach(const Predavach &p){
        this->brojKursevi=p.brojKursevi;
    	for (int i=0;i<p.brojKursevi;i++) this->kursevi[i]=p.kursevi[i];
    	this->imeIPrezime=new char[strlen(p.imeIPrezime)+1];
    	strcpy(this->imeIPrezime,p.imeIPrezime);
    }
    Predavach operator=(const Predavach &p){
        if (this==&p) return *this;
        this->brojKursevi=p.brojKursevi;
        for (int i=0;i<p.brojKursevi;i++) this->kursevi[i]=p.kursevi[i];
        this->imeIPrezime=new char[strlen(p.imeIPrezime)+1];
        delete [] imeIPrezime;
        strcpy(this->imeIPrezime,p.imeIPrezime);
        return *this;
    }
    ~Predavach(){delete [] imeIPrezime;}

   	int getBrojKursevi()const {return brojKursevi;}
   
    char * const getImeIPrezime()const {return imeIPrezime;}
   
    Kurs operator[](int i) const {
       if (i<brojKursevi&&i>=0) 
           return kursevi[i]; 
       else return Kurs();
    }
   
     void pecati() const  {
         cout<<imeIPrezime<<" (";
         for (int i=0;i<brojKursevi;i++){
             kursevi[i].pecati();
             if (i<brojKursevi-1) cout<<", ";  else cout<<")";
        }
    }
    

};


class Demonstrator: public Student{
private:
    char name[50];
    Kurs *kursevi;
    int brojKursevi;
    int brojCasovi;
    void copy(const Demonstrator &d){
        this->indeks=d.indeks;
    	this->brojOcenki=d.brojOcenki;
    	this->ocenki=new int[d.brojOcenki];
    	for (int i=0;i<d.brojOcenki;i++) 
            this->ocenki[i]=d.ocenki[i];
        strcpy(this->name,d.name);
        this->brojKursevi=d.brojKursevi;
        this->brojCasovi=d.brojCasovi;
        this->kursevi=new Kurs[this->brojKursevi];
        for(int i=0;i<this->brojKursevi;i++){
            this->kursevi[i]=d.kursevi[i];
        }
    }
public:
    Demonstrator(int indeks,int * ocenki,int brojOcenki,char *imeIPrezime,Kurs * kursevi,int brojKursevi,int brojCasovi):Student(indeks,ocenki,brojOcenki){
        strcpy(this->name,imeIPrezime);
        this->brojKursevi=brojKursevi;
        this->brojCasovi=brojCasovi;
        this->kursevi=new Kurs[this->brojKursevi];
        for(int i=0;i<this->brojKursevi;i++){
            this->kursevi[i]=kursevi[i];
        }
    }
    Demonstrator(const Demonstrator &d){
        copy(d);
    }
    Demonstrator & operator = (const Demonstrator &d){
        if(this!=&d){
            delete [] kursevi;
            copy(d);
        }return *this;
    }
    ~Demonstrator(){
        delete [] kursevi;
    }
    int getBodovi(){
        if(brojKursevi==0){
            throw NoCourseException(indeks);
        }  
        else 
            return  Student::getBodovi() + 20*brojCasovi/brojKursevi;
    }
    void pecati(){
        Student::pecati();
        cout<<": "<<name<<" (";
        for(int i=0;i<brojKursevi;i++){
            if(i==(brojKursevi-1))
                kursevi[i].pecati();
             else{
                kursevi[i].pecati();
                cout<<", ";
            }
        }
        cout<<")";
    }
    Kurs getKurs(int i){
        return kursevi[i];
    }
    int getBrojKursevi(){
        return brojKursevi;
    }
};

Student& vratiNajdobroRangiran(Student ** studenti, int n ){
    Student *student;
    int index=0;
    int max=studenti[0]->getBodovi();
    for(int i=0;i<n;i++){
        try{
        if(studenti[i]->getBodovi() > max){
            max=studenti[i]->getBodovi();
            index=i;  
        }
        }catch (NoCourseException &nce){
            nce.message();
        }
    }
    student=studenti[index];
    return *student;
}

void pecatiDemonstratoriKurs (char* kurs, Student** studenti, int n){
    for(int i=0;i<n;i++){
        Student *st = dynamic_cast<Demonstrator*>(studenti[i]);
        if(st){
            for(int j=0;j<st->getBrojKursevi();j++){
                if(strcmp(st->getKurs(j).getIme(),kurs)==0){
                    st->pecati();
                    cout<<endl;
                }
            }
        }
    }
}


int main(){
Kurs kursevi[10];
int indeks,brojKursevi, ocenki[20],ocenka,brojOcenki,tip,brojCasovi,krediti;
char ime[20],imeIPrezime[50];

cin>>tip;

if (tip==1) //test class Demonstrator
{
    cout<<"-----TEST Demonstrator-----"<<endl;
    cin>>indeks>>brojOcenki;
    for (int i=0;i<brojOcenki;i++){
         cin>>ocenka;
         ocenki[i]=ocenka;
    }
    cin>>imeIPrezime>>brojKursevi;
    for (int i=0;i<brojKursevi;i++){
         cin>>ime>>krediti;
         kursevi[i]=Kurs(ime,krediti);
    }
    cin>>brojCasovi;

Demonstrator d(indeks,ocenki,brojOcenki,imeIPrezime,kursevi,brojKursevi,brojCasovi);
cout<<"Objekt od klasata Demonstrator e kreiran";

} else if (tip==2) //funkcija pecati vo Student
{
    cout<<"-----TEST pecati-----"<<endl;
    cin>>indeks>>brojOcenki;
    for (int i=0;i<brojOcenki;i++){
         cin>>ocenka;
         ocenki[i]=ocenka;
    }

Student s(indeks,ocenki,brojOcenki);
s.pecati();

} else if (tip==3) //funkcija getVkupnaOcenka vo Student
{
    cout<<"-----TEST getVkupnaOcenka-----"<<endl;
    cin>>indeks>>brojOcenki;
    for (int i=0;i<brojOcenki;i++){
         cin>>ocenka;
         ocenki[i]=ocenka;
    }
Student s(indeks,ocenki,brojOcenki);
cout<<"Broj na bodovi: "<<s.getBodovi()<<endl;

} else if (tip==4) //funkcija getVkupnaOcenka vo Demonstrator
{
  cout<<"-----TEST getVkupnaOcenka-----"<<endl;
  cin>>indeks>>brojOcenki;
    for (int i=0;i<brojOcenki;i++){
         cin>>ocenka;
         ocenki[i]=ocenka;
    }
    cin>>imeIPrezime>>brojKursevi;
    for (int i=0;i<brojKursevi;i++){
         cin>>ime>>krediti;
         kursevi[i]=Kurs(ime,krediti);
    }
    cin>>brojCasovi;

Demonstrator d(indeks,ocenki,brojOcenki,imeIPrezime,kursevi,brojKursevi,brojCasovi);
cout<<"Broj na bodovi: "<<d.getBodovi()<<endl;

} else if (tip==5) //funkcija pecati vo Demonstrator
{
 cout<<"-----TEST pecati -----"<<endl;
 cin>>indeks>>brojOcenki;
    for (int i=0;i<brojOcenki;i++){
         cin>>ocenka;
         ocenki[i]=ocenka;
    }
    cin>>imeIPrezime>>brojKursevi;
    for (int i=0;i<brojKursevi;i++){
         cin>>ime>>krediti;
         kursevi[i]=Kurs(ime,krediti);
    }
    cin>>brojCasovi;

Demonstrator d(indeks,ocenki,brojOcenki,imeIPrezime,kursevi,brojKursevi,brojCasovi);
d.pecati();

} else if (tip==6) //site klasi
{
    cout<<"-----TEST Student i Demonstrator-----"<<endl;
 cin>>indeks>>brojOcenki;
    for (int i=0;i<brojOcenki;i++){
         cin>>ocenka;
         ocenki[i]=ocenka;
    }
    cin>>imeIPrezime>>brojKursevi;
    for (int i=0;i<brojKursevi;i++){
         cin>>ime>>krediti;
         kursevi[i]=Kurs(ime,krediti);
    }
    cin>>brojCasovi;

Student *s=new Demonstrator(indeks,ocenki,brojOcenki,imeIPrezime,kursevi,brojKursevi,brojCasovi);
s->pecati();
cout<<"\nBroj na bodovi: "<<s->getBodovi()<<endl;
delete s;


} else if (tip==7) //funkcija vratiNajdobroRangiran
{
    cout<<"-----TEST vratiNajdobroRangiran-----"<<endl;
int k, opt;
cin>>k;
Student **studenti=new Student*[k];
for (int j=0;j<k;j++){
   cin>>opt; //1 Student 2 Demonstrator
   cin>>indeks>>brojOcenki;
   for (int i=0;i<brojOcenki;i++)
      {
         cin>>ocenka;
         ocenki[i]=ocenka;
      }
   if (opt==1){
   		studenti[j]=new Student(indeks,ocenki,brojOcenki);
   }else{
       cin>>imeIPrezime>>brojKursevi;
   		for (int i=0;i<brojKursevi;i++){
         cin>>ime>>krediti;
         kursevi[i]=Kurs(ime,krediti);
        }
   		cin>>brojCasovi;
   		studenti[j]=new Demonstrator(indeks,ocenki,brojOcenki,imeIPrezime,kursevi,brojKursevi,brojCasovi);
   }
}
Student& najdobar=vratiNajdobroRangiran(studenti,k);
cout<<"Maksimalniot broj na bodovi e:"<<najdobar.getBodovi();
cout<<"\nNajdobro rangiran:";
najdobar.pecati();

for (int j=0;j<k;j++) delete studenti[j];
 delete [] studenti;
} else if (tip==8) //funkcija pecatiDemonstratoriKurs
{
cout<<"-----TEST pecatiDemonstratoriKurs-----"<<endl;
int k, opt;
cin>>k;
Student **studenti=new Student*[k];
for (int j=0;j<k;j++){
   cin>>opt; //1 Student 2 Demonstrator
   cin>>indeks>>brojOcenki;
   for (int i=0;i<brojOcenki;i++)
      {
         cin>>ocenka;
         ocenki[i]=ocenka;
      }
   if (opt==1){
   		studenti[j]=new Student(indeks,ocenki,brojOcenki);
   }else{
   cin>>imeIPrezime>>brojKursevi;
    for (int i=0;i<brojKursevi;i++)
      {
         cin>>ime>>krediti;
         kursevi[i]=Kurs(ime,krediti);
      }
      cin>>brojCasovi;
   	  studenti[j]=new Demonstrator(indeks,ocenki,brojOcenki,imeIPrezime,kursevi,brojKursevi,brojCasovi);
   }
}
char kurs[20];
cin>>kurs;
cout<<"Demonstratori na "<<kurs<<" se:"<<endl;
pecatiDemonstratoriKurs (kurs,studenti,k);
for (int j=0;j<k;j++) delete studenti[j];
delete [] studenti;

}

return 0;
}
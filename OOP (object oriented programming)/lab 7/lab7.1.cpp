/*
Да се дефинира апстрактна класа Employee којашто ќе содржи име на вработениот, години и работно искуство во години (integer). Да се дефинираат чисти виртуелни функции plata() и bonus() (double).

Од класата Employee да се изведе класа SalaryEmployee која покрај основните информации содржи и информација за основната плата. Бонусот на овие работници се пресметува како процент од основната плата, а процентот е бројот на години со работно искуство. На пример ако работеле 10 години, бонусот е 10 проценти од основната плата. Вкупната плата се пресметува како основната плата плус бонусот.

Од класата Employee исто така да се изведе класа HourlyEmployee која покрај основните информации содржи информација и за вкупниот број на часови кои ги одработил работникот и платата по час. Вкупната плата се пресметува како бројот на часови помножен со платата по час плус бонусот, додека бонусот се пресметува на следниот начин: За секој час над 320-тиот се добива 50 проценти од платата по час.

Од класата Employee на крај се изведува класата Freelancer која покрај основните информации содржи и број на проекти на кои работел вработениет и низа со суми кои ги добил за тие проекти (double). По направени 5 проекти, за секој нареден вработените добиваат бонус од 1000 денари. Вкупната плата на овој тип на вработени изнесува вкупната сума добиена од сите проекти плус бонусот.

Да се преоптовари операторот == кој ќе прима два објекти од класата Employee и ќе ги споредува според тоа дали имаат ист број на години и дали добиваат ист бонус.

Да се дефинира класа Company која ќе содржи информации за името на компанијата, бројот на вработени, и динамична низа од покажувачи од класата Employee или Employee **. За потребите на оваа класа треба да се дефинира конструктор кој прима само еден аргумент - името на компанијата, да се преоптовари операторот += и да се дефинираат следните методи:

double vkupnaPlata() - метод којшто ја враќа вкупната плата на сите вработени во компанијата
double filtriranaPlata(Employee * emp) - метод којшто ја враќа платата само на работниците кои се еднакви со дадениот вработен (според оператор ==)
void pecatiRabotnici() - метод којшто печати по колку вработени има од секој тип на работници во компанијата, а форматот на печатење можете да го видите од тест примерите
Hint: За потребите на последниот метод можете да искористите статички членови и статички функции во секоја од класата, dynamic_cast, виртуелна функција која ќе ја преоптоварите во секоја од класите или нешто друго по ваша желба.
*/

#include <iostream>
#include <cstring>
using namespace std;

class Employee{
protected:
    int age;
    int experience;
    char name[30];
public:
    Employee(char name[30]="", int years=0, int experience=0){
        strcpy(this->name,name);
        this->age=years;
        this->experience=experience;
    }
    virtual double plata() =0;
    virtual double bonus() =0;
    virtual ~Employee(){}
    virtual int getType()=0; //0 salary, 1 hourly, 2 freelancer
    virtual int getAge(){ return age;}
    virtual bool operator == (const Employee &e){
        return (this->age==e.age);
    }
};

class SalaryEmployee:public Employee{
private:
    double startSalary;
public:
    SalaryEmployee(char name[30], int age=0, int experience=0, double startSalary=0.0):Employee(name,age,experience){
        this->startSalary=startSalary;
    }
    double plata(){
        return startSalary+bonus();
    }
    double bonus(){
        return startSalary*(float(experience)/100);
    }
    int getType(){
        return 0;
    }
    bool operator == (const SalaryEmployee &s){
        return (this->age==s.age);
    }
};

class HourlyEmployee:public Employee{
private:
    int hours;
    double hourlyPay;
public:
    HourlyEmployee(char name[30], int age=0, int experience=0, int hours=0, double hourlyPay=0.0):Employee(name,age,experience){
        this->hours=hours;
        this->hourlyPay=hourlyPay;
    }
    double plata(){
        return hourlyPay*hours+bonus();
    }
    double bonus(){
        double b=0;
        if(hours>320){
            b=hourlyPay*0.5*(hours-320);
        }
        return b;
    }
    int getType(){
        return 1;
    }
    bool operator == (const HourlyEmployee &h){
        return (this->age=h.age);
    }
};

class Freelancer:public Employee{
private:
    int numProjects;
    double *projects;
    void copy(const Freelancer &f){
        strcpy(this->name,f.name);
        this->age=f.age;
        this->experience=f.experience;
        this->numProjects=f.numProjects;
        this->projects=new double[f.numProjects];
        for(int i=0;i<this->numProjects;i++){
            this->projects[i]=f.projects[i];
        }
    }
public:
    Freelancer(char name[30]="", int age=0, int experience=0, int numProjects=0, double *projects=0):Employee(name,age,experience){
        this->numProjects=numProjects;
        this->projects=new double[numProjects];
        for(int i=0;i<numProjects;i++){
            this->projects[i]=projects[i];
        }
    }
    Freelancer(const Freelancer &f){
        copy(f);
    }
    Freelancer & operator=(const Freelancer &f){
        if(this!=&f){
            delete [] projects;
            copy(f);
        }return *this;
    }
    ~Freelancer(){
        delete [] projects;
    }
    double plata(){
        double p=0;
        for(int i=0;i<numProjects;i++){
            p+=projects[i];
        }
        return p + bonus();
    }
    double bonus(){
        if(numProjects>5){
            return 1.0*(numProjects-5)*1000;
        }return 0;
    }
    int getType(){
        return 2;
    }
    bool operator == (const Freelancer &f){
        return(this->age==f.age);
    }
};

class Company{
private:
    char name[30];
    int numEmployees;
    Employee ** employees;
    void copy(const Company & c){
        strcpy(this->name,c.name);
        this->numEmployees=c.numEmployees;
        this->employees=new Employee *[this->numEmployees];
        for(int i=0;i<this->numEmployees;i++){
            this->employees[i]=c.employees[i];
        }
    }
public:
    Company(char name[30]=""){
        strcpy(this->name,name);
        this->numEmployees=0;
        this->employees=new Employee *[this->numEmployees];
    }
    Company(const Company &c){
        copy(c);
    }
    Company & operator=(const Company &c){
        if(this!=&c){
            delete [] employees;
            copy(c);
        }return *this;
    }
    ~Company(){
        delete [] employees;
    }
    Company operator += (Employee * e){
        Employee **tmp=new Employee*[numEmployees+1];
        for(int i=0;i<numEmployees;i++){
            tmp[i]=employees[i];
        }
        tmp[numEmployees++]=e;
        delete [] employees;
        employees=tmp;
        return *this;
    }
    double vkupnaPlata(){
        double vkupna=0;
        for(int i=0;i<numEmployees;i++){
            vkupna+=employees[i]->plata();
        }return vkupna;
    }
    void pecatiRabotnici(){
        int salary=0,hourly=0,freelancer=0;
        for(int i=0;i<numEmployees;i++){
            if(employees[i]->getType()==0){
               salary+=1;
            }else if(employees[i]->getType()==1){
                hourly+=1;
            }else{
                freelancer+=1;
            }
        }
        cout<<"Vo kompanijata "<<name<<" rabotat: "<<endl;
        cout<<"Salary employees: "<<salary<<endl;
        cout<<"Hourly employees: "<<hourly<<endl;
        cout<<"Freelancers: "<<freelancer<<endl;
    }
    double filtriranaPlata(Employee * emp) {
        /* Ova e kod bez operator == i gi pominuva site test primeri
        double p=0;
        for(int i=0;i<numEmployees;i++){
            if(employees[i]->getAge()==emp->getAge()){
                p+=employees[i]->plata();
            }
        }
        return p;
        */
        double p=0;
        for(int i=0;i<numEmployees;i++){
            if(*employees[i]==*emp){
                p+=employees[i]->plata();
            }
        }return p;
    }
};

int main() {

char name[50];
cin >> name;
Company c(name);

int n;
cin >> n;

char employeeName[50];
int age;
int experience;
int type;

for (int i=0; i <n; ++i) {
  cin >> type;
  cin >> employeeName >> age >> experience;

  if (type == 1) {
    int basicSalary;
    cin >> basicSalary;
    c += new SalaryEmployee(employeeName, age, experience, basicSalary);
  }

  else if (type == 2) {
    int hoursWorked;
    int hourlyPay;
    cin >> hoursWorked >> hourlyPay;
    c += new HourlyEmployee(employeeName, age, experience, hoursWorked, hourlyPay);
  }

  else {
    int numProjects;
    cin >> numProjects;
    double projects[10];
    for (int i=0; i < numProjects; ++i) {
      cin >> projects[i];
    }
    c += new Freelancer(employeeName, age, experience, numProjects, projects);
  }
}

c.pecatiRabotnici();
cout << "Vkupnata plata e: " << c.vkupnaPlata() << endl;
Employee * emp = new HourlyEmployee("Petre_Petrov",31,6,340,80);
cout << "Filtriranata plata e: " << c.filtriranaPlata(emp);

delete emp;
}
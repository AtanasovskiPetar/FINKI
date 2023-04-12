#include <iostream>
#include <cstring>
using namespace std;

class Worker{
private:
    char firstName[30];
    char lastName[30];
    int salary;
public:
    Worker(){
        strcpy(firstName,"no_firstName");
        strcpy(lastName,"no_lastName");
        salary=0;
    }
    Worker(char *_firstName, char *_lastName, int _salary){
        strcpy(firstName,_firstName);
        strcpy(lastName,_lastName);
        salary=_salary;
    }
    ~Worker(){}
    int getSalary(){
        return salary;
    }
    void print(){
        cout<<firstName<<" "<<lastName<<" "<<salary<<endl;
    }
    void setFirstName(char *name){
        strcpy(firstName,name);
    }
    void setLastName(char *name){
        strcpy(lastName,name);
    }
    void setSalary(int n){
        salary=n;
    }
};

class Factory{
private:
    Worker workers[100];
    int numWorkers;
public:
    Factory(){
        numWorkers=0;
    }
    Factory(Worker *_workers, int _numWorkers){
        int i;
        numWorkers=_numWorkers;
        for(i=0;i<_numWorkers;i++){
            workers[i]=_workers[i];
        }
    }
    ~Factory(){}
    void print(){
        int i;
        cout<<"Site vraboteni:"<<endl;
        for(i=0;i<numWorkers;i++){
            workers[i].print();
        }
    }
    void printWithSalary(int n){
        int i;
        cout<<"Vraboteni so plata povisoka od "<<n<<" :"<<endl;
        for(i=0;i<numWorkers;i++){
            if(workers[i].getSalary()>=n){
                workers[i].print();
            }
        }
    }
};

int main(){
    int n;
    cin>>n;
    char firstName[30], lastName[30];
    int salary;
    int i;
    Worker workers[n];
    for(i=0;i<n;i++){
        cin>>firstName;
        cin>>lastName;
        cin>>salary;
        workers[i].setFirstName(firstName);
        workers[i].setLastName(lastName);
        workers[i].setSalary(salary);
    }
    int minSalary;
    cin>>minSalary;
    Factory f(workers, n);
    f.print();
    f.printWithSalary(minSalary);
    return 0;
}
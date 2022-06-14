/*
Да се дефинира апстрктна класа Number со следните чисто виртуелни методи:

double doubleValue() -ја враќа децималната вредност на даден број
int intValue()- ја враќа целобројната вредност на даден број
void print() - печати информации за бројот. (1 поен)
Од оваа класа да се изведат две класи:

Integer (во која што се чува еден број од тип int)
Double (во која што се чува еден број од тип double) (1 поен).
За двете изведени класи да се имплементираaт соодветен конструктори, како и да се препокријат методите од основната класа. (1 поени)

Да се преоптовари операторот == којшто ќе споредува два броеви според нивната вредност (објекти од класа Number) (1 поен)

Дополнително да се дефинира класа Numbers во која што ќе се чуваат:

динамички алоцирана низа од покажувачи кон објекти од класата Number
број на елементи во низата од покажувачи
За класата да се имплементира соодветен конструктор (default) , како и: (1 поени)

операторот += за додавање на броеви во низата од броеви (1 поен)
Бројот се додава ако и само ако сите броеви што се веќе додадени во низата се различни од него
функција void statistics() која што печати информации за броевите во низата: (2 поени)
Count of numbers: [број на броеви во низата

Sum of all numbers: [сума на сите броеви во низата]

Count of integer numbers: [број на цели броеви (Integer)]

Sum of integer numbers: [сума на сите цели броеви (Integer)]

Count of double numbers: [број на децимални броеви (Double)]

Sum of double numbers: [сума на сите децимални броеви (Double)]

функција void integersLessThan (Integer n) која што ги печати сите цели броеви помали од бројот n (1 поен)
функција void doublesBiggerThan (Double n) која што ги печати сите децимални броеви поголеми од бројот n (1 поен)
*/

#include <iostream>
#include <cstring>
using namespace std;

class Number{
    double n;
public:
    Number(double n=0.0){
        this->n=n;
    }
    virtual double doubleValue() =0;
    virtual int intValue() =0;
    virtual void print() =0;
    bool operator == (const Number &n){
        return this->n==n.n;
    }
    virtual int getType(){return 0;}
};

class Integer:public Number{
private:
    int n;
public:
    Integer(int n=0):Number(){
        this->n=n;
    }
    double doubleValue(){
        return n;
    }
    int intValue(){
        return n;
    }
    void print(){
        cout<<"Integer: "<<n<<endl;
    }
    ~Integer(){}
    bool operator == (const Integer &i){
        return this->n==i.n;
    }
    int getType(){
        return 0;
    }
};

class Double:public Number{
private:
    double n;
public:
    Double(double n=0.0):Number(){
        this->n=n;
    }
    double doubleValue(){
        return n;
    }
    int intValue(){
        return int(n);
    }
    void print(){
        cout<<"Double: "<<n<<endl;
    }
    ~Double(){}
    bool operator == (const Double &d){
        return this->n==d.n;
    }
    int getType(){
        return 1;
    }
};

class Numbers{
private:
    Number **numbers;
    int numOfPointers;
    void copy(const Numbers &n){
        this->numOfPointers=n.numOfPointers;
        this->numbers = new Number*[numOfPointers];
        for(int i=0;i<this->numOfPointers;i++){
            *(this->numbers[i])=*(n.numbers[i]);
        }
    }
public:
    Numbers(){
        this->numOfPointers=0;
        this->numbers = new Number*[0];
    }
    Numbers(const Numbers &n){
        copy(n);
    }
    Numbers & operator = (const Numbers & n){
        if(this!=&n){
            delete [] numbers;
            copy(n);
        }return *this;
    }
    ~Numbers(){
        delete [] numbers;
    }
    Numbers & operator += (Number *n){
        bool flag=true;
        for(int i=0;i<numOfPointers;i++){
            if(numbers[i]->doubleValue()==n->doubleValue()){
                flag=false;
                break;
            }
        }
        if(flag){
            Number **tmp = new Number*[numOfPointers+1];
            for(int i=0;i<numOfPointers;i++){
                if(numbers[i]->getType()==0){
                    tmp[i]=new Integer(numbers[i]->intValue());
                }else{
                    tmp[i]=new Double(numbers[i]->doubleValue());
                }
            }
            if(n->getType()==0){
                tmp[numOfPointers++]=new Integer(n->intValue());
            }else{
                tmp[numOfPointers++]=new Double(n->doubleValue());
            }
            delete [] numbers;
            numbers=tmp;
        }return *this;
    }
    void print(){
        for(int i=0;i<numOfPointers;i++){
            numbers[i]->print();
        }
    }
    void statistics(){
        double sum=0,sumDouble=0;
        int sumInt=0, counterInt=0, counterDouble=0;
        cout<<"Count of numbers: "<<numOfPointers<<endl;
        for(int i=0;i<numOfPointers;i++){
            if(numbers[i]->getType()==0){
                sumInt+=numbers[i]->intValue();
                counterInt++;
            }else{
                sumDouble+=numbers[i]->doubleValue();
                counterDouble++;
            }
            sum+=numbers[i]->doubleValue();
        }
        cout<<"Sum of all numbers: "<<sum<<endl;
        cout<<"Count of integer numbers: "<<counterInt<<endl;
        cout<<"Sum of integer numbers: "<<sumInt<<endl;
        cout<<"Count of double numbers: "<<counterDouble<<endl;
        cout<<"Sum of double numbers: "<<sumDouble<<endl;
    }
    void integersLessThan (Integer n){
        bool flag=false;
        for(int i=0;i<numOfPointers;i++){
            if(numbers[i]->getType()==0){
                if(numbers[i]->intValue()<n.intValue()){
                    flag = true;
                    numbers[i]->print();
                }
            }
        }if(flag==false){
            cout<<"None"<<endl;
        }
    }
    void doublesBiggerThan (Double n){
        bool flag=false;
        for(int i=0;i<numOfPointers;i++){
            if(numbers[i]->getType()==1){
                if(numbers[i]->doubleValue() > n.doubleValue()){
                    flag=true;
                    numbers[i]->print();
                }
            }
        }if(flag==false){
            cout<<"None"<<endl;
        }
    }
};

int main() {

	int n;
	cin>>n;
	Numbers numbers;
	for (int i=0;i<n;i++){
		int type;
		double number;
		cin>>type>>number;
		if (type==0){//Integer object
			Integer * integer = new Integer((int) number);
			numbers+=integer;
		}
		else {
			Double * doublee = new Double(number);
			numbers+=doublee;
		}
	}

	int lessThan;
	double biggerThan;

	cin>>lessThan;
	cin>>biggerThan;

	cout<<"STATISTICS FOR THE NUMBERS\n";
	numbers.statistics();
	cout<<"INTEGER NUMBERS LESS THAN "<<lessThan<<endl;
	numbers.integersLessThan(Integer(lessThan));
	cout<<"DOUBLE NUMBERS BIGGER THAN "<<biggerThan<<endl;
	numbers.doublesBiggerThan(Double(biggerThan));

	return 0;
}

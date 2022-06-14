/*
Потребно е да се напише класа за работа со комплексни броеви ComplexNumber. Во неа се чуваат две променливи кои можат да бидат децимални броеви. Едната го претставува реалниот дел од комплексниот број - real, а другата имагинарниот дел - imaginary. За класата да се напише:

конструктор со параметри и со предодредени вредности - нули
метод со потпис double module() кој го враќа модулот на комплексниот број. Модул се пресметува според следната формула:

sqrt(pow(real, 2) + pow(imaginary, 2))

Дополнително, потребно е да се преоптоварат следниве оператори:

оператор за доделување вредност =
оператор за печатење на стандарден излез << кој ќе го печати бројот во формат [real]+[imaginary]i. Доколку реалниот дел е 0, се печати само [imaginary]i, a доколку имагинарниот дел е 0, се печати само [real].
оператор за собирање на комплексни броеви +. Резултатот е нов комплексен број, чиј реален и имагинарен дел се збир од соодветните делови од собироците
оператор за одземање на комплексни броеви -. Резултатот е нов комплексен број, чиј реален и имагинарен дел се разлика од соодветните делови од собироците
оператор за множење на комплексни броеви *. Резултатот е нов комплексен број, чиј реален и имагинарен дел се производ од соодветните делови од множителите
оператор за делење на комплексни броеви /. Резултатот е нов комплексен број, чиј реален и имагинарен дел се количник од соодветните делови од делителите
оператор за еднаквост на комплексни броеви ==. Два комплексни броеви се еднакви ако и само ако се еднакви нивните реални и имагинарни делови
оператори за споредба на комплексни броеви > и <. Комплексните броеви се споредуваат според вредноста на нивниот реален дел. Ако тој е еднаков, тогаш се споредуваат според вредноста на имагинарниот дел
Целосна функционалност на класата носи (5 поени).

Потоа, потребно е да се напише класа за работа со равенки од комплексни броеви Equation. Во неа ќе се чуваат динамички алоцирана низа од комплексни броеви (објекти од класата ComplexNumber) и динамички алоцирана низа од знаци char - аритметички оператори за операции меѓу броевите.

Следно, треба да се преоптовари операторот за читање од стандарден влез >> кој ќе чита податоци за равенката. Прво се вчитуваат податоци за еден комплексен број (прво реален па имагинарен дел), па оператор, па повторно комплексен број, повторно оператор итн. сѐ додека не се прочита знакот =. Со вчитување на знакот = (на местото за оператор) читањето од влез завршува (2 поени).

За класата да се напишат и следниве методи:

ComplexNumber result() кој ќе го врати резултатот од равенката. При пресметувањето на резултатот сите аритметички оператори (+, -, *, /) имаат еднаква предност, т.е. не треба да внимавате да извршувате множење пред собирање или слично, туку ги извршувате операциите во оној редослед во кој сте ги добиле при вчитување од стандарден влез. (2 поени)
double sumModules() кој ќе врати збир од модулите на сите комплексни броеви во равенката. (1 поен)
Забелешка: Не го менувајте почетниот код!
*/

#include <iostream>
#include <cstring>
#include <cmath>
using namespace std;

class ComplexNumber{
private:
    float real;
    float imaginary;
    friend class Equation;
public:
    ComplexNumber(float real=0.0, float imaginary=0.0){
        this->real=real;
        this->imaginary=imaginary;
    }
    double module(){
        return sqrt(pow(real, 2) + pow(imaginary, 2));
    }
    friend ostream & operator << (ostream & out, const ComplexNumber &c){
        if(c.real==0){
            out<<c.imaginary<<"i"<<endl;
        }
        else if(c.imaginary==0){
            out<<c.real<<endl;
        }
        else{
            out<<c.real<<"+"<<c.imaginary<<"i"<<endl;
        }
        return out;
    }
    ComplexNumber & operator =(const ComplexNumber &c){
        if(this!=&c){
            this->real=c.real;
            this->imaginary=c.imaginary;
        }return *this;
    }
    ComplexNumber operator +(const ComplexNumber & c){
        return ComplexNumber(this->real+c.real, this->imaginary+c.imaginary);
    }
    ComplexNumber operator -(const ComplexNumber & c){
        return ComplexNumber(this->real-c.real,this->imaginary-c.imaginary);
    }
    ComplexNumber operator *(const ComplexNumber & c){
        return ComplexNumber(this->real*c.real,this->imaginary*c.imaginary);
    }
    ComplexNumber operator /(const ComplexNumber & c){
        return ComplexNumber(this->real/c.real,this->imaginary/c.imaginary);
    }
    bool operator ==(const ComplexNumber &c){
        if(this->real==c.real){
            if(this->imaginary==c.imaginary){
                return true;
            }
        }return false;
    }
    int operator >(const ComplexNumber &c){
        if(this->real>c.real){
            return 1;
        }else if(this->real==c.real){
            if(this->imaginary>c.imaginary){
                return 1;
            }else if(this->imaginary==c.imaginary){
                return 0;
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }
    int operator <(const ComplexNumber &c){
        if(this->real<c.real){
            return 1;
        }else if(this->real==c.real){
            if(this->imaginary<c.imaginary){
                return 1;
            }else if(this->imaginary==c.imaginary){
                return 0;
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }
};

class Equation{
private:
    ComplexNumber *numbers;
    char *chars;
    int n;
    void copy(const Equation & eq){
        this->n=eq.n;
        this->numbers=new ComplexNumber[eq.n];
        for(int i=0;i<this->n;i++){
            this->numbers[i]=eq.numbers[i];
        }
        this->chars=new char[strlen(eq.chars)+1];
        strcpy(this->chars,eq.chars);
    }
public:
    Equation(){
        this->n=0;
        this->numbers=new ComplexNumber[this->n];
        for(int i=0;i<this->n;i++){
            this->numbers[i]=numbers[i];
        }
        this->chars=new char[strlen(chars)+1];
        strcpy(this->chars,chars);
    }
    Equation(const Equation & eq){
        copy(eq);
    }
    Equation & operator=(const Equation & eq){
        if(this!=&eq){
            delete [] numbers;
            delete [] chars;
            copy(eq);
        }return *this;
    }
    ~Equation(){
        delete [] numbers;
        delete[] chars;
    }
    friend istream & operator >> (istream & in, Equation & eq){
        char c='-';
        while(c!= '='){
            ComplexNumber *tmp=new ComplexNumber[eq.n+1];
            for(int i=0;i<eq.n;i++){
                tmp[i]=eq.numbers[i];
            }
            float r,i;
            in>>r>>i;
            tmp[eq.n]=ComplexNumber(r,i);
            delete [] eq.numbers;
            eq.numbers=tmp;
            char *temp=new char[eq.n+1];
            strcpy(temp,eq.chars);
            in>>c;
            temp[eq.n++]=c;
            delete [] eq.chars;
            eq.chars=temp;
        }
        return in;
    }
    ComplexNumber result(){
        ComplexNumber res=numbers[0];
        for(int i=1;i<n;i++){
            switch(chars[i-1]){
            case '+':
                res= res + numbers[i];
                break;
            case '-':
                res=res-numbers[i];
                break;
            case '*':
                res=res*numbers[i];
                break;
            case '/':
                res=res/numbers[i];
                break;
            default:
                break;
            }
        }return res;
    }
    double sumModules(){
        double sum=0;
        for(int i=0;i<n;i++){
            sum+=numbers[i].module();
        }
        return sum;
    }
};

int main() {
	int testCase;
	double real, imaginary;
	ComplexNumber first, second, result;

	cin >> testCase;

	if (testCase <= 8) {
		cin >> real;
		cin >> imaginary;
		first = ComplexNumber(real, imaginary);
		cin >> real;
		cin >> imaginary;
		second = ComplexNumber(real, imaginary);
	}

	if (testCase == 1) {
		cout << "===== OPERATOR + =====" << endl;
		result = first + second;
		cout << result;
	}
	else if (testCase == 2) {
		cout << "===== OPERATOR - =====" << endl;
		result = first - second;
		cout << result;
	}
	else if (testCase == 3) {
		cout << "===== OPERATOR * =====" << endl;
		result = first * second;
		cout << result;
	}
	else if (testCase == 4) {
		cout << "===== OPERATOR / =====" << endl;
		result = first / second;
		cout << result;
	}
	else if (testCase == 5) {
		cout << "===== OPERATOR == =====" << endl;
		cout << first;
		cout << second;
		if (first == second)
			cout << "EQUAL" << endl;
		else
			cout << "NOT EQUAL" << endl;
	}
	else if (testCase == 6) {
		cout << "===== OPERATOR > =====" << endl;
		cout << first;
		cout << second;
		if (first > second)
			cout << "FIRST GREATER THAN SECOND" << endl;
		else
			cout << "FIRST LESSER THAN SECOND" << endl;
	}
	else if (testCase == 7) {
		cout << "===== OPERATOR < =====" << endl;
		cout << first;
		cout << second;
		if (first < second)
			cout << "FIRST LESSER THAN SECOND" << endl;
		else
			cout << "FIRST GREATER THAN SECOND" << endl;
	}
	else if (testCase == 8) {
		cout << "===== MODULE =====" << endl;
		double module = first.module();
		cout << first;
		cout << "Module: " << module << endl;
		cout << second;
		module = second.module();
		cout << "Module: " << module << endl;
	}
	else if (testCase == 9) {
		cout << "===== OPERATOR >> & SUM OF MODULES =====" << endl;
		Equation equation;
		cin >> equation;
		cout << equation.sumModules();
	}
	else if (testCase == 10) {
		cout << "===== EQUATION RESULT =====" << endl;
		Equation equation;
		cin >> equation;C:\Users\patan\OOP\main.cpp
		result = equation.result();
		cout << result;
	}
	return 0;
}

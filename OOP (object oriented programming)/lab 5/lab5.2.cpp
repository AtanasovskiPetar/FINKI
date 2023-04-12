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
		cin >> equation;
		result = equation.result();
		cout << result;
	}
	return 0;
}

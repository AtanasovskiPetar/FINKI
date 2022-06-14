/*
Да се креира класа PositiveIntegers во која што ќе се чуваат информации за:

низа од позитивни броеви (>0) (динамички алоцирана низа од цели броеви)
број на елементи во низата
максимален капацитет на низата
За класата да се имплементираат:

потребниот конструктор (погледнете во главната функција како се креира објект од оваа класа)
метод void increaseCapacity(int c) којшто го зголемува максималниот капацитет на низата за бројот с
оператор за додавање на нов број во низата од позитивни броеви +=
број се додава ако и само ако
оператор за множење * за множење на низата со друг цел број
Пример, низата има објекти (1 2 3 4 5) и истата се множи со 2, резултатот ќе биде (1 2 3 4 5)*2 = (2 3 6 8 10)
оператор за делење \ за делење на низата до друг цел број
потребно е секој елемент од низата да биде делив со тој број, како и делителот не смее да биде нула.
оператор [] за пристап до елемент од низата
Потребно е да се дефинираат класи за исклучоци и секој од нив да има метод void message() којшто ќе принта соодветна порака кога исклучокот ќе биде фатен. Можните исклучоци се следните:

ArithmeticException (се фрла ако се проба да се дели со нула)
принта порака Division by zero is not allowed
NumbersNotDivisibleException (се фрла ако се проба да се дели низата со некој број, а барем еден елемент од низата не е делив со тој број)
принта порака Division by number [делителот] is not supported
ArrayFullException (се фрла ако се проба да се додади елемент во низа која и е исполнет максималниот капацитет)
принта порака The array is full. Increase the capacity
IndexOutOfBoundsException (се фрла доколку се проба да се пристапи до елемент од низата со несоодветен индекс)
принта порака Index [индексот] is out of bounds
NumberIsNotPositiveException (се фрла доколку се проба да се внесе во низата број што не е позитивен или е нула)
принта порака Number [бројот] is not positive integer.
*/
#include<iostream>
using namespace std;

class ArithmeticException{
public:
    void message(){
        cout<<"Division by zero is not allowed"<<endl;
    }
};

class ArrayFullException{
public:
    void message(){
        cout<<"The array is full. Increase the capacity"<<endl;
    }
};

class NumbersNotDivisibleException{
private:
    int n;
public:
    NumbersNotDivisibleException(int n=0){
        this->n=n;
    }
    void message(){
        cout<<"Division by "<<n<<" is not supported"<<endl;
    }
};

class IndexOutOfBoundsException{
private:
    int index;
public:
    IndexOutOfBoundsException(int index=0){
        this->index=index;
    }
    void message(){
        cout<<"Index "<<index<<" is out of bounds"<<endl;
    }
};

class NumberIsNotPositiveException{
private:
    int n;
public:
    NumberIsNotPositiveException(int n=0){
        this->n=n;
    }
    void message(){
        cout<<"Number "<<n<<" is not positive integer"<<endl;
    }
};

class PositiveIntegers{
private:
    int *arr;
    int n;
    int capacity;
    void copy(const PositiveIntegers &p){
        this->n=p.n;
        this->capacity=p.capacity;
        this->arr=new int[this->n];
        for(int i=0;i<this->n;i++){
            this->arr[i]=p.arr[i];
        }
    }
public:
    PositiveIntegers(int capacity=0, int n=0, int*arr=0){
        this->n=n;
        this->capacity=capacity;
        this->arr=new int[this->n];
        for(int i=0;i<this->n;i++){
            this->arr[i]=arr[i];
        }
    }
    PositiveIntegers(const PositiveIntegers &p){
        copy(p);
    }
    PositiveIntegers & operator=(const PositiveIntegers &p){
        if(this!=&p){
            delete [] arr;
            copy(p);
        }return *this;
    }
    ~PositiveIntegers(){
        delete [] arr;
    }
    void increaseCapacity(int c){
        this->capacity+=c;
    }
    PositiveIntegers & operator += (int num){
        if(capacity == n)
            throw ArrayFullException();
        else if(num<=0)
            throw NumberIsNotPositiveException(n);
        else{
            int *tmp=new int[n+1];
            for(int i=0;i<n;i++)
                tmp[i]=arr[i];
            tmp[n++]=num;
            delete [] arr;
            arr=tmp;
            return *this;
        }
    }
    PositiveIntegers operator *(int num){
        int arr1[n];
        for(int i=0;i<n;i++)
            arr1[i]=num*arr[i];
        PositiveIntegers p1(capacity, n, arr1);
        return p1;
    }
    PositiveIntegers operator /(int num){
        if(num==0)
            throw ArithmeticException();
        else{
            int arr1[n];
            for(int i=0;i<n;i++){
                if(arr[i]%num != 0)
                    throw NumbersNotDivisibleException(num);
                else
                    arr1[i]=arr[i]/num;
            }
            PositiveIntegers p1(capacity, n, arr1);
            return p1;
        }
    }
    int operator [] (int index){
        if(index>=n || index<0)
            throw IndexOutOfBoundsException(index);
        return arr[index];
    }
    void print(){
        cout<<"Size: "<<n<<" Capacity: "<<capacity<<" Numbers: ";
        for(int i=0;i<n;i++)
            cout<<arr[i]<<" ";
        cout<<endl;
    }
};

int main() {

	int n,capacity;
	cin >> n >> capacity;
	PositiveIntegers pi (capacity);
	for (int i=0;i<n;i++){
		int number;
		cin>>number;
		try{
            pi+=number;
		}
		catch (ArrayFullException &full){
            full.message();
		}
		catch (NumberIsNotPositiveException){
		    NumberIsNotPositiveException neg(number);
            neg.message();
		}
	}
	cout<<"===FIRST ATTEMPT TO ADD NUMBERS==="<<endl;
	pi.print();
	int incCapacity;
	cin>>incCapacity;
	pi.increaseCapacity(incCapacity);
	cout<<"===INCREASING CAPACITY==="<<endl;
	pi.print();

	int n1;
	cin>>n1;
	for (int i=0;i<n1;i++){
		int number;
		cin>>number;
		try{
            pi+=number;
		}
		catch (ArrayFullException &full){
            full.message();
		}
		catch (NumberIsNotPositiveException){
		    NumberIsNotPositiveException neg(number);
            neg.message();
		}
	}
	cout<<"===SECOND ATTEMPT TO ADD NUMBERS==="<<endl;

    pi.print();
	PositiveIntegers pi1;

	cout<<"===TESTING DIVISION==="<<endl;
    try{
        pi1 = pi/0;
        pi1.print();
    }
    catch (ArithmeticException &ari){
        ari.message();
    }
	pi1 = pi/1;
	pi1.print();
    try{
        pi1 = pi/2;
        pi1.print();
    }
    catch (NumbersNotDivisibleException &notDiv){
        notDiv.message();
    }
	cout<<"===TESTING MULTIPLICATION==="<<endl;
	pi1 = pi*3;
	pi1.print();


	cout<<"===TESTING [] ==="<<endl;
	try{
	cout<<"PositiveIntegers[-1] = "<<pi[-1]<<endl;
	}
	catch (IndexOutOfBoundsException){
	    IndexOutOfBoundsException ind(-1);
        ind.message();
	}
	try{
	cout<<"PositiveIntegers[2] = "<<pi[2]<<endl;
	}
	catch (IndexOutOfBoundsException){
	    IndexOutOfBoundsException ind(2);
        ind.message();
	}
	try{
	cout<<"PositiveIntegers[3] = "<<pi[3]<<endl;
	}
	catch (IndexOutOfBoundsException){
	    IndexOutOfBoundsException ind(3);
        ind.message();
	}
	try{
	cout<<"PositiveIntegers[12] = "<<pi[12]<<endl;
	}
	catch (IndexOutOfBoundsException){
	    IndexOutOfBoundsException ind(12);
        ind.message();
	}
	return 0;
}

#include<iostream>
#include<cstring>
using namespace std;

class Person{
    char name[20];
    char lastName[20];
public:
    Person(){
        strcpy(name,"");
        strcpy(lastName,"");
    }
    Person(char *_name, char *_lastName){
        strcpy(name,_name);
        strcpy(lastName,_lastName);
    }
    ~Person(){}
    void print(){
        cout<<name<<" "<<lastName<<endl;
    }
};

class Date{
private:
    int year;
    int month;
    int day;
public:
    Date(int y=1900, int m=1, int d=1){
        if(m>=1 && m<=12){
            if(d>=1 && d<=31){
                month=m;
                day=d;
                year=y;
            }
        }
    }
    ~Date(){}
    Date(const Date &d){
        year=d.year;
        month=d.month;
        day=d.day;
    }
    void print(){
        cout<<year<<"."<<month<<"."<<day<<endl;
    }
};

class Car{
private:
    Person person;
    Date date;
    float price;
public:
    Car(){
        price=0.0;
    }
    Car(Person _person, Date _date, float _price){
        person=_person;
        date=_date;
        price=_price;
    }
    ~Car(){}
    void print(){
        person.print();
        date.print();
        cout<<"Price: "<<price;
    }
    float getPrice(){
        return price;
    }
};

void cheaperThan(Car* cars, int numCars, float price){
    int i;
    for(i=0;i<numCars;i++){
        if(cars[i].getPrice()<price){
            cars[i].print();
        }
    }
}

int main() {
	char name[20];
	char lastName[20];
	int year;
	int month;
	int day;
	float price;

	int testCase;
	cin >> testCase;

	if (testCase == 1) {
		cin >> name;
		cin >> lastName;
		Person lik(name, lastName);

		cin >> year;
		cin >> month;
		cin >> day;
		Date date(year, month, day);

		cin >> price;
		Car car(lik, date,  price);

		car.print();
	}
	else if (testCase == 2) {
		cin >> name;
		cin >> lastName;
		Person lik(name, lastName);

		cin >> year;
		cin >> month;
		cin >> day;
		Date date(Date(year, month, day));

		cin >> price;
		Car car(lik, date,  price);
		car.print();
	}
	else {
		int numCars;
		cin >> numCars;

		Car cars[10];
		for (int i = 0; i < numCars; i++) {
			cin >> name;
			cin >> lastName;
			Person lik(name, lastName);

			cin >> year;
			cin >> month;
			cin >> day;
			Date date(year, month, day);

			cin >> price;
			cars[i] = Car(lik, date,  price);
		}
        float priceLimit;
        cin >> priceLimit;
		cheaperThan(cars, numCars, priceLimit);
	}


	return 0;
}
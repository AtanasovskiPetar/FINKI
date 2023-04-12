#include<iostream>
#include<cstring>
#include <string>

using namespace std;

class Avtomobil{
private:
    char color[20];
    char brand[20];
    char model[20];
public:
    Avtomobil(char *color="", char *brand="", char *model=""){
        strcpy(this->color,color);
        strcpy(this->brand,brand);
        strcpy(this->model,model);
    }
    Avtomobil(const Avtomobil &c){
        strcpy(this->color,c.color);
        strcpy(this->brand,c.brand);
        strcpy(this->model,c.model);
    }
    Avtomobil & operator = (const Avtomobil & c){
        strcpy(this->color,c.color);
        strcpy(this->brand,c.brand);
        strcpy(this->model,c.model);
        return *this;
    }
    ~Avtomobil(){}
    void print(){
        cout<<color<<" "<<brand<<" "<<model<<endl;
    }
};

class ParkingPlac {
private:
    char adress[20];
    char *ID;
    int pricePerHour;
    int totalEarnings;
    int numOfCarsMomentarily;
    Avtomobil *cars;
    void copy(const ParkingPlac & p){
        strcpy(this->adress,p.adress);
        this->ID = new char [strlen(p.ID)+1];
        strcpy(this->ID,p.ID);
        this->pricePerHour=p.pricePerHour;
        this->totalEarnings=p.totalEarnings;
        this->numOfCarsMomentarily=p.numOfCarsMomentarily;
        this->cars = new Avtomobil [this->numOfCarsMomentarily];
        for(int i=0;i<this->numOfCarsMomentarily;i++){
            this->cars[i]=p.cars[i];
        }
    }
public:
    ParkingPlac(char *adress="", char *ID="", int pricePerHour=0){
        strcpy(this->adress,adress);
        this->ID = new char [strlen(ID)+1];
        strcpy(this->ID,ID);
        this->pricePerHour=pricePerHour;
        this->totalEarnings=0;
        this->numOfCarsMomentarily=0;
        this->cars = new Avtomobil [numOfCarsMomentarily];
    }
    ParkingPlac(const ParkingPlac & p){
        copy(p);
    }
    ParkingPlac & operator = (const ParkingPlac & p){
        if(this!=&p){
            delete [] ID;
            delete [] cars;
            copy(p);
        }
        return *this;
    }
    ~ParkingPlac(){
        delete [] ID;
        delete [] cars;
    }
    void parkirajVozilo(const Avtomobil & a){
        Avtomobil * tmp = new Avtomobil [numOfCarsMomentarily+1];
        for(int i=0;i<numOfCarsMomentarily;i++){
            tmp[i]=cars[i];
        }
        tmp[numOfCarsMomentarily++]=a;
        delete [] cars;
        cars=tmp;
    }
    void pecatiParkiraniVozila(){
        cout<<"Vo parkingot se parkirani slednite vozila:"<<endl;
        for(int i=0;i<numOfCarsMomentarily;i++){
            cout<<i+1<<".";
            cars[i].print();
        }
    }
    void platiCasovi(int casovi){
        totalEarnings+=(casovi)*pricePerHour;
    }
    bool daliIstaAdresa(const ParkingPlac &p){
        if(strcmp(this->adress, p.adress)==0){
            return true;
        }else{
            return false;
        }
    }
    char *getId() const{
        return this->ID;
    }
    void pecati(){
        if(totalEarnings==0){
            cout<<ID<<" "<<adress<<endl;
        }else{
            cout<<ID<<" "<<adress<<" - "<<totalEarnings<<" denari"<<endl;
        }
    }
};
// вашиот код

int main(){

	ParkingPlac p[100];
	int n,m;
	char adresa[50],id[50];
	int brojcasovi,cenacas;
	cin>>n;
	if(n > 0){


		for (int i=0;i<n;i++){
	        cin.get();
			cin.getline(adresa,50);
			cin>>id>>cenacas;
			
			ParkingPlac edna(adresa,id,cenacas);
	        
	        p[i]=edna;
		}
	    
		//plakjanje
		cin>>m;
		for (int i=0;i<m;i++){

			cin>>id>>brojcasovi;
	        
	        int findId=false;
	        for (int j=0;j<m;j++){
	            if (strcmp(p[j].getId(),id)==0){
	                p[j].platiCasovi(brojcasovi);
	                findId=true;
	            }
	        }
			if (!findId)
	        cout<<"Ne e platen parking. Greshen ID."<<endl;
		}

	    cout<<"========="<<endl;
	    ParkingPlac pCentar("Cvetan Dimov","C10",80);
		for (int i=0;i<n;i++)
	        if (p[i].daliIstaAdresa(pCentar))
	            p[i].pecati();
	} else {

		ParkingPlac najdobarPlac("Mars", "1337", 1);
	    int brVozila;
	    cin >> brVozila;
	    for(int i = 0; i < brVozila; ++i){

	    	char boja[20];
	    	char brend[20];
	    	char model[20];

	    	cin >> boja >> brend >> model;
	    	Avtomobil novAvtomobil(boja, brend, model);
	    	najdobarPlac.parkirajVozilo(novAvtomobil);
	    }
	    if(brVozila != 0)
	    najdobarPlac.pecatiParkiraniVozila();

	}  
}
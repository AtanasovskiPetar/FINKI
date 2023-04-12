
#include <iostream>
#include <cstring>
using namespace std;

//vasiot kod ovde
class Masa{
private:
    int length;
    int width;
public:
    Masa(){
        length=0;
        width=0;
    }
    Masa(int _length, int _width){
        length=_length;
        width=_width;
    }
    ~Masa(){}
    void pecati(){
        cout<<" Masa: "<<length<<" "<<width;
    }

};

class Soba{
private:
    int length;
    int width;
    Masa masa;
public:
    Soba(){
        length=0;
        width=0;
    }
    Soba(int _width, int _length, Masa _masa){
        length=_length;
        width=_width;
        masa=_masa;
    }
    ~Soba(){}
    void pecati(){
        cout<<" Soba: "<<width<<" "<<length;
        masa.pecati();

    }
};

class Kukja{
private:
    Soba soba;
    char adress[50];
public:
    Kukja(){
        strcpy(adress,"");
    }
    Kukja(Soba _soba, char *_adress){
        strcpy(adress,_adress);
        soba=_soba;
    }
    ~Kukja(){}
    void pecati(){
        cout<<"Adresa: "<<adress;
        soba.pecati();
        cout<<endl;
    }

};

//ne smee da se menuva main funkcijata!
int main(){
    int n;
    cin>>n;
    for(int i=0;i<n;i++){
    	int masaSirina,masaDolzina;
        cin>>masaSirina;
        cin>>masaDolzina;
    	Masa m(masaSirina,masaDolzina);
    	int sobaSirina,sobaDolzina;
        cin>>sobaSirina;
        cin>>sobaDolzina;
    	Soba s(sobaSirina,sobaDolzina,m);
    	char adresa[30];
        cin>>adresa;
    	Kukja k(s,adresa);
    	k.pecati();
	}

    return 0;
}

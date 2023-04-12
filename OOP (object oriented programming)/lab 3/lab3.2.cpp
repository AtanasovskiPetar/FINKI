#include <iostream>
#include <cstring>

using namespace std;

class Potpisuvac
{
	//vasiot kod ovde
private:
	char name[20];
	char lastName[20];
	char EMBG[20];
public:
    Potpisuvac(){
        strcpy(name,"");
        strcpy(lastName,"");
        strcpy(EMBG, "");
    }
    Potpisuvac(char *_name, char *_lastName, char *_EMBG){
        strcpy(name,_name);
        strcpy(lastName,_lastName);
        strcpy(EMBG, _EMBG);
    }
    Potpisuvac(const Potpisuvac &p){
        strcpy(name,p.name);
        strcpy(lastName,p.lastName);
        strcpy(EMBG, p.EMBG);
    }
    ~Potpisuvac(){}
    char *getEMBG(){
        return EMBG;
    }
};
class Dogovor
{
    //vasiot kod ovde
private:
    int brojDogovor;
    char kategorijaDogovor[50];
    Potpisuvac potpisuvaci[3];
public:
    Dogovor(){
        brojDogovor=0;
        strcpy(kategorijaDogovor,"");
    }
    Dogovor(int _brojDogovor, char *_kategorijaDogovor, Potpisuvac *_potpisuvaci){
        brojDogovor=_brojDogovor;
        strcpy(kategorijaDogovor,_kategorijaDogovor);
        for(int i=0;i<3;i++){
            potpisuvaci[i]=_potpisuvaci[i];        }
    }
    bool proverka(){
        int i,j;
        bool k=false;
        for(i=0;i<3;i++){
            for(j=0;j<3;j++){
                if(i!=j && strcmp(potpisuvaci[i].getEMBG(),potpisuvaci[j].getEMBG())==0){
                    k=true;
                    break;
                }
            }
        }
        return k;
    }
};

//ne smee da se menuva main funkcijata
int main()
{
    char embg[14], ime[20], prezime[20], kategorija[20];
    int broj, n;
    cin >> n;
    for(int i = 0; i < n; i++){
    	cin >> embg >> ime >> prezime;
    	Potpisuvac p1(ime, prezime, embg);
    	cin >> embg >> ime >> prezime;
    	Potpisuvac p2(ime, prezime, embg);
    	cin >> embg >> ime >> prezime;
    	Potpisuvac p3(ime, prezime, embg);
    	cin >> broj >> kategorija;
    	Potpisuvac p[3];
    	p[0] = p1; p[1] = p2; p[2] = p3;
    	Dogovor d(broj, kategorija, p);
        cout << "Dogovor " << broj << ":" << endl; 
    	if(d.proverka() == true){
    	    cout << "Postojat potpishuvaci so ist EMBG" << endl;
    	}
    	else
    	    cout << "Ne postojat potpishuvaci so ist EMBG" << endl;
    }
    return 0;
}
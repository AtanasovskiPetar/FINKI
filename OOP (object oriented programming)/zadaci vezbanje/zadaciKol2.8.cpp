#include <iostream>
#include <cstring>
using namespace std;

class Koncert{
protected:
    char naziv[20];
    char lokacija[20];
    static double sezonskiPopust;
    double cenaBilet;
public:
    Koncert(const char naziv[20]="", const char lokacija[20]="", double cenaBilet=0){
        strcpy(this->naziv,naziv);
        strcpy(this->lokacija,lokacija);
        this->cenaBilet=cenaBilet;
    }
    virtual double cena(){
        return (1-(1.0*sezonskiPopust/100))*cenaBilet;
    }
    char *getNaziv(){
        return naziv;
    }
    static double getSezonskiPopust(){
        return sezonskiPopust/100;
    }
    static void setSezonskiPopust(int pop){
        sezonskiPopust=pop;
    }
};
double Koncert::sezonskiPopust = 20;

class ElektronskiKoncert : public Koncert{
private:
    char *DJime;
    double traenje;
    bool den;
public:
    ElektronskiKoncert(const char naziv[20]="", const char lokacija[20]="", double cenaBilet=0, const char *DJime="", double traenje=0, bool den=false)
    :Koncert(naziv, lokacija, cenaBilet)
    {
        this->DJime = new char [strlen(DJime)+1];
        strcpy(this->DJime,DJime);
        this->traenje=traenje;
        this->den=den;
    }
    ~ElektronskiKoncert(){
        delete [] DJime;
    }
    double cena(){
        double c;
        c=Koncert::cena();
        if(traenje > 7)
            c+=360;
        else if(traenje > 5)
            c+=150;
        if(den)
            c-=50;
        else
            c+=100;
        return c;
    }
};

void najskapKoncert(Koncert ** koncerti, int n){
    int index=0;
    int counter=0;
    for(int i=0;i<n;i++){
        if(koncerti[i]->cena() > koncerti[index]->cena())
            index=i;
        ElektronskiKoncert *casetd = dynamic_cast <ElektronskiKoncert *>(koncerti[i]);
        if(casetd)
            counter++;
    }
    cout<<"Najskap koncert: "<<koncerti[index]->getNaziv()<<" "<<koncerti[index]->cena()<<endl;
    cout<<"Elektronski koncerti: "<<counter<<" od vkupno "<<n<<endl;
}

bool prebarajKoncert(Koncert ** koncerti, int n, char * naziv, bool elektronski){
    if(elektronski){
        for(int i=0;i<n;i++){
            ElektronskiKoncert *casted = dynamic_cast <ElektronskiKoncert *>(koncerti[i]);
            if(casted){
                if(strcmp(koncerti[i]->getNaziv(),naziv)==0){
                    cout<<koncerti[i]->getNaziv()<<" "<<koncerti[i]->cena()<<endl;
                    return true;
                }
            }
        }
    }else{
       for(int i=0;i<n;i++){
            Koncert *casted = dynamic_cast <Koncert *>(koncerti[i]);
            if(casted){
                if(strcmp(koncerti[i]->getNaziv(),naziv)==0){
                    cout<<koncerti[i]->getNaziv()<<" "<<koncerti[i]->cena()<<endl;
                    return true;
                }
            }
        }
    }
    return false;
}

int main(){

    int tip,n,novaCena;
    char naziv[100], lokacija[100], imeDJ[40];
    bool dnevna;
    float cenaBilet, novPopust;
    float casovi;

        cin>>tip;
        if (tip==1){//Koncert
        	cin>>naziv>>lokacija>>cenaBilet;
        	Koncert k1(naziv,lokacija,cenaBilet);
        	cout<<"Kreiran e koncert so naziv: "<<k1.getNaziv()<<endl;
        }
        else if (tip==2){//cena - Koncert
            cin>>naziv>>lokacija>>cenaBilet;
        	Koncert k1(naziv,lokacija,cenaBilet);
        	cout<<"Osnovna cena na koncertot so naziv "<<k1.getNaziv()<< " e: " <<k1.cena()<<endl;
        }
        else if (tip==3){//ElektronskiKoncert
            cin>>naziv>>lokacija>>cenaBilet>>imeDJ>>casovi>>dnevna;
            ElektronskiKoncert s(naziv,lokacija,cenaBilet,imeDJ,casovi,dnevna);
            cout<<"Kreiran e elektronski koncert so naziv "<<s.getNaziv()<<" i sezonskiPopust "<<s.getSezonskiPopust()<<endl;
        }
        else if (tip==4){//cena - ElektronskiKoncert
             cin>>naziv>>lokacija>>cenaBilet>>imeDJ>>casovi>>dnevna;
             ElektronskiKoncert s(naziv,lokacija,cenaBilet,imeDJ,casovi,dnevna);
             cout<<"Cenata na elektronskiot koncert so naziv "<<s.getNaziv()<<" e: "<<s.cena()<<endl;
        }
        else if (tip==5) {//najskapKoncert

        }
        else if (tip==6) {//prebarajKoncert
            Koncert ** koncerti = new Koncert *[5];
            int n;
            koncerti[0] = new Koncert("Area","BorisTrajkovski",350);
            koncerti[1] = new ElektronskiKoncert("TomorrowLand","Belgium",8000,"Afrojack",7.5,false);
            koncerti[2] = new ElektronskiKoncert("SeaDance","Budva",9100,"Tiesto",5,true);
            koncerti[3] = new Koncert("Superhiks","PlatoUkim",100);
            koncerti[4] = new ElektronskiKoncert("CavoParadiso","Mykonos",8800,"Guetta",3,true);
            char naziv[100];
        	najskapKoncert(koncerti,5);
        }
        else if (tip==7){//prebaraj
        	  Koncert ** koncerti = new Koncert *[5];
            int n;
            koncerti[0] = new Koncert("Area","BorisTrajkovski",350);
            koncerti[1] = new ElektronskiKoncert("TomorrowLand","Belgium",8000,"Afrojack",7.5,false);
            koncerti[2] = new ElektronskiKoncert("SeaDance","Budva",9100,"Tiesto",5,true);
            koncerti[3] = new Koncert("Superhiks","PlatoUkim",100);
            koncerti[4] = new ElektronskiKoncert("CavoParadiso","Mykonos",8800,"Guetta",3,true);
            char naziv[100];
            bool elektronski;
        	cin>>elektronski;
        	if(prebarajKoncert(koncerti,5, "Area",elektronski))
                cout<<"Pronajden"<<endl;
            else cout<<"Ne e pronajden"<<endl;

            if(prebarajKoncert(koncerti,5, "Area",!elektronski))
                cout<<"Pronajden"<<endl;
            else cout<<"Ne e pronajden"<<endl;

        }
        else if (tip==8){//smeni cena
        	  Koncert ** koncerti = new Koncert *[5];
            int n;
            koncerti[0] = new Koncert("Area","BorisTrajkovski",350);
            koncerti[1] = new ElektronskiKoncert("TomorrowLand","Belgium",8000,"Afrojack",7.5,false);
            koncerti[2] = new ElektronskiKoncert("SeaDance","Budva",9100,"Tiesto",5,true);
            koncerti[3] = new Koncert("Superhiks","PlatoUkim",100);
            koncerti[2] -> setSezonskiPopust(90);
        	najskapKoncert(koncerti,4);
        }

    return 0;
}

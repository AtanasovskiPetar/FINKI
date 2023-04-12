#include<iostream>
#include<cstring>
#include <string>

using namespace std;

enum Tip{LINUX,UNIX,WINDOWS};

class OperativenSistem{
private:
    char *name;
    float version;
    float size;
    Tip type;
    void copy(const OperativenSistem & os){
        this->name = new char[strlen(os.name)+1];
        strcpy(this->name,os.name);
        this->version=os.version;
        this->type=os.type;
        this->size=os.size;
    }
public:
    OperativenSistem(char *name="", float version=1.0, enum Tip type=LINUX, float size=1.0){
        this->name = new char[strlen(name)+1];
        strcpy(this->name,name);
        this->version=version;
        this->type=type;
        this->size=size;
    }
    OperativenSistem(const OperativenSistem & os){
        copy(os);
    }
    OperativenSistem & operator =(const OperativenSistem & os){
        if(this!=&os){
            delete [] name;
            copy(os);
        }
        return *this;
    }
    ~OperativenSistem(){
        delete [] name;
    }
    void print(){
        cout<<"Ime: "<<name<<" Verzija: "<<version<<" Tip: "<<type<<" Golemina:"<<size<<"GB"<<endl;
    }
    bool ednakviSe(const OperativenSistem &os){
        if(strcmp(this->name,os.name)==0){
            if(this->version==os.version){
                if(this->type==os.type){
                    if(this->size==size){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    int sporediVerzija(const OperativenSistem &os){
        if(this->version==os.version){
            return 0;
        }
        else if(this->version<os.version){
            return -1;
        }
        else{
            return 1;
        }
    }
    bool istaFamilija(const OperativenSistem &os){
        if(strcmp(this->name,os.name)==0){
            if(this->type==os.type){
                return true;
            }
        }
        return false;
    }

};

class Repozitorium{
private:
    char name[20];
    OperativenSistem *os;
    int numOfOSMomentarily;
    void copy(const Repozitorium & r){
        strcpy(this->name,r.name);
        this->numOfOSMomentarily=r.numOfOSMomentarily;
        this->os=new OperativenSistem[this->numOfOSMomentarily];
        for(int i=0; i<numOfOSMomentarily;i++){
            this->os[i]=r.os[i];
        }
    }
public:
    Repozitorium(const char *name=""){
        strcpy(this->name,name);
        this->numOfOSMomentarily=0;
        this->os=new OperativenSistem[numOfOSMomentarily];
    }
    Repozitorium(const Repozitorium & r){
        copy(r);
    }
    Repozitorium &operator=(const Repozitorium & r){
        if(this!=&r){
            delete [] os;
            copy(r);
        }
        return *this;
    }
    ~Repozitorium(){
        delete [] os;
    }
    void dodadi(const OperativenSistem &newOS){
        for(int i=0;i<numOfOSMomentarily;i++){
            if(os[i].istaFamilija(newOS)){
                if(os[i].sporediVerzija(newOS)==-1){
                    izbrishi(os[i]);
                }
            }
        }
        OperativenSistem *tmp = new OperativenSistem[numOfOSMomentarily+1];
        for(int i=0;i<numOfOSMomentarily;i++){
            tmp[i]=os[i];
        }
        tmp[numOfOSMomentarily++]=newOS;
        delete [] os;
        os=tmp;
    }
    void izbrishi(const OperativenSistem &del){
        bool flag=false;
        for(int i=0;i<numOfOSMomentarily;i++){
            if(os[i].ednakviSe(del)){
                flag=true;
                break;
            }
        }
        if(flag==false){
            return;
        }
        flag=false;
        OperativenSistem *tmp = new OperativenSistem[numOfOSMomentarily-1];
        int j=0;
        for(int i=0;i<numOfOSMomentarily;i++){
            if(!os[i].ednakviSe(del) || flag==true){
                tmp[j++]=os[i];
            }else{
                flag=true;
            }
        }
        numOfOSMomentarily--;
        delete [] os;
        os=tmp;
    }
    void pecatiOperativniSistemi(){
        cout<<"Repozitorium: "<<name<<endl;
        for(int i=0;i<numOfOSMomentarily;i++){
            os[i].print();
        }
    }
};

int main() {
    char repoName[20];
    cin>>repoName;
    Repozitorium repozitorium=Repozitorium(repoName);
    int brojOperativniSistemi = 0;
    cin>>brojOperativniSistemi;
    char ime[20];
    float verzija;
    int tip;
    float golemina;
    for (int i = 0; i<brojOperativniSistemi; i++){
        cin>>ime;
        cin>>verzija;
        cin>>tip;
        cin>>golemina;
        OperativenSistem os = OperativenSistem(ime, verzija, (Tip)tip, golemina);
        repozitorium.dodadi(os);
    }

    repozitorium.pecatiOperativniSistemi();
     cin>>ime;
    cin>>verzija;
    cin>>tip;
    cin>>golemina;
    OperativenSistem os = OperativenSistem(ime, verzija, (Tip)tip, golemina);
    cout<<"=====Brishenje na operativen sistem====="<<endl;
    repozitorium.izbrishi(os);
    repozitorium.pecatiOperativniSistemi();
    return 0;
}
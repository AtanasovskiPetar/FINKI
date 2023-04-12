#include <iostream>
using namespace std;

class Agol{
private:
    float stepeni;
    float minuti;
    float sekundi;
public:
    Agol(){
        stepeni=0.0;
        minuti=0.0;
        sekundi=0.0;
    }
    Agol(float stepeni, float minuti, float sekundi){
        this->stepeni=stepeni;
        this->minuti=minuti;
        this->sekundi=sekundi;
    }
    ~Agol(){}
    Agol(const Agol &a){
        this->stepeni=a.stepeni;
        this->minuti=a.minuti;
        this->sekundi=a.sekundi;
    }
    void print(){
        cout<<stepeni<<" stepeni,"<<minuti<<" minuti i "<<sekundi<<" sekundi."<<endl;
    }
    void set_stepeni(float i){
        stepeni=i;
    }
    void set_minuti(float i){
        minuti=i;
    }
    void set_sekundi(float i){
        sekundi=i;
    }
    float getSekundi() const{
        return sekundi;
    }
    float to_sekundi(){
        return sekundi+60*minuti+3600*stepeni;
    }
};


bool changeOfSeconds(Agol a, int sec){
	return a.getSekundi()!=sec;
}

int proveri(float deg, float min, float sec){
    if(deg<360 && deg>=0){
        if (min<60 && min>=0){
            if (sec<60 && sec>=0){
                return 1;
            }
            else{
                return 0;
            }
        }else{
            return 0;
        }
    }
    else{
        return 0;
    }
}

int main() {
    
    Agol a1;
    int deg, min, sec;
    cin >> deg >> min >> sec;
    
    if (proveri(deg, min, sec)) {
    
    	a1.set_stepeni(deg);
        a1.set_minuti(min);
        a1.set_sekundi(sec);
        cout << a1.to_sekundi();
        if (changeOfSeconds(a1,sec))
			cout << "Ne smeete da gi menuvate sekundite vo ramkite na klasata!" << endl;
        
    }else{
        cout<<"Nevalidni vrednosti za agol";
    }
    
    return 0;
}

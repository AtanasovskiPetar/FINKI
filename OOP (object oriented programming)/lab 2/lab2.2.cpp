#include <iostream>
using namespace std;

class Krug {
private:
    float r;
    const float pi=3.14;
public:
    Krug(){
        r=1;
    }
    Krug(float r){
        this->r=r;
    }
    ~Krug(){}
    Krug(const Krug &k){
        r=k.r;
    }
    float plostina(){
        return r*r*pi;
    }
    float perimetar(){
        return 2*r*pi;
    }
    int ednakvi(){
        //if(r*r*pi==2*r*pi){
        if(plostina()==perimetar()){
            return 1;
        }
        else{
            return 0;
        }
    }
};

int main() {
	float r;
	cin >> r;
	//instanciraj objekt od klasata Krug cij radius e vrednosta procitana od tastatura
	Krug k(r);
	cout << k.perimetar() << endl;
	cout << k.plostina() << endl;
	cout << k.ednakvi() <<endl;
    //instanciraj objekt od klasata Krug cij radius ne e definiran
    Krug k1;
	return 0;
}
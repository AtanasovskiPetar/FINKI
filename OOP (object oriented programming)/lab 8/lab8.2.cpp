/*
Во една игротека има 2 типа играчки: топки и коцки. Коцките и топките се опишани со параметри како што се:

боја (char *)
густина (int).
Дополнително за топките се знае и радиусот (int), додека за коцките целосните димензии (висина, ширина и длабочина – int).

За секоја од класите треба да се дефинираат методи getMasa() и getVolumen(). Масата на играчката се пресметува како производ од волуменот и густината на играчката. За PI користете ја вредноста 3.14.

Во функцијата main да се декларира променлива kupche што претставува динамички алоцирана низа кон Igrachka. Во зависност од првиот влезен параметар се внесуваат објекти од класите Topka или Kocka (1 - се внесува топка, 2 - се внесува коцка).

Од тастатура да се внесат податоци за коцката на Петра Kocka petra. Во главната функција во да се отпечатат:

Да се отпечати DA ако вкупната маса на сите играчки е поголема од масата на играчката на Петра, а NE во спротивно.
Разликата по апсолутна вредност на волуменот на играчката со максимален волумен во купчето и волуменот на коцката на Петра. Форматот е:

Razlikata e: {razlika}
Задачата да се реши со тоа што класите Kocka и Topka ќе наследуваат од класите Forma и Igrachka.

Дополнителни барања:

Во класата Igrachka да се додаде уште една чисто виртуелна функција float getPlostina(). Истата да се имплементира во класите Kocka и Topka
Во главната функција, дополнително да се испечати и: Разликата по апсолутна вредност на плоштината на играчката со минимална плоштина во купчето и плоштината на коцката на Петра во истиот формат како и второто барање погоре.
*/
#include<iostream>
#include <cstring>
using namespace std;

class Igrachka {
public:
    virtual float getVolumen() = 0;
    virtual float getMasa() = 0;
    virtual float getPlostina()=0;
};

class Forma {
protected:
    char boja[100];
    int gustina;
public:
    Forma(char boja[100]="", int gustina=0){
        strcpy(this->boja,boja);
        this->gustina=gustina;
    }
};

class Kocka:public Forma, public Igrachka{
private:
    int a;
    int b;
    int c;
public:
    Kocka(char boja[100]="", int gustina=0, int a=0, int b=0, int c=0):Forma(boja, gustina), Igrachka(){
        this->a=a;
        this->b=b;
        this->c=c;
    }
    float getMasa(){
        return 1.0*getVolumen()*gustina;
    }
    float getVolumen(){
        return 1.0*a*b*c;
    }
    float getPlostina(){
        return 2*a*b + 2*b*c + 2*c*a;
    }
};

class Topka:public Forma, public Igrachka{
private:
    int r;
public:
    Topka(char boja[100]="", int gustina=0, int r=0):Forma(boja, gustina), Igrachka(){
        this->r=r;
    }
    float getMasa(){
        return 1.0*getVolumen()*gustina;
    }
    float getVolumen(){
        return 1.0*4/3*3.14*r*r*r;
    }
    float getPlostina(){
        return 3.14*4*r*r;
    }
};

float vkupnaMasa(Igrachka **igracki, int n){
    float vkupna=0;
    for(int i=0;i<n;i++){
        vkupna+=igracki[i]->getMasa();
    }
    return vkupna;
}

float maxVolume(Igrachka **igracki, int n){
    float m=igracki[0]->getVolumen();
    for(int i=0;i<n;i++){
        if(igracki[i]->getVolumen() > m)
            m=igracki[i]->getVolumen();
    }
    return m;
}

float minPloshtina(Igrachka **igracki, int n){
    float m=igracki[0]->getPlostina();
    for(int i=0;i<n;i++){
        if(igracki[i]->getPlostina() < m)
            m=igracki[i]->getPlostina();
    }
    return m;
}

int main(){
	//vnesi informacii za kupche
	int n;
	cin>>n;
    Igrachka **kupche = new Igrachka*[n];
    for(int i=0;i<n;i++){
        int type;
        cin>>type;
        char boja[100];
        cin>>boja;
        int gustina;
        cin>>gustina;
        if(type==1){
            int r;
            cin>>r;
            kupche[i]=new Topka(boja,gustina,r);
        }else{
            int a,b,c;
            cin>>a>>b>>c;
            kupche[i]=new Kocka(boja,gustina,a,b,c);
        }
    }
    char boja[100];
    cin>>boja;
    int gustina;
    cin>>gustina;
    int a,b,c;
    cin>>a>>b>>c;
    Kocka petra(boja,gustina,a,b,c);
    if(vkupnaMasa(kupche,n)>petra.getMasa())
        cout<<"DA"<<endl;
    float max=maxVolume(kupche,n);
    if(petra.getVolumen() > max)
        cout<<"Razlikata e: "<<petra.getVolumen() - max<<endl;
    else
        cout<<"Razlikata e: "<<max - petra.getVolumen()<<endl;
    float min=minPloshtina(kupche,n);
    if(petra.getPlostina() > min)
        cout<<"Razlikata e: "<<petra.getPlostina() - min<<endl;
    else
        cout<<"Razlikata e: "<<min - petra.getPlostina()<<endl;
    //дополнително барање 2

	return 0;
}

/*
Да се напише класа Automobile во која се чуваат информации за марката на автомобилот (динамички алоцирана низа од знаци), регистрација (динамички алоцирана низа од 5 цели броја) и максимална брзина (цел број). За класата да се обезбедат set и get методите што се користат и да се преоптоварат следните оператори:
NBAPlayer
Да се дефинира класа NBAPlayer за која ќе се чуваат:

динамички алоцирана низа од карактери за името на играчот
низа од максимум 40 карактери за тимот во кој играчот моментално настапува
просечен број на поени на играчот оваа сезона (double)
просечен број на асистенции на играчот оваа сезона (double)
просечен број на скокови на играчот оваа сезона (double)
За потребите на класата да се дефинираат:

default конструктор и конструктор со аргументи
copy constructor и оператор =
деструктор
метод rating() кој го враќа рејтингот на кошаркарот кој се пресметува како:

45% од поените + 30% од асистенциите + 25% од скоковите

метод print() кој го печати играчот во следниот формат:

Име - тим

Points: поени

Assists: асистенции

Rebounds: скокови

Rating: рејтинг

AllStarPlayer
Од претходната класа NBAPlayer да се изведе класата AllStarPlayer за која дополнително ќе се чуваат и:

просечен број на поени на играчот од All Star натпреварите (double)
просечен број на асистенции на играчот од All Star натпреварите (double)
просечен број на скокови на играчот од All Star натпреварите (double)
За потребите на класата да се дефинираат:

default конструктор
конструктор кој прима објект од NBAPlayer и плус додатните информации (погледни main)
конструктор кој ги прима сите аргументи (погледни main)
copy constructor, оператор =, деструктор
метод allStarRating() кој го враќа рејтингот на кошаркарот од All Star натпреварите и кој се пресметува како:

30% од поените + 40% од асистенциите + 30% од скоковите

Да се препокријат методите:

rating() кој го враќа просекот од обичниот рејтинг на кошаркарот и неговиот All Star рејтинг
print() кој покрај основните информации за кошаркарот печати и:

All Star Rating: рејтингот од All Star натпреварите

New Rating: просечниот рејтинг
*/

#include <iostream>
#include <cstring>
using namespace std;

class NBAPlayer{
protected:
    char *name;
    char team[40];
    double pointsAvg;
    double assistsAvg;
    double reboundsAvg;
    void copy(const NBAPlayer &p){
        this->name=new char[strlen(p.name)+1];
        strcpy(this->name,p.name);
        strcpy(this->team,p.team);
        this->pointsAvg=p.pointsAvg;
        this->assistsAvg=p.assistsAvg;
        this->reboundsAvg=p.reboundsAvg;
    }
public:
    NBAPlayer(char *name="", char team[40]="", double pointsAvg=0.0, double assistsAvg=0.0, double reboundsAvg=0.0){
        this->name=new char[strlen(name)+1];
        strcpy(this->name,name);
        strcpy(this->team,team);
        this->pointsAvg=pointsAvg;
        this->assistsAvg=assistsAvg;
        this->reboundsAvg=reboundsAvg;
    }
    NBAPlayer(const NBAPlayer &p){
        copy(p);
    }
    NBAPlayer & operator=(const NBAPlayer &p){
        if(this!=&p){
            delete [] name;
            copy(p);
        }return *this;
    }
    ~NBAPlayer(){
        delete [] name;
    }
    double rating(){
        return (0.45*pointsAvg+0.3*assistsAvg+0.25*reboundsAvg);
    }
    void print(){
        cout<<name<<" - "<<team<<endl;
        cout<<"Points: "<<pointsAvg<<endl;
        cout<<"Assists: "<<assistsAvg<<endl;
        cout<<"Rebounds: "<<reboundsAvg<<endl;
        cout<<"Rating: "<<rating()<<endl;
    }
};

class AllStarPlayer: public NBAPlayer{
private:
    double allStarPts;
    double allStarAst;
    double allStarReb;
    void copyAllStar(const AllStarPlayer &a){
        copy(a);
        this->allStarPts=a.allStarPts;
        this->allStarAst=a.allStarAst;
        this->allStarReb=a.allStarReb;
    }
public:
    AllStarPlayer(NBAPlayer player, double allStarPts=0.0, double allStarAst=0.0, double allStarReb=0.0):NBAPlayer(player){
        this->allStarPts=allStarPts;
        this->allStarAst=allStarAst;
        this->allStarReb=allStarReb;
    }
    AllStarPlayer(char *name="", char team[40]="", double points=0.0, double assists=0.0, double rebounds=0.0, double allStarPoints=0.0, double allStarAssists=0.0, double allStarRebounds=0.0){
        strcpy(this->name,name);
        strcpy(this->team,team);
        this->pointsAvg=points;
        this->assistsAvg=assists;
        this->reboundsAvg=rebounds;
        this->allStarPts=allStarPoints;
        this->allStarAst=allStarAssists;
        this->allStarReb=allStarRebounds;
    }
    AllStarPlayer(const AllStarPlayer &a){
        copyAllStar(a);
    }
    AllStarPlayer & operator = (const AllStarPlayer &a){
        if(this!=&a){
            delete [] name;
            copyAllStar(a);
        }return *this;
    }
    double  allStarRating(){
        return (0.3*allStarPts+0.4*allStarAst+0.3*allStarReb);
    }
    double rating(){
        return (NBAPlayer::rating()+allStarRating())/2;
    }
    void print(){
        NBAPlayer::print();
        cout<<"All Star Rating: "<<allStarRating()<<endl;
        cout<<"New Rating: "<<rating()<<endl;
    }
};

int main() {

  char name[50];
  char team[40];
  double points;
  double assists;
  double rebounds;
  double allStarPoints;
  double allStarAssists;
  double allStarRebounds;

  NBAPlayer * players = new NBAPlayer[5];
  AllStarPlayer * asPlayers = new AllStarPlayer[5];
  int n;
  cin >> n;

  if (n == 1) {

    cout << "NBA PLAYERS:" << endl;
    cout << "=====================================" << endl;
    for (int i = 0; i < 5; ++i) {
      cin >> name >> team >> points >> assists >> rebounds;
      players[i] = NBAPlayer(name,team,points,assists,rebounds);
      players[i].print();
    }
  }
  else if (n == 2) {

    for (int i=0; i < 5; ++i){
      cin >> name >> team >> points >> assists >> rebounds;
      cin >> allStarPoints >> allStarAssists >> allStarRebounds;
      players[i] = NBAPlayer(name,team,points,assists,rebounds);
      asPlayers[i] = AllStarPlayer(players[i],allStarPoints,allStarAssists,allStarRebounds);
    }

    cout << "NBA PLAYERS:" << endl;
    cout << "=====================================" << endl;
    for (int i=0; i < 5; ++i)
      players[i].print();

    cout << "ALL STAR PLAYERS:" << endl;
    cout << "=====================================" << endl;
    for (int i=0; i < 5; ++i)
      asPlayers[i].print();

    }
    else if (n == 3) {

      for (int i=0; i < 5; ++i){
        cin >> name >> team >> points >> assists >> rebounds;
        cin >> allStarPoints >> allStarAssists >> allStarRebounds;
        asPlayers[i] = AllStarPlayer(name, team, points, assists, rebounds,
                                     allStarPoints,allStarAssists,allStarRebounds);
      }
      cout << "ALL STAR PLAYERS:" << endl;
      cout << "=====================================" << endl;
      for (int i=0; i < 5; ++i)
        asPlayers[i].print();

    }

    delete [] players;
    delete [] asPlayers;
    return 0;
}


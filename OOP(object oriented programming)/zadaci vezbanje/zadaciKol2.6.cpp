#include <iostream>
#include <cstring>

using namespace std;

class ExistingGame{
public:
    void message(){
        cout<<"The game is already in the collection"<<endl;
    }
};

class Game{
protected:
    char name[100];
    double price;
    bool sale;
public:
    Game(const char name[100]="", double price=0, bool sale=true){
        strcpy(this->name,name);
        this->price=price;
        this->sale=sale;
    }
    friend ostream & operator << (ostream & out, Game & g){
        out<<"Game: "<<g.name<<", regular price: $"<<g.price;
        if(g.sale)
            out<<", bought on sale";
        return out;
    }
    friend istream & operator >> (istream & in, Game & g){
        in.get();
        in.getline(g.name,100);
        in>>g.price>>g.sale;
        return in;
    }
    bool operator == (Game & g){
        if(strcmp(this->name,g.name)==0)
            return true;
        return false;
    }
    virtual double getPrice(){
        if(sale)
            return 0.7*price;
        return price;
    }
};

class SubscriptionGame:public Game{
private:
    int monthlySubscription;
    int month;
    int year;
public:
    SubscriptionGame(const char name[100]="", double price=0, bool sale=true, int monthlySubscription=0, int month=1, int year=1):Game(name,price,sale){
        this->monthlySubscription=monthlySubscription;
        this->month=month;
        this->year=year;
    }
    friend ostream & operator << (ostream & out, SubscriptionGame & g){
        out<<"Game: "<<g.name<<", regular price: $"<<g.price;
        if(g.sale)
            out<<", bought on sale";
        out<<", monthly fee: $"<<g.monthlySubscription<<", purchased: "<<g.month<<"-"<<g.year<<endl;
        return out;
    }
    friend istream & operator >> (istream & in, SubscriptionGame & g){
        in.get();
        in.getline(g.name, 100);
        in>>g.price>>g.sale>>g.monthlySubscription>>g.month>>g.year;
        return in;
    }
    double getPrice(){
        int m;
        m=(12*2018 + 5) - (12*year - month);
        if (sale)
            return 0.7*price+1.0*m*monthlySubscription;
        return price+1.0*m*monthlySubscription;
    }
};

class User{
private:
    char username[100];
    Game **games;
    int num;
    void copy(const User &u){
        strcpy(this->username,u.username);
        this->num=u.num;
        this->games=new Game*[this->num];
        for(int i=0;i<this->num;i++){
            this->games[i]=u.games[i];
        }
    }
public:
    User(const char username[100]=""){
        strcpy(this->username,username);
        this->num=0;
        this->games=new Game*[0];
    }
    User(const User & u){
        copy(u);
    }
    User & operator = (const User & u){
        if(this!=&u){
            delete [] games;
            copy(u);
        }return *this;
    }
    ~User(){
        delete [] games;
    }
    User & operator += (Game & g){
        for(int i=0;i<num;i++){
            if(*(games[i]) == g){
                throw ExistingGame();
                return *this;
            }
        }
        Game **tmp=new Game*[num+1];
        for(int i=0;i<num;i++){
            tmp[i]=games[i];
        }
        SubscriptionGame *g1=dynamic_cast<SubscriptionGame *>(&g);
        if(g1)
            tmp[num++]=new SubscriptionGame(*g1);
        else
            tmp[num++]=new Game(g);
        delete [] games;
        games=tmp;
        return *this;
    }
    friend ostream & operator << (ostream & out, User & u){
        out<<endl;
        out<<"User: "<<u.username<<endl;
        for(int i=0;i<u.num;i++){
            out<<"- "<<*(u.games[i])<<endl;
        }
        return out;
    }
    double total_spent(){
        int totalPrice=0;
        for(int i=0;i<num;i++)
            totalPrice+=games[i]->getPrice();
        return totalPrice;
    }
};

int main() {
  int test_case_num;

  cin>>test_case_num;

  // for Game
  char game_name[100];
  float game_price;
  bool game_on_sale;

  // for SubscritionGame
  float sub_game_monthly_fee;
  int sub_game_month, sub_game_year;

  // for User
  char username[100];
  int num_user_games;

  if (test_case_num == 1){
    cout<<"Testing class Game and operator<< for Game"<<std::endl;
    cin.get();
    cin.getline(game_name,100);
    //cin.get();
    cin>>game_price>>game_on_sale;

    Game g(game_name, game_price, game_on_sale);

    cout<<g;
  }
  else if (test_case_num == 2){
    cout<<"Testing class SubscriptionGame and operator<< for SubscritionGame"<<std::endl;
    cin.get();
    cin.getline(game_name, 100);

    cin>>game_price>>game_on_sale;

    cin>>sub_game_monthly_fee;
    cin>>sub_game_month>>sub_game_year;

    SubscriptionGame sg(game_name, game_price, game_on_sale, sub_game_monthly_fee, sub_game_month, sub_game_year);
    cout<<sg;
  }
  else if (test_case_num == 3){
    cout<<"Testing operator>> for Game"<<std::endl;
    Game g;

    cin>>g;

    cout<<g;
  }
  else if (test_case_num == 4){
    cout<<"Testing operator>> for SubscriptionGame"<<std::endl;
    SubscriptionGame sg;

    cin>>sg;

    cout<<sg;
  }
  else if (test_case_num == 5){
    cout<<"Testing class User and operator+= for User"<<std::endl;
    cin.get();
    cin.getline(username,100);
    User u(username);

    int num_user_games;
    int game_type;
    cin >>num_user_games;

    try {

      for (int i=0; i<num_user_games; ++i){

        cin >> game_type;

        Game *g;
        // 1 - Game, 2 - SubscriptionGame
        if (game_type == 1){
          cin.get();
          cin.getline(game_name, 100);

          cin>>game_price>>game_on_sale;
          g = new Game(game_name, game_price, game_on_sale);
        }
        else if (game_type == 2){
          cin.get();
          cin.getline(game_name, 100);

          cin>>game_price>>game_on_sale;

          cin>>sub_game_monthly_fee;
          cin>>sub_game_month>>sub_game_year;
          g = new SubscriptionGame(game_name, game_price, game_on_sale, sub_game_monthly_fee, sub_game_month, sub_game_year);
        }

        //cout<<(*g);


        u+=(*g);
      }
    }catch(ExistingGame &ex){
      ex.message();
    }

    cout<<u;

//    cout<<"\nUser: "<<u.get_username()<<"\n";

//    for (int i=0; i < u.get_games_number(); ++i){
//        Game * g;
//        SubscriptionGame * sg;
//        g = &(u.get_game(i));

//        sg = dynamic_cast<SubscriptionGame *> (g);

//        if (sg){
//          cout<<"- "<<(*sg);
//        }
//        else {
//          cout<<"- "<<(*g);
//        }
//        cout<<"\n";
//    }

  }
  else if (test_case_num == 6){
      cout<<"Testing exception ExistingGame for User"<<std::endl;
      cin.get();
      cin.getline(username,100);
      User u(username);

      int num_user_games;
      int game_type;
      cin >>num_user_games;

      for (int i=0; i<num_user_games; ++i){

          cin >> game_type;

          Game *g;
          // 1 - Game, 2 - SubscriptionGame
          if (game_type == 1){
            cin.get();
            cin.getline(game_name, 100);

            cin>>game_price>>game_on_sale;
            g = new Game(game_name, game_price, game_on_sale);
          }
          else if (game_type == 2){
            cin.get();
            cin.getline(game_name, 100);

            cin>>game_price>>game_on_sale;

            cin>>sub_game_monthly_fee;
            cin>>sub_game_month>>sub_game_year;
            g = new SubscriptionGame(game_name, game_price, game_on_sale, sub_game_monthly_fee, sub_game_month, sub_game_year);
          }

          //cout<<(*g);

          try {
            u+=(*g);
          }
          catch(ExistingGame &ex){
            ex.message();
          }
        }

      cout<<u;

//      for (int i=0; i < u.get_games_number(); ++i){
//          Game * g;
//          SubscriptionGame * sg;
//          g = &(u.get_game(i));

//          sg = dynamic_cast<SubscriptionGame *> (g);

//          if (sg){
//            cout<<"- "<<(*sg);
//          }
//          else {
//            cout<<"- "<<(*g);
//          }
//          cout<<"\n";
//      }
  }
  else if (test_case_num == 7){
      cout<<"Testing total_spent method() for User"<<std::endl;
      cin.get();
      cin.getline(username,100);
      User u(username);

      int num_user_games;
      int game_type;
      cin >>num_user_games;

      for (int i=0; i<num_user_games; ++i){

          cin >> game_type;

          Game *g;
          // 1 - Game, 2 - SubscriptionGame
          if (game_type == 1){
            cin.get();
            cin.getline(game_name, 100);

            cin>>game_price>>game_on_sale;
            g = new Game(game_name, game_price, game_on_sale);
          }
          else if (game_type == 2){
            cin.get();
            cin.getline(game_name, 100);

            cin>>game_price>>game_on_sale;

            cin>>sub_game_monthly_fee;
            cin>>sub_game_month>>sub_game_year;
            g = new SubscriptionGame(game_name, game_price, game_on_sale, sub_game_monthly_fee, sub_game_month, sub_game_year);
          }

          //cout<<(*g);


          u+=(*g);
        }

      cout<<u;

      cout<<"Total money spent: $"<<u.total_spent()<<endl;
  }

  return 0;
}

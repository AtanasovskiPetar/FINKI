#include <iostream>
#include <cstring>

using namespace std;

enum typeGenetic{RNA,DNA};
enum typeInactivated{DMG, PEG, SM_102, DPSC};

class CovidVaccine{
protected:
    char company[100];
    char *name;
    double efficiency;
    void copy(const CovidVaccine &c){
        this->name=new char [strlen(c.name)+1];
        strcpy(this->name,c.name);
        strcpy(this->company,c.company);
        this->efficiency=c.efficiency;
    }
public:
    CovidVaccine(const char company[100]="", const char *name="", double efficiency=0)
     {
        this->name=new char [strlen(name)+1];
        strcpy(this->name,name);
        strcpy(this->company,company);
        this->efficiency=efficiency;
     }
     CovidVaccine (const CovidVaccine &c){
        copy(c);
     }
     CovidVaccine & operator = (const CovidVaccine &c){
        if(this!=&c){
            delete [] name;
            copy(c);
        }return *this;
     }
     virtual double calculateVaccineEfficiency()=0;
};

class GeneticVaccine : public CovidVaccine{
private:
    typeGenetic saving;
public:
    GeneticVaccine(const char company[100]="", const char *name="", double efficiency=0, short saving=0)
    :CovidVaccine(company,name,efficiency){
        this->saving = (typeGenetic) saving;
    }
    double calculateVaccineEfficiency(){
        if(saving == RNA)
            return 1.35*efficiency;
        else
            return 1.55*efficiency;
    }
    friend ostream & operator << (ostream & out, GeneticVaccine &g){
        out<<"Vaccine info: "<<g.name<<" - "<<g.company<<" - "<<g.calculateVaccineEfficiency()<<endl;
        out<<"Mechanism for storing genetic information: ";
        switch(g.saving){
            case (RNA):
                out<<"RNA"<<endl;
                break;
            case (DNA):
                out<<"DNA"<<endl;
                break;
            default:
                out<<"ERROR"<<endl;
                break;
        }
        return out;
    }
};

class InactivatedVaccine : public CovidVaccine{
private:
    typeInactivated ingredient;
public:
    InactivatedVaccine(const char company[100]="", const char *name="", double efficiency=0, short ingredient=0)
    :CovidVaccine(company,name,efficiency){
        this->ingredient = (typeInactivated) ingredient;
    }
    double calculateVaccineEfficiency(){
        if(ingredient == DMG)
            return 0.9*efficiency;
        else if(ingredient == PEG)
            return 1.12*efficiency;
        else if(ingredient == SM_102)
            return 0.85*efficiency;
        else
            return 1.20*efficiency;
    }
    friend ostream & operator << (ostream & out, InactivatedVaccine &g){
        out<<"Vaccine info: "<<g.name<<" - "<<g.company<<" - "<<g.calculateVaccineEfficiency()<<endl;
        out<<"The vaccine consists of the following ingredient: ";
        switch(g.ingredient){
            case (DMG):
                out<<"DMG"<<endl;
                break;
            case (PEG):
                out<<"PEG"<<endl;
                break;
            case (SM_102):
                out<<"SM_102"<<endl;
                break;
            case (DPSC):
                out<<"DPSC"<<endl;
                break;
            default:
                out<<"ERROR"<<endl;
                break;
        }
        return out;
    }
};

bool operator <= (CovidVaccine & c1, CovidVaccine & c2){
    if(c1.calculateVaccineEfficiency() > c2.calculateVaccineEfficiency()){
        return false;
    }return true;
}

void mostEffectiveInactivatedVaccine(CovidVaccine **vaccines, int n){
    InactivatedVaccine *v=nullptr;
    for(int i=0;i<n;i++){
        InactivatedVaccine *casted=dynamic_cast<InactivatedVaccine*>(vaccines[i]);
        if(casted){
            if(v==nullptr){
                v=casted;
            }
            else if(casted->calculateVaccineEfficiency() > v->calculateVaccineEfficiency()){
                v=casted;
            }
        }
    }
    cout<<*(v);
}

int main() {
    int test_case;

    cin >> test_case;

    //For CovidVaccine
    char manufacturer[100];
    char vaccineName[100];
    float basicEfficiencyCoefficient;

    //For GeneticVaccine
    unsigned short geneticMechanism; // 0 -> RNA, 1 -> DNA

    //For InactivatedVaccine
    unsigned short constituentIngredient; //0 -> DMG, 1 -> PEG, 2 -> SM_102, 3 -> DPSC

    if (test_case == 1) {
        // Test Case GeneticVaccine - Constructor, operator <<, calculateVaccineEfficiency
        cout << "Testing class GeneticVaccine, operator<< and calculateVaccineEfficiency" << endl;
        cin.get();
        cin.getline(manufacturer, 100);
        cin.getline(vaccineName, 100);
        cin >> basicEfficiencyCoefficient;
        cin >> geneticMechanism;

        GeneticVaccine gv(manufacturer, vaccineName, basicEfficiencyCoefficient, geneticMechanism);

        cout << gv;
    } else if (test_case == 2) {
        // Test Case InactivatedVaccine - Constructor, operator <<, calculateVaccineEfficiency
        cout << "Testing class InactivatedVaccine, operator<< and calculateVaccineEfficiency" << endl;
        cin.get();
        cin.getline(manufacturer, 100);
        cin.getline(vaccineName, 100);
        cin >> basicEfficiencyCoefficient;
        cin >> constituentIngredient;

        InactivatedVaccine iv(manufacturer, vaccineName, basicEfficiencyCoefficient, constituentIngredient);

        cout << iv;
    } else if (test_case == 3) {
        cout << "Testing operator <=" << endl;

        cin.get();
        cin.getline(manufacturer, 100);
        cin.getline(vaccineName, 100);
        cin >> basicEfficiencyCoefficient;
        cin >> geneticMechanism;

        GeneticVaccine gv(manufacturer, vaccineName, basicEfficiencyCoefficient, geneticMechanism);

        cin.get();
        cin.getline(manufacturer, 100);
        cin.getline(vaccineName, 100);
        cin >> basicEfficiencyCoefficient;
        cin >> constituentIngredient;

        InactivatedVaccine iv(manufacturer, vaccineName, basicEfficiencyCoefficient, constituentIngredient);

        if (gv <= iv) {
            cout << iv;
        } else {
            cout << gv;
        }
    } else if (test_case == 4) {
        cout << "Testing operator <=" << endl;

        cin.get();
        cin.getline(manufacturer, 100);
        cin.getline(vaccineName, 100);
        cin >> basicEfficiencyCoefficient;
        cin >> geneticMechanism;

        GeneticVaccine gv1(manufacturer, vaccineName, basicEfficiencyCoefficient, geneticMechanism);

        cin.get();
        cin.getline(manufacturer, 100);
        cin.getline(vaccineName, 100);
        cin >> basicEfficiencyCoefficient;
        cin >> geneticMechanism;

        GeneticVaccine gv2(manufacturer, vaccineName, basicEfficiencyCoefficient, geneticMechanism);

        if (gv1 <= gv2) {
            cout << gv2;
        } else {
            cout << gv1;
        }
    } else if (test_case == 5) {
        cout << "Testing function: mostEffectiveInactivatedVaccine " << endl;

        //1 - GeneticVaccine
        //2 - InactivatedVaccine
        short vaccineType;

        int number_of_vaccines;
        CovidVaccine **vaccine_array;

        cin >> number_of_vaccines;

        vaccine_array = new CovidVaccine *[number_of_vaccines];

        for (int i = 0; i < number_of_vaccines; ++i) {
            cin >> vaccineType;

            cin.get();
            cin.getline(manufacturer, 100);
            cin.getline(vaccineName, 100);
            cin >> basicEfficiencyCoefficient;

            switch (vaccineType) {
                case 1:
                    cin >> geneticMechanism;
                    vaccine_array[i] = new GeneticVaccine(manufacturer, vaccineName, basicEfficiencyCoefficient,
                                                          geneticMechanism);
                    break;
                case 2:
                    cin >> constituentIngredient;
                    vaccine_array[i] = new InactivatedVaccine(manufacturer, vaccineName, basicEfficiencyCoefficient,
                                                              constituentIngredient);
                    break;
            }

        }

        mostEffectiveInactivatedVaccine(vaccine_array, number_of_vaccines);
        delete[] vaccine_array;
    }

    return 0;
}

#include <iostream>
#include <cstring>
#include <string>

using namespace std;

class List{
private:
    int *numbers;
    int numOfNumbers;
    void copy(const List &l){
        this->numOfNumbers=l.numOfNumbers;
        this->numbers=new int[this->numOfNumbers];
        for(int i=0;i<this->numOfNumbers;i++){
            this->numbers[i]=l.numbers[i];
        }
    }
public:
    List(){
        this->numOfNumbers=0;
        this->numbers = new int[numOfNumbers];
    }
    List(int *numbers, int numOfNumbers){
        this->numOfNumbers=numOfNumbers;
        this->numbers=new int[this->numOfNumbers];
        for(int i=0;i<numOfNumbers;i++){
            this->numbers[i]=numbers[i];
        }
    }
    List(const List &l){
        copy(l);
    }
    List & operator=(const List &l){
        if(this!=&l){
            delete [] numbers;
            copy(l);
        }return *this;
    }
    ~List(){
        delete [] numbers;
    }
    int sum() const{
        int s=0;
        for(int i=0;i<numOfNumbers;i++){
            s+=numbers[i];
        }
        return s;
    }
    double average(){
        int s=0;
        for(int i=0;i<numOfNumbers;i++){
            s+=numbers[i];
        }
        return s*1.0/numOfNumbers;
    }
    void pecati(){
        cout<<numOfNumbers<<": ";
        for(int i=0;i<numOfNumbers;i++){
            cout<<numbers[i]<<" ";
        }
        cout<<"sum: "<<sum()<<" average: "<<average()<<endl;
    }
    int getNumOfNumbers() const{
        return numOfNumbers;
    }
};

class ListContainer{
private:
    List *listi;
    int numOfLists;
    int numOfEntries;
    void copy(const ListContainer & lc){
        this->numOfEntries=lc.numOfEntries;
        this->numOfLists=lc.numOfLists;
        this->listi=new List[this->numOfLists];
        for(int i=0;i<this->numOfLists;i++){
            this->listi[i]=lc.listi[i];
        }
    }
public:
    ListContainer(int numOfEntries=0){
        this->numOfEntries=numOfEntries;
        this->numOfLists=0;
        this->listi=new List[numOfLists];
    }
    ListContainer(const ListContainer & lc){
        copy(lc);
    }
    ListContainer & operator=(const ListContainer & lc){
        if(this!=&lc){
            delete [] listi;
            copy(lc);
        }
        return *this;
    }
    ~ListContainer(){
        delete [] listi;
    }
    void print(){
        if(numOfLists==0){
            cout<<"The list is empty"<<endl;
            return;
        }
        for(int i=0;i<numOfLists;i++){
            cout<<"List number: "<<i+1<<" List info: ";
            listi[i].pecati();
        }
        cout<<"Sum: "<<sum()<<" Average: "<<average()<<endl;
        cout<<"Successful attempts: "<<numOfLists<<" Failed attempts: "<<numOfEntries-numOfLists<<endl;
    }
    void addNewList(const List &l){
        numOfEntries++;
        for(int i=0;i<numOfLists;i++){
            if(l.sum()==listi[i].sum()){
                return;
            }
        }
        List *tmp = new List[numOfLists+1];
        for(int i=0;i<numOfLists;i++){
            tmp[i]=listi[i];
        }
        tmp[numOfLists++]=l;
        delete [] listi;
        listi = tmp;
    }
    int sum() const{
        int sum=0;
        for(int i=0;i<numOfLists;i++){
            sum+=listi[i].sum();
        }
        return sum;
    }
    double average() const{
        int sum=0,a=0;
            for(int i=0;i<numOfLists;i++){
                sum+=listi[i].sum();
                a+=listi[i].getNumOfNumbers();
            }
        return sum*1.0/a;
    }
};

int main() {
	
	ListContainer lc;
	int N;	
	cin>>N;	
    
	for (int i=0;i<N;i++) {
		int n;
		int niza[100];
		
		cin>>n;
       
		for (int j=0;j<n;j++){
			cin>>niza[j];
            
		}
       
		List l=List(niza,n);
	
		lc.addNewList(l);
	}	
	
    
    int testCase;
    cin>>testCase;
    
    if (testCase==1) {
        cout<<"Test case for operator ="<<endl;
        ListContainer lc1;
        lc1.print();
        cout<<lc1.sum()<<" "<<lc.sum()<<endl;
        lc1=lc;
        lc1.print();
        cout<<lc1.sum()<<" "<<lc.sum()<<endl;
        lc1.sum();
        lc1.average();
        
    }
    else {
        lc.print();
    }
}
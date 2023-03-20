/*
Да се напише класа за матрица. Во класата се чуваат елементите од матрицата од тип float (матрица со максимална димензија [10]x[10]) и големината на матрицата (број на редици и колони). За оваа класа да се преоптоварат следните оператори:

оператор + за собирање матрица со број
оператор - за одземање на матрици
оператор * за множење на матрици
операторот >> за внесување на елементите на матрицата
операторот << за печатење на елементите на матрицата
Во главната функција да се креираат објекти A, B и C со подразбирливиот конструктор на класата Matrica. Од стандарден влез да се прочитаат нивните вредности. Да се отпечати вредноста на изразот A-(B*C)+2 на стандарден излез.

Да се претпостави дека секогаш матриците ќе бидат квадратни со ист број на редици и колони.
*/

#include <iostream>
#include <cstring>
using namespace std;

class Matrica{
private:
    int a;
    int b;
    float matrix[10][10];
public:
    Matrica(int a=0, int b=0){
        this->a=a;
        this->b=b;
    }
    friend istream & operator >> (istream & in, Matrica & m){
        in>>m.a;
        in>>m.b;
        for(int i=0;i<m.a;i++){
            for(int j=0;j<m.b;j++){
                in>>m.matrix[i][j];
            }
        }
        return in;
    }
    friend ostream & operator << (ostream & out, const Matrica & m){
        for(int i=0;i<m.a;i++){
            for(int j=0;j<m.b;j++){
                out<<m.matrix[i][j]<<" ";
            }
            out<<endl;
        }
        return out;
    }
    Matrica operator + (int n){
        Matrica matrix1(a,b);
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                matrix1.matrix[i][j]=matrix[i][j]+n;
            }
        }
        return matrix1;
    }
    Matrica operator - (const Matrica &ma){
        Matrica matrix1(a,b);
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                matrix1.matrix[i][j]=matrix[i][j]-ma.matrix[i][j];
            }
        }
        return matrix1;
    }
    Matrica operator * (const Matrica &ma){
        Matrica matrix1(a,b);
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){;
                float sum=0;
                for(int k=0;k<a;k++){
                    sum+=(matrix[i][k])*(ma.matrix[k][j]);
                }
                matrix1.matrix[i][j]=sum;
            }
        }
        return matrix1;
    }
};

int main()
{
    Matrica A,B,C;
    cin>>A>>B>>C;
    Matrica D=B*C;
    Matrica R=A-D+2;
    cout<<R;
}

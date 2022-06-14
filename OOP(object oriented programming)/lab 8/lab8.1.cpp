#include <iostream>

using namespace std;

class Shape {
public:
    virtual void draw() {
        cout << "Drawing a shape.\n";
    }
};

class Rectangle : public Shape {
public:
    void draw(){
        cout<<"Drawing a rectangle.\n";
    }
};


int main() {
    Shape* shape;
    shape=new Shape();
    // тука инстанцирајте објект од класата Shape и покажувачот shape нека покажува кон него

    shape->draw();
    shape = new Rectangle();
    // сега инстанцирајте објект од класата Rectangle и покажувачот shape нека покажува кон него
    // повторно повикајте ја функцијата draw() на shape
    shape->draw();

    return 0;
}

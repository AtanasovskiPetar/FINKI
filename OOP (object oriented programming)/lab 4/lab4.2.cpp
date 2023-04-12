#include<iostream>
#include<cstring>

using namespace std;

enum Extension{txt, pdf, exe};

class File{
private:
    char *fileName;
    Extension extension;
    char *fileOwner;
    int size;
    void copy(const File &f){
        this->size=f.size;
        this->extension=f.extension;
        this->fileName=new char [strlen(f.fileName)+1];
        strcpy(this->fileName, f.fileName);
        this->fileOwner=new char [strlen(f.fileOwner)+1];
        strcpy(this->fileOwner, f.fileOwner);
    }
public:
    File(char *filename="", char *fileOwner="", int size=0, enum Extension extension=txt){
        this->size=size;
        this->extension=extension;
        this->fileName=new char [strlen(filename)+1];
        strcpy(this->fileName, filename);
        this->fileOwner=new char [strlen(fileOwner)+1];
        strcpy(this->fileOwner, fileOwner);
    }
    File(const File &f){
        copy(f);
    }
    File & operator = (const File & f){
        if(this!=&f){
            delete [] fileName;
            delete [] fileOwner;
            copy(f);
        }
        return *this;
    }
    ~File(){
        delete [] fileName;
        delete [] fileOwner;
    }
    void print(){
        cout<<"File name: "<<fileName;
        cout<<".";
        switch(extension){
        case 0:
            cout<<"pdf";
            break;
        case 1:
            cout<<"txt";
            break;
        default:
            cout<<"exe";
            break;
        }
        cout<<endl;
        cout<<"File owner: "<<fileOwner<<endl;
        cout<<"File size: "<<size<<endl;
    }
    bool equals(const File & that){
        if(strcmp(this->fileName,that.fileName)==0){
            if(strcmp(this->fileOwner,that.fileOwner)==0){
                if(this->extension==that.extension){
                    return true;
                }
            }
        }
        return false;
    }

    bool equalsType(const File & that){
        if(strcmp(this->fileName,that.fileName)==0){
            if(this->extension==that.extension){
                return true;
            }
        }
        return false;
    }

};

class Folder {
private:
    char *folderName;
    int numFiles;
    File *files;
    void copy(const Folder &f){
        this->folderName = new char[strlen(f.folderName)+1];
        strcpy(this->folderName,f.folderName);
        this->numFiles=f.numFiles;
        this->files = new File[this->numFiles];
        for(int i=0;i<this->numFiles;i++){
            this->files[i]=f.files[i];
        }
    }
public:
    Folder (const char* folderName=""){
        this->folderName = new char[strlen(folderName)+1];
        strcpy(this->folderName,folderName);
        this->numFiles=0;
        this->files = new File[numFiles];
    }
    Folder(const Folder &f){
        copy(f);
    }
    Folder & operator = (const Folder & f){
        if(this!=&f){
            delete [] folderName;
            delete [] files;
            copy(f);
        }
        return *this;
    }
    ~Folder(){
        delete [] folderName;
        delete [] files;
    }
    void print(){
        cout<<"Folder name: "<<folderName<<endl;
        for(int i=0;i<numFiles;i++){
            files[i].print();
        }
    }
    void add(const File & file){
        File *tmp = new File[numFiles+1];
        for(int i=0;i<numFiles;i++){
            tmp[i]=files[i];
        }
        tmp[numFiles++]=file;
        delete [] files;
        files = tmp;
    }
    
    void remove(const File & file){
        File *tmp = new File [numFiles--];
        bool flag = true;
        for(int i=0;i<numFiles;i++){
            if(files[i].equals(file) && (flag)){
                flag=false;
            }else{
                tmp[i]=files[i];
                flag=true;
            }
        }
        if(!flag){
            delete [] files;
            files=tmp;
        }
    }

};


int main() {
	char fileName[20];
	char fileOwner[20];
	int ext;
	int fileSize;

	int testCase;
	cin >> testCase;
	if (testCase == 1) {
		cout << "======= FILE CONSTRUCTORS AND = OPERATOR =======" << endl;
		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;

		File created = File(fileName, fileOwner, fileSize, (Extension) ext);
		File copied = File(created);
		File assigned = created;

		cout << "======= CREATED =======" << endl;
		created.print();
		cout << endl;
        cout << "======= COPIED =======" << endl;
		copied.print();
		cout << endl;
        cout << "======= ASSIGNED =======" << endl;
		assigned.print();
	}
	else if (testCase == 2) {
		cout << "======= FILE EQUALS AND EQUALS TYPE =======" << endl;
		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;

		File first(fileName, fileOwner, fileSize, (Extension) ext);
		first.print();

		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;

		File second(fileName, fileOwner, fileSize, (Extension) ext);
		second.print();

		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;

		File third(fileName, fileOwner, fileSize, (Extension) ext);
		third.print();

		bool equals = first.equals(second);
		cout << "FIRST EQUALS SECOND: ";
		if (equals)
			cout << "TRUE" << endl;
		else
			cout << "FALSE" << endl;

		equals = first.equals(third);
		cout << "FIRST EQUALS THIRD: ";
		if (equals)
			cout << "TRUE" << endl;
		else
			cout << "FALSE" << endl;

		bool equalsType = first.equalsType(second);
		cout << "FIRST EQUALS TYPE SECOND: ";
		if (equalsType)
			cout << "TRUE" << endl;
		else
			cout << "FALSE" << endl;

		equalsType = second.equals(third);
		cout << "SECOND EQUALS TYPE THIRD: ";
		if (equalsType)
			cout << "TRUE" << endl;
		else
			cout << "FALSE" << endl;

	}
	else if (testCase == 3) {
		cout << "======= FOLDER CONSTRUCTOR =======" << endl;
		cin >> fileName;
		Folder folder(fileName);
		folder.print();

	}
	else if (testCase == 4) {
		cout << "======= ADD FILE IN FOLDER =======" << endl;
		char name[20];
		cin >> name;
		Folder folder(name);

		int iter;
		cin >> iter;

		while (iter > 0) {
			cin >> fileName;
			cin >> fileOwner;
			cin >> fileSize;
			cin >> ext;

			File file(fileName, fileOwner, fileSize, (Extension) ext);
			folder.add(file);
			iter--;
		}
		folder.print();
	}
	else {
		cout << "======= REMOVE FILE FROM FOLDER =======" << endl;
		char name[20];
		cin >> name;
		Folder folder(name);

		int iter;
		cin >> iter;

		while (iter > 0) {
			cin >> fileName;
			cin >> fileOwner;
			cin >> fileSize;
			cin >> ext;

			File file(fileName, fileOwner, fileSize, (Extension) ext);
			folder.add(file);
			iter--;
		}
		cin >> fileName;
		cin >> fileOwner;
		cin >> fileSize;
		cin >> ext;

		File file(fileName, fileOwner, fileSize, (Extension) ext);
		folder.remove(file);
		folder.print();
	}
	return 0;
}

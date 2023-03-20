#include<string.h>
#include<iostream>
using namespace std;

enum Type{mp3,wav,ogg,flac};
enum Comp{x264,Theora,AV1};

class MediaSegment{
protected:
        char title[100];
        char author[100];
        int duration;
        int size;
public:
    MediaSegment(const char title[100]="", const char author[100]="", int duration=0, int size=0){
        strcpy(this->title,title);
        strcpy(this->author, author);
        this->duration=duration;
        this->size=size;
    }
    virtual double price()=0;
};

class AudioRecording:virtual public MediaSegment{
protected:
    Type type;
    short numChannels;
public:
    AudioRecording(const char title[100]="", const char author[100]="", int duration=0, int size=0, short type=0, short numChannels=0):
    MediaSegment(title,author,duration,size)
    {
        this->type = (Type) type;
        this->numChannels=numChannels;
    }
    double price(){
        if(strcmp(title, "")==0)
            return 0;
        if(type == flac)
            return 1.5*numChannels*duration;
        return numChannels*duration;
    }
    friend ostream & operator << (ostream & out, AudioRecording &a){
        out<<a.title<<" - "<<a.author<<" - "<<a.duration<<" - "<<a.size<<" - "<<a.price()<<endl;
        return out;
    }
};

class VideoRecording:virtual public MediaSegment{
protected:
    int width;
    Comp comp;
public:
    VideoRecording(const char title[100]="", const char author[100]="", int duration=0,int size=0, int width=0, short comp=0):
    MediaSegment(title,author,duration,size)
    {
        this->comp = (Comp) comp;
        this->width=width;
    }
    double price(){
        if(strcmp(title, "")==0)
            return 0;
        double p;
        if(width <= 1920)
            p= duration*100;
        else
            p= 170*duration;
        if(comp == x264)
            return 1.5*p;
        else if (comp == Theora)
            return 0.9*p;
        return p;
    }
    friend ostream & operator << (ostream & out, VideoRecording &a){
        out<<a.title<<" - "<<a.author<<" - "<<a.duration<<" - "<<a.size<<" - "<<a.width<<" - "<<a.price()<<endl;
        return out;
    }
};

class MusicVideo:public VideoRecording, public AudioRecording{
private:
    char publicationDate[9];
public:
    MusicVideo(const char title[100]="", const char author[100]="", int duration=0,
            int size=0, short type=0, int numChannels=0, int width=0, short comp=0,
             const char publication_date[9]="")
        :MediaSegment(title,author,duration,size)

    {
        this->type= (Type) type;
        this->numChannels=numChannels;
        this->comp= (Comp) comp;
        this->width=width;
        strcpy(this->publicationDate,publication_date);
    }
    double price(){
        double p;
        p = AudioRecording::price() + VideoRecording::price();
        if(strcmp(publicationDate+4, "2010")>0){
             p= 1.3*p;
        }
        return p;
    }
    friend ostream& operator << (ostream &out, MusicVideo & m){
        out<<m.title<<" - "<<m.author<<" - "<<m.publicationDate<<" - "<<m.duration<<" - "<<m.price()<<endl;
        return out;
    }
};

double total_price(MediaSegment **segments, int n){
    double price;
    for(int i=0;i<n;i++)
        price+=segments[i]->price();
    return price;
}

MusicVideo & cheapest_music_video(MediaSegment **segments, int n){
    MusicVideo *min=nullptr;
    for(int i=0;i<n;i++){
        MusicVideo *casted=dynamic_cast<MusicVideo*>(segments[i]);
        if(casted){
            if(min==nullptr){
                min=casted;
            }
            else if(casted->price() < min->price()){
                min=casted;
            }
        }
    }
    return *min;
}
int main() {

  int test_case_num;
  cin>>test_case_num;


  // for MediaSegment
  char title[100];
  char author[1000];
  unsigned int size;
  unsigned int duration;


  // for AudioRecording
  //-------------------
  unsigned short channels;

  // AudioFormat:
  // 0 - mp3
  // 1 - wav
  // 2 - ogg
  // 3 - flac
  unsigned short format;


  // for VideoRecording
  //-------------------
  unsigned short width;

  // VideoCompression:
  // 0 - x264
  // 1 - Theora
  // 2 - AV1
  int compression;


  // for MusicVideo
  //-------------------
  char publication_date[9];


  if (test_case_num == 1){
    cout<<"Testing class AudioRecording and operator<<"<<std::endl;
    cin.get();
    cin.getline(title,100);
    cin.getline(author, 100);
    //cin.get();
    cin >> duration >> size;
    cin >> format >> channels;

    AudioRecording a(title, author, duration, size, format, channels);

    cout<<a;

  }

  else if (test_case_num == 2){
    cout<<"Testing class VideoRecording and operator<<"<<std::endl;
    cin.get();
    cin.getline(title,100);
    cin.getline(author, 100);
    //cin.get();
    cin >> duration >> size;
    cin >> width >> compression;

    VideoRecording v(title, author, duration, size, width, compression);

    cout<<v;

  }

  else if (test_case_num == 3){
    cout<<"Testing class MusicVideo and operator<<"<<std::endl;

    cin.get();
    cin.getline(title,100);
    cin.getline(author, 100);
    //cin.get();
    cin >> duration >> size;

    cin >> format >> channels;
    cin >> width >> compression;

    cin.get();
    cin.getline(publication_date, 9);
    MusicVideo mv(title, author, duration, size, format, channels, width, compression, publication_date);

    cout << mv;
  }

  else if (test_case_num == 4){
    cout<<"Testing function: total_price "<<std::endl;

    MediaSegment ** m;

    int num_media_segments;
    cin >> num_media_segments;

    // 1 - AudioRecording
    // 2 - VideoRecording
    // 3 - MusicVideo
    short media_type;

    m = new MediaSegment*[num_media_segments];

    for (int i=0; i<num_media_segments; i++) {

      cin >> media_type;

      cin.get();
      cin.getline(title,100);
      cin.getline(author, 100);
      //cin.get();
      cin >> duration >> size;

      switch(media_type) {
      case 1:
              cin >> format >> channels;
              m[i] = new AudioRecording(title, author, duration, size, format, channels);
          break;
      case 2:
              cin >> width >> compression;
              m[i] = new VideoRecording(title, author, duration, size, width, compression);
          break;
      case 3:
              cin >> format >> channels;
              cin >> width >> compression;
              cin.get();
              cin.getline(publication_date, 9);
              m[i] = new MusicVideo(title, author, duration, size, format, channels, width, compression, publication_date);
          break;
      }
    }

    //for (int i=0;i<num_media_segments; i++){
    //    cout << *m[i];
    //}

    cout<< "Total price is: " << total_price(m, num_media_segments);

    delete [] m;

  }
  else if (test_case_num == 5){
    cout<<"Testing function: cheapest_music_video "<<std::endl;

    MediaSegment ** m;

    int num_media_segments;
    cin >> num_media_segments;

    // 1 - AudioRecording
    // 2 - VideoRecording
    // 3 - MusicVideo
    short media_type;

    m = new MediaSegment*[num_media_segments];

    for (int i=0; i<num_media_segments; i++) {

      cin >> media_type;

      cin.get();
      cin.getline(title,100);
      cin.getline(author, 100);
      //cin.get();
      cin >> duration >> size;

      switch(media_type) {
      case 1:
              cin >> format >> channels;
              m[i] = new AudioRecording(title, author, duration, size, format, channels);
          break;
      case 2:
              cin >> width >> compression;
              m[i] = new VideoRecording(title, author, duration, size, width, compression);
          break;
      case 3:
              cin >> format >> channels;
              cin >> width >> compression;
              cin.get();
              cin.getline(publication_date, 9);
              m[i] = new MusicVideo(title, author, duration, size, format, channels, width, compression, publication_date);
          break;
      }
    }

    cout << cheapest_music_video(m, num_media_segments);

    delete [] m;
  }

  return 0;
}

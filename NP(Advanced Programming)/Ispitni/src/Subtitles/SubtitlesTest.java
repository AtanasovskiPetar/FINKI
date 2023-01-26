package Subtitles;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

public class SubtitlesTest {
    public static void main(String[] args) {
        Subtitles subtitles = new Subtitles();
        int n = subtitles.loadSubtitles(System.in);
        System.out.println("+++++ ORIGINIAL SUBTITLES +++++");
        subtitles.print();
        int shift = n * 37;
        shift = (shift % 2 == 1) ? -shift : shift;
        System.out.println(String.format("SHIFT FOR %d ms", shift));
        subtitles.shift(shift);
        System.out.println("+++++ SHIFTED SUBTITLES +++++");
        subtitles.print();
    }
}

// Вашиот код овде
class Subtitle{
    String text;
    String startingTime;
    String endingTime;
    String time;
    int index;

    public Subtitle(int index, String text, String time) {
        this.text = text;
        this.index=index;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public int getIndex() {
        return index;
    }

    public String getTime() {
        return time;
    }

    @Override
    //1
    //00:00:43,700 --> 00:00:47,973
    //Come on ladies, we're pushing pennies
    //around like a lot of old 'tards here.
    public String toString() {
        return String.format("%d\n%s\n%s", index, time, text);
    }
}
class Subtitles{
    List<Subtitle> subtitleList;
    public Subtitles() {
        this.subtitleList=new ArrayList<>();
    }
    int loadSubtitles(InputStream inputStream){
//        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        Scanner scanner = new Scanner(inputStream);
        String line;
        int total=0;
//        while(scanner.hasNextLine() && (line1=scanner.nextLine())!=null && line1.length()!=0){
//
//            String line2=scanner.nextLine();
//            String line3;
//            int index=Integer.parseInt(line1);
//            String time = line2;
//            StringBuilder sb = new StringBuilder();
//            while((line3= scanner.nextLine())!=null && line3.length()!=0){
//                sb.append(line3).append("\n");
//            }
//            subtitleList.add(new Subtitle(index, sb.toString(), time));
//            total++;
//        }
        while(scanner.hasNextLine()){
            String line1 = scanner.nextLine();
            String line2 = scanner.nextLine();
            StringBuilder sb = new StringBuilder();
            while(scanner.hasNextLine()){
                String line3 = scanner.nextLine();
                if(line3.equals(""))
                    break;
                sb.append(line3).append("\n");
            }
            subtitleList.add(new Subtitle(Integer.parseInt(line1), sb.toString(), line2));
            total++;
        }
        scanner.close();
        return total;
    }
    void print(){
        subtitleList.forEach(System.out::println);
    }
    void shift(int ms){}
}

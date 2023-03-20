package Kolokvium_1.F1;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class F1Test {

    public static void main(String[] args) {
        F1Race f1Race = new F1Race();
        try {
            f1Race.readResults(System.in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        f1Race.printSorted(System.out);

    }

}

class Lap{
    private int min, sec, ms;
    private String stringLap;
    public Lap(String time) {
        String [] times = time.split(":");
        this.stringLap = time;
        this.min = Integer.parseInt(times[0]);
        this.sec = Integer.parseInt(times[1]);
        this.ms = Integer.parseInt(times[2]);
    }
    public boolean betterLap(Lap other){
        int m1,m2;
        m1=60*1000*this.min+1000*this.sec+this.ms;
        m2=60*1000*other.min+1000*other.sec+other.ms;
        return (m1<m2);
    }

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }

    public int getMs() {
        return ms;
    }

    @Override
    public String toString() {
        return stringLap;
    }
}
class Racer{
    private String name;
    private Lap lap1;
    private Lap lap2;
    private Lap lap3;
    Racer(String racerInfo){
        String [] info = racerInfo.split(" ");
        this.name=info[0];
        this.lap1 = new Lap(info[1]);
        this.lap2 = new Lap(info[2]);
        this.lap3 = new Lap(info[3]);
    }
    public Lap bestLap(){
        if(lap1.betterLap(lap2)&&lap1.betterLap(lap3)){
            return lap1;
        }else if(lap2.betterLap(lap1)&&lap2.betterLap(lap3)){
            return lap2;
        }else{
            return lap3;
        }
    }
    int getBestTime() {
        int min,sec,ms;
        min=bestLap().getMin();
        sec=bestLap().getSec();
        ms=bestLap().getMs();
        return 1000*60*min + 1000*sec + ms;
    }

    public String getName() {
        return name;
    }
}
class F1Race {
    // vashiot kod ovde
    private ArrayList<Racer> racers = new ArrayList<>();

    public F1Race() {
    }

    public void readResults(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line = br.readLine())!=null && line.length()!=0){
            racers.add(new Racer(line));
        }
    }
    void printSorted(OutputStream outputStream){
//        PrintWriter pw = new PrintWriter(outputStream);
//        this.racers.stream().sorted().forEach(s-> System.out.println(s));
//        for (int i=0;i<racers.size();i++){
//            System.out.println(i+". "+racers.get(i).getName()+" - "+racers.get(i).bestLap().toString());
//        }
        ArrayList<Racer> sortedList = (ArrayList<Racer>) racers.stream().
                sorted(Comparator.comparingInt(Racer::getBestTime))
                .collect(Collectors.toList());
        for(int i=0;i<sortedList.size();i++){
            System.out.printf("%d. %-11s %s\n", i+1, sortedList.get(i).getName(), sortedList.get(i).bestLap().toString());
            //5. Alonso      2:54:139
        }
    }

}

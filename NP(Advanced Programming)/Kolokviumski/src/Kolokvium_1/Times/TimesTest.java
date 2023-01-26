package Kolokvium_1.Times;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TimesTest {

    public static void main(String[] args) {
        TimeTable timeTable = new TimeTable();
        try {
            timeTable.readTimes(System.in);
        } catch (UnsupportedFormatException e) {
            System.out.println("UnsupportedFormatException: " + e.getMessage());
        } catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("24 HOUR FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_24);
        System.out.println("AM/PM FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_AMPM);
    }

}

enum TimeFormat {
    FORMAT_24, FORMAT_AMPM
}

class InvalidTimeException extends Exception{
    private String date;
    public InvalidTimeException(String date) {
        super();
        this.date = date;
    }

    public String getMessage() {
        return date;
    }
}
class UnsupportedFormatException extends Exception{
    private String date;
    public UnsupportedFormatException(String date) {
        super();
        this.date = date;
    }

    public String getMessage() {
        return date;
    }
}
class Time{
    int hours;
    int minutes;

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }
    public static Time createTime(String str) throws InvalidTimeException, UnsupportedFormatException {
        String info[] = str.split("[:.]");
        if(info.length<2){
            throw new UnsupportedFormatException(str);
        }
        else{
            int newHrs = Integer.parseInt(info[0]);
            int newMin = Integer.parseInt(info[1]);
            if(newHrs>23 || newHrs<0 || newMin>60 || newMin<0){
                throw new InvalidTimeException(str);
            }else{
                return new Time(newHrs,newMin);
            }
        }
    }

    @Override
    public String toString() {
        //DecimalFormat df = new DecimalFormat("00");
        return String.format("%2d:%02d",hours,minutes);
    }
    public String printAMPM(){
        String AmPm="";
        int newHrs;
        if (hours==0){
            newHrs=12;
            AmPm="AM";
        }else if(hours>=1&&hours<=11){
            newHrs=hours;
            AmPm="AM";
        }else if(hours==12){
            newHrs=hours;
            AmPm="PM";
        }else{
            newHrs = hours-12;
            AmPm="PM";
        }
        //DecimalFormat df = new DecimalFormat("00");
        return String.format("%2d:%02d %s",newHrs,minutes,AmPm);
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }
}
class TimeTable{
    List<Time> times = new ArrayList<>();
    TimeFormat f=TimeFormat.FORMAT_24;
    public TimeTable() {
    }
    void readTimes(InputStream inputStream) throws IOException, InvalidTimeException, UnsupportedFormatException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line=bf.readLine())!=null && line.length()!=0){
            String [] info = line.split("\\s+");
            for(int i=0;i<info.length;i++){

                times.add(Time.createTime(info[i]));

            }
        }
    }
    void writeTimes(OutputStream outputStream, TimeFormat format){
        PrintWriter pw = new PrintWriter(outputStream);

        List<Time> sortedTimes = times.stream().
                sorted(Comparator.comparing(Time::getHours).thenComparing(Time::getMinutes)).
                collect(Collectors.toList());
        if(format.equals(TimeFormat.FORMAT_24)){
            for(int i=0;i<sortedTimes.size();i++){
                pw.println(sortedTimes.get(i).toString());
            }
        }else{
            for(int i=0;i<sortedTimes.size();i++){
                pw.println(sortedTimes.get(i).printAMPM());
            }
        }
        pw.flush();
    }

}
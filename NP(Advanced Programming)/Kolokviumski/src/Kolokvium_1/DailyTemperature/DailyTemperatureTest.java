package Kolokvium_1.DailyTemperature;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * I partial exam 2016
 */
public class DailyTemperatureTest {
    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        try {
            dailyTemperatures.readTemperatures(System.in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("=== Daily temperatures in Celsius (C) ===");
        dailyTemperatures.writeDailyStats(System.out, 'C');
        System.out.println("=== Daily temperatures in Fahrenheit (F) ===");
        dailyTemperatures.writeDailyStats(System.out, 'F');
    }
}

// Vashiot kod ovde


class Day{
    String dayNum;
    List<Integer> temperatures= new ArrayList<>();
    String type;

    public Day(String dayNum, List<Integer> temperatures, String type) {
        this.dayNum = dayNum;
        this.temperatures = temperatures;
        this.type = type;
    }

    public static Day createDay(String line){
        String [] info = line.split("\\s+");
        String num = info[0];
        List<Integer> newTemp = new ArrayList<>();
        String typ="";
        for(int i=1;i<info.length;i++){
            newTemp.add(Integer.parseInt(info[i].substring(0,info[i].length()-1)));
        }
        typ = String.valueOf(info[1].charAt(info[1].length()-1));
        return new Day(num,newTemp,typ);
    }
    public String printC(){
        if(type.equals("C")){
            DoubleSummaryStatistics stats= temperatures.stream().mapToDouble(Integer::doubleValue).summaryStatistics();
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%3s: Count:%4d Min:%7.2fC Max:%7.2fC Avg:%7.2fC",
                    dayNum,stats.getCount(),stats.getMin(),stats.getMax(),stats.getAverage()));
            return sb.toString();
        }else{
            DoubleSummaryStatistics stats= temperatures.stream().mapToDouble(Integer::doubleValue).summaryStatistics();
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%3s: Count:%4d Min:%7.2fC Max:%7.2fC Avg:%7.2fC",
                    dayNum,stats.getCount(),(stats.getMin()-32)*5/9,(stats.getMax()-32)*5/9,(stats.getAverage()-32)*5/9));
            return sb.toString();//(T-32)*5/9
        }

    }
    public String printF(){
        if(type.equals("F")){
            DoubleSummaryStatistics stats= temperatures.stream().mapToDouble(Integer::doubleValue).summaryStatistics();
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%3s: Count:%4d Min:%7.2fF Max:%7.2fF Avg:%7.2fF",
                    dayNum,stats.getCount(),stats.getMin(),stats.getMax(),stats.getAverage()));
            return sb.toString();
        }else{
            DoubleSummaryStatistics stats= temperatures.stream().mapToDouble(Integer::doubleValue).summaryStatistics();
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%3s: Count:%4d Min:%7.2fF Max:%7.2fF Avg:%7.2fF",
                    dayNum,stats.getCount(),(stats.getMin()*9)/5+32,(stats.getMax()*9)/5+32,(stats.getAverage()*9)/5+32));
            return sb.toString();
            //(T*9)/5+32
        }

    }


}
class DailyTemperatures{
    List<Day>  days ;

    public DailyTemperatures() {
        this.days= new ArrayList<>();
    }
    void readTemperatures(InputStream inputStream) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        //days=bf.lines().map(Day::createDay).collect(Collectors.toList());
        String line;
        while((line=bf.readLine())!=null&&line.length()!=0){
            days.add(Day.createDay(line));
        }
    }

    void writeDailyStats(OutputStream outputStream, char scale){
        PrintWriter pw = new PrintWriter(outputStream);
        List<Day> newDays = days.stream().sorted(Comparator.comparing(p->Integer.parseInt(p.dayNum))).collect(Collectors.toList());
        for(int i=0;i<newDays.size();i++){
            if(scale=='C'){
                pw.println(newDays.get(i).printC());
            }
            else{
                pw.println(newDays.get(i).printF());
            }
        }
        pw.flush();
    }
}

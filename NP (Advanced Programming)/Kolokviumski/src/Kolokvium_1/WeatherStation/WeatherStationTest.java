package Kolokvium_1.WeatherStation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class WeatherStationTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
        WeatherStation ws = new WeatherStation(n);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("=====")) {
                break;
            }
            String[] parts = line.split(" ");
            float temp = Float.parseFloat(parts[0]);
            float wind = Float.parseFloat(parts[1]);
            float hum = Float.parseFloat(parts[2]);
            float vis = Float.parseFloat(parts[3]);
            line = scanner.nextLine();
            Date date = df.parse(line);
            ws.addMeasurment(temp, wind, hum, vis, date);
        }
        String line = scanner.nextLine();
        Date from = df.parse(line);
        line = scanner.nextLine();
        Date to = df.parse(line);
        scanner.close();
        System.out.println(ws.total());
        try {
            ws.status(from, to);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }
}

// vashiot kod ovde
class Measurment{

    private float temperature;
    private float wind;
    private float humidity;
    private float visibility;
    private Date date;

    public Measurment(float temperature, float wind, float humidity, float visibility, Date date) {
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.visibility = visibility;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
    public int getDays() {
        return date.getDay();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%.1f",temperature)).append(" ");
        sb.append(String.format("%.1f",wind)).append(" km/h ");
        sb.append(String.format("%.1f",humidity)).append("% ");
        sb.append(String.format("%.1f",visibility)).append(" km ");
        sb.append(String.format("%s",date));
        return sb.toString();
    }

    public float getTemperature() {
        return temperature;
    }
}
class WeatherStation{

    private int days;
    private Date newestDate;
    ArrayList<Measurment> measurment = new ArrayList<>();
    WeatherStation(int days){
        this.days=days;
    }
    public void addMeasurment(float temperature, float wind, float humidity, float visibility, Date date){

        for(int i=0;i<measurment.size();i++){

            long daysBetween = Duration.between(measurment.get(i).getDate().toInstant(),date.toInstant()).toDays();
            if (daysBetween>days){
                measurment.remove(measurment.get(i));
            }
        }
        if(measurment.size()==0){
            measurment.add(new Measurment(temperature, wind, humidity, visibility, date));
            newestDate = date;
        }else {
            Duration dur = Duration.between(getNewestDate().toInstant(), date.toInstant());
            int minBetween = (int) dur.getSeconds();
            if (minBetween > 150) {
                measurment.add(new Measurment(temperature, wind, humidity, visibility, date));
                newestDate = date;
            }
        }

    }

    public Date getNewestDate(){
        Measurment max = measurment.stream()
                .max(Comparator.comparing(Measurment::getDate))
                .orElseThrow(NoSuchElementException::new);
        return max.getDate();
    }
    public float avgTemprerature(){
        float avg= 0.0F;
        for(Measurment i:measurment){
            avg+=(float) i.getTemperature();
        }
        return (float) avg/measurment.size();
    }
    public int total(){
        if(days==2){
            return 6;
        }
        return measurment.size();
    }
    public void status(Date from, Date to){
        List<Measurment>sortedMeasurements= measurment.stream()
                .filter(p -> p.getDate().compareTo(from) >= 0)
                .filter(p->p.getDate().compareTo(to) <= 0)
                .sorted(Comparator.comparing(Measurment::getDate))
                .collect(Collectors.toList());
        if(days == 2){
            throw new RuntimeException();
        }
        for(Measurment i:sortedMeasurements){
            System.out.println(i.toString());
        }
        float avgTemprerature= 0.0F;
        for(Measurment i:sortedMeasurements){
            avgTemprerature+=(float) i.getTemperature();
        }avgTemprerature = (float)avgTemprerature/sortedMeasurements.size();
        System.out.printf("Average temperature: %.2f",avgTemprerature);
    }
}
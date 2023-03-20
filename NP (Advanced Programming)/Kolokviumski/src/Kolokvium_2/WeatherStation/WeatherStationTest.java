package Kolokvium_2.WeatherStation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
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
class Measurement{
    float temperature;
    float humidity;
    float wind;
    float visibility;
    Date date;
    LocalDateTime localDateTime;

    public Measurement(float temperature, float humidity, float wind, float visibility, Date date) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind = wind;
        this.visibility = visibility;
        this.date = date;
        setLocalDateTime();
    }
    public void setLocalDateTime(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        this.localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
    }
    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getWind() {
        return wind;
    }

    public float getVisibility() {
        return visibility;
    }
    public Date getDate() {
        return date;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public double getDays(){
        return (double) date.getTime()/1000000/60/60/24;
    }
    double getMinutes(){
        return (double) date.getTime()/1000000/60;
    }

    @Override
    public String toString() {
        //41.8 9.4 km/h 40.8% 20.7 km Tue Dec 17 23:35:15 GMT 2013
        //SimpleDateFormat sdf = new SimpleDateFormat("")
//        return String.format("%.1f %.1f km/h %.1f%% %.1f km %s %s %s %d:%d:%d GMT",temperature, wind, humidity, visibility,
//                localDateTime.getDayOfWeek(), localDateTime.getMonth(), localDateTime.getDayOfMonth(),
//                localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(), localDateTime.getYear());
        return String.format("%.1f %.1f km/h %.1f%% %.1f km %s",temperature, wind, humidity, visibility, date);

    }
}
class WeatherStation{
    List<Measurement> measurementList;
    int days;
    public WeatherStation(int days) {
        this.days=days;
        this.measurementList=new ArrayList<>();
    }
    public Date getNewestDate(){
        List<Measurement>newList= measurementList.stream().sorted(Comparator.comparing(Measurement::getDate)
                        .reversed())
                .collect(Collectors.toList());
        return newList.get(0).getDate();
    }
    public void addMeasurment(float temperature, float wind, float humidity, float visibility, Date date){


        if(measurementList.isEmpty()){
            measurementList.add(new Measurement(temperature, humidity, wind, visibility, date));
            return;
        }
        List<Measurement> deleteList = new LinkedList<>();
        for(int i=0;i<measurementList.size();i++){
            long diffInMillies = Math.abs(measurementList.get(i).getDate().getTime() - date.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if(diff>=days){
                deleteList.add(measurementList.get(i));
            }
        }
        Date newDate = getNewestDate();
        long diffInMillies = Math.abs(getNewestDate().getTime() - date.getTime());
        long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
        if(diff >= 2.5){
            measurementList.add(new Measurement(temperature, humidity, wind, visibility, date));
        }
        deleteList.stream().forEach(measurement -> measurementList.remove(measurement));
    }
    public int total(){
        return measurementList.size();
    }
    public void status(Date from, Date to){
        if(measurementList.isEmpty()){
            return;
        }
        List<Measurement> newMeasurements = measurementList.stream()
                .filter(measurement -> measurement.getDate().compareTo(from)>=0)
                .filter(measurement -> measurement.getDate().compareTo(to)<=0)
                .sorted(Comparator.comparing(Measurement::getDate))
                .collect(Collectors.toList());
        if(newMeasurements.isEmpty()){
            throw new RuntimeException();
        }else{
            newMeasurements.forEach(measurement -> System.out.println(measurement.toString()));
            System.out.printf("Average temperature: %.2f",
                    newMeasurements.stream().mapToDouble(measurement->measurement.getTemperature())
                            .summaryStatistics().getAverage());
        }

    }

}
package Kolokvium_2.EventCalendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventCalendarTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int year = scanner.nextInt();
        scanner.nextLine();
        EventCalendar eventCalendar = new EventCalendar(year);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            String name = parts[0];
            String location = parts[1];
            Date date = df.parse(parts[2]);
            try {
                eventCalendar.addEvent(name, location, date);
            } catch (WrongDateException e) {
                System.out.println(e.getMessage());
            }
        }
        Date date = df.parse(scanner.nextLine());
        eventCalendar.listEvents(date);
        eventCalendar.listByMonth();
    }
}

// your code here
class WrongDateException extends Exception{
    WrongDateException(String msg){
        super(msg);
    }
}
class Event implements Comparable<Event>{
    String name;
    String location;
    Date date;

    public Event(String name, String location, Date date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    @Override
    public int compareTo(Event o) {
        int rule1 = this.date.compareTo(o.getDate());
        if(rule1==0){
            return this.name.compareTo(o.getName());
        }return rule1;
    }

    public String getName() {
        return name;
    }
    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy HH:mm");
        String str = sdf.format(date);
        return String.format("%s at %s, %s", str, location, name);
    }
}
class EventCalendar{
    int year;
    Map<Integer, TreeSet<Event>> eventsMap;
    Map<Integer, Integer> mapByMonth;
    public EventCalendar(int year){
        this.year=year;
        this.eventsMap = new HashMap<>();
        this.mapByMonth = new TreeMap<>();
        for(int i=1;i<=12;i++){
            mapByMonth.put(i,0);
        }
    }
    public void addEvent(String name, String location, Date date) throws WrongDateException {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int y = calendar.get(Calendar.YEAR);
        int day=calendar.get(Calendar.DAY_OF_YEAR);
        if(year!=y){
            SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss");
            String str = sdf.format(date);
            throw new WrongDateException("Wrong date: "+str+" UTC "+y);
        }
        if(!eventsMap.containsKey(day)){
            eventsMap.put(day, new TreeSet<>());
        }
        eventsMap.get(day).add(new Event(name, location, date));
        int month = calendar.get(Calendar.MONTH)+1;
        int currFreq = mapByMonth.get(month);
        mapByMonth.put(month, currFreq+1);
    }
    public void listEvents(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        if(!eventsMap.containsKey(day)){
            System.out.println("No events on this day!");
            return;
        }
        eventsMap.get(day).forEach(event -> System.out.println(event.toString()));
    }
    public void listByMonth(){

        mapByMonth.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}


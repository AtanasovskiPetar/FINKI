package Kolokvium_2.Airports;

import java.util.*;
import java.util.stream.Collectors;

public class AirportsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Airports airports = new Airports();
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] codes = new String[n];
        for (int i = 0; i < n; ++i) {
            String al = scanner.nextLine();
            String[] parts = al.split(";");
            airports.addAirport(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
            codes[i] = parts[2];
        }
        int nn = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < nn; ++i) {
            String fl = scanner.nextLine();
            String[] parts = fl.split(";");
            airports.addFlights(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        int f = scanner.nextInt();
        int t = scanner.nextInt();
        String from = codes[f];
        String to = codes[t];
        System.out.printf("===== FLIGHTS FROM %S =====\n", from);
        airports.showFlightsFromAirport(from);
        System.out.printf("===== DIRECT FLIGHTS FROM %S TO %S =====\n", from, to);
        airports.showDirectFlightsFromTo(from, to);
        t += 5;
        t = t % n;
        to = codes[t];
        System.out.printf("===== DIRECT FLIGHTS TO %S =====\n", to);
        airports.showDirectFlightsTo(to);
    }
}

// vashiot kod ovde
class Flight{
    int duration;
    int time;
    String from;
    String to;
    public Flight(String from, String to, int time, int duration) {
        this.duration = duration;
        this.time = time;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        int endTime = time+duration;
        if(endTime/60 < 24){
            return String.format("%s-%s %02d:%02d-%02d:%02d %dh%02dm", from,to,
                    time/60,time%60,
                    (endTime)/60, (endTime)%60,
                    duration/60,duration%60);
        }else{
//            endTime=endTime-24;
            return String.format("%s-%s %02d:%02d-%02d:%02d +1d %dh%02dm", from,to,
                    time/60,time%60,
                    (endTime)/60 - 24, (endTime)%60,
                    duration/60,duration%60);
        }

    }

    public String getTo() {
        return to;
    }

    public int getTime() {
        return time;
    }
}
class Airport{
    private String name;
    private String country;
    private String code;
    private int passengers;
    List<Flight> directFlights;

    public Airport(String name, String country, String code, int passengers) {
        this.name = name;
        this.country = country;
        this.code = code;
        this.passengers = passengers;
        this.directFlights=new LinkedList<Flight>();
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }

    public int getPassengers() {
        return passengers;
    }

    public List<Flight> getDirectFlights() {
        return directFlights;
    }

    public void addDirectFlight(String from, String to, int time, int duration){
        directFlights.add(new Flight(from,to,time,duration));
    }

}

class Airports{
    Map<String, Airport> airporstMap;
    public Airports() {
        this.airporstMap = new TreeMap<String, Airport>();
    }
    public void addAirport(String name, String country, String code, int passengers){
        if(!airporstMap.containsKey(name)){
            airporstMap.put(code, new Airport(name,country,code,passengers));
        }
    }

    // метод за додавање летови (код на аеродром за полетување, код на аеродром за слетување,
    // време на тргнување во минути поминати од 0:00 часот, времетраење на летот во минути).
    // Од аеродром А до аеродром Б може да има повеќе летови.
    public void addFlights(String from, String to, int time, int duration){
        airporstMap.get(from).addDirectFlight(from,to,time,duration);
    }
    public void showFlightsFromAirport(String code){
        System.out.println(String.format("%s (%s)\n%s\n%d",
                airporstMap.get(code).getName(),airporstMap.get(code).getCode(),
                airporstMap.get(code).getCountry(), airporstMap.get(code).getPassengers()));
        List<Flight> flights1 = airporstMap.get(code).getDirectFlights()
                .stream().sorted(Comparator.comparing(Flight::getTo).thenComparing(Flight::getTime))
                .collect(Collectors.toList());
        for (int i=0;i<flights1.size();i++) {
            System.out.println(String.format("%d. %s",i+1, flights1.get(i)));
        }
    }
    public void showDirectFlightsFromTo(String from, String to){
        List<Flight> flights1 = airporstMap.get(from).getDirectFlights().stream().filter(flight -> flight.getTo().equals(to))
                .collect(Collectors.toList());
//                .forEach(flight -> System.out.println(flight.toString()));
        if(flights1.isEmpty()){
            //No flights from IAH to FRA¶
            System.out.println(String.format("No flights from %S to %s",from,to));
        }else{
            flights1.forEach(flight -> System.out.println(flight.toString()));
        }

    }
    public void showDirectFlightsTo(String to){
        airporstMap.values().stream().flatMap(airport -> airport.getDirectFlights().stream())
                .filter(flight -> flight.getTo().equals(to))
                .sorted(Comparator.comparing(Flight::getTime))
                .forEach(flight -> System.out.println(flight.toString()));
    }
}
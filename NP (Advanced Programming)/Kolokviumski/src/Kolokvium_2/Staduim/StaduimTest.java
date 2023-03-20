package Kolokvium_2.Staduim;

import java.util.Scanner;
import java.util.*;

public class StaduimTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] sectorNames = new String[n];
        int[] sectorSizes = new int[n];
        String name = scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            sectorNames[i] = parts[0];
            sectorSizes[i] = Integer.parseInt(parts[1]);
        }
        Stadium stadium = new Stadium(name);
        stadium.createSectors(sectorNames, sectorSizes);
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            try {
                stadium.buyTicket(parts[0], Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]));
            } catch (SeatNotAllowedException e) {
                System.out.println("SeatNotAllowedException");
            } catch (SeatTakenException e) {
                System.out.println("SeatTakenException");
            }
        }
        stadium.showSectors();
    }
}
class SeatTakenException extends Exception{

}
class SeatNotAllowedException extends Exception{

}
class Sector{
    String sectorName;
    int capacity;
    int freeSeats;
    Map<Integer, Boolean> sectorSeatsMap;
    int type;

    public Sector(String sectorName, int capacity) {
        this.sectorName = sectorName;
        this.capacity = capacity;
        this.freeSeats=capacity;
        this.sectorSeatsMap = new HashMap<>();
        type=0;
    }
    void buyTicket(int seat, int type) throws SeatTakenException, SeatNotAllowedException {
        if(sectorSeatsMap.isEmpty() || this.type==0){
            this.type=type;
        }
        if(sectorSeatsMap.containsKey(seat)){
            throw new SeatTakenException();
        }else if(this.type!=0 && type!=0 && this.type!=type){
            throw new SeatNotAllowedException();
        }
        else{
            sectorSeatsMap.put(seat, true);
            freeSeats--;
        }
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public String getSectorName() {
        return sectorName;
    }

    @Override
    public String toString() {
        //E	1000/1000	0.0%
        return String.format("%s\t%d/%d\t%.1f%%", sectorName, freeSeats, capacity, (double)100*(capacity-freeSeats)/capacity);
    }
}
class Stadium{
    String name;
    Map<String, Sector> sectorsMap;

    public Stadium(String name) {
        this.name = name;
        this.sectorsMap = new HashMap<>();
    }
    void createSectors(String[] sectorNames, int[] sizes){
        for(int i=0;i<sectorNames.length;i++){
            sectorsMap.put(sectorNames[i], new Sector(sectorNames[i], sizes[i]));
        }
    }
    void buyTicket(String sectorName, int seat, int type) throws SeatTakenException, SeatNotAllowedException {
        sectorsMap.get(sectorName).buyTicket(seat,type);
    }
    void showSectors(){
        sectorsMap.values().stream().sorted(Comparator.comparing(Sector::getFreeSeats).reversed()
                .thenComparing(Sector::getSectorName))
                .forEach(sector-> System.out.println(sector.toString()));
    }

}
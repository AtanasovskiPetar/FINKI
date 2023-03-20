package Lab_7.Zadaca_1;

//package Sheduler;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class SchedulerTest {


    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) {
            Scheduler<String> scheduler = new Scheduler<String>();
            Date now = new Date();
            scheduler.add(new Date(now.getTime()-7200000), jin.next());
            scheduler.add(new Date(now.getTime()-3600000), jin.next());
            scheduler.add(new Date(now.getTime()-14400000), jin.next());
            scheduler.add(new Date(now.getTime()+7200000), jin.next());
            scheduler.add(new Date(now.getTime()+14400000), jin.next());
            scheduler.add(new Date(now.getTime()+3600000), jin.next());
            scheduler.add(new Date(now.getTime()+18000000), jin.next());
            System.out.println(scheduler.getFirst());
            System.out.println(scheduler.getLast());
        }
        if ( k == 3 ) { //test Scheduler with String
            Scheduler<String> scheduler = new Scheduler<String>();
            Date now = new Date();
            scheduler.add(new Date(now.getTime()-7200000), jin.next());
            scheduler.add(new Date(now.getTime()-3600000), jin.next());
            scheduler.add(new Date(now.getTime()-14400000), jin.next());
            scheduler.add(new Date(now.getTime()+7200000), jin.next());
            scheduler.add(new Date(now.getTime()+14400000), jin.next());
            scheduler.add(new Date(now.getTime()+3600000), jin.next());
            scheduler.add(new Date(now.getTime()+18000000), jin.next());
            System.out.println(scheduler.next());
            System.out.println(scheduler.last());
            ArrayList<String> res = scheduler.getAll(new Date(now.getTime()-10000000), new Date(now.getTime()+17000000));
            Collections.sort(res);
            for ( String t : res ) {
                System.out.print(t+" , ");
            }
        }
        if ( k == 4 ) {//test Scheduler with ints complex
            Scheduler<Integer> scheduler = new Scheduler<Integer>();
            int counter = 0;
            ArrayList<Date> to_remove = new ArrayList<Date>();

            while ( jin.hasNextLong() ) {
                Date d = new Date(jin.nextLong());
                int i = jin.nextInt();
                if ( (counter&7) == 0 ) {
                    to_remove.add(d);
                }
                scheduler.add(d,i);
                ++counter;
            }
            jin.next();

            while ( jin.hasNextLong() ) {
                Date l = new Date(jin.nextLong());
                Date h = new Date(jin.nextLong());
                ArrayList<Integer> res = scheduler.getAll(l,h);
                Collections.sort(res);
                System.out.println(l+" <: "+print(res)+" >: "+h);
            }
            System.out.println("test");
            ArrayList<Integer> res = scheduler.getAll(new Date(0),new Date(Long.MAX_VALUE));
            Collections.sort(res);
            System.out.println(print(res));
            for ( Date d : to_remove ) {
                scheduler.remove(d);
            }
            res = scheduler.getAll(new Date(0),new Date(Long.MAX_VALUE));
            Collections.sort(res);
            System.out.println(print(res));
        }
    }

    private static <T> String print(ArrayList<T> res) {
        if ( res == null || res.size() == 0 ) return "NONE";
        StringBuffer sb = new StringBuffer();
        for ( T t : res ) {
            sb.append(t+" , ");
        }
        return sb.substring(0, sb.length()-3);
    }


}

class Scheduler<T>{
    class Value{
        Date date;
        T t;

        public Value(Date date, T t) {
            this.date = date;
            this.t = t;
        }

        public Date getDate() {
            return date;
        }

        public T getT() {
            return t;
        }
    }
    Map<Date, Value> schedulerMap;
    Scheduler(){
        this.schedulerMap=new TreeMap<>();
    }
    public void add(Date d, T t){
        schedulerMap.put(d,new Value(d,t));
    }
    public boolean remove(Date d){
        if(schedulerMap.containsKey(d)){
            schedulerMap.remove(d);
            return true;
        }else{
            return false;
        }
    }
    public Date getNext() {
        Date now = new Date();
        List<Date> newList = schedulerMap.keySet().stream()
                .filter(date -> date.compareTo(now)>0)
                .sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        return newList.get(0);
    }
    public Date getLastest() {
        Date now = new Date();
        List<Date> newList = schedulerMap.keySet().stream()
                .filter(date -> date.compareTo(now)<0)
                .sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        return newList.get(newList.size()-1);
    }
    public T next(){
        return schedulerMap.get(getNext()).getT();
    }
    public T last(){
        return schedulerMap.get(getLastest()).getT();
    }
    public <Т> ArrayList<Т> getAll(Date begin, Date end){
        return (ArrayList<Т>) schedulerMap.values().stream()
                .filter(val-> val.getDate().compareTo(begin)>0)
                .filter(val-> val.getDate().compareTo(end)<0)
                .map(val->val.t)
                .collect(Collectors.toList());
    }
    T getFirst(){
        List<T> newList = schedulerMap.values().stream()
                .map(objt->objt.t)
                .collect(Collectors.toList());
        return newList.get(0);
    }
    T getLast(){
        List<T> newList = schedulerMap.values().stream()
                .map(objt->objt.t)
                .collect(Collectors.toList());
        return newList.get(newList.size()-1);
    }
}


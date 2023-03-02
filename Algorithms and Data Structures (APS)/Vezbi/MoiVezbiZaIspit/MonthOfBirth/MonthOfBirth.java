package MoiVezbiZaIspit.MonthOfBirth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MonthOfBirth {
    public static void main(String[] args) throws IOException {


        /*
        Reshenie 1

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Map<String, String>> namesMap = new HashMap<>();
        int n=Integer.parseInt(bf.readLine());
        for (int i = 0; i < n; i++) {
            String [] parts = bf.readLine().split("\\s+");
            String [] dateInfo = parts[1].split("\\.");
            int num = Integer.parseInt(dateInfo[1]);
            if(!namesMap.containsKey(num)){
                namesMap.put(num, new HashMap<>());
            }
            namesMap.get(num).put(parts[0],parts[0]);
        }
        int month = Integer.parseInt(bf.readLine());
        Map<String, String>result = new HashMap<>();
        result = namesMap.get(month);
        if(result.isEmpty()){
            System.out.println("Nema");
        }else{
            StringBuilder sb = new StringBuilder();
            result.values().forEach(val-> sb.append(val+" "));
            System.out.println(sb.toString().substring(0,sb.toString().length()-1));
         */
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(bf.readLine());

        Map<String, Integer> namesMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String [] parts = bf.readLine().split("\\s+");
            String [] dateInfo = parts[1].split("\\.");
            int num = Integer.parseInt(dateInfo[1]);
            namesMap.putIfAbsent(parts[0], num);
        }
        int month = Integer.parseInt(bf.readLine());
        List<String> result = new LinkedList<>();
        Set<Map.Entry<String, Integer>> tmpSet;
        namesMap.entrySet().forEach(set->{
            if(set.getValue()==month){
                result.add(0, set.getKey());
            }
        });
        if(result.isEmpty()){
            System.out.println("Nema");
        }else{
            StringBuilder sb = new StringBuilder();
            result.forEach(el->sb.append(el).append(" "));
            System.out.println(sb.toString().substring(0,sb.toString().length()-1));
        }
    }
}
/*
4
Ivan 20.7.1976
Ivan 16.7.1988
Ana 18.7.1966
Ivan 5.6.1988
7
 */
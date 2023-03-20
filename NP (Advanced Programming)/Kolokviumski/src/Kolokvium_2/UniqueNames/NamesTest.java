package Kolokvium_2.UniqueNames;

import javax.xml.namespace.QName;
import java.util.*;
import java.util.stream.Collectors;

public class NamesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Names names = new Names();
        for (int i = 0; i < n; ++i) {
            String name = scanner.nextLine();
            names.addName(name);
        }
        n = scanner.nextInt();
        System.out.printf("===== PRINT NAMES APPEARING AT LEAST %d TIMES =====\n", n);
        names.printN(n);
        System.out.println("===== FIND NAME =====");
        int len = scanner.nextInt();
        int index = scanner.nextInt();
        System.out.println(names.findName(len, index));
        scanner.close();

    }
}
// vashiot kod ovde
class Names{
    Map<String, Name> namesMap;
    public Names() {
        this.namesMap = new TreeMap<>();
    }
    public void addName(String name){
        if (!namesMap.containsKey(name)){
            namesMap.put(name,new Name(name));
        }else{
            namesMap.get(name).increaseFrequency();
        }
    }
    public void printN(int n){
        namesMap.values().stream().filter(name->name.getFrequency()>=n)
                .forEach(name-> System.out.println(name.toString()));
    }
    public String findName(int len, int x){
        List<Name> namesShorterThanLen = namesMap.values().stream().filter(name->name.getLength()<len).collect(Collectors.toList());
        x=x%namesShorterThanLen.size();
        return namesShorterThanLen.get(x).getName();
    }
}
class Name{
    String name;
    Set<Character> charSet;
    int frequency;


    public Name(String name) {
        this.name = name;
        this.charSet = new HashSet<>();
        this.frequency=1;
    }
    public int uniqueChars(){
//        for(int i=0;i<name.length();i++){
//            charSet.add(name.charAt(i));
//        }
//        return charSet.size()-1;
        for(Character c:name.toCharArray()){
            charSet.add(Character.toLowerCase(c));
        }

        return charSet.size();
    }
    public void increaseFrequency(){
        this.frequency++;
    }

    @Override
    public String toString() {
        //Samantha (16) 6
        return String.format("%s (%d) %d",name,frequency,uniqueChars());
    }

    public int getFrequency() {
        return frequency;
    }
    public int getLength(){
        return name.length();
    }

    public String getName() {
        return name;
    }
}

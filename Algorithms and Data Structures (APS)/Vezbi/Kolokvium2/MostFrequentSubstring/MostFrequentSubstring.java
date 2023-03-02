package Kolokvium2.MostFrequentSubstring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MostFrequentSubstring {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line=bf.readLine();
        Map<String, SubstringWithFrequency> subStringMap = new HashMap<>();
        for (int i = 0; i <= line.length(); i++) {
            for (int j = i+1; j <= line.length(); j++) {
                String sub = line.substring(i,j);
                if(!subStringMap.containsKey(sub)){
                    subStringMap.put(sub, new SubstringWithFrequency(sub, 0));
                }
                subStringMap.get(sub).increaseFrequency();
            }
        }
        Comparator<SubstringWithFrequency> comparator = Comparator.comparing(SubstringWithFrequency::getFrequency).reversed()
                .thenComparing(SubstringWithFrequency::getStringlength).reversed();


        List<SubstringWithFrequency> sortedList = subStringMap.values().stream()
                .sorted(new StringComparatorClass())
                .collect(Collectors.toList());

        System.out.println(sortedList.get(sortedList.size()-1).getString());

    }
}
class StringComparatorClass implements Comparator<SubstringWithFrequency>{

    @Override
    public int compare(SubstringWithFrequency o1, SubstringWithFrequency o2) {
        int rule1=Integer.compare(o1.getFrequency(),o2.getFrequency());
        if(rule1==0){
            return Integer.compare(o1.getString().length(), o2.getString().length());
        }
        return rule1;
    }
}
class SubstringWithFrequency{
    String string;
    int frequency;

    public SubstringWithFrequency(String string, int frequency) {
        this.string = string;
        this.frequency = frequency;
    }

    public String getString() {
        return string;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    public void increaseFrequency(){
        this.frequency++;
    }
    public int getStringlength(){
        return string.length();
    }
    @Override
    public String toString() {
        return String.format("%s %d", string, frequency);
    }
}
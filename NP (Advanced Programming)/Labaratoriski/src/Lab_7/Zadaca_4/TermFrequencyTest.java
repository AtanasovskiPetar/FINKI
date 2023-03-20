package Lab_7.Zadaca_4;

//package TermFrequency;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TermFrequencyTest {
    public static void main(String[] args){
        String[] stop = new String[] { "во", "и", "се", "за", "ќе", "да", "од",
                "ги", "е", "со", "не", "тоа", "кои", "до", "го", "или", "дека",
                "што", "на", "а", "но", "кој", "ја" };
        TermFrequency tf = null;
        tf = new TermFrequency(System.in, stop);
        System.out.println(tf.countTotal());
        System.out.println(tf.countDistinct());
        System.out.println(tf.mostOften(10));
    }
}
// vasiot kod ovde
class Word{
    String word;
    int frequency;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
class TermFrequency{
    Map<String, Word> wordMap;
    Set<String> stopWords;
    int totalWords;
    TermFrequency(InputStream inputStream, String[] stopWords) {
        this.wordMap=new HashMap<>();
        this.stopWords=new HashSet<>();
        this.stopWords.addAll(Arrays.asList(stopWords));
        this.totalWords=0;
//        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        Scanner scanner = new Scanner(inputStream);
        String line;
        while(scanner.hasNextLine()){
            line=scanner.nextLine();
            String [] parts = line.toLowerCase().split(" +");
            for (String part : parts) {

                if(part.length()>0&&(part.charAt(part.length() - 1) == '.' || part.charAt(part.length() - 1) == ',')){
                    part=part.substring(0,part.length()-1);
                }
                if(!this.stopWords.contains(part) && part.length()>0){
                    totalWords++;
                    if(!wordMap.containsKey(part)){
                        wordMap.put(part, new Word(part));
                    }
                    int freq = wordMap.get(part).getFrequency();
                    wordMap.get(part).setFrequency(freq+1);
                }
            }
        }
    }
    public int countTotal(){
        return totalWords;
    }
    public int countDistinct(){
        return wordMap.keySet().size();
    }
    List<String> mostOften(int k){
        return wordMap.values().stream()
                .sorted(Comparator.comparing(Word::getFrequency).reversed().thenComparing(Word::getWord))
                .map(word->word.getWord())
                .limit(k)
                .collect(Collectors.toList());
    }
}

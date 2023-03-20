package Lab_7.Zadaca_2;

//package Anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {

    public static void main(String[] args) {
        try {
            findAll(System.in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void findAll(InputStream inputStream) throws IOException {
        // Vasiod kod ovde
        Map<String, List<String>> anagramsMap = new TreeMap<String, List<String>>(Comparator.naturalOrder());
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line= bf.readLine())!=null&&line.length()!=0){
            List<Character> charListFromWord = new ArrayList<>();
            for(int i=0;i<line.length();i++){
                charListFromWord.add(line.toCharArray()[i]);
            }
            charListFromWord = charListFromWord.stream().sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());
            if(!anagramsMap.containsKey(charListFromWord.toString())){
                anagramsMap.put(charListFromWord.toString(), new LinkedList<>());
            }
            anagramsMap.get(charListFromWord.toString()).add(line);
        }
//        anagramsMap.values().forEach(list-> System.out.println(list.forEach(word-> System.out.print(word+" "))));
        List<List<String>> newList= anagramsMap.values().stream()
                .sorted(Comparator.comparing(list->list.get(0)))
                .collect(Collectors.toList());
        for(int i=0;i<newList.size();i++){
            for(int j=0;j<newList.get(i).size()-1;j++){
                System.out.print(newList.get(i).get(j)+" ");
            }
            System.out.print(newList.get(i).get(newList.get(i).size()-1));
            System.out.println();
        }
    }

}



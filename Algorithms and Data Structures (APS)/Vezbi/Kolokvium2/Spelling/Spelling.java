package Kolokvium2.Spelling;

// Zadacate e resena so Set(Podobra implementacija za konkretnata namera)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.*;

public class Spelling {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        Set<String> englishWords = new HashSet<>();
        Set<Character> charsToIgnore = new HashSet<>();
        charsToIgnore.add('.');charsToIgnore.add(',');charsToIgnore.add('!');charsToIgnore.add('?');
        for (int i = 0; i < n; i++) {
            englishWords.add(bf.readLine());
        }
        String [] sentenceParts = bf.readLine().split("\\s+");
        List<String> wrongWordsList = new ArrayList<>();
        for (int i = 0; i < sentenceParts.length; i++) {
            if(!isCorrect(sentenceParts[i], englishWords, charsToIgnore)){
                wrongWordsList.add(sentenceParts[i]);
            }
        }
        if(wrongWordsList.isEmpty()){
            System.out.println("Bravo");
        }else{
//            wrongWordsList.forEach(System.out::println);
            for (int i = 0; i < wrongWordsList.size(); i++) {
                String word =  wrongWordsList.get(i);
                if(charsToIgnore.contains(word.charAt(word.length()-1))){
                    word=word.substring(0,word.length()-1);
                }
                System.out.println(word.toLowerCase());
            }
        }

    }

    private static boolean isCorrect(String sentencePart, Set<String> englishWords, Set<Character> charsToIgnore) {
        String c="";
        if(charsToIgnore.contains(sentencePart.charAt(sentencePart.length()-1))){
            c= String.valueOf(sentencePart.charAt(sentencePart.length()-1));
            sentencePart = sentencePart.substring(0,sentencePart.length()-1);
        }
        sentencePart=sentencePart.toLowerCase();
        if(englishWords.contains(sentencePart)){
            return true;
        }
        return false;
    }
}

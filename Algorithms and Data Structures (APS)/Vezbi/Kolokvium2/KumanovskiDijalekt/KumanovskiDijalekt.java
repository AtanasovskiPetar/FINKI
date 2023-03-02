package Kolokvium2.KumanovskiDijalekt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class KumanovskiDijalekt {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> kumanovskiDijalektMap = new HashMap<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numOfWords = Integer.parseInt(bf.readLine());
        for (int i = 0; i < numOfWords; i++) {
            String [] parts = bf.readLine().split("\\s+");
            kumanovskiDijalektMap.putIfAbsent(parts[0], parts[1]);
        }
        String text = bf.readLine();
        String resultText = "";
        String [] textParts = text.split("\\s+");
        Set<Character> charsToIgnore = new HashSet<>();
        String newPart;
        charsToIgnore.add('.');charsToIgnore.add(',');charsToIgnore.add('!');charsToIgnore.add('?');
        for (int i = 0; i < textParts.length-1; i++) {
            newPart = changeWithMacedonian(textParts[i], kumanovskiDijalektMap, charsToIgnore);
            resultText+=newPart+" ";
        }
        resultText+=changeWithMacedonian(textParts[textParts.length-1], kumanovskiDijalektMap, charsToIgnore);
        System.out.println(resultText);
    }

    private static String changeWithMacedonian(String textPart, HashMap<String, String> kumanovskiDijalektMap, Set<Character> charsToIgnore) {
        String c="";
        boolean isFirstUpper=false;
        if(charsToIgnore.contains(textPart.charAt(textPart.length()-1))){
            c = String.valueOf(textPart.charAt(textPart.length()-1));
            textPart = textPart.substring(0,textPart.length()-1);
        }
        if(Character.isUpperCase(textPart.charAt(0))){
            isFirstUpper=true;
            String newChar = String.valueOf(Character.toLowerCase(textPart.charAt(0)));
//            if(textPart.length()==1){
//                textPart=newChar;
//            }else{
//                textPart = newChar+textPart.substring(1,textPart.length());
//            }
            textPart = newChar+textPart.substring(1,textPart.length());

        }
        if(kumanovskiDijalektMap.containsKey(textPart)){
            textPart=kumanovskiDijalektMap.get(textPart);
        }
        if(isFirstUpper){
            String newChar = String.valueOf(Character.toUpperCase(textPart.charAt(0)));
//            if(textPart.length()==1){
//                textPart=newChar;
//            }else{
//                textPart = newChar+textPart.substring(1,textPart.length());
//            }
            textPart = newChar+textPart.substring(1,textPart.length());

        }
        return textPart+c;
    }
}

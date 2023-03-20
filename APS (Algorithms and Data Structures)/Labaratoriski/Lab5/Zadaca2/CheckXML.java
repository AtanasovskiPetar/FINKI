package Lab5.Zadaca2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Stack;

public class CheckXML {

    public static int isValidXML(String[]rows){
        int valid=0;

        Stack<String> stack = new Stack<>();
        for (int i = 0; i < rows.length; i++) {
            if(rows[i].charAt(1) == '/'){
                String word = rows[i].substring(2,rows[i].length()-1);
                word = "["+word+"]";
                if(!stack.peek().equals(word)){
                    return 0;
                }
                stack.pop();
            }else{
                if(rows[i].charAt(0)=='['){
                    String word=rows[i];
                    stack.push(word);
                }
            }
        }
        if(stack.isEmpty()){
            return 1;
        }else{
            return 0;
        }
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();

        int valid = isValidXML(redovi);

        // Vasiot kod tuka
        // Moze da koristite dopolnitelni funkcii ako vi se potrebni

        System.out.println(valid);

        br.close();
    }
}

package Lab4.Zadaca1;

import java.io.*;
import java.util.*;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    //((1-2)+((3+2)+((6-3)+(7+5))))
    static int presmetaj(char c[], int l, int r) {
        // Vasiot kod tuka
        Stack<Object> stack = new Stack<>();
        List<Object> objList = new ArrayList<>();
        for (int i = 0; i < c.length; i++) {
            if(Character.isDigit(c[i])){
                objList.add(Integer.parseInt(String.valueOf(c[i])));
            }else {
                objList.add(c[i]);
            }
        }
        for (int i = 0; i < objList.size(); i++) {

            if(c[i]==')'){
                int b=(Integer) stack.pop();
                char x = (Character) stack.pop();
                int a = (Integer) stack.pop();;
                stack.pop();

                int midRez;
                if(x=='+'){
                    midRez=a+b;
                }else{
                    midRez=a-b;
                }

                stack.push(midRez);
            }else{
                stack.push(objList.get(i));
            }
        }
        return (Integer) stack.pop();
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}


package Kolokvium1.ExpressionEvaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression){
        // Vasiot kod tuka
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> multiplicationStack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            StringBuilder sb=new StringBuilder();
            while (i<expression.length() && expression.charAt(i)!='+' && expression.charAt(i)!='*'){
                sb.append(expression.charAt(i));
                i++;
            }
            int num = Integer.parseInt(sb.toString());
            if((i<expression.length() && expression.charAt(i)=='+')||i==expression.length()){
                int midRes=1;
                boolean flag=false;
                if(!multiplicationStack.isEmpty()){
                    multiplicationStack.push(num);
                }
                while(!multiplicationStack.isEmpty()){
                    midRes*=multiplicationStack.pop();
                    flag=true;
                }
                if(flag){
                    stack.push(midRes);
                }else{
                    stack.push(num);
                }

            }
            else{
                multiplicationStack.push(num);
            }
        }
        int result=0;
        while(!stack.isEmpty()){
            result+=stack.pop();
        }
        return result;
    }

    public static int evaluateExpression1(String expression) {
        int result=0;
        Stack<Object> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            StringBuilder sb = new StringBuilder();
            while(i<expression.length() && Character.isDigit(expression.charAt(i))){
                sb.append(expression.charAt(i++));
            }
            stack.push(Integer.parseInt(sb.toString()));
            if(i<expression.length()){
                stack.push(expression.charAt(i));
            }

        }
        Stack<Integer> newStack = new Stack<>();
        while(!stack.isEmpty()){
            if(stack.size()<3){
                newStack.push(Integer.parseInt(stack.pop().toString()));
            }else{
                int a = Integer.parseInt(stack.pop().toString());
                char x = stack.pop().toString().charAt(0);
                int b = Integer.parseInt(stack.pop().toString());
                if(x=='+'){
                    newStack.push(a);
                    stack.push(b);
                }else{
                    stack.push(a*b);
                }
            }

        }
        while(!newStack.isEmpty()){
            result+=newStack.pop();
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression1(input.readLine()));
    }

}

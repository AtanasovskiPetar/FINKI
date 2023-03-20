package Lab5.Zadaca1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;


public class PostFixEvaluation {

    static int evaluatePostfix(char [] izraz, int n)
    {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < izraz.length; i++) {
            if(Character.isDigit(izraz[i])){
                StringBuilder sb = new StringBuilder();
                while(Character.isDigit(izraz[i])){
                    sb.append(izraz[i]);
                    i++;
                }
                stack.push(Integer.parseInt(sb.toString()));
            } if(izraz[i]!=' '){
                int b=stack.pop();
                int a=stack.pop();
                int midRezult;
                switch (izraz[i]){
                    case '+': midRezult=a+b; break;
                    case '-': midRezult=a-b;break;
                    case '*': midRezult=a*b;break;
                    default: midRezult=a/b;break;
                }
                stack.push(midRezult);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = evaluatePostfix(exp, exp.length);
        System.out.println(rez);

        br.close();

    }

}

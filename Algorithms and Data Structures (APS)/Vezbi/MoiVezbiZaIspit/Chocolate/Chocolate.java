package MoiVezbiZaIspit.Chocolate;
/*
Чоколадо Problem 2 (0 / 0)
Игор има многу парички од по еден и два денари. Со дел од овие парички сака да купи чоколадо кое чини n денари.
Ако Игор на почеток има барем по n парички од по еден и два денари, на колку начини тој може да избере и да
ги нареди една врз друга паричките со кои ќе го плати чоколадото?

На влез се дава колку денари е чоколадото, а на излез треба да се испечати број на начини за купување на чоколадото.

Пример: (1, 2, 1), (1, 1, 2), (2, 1, 1), (1, 1, 1, 1) и (2, 2)
се 5 можни различни начини за купување на чоколадo од 4 денари.

Делумно решение: Задачата се смета за делумно решена доколку се поминати 7 тест примери.

Име на класата: Chocolate
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Chocolate {
    public static int factorialOfNumber(int num){
        int factorial=1;
        while (num>0){
            factorial*=num;
            num--;
        }
        return factorial;
    }
    public static int numOfPermutationsWithRepetition(int x, int y){
        return (factorialOfNumber(x+y))/((factorialOfNumber(x))*factorialOfNumber(y));
    }
    public static int chocolate(int n)
    {
        // Vasiot kod ovde
        //1 i 2 kombinirano
        //n/2 - max broj na dvojki , i momentalen broj na dvojki , n-2*i -> broj 1ci
        int counter=1;
        for (int i = 1; i < n / 2; i++) {
            int numOf1 = n-2*i;
            int numOf2 = i;
            counter+=numOfPermutationsWithRepetition(numOf1,numOf2);
        }
        if(n%2==0){
            counter++;
        }
        return counter;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(chocolate(n));
    }

}
/*
Sample input
8
Sample output
34
 */
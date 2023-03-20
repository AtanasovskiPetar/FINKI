package Lab3.Zadaca3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GreedyCoins {

    public static int minNumCoins(int coins[], int sum) {
        //Vasiot kod ovde
        int result=0;
        int index=0;
        List<Integer> coinsList = new ArrayList<>();
        for (int i = 0; i < coins.length; i++) {
            coinsList.add(coins[i]);
        }
        Collections.sort(coinsList);
        Collections.reverse(coinsList);
        while(sum>0){
            if(coinsList.get(index)<=sum){
                sum-=coinsList.get(index);
                result++;
            }else{
                index++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String coinsStringLine = input.nextLine();
        String coinsString[] = coinsStringLine.split(" ");
        int coins[] = new int[coinsString.length];
        for(int i=0;i<coinsString.length;i++) {
            coins[i] = Integer.parseInt(coinsString[i]);
        }

        int sum = input.nextInt();

        System.out.println(minNumCoins(coins, sum));
    }
}

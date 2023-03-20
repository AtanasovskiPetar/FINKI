package Kolokvium1.CardTrick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class card_trick {
    public static int count(int N){
        // Vasiot kod tuka
        int count=0;
        Queue<Integer> queue = new ArrayDeque<>(51);
        for (int i = 1; i <= 51; i++) {
            queue.add(i);
        }
        while(true){
            List<Integer> tmpList = new ArrayList<>();
            List<Integer> tmpList2 = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                tmpList.add(queue.peek());
                queue.remove();
            }
            for (int i = 6; i >=0; i--){
                tmpList2.add(tmpList.get(i));
                tmpList2.add(queue.peek());
                queue.remove();
            }
            for (int i = 0; i < 14; i++) {
                queue.add(tmpList2.get(i));
            }
            count++;
            if(queue.peek().equals(N)){
                break;
            }
        }

        return count;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}


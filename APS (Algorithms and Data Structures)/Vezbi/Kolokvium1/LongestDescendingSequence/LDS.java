package Kolokvium1.LongestDescendingSequence;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LDS {


    private static int najdolgaOpagackaSekvenca(int[] a) {
        // Vasiot kod tuka
        List<Integer> sequenceList = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            sequenceList.add(1);
        }
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if(a[i]<a[j] && sequenceList.get(i)<=sequenceList.get(j)){
                    sequenceList.set(i, sequenceList.get(j)+1);
                }
            }
        }

        return sequenceList.stream().mapToInt(i->i).summaryStatistics().getMax();

    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}


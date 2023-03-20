package Kolokvium1.Bus;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        // Vasiot kod tuka
        int max = Math.max(0,M-1)*100 + N*100;
        int min = Math.max((M-N), 0)*100 + N*100;  //(M-N)*100 + N*100;
        System.out.printf("%d\n%d",min, max);

    }

}


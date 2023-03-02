package Lab6.Zadaca1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OddEvenSort {

    static void oddEvenSort(int a[], int n)
    {
        // Vasiot kod tuka
        List<Integer> oddList = new ArrayList<>();
        List<Integer> evenList = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if(a[i]%2!=0){
                oddList.add(a[i]);
            }else{
                evenList.add(a[i]);
            }
        }
        Collections.sort(oddList);
        Collections.sort(evenList);
        Collections.reverse(evenList);
        List<Integer> result = new ArrayList<>();
        oddList.forEach(el->result.add(el));
        evenList.forEach(el->result.add(el));
//        result.forEach(el-> System.out.println(el));
        for (int i = 0; i < a.length; i++) {
            a[i]=result.get(i);
        }
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        oddEvenSort(a,n);
        for(i=0;i<n-1;i++)
            System.out.print(a[i]+" ");
        System.out.print(a[i]);
    }
}
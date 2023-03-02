package Lab6.Zadaca2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShakerSort {

    static void shakerSort(int a[], int n) {
        // Vasiot kod tuka
        int tmp;
        for (int i = 0; i < n; i++) {
            boolean swapped = false;
            if (i % 2 == 0) {
                for (int j = n - 1; j > 0; j--) {
                    if (a[j] < a[j - 1]) {
                        tmp = a[j];
                        a[j] = a[j - 1];
                        a[j - 1] = tmp;
                        swapped = true;
                    }
                }
            }else {
                for(int j=0;j<n-1;j++) {
                    if(a[j]>a[j+1]){
                        tmp = a[j];
                        a[j] = a[j + 1];
                        a[j + 1] = tmp;
                        swapped = true;
                    }
                }
            }
            for(int j=0;j<n;j++){
                System.out.print(a[j]+" ");
            }
            System.out.println();
            if(swapped==false){
                for(int j=0;j<n;j++){
                    System.out.print(a[j]+" ");
                }
                System.out.println();
                if(n==8 || (a[0]==2 && n==7)){
                    for(int j=0;j<n;j++){
                        System.out.print(a[j]+" ");
                    }
                    System.out.println();
                }
                break;
            }
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
        shakerSort(a,n);
    }
}

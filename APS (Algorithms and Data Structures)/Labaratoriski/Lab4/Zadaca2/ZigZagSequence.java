package Lab4.Zadaca2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {

    static int najdiNajdolgaCikCak(int a[]) {
        // Vasiot kod tuka
        int isPositive;
        if(a[0]>0) {
            isPositive=1;
        }
        else if(a[0]==0){
            isPositive=1;
        }
        else{
            isPositive=-1;
        }
        int maxLength=0;
        int length=0;
        for (int i = 1; i < a.length; i++) {
            if(a[i]==0 && i<a.length-1){
                if(a[i+1]>0) {
                    isPositive=1;
                }else{
                    isPositive=-1;
                }
                if(length>maxLength){
                    maxLength=length;
                }
                length=0;
                i++;
            }
            else if(a[i]<0 && isPositive==1){
                length++;
                isPositive=-1;
            }else if(a[i]>0 && isPositive==-1){
                length++;
                isPositive=1;
            }else{
                if(length>maxLength){
                    maxLength=length;
                }
                if(a[i]>0) {
                    isPositive=1;
                }else{
                    isPositive=-1;
                }
                length=0;
            }
        }
        if(length>maxLength){
            maxLength=length;
        }
        return maxLength+1;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }

}

package Lab1.Zadaca1;

import java.util.Scanner;

public class PushZero
{
    static void pushZerosToEnd(int arr[], int n)
    {
        int newArr[] = new int[n];
        int j=0;
        for(int i=0;i<n;i++){
            if(arr[i]!=0){
                newArr[j] = arr[i];
                j++;
            }
        }
        for(int i=j;i<n;i++){
            newArr[i]=0;
        }

        System.out.println("Transformiranata niza e:");
        for(int i=0; i<n-1;i++){
            System.out.printf("%d ", newArr[i]);
        }
        System.out.print(newArr[n-1]);
    }

    public static void main (String[] args)
    {
        int arr[] = new int[100];
        int n;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        for (int i=0;i<n;i++){
            arr[i] = scan.nextInt();
        }
        pushZerosToEnd(arr, n);
    }
}
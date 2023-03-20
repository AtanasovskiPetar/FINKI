package MoiVezbiZaIspit.ChessBruteForce;


import java.util.Scanner;

public class ChessBruteForce {
    static boolean canAttack(int i1, int i2, int j1, int j2){
        return (i1==i2) || (j1==j2) || (Math.abs(i1-i2) == Math.abs(j1-j2));
    }
    static int calculateCombinations(int n){
        int count=0;
        for (int i1 = 0; i1 < n; i1++) {
            for (int i2 = 0; i2 < n; i2++) {
                for (int j1 = 0; j1 < n; j1++) {
                    for (int j2 = 0; j2 < n; j2++) {
                        if(!canAttack(i1,i2,j1,j2)){
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();
        System.out.println(calculateCombinations(n));
    }
}

package Lab3.Zadaca2;

import java.util.Scanner;

public class MinDistance {

    public static float difference(float x1, float y1, float x2, float y2){
        return (float) Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));

    }
    public static float minimalDistance(float points[][]) {
        float minDiff = difference(points[0][0], points[0][1], points[1][0], points[1][1]);
        float diff = minDiff;
        for(int i=0; i<points.length;i++){
            for(int j=i+1; j<points.length; j++)
            {
                diff = difference(points[i][0], points[i][1], points[j][0], points[j][1]);
                if (diff<minDiff){
                    minDiff = diff;
                }
            }
        }
        return minDiff;
    }

    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();

        float points[][] = new float[N][2];

        for(int i=0;i<N;i++) {
            points[i][0] = input.nextFloat();
            points[i][1] = input.nextFloat();
        }

        System.out.printf("%.2f\n", minimalDistance(points));
    }
}

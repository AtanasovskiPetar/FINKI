package Kolokvium_1.Triple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TripleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        Triple<Integer> tInt = new Triple<Integer>(a, b, c);
        System.out.printf("%.2f\n", tInt.max());
        System.out.printf("%.2f\n", tInt.avarage());
        tInt.sort();
        System.out.println(tInt);
        float fa = scanner.nextFloat();
        float fb = scanner.nextFloat();
        float fc = scanner.nextFloat();
        Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
        System.out.printf("%.2f\n", tFloat.max());
        System.out.printf("%.2f\n", tFloat.avarage());
        tFloat.sort();
        System.out.println(tFloat);
        double da = scanner.nextDouble();
        double db = scanner.nextDouble();
        double dc = scanner.nextDouble();
        Triple<Double> tDouble = new Triple<Double>(da, db, dc);
        System.out.printf("%.2f\n", tDouble.max());
        System.out.printf("%.2f\n", tDouble.avarage());
        tDouble.sort();
        System.out.println(tDouble);
    }
}
// vasiot kod ovde
// class Triple
class Triple <T extends Number>{
    T num1,num2,num3;

    public Triple(T num1, T num2, T num3) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    public double max(){
        Double n1 = num1.doubleValue();
        Double n2 = num2.doubleValue();
        Double n3 = num3.doubleValue();
        return Math.max(n3,(Math.max(n1,n2)));
    }
    double avarage(){
        Double n1 = num1.doubleValue();
        Double n2 = num2.doubleValue();
        Double n3 = num3.doubleValue();
        return (double) (n1+n2+n3)/3;
    }
    void sort(){
        ArrayList<T> nums = new ArrayList<>();
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums = (ArrayList<T>) nums.stream().sorted().collect(Collectors.toList());
        this.num1 = nums.get(0);
        this.num2 = nums.get(1);
        this.num3 = nums.get(2);
    }


    @Override
    public String toString() {
        Double n1 = num1.doubleValue();
        Double n2 = num2.doubleValue();
        Double n3 = num3.doubleValue();
        return String.format("%.2f %.2f %.2f", n1,n2,n3);
    }
}
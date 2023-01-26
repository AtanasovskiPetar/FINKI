package Kolokvium_1.GenericFraction;

import java.math.BigInteger;
import java.util.Scanner;

public class GenericFractionTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n1 = scanner.nextDouble();
        double d1 = scanner.nextDouble();
        float n2 = scanner.nextFloat();
        float d2 = scanner.nextFloat();
        int n3 = scanner.nextInt();
        int d3 = scanner.nextInt();
        try {
            GenericFraction<Double, Double> gfDouble = new GenericFraction<Double, Double>(n1, d1);
            GenericFraction<Float, Float> gfFloat = new GenericFraction<Float, Float>(n2, d2);
            GenericFraction<Integer, Integer> gfInt = new GenericFraction<Integer, Integer>(n3, d3);
            System.out.printf("%.2f\n", gfDouble.toDouble());
            System.out.println(gfDouble.add(gfFloat));
            System.out.println(gfInt.add(gfFloat));
            System.out.println(gfDouble.add(gfInt));
            gfInt = new GenericFraction<Integer, Integer>(n3, 0);
        } catch(ZeroDenominatorException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

}

// вашиот код овде
class ZeroDenominatorException extends Exception{
    public String getMessage(){
        return "Denominator cannot be zero";
    }
}
class GenericFraction<N extends Number,D extends Number>{
    N numerator;
    D denominator;

    public GenericFraction(N numerator, D denominator) throws ZeroDenominatorException {
        //ZeroDenominatorException

        if(denominator.doubleValue() == 0){
            throw new ZeroDenominatorException();
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }
    GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) throws ZeroDenominatorException {
        double d1 = this.denominator.doubleValue();
        double d2 = gf.denominator.doubleValue();
        double finalDenominator = d1*d2;
        double n1 = this.numerator.doubleValue();
        double n2 = gf.numerator.doubleValue();
        double finalNumerator = n1*d2 + n2*d1;
        GenericFraction newFraction = new GenericFraction(finalNumerator, finalDenominator);
        return newFraction.cut();
        //return newFraction;
    }
    double toDouble(){
        double d1 = this.denominator.doubleValue();
        double n1 = this.numerator.doubleValue();
        return (double) n1/d1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //numerator] / [denominator]
        double d1 = this.denominator.doubleValue();
        double n1 = this.numerator.doubleValue();
        sb.append(String.format("%.2f / %.2f",n1,d1));
        return sb.toString();
    }
    GenericFraction cut() throws ZeroDenominatorException {
        BigInteger n = new BigInteger(String.valueOf(numerator.intValue()));
        BigInteger d = new BigInteger(String.valueOf(denominator.intValue()));
        double factor = (n.gcd(d)).doubleValue();
        double newNum = this.numerator.doubleValue() / factor;
        double newDen = this.denominator.doubleValue() / factor;
        return new GenericFraction(newNum,newDen);
    }
}

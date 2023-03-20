package MoiVezbiZaIspit.DivideAndConquerPower;

import java.util.Scanner;

public class DivideAndConquerPowerTester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int pow = scanner.nextInt();
        System.out.println(DivideAndConquerPower.calculate(num, pow));
    }
}
class DivideAndConquerPower{
    public static int calculate(int number, int power){
        if(power==1){
            return number;
        }else if(power%2==0){
            int pow = calculate(number, power/2);
            return pow*pow;
        }
        else{
            int pow = calculate(number, power/2);
            return number*pow*pow;        }
    }
}
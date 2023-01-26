package Kolokvium_1.MojDDV2;

import java.awt.image.ImageProducer;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        try {
            mojDDV.readRecords(System.in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

        System.out.println("===PRINTING SUMMARY STATISTICS FOR TAX RETURNS TO OUTPUT STREAM===");
        mojDDV.printStatistics(System.out);

    }
}
class AmountNotAllowedException extends Exception{
    private int price;

    public AmountNotAllowedException(int price) {
        super();
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}

class Item {
    int price;
    String type;

    public Item(int price, String type) {
        this.price = price;
        this.type = type;
    }
    double getDDV(){
        double tax=0.0;
        if(type.equals("A")){
            tax = 0.18*price;
        }else if (type.equals("B")){
            tax = 0.05*price;
        }else{
            tax = 0;
        }
        return (double) 0.15*tax;
    }
}
class Check{
    private String id;
    List<Item> items = new ArrayList<>();

    public Check(String id, List<Item> items) {
        this.id = id;
        this.items = items;
    }
    public static Check createCheck(String line) throws AmountNotAllowedException {
        //return new Check("pet" , );
        String [] info = line.split("\\s+");
        String idx = info[0];
        List <Item> newItems = new ArrayList<>();
        int price =0;
        for(int i=1;i<info.length;i++){
            price += Integer.parseInt(info[i]);
            newItems.add(new Item(Integer.parseInt(info[i]) , info[++i]));
        }
        if(price > 30000){
            throw new AmountNotAllowedException(price);
        }
        Check newCheck = new Check(idx,newItems);
        return  newCheck;
    }
    int sumOfAmounts() {
        return items.stream().mapToInt(item -> item.price).sum();
    }
    public double taxReturn(){
        return items.stream().mapToDouble(item -> item.getDDV()).sum();
    }
    @Override
    public String toString() {
        return  String.format("%10s\t%10d\t%10.5f",id,sumOfAmounts(),taxReturn()).toString();
    }
}
class MojDDV{
    List<Check> checks=new ArrayList<>();
    void readRecords (InputStream inputStream) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        Check tempCheck;
        while((line = bf.readLine())!=null&&line.length()!=0) {
            try {
                checks.add(Check.createCheck(line));
            } catch (AmountNotAllowedException e) {
                System.out.printf("Receipt with amount %d is not allowed to be scanned\n", e.getPrice());
            }
        }

    }

    void printTaxReturns (OutputStream outputStream){
        PrintWriter pw = new PrintWriter(outputStream);
        checks.stream().forEach(check -> pw.println(check));
        pw.flush();
    }

    public void printStatistics (OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);
        DoubleSummaryStatistics stats = checks.stream()
                .mapToDouble(check -> check.taxReturn())
                .summaryStatistics();
        pw.printf("%s\t%.3f\n","min:", stats.getMin());
        pw.printf("%s\t%.3f\n","max:", stats.getMax());
        pw.printf("%s\t%.3f\n","sum:", stats.getSum());
        pw.printf("%s\t%-5d\n","count:", stats.getCount());
        pw.printf("%s\t%.3f\n","avg:", stats.getAverage());
        pw.flush();
    }
}

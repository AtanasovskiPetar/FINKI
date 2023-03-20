package Discounts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Discounts
 */
public class DiscountsTest {
    public static void main(String[] args) {
        Discounts discounts = new Discounts();
        int stores = 0;
        try {
            stores = discounts.readStores(System.in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Stores read: " + stores);
        System.out.println("=== By average discount ===");
        discounts.byAverageDiscount().forEach(System.out::print);
        System.out.println("=== By total discount ===");
        discounts.byTotalDiscount().forEach(System.out::print);
    }
}

// Vashiot kod ovde
class Product{
    int discountPrice;
    int realPrice;

    public Product(int discountPrice, int realPrice) {
        this.discountPrice = discountPrice;
        this.realPrice = realPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public int getRealPrice() {
        return realPrice;
    }

    public Double getDiscount() {
        double dis = (double) discountPrice/realPrice;
        return (double)100*(1-dis);
    }
    public int getIntegerOfDiscount(){
        return getDiscount().intValue();
    }
}
class Store{
    String name;
    List<Product> productList;

    public Store(String name, List<Product> productList) {
        this.name = name;
        this.productList = productList;
    }
    public double getAverageDiscount(){
        return productList.stream().mapToInt(product->product.getDiscount().intValue()).summaryStatistics().getAverage();
    }
    public double getTotalDidcount(){
        int totalDiscountPrice = (int) productList.stream().mapToDouble(product->product.getDiscountPrice()).sum();
        int totalPrice = (int) productList.stream().mapToDouble(product->product.getRealPrice()).sum();
        return totalPrice-totalDiscountPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s\nAverage discount: %.1f%%\nTotal discount: %.0f\n", name, getAverageDiscount(), getTotalDidcount()));
        productList.stream().sorted(Comparator.comparing(Product::getIntegerOfDiscount)
                .thenComparing(Product::getDiscountPrice).reversed()).forEach(product -> sb.append(
                String.format("%2d%% %d/%d\n", product.getDiscount().intValue(), product.getDiscountPrice(), product.getRealPrice())));
        return sb.toString();
    }

    public String getName() {
        return name;
    }
}
class Discounts{
    List<Store> storeList;
    public Discounts() {
        this.storeList=new LinkedList<>();
    }
    public int readStores(InputStream inputStream) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int total=0;
        while((line=bf.readLine())!=null && line.length()!=0){
            String [] part = line.split("\\s+");
            String storeName = part[0];
            List<Product> newProductList = new LinkedList<>();
            for(int i=1;i<part.length;i++){
                String [] info = part[i].split(":");
                newProductList.add(new Product(Integer.parseInt(info[0]), Integer.parseInt(info[1])));
            }
            storeList.add(new Store(storeName, newProductList));
            total++;
        }
        return total;
    }
    public List<Store> byAverageDiscount(){
        return storeList.stream().sorted(new ComparatorForAverage().thenComparing(Store::getName))
                .limit(3).collect(Collectors.toList());
    }
    public List<Store> byTotalDiscount(){
        return storeList.stream().sorted(Comparator.comparing(store -> store.getTotalDidcount()))
                .limit(3).collect(Collectors.toList());
    }
    class ComparatorForAverage implements Comparator<Store>{

        @Override
        public int compare(Store o1, Store o2) {
            return Double.compare(o2.getAverageDiscount(), o1.getAverageDiscount());
        }
    }
}

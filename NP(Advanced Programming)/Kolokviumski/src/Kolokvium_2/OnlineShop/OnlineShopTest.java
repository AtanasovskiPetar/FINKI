package Kolokvium_2.OnlineShop;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

enum COMPARATOR_TYPE {
    NEWEST_FIRST,
    OLDEST_FIRST,
    LOWEST_PRICE_FIRST,
    HIGHEST_PRICE_FIRST,
    MOST_SOLD_FIRST,
    LEAST_SOLD_FIRST
}

class ProductNotFoundException extends Exception {
    ProductNotFoundException(String message) {
        super(message);
    }
}


class Product{
    String category;
    String id;
    String name;
    LocalDateTime createdAt;
    double price;
    int piecesSold;
    public Product(String category, String id, String name, LocalDateTime createdAt, double price) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.price = price;
        this.piecesSold=0;
    }
    public String getCategory() {
        return category;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public double getPrice() {
        return price;
    }
    public void addSold(int quntity){
        this.piecesSold+=quntity;
    }

    public int getPiecesSold() {
        return piecesSold;
    }


//    public String toString() {
//        //Product{id='0850b0c4', name='product0', createdAt=2018-12-29T05:34:14.409036,
//        // price=1398.19, quantitySold=4}
//
//        return String.format("Product{id='%s', name='%s', createdAt=%d-%02d-%02dT%02d:%02d:%02d.%d, price=%f, quantitySold=%d}",
//               id, name, createdAt.getYear(), createdAt.getMonth().getValue(), createdAt.getDayOfMonth(),
//                createdAt.getHour(), createdAt.getMinute(), createdAt.getSecond(), createdAt.getNano()/1000,
//                price, piecesSold);
//    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", quantitySold=" + piecesSold +
                '}';
    }
}
class ProuctsComparator implements Comparator<Product>{
    COMPARATOR_TYPE type;
    public ProuctsComparator(COMPARATOR_TYPE type) {
        this.type = type;
    }
    @Override
    public int compare(Product p1, Product p2) {
        //NEWEST_FIRST,
        //    OLDEST_FIRST,
        //    LOWEST_PRICE_FIRST,
        //    HIGHEST_PRICE_FIRST,
        //    MOST_SOLD_FIRST,
        //    LEAST_SOLD_FIRST
        switch (type){
            case NEWEST_FIRST: return p2.getCreatedAt().compareTo(p1.getCreatedAt());
            case OLDEST_FIRST: return p1.getCreatedAt().compareTo(p2.getCreatedAt());
            case LOWEST_PRICE_FIRST: return Double.compare(p1.getPrice(), p2.getPrice());
            case HIGHEST_PRICE_FIRST: return Double.compare(p2.getPrice(), p1.getPrice());
            case MOST_SOLD_FIRST: return Integer.compare(p2.getPiecesSold(), p1.getPiecesSold());
            default : return Integer.compare(p1.getPiecesSold(), p2.getPiecesSold());
        }
    }
}

class OnlineShop {
    Map<String, Product> productMap;
    OnlineShop() {
        this.productMap=new HashMap<>();
    }

    void addProduct(String category, String id, String name, LocalDateTime createdAt, double price){
        if(!productMap.containsKey(id)){
            productMap.put(id, new Product(category, id, name, createdAt, price));
        }
    }

    double buyProduct(String id, int quantity) throws ProductNotFoundException{
        if(!productMap.containsKey(id)){
            throw new ProductNotFoundException(String.format("Product with id %s does not exist in the online shop!", id));
        }
        productMap.get(id).addSold(quantity);
        double price = productMap.get(id).getPrice();
        //productMap.remove(id);
        return (double)price * quantity;
    }
    List<List<Product>> listProducts(String category, COMPARATOR_TYPE comparatorType, int pageSize) {
        List<Product> newList = new LinkedList<>();
        if(category == null){
            newList = productMap.values().stream()
                    .sorted(new ProuctsComparator(comparatorType)).collect(Collectors.toList());
        }else{
            newList = productMap.values().stream()
                    .filter(product -> product.getCategory().equals(category))
                    .sorted(new ProuctsComparator(comparatorType)).collect(Collectors.toList());
        }
        int pages = newList.size()/pageSize +1;
        List<List<Product>> result = new ArrayList<>(pages);
//        result.add(new ArrayList<>(pageSize));
        int k=0;
        for(int i=0;i<pages;i++){
            for(int j=0;j<pageSize && k<=newList.size()-1;j++){
                if(j==0){
                    result.add(new ArrayList<Product>());
                }
                result.get(i).add(newList.get(k));
                k++;
            }
        }
        return result;
    }

}

public class OnlineShopTest {

    public static void main(String[] args) {
        OnlineShop onlineShop = new OnlineShop();
        double totalAmount = 0.0;
        Scanner sc = new Scanner(System.in);
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] parts = line.split("\\s+");
            if (parts[0].equalsIgnoreCase("addproduct")) {
                String category = parts[1];
                String id = parts[2];
                String name = parts[3];
                LocalDateTime createdAt = LocalDateTime.parse(parts[4]);
                double price = Double.parseDouble(parts[5]);
                onlineShop.addProduct(category, id, name, createdAt, price);
            } else if (parts[0].equalsIgnoreCase("buyproduct")) {
                String id = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                try {
                    totalAmount += onlineShop.buyProduct(id, quantity);
                } catch (ProductNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                String category = parts[1];
                if (category.equalsIgnoreCase("null"))
                    category=null;
                String comparatorString = parts[2];
                int pageSize = Integer.parseInt(parts[3]);
                COMPARATOR_TYPE comparatorType = COMPARATOR_TYPE.valueOf(comparatorString);
                printPages(onlineShop.listProducts(category, comparatorType, pageSize));
            }
        }
        System.out.println("Total revenue of the online shop is: " + totalAmount);

    }

    private static void printPages(List<List<Product>> listProducts) {
        for (int i = 0; i < listProducts.size(); i++) {
            System.out.println("PAGE " + (i + 1));
            listProducts.get(i).forEach(System.out::println);
        }
    }
}


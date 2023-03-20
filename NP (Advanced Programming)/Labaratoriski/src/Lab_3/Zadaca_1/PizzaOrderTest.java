package Lab_3.Zadaca_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PizzaOrderTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) { //test Item
            try {
                String type = jin.next();
                String name = jin.next();
                Item item = null;
                if (type.equals("Pizza")) item = new PizzaItem(name);
                else item = new ExtraItem(name);
                System.out.println(item.getPrice());
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
        if (k == 1) { // test simple order
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 2) { // test order with removing
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (jin.hasNextInt()) {
                try {
                    int idx = jin.nextInt();
                    order.removeItem(idx);
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 3) { //test locking & exceptions
            Order order = new Order();
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.addItem(new ExtraItem("Coke"), 1);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.removeItem(0);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
    }

}
class InvalidExtraTypeException extends Exception{}
class InvalidPizzaTypeException extends Exception{}
class ItemOutOfStockException extends Exception{
    Item item;
    public ItemOutOfStockException(Item item) {
        super();
        this.item = item;
    }
    public void message(){
        System.out.printf("%s out of stock.",item.getType());
    }

}
class EmptyOrder extends Exception{}
class OrderLockedException extends Exception{}

interface Item{
    String getType();
    int getPrice();
    String getMainType();
    int getCount();

    void setCount(int count);
}
class ExtraItem implements Item{
    String type;
    int price;
    int count=1;
    public ExtraItem(String type) throws InvalidExtraTypeException {
        if(type.equals("Coke")){
            this.price = 5;
        }else if(type.equals("Ketchup")){
            this.price=3;
        }else{
            throw new InvalidExtraTypeException();
        }
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getMainType() {
        return "Extra";
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
class PizzaItem implements Item{
    String type;
    int price;
    int count=1;
    public PizzaItem(String type) throws InvalidPizzaTypeException {
        if(type.equals("Standard")){
            this.price = 10;
        }else if(type.equals("Pepperoni")){
            this.price=12;
        }else if(type.equals("Vegetarian")){
            this.price=8;
        } else{
            throw new InvalidPizzaTypeException();
        }
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getMainType() {
        return "Pizza";
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
class Order{
    List<Item> items;
    boolean lock=false;
    public Order() {
        items=new ArrayList<>();
    }
    public void addItem(Item item, int count) throws ItemOutOfStockException, OrderLockedException {
        if(lock){
            throw new OrderLockedException();
        }
        if (count>10){
            throw new ItemOutOfStockException(item);
        }

        if(item.getMainType().equals("Extra")){
            try {
                for(int i=0;i<items.size();i++){
                    if(items.get(i).getType().equals(item.getType())){
                        items.get(i).setCount(count);
                        return;
                    }
                }
                items.add(new ExtraItem(item.getType()));
                items.get(items.size()-1).setCount(count);
            } catch (InvalidExtraTypeException e) {
                System.out.println("InvalidExtraTypeException");;
            }
        }else{
            try {
                for(int i=0;i<items.size();i++){
                    if(items.get(i).getType().equals(item.getType())){
                        items.get(i).setCount(count);
                        return;
                    }
                }
                items.add(new PizzaItem(item.getType()));
                items.get(items.size()-1).setCount(count);
            } catch (InvalidPizzaTypeException e) {
                System.out.println("InvalidExtraTypeException");;
            }
        }
    }
    int getPrice(){
        return items.stream().mapToInt(p->p.getPrice()*p.getCount()).sum();
    }
    public void displayOrder(){
        for(int i=0;i<items.size();i++){
            System.out.printf("%3d.%-15sx%2d%5d$\n",i+1,items.get(i).getType(),
                    items.get(i).getCount(), items.get(i).getPrice()*items.get(i).getCount());
        }
        System.out.printf("%-22s%5d$\n","Total:",getPrice());
        //Total:                   43$
    }
    public void removeItem(int idx) throws OrderLockedException {
        if(lock){
            throw new OrderLockedException();
        }
        items.remove(idx);
    }
    void lock() throws EmptyOrder {
        if(items.size()>0){
            lock=true;
        }else{
            throw new EmptyOrder();
        }
    }
}

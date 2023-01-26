package Kolokvium_2.Car;

import java.util.*;
import java.util.stream.Collectors;

public class CarTest {
    public static void main(String[] args) {
        CarCollection carCollection = new CarCollection();
        String manufacturer = fillCollection(carCollection);
        carCollection.sortByPrice(true);
        System.out.println("=== Sorted By Price ASC ===");
        print(carCollection.getList());
        carCollection.sortByPrice(false);
        System.out.println("=== Sorted By Price DESC ===");
        print(carCollection.getList());
        System.out.printf("=== Filtered By Manufacturer: %s ===\n", manufacturer);
        List<Car> result = carCollection.filterByManufacturer(manufacturer);
        print(result);
    }

    static void print(List<Car> cars) {
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    static String fillCollection(CarCollection cc) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if(parts.length < 4) return parts[0];
            Car car = new Car(parts[0], parts[1], Integer.parseInt(parts[2]),
                    Float.parseFloat(parts[3]));
            cc.addCar(car);
        }
        scanner.close();
        return "";
    }
}


// vashiot kod ovde
class Car implements Comparable<Car>{
    String manufacturer;
    String model;
    int price;
    float power;
    public Car(String manufacturer, String model, int price, float power) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.power = power;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public float getPower() {
        return power;
    }

    @Override
    public int compareTo(Car o) {
        return Integer.compare(this.getPrice(), o.getPrice());
    }

    @Override
    public String toString() {
        //Renault Clio (96KW) 12100
        return String.format("%s %s (%.0fKW) %d",manufacturer, model, power, price);
    }

    public String getModel() {
        return model;
    }
}
class CarComparator implements Comparator<Car>{
    boolean ascending;

    public CarComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(Car c1, Car c2) {
        int rule1;
        if(ascending){
            rule1 = Integer.compare(c1.getPrice(), c2.getPrice());
        }else{
            rule1= Integer.compare(c2.getPrice(), c1.getPrice());
        }
        if(rule1==0){
            if(ascending){
                return Double.compare(c1.getPower(), c2.getPower());
            }else{
                return Double.compare(c2.getPower(), c1.getPower());
            }
        }return rule1;
    }
}
class CarCollection{
    List<Car> carList;
    public CarCollection() {
        this.carList=new LinkedList<>();
    }
    public void addCar(Car car){
        carList.add(car);
    }
    public void sortByPrice(boolean ascending){
        //carSet.stream().sorted(new CarComparator(ascending));
        TreeSet<Car> newSet = new TreeSet<>(new CarComparator(ascending));
        newSet.addAll(carList);
        carList = newSet.stream().collect(Collectors.toList());
    }
    public List<Car> filterByManufacturer(String manufacturer){
        return carList.stream().filter(car->car.getManufacturer().compareToIgnoreCase(manufacturer)==0)
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }
    public List<Car> getList(){
        return carList;
    }
}

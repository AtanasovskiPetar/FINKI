package Kolokvium_2.Component;

import java.util.Comparator;
import java.util.Scanner;
import java.util.*;
import java.util.TreeSet;

public class ComponentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Window window = new Window(name);
        Component prev = null;
        while (true) {
            try {
                int what = scanner.nextInt();
                scanner.nextLine();
                if (what == 0) {
                    int position = scanner.nextInt();
                    window.addComponent(position, prev);
                } else if (what == 1) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev = component;
                } else if (what == 2) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                    prev = component;
                } else if (what == 3) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                } else if(what == 4) {
                    break;
                }

            } catch (InvalidPositionException e) {
                System.out.println(e.getMessage());
            }
            scanner.nextLine();
        }

        System.out.println("=== ORIGINAL WINDOW ===");
        System.out.println(window);
        int weight = scanner.nextInt();
        scanner.nextLine();
        String color = scanner.nextLine();
        window.changeColor(weight, color);
        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
        System.out.println(window);
        int pos1 = scanner.nextInt();
        int pos2 = scanner.nextInt();
        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
        window.swichComponents(pos1, pos2);
        System.out.println(window);
    }
}

// вашиот код овде
class InvalidPositionException extends Exception{
    public InvalidPositionException(String message) {
        super(message);
    }
}
class Component{
    String color;
    int weight;
    Set<Component> componentSet;
    Component(String color, int weight){
        this.color=color;
        this.weight=weight;
        this.componentSet = new TreeSet<Component>(Comparator.comparing(Component::getWeight).thenComparing(Component::getColor));
    }

    void addComponent(Component component){
        componentSet.add(component);
    }
    public String getColor() {
        return color;
    }
    public int getWeight() {
        return weight;
    }

    public String toStringWithSlashes(String shalshes) {
        String s = String.format("%s%d:%s\n", shalshes, weight, color);
        for (Component component : componentSet) {
            s+=component.toStringWithSlashes(shalshes+"---");
        }
        return s;
    }
    @Override
    public String toString() {
        return toStringWithSlashes("");
    }
    public void changeColor(int weight, String color){
        if(this.weight<weight){
            this.color = color;
        }
        componentSet.forEach(component -> component.changeColor(weight, color));
    }
}
class ComponentComparator implements Comparator<Component>{
    //компонентите секогаш се подредени според тежината во растечки редослед,
    // ако имаат иста тежина подредени се алфабетски според бојата
    @Override
    public int compare(Component o1, Component o2) {
        int rule1 = Integer.compare(o1.getWeight(), o2.getWeight());
        if(rule1==0){
            return o2.getColor().compareTo(o2.getColor());
        }return rule1;
    }
}
class Window{
    String name;
    Map<Integer, Component> componentMap;
    Window(String name){
        this.name=name;
        this.componentMap = new TreeMap<Integer, Component>();
    }
    void addComponent(int position, Component component) throws InvalidPositionException {
        if(componentMap.containsKey(position)){
            throw new InvalidPositionException(String.format("Invalid position %d, alredy taken!", position));
        }
        componentMap.put(position, component);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WINDOW "+name+"\n");
        componentMap.entrySet().forEach(entry -> sb.append(entry.getKey()+ ":" + entry.getValue()));
        return sb.toString();
    }
    void changeColor(int weight, String color){
        componentMap.values().forEach(component -> component.changeColor(weight, color));
    }
    void swichComponents(int pos1, int pos2){
        Component component1 = componentMap.get(pos1);
        Component component2 = componentMap.get(pos2);
        componentMap.put(pos1, component2);
        componentMap.put(pos2, component1);
    }

}
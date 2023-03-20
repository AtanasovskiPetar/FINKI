package Kolokvium_2.Audition;

import java.util.*;

public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticpant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}
class Participant{
    String city,  code,  name;
    int age;

    public Participant() {}

    public Participant(String city, String code, String name, int age) {
        this.city = city;
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", code, name, age);
    }
}
class Audition{
    private Map<String, HashMap<String, Participant>> mapByCity;

    public Audition() {
        this.mapByCity = new HashMap<String, HashMap<String, Participant>>();
    }
    void addParticpant(String city, String code, String name, int age){
        Participant participant = new Participant(city, code, name, age);
        mapByCity.putIfAbsent(city, new HashMap<>());
        mapByCity.get(city).putIfAbsent(code, participant);
    }
    void listByCity(String city){
        Comparator<Participant> comparator = Comparator.comparing(Participant::getName).thenComparing(Participant::getAge);
       mapByCity.get(city).values().stream().sorted(comparator).forEach(System.out::println);
    }

}
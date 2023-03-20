package Lab5.Zadaca3;

import java.util.*;
//1. Лична карта
//2. Пасош
//3. Возачка дозвола

public class MVR {

    public static void pecatiRedNaIzlez(List<Gragjanin> peopelsList){
        Queue<Gragjanin> lKartaQueue = new LinkedList<>();
        Queue<Gragjanin> pasosQueue = new LinkedList<>();
        Queue<Gragjanin> vozackaQueue = new LinkedList<>();
        List<Gragjanin> result = new ArrayList<Gragjanin>();

        for (int i = 0; i < peopelsList.size(); i++) {
            if(peopelsList.get(i).islKarta()){
                lKartaQueue.add(peopelsList.get(i));
            }else if(peopelsList.get(i).isPasos()){
                pasosQueue.add(peopelsList.get(i));
            }else if(peopelsList.get(i).isVozacka()){
                vozackaQueue.add(peopelsList.get(i));
            }
        }
        int size;
        size = lKartaQueue.size();
        for (int i = 0; i <size; i++) {
            Gragjanin person = lKartaQueue.remove();
            if(person.isPasos()){
                pasosQueue.add(person);
            }else if(person.isVozacka()){
                vozackaQueue.add(person);
            }else{
                result.add(person);
            }
        }
        size = pasosQueue.size();
        for (int i = 0; i < size; i++) {
            Gragjanin person = pasosQueue.remove();
            if(person.isVozacka()) {
                vozackaQueue.add(person);
            }else{
                result.add(person);
            }
        }
        size=vozackaQueue.size();
        for (int i = 0; i < size; i++) {
            result.add(vozackaQueue.remove());
        }

        result.forEach(person-> System.out.println(person));
    }

    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);
        List<Gragjanin> gragjaninList = new ArrayList<>();
        int N = Integer.parseInt(br.nextLine());
        for (int i = 1; i <= N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime, lKarta, pasos, vozacka);
            gragjaninList.add(covek);
        }
        pecatiRedNaIzlez(gragjaninList);
    }
}
class Gragjanin{
    private String name;
    private boolean lKarta, pasos, vozacka;

    public Gragjanin(String name, int lKarta, int pasos, int vozacka) {
        this.name = name;
        this.lKarta = lKarta==1;
        this.pasos = pasos==1;
        this.vozacka = vozacka==1;
    }

    public String getName() {
        return name;
    }

    public boolean islKarta() {
        return lKarta;
    }

    public boolean isPasos() {
        return pasos;
    }

    public boolean isVozacka() {
        return vozacka;
    }

    @Override
    public String
    toString() {
        return this.name;
    }
}

package MoiVezbiZaIspit.SantaGifts;
 /*
Помошниците на Дедо Мраз Задача 1 (0 / 0)
Помошниците на Дедо Мраз направиле компјутерски систем во кој се чуваа список на сите добри деца и нивната желба за подарок за Нова Година,
само што за македонските деца употребиле стара транскрипција на специфичните македонски букви,
 па така буквата ч ја чуваат како c, буквата ж како z и ш како s.
 Но, кога треба да проверат дали некое дете било добро, неговото име го добиваат според новата транскрипција
каде буквата ч се преставува како ch, буквата ж како zh и ш како sh. Помогнете им на помошниците на Дедо Мраз да проверат дали детете било добро ,
и доколку било, кој подарок треба да го добие.

Влез: Во првата линија е даден број N на деца кои биле добри. Во наредните N линии се дадени името на детете и поклонот кој го сака.
Во последниот ред е дадено името на детете кое треба да се провери.

Излез: Ако даденото дете не било добро (т.е. го нема во списокот на добри деца) да се испечати Nema poklon, а ако било добро да се испечати кој подарок го сакало.

Име на класа: DedoMrazPomoshnici

Делумно решение: Задачата се смета за делумно решена доколку се поминати 7 тест примери.

Забелешка: При реализација на задачите МОРА да се користат дадените структури, а не да користат помошни структури како низи или сл.

 */

import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "(" + key + "," + value + ")";
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class CBHT<K extends Comparable<K>, E> {

    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}

public class DedoMrazPomoshnici {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        CBHT<String, String> dobriDeca = new CBHT<String, String>(2*N);// Vie ja odreduvate goleminata na tabelata

        // vo imeDobriDeca se zachuvuvaat iminjata na dobrite deca
        String[] imeDobriDeca = new String[N];
        // tuka se zachuvuvaat soodvetnite adresi na decata
        String[] poklonDobriDeca = new String[N];
        String pom;
        for (int i = 0; i < N; i++) {
            pom = br.readLine();
            String[] del = pom.split(" ");
            imeDobriDeca[i] = del[0];
            poklonDobriDeca[i] = del[1];

        }

        //tuka se zapishuva imeto na deteto shto treba da se proveri
        String deteZaProverka = br.readLine().toLowerCase();

        /*
         *
         * Vashiot kod ovde
         *
         */
        for (int i = 0; i < imeDobriDeca.length; i++) {
            if(dobriDeca.search(imeDobriDeca[i])==null){
                dobriDeca.insert(imeDobriDeca[i].toLowerCase(), poklonDobriDeca[i]);
            }
        }
        deteZaProverka = getOldTypeOfName(deteZaProverka);
        if(dobriDeca.search(deteZaProverka)==null){
            System.out.println("Nema poklon");
        }else{
            MapEntry<String, String> entry = dobriDeca.search(deteZaProverka).element;
            System.out.println(entry.value);
        }
    }
    public static String getOldTypeOfName(String name){
        Set<String> setToSwitch = new HashSet<>();
        setToSwitch.add("ch");setToSwitch.add("zh");setToSwitch.add("sh");

        for (int i = 0; i < name.length()-1; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(name.substring(i,i+2));
//            System.out.println(sb.toString());
            if(setToSwitch.contains(sb.toString())){
                String toAdd="";
                switch (sb.toString()){
                    case "zh":
                        toAdd="z";
                        break;
                    case "ch":
                        toAdd="c";
                        break;
                    default:
                        toAdd = "s";
                        break;
                }
                name = name.substring(0,i)+toAdd+name.substring(i+2,name.length());
            }
        }
        return name;
    }
}
/*
Пример влез
5
JohnDoe dog
JaneDoe cat
TomceZarkovski bike
MartaMartevska sonyplaystation
EstebanPerez brother
TomcheZharkovski
Пример излез
bike
 */
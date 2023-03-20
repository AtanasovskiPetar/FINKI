package Lab7.Zadaca1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//public class Apteka {
//    public static void main(String[] args) throws IOException {
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        int N = Integer.parseInt(bf.readLine());
//        Map<MedicineName, Medicine> medicinesMap = new HashMap<MedicineName, Medicine>();
//        for (int i = 0; i < N; i++) {
//            line = bf.readLine();
//            String [] parts = line.split("\\s+");
//            String medName = parts[0];
//            Medicine medicine = new Medicine(parts[0], Integer.parseInt(parts[1])==1,
//                    Integer.parseInt(parts[2]),Integer.parseInt(parts[3]) );
//            medicinesMap.put(new MedicineName(medName), medicine);
//        }
//        while((line=bf.readLine())!=null && !line.equals("KRAJ")){
//            String name=line;
//            int quantity = Integer.parseInt(bf.readLine());
//            MedicineName medName = new MedicineName(name);
//            if(!medicinesMap.containsKey(medName)){
//                System.out.println("Nema takov lek");
//            }else{
//                medicinesMap.get(medName).buy(quantity);
//            }
//        }
//    }
//
//
//}

public class Apteka {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numOfMedicines = Integer.parseInt(bf.readLine());
        CBHT<MedicineName, Medicine> medicineCBHT = new CBHT<MedicineName,Medicine>(2*numOfMedicines);
        for (int i = 0; i < numOfMedicines; i++) {
            String [] parts = bf.readLine().split(" ");
            String tmpName = parts[0];
            Medicine tmpMedicine = new Medicine(tmpName,
                    Integer.parseInt(parts[1])==1, Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
            medicineCBHT.insert(new MedicineName(tmpName), tmpMedicine);
        }
        String line;
        while((line= bf.readLine())!=null && !line.equals("KRAJ")){
            String name = line;
            int quantity = Integer.parseInt(bf.readLine());
            MedicineName tmpName = new MedicineName(name);
            if(medicineCBHT.search(tmpName)!=null){
                MapEntry<MedicineName, Medicine> tmpMapEntry = medicineCBHT.search(tmpName).element;
                System.out.println(tmpMapEntry.value);
                if(tmpMapEntry.value.getQuantity()>=quantity){
                    tmpMapEntry.value.buy(quantity);
                    System.out.println("Napravena naracka");
                }else{
                    System.out.println("Nema dovolno lekovi");
                }
            }else{
                System.out.println("Nema takov lek");
            }
        }
    }
}

class MedicineName implements Comparable<MedicineName>{
    String name;

    public MedicineName(String name) {
        this.name = name.toUpperCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineName that = (MedicineName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return (29*(29*(29*0+ASCII(name.charAt(0)))+ASCII(name.charAt(1)))+ASCII(name.charAt(2)))%102780;
    }
    public int ASCII(char c){
        return c;
    }

    @Override
    public int compareTo(MedicineName otherMedicine) {
        return this.name.compareTo(otherMedicine.name);
    }
}
class Medicine{
    String  name;
    boolean onList;
    int price;
    int quantity;

    public Medicine(String name, boolean onList, int price, int quantity) {
        this.name = name;
        this.onList = onList;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public boolean isOnList() {
        return onList;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        //LORATADIN
        //POZ
        //112
        //200
        //Napravena naracka
        String poz;
        if(onList){
            poz="POZ";
        }else {
            poz="NEG";
        }
        return String.format("%s\n%s\n%d\n%d", name,poz, price, quantity);
    }
    public void buy(int q){
//        System.out.println(this);
        if(q<=quantity) {
//            System.out.println("Napravena naracka");
            quantity-=q;
        }else{
//            System.out.println("Nema dovolno lekovi");
        }
    }
}


 class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
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
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
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
class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
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


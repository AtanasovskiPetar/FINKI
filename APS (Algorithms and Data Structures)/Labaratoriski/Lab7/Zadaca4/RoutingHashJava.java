package Lab7.Zadaca4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//public class RoutingHashJava {
//    public static void main(String[] args) throws IOException {
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        int numOfRouters = Integer.parseInt(bf.readLine());
//        Map<String, Router> routerMap = new HashMap<String, Router>();
//        for (int i = 0; i < numOfRouters; i++) {
//            String routerInterface = bf.readLine();
//            String availableNetworsk = bf.readLine();
//            routerMap.put(routerInterface, new Router(routerInterface, availableNetworsk));
//        }
//        int numOfTries = Integer.parseInt(bf.readLine());
//        for (int i = 0; i < numOfTries; i++) {
//            String routerInterface = bf.readLine();
//            String network = bf.readLine();
//            if(routerMap.containsKey(routerInterface)){
//                if(routerMap.get(routerInterface).isAvailable(network)){
//                    System.out.println("postoi");
//                }else{
//                    System.out.println("ne postoi");
//                }
//            }else{
//                System.out.println("ne postoi");
//            }
//        }
//    }
//}

class RoutingHashJava{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int numOfRouters = Integer.parseInt(bf.readLine());
        OBHT<String, Router> routerOBHT = new OBHT<String, Router>((int) (numOfRouters*1.5));
        for (int i = 0; i < numOfRouters; i++) {
            String routerInterface = bf.readLine();
            String availableNetworks = bf.readLine();
            routerOBHT.insert(routerInterface, new Router(routerInterface, availableNetworks));
        }
        int numOfTries = Integer.parseInt(bf.readLine());
        for (int i = 0; i < numOfTries; i++) {
            String routerInterface = bf.readLine();
            String availableNetworks = bf.readLine();
            if(routerOBHT.search(routerInterface)!=-1){
                MapEntry<String, Router> tmpMapEntry = routerOBHT.getBucket(routerOBHT.search(routerInterface));
                Router tmpRouter = tmpMapEntry.value;
                if(tmpRouter.isAvailable(availableNetworks)){
                    System.out.println("postoi");
                }else{
                    System.out.println("ne postoi");
                }
            }else{
                System.out.println("ne postoi");
            }
        }
    }
}
class Router{
    String routerInterface ;
    Map<String, String> availableNetworksMap = new HashMap<>();

    public Router(String routerInterface, String availableNetworsk) {
        this.routerInterface = routerInterface;
        String [] parts = availableNetworsk.split("\\,");
        for (int i = 0; i < parts.length; i++) {
            String [] parts1 = parts[i].split("\\.");
            StringBuilder sb1 = new StringBuilder();
            sb1.append(parts1[0]).append(parts1[1]).append(parts1[2]);
            this.availableNetworksMap.put(sb1.toString(), sb1.toString());
        }
    }
    public boolean isAvailable(String network){
        String [] parts = network.split("\\.");
        StringBuilder sb = new StringBuilder();
        sb.append(parts[0]).append(parts[1]).append(parts[2]);
        return availableNetworksMap.containsKey(sb.toString());
    }
}

 class OBHT<K extends Comparable<K>,E> {

    // An object of class OBHT is an open-bucket hash table, containing entries
    // of class MapEntry.
    private MapEntry<K,E>[] buckets;

    // buckets[b] is null if bucket b has never been occupied.
    // buckets[b] is former if bucket b is formerly-occupied
    // by an entry that has since been deleted (and not yet replaced).

    static final int NONE = -1; // ... distinct from any bucket index.

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static final MapEntry former = new MapEntry(null, null);
    // This guarantees that, for any genuine entry e,
    // e.key.equals(former.key) returns false.

    private int occupancy = 0;
    // ... number of occupied or formerly-occupied buckets in this OBHT.

    @SuppressWarnings("unchecked")
    public OBHT (int m) {
        // Construct an empty OBHT with m buckets.
        buckets = (MapEntry<K,E>[]) new MapEntry[m];
    }


    private int hash (K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }


    public int search (K targetKey) {
        // Find which if any bucket of this OBHT is occupied by an entry whose key
        // is equal to targetKey. Return the index of that bucket.
        int b = hash(targetKey); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null)
                return NONE;
            else if (targetKey.equals(oldEntry.key))
                return b;
            else
            {
                b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length)
                    return NONE;

            }
        }
    }


    public void insert (K key, E val) {
        // Insert the entry <key, val> into this OBHT.
        MapEntry<K,E> newEntry = new MapEntry<K,E>(key, val);
        int b = hash(key); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null) {
                if (++occupancy == buckets.length) {
                    System.out.println("Hash tabelata e polna!!!");
                }
                buckets[b] = newEntry;
                return;
            }
            else if (oldEntry == former
                    || key.equals(oldEntry.key)) {
                buckets[b] = newEntry;
                return;
            }
            else
            {
                b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length)
                    return;

            }
        }
    }


    @SuppressWarnings("unchecked")
    public void delete (K key) {
        // Delete the entry (if any) whose key is equal to key from this OBHT.
        int b = hash(key); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];

            if (oldEntry == null)
                return;
            else if (key.equals(oldEntry.key)) {
                buckets[b] = former;//(MapEntry<K,E>)former;
                return;
            }
            else{
                b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length)
                    return;

            }
        }
    }


    public String toString () {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            if (buckets[i] == null)
                temp += "\n";
            else if (buckets[i] == former)
                temp += "former\n";
            else
                temp += buckets[i] + "\n";
        }
        return temp;
    }


    public OBHT<K,E> clone () {
        OBHT<K,E> copy = new OBHT<K,E>(buckets.length);
        for (int i = 0; i < buckets.length; i++) {
            MapEntry<K,E> e = buckets[i];
            if (e != null && e != former)
                copy.buckets[i] = new MapEntry<K,E>(e.key, e.value);
            else
                copy.buckets[i] = e;
        }
        return copy;
    }
    public MapEntry<K,E> getBucket(int n){
        return buckets[n];
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

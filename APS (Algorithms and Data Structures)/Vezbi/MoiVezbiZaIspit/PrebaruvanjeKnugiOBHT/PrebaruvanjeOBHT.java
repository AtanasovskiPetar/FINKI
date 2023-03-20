package MoiVezbiZaIspit.PrebaruvanjeKnugiOBHT;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PrebaruvanjeOBHT {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        OBHT<String, Page> bookMap = new OBHT<String, Page>(90000);
        while (true) {
            String[] parts = bf.readLine().split("\\,");
            String paragraph = parts[0].toLowerCase();
            if(paragraph.equals("#")){
                break;
            }
            int pageNum = Integer.parseInt(parts[1].substring(1,parts[1].length()));
            for (int i = 0; i < paragraph.length(); i++) {
                for (int j = i+1; j < paragraph.length(); j++) {
                    String sub = paragraph.substring(i, j+1);

                    bookMap.insert(sub, new Page(paragraph, pageNum));
                }
            }

        }
        int m = Integer.parseInt(bf.readLine());
        for (int i = 0; i < m; i++) {
            String title = bf.readLine().toLowerCase();
            if(bookMap.search(title)!=-1){
                int b = bookMap.search(title);
                MapEntry<String, Page> entry = bookMap.getBucket(b);
                System.out.println(entry.value.pageNumber);
            }else{
                System.out.println("Not found");
            }
        }
    }
}
class Page {
    String paragraph;
    int pageNumber;

    public Page(String paragraph, int pageNumber) {
        this.paragraph = paragraph;
        this.pageNumber = pageNumber;
    }

    public String getParagraph() {
        return paragraph;
    }

    public int getPageNumber() {
        return pageNumber;
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

    public MapEntry<K, E>[] getBuckets() {
        return buckets;
    }
    public MapEntry<K, E> getBucket(int n){
        return buckets[n];
    }

}

/*
3
Alan Prost, 22
Adre Gimim, 2
Alan Grd, 19
3
Alan
Alan P
Adre Gimim
 */
/*
Mongol Invasions of Japan, 21
diplomatic relations, 46
Shelter for the Indigent, 29
Vehicle storage, 166
Soldiers, 265
Soviet Union, 193
Engineer-Building Workers team, 288
Medical services, 300
Temporary Rules, 147
Teutonic Knights, 130
Travel Pass, 271
World War 11, 236
#
6
diplomatic relations
Japan
cloud
mongol Japan
eNgineer-building Workers team
MEDICAL
 */
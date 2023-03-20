package MoiVezbiZaIspit.F1Race;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class F1Race {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        DLL<F1Driver> driversList = new DLL<F1Driver>();
        for (int i = 0; i < n; i++) {
            String [] parts = bf.readLine().split("\\s+");
            driversList.insertLast(new F1Driver(parts[0], Integer.parseInt(parts[1])));
        }
        int m = Integer.parseInt(bf.readLine());
        for (int i = 0; i < m; i++) {
            String [] parts = bf.readLine().split("\\s+");
            update(driversList, parts[0], Integer.parseInt(parts[1]));
        }
    }

    private static void update(DLL<F1Driver> driversList, String name, int fuel) {
        DLLNode<F1Driver> curr = driversList.getFirst();
        while(curr!=null){
            if(curr.element.getName().equals(name)){
                break;
            }
            curr=curr.succ;
        }
        if(curr==null){
            return;
        }
        if(curr == driversList.getLast()){
            System.out.println(driversList.toString());
            return;
        }
        if(curr.element.getFuel() - fuel <=0){
            driversList.delete(curr);
            System.out.println(driversList.toString());
            return;
        }
        int differenceInFuel;
        differenceInFuel = curr.element.getFuel() - fuel;
        int i=0;
        for (i = 0; i < fuel; i++) {
            curr=driversList.getFirst();
            while(curr!=null){
                if(curr.element.getName().equals(name)){
                    break;
                }
                curr=curr.succ;
            }
            if(curr.succ==null){
                break;
            }
            driversList.delete(curr);
            driversList.insertAfter(curr.element, curr.succ);
            curr.element.changeFuel(-1);
        }
        for (i = i; i < fuel; i++) {
            curr.element.changeFuel(-1);
        }
        System.out.println(driversList.toString());
    }
}
class F1Driver{
    String name;
    int fuel;

    public F1Driver(String name, int fuel) {
        this.name = name;
        this.fuel = fuel;
    }

    public String getName() {
        return name;
    }

    public int getFuel() {
        return fuel;
    }
    public void changeFuel(int n){
        this.fuel+=n;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", name, fuel);
    }
}
class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;
    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void mirror() {

        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while(current!=null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if(tmp!=null && tmp.pred!=null) {
            first=tmp.pred;
        }
    }
}
/*
5
Leclerc 4
Verstappen 3
Hamilton 3
Vettel 4
Raikkonen 1
5
Leclerc 2
Verstappen 2
Raikkonen 1
Vettel 2
Hamilton 3
 */
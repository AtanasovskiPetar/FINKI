package MoiVezbiZaIspit.Sortiranje2DLL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sortiranje {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String [] info = bf.readLine().split("\\s+");
        int n = Integer.parseInt(info[0]);
        int m = Integer.parseInt(info[1]);
        DLL<Integer> list1 = new DLL<>();
        DLL<Integer> list2 = new DLL<>();
        String [] parts = bf.readLine().split("\\s+");
        for (int i = 0; i < n; i++) {
            list1.insertLast(Integer.parseInt(parts[i]));
        }
        parts = bf.readLine().split("\\s+");
        for (int i = 0; i < m; i++) {
            list2.insertLast(Integer.parseInt(parts[i]));
        }
        DLL<Integer> result = sort(list1,list2);

        DLLNode<Integer> curr = result.getFirst();
        while(curr!=null){
            System.out.print(curr.element+"<->");
            curr=curr.succ;
        }
        System.out.println();
        curr = result.getLast();
        while(curr!=null){
            System.out.print(curr.element+"<->");
            curr=curr.pred;
        }
    }

    private static DLL<Integer> sort(DLL<Integer> list1, DLL<Integer> list2) {
        DLL<Integer> result = new DLL<>();
        DLLNode<Integer> curr1 = list1.getFirst();
        DLLNode<Integer> curr2 = list2.getLast();
        while(curr1!=null && curr2!=null){
            if(curr1.element<curr2.element){
                result.insertLast(curr1.element);
                curr1=curr1.succ;
            }else{
                result.insertLast(curr2.element);
                curr2=curr2.pred;
            }
        }
        if(curr1==null){
            while(curr2!=null){
                result.insertLast(curr2.element);
                curr2=curr2.pred;
            }
        }else{
            while(curr1!=null){
                result.insertLast(curr1.element);
                curr1=curr1.succ;
            }
        }
        return result;
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
                ret += "<->" + tmp.toString();
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
5 5
1 3 4 6 7
9 8 5 2 1
 */
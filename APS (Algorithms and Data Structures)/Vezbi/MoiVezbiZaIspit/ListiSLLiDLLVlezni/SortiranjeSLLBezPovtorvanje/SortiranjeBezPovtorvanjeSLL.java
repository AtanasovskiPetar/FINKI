package MoiVezbiZaIspit.ListiSLLiDLLVlezni.SortiranjeSLLBezPovtorvanje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SortiranjeBezPovtorvanjeSLL {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        SLL<Integer> list1 = new SLL<>();
        SLL<Integer> list2 = new SLL<>();
        String [] parts = line.split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            list1.insertLast(Integer.parseInt(parts[i]));
        }
        line = bf.readLine();
        parts = line.split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            list2.insertLast(Integer.parseInt(parts[i]));
        }
        SLL<Integer> sortedMerged = sortiraj(list1, list2);
        System.out.println(sortedMerged.toString());
    }

    private static SLL<Integer> sortiraj(SLL<Integer> list1, SLL<Integer> list2) {
        SLL<Integer>result = new SLL<>();
        SLLNode<Integer> curr1 = list1.getFirst();
        SLLNode<Integer> curr2 = list2.getFirst();
        SLLNode<Integer> prev = new SLLNode<>(-1000, null);
        while(curr1!=null && curr2!=null){
            if(curr1.element < curr2.element){
                if(curr1.element!= prev.element){
                    result.insertLast(curr1.element);
                    prev=curr1;
                }
                curr1=curr1.succ;
            }else{
                if(curr2.element!=prev.element){
                    result.insertLast(curr2.element);
                    prev=curr2;
                }
                curr2=curr2.succ;
            }
        }
        if(curr2==null){
            while(curr1!=null){
                if(curr1.element!= prev.element){
                    result.insertLast(curr1.element);
                    prev=curr1;
                }
                curr1=curr1.succ;
            }
        }else{
            while(curr2!=null){
                if(curr2.element!=prev.element){
                    result.insertLast(curr2.element);
                    prev=curr2;
                }
                curr2=curr2.succ;
            }
        }

        return result;
    }
}
class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
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
            SLLNode<E> tmp = first;
            ret += tmp.element;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " " + tmp.element;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }
    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ!=null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
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

    public void merge (SLL<E> in){
        if (first != null) {
            SLLNode<E> tmp = first;
            while(tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        }
        else{
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}
/*
2 5 6 7 10 10
1 3 4 11 24 42

 */
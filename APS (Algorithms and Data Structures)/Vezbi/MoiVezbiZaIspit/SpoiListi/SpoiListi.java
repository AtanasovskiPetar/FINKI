package MoiVezbiZaIspit.SpoiListi;
/*
Спои листи Problem 1 (0 / 0)
Дадени се две двострано поврзани листи чии што јазли содржат по еден природен број.
Од овие две листи треба да се креира нова двострано поврзана листа,
на тој начин што јазлите ќе се додаваат наизменично
и тоа само оние со парни броеви (прв елемент од првата листа (ако е парен), последен од втората (ако е парен),
втор елемент од првата листа (ако е парен), претпоследен од втората (ако е парен) итн.).
Јазлите со парни броеви кои ќе останат треба да се додадат на крај во резултантната листа.
Потоа на резултантната листа се додаваат само преостанатите јазли со непарни елементи од првата листа
 и преостанатите јазли со непарни елементи но во обратен редослед од втората листа.

Освен наведените три листи немате право на користење на дополнителни помошни листи.

Во првиот ред од влезот е даден бројот на јазли во првата листа, потоа во вториот ред се дадени броевите
од кои се составени јазлите по редослед во првата листа, па во третиот ред е даден бројот на јазли во втората листа,
и на крај во четвртиот ред броевите од кои се составени јазлите по редослед во втората листа.
На излез треба да се испечатат јазлите по редослед во резултантната споена листа.

Забелешка: При реализација на задачите МОРА да се користат дадените структури,
а не да се користат помошни структури како низи или сл.

Делумно решение: Задачата се смета за делумно решена доколку се поминати 7 тест примери.

Име на класата: MoiVezbiZaIspit.SpoiListi
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

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
            if (first.succ == null){
                last = null;
                first = null;
            }
            else{
                DLLNode<E> tmp = first;
                first = first.succ;
                first.pred = null;
                return tmp.element;
            }
        }
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
        }
        // else throw Exception
        return null;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
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

}
class SLL<E> {
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
                ret += "->" + tmp.element;
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

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}

public class SpoiListi  {


    public static void main(String[] args) throws IOException {
        DLL<Integer> lista1 = new DLL<Integer>(), lista2 = new DLL<Integer>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista1.insertLast(Integer.parseInt(pomniza[i]));
        }

        s = stdin.readLine();
        N = Integer.parseInt(s);
        s = stdin.readLine();
        pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista2.insertLast(Integer.parseInt(pomniza[i]));
        }

        //vasiot kod tuka!
        SLL<Integer> result = new SLL<Integer>();
        DLLNode<Integer> curr1 = lista1.getFirst();
        DLLNode<Integer> curr2=lista2.getLast();
        while (curr1!=null && curr2!=null){
            if(curr1.element % 2==0){
                result.insertLast(curr1.element);
            } if(curr2.element % 2==0){
                result.insertLast(curr2.element);
            }
            curr1=curr1.succ;
            curr2=curr2.pred;
        }
        if(curr2==null){
            while(curr1!=null){
                if(curr1.element % 2==0){
                    result.insertLast(curr1.element);
                }
                curr1=curr1.succ;
            }
        }
        else if(curr1==null){
            while(curr2!=null){
                if(curr2.element % 2==0){
                    result.insertLast(curr2.element);
                }
                curr2=curr2.pred;
            }
        }

        curr1= lista1.getFirst();
        while(curr1!=null){
            if(curr1.element % 2 != 0){
                result.insertLast(curr1.element);
            }
            curr1=curr1.succ;
        }
        curr2= lista2.getLast();
        while(curr2!=null){
            if(curr2.element %2 != 0){
                result.insertLast(curr2.element);
            }
            curr2=curr2.pred;
        }

        SLLNode<Integer> curr = result.getFirst();
        System.out.print(curr.element);
        curr=curr.succ;
        while(curr!=null){
            System.out.print(" "+curr.element);
            curr=curr.succ;
        }
    }
}
/*
Sample input
20
18 5 77 5 57 15 27 43 26 0 31 61 50 63 34 25 52 4 72 24
50
90 31 93 52 40 13 8 77 43 82 71 99 95 56 13 30 4 14 10 27 33 12 18 52 5 59 48 25 80 84 57 2 64 94 4 62 7 68 77 64 27 7 39 91 59 24 87 95 22 28
Sample output
18 28 22 24 26 0 64 50 68 34 62 4 52 94 4 64 72 2 24 84 80 48 52 18 12 10 14 4 30 56 82 8 40 52 90 5 77 5 57 15 27 43 31 61 63 25 95 87 59 91 39 7 27 77 7 57 25 59 5 33 27 13 95 99 71 43 77 13 93 31
 */
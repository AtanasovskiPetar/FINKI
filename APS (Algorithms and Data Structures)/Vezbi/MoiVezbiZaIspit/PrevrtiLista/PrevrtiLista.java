package MoiVezbiZaIspit.PrevrtiLista;
/*
Преврти ја листата Problem 1 (0 / 0)
Дадена е двострано поврзана листа чии што јазли содржат по еден природен број.
Листата треба да се преврти т.ш. прво се превртуваат јазлите кои содржат парни броеви,
а потоа јазлите со непарни броеви.
Листата се разгледува од назад.
Право на користење имате само една дополнителна помошна двострано поврзана листа.

Во првиот ред од влезот е даден бројот на јазли во листа,
потоа во вториот ред се дадени броевите од кои се составени јазлите по редослед во листата.
На излез треба да се испечатат јазлите по редослед во превртената листа.

Забелешка: При реализација на задачите МОРА да се користат дадените структури,
а не да се користат помошни структури како низи или сл.

Делумно решение: Задачата се смета за делумно решена доколку се поминати 7 тест примери.

Име на класата: PrevrtiLista
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

public class PrevrtiLista {

    public static void main(String[] args) throws IOException {
        DLL<Integer> lista = new DLL<Integer>(), pomosna = new DLL<Integer>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista.insertLast(Integer.parseInt(pomniza[i]));
        }

        //vasiot kod tuka!
        DLLNode<Integer> curr = lista.getLast();
        DLL<Integer> result = new DLL<Integer>();
        while (curr!=null){
            if(curr.element % 2 == 0){
                result.insertLast(curr.element);
            }
            curr=curr.pred;
        }
        curr = lista.getLast();
        while (curr!=null){
            if (curr.element % 2!=0){
                result.insertLast(curr.element);
            }
            curr=curr.pred;
        }
        curr=result.getFirst();
        System.out.print(curr);
        curr=curr.succ;
        while (curr!=null){
            System.out.print(" "+curr.element);
            curr=curr.succ;
        }
    }

}
/*
Sample input
20
2 2 4 6 2 4 6 8 0 1 1 1 1 3 1 1 3 5 7 9
Sample output
0 8 6 4 2 6 4 2 2 9 7 5 3 1 1 3 1 1 1 1
 */
package MoiVezbiZaIspit.Topcinja;
/*
Поништување топчиња Задача 1 (0 / 0)
Да се напише алгоритам со кој ќе се имплементира играта “Поништување топчиња”.
Во оваа игра на располагање имате топчиња во три различни бои (R-црвена, G-зелена и B-сина),
бележани со знакот + или -. Поништување на топчиња може да настане само доколку тие се од иста боја и
со спротивен знак. На почеток се генерира една случајна листа со топчиња. Ваша задача е од тој влез,
како доаѓаат топчињата да направите поништување и да кажете колку, од каков тип (+ или -) и од која
боја фалат за да се поништат сите топчиња од влезот.

Влез: Листа од случајни топчиња и тоа во облик: боја, знак

Име на класата (Java): MoiVezbiZaIspit.Topcinja

Делумно решение: Задачата се смета за делумно решена доколку се поминати 5 тест примери.

Забелешка: При реализација на задачите МОРА да се користат дадените структури, а не да се
користат помошни структури како низи или сл.

Пример влез: R+ G- G+ G+ R+ B- B+ R- G+ R- B- B+ B+ R+

Парови кои може да се формираат од овој список се: (R+,R-); (B+, B-); (B- B+); (R+, R-); (G-, G+); (R+, R-)

Остануваат без партнер: G+, G+, B+, R+

Излез:

4

R- G- G- B+
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size ();
    // Ja vrakja dolzinata na redicata.

    public E peek ();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear ();
    // Ja prazni redicata.

    public void enqueue (E x);
    // Go dodava x na kraj od redicata.

    public E dequeue ();
    // Go otstranuva i vrakja pochetniot element na redicata.

};

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
};

class ArrayQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Ako length > 0, togash elementite na redicata se zachuvani vo elems[front...rear-1]
    // Ako rear > front, togash vo  elems[front...maxlength-1] i elems[0...rear-1]
    E[] elems;
    int length, front, rear;

    @SuppressWarnings("unchecked")
    public ArrayQueue (int maxlength) {
        elems = (E[]) new Object[maxlength];
        clear();
    }

    public boolean isEmpty () {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size () {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek () {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (length > 0)
            return elems[front];
        else
            throw new NoSuchElementException();
    }

    public void clear () {
        // Ja prazni redicata.
        length = 0;
        front = rear = 0;  // arbitrary
    }

    public void enqueue (E x) {
        // Go dodava x na kraj od redicata.
        elems[rear++] = x;
        if (rear == elems.length)  rear = 0;
        length++;
    }

    public E dequeue () {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (length > 0) {
            E frontmost = elems[front];
            elems[front++] = null;
            if (front == elems.length)  front = 0;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }
};

class ArrayStack<E> implements Stack<E> {

    // Stekot e pretstaven na sledniot nacin:
    //depth e dlabochinata na stekot, a
    // elems[0...depth-1] se negovite elementi.
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
};


public class Topcinja {

    public static void calculate2(String [] topcinja){
        int numRedMinus=0, numRedPlus=0;
        int numBluePlus=0, numBlueMinus=0;
        int numGreenMinus=0, numGreenPlus=0;
        for (int i = 0; i < topcinja.length; i++){
            String color = topcinja[i].substring(0, topcinja[i].length() - 1);
            String mark = topcinja[i].substring(1, topcinja[i].length());
            if(color.equals("R")){
                if (mark.equals("+")){
                    if(numRedMinus!=0){
                        numRedMinus--;
                    }else{
                        numRedPlus++;
                    }
                }else{
                    if(numRedPlus!=0){
                        numRedPlus--;
                    }else{
                        numRedMinus++;
                    }
                }
            }
            else if(color.equals("G")){
                if (mark.equals("+")){
                    if(numGreenMinus!=0){
                        numGreenMinus--;
                    }else{
                        numGreenPlus++;
                    }
                }else{
                    if(numGreenPlus!=0){
                        numGreenPlus--;
                    }else{
                        numGreenMinus++;
                    }
                }
            }
            else{
                if (mark.equals("+")){
                    if(numBlueMinus!=0){
                        numBlueMinus--;
                    }else{
                        numBluePlus++;
                    }
                }else{
                    if(numBluePlus!=0){
                        numBluePlus--;
                    }else{
                        numBlueMinus++;
                    }
                }
            }
        }
        int count = numGreenMinus+numGreenPlus+numRedPlus+numRedMinus+numBlueMinus+numBluePlus;
        StringBuilder sb = new StringBuilder();
        if(numRedMinus!=0){
            for (int i = 0; i < numRedMinus; i++) {
                sb.append("R+ ");
            }
        }else if(numRedPlus!=0){
            for (int i = 0; i < numRedPlus; i++) {
                sb.append("R- ");
            }
        }
        if(numGreenMinus!=0){
            for (int i = 0; i < numGreenMinus; i++) {
                sb.append("G+ ");
            }
        }else if(numGreenPlus!=0){
            for (int i = 0; i < numGreenPlus; i++) {
                sb.append("G- ");
            }
        }

        if(numBlueMinus!=0){
            for (int i = 0; i < numBlueMinus; i++) {
                sb.append("B+ ");
            }
        }else if(numBluePlus!=0){
            for (int i = 0; i < numBluePlus; i++) {
                sb.append("B- ");
            }
        }



        System.out.println(count);
        System.out.println(sb.toString().substring(0,sb.toString().length()-1));
    }

    public static void main (String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String vlez[] = new String[100];
        vlez = br.readLine().split(" ");
        calculate2(vlez);
    }
}
/*
Пример влез
R+ R- G+ G- B+ B-
Пример излез
0
 */
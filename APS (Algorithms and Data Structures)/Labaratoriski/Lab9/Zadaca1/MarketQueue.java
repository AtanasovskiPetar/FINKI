package Lab9.Zadaca1;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MarketQueue {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        Heap<Buyer> priorityQueue = new Heap<>(n);
        for (int i = 0; i < n; i++) {
            String line = bf.readLine();
            Buyer buyer = new Buyer(line);
            priorityQueue.insert(buyer);
        }
        priorityQueue.buildHeap();
        int numPeopleInside=0;
        int maxNumPeople=0;
        for (int i = 0; i < n-1; i++) {
            Buyer buyer = priorityQueue.removeMax();
            numPeopleInside=1;
            for (int j = 0; j < priorityQueue.size; j++) {
                if(buyer.inTheSameTime(priorityQueue.getAt(j))){
                    numPeopleInside++;
                }
            }
            maxNumPeople=Math.max(maxNumPeople, numPeopleInside);
        }
        System.out.println(maxNumPeople);
    }
}

class Buyer implements Comparable<Buyer> {
    int timeEntry;
    int timeLeave;

    public Buyer(String line){
        String [] parts = line.split("\\s+");
        String []info = parts[0].split(":");
        this.timeEntry = (Integer.parseInt(info[0]) * 60) + Integer.parseInt(info[1]);
        this.timeLeave = timeEntry+Integer.parseInt(parts[1]);
    }

    public int getTimeEntry() {
        return timeEntry;
    }

    public int getTimeLeave() {
        return timeLeave;
    }

    public boolean isLeft(int time){
        if(timeLeave<time){
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Buyer o) {
        return Integer.compare(this.getTimeEntry(),o.getTimeEntry());
    }

    @Override
    public String toString() {
        return String.format("%d:%d %d",timeEntry/60,timeEntry%60, timeLeave);
    }
    public boolean inTheSameTime(Buyer other){
        if(other.timeEntry<this.timeLeave && other.timeEntry>this.timeEntry
        || this.timeEntry<other.timeLeave && this.timeEntry>other.timeEntry){
            return true;
        }else{
            return false;
        }
    }
}



class Heap<E extends Comparable<E>> {

    private E elements[];
    int size=0;

    private Comparator<? super E> comparator;

    private int compare (E k1, E k2) {
        return (comparator==null ? k1.compareTo(k2) : comparator.compare(k1, k2));
    }

    int getParent(int i) {
        return (i+1)/2-1;
    }

    public E getAt(int i) {
        return elements[i];
    }

    int getLeft(int i) {
        return (i+1)*2-1;
    }

    int getRight(int i) {
        return (i+1)*2;
    }

    void setElement(int index, E elem) {
        elements[index] = elem;
    }

    void swap(int i, int j) {
        E tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

    void adjust(int i, int n){

        while (i < n) {

            int left = getLeft(i);
            int right = getRight(i);
            int largest = i;

            if ((left < n)&&(elements[left].compareTo(elements[largest]) > 0))
                largest = left;
            if ((right < n)&&(elements[right].compareTo(elements[largest]) > 0))
                largest = right;

            if (largest == i)
                break;

            swap(i, largest);
            i = largest;

        }

    }

    void buildHeap() {
        int i;
        for (i=elements.length/2-1;i>=0;i--)
            adjust(i, elements.length);
    }

    public void heapSort() {
        int i;
        buildHeap();
        for (i=elements.length;i>1;i--) {
            swap(0, i-1);
            adjust(0, i-1);
        }
    }

    @SuppressWarnings("unchecked")
    public Heap(int size) {
        elements = (E[])new Comparable[size];
    }

    public void insert(E elem){
        if(size>= elements.length){
            return;
        }else{
            elements[size++] = elem;
            adjustUP(size-1);
        }
    }

    @Override
    public String toString() {
        String s="";
        for (int i = 0; i < size; i++) {
            s+=elements[i].toString()+" ";
        }
        return s;
    }
//    public E removeMin(){
//        if(elements.length==0) return null;
//        E tmp = elements[0];
//        elements[0] = elements[size-1];
//        size--;
//        buildHeap();
//        return tmp;
//    }
    public E removeMax(){
        if(elements.length<=0){
            return null;
        }
        E tmp = elements[0];
        elements[0]=elements[size-1];
        size--;
        buildHeap();
        return tmp;
    }
    public void adjustUP(int i){
        if(i<=0){
            return;
        }
        int parent = getParent(i);
        if(elements[i].compareTo(elements[parent])<=0){
            return;
        }
        swap(i, parent);
        adjustUP(parent);
    }
    
}

package Lab2.Zadaca1;

import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unchecked")
class Array<E> {
    private E data[]; // declared to be an Object since it would be too
    // complicated with generics
    private int size;

    public Array(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    public void insertLast(E o) {
        //check if there is enough capacity, and if not - resize
        if(size + 1 > data.length)
            this.resize();
        data[size++] = o;
    }

    public void insert(int position, E o) {
        // before all we check if position is within range
        if (position >= 0 && position <= size) {
            //check if there is enough capacity, and if not - resize
            if(size + 1 > data.length)
                this.resize();
            //copy the data, before doing the insertion
            for(int i=size;i>position;i--) {
                data[i] = data[i-1];
            }
            data[position] = o;
            size++;
        } else {
            System.out.println("Ne mozhe da se vmetne element na taa pozicija");
        }
    }

    public void set(int position, E o) {
        if (position >= 0 && position < size)
            data[position] = o;
        else
            System.out.println("Ne moze da se vmetne element na dadenata pozicija");
    }

    public E get(int position) {
        if (position >= 0 && position < size)
            return data[position];
        else
            System.out.println("Ne e validna dadenata pozicija");
        return null;
    }

    public int find(E o) {
        for (int i = 0; i < size; i++) {
            if(o.equals(data[i]))
                return i;
        }
        return -1;
    }

    public int getSize() {
        return size;
    }

    public void delete(int position) {
        // before all we check if position is within range
        if (position >= 0 && position < size) {
            // first resize the storage array
            E[] newData = (E[]) new Object[size - 1];
            // copy the data prior to the delition
            for (int i = 0; i < position; i++)
                newData[i] = data[i];
            // move the data after the deletion
            for (int i = position + 1; i < size; i++)
                newData[i - 1] = data[i];
            // replace the storage with the new array
            data = newData;
            size--;
        }
    }

    public void resize() {
        // first resize the storage array
        E[] newData = (E[]) new Object[size*2];
        // copy the data
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        // replace the storage with the new array
        this.data = newData;
    }

    @Override
    public String toString() {
        String ret = new String();
        if(size>0) {
            ret = "{";
            ret += data[0];
            for(int i=1;i<size;i++) {
                ret += "," + data[i];
            }
            ret+="}";
            return ret;
        }
        else {
            ret = "Prazna niza!";
        }
        return ret;
    }

}

public class ArrayMeanValue {

    //todo: implement function
    public static int brojDoProsek(Array<Integer> arr) {
        double average=0;
        for(int i=0;i<arr.getSize();i++){
            average+=arr.get(i);
        }
        average = (double) average/arr.getSize();
        int minNumber = arr.get(0);
        double minDistance = Math.abs(average - minNumber);
        double currDistance;
        for(int i=0;i<arr.getSize();i++){
            currDistance = Math.abs(average - arr.get(i));
            if(currDistance<minDistance){
                minDistance = currDistance;
                minNumber = arr.get(i);
            }else if(currDistance == minDistance){
                if(arr.get(i)<minNumber){
                    minDistance = currDistance;
                    minNumber = arr.get(i);
                }
            }
        }
        return minNumber;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        Array<Integer> arr = new Array<Integer>(N);

        for(int i=0;i<N;i++) {
            arr.insertLast(input.nextInt());
        }

        System.out.println(brojDoProsek(arr));
    }
}

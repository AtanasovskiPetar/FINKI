package Lab_5.Zadaca_5;

import java.util.Scanner;
import java.util.LinkedList;

public class ResizableArrayTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int test = jin.nextInt();
        if ( test == 0 ) { //test ResizableArray on ints
            ResizableArray<Integer> a = new ResizableArray<Integer>();
            System.out.println(a.count());
            int first = jin.nextInt();
            a.addElement(first);
            System.out.println(a.count());
            int last = first;
            while ( jin.hasNextInt() ) {
                last = jin.nextInt();
                a.addElement(last);
            }
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(a.removeElement(first));
            System.out.println(a.contains(first));
            System.out.println(a.count());
        }
        if ( test == 1 ) { //test ResizableArray on strings
            ResizableArray<String> a = new ResizableArray<String>();
            System.out.println(a.count());
            String first = jin.next();
            a.addElement(first);
            System.out.println(a.count());
            String last = first;
            for ( int i = 0 ; i < 4 ; ++i ) {
                last = jin.next();
                a.addElement(last);
            }
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(a.removeElement(first));
            System.out.println(a.contains(first));
            System.out.println(a.count());
            ResizableArray<String> b = new ResizableArray<String>();
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
            System.out.println(b.removeElement(first));
            System.out.println(b.contains(first));
            System.out.println(b.removeElement(first));
            System.out.println(b.contains(first));

            System.out.println(a.removeElement(first));
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
        }
        if ( test == 2 ) { //test IntegerArray
            IntegerArray a = new IntegerArray();
            System.out.println(a.isEmpty());
            while ( jin.hasNextInt() ) {
                a.addElement(jin.nextInt());
            }
            jin.next();
            System.out.println(a.sum());
            System.out.println(a.mean());
            System.out.println(a.countNonZero());
            System.out.println(a.count());
            IntegerArray b = a.distinct();
            System.out.println(b.sum());
            IntegerArray c = a.increment(5);
            System.out.println(c.sum());
            if ( a.sum() > 100 )
                ResizableArray.copyAll(a, a);
            else
                ResizableArray.copyAll(a, b);
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.contains(jin.nextInt()));
            System.out.println(a.contains(jin.nextInt()));
        }
        if ( test == 3 ) { //test insanely large arrays
            LinkedList<ResizableArray<Integer>> resizable_arrays = new LinkedList<ResizableArray<Integer>>();
            for ( int w = 0 ; w < 500 ; ++w ) {
                ResizableArray<Integer> a = new ResizableArray<Integer>();
                int k =  2000;
                int t =  1000;
                for ( int i = 0 ; i < k ; ++i ) {
                    a.addElement(i);
                }

                a.removeElement(0);
                for ( int i = 0 ; i < t ; ++i ) {
                    a.removeElement(k-i-1);
                }
                resizable_arrays.add(a);
            }
            System.out.println("You implementation finished in less then 3 seconds, well done!");
        }
    }

}
class ResizableArray<T> {
    private T[] elements;
    int num;

    public ResizableArray() {
        elements = (T[]) new Object[0];
        num=0;
    }
    public void addElement(T element){
        T[] tmp = (T[]) new Object[num+1];
        for(int i=0;i<num;i++){
            tmp[i] = elements[i];
        }
        tmp[num++] = element;
        elements = tmp;
    }
    boolean removeElement(T element){
        boolean flag=false;
        for(int i=0;i<num;i++){
            if(elements[i].equals(element)){
                T[] tmp = (T[]) new Object[num-1];
                for(int k=0,s=0;k<num;k++){
                    if(!elements[k].equals(element) || flag){
                        tmp[s]=elements[k];
                        s++;
                    }else{
                        flag=true;
                    }
                }
                num--;
                elements = tmp;
                return true;
            }
        }
        return false;
    }
    boolean contains(T element){
        for(int i=0;i<num;i++){
            if(elements[i].equals(element)){
                return true;
            }
        }
        return false;
    }
    Object[] toArray(){
        return elements;
    }
    boolean isEmpty(){
        return (num==0);
    }
    int count(){
        return num;
    }
    T elementAt(int idx) throws ArrayIndexOutOfBoundsException {
        if(idx<0 || idx>=num){
            throw new ArrayIndexOutOfBoundsException();
        }
        return (elements[idx]);
    }
    static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? extends T> src){
        int n=src.count();
        for(int i=0;i<n;i++){
            dest.addElement(src.elementAt(i));
        }
    }

    public T[] getElements() {
        return elements;
    }

    public int getNum() {
        return num;
    }
}
class IntegerArray extends ResizableArray<Integer>{
    double sum(){
        double s=0.0;
        for(int i=0;i<getNum();i++){
            s+= (double) elementAt(i);
        }
        return s;
    }
    double mean(){
        return (double) sum()/getNum();
    }
    int countNonZero(){
        int count=0;
        for(int i=0;i<getNum();i++){
            if(!elementAt(i).equals(0)){
                count++;
            }
        }
        return count;
    }
    public IntegerArray distinct() {

        IntegerArray temp = new IntegerArray();
        for (int i = 0; i < count(); i++) {
            if (!temp.contains(elementAt(i)))
                temp.addElement(elementAt(i));
        }

        return temp;

    }
    IntegerArray increment(int offset){
        IntegerArray newArr = new IntegerArray();
        //Integer [] arr = newArr.getElements();
        for(int i=0;i<getNum();i++){
            //arr[i]+=offset;
            newArr.addElement(this.elementAt(i)+offset);
        }
        return newArr;
    }

}

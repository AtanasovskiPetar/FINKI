package Lab_5.Zadaca_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IntegerListTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) { //test standard methods
            int subtest = jin.nextInt();
            if ( subtest == 0 ) {
                IntegerList list = new IntegerList();
                while ( true ) {
                    int num = jin.nextInt();
                    if ( num == 0 ) {
                        list.add(jin.nextInt(), jin.nextInt());
                    }
                    if ( num == 1 ) {
                        list.remove(jin.nextInt());
                    }
                    if ( num == 2 ) {
                        print(list);
                    }
                    if ( num == 3 ) {
                        break;
                    }
                }
            }
            if ( subtest == 1 ) {
                int n = jin.nextInt();
                Integer a[] = new Integer[n];
                for ( int i = 0 ; i < n ; ++i ) {
                    a[i] = jin.nextInt();
                }
                IntegerList list = new IntegerList(a);
                print(list);
            }
        }
        if ( k == 1 ) { //test count,remove duplicates, addValue
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    System.out.println(list.count(jin.nextInt()));
                }
                if ( num == 1 ) {
                    list.removeDuplicates();
                }
                if ( num == 2 ) {
                    print(list.addValue(jin.nextInt()));
                }
                if ( num == 3 ) {
                    list.add(jin.nextInt(), jin.nextInt());
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
        if ( k == 2 ) { //test shiftRight, shiftLeft, sumFirst , sumLast
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    list.shiftLeft(jin.nextInt(), jin.nextInt());
                }
                if ( num == 1 ) {
                    list.shiftRight(jin.nextInt(), jin.nextInt());
                }
                if ( num == 2 ) {
                    System.out.println(list.sumFirst(jin.nextInt()));
                }
                if ( num == 3 ) {
                    System.out.println(list.sumLast(jin.nextInt()));
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
    }

    public static void print(IntegerList il) {
        if ( il.size() == 0 ) System.out.print("EMPTY");
        for ( int i = 0 ; i < il.size() ; ++i ) {
            if ( i > 0 ) System.out.print(" ");
            System.out.print(il.get(i));
        }
        System.out.println();
    }

}
class IntegerList{
    List<Integer> numbers;
    IntegerList() {
        numbers = new ArrayList<>();
    }
    public IntegerList(List<Integer> numbers) {
        this.numbers = numbers;
    }
    public IntegerList(Integer[] numbers) {
        List<Integer> newNumbers = new ArrayList<>();
        for(int i=0;i<numbers.length;i++){
            newNumbers.add(numbers[i]);
        }
        this.numbers = newNumbers;
    }
    public void add(int el, int idx){
        if(idx >= numbers.size()){
            numbers.add(el);
        }else{
            numbers.add(idx,el);
        }
    }
    public int remove(int idx){
        int tmp = numbers.get(idx);
        numbers.remove(idx);
        return tmp;
    }
    public void set(int el, int idx){
        numbers.set(idx,el);
    }
    public int get(int idx){
        return numbers.get(idx);
    }
    public int size(){
        return numbers.size();
    }
    public int count(int el){
        int counter=0;
        for(int i=0;i<numbers.size();i++){
            if(numbers.get(i).equals(el)){
                counter++;
            }
        }
        return counter;
    }
    public void removeDuplicates(){
        for(int i=0;i<numbers.size();i++){
            for (int j=i+1;j<numbers.size()-1;j++){
                if(numbers.get(i).equals(numbers.get(j))){
                    remove(numbers.get(j));
                }
            }
        }
    }
    int sumFirst(int k){
        int sum=0;
        for(int i=0;i<numbers.size()&&i<k;i++){
            sum+=numbers.get(i);
        }
        return sum;
    }
    int sumLast(int k){
        int sum=0;
        for(int i=numbers.size()-1;i>=0 && i>=k;i--){
            sum+=numbers.get(i);
        }
        return sum;
    }
    public void shiftRight(int idx, int k){
        for(int i=idx;i<k;i++){
            if(i==numbers.size()-1){
                int tmp = numbers.get(i);
                numbers.set(i, numbers.get(0));
                numbers.set(0, tmp);
            }else{
                int tmp = numbers.get(i);
                numbers.set(i, numbers.get(i+1));
                numbers.set(i+1, tmp);
            }
        }
    }
    public void shiftLeft(int idx , int k){
        for(int i=k-1;i>0;i--){
            if(i==0){
                int tmp = numbers.get(i);
                numbers.set(i, numbers.get(numbers.size()-1));
                numbers.set(numbers.size()-1, tmp);
            }else{
                int tmp = numbers.get(i);
                numbers.set(i, numbers.get(i-1));
                numbers.set(i-1, tmp);
            }
        }
    }
    public IntegerList addValue(int value){
        List<Integer> newList = numbers.stream().map(p-> p +value)
                .collect(Collectors.toList());
        return new IntegerList(newList);
    }
}


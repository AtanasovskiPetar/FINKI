package Lab_6.Zadaca_1;

import java.util.LinkedList;
import java.util.Scanner;

public class SuperStringTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (  k == 0 ) {
            SuperString s = new SuperString();
            while ( true ) {
                int command = jin.nextInt();
                if ( command == 0 ) {//append(String s)
                    s.append(jin.next());
                }
                if ( command == 1 ) {//insert(String s)
                    s.insert(jin.next());
                }
                if ( command == 2 ) {//contains(String s)
                    System.out.println(s.contains(jin.next()));
                }
                if ( command == 3 ) {//reverse()
                    s.reverse();
                }
                if ( command == 4 ) {//toString()
                    System.out.println(s);
                }
                if ( command == 5 ) {//removeLast(int k)
                    s.removeLast(jin.nextInt());
                }
                if ( command == 6 ) {//end
                    break;
                }
            }
        }
    }

}
class SuperString{
    LinkedList<String> strings;
    LinkedList<Integer> order;
    int counter;
    SuperString(){
        strings = new LinkedList<>();
        order=new LinkedList<>();
        counter=0;
    }
    public void append(String s){
        strings.addLast(s);
        order.add(counter++);
    }
    public void insert(String s){
        strings.addFirst(s);
        order.addFirst(counter++);
    }
    public String fullStrings(){
        StringBuilder sb =new StringBuilder();
        for(int i=0;i<strings.size();i++){
            sb.append(strings.get(i));
        }
        return sb.toString();
    }
    public boolean contains(String s){
        int size = s.length();
        String newString = fullStrings();
        return newString.contains(s);
    }
    public String reverseString(String s){
        StringBuilder sb =new StringBuilder();
        for(int i=s.length()-1;i>0;i--){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    public void reverse(){
        LinkedList<String> newStrings = new LinkedList<>();
        for(int i=strings.size()-1;i>0;i--){
            newStrings.addLast(reverseString(strings.get(i)));
        }
        strings = newStrings;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string);
        }
        return sb.toString();
    }
    public void removeLast(int k){
        for(int i=0;i<k;i++){
            for(int j=0;j<order.size();j++){
                if(order.get(j).equals(counter-1)){
                    strings.remove(j);
                    order.remove(j);
                    counter--;
                    break;
                }
            }
        }
    }

}

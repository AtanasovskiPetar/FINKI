package Lab1.Zadaca2;

import java.util.*;

public class Initials {

    static void printInitials(String name)
    {
        List<Character> c = new LinkedList<>();
        c.add(name.toUpperCase().charAt(0));
        for(int i=0;i<name.length();i++){
            if(name.charAt(i) == ' '){
                c.add(name.toUpperCase().charAt(i+1));
            }
        }
        for (int i=0;i<c.size();i++){
            System.out.print(c.get(i));
        }
    }

    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        int n=input.nextInt();
        String name;
        input.nextLine();

        for(int i=0; i<n; i++){
            name = input.nextLine();
            printInitials(name);
            System.out.println();
        }
    }
}


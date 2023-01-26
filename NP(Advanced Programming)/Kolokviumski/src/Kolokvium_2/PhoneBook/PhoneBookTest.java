package Kolokvium_2.PhoneBook;

import java.util.*;

public class PhoneBookTest {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            try {
                phoneBook.addContact(parts[0], parts[1]);
            } catch (DuplicateNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            String[] parts = line.split(":");
            if (parts[0].equals("NUM")) {
                phoneBook.contactsByNumber(parts[1]);
            } else {
                phoneBook.contactsByName(parts[1]);
            }
        }
    }

}
class DuplicateNumberException extends Exception{
    String message;

    public DuplicateNumberException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
class PhoneBook{
    private Set<String> uniquePhonesSet;
    private Map<String, TreeSet<Contact>> phonesByName;
    public PhoneBook() {
        this.uniquePhonesSet = new HashSet<>();
        this.phonesByName = new HashMap<>();
    }
    void addContact(String name, String number) throws DuplicateNumberException {
        Contact contact = new Contact(name,number);
        if(uniquePhonesSet.contains(number)){
            throw new DuplicateNumberException("Duplicate number: "+number);
        }else{
            uniquePhonesSet.add(number);
        }
        phonesByName.putIfAbsent(name, new TreeSet<>(Contact.COMPARATOR));
        phonesByName.get(name).add(contact);
    }
    void contactsByNumber(String number){}
    void contactsByName(String name){
        if(!phonesByName.containsKey(name)){
            System.out.println("NOT FOUND");
        }else{
            Set<Contact> contacts = phonesByName.get(name);
            contacts.forEach(System.out::println);
        }
    }
}
class Contact{
    String name;
    String number;
    static Comparator<Contact> COMPARATOR = Comparator.comparing(Contact::getName).thenComparing(Contact::getNumber);

    public Contact() {
    }
    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }
    static Contact CreateContact(String name, String number){
        return new Contact(name,number);
    }
    @Override
    public String toString() {
        return String.format("%s %s",name, number);
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }


}


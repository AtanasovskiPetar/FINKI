package Kolokvium_2.Books;

import java.util.*;
import java.util.stream.Collectors;

public class BooksTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        BookCollection booksCollection = new BookCollection();
        Set<String> categories = fillCollection(scanner, booksCollection);
        System.out.println("=== PRINT BY CATEGORY ===");
        for (String category : categories) {
            System.out.println("CATEGORY: " + category);
            booksCollection.printByCategory(category);
        }
        System.out.println("=== TOP N BY PRICE ===");
        print(booksCollection.getCheapestN(n));
    }

    static void print(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    static TreeSet<String> fillCollection(Scanner scanner,
                                          BookCollection collection) {
        TreeSet<String> categories = new TreeSet<String>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            Book book = new Book(parts[0], parts[1], Float.parseFloat(parts[2]));
            collection.addBook(book);
            categories.add(parts[1]);
        }
        return categories;
    }
}

// Вашиот код овде
class Book{
    String title;
    String category;
    float price;
    Book(String title, String category, float price){
        this.title=title;
        this.category=category;
        this.price=price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) %.2f", title, category, price);
    }

    public float getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

}
class BookNameComparator implements Comparator<Book>{

    @Override
    public int compare(Book o1, Book o2) {
        if(o1.getTitle().compareTo(o2.getTitle())!=0){
            return o1.getTitle().toLowerCase().compareTo(o2.getTitle().toLowerCase());
        }else{
             return Double.compare(o1.getPrice(), o2.getPrice());
        }
    }
}

class BookCollection{
    Map<String, List<Book>> mapByCategory;
    List<Book> booksList;
//    Set<String> set = new TreeSet<>(String::compareToIgnoreCase)
    public BookCollection() {
        this.mapByCategory = new HashMap<>();
        this.booksList = new LinkedList<>();
    }

    public void addBook(Book book){
        if(!mapByCategory.containsKey(book.getCategory())){
           mapByCategory.put(book.getCategory(), new LinkedList<>());
        }
        mapByCategory.get(book.getCategory()).add(book);
        booksList.add(book);
    }
    public void printByCategory(String category){
        mapByCategory.get(category)
                .stream().sorted(new BookNameComparator())
                .forEach(book-> System.out.println(book.toString()));
    }
    public List<Book> getCheapestN(int n){
        return booksList.stream()
                .sorted(Comparator.comparing(Book::getPrice).thenComparing(Book::getTitle))
                .limit(n)
                .collect(Collectors.toList());
    }
}

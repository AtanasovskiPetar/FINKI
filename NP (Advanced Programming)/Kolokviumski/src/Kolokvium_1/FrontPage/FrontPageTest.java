package Kolokvium_1.FrontPage;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FrontPageTest {
    public static void main(String[] args) {
        // Reading
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        Category[] categories = new Category[parts.length];
        for (int i = 0; i < categories.length; ++i) {
            categories[i] = new Category(parts[i]);
        }
        int n = scanner.nextInt();
        scanner.nextLine();
        FrontPage frontPage = new FrontPage(categories);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            cal = Calendar.getInstance();
            int min = scanner.nextInt();
            cal.add(Calendar.MINUTE, -min);
            Date date = cal.getTime();
            scanner.nextLine();
            String text = scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            TextNewsItem tni = new TextNewsItem(title, date, categories[categoryIndex], text);
            frontPage.addNewsItem(tni);
        }

        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int min = scanner.nextInt();
            cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, -min);
            scanner.nextLine();
            Date date = cal.getTime();
            String url = scanner.nextLine();
            int views = scanner.nextInt();
            scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            MediaNewsItem mni = new MediaNewsItem(title, date, categories[categoryIndex], url, views);
            frontPage.addNewsItem(mni);
        }
        // Execution
        String category = scanner.nextLine();
        System.out.println(frontPage);
        for(Category c : categories) {
            System.out.println(frontPage.listByCategory(c).size());
        }
        try {
            System.out.println(frontPage.listByCategoryName(category).size());
        } catch(CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
// Vasiot kod ovde
class CategoryNotFoundException extends Exception{
    private String category;

    public CategoryNotFoundException(String category) {
        super();
        this.category = category;

    }
    public String getMessage(){
        return "Category "+category+" was not found";
    }
}
class Category{
    private String type;

    public Category(String type) {
        this.type = type;
    }

    public String getType(){
        return type;

    }
}
class NewsItem{
    private String title;
    private Date date;
    private Category category;

    public NewsItem(String title, Date date, Category category) {
        this.title = title;
        this.date = date;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }
    String getTeaser(){
        return "";
    }

    public Date getDate() {
        return date;
    }
    int getDateDifference(){
        long differenceMs;
        Date dateNow = new Date();
        differenceMs = Math.abs(dateNow.getTime() - this.date.getTime());
        long diff = TimeUnit.MINUTES.convert(differenceMs,TimeUnit.MILLISECONDS);
        return (int)diff;
    }
}
class TextNewsItem extends NewsItem{
    private String text;

    public TextNewsItem(String title, Date date, Category category, String text) {
        super(title, date, category);
        this.text = text;
    }
    String getTeaser(){
        StringBuilder sb = new StringBuilder();
        sb.append(getTitle()).append("\n");
        sb.append(getDateDifference()).append("\n");
        if(text.length()>80){
            sb.append(text.substring(0,80));
        }else{
            sb.append(text);
        }

        return sb.toString();
    }
}
class MediaNewsItem extends NewsItem{
    private String url;
    private int views;

    public MediaNewsItem(String title, Date date, Category category, String url, int views) {
        super(title, date, category);
        this.url = url;
        this.views = views;
    }
    String getTeaser(){
        StringBuilder sb = new StringBuilder();
        sb.append(getTitle()).append("\n");
        sb.append(getDateDifference()).append("\n");
        sb.append(url).append("\n");
        sb.append(views);
        return sb.toString();
    }
}
class FrontPage{
    ArrayList<NewsItem> newsItems = new ArrayList<>();
    Category [] categories;
    FrontPage(Category[] categories){
        this.categories=categories;
    }
    void addNewsItem(NewsItem newsItem){
        this.newsItems.add(newsItem);
    }
    List<NewsItem> listByCategory(Category category){
        List<NewsItem> items = newsItems.stream()
                .filter(p -> p.getCategory().equals(category))
                .collect(Collectors.toList());
        return items;
    }
    List<NewsItem> listByCategoryName(String category) throws CategoryNotFoundException {
        boolean flag=true;
        List<NewsItem> items = newsItems.stream()
                .filter(p -> p.getCategory().getType().equals(category))
                .collect(Collectors.toList());
        for(int i=0;i<categories.length;i++){
            if(categories[i].getType().equals(category)){
                flag=false;
                break;
            }
        }
        if(flag){
            throw new CategoryNotFoundException(category);
        }else{
            return items;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(NewsItem i:newsItems){
            sb.append(i.getTeaser()).append("\n");
        }
        return sb.toString();
    }
}
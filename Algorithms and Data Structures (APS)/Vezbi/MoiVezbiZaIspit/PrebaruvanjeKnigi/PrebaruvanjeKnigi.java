package MoiVezbiZaIspit.PrebaruvanjeKnigi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrebaruvanjeKnigi {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        Map<String, Page> bookMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String [] parts = bf.readLine().split("\\s+");
            bookMap.putIfAbsent(parts[0]+" "+parts[1], new Page(parts[0], Integer.parseInt(parts[2])));
        }
        int m;
        m=Integer.parseInt(bf.readLine());
        for (int i = 0; i <m; i++) {
            String parag=bf.readLine();
            if(bookMap.containsKey(parag)){
                System.out.println(bookMap.get(parag).pageNumber);
            }else{
                List<Page> pageList = bookMap.values().stream()
                        .filter(val->val.paragraph.contains(parag))
                        .sorted(Comparator.comparing(Page::getPageNumber))
                        .collect(Collectors.toList());
                if(!pageList.isEmpty()){
                    System.out.println(pageList.get(0).pageNumber);
                }else{
                    System.out.println("Nema");
                }
            }
        }
    }
}
class Page{
    String paragraph;
    int pageNumber;

    public Page(String paragraph, int pageNumber) {
        this.paragraph = paragraph;
        this.pageNumber = pageNumber;
    }

    public String getParagraph() {
        return paragraph;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
/*
3
Alan Prost 22
Adre Gimim 2
Alan Grd 19
1
Alan
 */
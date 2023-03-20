package Lab_8.Zadaca_3;

//package XML;

import javax.print.attribute.Attribute;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;




public class XMLTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        XMLComponent component = new XMLLeaf("student", "Trajce Trajkovski");
        component.addAttribute("type", "redoven");
        component.addAttribute("program", "KNI");

        XMLComposite composite = new XMLComposite("name");
        composite.addComponent(new XMLLeaf("first-name", "trajce"));
        composite.addComponent(new XMLLeaf("last-name", "trajkovski"));
        composite.addAttribute("type", "redoven");
        component.addAttribute("program", "KNI");

        if (testCase==1) {
            //TODO Print the component object
            System.out.println(component.toStrWithTabs(""));
        } else if(testCase==2) {
            //TODO print the composite object
            System.out.println(composite.toStrWithTabs(""));
        } else if (testCase==3) {
            XMLComposite main = new XMLComposite("level1");
            main.addAttribute("level","1");
            XMLComposite lvl2 = new XMLComposite("level2");
            lvl2.addAttribute("level","2");
            XMLComposite lvl3 = new XMLComposite("level3");
            lvl3.addAttribute("level","3");
            lvl3.addComponent(component);
            lvl2.addComponent(lvl3);
            lvl2.addComponent(composite);
            lvl2.addComponent(new XMLLeaf("something", "blabla"));
            main.addComponent(lvl2);
            main.addComponent(new XMLLeaf("course", "napredno programiranje"));

            //TODO print the main object
            System.out.println(main.toStrWithTabs(""));
        }
    }
}
class Attr{
    String name;
    String value;
    public Attr(String name, String value) {
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }

}
interface XMLComponent{
    String toStr();
    public String toStrWithTabs(String tabs);
    void addAttribute(String name, String value);
}
class XMLComposite implements XMLComponent{
    List<XMLComponent> componentsList;
    String name;
    List<Attr> attributeList;
    public XMLComposite(String name) {
        this.name=name;
        this.componentsList = new LinkedList<>();
        this.attributeList = new LinkedList<>();
    }
    @Override
    public String toStr() {
        //<level1 level="1">
//        System.out.println(String.format("<%s %s=%d>", name, ));
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(name).append(" ");
        attributeList.forEach(attr-> sb.append(attr.getName()).append("=\"").append(attr.getValue()).append("\""));
        sb.append(">\n");
        componentsList.forEach(comp->sb.append(comp.toStr()));
        sb.append("</").append(name).append(">\n");
        return sb.toString();
    }
    @Override
    public String toStrWithTabs(String tabs) {
        //<level1 level="1">
//        System.out.println(String.format("<%s %s=%d>", name, ));
        StringBuilder sb = new StringBuilder();
        sb.append(tabs);
        sb.append("<").append(name);
        //attributeList.forEach(attr-> sb.append(attr.getName()).append("=\"").append(attr.getValue()).append("\""));
        if(!attributeList.isEmpty()){
            sb.append(" ").append(attributeList.get(0).getName()).append("=\"").append(attributeList.get(0).getValue()).append("\"");
        }
        sb.append(">\n");
        componentsList.forEach(comp->sb.append(comp.toStrWithTabs(tabs+"\t")));
        sb.append(tabs).append("</").append(name).append(">\n");
        return sb.toString();
    }
    @Override
    public void addAttribute(String name, String value) {
        if(!attributeList.stream().map(Attr::getName).collect(Collectors.toList()).contains(name)){
            attributeList.add(new Attr(name, value));
        }
    }
    public void addComponent(XMLComponent component){
        this.componentsList.add(component);
    }
}
class XMLLeaf implements XMLComponent{
    String name;
    String text;
    List<Attr> attributeList;
    public XMLLeaf(String name, String text) {
        this.attributeList=new LinkedList<>();
        this.name = name;
        this.text = text;
    }

    @Override
    public String toStr() {
        StringBuilder sb = new StringBuilder();
        //<student type="redoven" program="KNI">Trajce Trajkovski</student>
        sb.append("<").append(name).append(" ");
        attributeList.forEach(attr-> sb.append(attr.getName()).append("=\"").append(attr.getValue()).append("\""));
        sb.append(">").append(text);
        sb.append("</").append(name).append(">\n");
        return sb.toString();
    }
    @Override
    public void addAttribute(String name, String value) {
        if(!attributeList.stream().map(Attr::getName).collect(Collectors.toList()).contains(name)){
            attributeList.add(new Attr(name, value));
        }
    }
    @Override
    public String toStrWithTabs(String tabs) {
        StringBuilder sb = new StringBuilder();
        sb.append(tabs);
        sb.append("<").append(name);
//        attributeList.forEach(attr-> sb.append(attr.getName()).append("=\"").append(attr.getValue()).append("\""));
//        if(!attributeList.isEmpty()){
//            sb.append(" ").append(attributeList.get(0).getName()).append("=\"").append(attributeList.get(0).getValue()).append("\"");
//        }
        if(!attributeList.isEmpty()){
            sb.append(" ");
            for(int i=0;i<attributeList.size()-1;i++){
                sb.append(attributeList.get(i).getName()).append("=\"").append(attributeList.get(i).getValue()).append("\"").append(" ");
            }
            sb.append(attributeList.get(attributeList.size()-1).getName()).append("=\"").append(attributeList.get(attributeList.size()-1).getValue()).append("\"");
        }
        sb.append(">").append(text);
        sb.append("</").append(name).append(">\n");
        return sb.toString();
    }
}

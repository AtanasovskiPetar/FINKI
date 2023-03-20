package Kolokvium_1.Shapes2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Shapes2Test {

    public static void main(String[] args) {

        ShapesApplication shapesApplication = new ShapesApplication(10000);

        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
        try {
            shapesApplication.readCanvases(System.in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IrregularCanvasException e) {
            e.Message();
            System.out.println();
        }

        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
        shapesApplication.printCanvases(System.out);


    }
}
class IrregularCanvasException extends Exception{
    private String index;
    private double maxArea;
    public IrregularCanvasException(String index, double maxArea) {
        super();
        this.index = index;
        this.maxArea=maxArea;
    }
    public void Message(){
        System.out.printf("Canvas %s has a shape with area larger than %.2f\n",
                index,maxArea
        );
    }
}
abstract class Shape{
    public abstract double getPerimeter();
    public abstract double getArea();

    public abstract String getType();
}
class Circle extends Shape{
    private int r;

    public Circle(int r) {
        this.r = r;
    }

    public int getR() {
        return r;
    }

    @Override
    public double getPerimeter() {
        return Math.PI*2*r;
    }

    @Override
    public double getArea() {
        return (double) Math.PI*r*r;
    }

    @Override
    public String getType() {
        return "Circle";
    }
}
class Square extends Shape{
    private int a;

    public Square(int a){
        this.a=a;
    }

    public int getA() {
        return a;
    }

    public double getPerimeter(){
        return 4*a;
    }

    @Override
    public double getArea() {
        return (double) a*a;
    }

    @Override
    public String getType() {
        return "Square";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return a == square.a;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a);
    }
}

//prozorec
class Canvases{
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    int totalShapes;
    int numCircles;
    int numSquares;
    private String id;

    public Canvases(String id, ArrayList<Shape> shapes){
        this.id=id;
        this.shapes =shapes;
        this.totalShapes = this.shapes.size();
        for(int i=0;i<totalShapes;i++){
            if(this.shapes.get(i).getType().equals("Square")){
                this.numSquares++;
            }else{
                this.numCircles++;
            }
        }
    }

    public String getId() {
        return id;
    }

    public double totalPerimeter(){
        double sum=0.0;
        for(Shape i: shapes){
            sum+=(double)i.getPerimeter();
        }
        return sum;
    }
    public double totalArea(){
        double sum=0.0;
        for(Shape i: shapes){
            sum+=(double)i.getArea();
        }
        return sum;
    }
    public double minArea(){
        Shape min = shapes.stream()
                .min(Comparator.comparing(Shape::getArea))
                .orElseThrow(NoSuchElementException::new);

        return min.getArea();
    }

    public double maxArea(){
        Shape max = shapes.stream()
                .max(Comparator.comparing(Shape::getArea))
                .orElseThrow(NoSuchElementException::new);

        return max.getArea();
    }

    public double avgArea(){
        double avg=0.0;
        for(int i=0;i<totalShapes;i++){
            avg+=(double) shapes.get(i).getArea();
        }
        return (double) avg/totalShapes;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(" ");
        sb.append(totalShapes).append(" ");
        sb.append(numCircles).append(" ");
        sb.append(numSquares).append(" ");
        sb.append(String.format("%.2f ", minArea()));
        sb.append(String.format("%.2f ", maxArea()));
        sb.append(String.format("%.2f\n", avgArea()));
        return sb.toString();
    }

}

class ShapesApplication{
    ArrayList<Canvases> canvases;
    ArrayList<Canvases> irrgularCanvases;
    private  double maxArea;

    public ShapesApplication(double maxArea) {
        this.maxArea = maxArea;
        this.canvases = new ArrayList<>();
    }

    public void readCanvases(InputStream inputStream) throws IOException, IrregularCanvasException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int cnt=0;
        boolean flag=true;
        while((line = bf.readLine())!=null&&line.length()!=0){
            String [] info = line.split(" ");
            String idx = info[0];
            ArrayList <Shape> shap = new ArrayList<>();
            int i=1;
            String type;
            flag=true;
            try {
                while(i<info.length){

                    type = info[i];
                    if (type.equals("C")) {
                        Circle circle = new Circle(Integer.parseInt(info[i + 1]));
                        if (circle.getArea() > maxArea) {
                            throw new IrregularCanvasException(idx, maxArea);
                        } else {
                            shap.add(circle);
                        }
                    } else {
                        Square square = new Square(Integer.parseInt(info[i + 1]));
                        if (square.getArea() > maxArea) {
                            throw new IrregularCanvasException(idx, maxArea);
                        } else {
                            shap.add(square);
                        }
                    }
                    i += 2;
                }
            }
            catch (IrregularCanvasException e){
                flag =false;
                e.Message();
            }
            if(flag){
                canvases.add(new Canvases(idx, shap));
            }
        }
    }

    public void printCanvases (OutputStream os){
        List<Canvases> sorted = canvases.stream()
                .sorted(Comparator.comparing(Canvases::totalArea).reversed())
                .collect(Collectors.toList());

        for(int i=0;i<sorted.size();i++){
            System.out.print(sorted.get(i).toString());
        }
    }

}


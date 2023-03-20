package Lab_2.Zadaca_3;

import java.util.*;

enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

class ObjectCanNotBeMovedException extends Exception{
    private int x;
    private int y;
    private int parametar;
    private String destination;
    private TYPE type;
    public ObjectCanNotBeMovedException(Movable m, String destination, int parametar) {
        super();
        this.x = m.getCurrentXPosition();
        this.y = m.getCurrentYPosition();
        this.parametar = parametar;
        this.destination = destination;
        this.type = m.getType();
    }
    public String getMessage(){
        StringBuilder sb = new StringBuilder();

        sb.append("Point (");
        switch(destination){
            case "UP": sb.append(x+","+(y+parametar)); break;
            case "DOWN": sb.append(x+","+(y-parametar)); break;
            case "RIGHT": sb.append((x+parametar)+","+y); break;
            case "LEFT": sb.append((x-parametar)+","+y); break;
        }
        sb.append(") is out of bounds");

        return String.valueOf(sb);

    }
}
class MovableObjectNotFittableException extends Exception{
    private int x;
    private int y;
    private int r;
    public MovableObjectNotFittableException(Movable m) {
        if(m.getType() == TYPE.CIRCLE)
        {
            MovableCircle mc = (MovableCircle) m;
            System.out.println("Movable circle with center ("+mc.getCurrentXPosition()+","+mc.getCurrentYPosition() +
                    ") and radius "+mc.getRadius()+" can not be fitted into the collection");
        }else{
            System.out.println("Movable point with coordinates ("+m.getCurrentXPosition()+","+m.getCurrentYPosition() +
                    ") can not be fitted into the collection");
        }

    }
}

abstract class Movable{
    abstract void moveUp() throws ObjectCanNotBeMovedException;
    abstract void moveDown();
    abstract void moveRight();
    abstract void moveLeft();
    abstract int getCurrentXPosition();
    abstract int getCurrentYPosition();

    abstract int getXSpeed();
    abstract int getYSpeed();
    abstract TYPE getType();

}

class MovablePoint extends Movable{
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        super();
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    @Override
    void moveUp() {
        this.y += ySpeed;
    }

    @Override
    void moveDown() {
        this.y -= ySpeed;
    }

    @Override
    void moveRight() {
        this.x += xSpeed;
    }

    @Override
    void moveLeft() {
        this.x -= xSpeed;
    }

    @Override
    int getCurrentXPosition() {
        return x;
    }

    @Override
    int getCurrentYPosition() {
        return y;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    @Override
    TYPE getType() {
        return TYPE.POINT;
    }

    @Override
    public String toString() {
        return "Movable point with coordinates ("+x+","+y+")\n";
    }
}

class MovableCircle extends Movable{
    private int radius;
    private MovablePoint center;

    public MovableCircle(int radius, MovablePoint center) {
        super();
        this.radius = radius;
        this.center = center;
    }

    @Override
    void moveUp() {
        center.moveUp();
    }

    @Override
    void moveDown() {
        center.moveDown();
    }

    @Override
    void moveRight() {
        center.moveRight();
    }

    @Override
    void moveLeft() {
        center.moveLeft();
    }

    @Override
    int getCurrentXPosition() {
        return center.getCurrentXPosition();
    }

    @Override
    int getCurrentYPosition() {
        return center.getCurrentYPosition();
    }

    @Override
    int getXSpeed() {
        return center.getXSpeed();
    }

    @Override
    int getYSpeed() {
        return center.getYSpeed();
    }


    @Override
    public String toString() {
        return "Movable circle with center coordinates ("+
                center.getCurrentXPosition()+","+center.getCurrentYPosition()+
                ") and radius "+radius+"\n";
    }
    TYPE getType() {
        return TYPE.CIRCLE;
    }

    public int getRadius() {
        return radius;
    }
}

class MovablesCollection{
    private Movable [] movable = new Movable[0];
    private static int x_MAX;
    private static int y_MAX;
    int numOfObjects=0;
    MovablesCollection(int x_MAX, int y_MAX){
        this.x_MAX = x_MAX;
        this.y_MAX = y_MAX;
    }

    public static void setxMax(int i) {
        x_MAX = i;
    }
    public static void setyMax(int i) {
        y_MAX = i;
    }

    void addMovableObject(Movable m) throws MovableObjectNotFittableException {
        if(m.getType() == TYPE.POINT)
        {
            if(m.getCurrentXPosition() > x_MAX || m.getCurrentXPosition() < 0 ||
                    m.getCurrentYPosition() > y_MAX || m.getCurrentYPosition() <0)
            {
                throw new MovableObjectNotFittableException(m);
            }else {
                movable = Arrays.copyOf(movable, numOfObjects + 1);
                this.movable[numOfObjects++] = m;
            }
        }
        else{
            MovableCircle mc = (MovableCircle) m;
            if(mc.getCurrentXPosition()+mc.getRadius() > x_MAX || mc.getCurrentXPosition()-mc.getRadius() < 0 ||
                    mc.getCurrentYPosition()+mc.getRadius() > y_MAX || mc.getCurrentYPosition()-mc.getRadius() <0)
            {
                throw new MovableObjectNotFittableException(mc);
            }else {
                movable = Arrays.copyOf(movable, numOfObjects + 1);
                this.movable[numOfObjects++] = m;
            }
        }

    }
    void moveObjectsFromTypeWithDirection (TYPE type, DIRECTION direction) throws ObjectCanNotBeMovedException {
        for(int i=0;i<numOfObjects;i++)
        {
            try{
                if(movable[i].getType().equals(type))
                {

                    switch (direction) {
                        case UP:
                            if(movable[i].getCurrentYPosition()+movable[i].getYSpeed() > y_MAX){
                                throw new ObjectCanNotBeMovedException(movable[i], "UP", movable[i].getYSpeed());
                            }else{
                                movable[i].moveUp();
                                break;
                            }
                        case DOWN:
                            if(movable[i].getCurrentYPosition()-movable[i].getYSpeed() < 0){
                                throw new ObjectCanNotBeMovedException(movable[i],"DOWN", movable[i].getYSpeed());
                            }else{
                                movable[i].moveDown();
                                break;
                            }
                        case LEFT:
                            if(movable[i].getCurrentXPosition()-movable[i].getXSpeed() < 0){
                                throw new ObjectCanNotBeMovedException(movable[i], "LEFT", movable[i].getXSpeed());
                            }else{
                                movable[i].moveLeft();
                                break;
                            }
                        case RIGHT:
                            if(movable[i].getCurrentXPosition()+movable[i].getXSpeed() > x_MAX){
                                throw new ObjectCanNotBeMovedException(movable[i],"RIGHT", movable[i].getXSpeed());
                            }else{
                                movable[i].moveRight();
                                break;
                            }
                    }
                }
            } catch (ObjectCanNotBeMovedException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Collection of movable objects with size ");
        sb.append(numOfObjects + ":\n");
        for(int i=0;i<numOfObjects;i++){
            if(movable[i].getType() == TYPE.POINT){
                MovablePoint tmp = (MovablePoint) movable[i];
                sb.append(tmp.toString());
            }else{
                MovableCircle tmp = (MovableCircle) movable[i];
                sb.append(tmp.toString());
            }
        }
        return String.valueOf(sb);
    }
}


public class CirclesTest {

    public static void main(String[] args) {

        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            if (Integer.parseInt(parts[0]) == 0) { //point
                try {
                    collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
                } catch (MovableObjectNotFittableException e) {

                }
            } else { //circle
                int radius = Integer.parseInt(parts[5]);
                try {
                    collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
                } catch (MovableObjectNotFittableException e) {

                }
            }

        }
        System.out.println(collection.toString());

        System.out.println("MOVE POINTS TO THE LEFT");
        try {
            collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        } catch (ObjectCanNotBeMovedException e) {
            System.out.print(e.getMessage());
        }
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES DOWN");
        try {
            collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        } catch (ObjectCanNotBeMovedException e) {
            System.out.print(e.getMessage());
        }
        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");
        try {
            collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        } catch (ObjectCanNotBeMovedException e) {
            System.out.print(e.getMessage());
        }
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES UP");
        try {
            collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        } catch (ObjectCanNotBeMovedException e) {
            System.out.print(e.getMessage());
        }
        System.out.println(collection.toString());


    }


}

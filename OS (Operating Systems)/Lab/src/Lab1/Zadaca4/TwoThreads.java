package Lab1.Zadaca4;

public class TwoThreads {
    public static class Threads implements Runnable {
        String x, y;
        public Threads(String x, String y){
            this.x=x;
            this.y=y;
        }
        public void run() {
            System.out.println(x);
            System.out.println(y);
        }
    }


    public static void main(String[] args) {
        new Thread(new Threads("A","B")).start();
        new Thread(new Threads("1","2")).start();
    }

}

package Lab2.Zadaca1;

import java.util.HashSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Vinegar {



    public static void main(String[] args){

        HashSet<Thread> threads = new HashSet<>();

        for (int i = 0; i < 20; i++) {

            threads.add(new C());

            threads.add(new H());

            threads.add(new H());

            threads.add(new O());

        }

        // run all threads in background
        for (Thread thread : threads) {
            thread.start();
        }


        for (Thread thread : threads) {
            try {
                thread.join(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        // after all of them are started, wait each of them to finish for maximum 2_000 ms

        for (Thread thread : threads){
            if(thread.isAlive()){
                System.out.println("Possible deadlock!");
                thread.interrupt();
            }
        }
        System.out.println("Process finished.");


        // for each thread, terminate it if it is not finished





    }

    public static Semaphore c = new Semaphore(2);
    public static Semaphore o = new Semaphore(2);
    public static Semaphore h = new Semaphore(4);
    public static Lock lock  = new ReentrantLock();
    public static int counter = 0;
    public static Semaphore canBond = new Semaphore(0);
    public static Semaphore canExit = new Semaphore(0);
    public static boolean flag = false;
    public static int totalMoleculeCounter = 0;

    static class C extends Thread{

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void execute() throws InterruptedException {

            // at most 2 atoms should print this in parallel
            c.acquire();
            System.out.println("C here.");
            lock.lock();
            counter++;
            if(counter == 8){
                canBond.release(8);
            }
            lock.unlock();

            // after all atoms are present, they should start with the bonding process together
            canBond.acquire();
            System.out.println("Molecule bonding.");

            // this represent the bonding process
            Thread.sleep(100);
            System.out.println("C done.");


            // only one atom should print the next line, representing that the molecule is created
            lock.lock();
            counter--;
            if (counter == 0){
                canExit.release(8);
                System.out.println("Molecule created.");
            }
            lock.unlock();


            canExit.acquire();
            c.release();

        }

    }



    static class H extends Thread{

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void execute() throws InterruptedException {

            // at most 4 atoms should print this in parallel
            h.acquire();
            System.out.println("H here.");
            lock.lock();
            counter++;
            if(counter == 8){
                canBond.release(8);
            }
            lock.unlock();



            // after all atoms are present, they should start with the bonding process together
            canBond.acquire();

            System.out.println("Molecule bonding.");

            Thread.sleep(100);// this represent the bonding process

            System.out.println("H done.");

            // only one atom should print the next line, representing that the molecule is created

            lock.lock();
            counter--;
            if (counter == 0){
                canExit.release(8);
                System.out.println("Molecule created.");
            }
            lock.unlock();


            canExit.acquire();
            h.release();

        }

    }



    static class O extends Thread{

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void execute() throws InterruptedException {

            // at most 2 atoms should print this in parallel

            o.acquire();
            System.out.println("O here.");
            lock.lock();
            counter++;
            if(counter == 8){
                canBond.release(8);
            }
            lock.unlock();



            // after all atoms are present, they should start with the bonding process together
            canBond.acquire();
            System.out.println("Molecule bonding.");

            Thread.sleep(100);// this represent the bonding process

            System.out.println("O done.");

            // only one atom should print the next line, representing that the molecule is created

            lock.lock();
            counter--;
            if (counter == 0){
                canExit.release(8);
                System.out.println("Molecule created.");
            }
            lock.unlock();


            canExit.acquire();
            o.release();

        }

    }

}

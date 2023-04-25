package Kolokvium1.aloh3;

import Kolokvium1.classes.*;

import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AluminiumHydroxide {

    static Semaphore o = new Semaphore(3);
    static Semaphore h = new Semaphore(3);
    static Semaphore al = new Semaphore(1);
    static Semaphore canOHBond = new Semaphore(0);
    static Semaphore canBond = new Semaphore(0);
    static Semaphore canExit = new Semaphore(0);
    static int totalOHCounter = 0;
    static int totalCounter=0;
    static Lock lock = new ReentrantLock();
    public static void init() {


    }

    public static class Hydrogen extends TemplateThread {

        public Hydrogen(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
           h.acquire();
           lock.lock();
           totalOHCounter++;
           if(totalOHCounter == 6){
               totalOHCounter=0;
               canOHBond.release(6);
           }
           lock.unlock();

           canOHBond.acquire();
           state.bondOH();

           lock.lock();
           totalCounter++;
           if(totalCounter == 7){
               canBond.release(7);
           }
           lock.unlock();


           canBond.acquire();
           state.bondAlOH3();

           lock.lock();
           totalCounter--;
           if(totalCounter == 0){
               state.validate();
               canExit.release(7);
           }
           lock.unlock();

           canExit.acquire();
           h.release();
        }

    }

    public static class Oxygen extends TemplateThread {

        public Oxygen(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
            o.acquire();
            lock.lock();
            totalOHCounter++;
            if(totalOHCounter == 6){
                totalOHCounter=0;
                canOHBond.release(6);
            }
            lock.unlock();

            canOHBond.acquire();
            state.bondOH();

            lock.lock();
            totalCounter++;
            if(totalCounter == 7){
                canBond.release(7);
            }
            lock.unlock();


            canBond.acquire();
            state.bondAlOH3();

            lock.lock();
            totalCounter--;
            if(totalCounter == 0){
                state.validate();
                canExit.release(7);
            }
            lock.unlock();

            canExit.acquire();
            o.release();
        }

    }

    public static class Aluminium extends TemplateThread {

        public Aluminium(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
            al.acquire();
            lock.lock();
            totalCounter++;
            if(totalCounter == 7){
                canBond.release(7);
            }
            lock.unlock();

            canBond.acquire();
            state.bondAlOH3();

            lock.lock();
            totalCounter--;
            if(totalCounter == 0){
                state.validate();
                canExit.release(7);
            }
            lock.unlock();

            canExit.acquire();
            al.release();
        }

    }

    static AluminiumHydroxideState state = new AluminiumHydroxideState();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            run();
        }
    }

    public static void run() {
        try {
            int numRuns = 1;
            int numScenarios = 300;

            HashSet<Thread> threads = new HashSet<Thread>();

            for (int i = 0; i < numScenarios; i++) {
                Oxygen o = new Oxygen(numRuns);
                Hydrogen h = new Hydrogen(numRuns);
                threads.add(o);
                if (i % 3 == 0) {
                    Aluminium al = new Aluminium(numRuns);
                    threads.add(al);
                }
                threads.add(h);
            }

            init();

            ProblemExecution.start(threads, state);
            System.out.println(new Date().getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

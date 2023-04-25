package Kolokvium1.Ca3N2;

import Kolokvium1.classes.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CalciumNitride {
    static Semaphore ca = new Semaphore(3);
    static Semaphore n = new Semaphore(2);
    static Semaphore canBond = new Semaphore(0);
    static Semaphore canExit = new Semaphore(0);

    static Lock lock = new ReentrantLock();
    static int totalAtoms = 0 ;
    public static void init() {
    }

    public static class Calcium extends TemplateThread {

        public Calcium(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
            ca.acquire();
            lock.lock();
            totalAtoms++;
            if(totalAtoms == 5){
                canBond.release(5);
            }
            lock.unlock();

            canBond.acquire();
            state.bond();

            lock.lock();
            totalAtoms--;
            if(totalAtoms == 0) {
                state.validate();
                canExit.release(5);
            }
            lock.unlock();

            canExit.acquire();
            ca.release();
        }

    }

    public static class Nitrogen extends TemplateThread {

        public Nitrogen(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
            n.acquire();
            lock.lock();
            totalAtoms++;
            if(totalAtoms == 5){
                canBond.release(5);
            }
            lock.unlock();

            canBond.acquire();
            state.bond();

            lock.lock();
            totalAtoms--;
            if(totalAtoms == 0){
                state.validate();
                canExit.release(5);
            }
            lock.unlock();

            canExit.acquire();
            n.release();
        }

    }

    static CalciumNitrideState state = new CalciumNitrideState();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            run();
        }
    }

    public static void run() {
        try {
            Scanner s = new Scanner(System.in);
            int numRuns = 1;
            int numIterations = 100;
            s.close();

            HashSet<Thread> threads = new HashSet<Thread>();

            for (int i = 0; i < numIterations; i++) {
                Nitrogen n = new Nitrogen(numRuns);
                threads.add(n);
                Calcium ca = new Calcium(numRuns);
                threads.add(ca);
                ca = new Calcium(numRuns);
                threads.add(ca);
                n = new Nitrogen(numRuns);
                threads.add(n);
                ca = new Calcium(numRuns);
                threads.add(ca);
            }

            init();

            ProblemExecution.start(threads, state);
            System.out.println(new Date().getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

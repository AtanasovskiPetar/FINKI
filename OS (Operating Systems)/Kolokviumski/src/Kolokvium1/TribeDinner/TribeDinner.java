package Kolokvium1.TribeDinner;

import Kolokvium1.classes.ProblemExecution;
import Kolokvium1.classes.TemplateThread;

import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TribeDinner{
    static Semaphore table = new Semaphore(4);
    static Semaphore canEat = new Semaphore(0);
    static Semaphore cook = new Semaphore(0);
    static Semaphore filled = new Semaphore(0);
    static Lock lock = new ReentrantLock();
    public static void init() {

    }

    public static class TribeMember extends TemplateThread {

        public TribeMember(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {

            lock.lock();
            if(state.isPotEmpty()){
                cook.release();
                filled.acquire();
            }
            state.fillPlate();
            lock.unlock();

            table.acquire();
            state.eat();

//            finished
            table.release();

        }

    }

    public static class Chef extends TemplateThread {

        public Chef(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
            cook.acquire();
            state.cook();
            filled.release();
        }

    }

    static TribeDinnerState state = new TribeDinnerState();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            run();
        }
    }

    public static void run() {
        try {
            int numRuns = 1;
            int numIterations = 150;

            HashSet<Thread> threads = new HashSet<Thread>();

            for (int i = 0; i < numIterations; i++) {
                TribeMember h = new TribeMember(numRuns);
                threads.add(h);
            }

            Chef chef = new Chef(10);
            threads.add(chef);

            init();

            ProblemExecution.start(threads, state);
            System.out.println(new Date().getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

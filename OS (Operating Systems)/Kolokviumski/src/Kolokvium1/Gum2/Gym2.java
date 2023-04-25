package Kolokvium1.Gum2;

import Kolokvium1.classes.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Gym2 {
    static Semaphore sala;
    static Semaphore soblekuvalna;
    static Semaphore canPlay;
    static Lock lock = new ReentrantLock();
    static int counter;


    public static void init() {
        sala = new Semaphore(12);
        soblekuvalna = new Semaphore(4);
        canPlay = new Semaphore(0);
        counter = 0;
    }


    public static class Player extends TemplateThread {

        public Player(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
            sala.acquire();
            state.vlezi();

            lock.lock();
            counter++;
            if(counter == 12){
                canPlay.release(11);
            }
            lock.unlock();

            canPlay.acquire();
            state.sportuvaj();

            soblekuvalna.acquire();
            state.presobleci();
            soblekuvalna.release();

            lock.lock();
            counter --;
            if(counter == 0){
                state.slobodnaSala();
                sala.release(12);
            }
            lock.unlock();
        }

    }

    static Gym2State state = new Gym2State();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            run();
        }
    }

    public static void run() {
        try {
            Scanner s = new Scanner(System.in);
            int numRuns = 1;
            int numIterations = 1200;
            s.close();

            HashSet<Thread> threads = new HashSet<Thread>();

            for (int i = 0; i < numIterations; i++) {
                Player h = new Player(numRuns);
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

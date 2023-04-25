package Kolokvium1.Gym;

import Kolokvium1.classes.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class GymSolution {
    static Semaphore sala;
    static Semaphore soblekuvalna;
    static Semaphore startTraining;
    static Semaphore lock;
    static int count;
    public static void init() {
        sala = new Semaphore(12);
        soblekuvalna = new Semaphore(4);
        startTraining = new Semaphore(0);
        lock = new Semaphore(1);
        count = 0;
    }
    public static class Player extends TemplateThread {

        public Player(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
            sala.acquire();
            soblekuvalna.acquire();
            state.presobleci();
            soblekuvalna.release();

            lock.acquire();
            count++;
            if(count == 12){
                startTraining.release(12);
            }
            lock.release();
            startTraining.acquire();
            state.sportuvaj();

            lock.acquire();
            count -- ;
            if(count == 0){
                state.slobodnaSala();
                sala.release(12);
            }
            lock.release();
        }

    }

    static GymState state = new GymState();

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

            HashSet<Thread> threads = new HashSet<>();

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

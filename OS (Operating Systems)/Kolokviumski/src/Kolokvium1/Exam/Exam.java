package Kolokvium1.Exam;

import Kolokvium1.classes.ProblemExecution;
import Kolokvium1.classes.TemplateThread;

import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exam {
    static Semaphore teacher;
    static Semaphore students;
    static int studentsCounter;
    static Semaphore canStart;
    static Semaphore tLeave;
    static Semaphore sLeave;
    static Lock lock;
    public static void init() {
        teacher = new Semaphore(1);
        students = new Semaphore(0);
        studentsCounter = 0;
        canStart = new Semaphore(0);
        tLeave = new Semaphore(0);
        sLeave = new Semaphore(0);
        lock = new ReentrantLock();
    }

    public static class Teacher extends TemplateThread {

        public Teacher(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
            teacher.acquire();
            lock.lock();
            state.teacherEnter();
            students.release(50);
            lock.unlock();

            canStart.acquire();

            lock.lock();
            state.distributeTests();
            state.examEnd();
            sLeave.release(50);
            lock.unlock();

            tLeave.acquire();
            lock.lock();
            state.teacherLeave();
            teacher.release();
            lock.unlock();
        }
    }

    public static class Student extends TemplateThread {

        public Student(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
            students.acquire();
            lock.lock();
            state.studentEnter();
            studentsCounter++;
            if(studentsCounter == 50){
                canStart.release();
            }
            lock.unlock();

            sLeave.acquire();
            lock.lock();
            state.studentLeave();
            studentsCounter -- ;
            if(studentsCounter == 0){
                tLeave.release();
            }
            lock.unlock();
        }
    }

    static ExamState state = new ExamState();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            run();
        }
    }

    public static void run() {
        try {
            int numRuns = 1;
            int numScenarios = 1000;
            HashSet<Thread> threads = new HashSet<Thread>();

            for (int i = 0; i < numScenarios; i++) {
                Student p = new Student(numRuns);
                threads.add(p);
                if (i % 50 == 0) {
                    Teacher c = new Teacher(numRuns);
                    threads.add(c);
                }
            }

            init();

            ProblemExecution.start(threads, state);
            System.out.println(new Date().getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

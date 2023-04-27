package Zadaca2;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class OSMidterm {

    public static Semaphore [] semaphoreList = new Semaphore[10];
    public static Lock lock = new ReentrantLock();

    public static StringBuilder sb = new StringBuilder();

    //TODO: Initialize the semaphores you need
    public static void main(String[] args){

        //STARTING CODE, DON'T MAKE CHANGES
        //-----------------------------------------------------------------------------------------
        String final_text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";
        int m=10, n=100;
        Object[][] data = new Object[m][n];
        Random rand = new Random();
        int k=0;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                int random = rand.nextInt(100);
                if(random%2==0 & k<final_text.length()) {
                    data[i][j] = final_text.charAt(k);
                    k++;
                } else {
                    data[i][j] = rand.nextInt(100);
                }
            }
        }

        DataMatrix matrix = new DataMatrix(m,n, data);
        StatisticsResource statisticsResource = new StatisticsResource();
        //-----------------------------------------------------------------------------------------

        //ONLY TESTING CODE, SO YOU CAN TAKE A LOOK OF THE OUTPUT YOU NEED TO GET AT THE END
        //YOU CAN COMMENT OR DELETE IT AFTER YOU WRITE THE CODE USING THREADS
        //-----------------------------------------------------------------------------------------

        semaphoreList[semaphoreList.length-1] = new Semaphore(1);
        for (int i = 0; i < semaphoreList.length - 1; i++) {
            semaphoreList[i] = new Semaphore(0);
        }

        List<Concatenation> concatenationList = new ArrayList<>();
        for (int i = matrix.m-1; i >= 0; i--) {
            Object [][] currObject = new Object[1][matrix.n];
            for (int j = 0; j < matrix.n; j++) {
                currObject[0][j] = matrix.getEl(i, j);
            }
            DataMatrix currMatrix = new DataMatrix(1, matrix.n, currObject);
            concatenationList.add(new Concatenation(currMatrix, statisticsResource, i));

        }
        //TODO: Run the threads from the Concatenation class
        for (Concatenation concatenation : concatenationList){
            concatenation.start();
        }
        //TODO: Wait 10seconds for all threads to finish
        for (Concatenation concatenation : concatenationList){
            try {
                concatenation.join(10000);
            } catch (InterruptedException e) {
                System.out.println("Possible deadlock");
            }
        }

        //TODO: Print the string you get, call function printString()

        System.out.println(sb.toString());
        statisticsResource.printString();
        //TODO: Check if some thread is still alive, if so kill it and print "Possible deadlock"


    }

    // TODO: Make the Concatenation Class  a Thread Class
    static class Concatenation extends Thread{

        private DataMatrix row;
        private StatisticsResource statisticsResource;
        StringBuilder localStringBuilder = new StringBuilder();
        private int rowNum;

        public Concatenation(DataMatrix row, StatisticsResource statisticsResource, int rowNum) {
            this.row = row;
            this.statisticsResource = statisticsResource;
            this.rowNum = rowNum;
        }
        public void concatenate_by_row(){
            //TODO: Implement this function
            // add  arguments in the function if needed


            this.statisticsResource.concatenateString(localStringBuilder.toString());
            sb.append("Row "+rowNum+": "+localStringBuilder+"\n");

        }
        @Override
        public void run(){
            for (int i=0;i<this.row.getN();i++) {
                if (this.row.isString(0, i)) {
                    localStringBuilder.append(this.row.getEl(0,i));
                }
            }
            try {
                semaphoreList[rowNum].acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            execute();
            if (rowNum != 0){
                semaphoreList[rowNum - 1].release();
            }
        }
        public void execute(){
            //TODO: call the concatenate_by_row() function
            concatenate_by_row();
        }

    }

    //-------------------------------------------------------------------------
    //YOU ARE NOT CHANGING THE CODE BELOW
    static class DataMatrix {

        private int m,n;
        private Object[][] data;

        public DataMatrix(int m, int n, Object[][] data) {
            this.m = m;
            this.n = n;
            this.data = data;
        }

        public int getM() {
            return m;
        }

        public int getN() {
            return n;
        }

        public Object[][] getData() {
            return data;
        }

        public Object getEl(int i, int j) {
            return data[i][j];
        }

        public Object[] getRow(int pos) {
            return this.data[pos];
        }

        public Object[] getColumn(int pos) {
            Object[] result = new Object[m];
            for (int i=0;i<m;i++) {
                result[i]=data[i][pos];
            }
            return result;
        }

        public boolean isString(int i, int j) {
            return this.data[i][j] instanceof Character;
        }


    }

    static class StatisticsResource {

        private String concatenatedString;

        public StatisticsResource() {
            this.concatenatedString = "";
        }

        //function for String concatenation
        public void concatenateString(String new_character) {
            concatenatedString+=new_character;
        }

        //function for printing the concatenated string
        public void printString() {
            System.out.println("Here is the phrase from the matrix: " + concatenatedString);
        }

    }



}


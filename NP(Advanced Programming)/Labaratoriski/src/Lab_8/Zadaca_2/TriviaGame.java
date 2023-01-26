package Lab_8.Zadaca_2;

//package TriviaQuestion;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

abstract class TriviaQuestion{
    public String question;
    public String answer;
    public int value;
    public TriviaQuestion(String q, String a, int v) {
        this.question = q;
        this.answer = a;
        this.value = v;
    }
    public abstract String getType();
    public abstract void showQuestion();
}
class TrueFalseQuestion extends TriviaQuestion{

    public TrueFalseQuestion(String q, String a, int v) {
        super(q,a,v);
    }

    @Override
    public String getType() {
        return "trueFalse";
    }

    @Override
    public void showQuestion() {
        System.out.println(question);
        System.out.println("Enter 'T' for true or 'F' for false.");
    }
}
class FreeFormQuestion extends TriviaQuestion{
    public FreeFormQuestion(String q, String a, int v) {
        super(q,a,v);
    }
    @Override
    public String getType() {
        return "FreeForm";
    }

    @Override
    public void showQuestion() {
        System.out.println(question);
    }
}

class TriviaData {

    private ArrayList<TriviaQuestion> data;

    public TriviaData() {
        data = new ArrayList<>();
    }

    public void addQuestion(String q, String a, int v, int t) {
        if(t==0){
            data.add(new TrueFalseQuestion(q,a,v));
        }else{
            data.add(new FreeFormQuestion(q,a,v));
        }
    }

    public void showQuestion(int index) {
        TriviaQuestion q = data.get(index);
        System.out.println("Question " + (index + 1) + ".  " + q.value + " points.");
        data.get(index).showQuestion();
    }

    public int numQuestions() {
        return data.size();
    }

    public TriviaQuestion getQuestion(int index) {
        return data.get(index);
    }
}

public class TriviaGame {

    public TriviaData questions;	// Questions

    public TriviaGame() {
        // Load questions
        questions = new TriviaData();
        questions.addQuestion("The possession of more than two sets of chromosomes is termed?",
                "polyploidy", 3, 1);
        questions.addQuestion("Erling Kagge skiied into the north pole alone on January 7, 1993.",
                "F", 1, 0);
        questions.addQuestion("1997 British band that produced 'Tub Thumper'",
                "Chumbawumba", 2, 1);
        questions.addQuestion("I am the geometric figure most like a lost parrot",
                "polygon", 2, 1);
        questions.addQuestion("Generics were introducted to Java starting at version 5.0.",
                "T", 1, 0);
    }
    // Main game loop

    public static void main(String[] args) {
        int score = 0;			// Overall score
        int questionNum = 0;	// Which question we're asking
        TriviaGame game = new TriviaGame();
        Scanner keyboard = new Scanner(System.in);
        // Ask a question as long as we haven't asked them all
        while (questionNum < game.questions.numQuestions()) {
            // Show question
            game.questions.showQuestion(questionNum);
            // Get answer
            String answer = keyboard.nextLine();
            // Validate answer
            TriviaQuestion q = game.questions.getQuestion(questionNum);
            if (q.getType().equals("trueFalse")) {
                if (answer.charAt(0) == q.answer.charAt(0)) {
                    System.out.println("That is correct!  You get " + q.value + " points.");
                    score += q.value;
                } else {
                    System.out.println("Wrong, the correct answer is " + q.answer);
                }
            } else if (q.getType().equals("FreeForm")) {
                if (answer.equalsIgnoreCase(q.answer)) {
                    System.out.println("That is correct!  You get " + q.value + " points.");
                    score += q.value;
                } else {
                    System.out.println("Wrong, the correct answer is " + q.answer);
                }
            }
            System.out.println("Your score is " + score);
            questionNum++;
        }
        System.out.println("Game over!  Thanks for playing!");
    }
}


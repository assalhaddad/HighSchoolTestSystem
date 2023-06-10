package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private String id;
    private String text;
    private String[] answers;
    private int correct;
    private String subject;
    private int points;
    public String getId() {
        return id;
    }
    public Question(String id, String text, String[] answers, int correct, String subject, int points){
        this.id = id;
        this.text = text;
        this.answers = answers;
        this.correct = correct;
        this.subject = subject;
        this.points = points;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String id_question;
    private String text;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int correct;
    private String subject;
    private int points;
    public Question(){}

    public Question(String id, String text, String answer1,String answer2,String answer3,String answer4, int correct, String subject, int points){
        super();
        this.id_question = id;
        this.text = text;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correct = correct;
        this.subject = subject;
        this.points = points;
    }
    public String getId() {
        return id_question;
    }
    public void setId(String id) {
        this.id_question = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getId_question() {
        return id_question;
    }

    public void setId_question(String id_question) {
        this.id_question = id_question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
}

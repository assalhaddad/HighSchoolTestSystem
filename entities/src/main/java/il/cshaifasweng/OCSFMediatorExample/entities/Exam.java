package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String id_exam;
    private ArrayList<Question> questions;
    private int time;
    private String freeTextStudent;
    private String freeTextTeacher;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher author;
    private int moreTime;
    private ArrayList<Integer> correctAnswers;

    public Exam(String id_exam, ArrayList<Question> questions, int time, String freeTextStudent, String freeTextTeacher, Teacher author, int moreTime, ArrayList<Integer> correctAnswers) {
        this.id_exam = id_exam;
        this.questions = questions;
        this.time = time;
        this.freeTextStudent = freeTextStudent;
        this.freeTextTeacher = freeTextTeacher;
        this.author = author;
        this.moreTime = moreTime;
        this.correctAnswers = correctAnswers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_exam() {
        return id_exam;
    }

    public void setId_exam(String id_exam) {
        this.id_exam = id_exam;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getFreeTextStudent() {
        return freeTextStudent;
    }

    public void setFreeTextStudent(String freeTextStudent) {
        this.freeTextStudent = freeTextStudent;
    }

    public String getFreeTextTeacher() {
        return freeTextTeacher;
    }

    public void setFreeTextTeacher(String freeTextTeacher) {
        this.freeTextTeacher = freeTextTeacher;
    }

    public Teacher getAuthor() {
        return author;
    }

    public void setAuthor(Teacher author) {
        this.author = author;
    }

    public int getMoreTime() {
        return moreTime;
    }

    public void setMoreTime(int moreTime) {
        this.moreTime = moreTime;
    }

    public ArrayList<Integer> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(ArrayList<Integer> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}

package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exams")
public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String id_exam;
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity = Question.class
    )
    private List<Question> questions;
    private int time;
    private String freeTextStudent;
    private String freeTextTeacher;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher author;
    private int moreTime;

    public Exam(String id_exam, ArrayList<Question> questions, int time, String freeTextStudent, String freeTextTeacher, Teacher author) {
        this.id_exam = id_exam;
        this.questions = questions;
        this.time = time;
        this.freeTextStudent = freeTextStudent;
        this.freeTextTeacher = freeTextTeacher;
        this.author = author;
        this.moreTime = 0;
    }

    public Exam() {

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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
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
        if(author!=null) {
            this.author = author;
            author.getExams().add(this);
        }
    }
    public int getMoreTime() {
        return moreTime;
    }

    public void setMoreTime(int moreTime) {
        this.moreTime = moreTime;
    }


}

package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @OneToMany(fetch=FetchType.LAZY,mappedBy="course",cascade = CascadeType.ALL)
    private List<Exam> exams;
    @ManyToMany(mappedBy = "courses",
            //cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity = Question.class
    )
    private List<Question> questions;

    public Course(String name, Subject subject) {
        super();
        this.name = name;
        setSubject(subject);
        this.questions = new ArrayList<Question>();
        this.exams = new ArrayList<Exam>();
    }

    public Course() {

    }
    public void copy(Course c){
        this.id = c.getId();
        this.name = c.getName();
        this.questions = c.getQuestions();
        this.subject = c.getSubject();
        this.exams = c.getExams();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return this.subject;
    }

    public void setSubject(Subject subject) {
        if(subject!=null) {
            this.subject = subject;
            subject.getCourses().add(this);
        }
    }
    public List<Question> getQuestions() {
        return this.questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        for(Question question : questions){
            question.getCourses().add(this);
        }
    }

    public List<Exam> getExams() {
        return this.exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
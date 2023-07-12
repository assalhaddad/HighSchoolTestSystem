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
    @ManyToMany(mappedBy = "courses",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity = Question.class
    )
    private List<Question> questions;

    public Course(String name, Subject subject) {
        super();
        this.name = name;
        setSubject(subject);
        this.questions = new ArrayList<Question>();
    }

    public Course() {

    }
    public void copy(Course c){
        this.id = c.getId();
        this.name = c.getName();
        this.questions = c.getQuestions();
        this.subject = c.getSubject();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        if(subject!=null) {
            this.subject = subject;
            subject.getCourses().add(this);
        }
    }
    public List<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        for(Question question : questions){
            question.getCourses().add(this);
        }
    }
}

package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "subjects")
public class Subject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @OneToMany(fetch=FetchType.LAZY,mappedBy="subject",cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(fetch=FetchType.LAZY,mappedBy="subject",cascade = CascadeType.ALL)
    private List<Course> courses;

    @ManyToMany( mappedBy = "subjects",
           // cascade = { CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity = Teacher.class
    )
    private List<Teacher> teachers;

    public Subject(String name) {
        super();
        this.name = name;
        this.questions=new ArrayList<Question>();
        this.teachers = new ArrayList<Teacher>();
        this.courses = new ArrayList<Course>();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public Subject() {

    }
    public void copy(Subject s){
        this.id=s.getId();
        this.courses=s.getCourses();
        this.name=s.getName();
        this.questions=s.getQuestions();
        this.teachers=s.getTeachers();
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public List<Teacher> getTeachers() {
        return teachers;
    }
    public void setTeachers(List<Teacher> teachers) {
        if(teachers!=null) {
            this.teachers = teachers;
            for (Teacher teacher : teachers) {
                teacher.getSubjects().add(this);
            }
        }
    }
}

package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String username;
    private String password;

    @OneToMany(fetch=FetchType.LAZY,mappedBy="author")
    private List<Exam> exams;
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity = Subject.class
    )
    @JoinTable(
            name="subjects_teachers",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;


    public Teacher(){}

    public Teacher(String name ,String username, String password) {
        super();
        this.name = name;
        this.username = username;
        this.password = password;

        this.exams = new ArrayList<Exam>();
        this.subjects = new ArrayList<Subject>();
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Subject> getSubjects(){
        return this.subjects;
    }

    public void setSubjects(List<Subject> subjects){
        this.subjects = subjects;
    }
    public void addExam(Exam exam) {
        exams.add(exam);
    }

    public void removeExam(Exam exam) {
        exams.remove(exam);
    }

    public boolean containsExam(Exam exam) {
        return exams.contains(exam);
    }
    public void addSubject(Subject newSubject) {
        subjects.add(newSubject);
    }

    public void removeSubject(String subject) {
        subjects.remove(subject);
    }

    public boolean containsElement(String subject) {
        return subjects.contains(subject);
    }
}
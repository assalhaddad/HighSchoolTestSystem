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
    public void copy(Teacher t){
        this.id = t.getId();
        this.name = t.getName();
        this.password = t.getPassword();
        this.exams = t.getExams();
        this.username = t.getUsername();
        this.subjects = t.getSubjects();
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
        if(subjects!=null){
            this.subjects = subjects;
            for(Subject subject : subjects){
                subject.getTeachers().add(this);
            }
        }
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

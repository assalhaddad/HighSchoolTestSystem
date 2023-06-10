package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "teachers")

public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private String username;
    private String password;
    private ArrayList<String> subjects;
    private ArrayList<Exam> exams;

    public Teacher(){}

    public Teacher(String name ,String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.exams = new ArrayList<>();
        this.subjects = new ArrayList<>();
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

    public ArrayList<String> getSubjects(){
        return this.subjects;
    }

    public void setSubjects(ArrayList<String> subjects){
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
    public void addSubject(String newSubject) {
        subjects.add(newSubject);
    }

    public void removeSubject(String subject) {
        subjects.remove(subject);
    }

    public boolean containsElement(String subject) {
        return subjects.contains(subject);
    }
}

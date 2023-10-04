package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String id_student;
    private String name;
    private String username;
    private String password;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="student",cascade = CascadeType.ALL)
    private List<StudentData> data;

    public Student(){}

    public Student(String id_student, String name, String username, String password){
        super();
        this.id_student=id_student;
        this.name=name;
        this.password=password;
        this.username=username;
        this.data=new ArrayList<StudentData>();
    }

    public Student(Student s){
        this.id_student = s.getId_student();
        this.name = s.getName();
        this.username = s.getUsername();
        this.password = s.getPassword();
        this.data = s.getData();
    }

    public void copy(Student s){
        this.id = s.getId();
        this.id_student = s.getId_student();
        this.name = s.getName();
        this.username = s.getUsername();
        this.password = s.getPassword();
        this.data = s.getData();
    }

    public List<StudentData> getData() {
        return this.data;
    }

    public void setData(List<StudentData> data) {
        this.data = data;
    }

    public String getId_student() {
        return this.id_student;
    }

    public void setId_student(String id_student) {
        this.id_student = id_student;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

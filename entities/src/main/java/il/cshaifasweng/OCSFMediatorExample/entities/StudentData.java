package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "studentData")
public class StudentData implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "student_id")
   private Student student;
   private String name;
   private int timePassed;
   private boolean isFinished;
   private int grade;
   private String date;
   @ElementCollection
   private List<Integer> studentSolution;
   @ManyToOne
   @JoinColumn(name = "solvedExam_id")
   private SolvedExam solvedExam;

   public StudentData(Student student,String date ,int timePassed, boolean isFinished, List<Integer> studentSolution, SolvedExam solvedExam){
      super();
      setStudent(student);
      this.date=date;
      this.timePassed=timePassed;
      this.isFinished=isFinished;
      this.studentSolution= new ArrayList<Integer>(studentSolution);
      this.grade=0;
      setSolvedExam(solvedExam);
      this.name = student.getName();
   }

   public StudentData() {

   }

   public int getId() {
      return id;
   }

   public int getGrade() {
      return grade;
   }

   public void addToGrade(int x){
      this.grade+=x;
   }

   public void setGrade(int grade) {
      this.grade = grade;
   }

   public List<Integer> getStudentSolution() {
      return studentSolution;
   }

   public void setStudentSolution(List<Integer> studentSolution) {
      this.studentSolution = studentSolution;
   }

   public Student getStudent() {
      return student;
   }

   public void setStudent(Student student) {
      if(student!=null){
         this.student = student;
         student.getData().add(this);
      }
   }

   public SolvedExam getSolvedExam() {
      return solvedExam;
   }

   public void setSolvedExam(SolvedExam solvedExam) {
      if(solvedExam!=null){
         this.solvedExam = solvedExam;
         solvedExam.getData().add(this);
      }
      else System.out.println("Error!!!!!!!!!!");
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getTimePassed() {
      return timePassed;
   }

   public boolean isFinished() {
      return isFinished;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public void setTimePassed(int timePassed) {
      this.timePassed = timePassed;
   }

   public boolean getIsFinished() {
      return isFinished;
   }

   public void setFinished(boolean finished) {
      isFinished = finished;
   }

   public void copy(StudentData object) {
      this.id= object.getId();
      this.student=object.getStudent();
      this.date=object.getDate();
      this.timePassed= object.getTimePassed();
      this.isFinished=object.getIsFinished();
      this.studentSolution=object.getStudentSolution();
      this.solvedExam=object.getSolvedExam();
      this.name=object.getName();
      this.grade= object.getGrade();
   }
}
package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentData implements Serializable {
   private Student student;
   private int timePassed;
   private int end;
   private double grade;
   private ArrayList<Integer> studentSolution;

   public StudentData(Student student, int timePassed, int end, ArrayList<Integer> studentSolution){
      this.student=student;
      this.timePassed=timePassed;
      this.end=end;
      this.studentSolution=studentSolution;
      this.grade=0;
   }

   public double getGrade() {
      return grade;
   }

   public void addToGrade(int x){
      this.grade+=x;
   }

   public void setGrade(double grade) {
      this.grade = grade;
   }

   public ArrayList<Integer> getStudentSolution() {
      return studentSolution;
   }

   public void setStudentSolution(ArrayList<Integer> studentSolution) {
      this.studentSolution = studentSolution;
   }
}

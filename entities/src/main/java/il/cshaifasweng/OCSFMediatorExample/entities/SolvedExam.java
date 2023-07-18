package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class SolvedExam implements Serializable {
    private ArrayList<StudentData>data;
    private String date;
    private int updatedTime;
    Exam exam;
    public SolvedExam(){}
    public SolvedExam(ArrayList<StudentData> data, String date, int updatedTime, Exam exam){
        this.date = date;
        this.updatedTime = updatedTime;
        this.data=data;
        this.exam=exam;
        calculateGrades();
    }

    public void calculateGrades(){
        for(int i=0; i<this.data.size(); i++){
            for(int j=0; j<this.exam.getQuestions().size();j++){
                if(this.exam.getQuestions().get(j).getCorrect() == this.data.get(i).getStudentSolution().get(j))
                    this.data.get(i).addToGrade(this.exam.getQuestions().get(j).getPoints());
            }
        }
    }
    public ArrayList<StudentData> getData() {
        return data;
    }

    public void setData(ArrayList<StudentData> data) {
        this.data = data;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(int updatedTime) {
        this.updatedTime = updatedTime;
    }
}

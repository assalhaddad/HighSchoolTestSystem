package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "solvedExam")
public class SolvedExam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany (mappedBy = "solvedExam")
    private List<StudentData> data;
    private String date;
    private int updatedTime;
    @OneToOne
    @JoinColumn(name="exam_id")
    private Exam exam;
    public SolvedExam(){}
    public SolvedExam(String date, int updatedTime, Exam exam){
        super();
        this.date = date;
        this.updatedTime = updatedTime;
        this.data=new ArrayList();
        this.exam=exam;
    }

    public void calculateGrades(){
        for(int i=0; i<this.data.size(); i++){
            if(this.data.get(i).getGrade()==0) {
                for (int j = 0; j < this.exam.getQuestions().size(); j++) {
                    //System.out.println("answer for ques "+j+": "+this.data.get(i).getStudentSolution().get(j));
                    //System.out.println("the correct answer is: "+this.exam.getQuestions().get(j).getCorrect());
                    if (this.exam.getQuestions().get(j).getCorrect() == this.data.get(i).getStudentSolution().get(j)) {
                        //System.out.println("adding "+this.exam.getQuestions().get(j).getPoints()+" for question num "+j);
                        this.data.get(i).addToGrade(this.exam.getQuestions().get(j).getPoints());
                    }
                }
            }
        }
    }
    public List<StudentData> getData() {
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

package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "solvedExam")
public class SolvedExam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany (mappedBy = "solvedExam",cascade = CascadeType.ALL)
    private List<StudentData> data;

    private int updatedTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="exam_id")
    private Exam exam;

    private boolean isBuild=false;
    public SolvedExam()
    {
        //super();
        this.exam=new Exam();
    }
    public SolvedExam( int updatedTime, Exam exam){
        super();
        this.updatedTime = updatedTime;
        this.isBuild=true;
        this.data=new ArrayList();
        setExam(exam);
    }

    public void calculateGrades(){
        for(int i=0; i<this.data.size(); i++){
            if(this.data.get(i).getGrade()==0) {
                for (int j = 0; j < this.exam.getQuestions().size(); j++) {
                    if (this.exam.getQuestions().get(j).getCorrect() == this.data.get(i).getStudentSolution().get(j)) {
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
        if(exam!=null) {
            this.exam = exam;
            exam.setSolvedExam(this);
        }
    }



    public int getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(int updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setData(List<StudentData> data) {
        this.data = data;
    }

    public boolean GetIsBuild() {
        return isBuild;
    }

    public void setBuild(boolean build) {
        isBuild = build;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void copy(SolvedExam solvedExam) {
        this.exam=solvedExam.getExam();
        this.id = solvedExam.getId();
        //this.exam.copy(solvedExam.getExam());
        this.data = solvedExam.getData();
        this.updatedTime=solvedExam.getUpdatedTime();
        this.isBuild= solvedExam.GetIsBuild();
    }

}
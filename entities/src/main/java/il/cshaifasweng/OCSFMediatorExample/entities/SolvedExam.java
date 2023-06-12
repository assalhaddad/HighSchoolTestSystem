package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class SolvedExam implements Serializable {
    private String id;
    private ArrayList<StudentData>data;
    private String date;
    private int updatedTime;
    public SolvedExam(){}
    public SolvedExam(String id, ArrayList<StudentData> data, String date, int updatedTime){
        this.id = id;
        this.date = date;
        this.updatedTime = updatedTime;
        int i=0;
        while(data != null)
            (this.data).add(data.get(i));
    }

    public String getId() {
        return id;
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

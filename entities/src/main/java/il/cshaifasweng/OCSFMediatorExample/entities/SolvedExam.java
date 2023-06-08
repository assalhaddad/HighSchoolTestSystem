package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class SolvedExam implements Serializable {
    private String id;
    private ArrayList<StudentData>data;
    private String date;
    private int updatedTime;
}

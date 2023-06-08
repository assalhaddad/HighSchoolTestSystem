package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Exam implements Serializable {
    private String id;
    private ArrayList<Question> questions;
    private int time;
    private String freeTextStudent;
    private String freeTextTeacher;
    private String author;
    private int moreTime;
    private ArrayList<Integer> correctAnswers;
}

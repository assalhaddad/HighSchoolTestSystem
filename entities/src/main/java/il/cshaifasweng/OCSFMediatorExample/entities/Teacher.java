package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Teacher implements Serializable {
    private String username;
    private String password;
    private ArrayList<String> subjects;
    private ArrayList<Exam> exams;

}

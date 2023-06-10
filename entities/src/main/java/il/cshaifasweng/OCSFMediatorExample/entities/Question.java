package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private String id;
    private String text;
    private String[] answers;
    private int correct;
    private String subject;
    private int points;

    public Question(){}
    public Question(String id, String text,String[] answers, int correct, String subject, int points){
        this.id = id;
        this.text = text;
        this.answers = new String[4];
        for(int i = 0; i< 4; i++)
            (this.answers)[i] = answers[i];
        this.correct = correct;
        this.subject = subject;
        this.points = points;
    }
}

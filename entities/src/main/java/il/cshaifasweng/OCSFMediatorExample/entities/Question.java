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
}

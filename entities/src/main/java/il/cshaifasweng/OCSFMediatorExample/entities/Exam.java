package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exams")
public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String id_exam;
    @ManyToMany(mappedBy = "exams",
           // cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity = Question.class
    )
    private List<Question> questions;
    private int time;
    private String freeTextStudent="";
    private String freeTextTeacher="";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
    private int moreTime;
    @OneToOne(mappedBy = "exam")
    private SolvedExam solvedExam;
    private String code4Digits;




    public Exam(String id_exam, ArrayList<Question> questions, int time, String freeTextStudent, String freeTextTeacher, Teacher author, Course course,String code4Digits) {
        super();
        setCourse(course);
        setAuthor(author);
        setId_exam(id_exam);
        //this.questions = new ArrayList<Question>(questions);
        this.questions = new ArrayList();
        for(Question question : questions){
            Question temp = new Question();
            temp.copy(question);
            this.questions.add(temp);
        }
        for(int i = 0; i<questions.size(); i++)
            this.questions.get(i).getExams().add(this);
        this.time = time;
        this.freeTextStudent = freeTextStudent;
        this.freeTextTeacher = freeTextTeacher;
        this.moreTime = 0;
        this.solvedExam = new SolvedExam();
        this.code4Digits=code4Digits;
    }

    public Exam() {

    }
    public void copy(Exam e){
        this.id = e.getId();
        this.id_exam = e.getId_exam();
        this.author = e.getAuthor();
        this.questions =e.getQuestions();
        this.time = e.getTime();
        this.course = e.getCourse();
        this.moreTime = e.getMoreTime();
        this.freeTextStudent = e.getFreeTextStudent();
        this.freeTextTeacher = e.getFreeTextTeacher();
        this.solvedExam = e.getSolvedExam();
        this.code4Digits=e.getCode4Digits();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_exam() {
        return id_exam;
    }

    public SolvedExam getSolvedExam() {
        return this.solvedExam;
    }

    public void setSolvedExam(SolvedExam solvedExam) {
        this.solvedExam = solvedExam;
    }

    public void setId_exam(String id_exam) {
        this.id_exam=id_exam;
        if(this.course.getName().equals("Basic Math"))
            this.id_exam+="0101";
        if(this.course.getName().equals("Advanced Math"))
            this.id_exam+="0201";
        if(this.course.getName().equals("Basic English"))
            this.id_exam+="0302";
        if(this.course.getName().equals("Advanced English"))
            this.id_exam+="0402";
        if(this.course.getName().equals("Basic Science"))
            this.id_exam+="0503";
        if(this.course.getName().equals("Advanced Science"))
            this.id_exam+="0603";
        if(this.course.getName().equals("Basic Geography"))
            this.id_exam+="0704";
        if(this.course.getName().equals("Advanced Geography"))
            this.id_exam+="0804";
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public void setPoints(Question q, int points){
        q.setPoints(points);
    }
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getFreeTextStudent() {
        return this.freeTextStudent;
    }

    public void setFreeTextStudent(String freeTextStudent) {
        this.freeTextStudent = freeTextStudent;
    }

    public String getFreeTextTeacher() {
        return this.freeTextTeacher;
    }

    public void setFreeTextTeacher(String freeTextTeacher) {
        this.freeTextTeacher = freeTextTeacher;
    }

    public Teacher getAuthor() {
        return this.author;
    }
    public void setAuthor(Teacher author) {
        if(author!=null) {
            this.author = author;
            author.getExams().add(this);
        }
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        if(course!=null) {
            this.course = course;
            course.getExams().add(this);
        }
    }

    public int getMoreTime() {
        return this.moreTime;
    }

    public void setMoreTime(int moreTime) {
        this.moreTime = moreTime;
    }

    public String getCode4Digits() {
        return this.code4Digits;
    }

    public void setCode4Digits(String code4Digits) {
        this.code4Digits = code4Digits;
    }


}
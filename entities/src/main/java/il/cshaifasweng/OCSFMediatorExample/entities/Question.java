package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String id_question;
    private String text;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int correct;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToMany(
            //cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity = Course.class
    )
    @JoinTable(
            name="courses_questions",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
    @ManyToMany(
            //cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity = Exam.class
    )
    @JoinTable(
            name="exams_questions",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private List<Exam> exams;

    private int points;

    public Question(String id, String text, String answer1,String answer2,String answer3,String answer4, int correct, Subject subject, List<Course> courses){
        super();
        this.id_question = id;
        this.text = text;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correct = correct;
        this.points = 0;
        setSubject(subject);
        setCourses(courses);
        this.exams = new ArrayList();
    }
    public void copy(Question q){
        this.id = q.getId();
        this.id_question = q.getId_question();
        this.text = q.getText();
        this.answer1 = q.getAnswer1();
        this.answer2 = q.getAnswer2();
        this.answer3 = q.getAnswer3();
        this.answer4 = q.getAnswer4();
        this.correct = q.getCorrect();
        this.points = q.getPoints();
        this.subject = q.getSubject();
        this.courses = q.getCourses();
        this.exams = q.getExams();
        //setSubject(q.getSubject());
        //setCourses(q.getCourses());
    }
    public Question() {

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_question() {
        return id_question;
    }

    public void setId_question(String id_question) {
        this.id_question = id_question;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer1() {
        return this.answer1;
    }


    public String getAnswer2() {
        return this.answer2;
    }


    public String getAnswer3() {
        return this.answer3;
    }


    public String getAnswer4() {
        return this.answer4;
    }

    public int getCorrect() {
        return this.correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public Subject getSubject() {
        return this.subject;
    }

    public void setSubject(Subject subject) {
        if(subject!=null) {
            this.subject = subject;
            subject.getQuestions().add(this);
        }
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(List<Course> courses) {
        if(courses!=null){
            this.courses = new ArrayList();
            for(Course course : courses){
                Course temp = new Course();
                temp.copy(course);
                this.courses.add(temp);
                course.getQuestions().add(this);
            }
        }
    }

    public List<Exam> getExams() {
        return this.exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}

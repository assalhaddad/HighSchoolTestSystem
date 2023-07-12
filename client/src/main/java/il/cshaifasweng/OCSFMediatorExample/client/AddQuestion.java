package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.CheckComboBox;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;

public class AddQuestion {

    @FXML // fx:id="correctTF"
    private TextField correctTF; // Value injected by FXMLLoader

    @FXML // fx:id="firstTF"
    private TextField firstTF; // Value injected by FXMLLoader

    @FXML // fx:id="fourthTF"
    private TextField fourthTF; // Value injected by FXMLLoader

    @FXML // fx:id="idTF"
    private TextField idTF; // Value injected by FXMLLoader

    @FXML // fx:id="questionTF"
    private TextField questionTF; // Value injected by FXMLLoader

    @FXML // fx:id="secondTF"
    private TextField secondTF; // Value injected by FXMLLoader

    @FXML // fx:id="subjectCMB"
    private ComboBox<String> subjectCMB; // Value injected by FXMLLoader

    @FXML
    private CheckComboBox<String> courseCMB;

    @FXML // fx:id="subjectTF1"
    private Label subjectTF1; // Value injected by FXMLLoader

    @FXML // fx:id="thirdTF"
    private TextField thirdTF; // Value injected by FXMLLoader
    @FXML
    private Button doneBTN;

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        sendMessage("get list of subjects", (Object)null);
        courseCMB.setDisable(true);
    }

    private void sendMessage(String op, Object obj) {
        try {
            Message message = new Message(op, obj);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    String id;
    Subject subject;
    String text;
    int points;
    int correct;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    Subject chosenSubject = new Subject();
    ArrayList<Course> chosenCourses = new ArrayList();
    @FXML
    void selectSubject(ActionEvent event) {
        String subject = this.subjectCMB.getValue().toString();
        courseCMB.getItems().clear();
        sendMessage("get subject", subject);
    }

    @FXML
    void selectCourse(ActionEvent event) {
        ObservableList<String> list = courseCMB.getCheckModel().getCheckedItems();
        int i = 0;
        for(Object obj : list){
            sendMessage("get course",list.get(i).toString());
            i++;
        }
    }

    @FXML
    void done(ActionEvent event) {
        answer1 = firstTF.getText();
        answer2 = secondTF.getText();
        answer3 = thirdTF.getText();
        answer4 = fourthTF.getText();
        correct = Integer.parseInt(correctTF.getText());
        setID();
        text = questionTF.getText();
        subject = chosenSubject;
        Question newQuestion = new Question(id,text,answer1,answer2,answer3,answer4,correct,subject,chosenCourses);
        sendMessage("new question",newQuestion);
    }

    void setID(){
        id = idTF.getText();
        if(chosenSubject.getName().equals("Math"))
            id+="01";
        if(chosenSubject.getName().equals("English"))
            id+="02";
        if(chosenSubject.getName().equals("Science"))
            id+="03";
        if(chosenSubject.getName().equals("Geography"))
            id+="04";
    }

    @Subscribe
    public void handleMessage(Message message){
        String request = message.getMessage();
        Object obj = message.getObject();
        if(request.equals("subjects list is ready"))
            getSubjectsRequest(obj);
        else if(request.equals("courses list is ready"))
            getCoursesRequest(obj);
        else if(request.equals("question added successfully"))
            addedNewQuestion();
        else if(request.equals("found subject"))
            getChosenSubjectRequest(obj);
        else if(request.equals("found course")){
            getChosenCourseRequest(obj);
        }
    }

    private void getChosenSubjectRequest(Object obj){
        chosenSubject.copy((Subject) obj);
        sendMessage("get list of courses", chosenSubject.getName());
    }

    private void getChosenCourseRequest(Object obj){
        chosenCourses.add((Course) obj);
    }

    private void getSubjectsRequest(Object obj){
        ObservableList<String> subjectList = FXCollections.observableArrayList((ArrayList)obj);
        subjectCMB.setItems(subjectList);
    }
    private void getCoursesRequest(Object obj){
        courseCMB.setDisable(false);
        ObservableList<String> courseList = FXCollections.observableArrayList((ArrayList)obj);
        courseCMB.getItems().addAll(courseList);
    }
    private void addedNewQuestion(){
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Question added successfully");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
        subjectCMB.getSelectionModel().clearSelection();
        courseCMB.getItems().clear();
        courseCMB.setDisable(true);
        idTF.clear();
        questionTF.clear();
        firstTF.clear();
        secondTF.clear();
        thirdTF.clear();
        fourthTF.clear();
        correctTF.clear();
    }
}
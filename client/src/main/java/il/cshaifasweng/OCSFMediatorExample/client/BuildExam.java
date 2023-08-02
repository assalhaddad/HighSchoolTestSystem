package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;

public class BuildExam {

    @FXML
    private Button addBTN;
    @FXML
    private Button doneBTN;
    @FXML
    private TextField pointsTF;
    @FXML
    private TextField idTF;

    @FXML
    private ListView<String> questionsList;

    @FXML
    private ComboBox<String> subjectCMB;
    @FXML
    private ComboBox<String> courseCMB;

    @FXML
    private TextField timeTF;

    @FXML
    private TextField totalTF;

    @FXML
    void done(ActionEvent event) {
        if(totalPoints!=0)
            pointsError();
        else if(idTF.getText().isEmpty()||timeTF.getText().isEmpty())
            missingInfo();
        else {
            Exam newExam = new Exam(idTF.getText(),questions,Integer.parseInt(timeTF.getText()),"","",chosenTeacher,chosenCourse);
            System.out.println("exam object has been built");
            questions.clear();
            sendMessage("new exam",newExam);
        }
    }

    @FXML
    void selectSubject(ActionEvent event) {
        String subject = this.subjectCMB.getValue().toString();
        courseCMB.getItems().clear();
        questionsList.getItems().clear();
        pointsTF.setVisible(false);
        addBTN.setVisible(false);
        sendMessage("get subject for build exam", subject);
    }

    @FXML
    void selectCourse(ActionEvent event) {
        String course = this.courseCMB.getValue().toString();
        sendMessage("get course for build exam", course);
    }

    @FXML
    void setID(ActionEvent event) {

    }

    @FXML
    void setTime(ActionEvent event) {

    }

    @FXML
    void addQuestion(ActionEvent event) {
        if(pointsTF.getText().isEmpty()||Integer.parseInt(pointsTF.getText())<=0)
            pointsError();
        else{
            totalPoints-= Integer.parseInt(pointsTF.getText().toString());
            if(totalPoints<0){
                totalPoints+= Integer.parseInt(pointsTF.getText().toString());
                pointsError();
            }
            else
                addedNewQuestion();
        }
        pointsTF.clear();
    }

    @FXML
    void points(ActionEvent event) {

    }

    @FXML
    void selectQuestion(MouseEvent event) {
        String string = questionsList.getSelectionModel().getSelectedItem().toString();
        if(checkDuplicates(string)==true)
            duplicateError();
        else {
            pointsTF.setVisible(true);
            addBTN.setVisible(true);
            sendMessage("get question", string);
        }
    }

    @FXML
    void clearPressed(ActionEvent event) {
        clear();
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        courseCMB.setDisable(true);
        totalTF.setText(String.valueOf(totalPoints));
        sendMessage("get list of subjects for build exam", (Object)null);
        pointsTF.setVisible(false);
        addBTN.setVisible(false);
        chosenTeacher.copy(Login.teacher);
        //sendMessage("get teacher for build exam", (Object) null);
    }

    Subject chosenSubject = new Subject();
    Course chosenCourse = new Course();
    Question chosenQuestion = new Question();
    Teacher chosenTeacher = new Teacher();
    int totalPoints = 100;
    ArrayList<Question> questions= new ArrayList();

    private void sendMessage(String op, Object obj) {
        try {
            Message message = new Message(op, obj);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    @Subscribe
    public void handleMessage(Message message){
        String request = message.getMessage();
        Object obj = message.getObject();
        if(request.equals("subjects list is ready for build exam"))
            getSubjectsRequest(obj);
        else if(request.equals("found subject for build exam"))
            getChosenSubjectRequest(obj);
        else if(request.equals("courses list is ready for build exam"))
            getCoursesRequest(obj);
        else if(request.equals("found course for build exam"))
            getChosenCourseRequest(obj);
        else if(request.equals("found question"))
            getChosenQuestionRequest(obj);
        else if(request.equals("exam added successfully"))
            buildExam();
        //else if(request.equals("found teacher for build exam"))
            //getTeacherRequest(obj);

    }
    private void getTeacherRequest(Object obj){
        chosenTeacher.copy((Teacher) obj);
    }
    private void getChosenQuestionRequest(Object obj){
        chosenQuestion.copy((Question) obj);
    }

    private void getChosenCourseRequest(Object obj){
        chosenCourse.copy((Course) obj);
        ArrayList<String> list = new ArrayList();
        for(int i = 0; i<chosenCourse.getQuestions().size(); i++)
            list.add(chosenCourse.getQuestions().get(i).getText());
        questionsList.getItems().setAll(list);
    }
    private void getChosenSubjectRequest(Object obj){
        chosenSubject.copy((Subject) obj);
        sendMessage("get list of courses for build exam", chosenSubject.getName());
    }

    private void getCoursesRequest(Object obj){
        courseCMB.setDisable(false);
        ObservableList<String> courseList = FXCollections.observableArrayList((ArrayList)obj);
        courseCMB.setItems(courseList);
        System.out.println("setting courses in build exam");
    }

    private void getSubjectsRequest(Object obj){
        ObservableList<String> subjectList = FXCollections.observableArrayList((ArrayList)obj);
        subjectCMB.setItems(subjectList);
    }

    private void missingInfo(){
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("Some information is missing");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
    }

    private boolean checkDuplicates(String s){
        for(int i=0; i<questions.size();i++){
            if(questions.get(i).getText().equals(s))
                return true;
        }
        return false;
    }

    private void clear(){
        totalPoints = 100;
        totalTF.setText(String.valueOf(totalPoints));
        subjectCMB.getSelectionModel().clearSelection();
        courseCMB.getItems().clear();
        courseCMB.setDisable(true);
        idTF.clear();
        timeTF.clear();
        pointsTF.setVisible(false);
        addBTN.setVisible(false);
        questionsList.getItems().clear();
        questions.clear();
    }

    private void duplicateError(){
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("This question has already been added");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
    }
    private void pointsError(){
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("Invalid number of points\nPoints left: "+totalPoints);
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
    }

    private void buildExam(){
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Exam added successfully");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
        clear();
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
        Question temp = new Question();
        temp.copy(chosenQuestion);
        temp.setPoints(Integer.parseInt(pointsTF.getText()));
        //System.out.println("Adding "+temp.getText() +" from course: " + temp.getCourses().get(0).getName());
        questions.add(temp);
        pointsTF.setVisible(false);
        addBTN.setVisible(false);
        totalTF.setText(String.valueOf(totalPoints));
    }

}
package il.cshaifasweng.OCSFMediatorExample.client;

import com.sun.javafx.collections.ObservableListWrapper;
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
    private TextField freeText1;

    @FXML
    private TextField freeText2;

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
    private TextField digitcode;

    @FXML
    void done(ActionEvent event) {
        if(totalPoints!=0)
            pointsError();
        else if(idTF.getText().isEmpty()||timeTF.getText().isEmpty()||digitcode.getText().isEmpty())
            missingInfo();
        else
        {
            String temp=idTF.getText();
            temp = setId_exam(temp);
            System.out.println(temp);
            sendMessage("check exam id",temp);
        }
    }

    @FXML
    void selectSubject(ActionEvent event) {
        if(subjectCMB.getValue()!=null) {
            String subject = this.subjectCMB.getValue().toString();
            //courseCMB.getItems().clear();
            //questionsList.getItems().clear();
            temp.clear();
            pointsTF.setVisible(false);
            addBTN.setVisible(false);
            sendMessage("get subject for build exam", subject);
        }
    }

    @FXML
    void selectCourse(ActionEvent event) {
        if(courseCMB.getValue()!=null) {
            String course = this.courseCMB.getValue().toString();
            sendMessage("get course for build exam", course);
        }
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
        clear1();
    }

    ObservableList<String> temp = FXCollections.observableArrayList();
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        courseCMB.setDisable(true);
        totalTF.setText(String.valueOf(totalPoints));
        sendMessage("get list of subjects for build exam", (Object)null);
        pointsTF.setVisible(false);
        addBTN.setVisible(false);
        chosenTeacher.copy(Login.teacher);
        questionsList.setItems(temp);
        courseCMB.setItems(courseList);
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
        else if(request.equals("found duplicate exam id"))
            duplicateIDError();

        else if(request.equals("didnt find duplicate exam id"))
            sendMessage("get list of codes 2.0",digitcode.getText());
        else if(request.equals("found exam 2.0"))
            duplicate4DigitsError();
        else if(request.equals("didn't find exam"))
        {
            String free1 = "", free2 = "";
            if(!freeText1.getText().isEmpty())
                free1 = freeText1.getText();
            if(!freeText2.getText().isEmpty())
                free2 = freeText2.getText();
            Exam newExam = new Exam(idTF.getText(),questions,Integer.parseInt(timeTF.getText()),free2,free1,chosenTeacher,chosenCourse,digitcode.getText());
            System.out.println("exam object has been built");
            questions.clear();
            sendMessage("new exam",newExam);
        }
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
        Platform.runLater(() -> {
            temp.setAll(list);
        });
        //questionsList.getItems().setAll(list);
        //questionsList.setItems(temp);
    }
    private void getChosenSubjectRequest(Object obj){
        chosenSubject.copy((Subject) obj);
        sendMessage("get list of courses for build exam", chosenSubject.getName());
    }

    ObservableList<String> courseList = FXCollections.observableArrayList();
    private void getCoursesRequest(Object obj){
        courseCMB.setDisable(false);
        //ObservableList<String> courseList = FXCollections.observableArrayList((ArrayList)obj);
        Platform.runLater(() -> {
                courseList.setAll((ArrayList) obj);
                });
        //courseCMB.setItems(courseList);
        System.out.println("setting courses in build exam");
    }

    private void getSubjectsRequest(Object obj){
        ObservableList<String> subjectList = FXCollections.observableArrayList((ArrayList)obj);
        subjectCMB.setItems(subjectList);
    }

    public String setId_exam(String id_exam) {
        if(chosenCourse.getName().equals("Basic Math"))
            id_exam+="0101";
        if(chosenCourse.getName().equals("Advanced Math"))
            id_exam+="0201";
        if(chosenCourse.getName().equals("Basic English"))
            id_exam+="0302";
        if(chosenCourse.getName().equals("Advanced English"))
            id_exam+="0402";
        if(chosenCourse.getName().equals("Basic Science"))
            id_exam+="0503";
        if(chosenCourse.getName().equals("Advanced Science"))
            id_exam+="0603";
        if(chosenCourse.getName().equals("Basic Geography"))
            id_exam+="0704";
        if(chosenCourse.getName().equals("Advanced Geography"))
            id_exam+="0804";
        return id_exam;
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



    private void clear1(){
        totalPoints = 100;
        totalTF.setText(String.valueOf(totalPoints));
        Platform.runLater(() -> {
            subjectCMB.getSelectionModel().clearSelection();
        });
        Platform.runLater(() -> {
            courseCMB.getSelectionModel().clearSelection();
        });
        //subjectCMB.getSelectionModel().select(-1);
        //courseCMB.getSelectionModel().select(-1);
        courseCMB.setDisable(true);
        idTF.clear();
        timeTF.clear();
        digitcode.clear();
        pointsTF.setVisible(false);
        addBTN.setVisible(false);
        Platform.runLater(() -> {
            temp.clear(); // Clear the ObservableList using clear()
        });
        questions.clear();
        freeText1.clear();
        freeText2.clear();
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

    private void duplicateIDError(){
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("This ID has already been used!");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
    }
    private void duplicate4DigitsError(){
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("This 4 digit code has already been used!");
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
        clear1();
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
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
import javafx.scene.layout.VBox;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.switchScreen;

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
    private VBox Menu;

    @FXML
    private Button addQuestionBtn;

    @FXML
    private Button buildExamBtn;

    @FXML
    private Button checkGradesBtn;
    @FXML
    private Button logOutBtn;

    @FXML
    private Button menuBtn;

    @FXML
    private Button requestTimeBtn;

    @FXML
    void done(ActionEvent event) {
        try {
            if (totalPoints != 0) {
                pointsError();
                return;
            }
            else if (idTF.getText().isEmpty() || timeTF.getText().isEmpty() || digitcode.getText().isEmpty()) {
                missingInfo();
                return;
            }
            else if (idTF.getText().length() != 2) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("the exam's id's length is 2" + "\n" + "Please enter a new id");
                        alert.setContentText(null);
                        alert.showAndWait();
                    }
                });
                idTF.clear();
                return;
            }
            else if (Integer.parseInt(timeTF.getText()) > 180) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("Exam can be only 3 hours long" + "\n" + "Please enter a new time");
                        alert.setContentText(null);
                        alert.showAndWait();
                    }
                });
                timeTF.clear();
                return;
            }
            else if (Integer.parseInt(timeTF.getText()) <= 0) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("Exam's time should be a positive number" + "\n" + "Please enter a new time");
                        alert.setContentText(null);
                        alert.showAndWait();
                    }
                });
                timeTF.clear();
                return;
            }
        }
        catch (NumberFormatException e) {
            illegalTime();
            return;
        }
        try {
            if (digitcode.getText().length() != 4) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("The code can only be 4 digits" + "\n" + "Please enter a new code");
                        alert.setContentText(null);
                        alert.showAndWait();
                    }
                });
                digitcode.clear();

            } else {
                String send="";
                if(idTF.getText().equals("00")||idTF.getText().equals("01")||idTF.getText().equals("02")||idTF.getText().equals("03")||idTF.getText().equals("04")||idTF.getText().equals("05")||idTF.getText().equals("06")||idTF.getText().equals("07")||idTF.getText().equals("08")||idTF.getText().equals("09"))
                    send="0";
                String temp = String.valueOf(Integer.parseInt(idTF.getText()));
                send+=temp;

                send = setId_exam(send);
                if (Integer.parseInt(send) < 0) {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Error");
                            alert.setHeaderText("The exam ID should be 2 POSITIVE digits" + "\n" + "Please enter a new time");
                            alert.setContentText(null);
                            alert.showAndWait();
                        }
                    });
                    idTF.clear();
                    return;
                }
                System.out.println(send);
                sendMessage("check exam id", send);
            }
        }
        catch (NumberFormatException e) {
            illegalID();
        }

    }

    private void illegalID() {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("the ID can only be an Integer number" + "\n" + "Please enter a new time");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
        idTF.clear();
    }

    private void illegalTime() {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Time can only be an Integer number" + "\n" + "Please enter a new time");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
        timeTF.clear();
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
        sendMessage("get list of subjects for build exam", Login.teacher);
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
        else if(request.equals("didn't find exam 2.0"))
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
        idTF.clear();
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
        digitcode.clear();
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

    @FXML
    void OpenMenu(ActionEvent event) {
        menuBtn.setVisible(false);
        Menu.setVisible(true);
    }
    @FXML
    void AddQuestionEvent(ActionEvent event) throws IOException {
        EventBus.getDefault().unregister(this);
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        App.setRoot("addQuestion");
    }

    @FXML
    void CheckGradesEvent(ActionEvent event) throws IOException {
        EventBus.getDefault().unregister(this);
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        App.setRoot("checkGradesTeacher");
    }

    @FXML
    void CreateExamEvent(ActionEvent event) throws IOException {
        EventBus.getDefault().unregister(this);
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        App.setRoot("buildExam");
    }

    @FXML
    void RequestTimeEvent(ActionEvent event) throws IOException {
        EventBus.getDefault().unregister(this);
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        App.setRoot("requestExtraTime");
    }
    @FXML
    void LogOut(ActionEvent event) {
        EventBus.getDefault().unregister(this);
        switchScreen("Login");}

}
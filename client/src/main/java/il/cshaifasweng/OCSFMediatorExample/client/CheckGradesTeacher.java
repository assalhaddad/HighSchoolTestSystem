package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class CheckGradesTeacher {

    @FXML
    private Button submitBTN;

    @FXML
    private Label changeLBL;

    @FXML
    private Label explainLBL;

    @FXML
    private TextField explainTF;

    @FXML
    private Rectangle rectangle;

    @FXML
    private TextField changeTF;

    @FXML
    private TextField courseTF;

    @FXML
    private ComboBox<String> idCMB;

    @FXML
    private TextField subjectTF;
    @FXML
    private TextField freeText;
    @FXML
    private TableView<StudentData> tableView;
    @FXML
    private TableColumn<StudentData, Integer> gradeCol;
    @FXML
    private TableColumn<StudentData, String> nameCol;
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        currentTeacher.copy(Login.teacher);
        getTeacherRequest();
        //sendMessage("get teacher", (Object)null);
        hide();
        freeText.setVisible(false);
    }

    @FXML
    void chooseExam(ActionEvent event) {
        String exam = idCMB.getValue().toString();
        freeText.setVisible(false);
        sendMessage("get exam", exam);
    }

    @FXML
    void changeGrade(ActionEvent event) {
        if(changeTF.getText().isEmpty()||explainTF.getText().isEmpty())
            missingInfo();
        else if(Integer.parseInt(changeTF.getText()) > 100 || Integer.parseInt(changeTF.getText()) < 0)
            invalidError();
        else updateGrade();
    }

    @FXML
    void makeVisible(MouseEvent event) {
        show();
    }


    private void sendMessage(String op, Object obj) {
        try {
            Message message = new Message(op, obj);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }
    Teacher currentTeacher = new Teacher();
    Exam chosenExam = new Exam();
    @Subscribe
    public void handleMessage(Message message){
        String request = message.getMessage();
        Object obj = message.getObject();
       // if(request.equals("found teacher"))
           // getTeacherRequest(obj);
        if(request.equals("found exam"))
            getExamRequest(obj);
        else if(request.equals("grade updated successfully"))
            success();
    }
    public void getTeacherRequest(){
        //currentTeacher.copy((Teacher)obj);
        for(int i = 0; i<currentTeacher.getExams().size(); i++){
            idCMB.getItems().add(currentTeacher.getExams().get(i).getId_exam());
        }
    }
    public void getExamRequest(Object obj){
        chosenExam.copy((Exam)obj);
        subjectTF.setText(chosenExam.getCourse().getSubject().getName());
        courseTF.setText((chosenExam.getCourse().getName()));
        displayInfo();
    }
    public void displayInfo(){
        if(!(chosenExam.getSolvedExam().getData() == null)) {
            ObservableList<StudentData> data = FXCollections.observableArrayList(chosenExam.getSolvedExam().getData());
            nameCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("name"));
            gradeCol.setCellValueFactory(new PropertyValueFactory<StudentData, Integer>("grade"));
            tableView.setItems(data);
            if(!chosenExam.getFreeTextTeacher().equals("")) {
                freeText.setText(chosenExam.getFreeTextTeacher());
                freeText.setVisible(true);
            }
        }
        else
            tableView.getItems().clear();
    }
    public void updateGrade(){
        //String chosenStudent = tableView.getSelectionModel().getSelectedItem().getName();
        StudentData chosenStudent = tableView.getSelectionModel().getSelectedItem();
        int newGrade = Integer.parseInt(changeTF.getText());
        chosenStudent.setGrade(newGrade);
        sendMessage("update grade", chosenStudent);
    }
    public void hide(){
        changeLBL.setVisible(false);
        changeTF.setVisible(false);
        explainTF.setVisible(false);
        explainLBL.setVisible(false);
        submitBTN.setVisible(false);
        rectangle.setVisible(false);
    }
    public void show(){
        changeLBL.setVisible(true);
        changeTF.setVisible(true);
        explainTF.setVisible(true);
        explainLBL.setVisible(true);
        submitBTN.setVisible(true);
        rectangle.setVisible(true);
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
    private void invalidError(){
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("Illegal grade");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
    }

    private void success(){
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Grade updated successfully");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
        String exam = idCMB.getValue().toString();
        sendMessage("get exam", exam);

        changeTF.clear();
        explainTF.clear();
        hide();
    }

}

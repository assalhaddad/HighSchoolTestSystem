package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
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
    private TableView<StudentData> tableView;
    @FXML
    private TableColumn<StudentData, Integer> gradeCol;
    @FXML
    private TableColumn<StudentData, String> nameCol;
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        sendMessage("get teacher", (Object)null);
        hide();
    }

    @FXML
    void chooseExam(ActionEvent event) {
        String exam = idCMB.getValue().toString();
        sendMessage("get exam", exam);
    }

    @FXML
    void changeGrade(ActionEvent event) {
        hide();
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
        if(request.equals("found teacher"))
            getTeacherRequest(obj);
        else if(request.equals("found exam"))
            getExamRequest(obj);
    }
    public void getTeacherRequest(Object obj){
        currentTeacher.copy((Teacher)obj);
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
        ObservableList<StudentData> data = FXCollections.observableArrayList(chosenExam.getSolvedExam().getData());
        nameCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("name"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<StudentData, Integer>("grade"));
        tableView.setItems(data);
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

}

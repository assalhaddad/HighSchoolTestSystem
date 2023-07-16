package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class CheckGradesTeacher {

    @FXML
    private Button changeBTN;

    @FXML
    private Label changeLBL;

    @FXML
    private TextField changeTF;

    @FXML
    private TextField courseTF;

    @FXML
    private ComboBox<String> idCMB;

    @FXML
    private TextField subjectTF;
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        sendMessage("get teacher", (Object)null);
        changeLBL.setVisible(false);
        changeTF.setVisible(false);
        changeBTN.setVisible(false);
    }

    @FXML
    void chooseExam(ActionEvent event) {
        String exam = idCMB.getValue().toString();
        sendMessage("get exam", exam);
    }

    @FXML
    void explainTF(ActionEvent event) {

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
    }

}

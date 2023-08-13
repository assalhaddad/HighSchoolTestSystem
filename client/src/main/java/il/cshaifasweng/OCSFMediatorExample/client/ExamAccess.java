package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;

public class ExamAccess extends DoExam{

    @FXML
    private Button doneBTN;

    @FXML
    private TextField idStudent;

    @FXML
    private Button startBtn;

    protected Student student=null;
    boolean isOn=false;

    @FXML
    void donePressed(ActionEvent event) {

    }

    @FXML
    void startPressed(ActionEvent event) {

    }
    private void sendMessage(String op, Object obj) {
        try {
            Message message = new Message(op, obj);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }
    @Subscribe
    public void handleMessage(Message message) {
        String request = message.getMessage();
        Object obj = message.getObject();
        if (request.equals("students list is ready"))
            getStudentsRequest(obj);
        else
            wrongId();
    }

    private void getStudentsRequest(Object obj) {
        student = null;
        ObservableList<Student> studentsList = FXCollections.observableArrayList((ArrayList) obj);
        for (int i = 0; i < studentsList.size(); i++) {
            if ((studentsList.get(i).getId_student().equals(idStudent))) {
                startBtn.setDisable(false);
                isOn = true;
                student = studentsList.get(i);
                return;
            }
        }
        wrongId();
    }

    private void wrongId(){
        idStudent.clear();
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("Wrong Student's ID "+"\n"+"Please try again.");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
    }
    @FXML
    public void changeIdStudent(javafx.scene.input.InputMethodEvent inputMethodEvent)
    {
        if (isOn)
        {
            startBtn.setDisable(true);
            isOn=false;
        }
    }

}
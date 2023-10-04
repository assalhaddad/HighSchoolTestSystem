package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Request;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static il.cshaifasweng.OCSFMediatorExample.client.App.switchScreen;

public class RequestExtraTime {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField explainTF;

    @FXML
    private TextField extraTimeTF;

    @FXML
    private Button sendBTN;
    @FXML
    private ComboBox<String> idCMB;

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

    private String explain = "";
    private int extraMinutes;
    private Teacher teacher = Login.teacher;
    private Request request = new Request();
    private String examId = "";
    private ObservableList<String> exams;

    @FXML
    void sendExtraTimeRequest(ActionEvent event) {
        if(extraTimeTF.getText().isEmpty()){
            Platform.runLater(new Runnable() {
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("time is missing" + "\n" + "Please enter the time you want to request");
                    alert.setContentText(null);
                    alert.showAndWait();
                }
            });
        }
        else if(Integer.parseInt(extraTimeTF.getText()) > 60){
            Platform.runLater(new Runnable() {
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("extra minutes can't be more than 60 minutes" + "\n" + "Please enter a new time");
                    alert.setContentText(null);
                    alert.showAndWait();
                }
            });
            extraTimeTF.clear();
        }
        else{
            extraMinutes = Integer.parseInt(extraTimeTF.getText());
            explain = explainTF.getText();
            examId = idCMB.getValue();
            request = new Request(teacher,extraMinutes,explain,examId);
            sendMessage("new extra time request",request);
        }
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        sendMessage("get exams for request extra time",teacher);
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
    public void handleMessage(Message message){
        String request = message.getMessage();
        Object obj = message.getObject();
        if(request.equals("request added successfully"))
            requestAdded();
        else if(request.equals("exams list for request extra time is ready")){
            exams = FXCollections.observableArrayList((ArrayList)obj);
            for(int i = 0; i< exams.size(); i++)
                idCMB.getItems().add(exams.get(i));
        }
    }

    private void requestAdded(){
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Request sent successfully");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
        Platform.runLater(() -> {
            extraTimeTF.clear();
        });
        Platform.runLater(() -> {
            explainTF.clear();
        });
        Platform.runLater(() -> {
            idCMB.getSelectionModel().clearSelection();
        });
        // idCMB.setPromptText("Exam ID");
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
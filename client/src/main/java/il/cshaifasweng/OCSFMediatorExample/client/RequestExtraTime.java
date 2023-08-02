package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Request;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class RequestExtraTime {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField explainTF;
    @FXML
    private TextField examIdTF;

    @FXML
    private TextField extraTimeTF;

    @FXML
    private Button sendBTN;

    private String explain = "";
    private int extraMinutes;
    private Teacher teacher = Login.teacher;
    private Request request = new Request();
    private String examId = "";

    @FXML
    void sendExtraTimeRequest(ActionEvent event) {
        extraMinutes = Integer.parseInt(extraTimeTF.getText());
        explain = explainTF.getText();
        examId = examIdTF.getText();
        request = new Request(teacher,extraMinutes,explain,examId);
        sendMessage("new extra time request",request);
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        assert explainTF != null : "fx:id=\"explainationTF\" was not injected: check your FXML file 'requestExtraTime.fxml'.";
        assert extraTimeTF != null : "fx:id=\"extraTimeTF\" was not injected: check your FXML file 'requestExtraTime.fxml'.";
        assert sendBTN != null : "fx:id=\"sendBTN\" was not injected: check your FXML file 'requestExtraTime.fxml'.";
        assert extraTimeTF != null : "fx:id=\"extraTimeTF\" was not injected: check your FXML file 'requestExtraTime.fxml'.";
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
        extraTimeTF.clear();
        explainTF.clear();
        examIdTF.clear();
    }

}
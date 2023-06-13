/**
 * Sample Skeleton for 'addQuestion.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @FXML // fx:id="pointsTF"
    private TextField pointsTF; // Value injected by FXMLLoader

    @FXML // fx:id="questionTF"
    private TextField questionTF; // Value injected by FXMLLoader

    @FXML // fx:id="secondTF"
    private TextField secondTF; // Value injected by FXMLLoader

    @FXML // fx:id="subjectCMB"
    private ComboBox<String> subjectCMB; // Value injected by FXMLLoader

    @FXML // fx:id="subjectTF1"
    private Label subjectTF1; // Value injected by FXMLLoader

    @FXML // fx:id="thirdTF"
    private TextField thirdTF; // Value injected by FXMLLoader

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        System.out.println("inside initialize addQuestion");
        sendMessage("get list of subjects", (Object)null);
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
    String subject;
    String text;
    int points;
    String[] answers = new String[4];
    int correct;

    @FXML
    void done(ActionEvent event) {

    }

    @FXML
    void setAnswer1(ActionEvent event) {answers[0] = firstTF.getText().toString();}

    @FXML
    void setAnswer2(ActionEvent event) {answers[1] = secondTF.getText().toString();}

    @FXML
    void setAnswer3(ActionEvent event) {answers[2] = thirdTF.getText().toString();}

    @FXML
    void setAnswer4(ActionEvent event) {answers[3] = fourthTF.getText().toString();}

    @FXML
    void setCorrectAnswer(ActionEvent event) {correct = Integer.parseInt(correctTF.getText());}

    @FXML
    void setPoints(ActionEvent event) {points = Integer.parseInt(pointsTF.getText());}

    @FXML
    void setQuestion(ActionEvent event) {id = this.idTF.getText().toString();}

    @FXML
    void setSubject(ActionEvent event) {subject = this.subjectCMB.getValue().toString();}
    @FXML
    void setText(ActionEvent event){text = questionTF.getText().toString();}

    @Subscribe
    public void handleMessage(Message message){
        String request = message.getMessage();
        System.out.println("inside handleMessage");
        Object obj = message.getObject();
        if(request.equals("subjects list is ready"))
            getSubjectsRequest(obj);
        else if(request.equals("found student")) {
            System.out.println("hi2");
            //getStudentRequest(obj);
        }
        //else if(request.equals("grade1 updated successfully"))
        //updateGrade1(obj);
        //else if(request.equals("grade2 updated successfully"))
        //updateGrade2(obj);
    }

    private void getSubjectsRequest(Object obj){
        ObservableList<String> subjectList = FXCollections.observableArrayList((ArrayList)obj);
        subjectCMB.setItems(subjectList);
    }

}

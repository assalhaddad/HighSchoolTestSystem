/**
 * Sample Skeleton for 'addQuestion.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private Button doneBTN;

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
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
    int correct;
    String answer1;
    String answer2;
    String answer3;
    String answer4;


    @FXML
    void done(ActionEvent event) {
        Question newQuestion = new Question(id,text,answer1,answer2,answer3,answer4,correct,subject,points);
        sendMessage("new question",newQuestion);
    }

    @FXML
    void setAnswer1(ActionEvent event) {answer1 = firstTF.getText().toString();}

    @FXML
    void setAnswer2(ActionEvent event) {answer2 = secondTF.getText().toString();}

    @FXML
    void setAnswer3(ActionEvent event) {answer3 = thirdTF.getText().toString();}

    @FXML
    void setAnswer4(ActionEvent event) {answer4 = fourthTF.getText().toString();}

    @FXML
    void setCorrectAnswer(ActionEvent event) {correct = Integer.parseInt(correctTF.getText());}

    @FXML
    void setPoints(ActionEvent event) {points = Integer.parseInt(pointsTF.getText());}

    @FXML
    void setQuestion(ActionEvent event) {id = this.idTF.getText().toString();}

    @FXML
    void setSubject(ActionEvent event) {subject = this.subjectCMB.getValue();}
    @FXML
    void setText(ActionEvent event){text = questionTF.getText().toString();}

    @Subscribe
    public void handleMessage(Message message){
        String request = message.getMessage();
        Object obj = message.getObject();
        if(request.equals("subjects list is ready"))
            getSubjectsRequest(obj);
        else if(request.equals("question added successfully")) {
            addedNewQuestion();
        }
    }

    private void getSubjectsRequest(Object obj){
        ObservableList<String> subjectList = FXCollections.observableArrayList((ArrayList)obj);
        subjectCMB.setItems(subjectList);
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
        subjectCMB.getSelectionModel().clearSelection();
        idTF.clear();
        questionTF.clear();
        firstTF.clear();
        secondTF.clear();
        thirdTF.clear();
        fourthTF.clear();
        correctTF.clear();
        pointsTF.clear();
    }
}
package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;

public class BuildExam {

    @FXML
    private Button doneBTN;

    @FXML
    private TextField idTF;

    @FXML
    private ListView<?> questionsList;

    @FXML
    private ComboBox<String> subjectsCMB;

    @FXML
    private TextField timeTF;

    @FXML
    void done(ActionEvent event) {

    }

    @FXML
    void getSubjects(ActionEvent event) {

    }

    @FXML
    void setID(ActionEvent event) {

    }

    @FXML
    void setTime(ActionEvent event) {

    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        //System.out.println("inside initialize addQuestion");
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
        subjectsCMB.setItems(subjectList);
    }

}

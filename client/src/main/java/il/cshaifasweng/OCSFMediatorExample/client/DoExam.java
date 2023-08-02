package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.Subscribe;

import java.awt.event.InputMethodEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class DoExam {


    @FXML
    private Button doneBTN;


    @FXML
    private TextField forthDCode;

    @FXML
    private Button startBtn;
    private boolean isOn=false;

    protected Exam exam=null;

    private void sendMessage(String op, Object obj) {
        try {
            Message message = new Message(op, obj);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }
    public TextField getForthDCode() {
        return forthDCode;
    }

    public void setForthDCode(TextField forthDCode) {
        this.forthDCode = forthDCode;
    }
    @FXML
    void donePressed(ActionEvent event) {
        exam=null;
        if(forthDCode.getText().length()!=4)
            wrongFourDigitsCode();

        else
            sendMessage("get list of codes",(Object)null);
    }
    @Subscribe
    public void handleMessage(Message message) {
        String request = message.getMessage();
        Object obj = message.getObject();
        if (request.equals("list of codes is ready"))
            getCodesRequest(obj);
        else
            wrongFourDigitsCode();
    }

    private void getCodesRequest(Object obj){
        ObservableList<Exam> codesList = FXCollections.observableArrayList((ArrayList)obj);
        for(int i=0; i<codesList.size(); i++){
            if((codesList.get(i).getCode4Digits().equals(forthDCode))){
                startBtn.setDisable(false);
                isOn=true;
                exam=codesList.get(i);
                return;
            }
        }
        wrongFourDigitsCode();
    }

    @FXML
    void startPressed(ActionEvent event) { loadSceneForButton("examAccess.fxml"); }

    @FXML
    void changeForthDcode(ActionEvent event)
    {
        if (isOn)
        {
            startBtn.setDisable(true);
            isOn=false;
        }

        if (forthDCode.getText().length()>=4)
            doneBTN.setDisable(false);

        else
            doneBTN.setDisable(true);
    }

    private void wrongFourDigitsCode() {
        forthDCode.clear();
        doneBTN.setDisable(true);
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("Wrong 4 digits code!" + "\n" + "Please try again.");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
    }

    private void loadSceneForButton(String fxmlPath) {
        try {
            // Load the FXML file for the scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node sceneRoot = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

}
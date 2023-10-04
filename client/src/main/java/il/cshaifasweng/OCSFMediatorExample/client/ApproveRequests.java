package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Request;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.hibernate.sql.Update;

import javax.xml.bind.SchemaOutputResolver;

import static il.cshaifasweng.OCSFMediatorExample.client.App.switchScreen;

public class ApproveRequests {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button approveBtn;

    @FXML
    private TextArea explanationTA;

    @FXML
    private TextField extraTimeTF;

    @FXML
    private TextField fromTF;

    @FXML
    private ComboBox<String> requestCmb;

    @FXML
    private TextField testIdTF;
    @FXML
    private Button ViewExamsBtn;

    @FXML
    private Button ViewGradesBtn;

    @FXML
    private Button ViewQuestionsBtn;
    @FXML
    private Button ApproveBtn;
    @FXML
    private Button menuBtn;
    @FXML
    private VBox Menu;
    @FXML
    private Button declineBtn;

    Request currentRequest = new Request();
    ObservableList<Request> requestsList;

    @FXML
    void Approve(ActionEvent event) {
        sendMessage("approve this request",currentRequest);
    }

    @FXML
    void ChooseRequest(ActionEvent event) {
        String requestCmbValue = this.requestCmb.getValue();
        for(int i=0; i<requestsList.size(); i++){
            if(requestCmbValue!=null && requestsList.get(i).getId() == Integer.parseInt(requestCmbValue)){
                currentRequest.copy(requestsList.get(i));
                fromTF.setText(currentRequest.getTeacherName());
                testIdTF.setText(currentRequest.getExamId());
                extraTimeTF.setText(currentRequest.getMinutes()+" minutes");
                explanationTA.setText(currentRequest.getExplaination());
                break;
            }
        }
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        sendMessage("get list of requests", (Object)null);
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
        if(request.equals("requests list is ready"))
            getRequests(obj);
        else if(request.equals("request approved successfully")){
            System.out.println("bla bla");
            UpdateAll(obj);
        }
        else if(request.equals("requests list is ready to update"))
            getRequestsUpdated(obj);
        else if(request.equals("request declined successfully"))
            UpdateAll(obj);
    }

    private void getRequestsUpdated(Object obj) {
        requestCmb.getItems().clear();
        requestsList = FXCollections.observableArrayList((ArrayList)obj);
        for(int i = 0; i <requestsList.size(); i++)
            requestCmb.getItems().add(String.valueOf(requestsList.get(i).getId()));
    }

    private void getRequests(Object obj){
        requestsList = FXCollections.observableArrayList((ArrayList)obj);
        for(int i = 0; i <requestsList.size(); i++)
            requestCmb.getItems().add(String.valueOf(requestsList.get(i).getId()));
    }

    private void UpdateAll(Object obj){
        System.out.println("inside update");
        Platform.runLater(() -> {
            requestCmb.getSelectionModel().clearSelection();
        });
        fromTF.clear();
        testIdTF.clear();
        extraTimeTF.clear();
        explanationTA.clear();
        if((Integer)obj > 0){
            Platform.runLater(new Runnable() {
                public void run() {
                    System.out.println("print");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("Request approved successfully");
                    alert.setContentText(null);
                    alert.showAndWait();
                }
            });
        }
        else{
            Platform.runLater(new Runnable() {
                public void run() {
                    System.out.println("print");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("Request declined successfully");
                    alert.setContentText(null);
                    alert.showAndWait();
                }
            });
        }
        sendMessage("get list of requests to update", (Object)null);

    }

    @FXML
    void ApproveRequestsEvent(ActionEvent event) throws IOException {
        EventBus.getDefault().unregister(this);
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        App.setRoot("approveRequests");
    }

    @FXML
    void LogOut(ActionEvent event) {
        EventBus.getDefault().unregister(this);
        switchScreen("Login");
    }

    @FXML
    void ViewQuestionsEvent(ActionEvent event) throws IOException {
        EventBus.getDefault().unregister(this);
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        App.setRoot("viewQuestionsPrincipal");
    }

    @FXML
    void ViewGradesEvent(ActionEvent event) throws IOException {
        EventBus.getDefault().unregister(this);
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        App.setRoot("viewGradesPrincipal");
    }

    @FXML
    void ViewExamsEvent(ActionEvent event) throws IOException {
        EventBus.getDefault().unregister(this);
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        App.setRoot("viewExamsPrincipal");
    }

    @FXML
    void OpenMenu(ActionEvent event) {
        menuBtn.setVisible(false);
        Menu.setVisible(true);
    }
    @FXML
    void Decline(ActionEvent event) {sendMessage("decline this request",currentRequest);}
}
package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static il.cshaifasweng.OCSFMediatorExample.client.App.switchScreen;

public class Login {

    @FXML
    private ResourceBundle resources;
    @FXML
    private AnchorPane Pane1;

    @FXML
    private URL location;

    @FXML
    private Button loginBtn;

    @FXML
    private HBox loginHbox;

    @FXML
    private Label loginLbl;

    @FXML
    private Label passwordLbl;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Label usernameLbl;

    @FXML
    private TextField usernameTxt;
    String username;
    String password;
    public static Student student = new Student();
    public static Principal principal = new Principal();
    public static Teacher teacher = new Teacher();
    ObservableList<LoginInfo> loginInfos;

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        username="";
        password="";
        student = new Student();
        principal = new Principal();
        teacher = new Teacher();
        sendMessage("get lists of usernames and passwords",null);
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
        if(request.equals("login list is ready")){
            loginInfos = FXCollections.observableArrayList((ArrayList)obj);
        }
        else if(request.equals("found the student to login")){
            student.copy((Student)obj);
            checkPosition("student");
        }
        else if(request.equals("found the teacher to login")){
           teacher.copy((Teacher)obj);
           checkPosition("teacher");
        }
        else if(request.equals("principal is ready")){
            principal.copy((Principal)obj);
            checkPosition("principal");
        }
    }

    private void wrongInformation(){
        usernameTxt.clear();
        passwordTxt.clear();
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("Wrong username or password!"+"\n"+"Please try again.");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
        switchScreen("Login");
    }

    private void missingInformation(){
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("Missing information!");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
    }

    private void checkPosition(String position) {
        System.out.println("flag = "+position);
        switch (position){
            case "teacher":
                switchScreen("TeacherPage");
                break;
            case "student":
                switchScreen("StudentsPage");
                break;
            case "principal":
                switchScreen("Principal");
                break;
        }
        usernameTxt.clear();
        passwordTxt.clear();
    }

    @FXML
    void checkLogin(ActionEvent event) {
        int flag=0;
        if(usernameTxt.getText().isEmpty()||passwordTxt.getText().isEmpty())
            missingInformation();
        else {
            username = usernameTxt.getText();
            password = passwordTxt.getText();
            for(int i =0; i<loginInfos.size(); i++){
                if(loginInfos.get(i).getUsername().equals(username) && loginInfos.get(i).getPassword().equals(password)){
                    if(loginInfos.get(i).getType().equals("teacher")){
                        flag=1;
                        sendMessage("get this teacher to login",loginInfos.get(i));
                    }
                    else if(loginInfos.get(i).getType().equals("student")){
                        flag=1;
                        sendMessage("get this student to login",loginInfos.get(i));
                    }
                    else {
                        flag=1;
                        sendMessage("get the principal", null);
                    }
                }
            }
            if(flag == 0)
                wrongInformation();
        }
    }
}
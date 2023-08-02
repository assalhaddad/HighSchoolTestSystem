package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Principal;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
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
    public  static String flag = "";


    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        flag = "";
        username="";
        password="";
        student = new Student();
        principal = new Principal();
        teacher = new Teacher();
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'login.fxml'.";
        assert loginHbox != null : "fx:id=\"loginHbox\" was not injected: check your FXML file 'login.fxml'.";
        assert loginLbl != null : "fx:id=\"loginLbl\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordLbl != null : "fx:id=\"passwordLbl\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordTxt != null : "fx:id=\"passwordTxt\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameLbl != null : "fx:id=\"usernameLbl\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameTxt != null : "fx:id=\"usernameTxt\" was not injected: check your FXML file 'login.fxml'.";

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
        else if (request.equals("teachers list is ready"))
            getTeachersRequest(obj);
        else if(request.equals("principal is ready")) {
            principal.copy((Principal)obj);
            if((principal.getUsername().equals(username)) && (principal.getPassword().equals(password))){
                flag = "principal";
                checkPosition(flag);
            }
            else
                wrongInformation();
        }
    }

    private void getStudentsRequest(Object obj){
        ObservableList<Student> studentsList = FXCollections.observableArrayList((ArrayList)obj);
        for(int i=0; i<studentsList.size(); i++){
            if((studentsList.get(i).getUsername().equals(username)) && (studentsList.get(i).getPassword().equals(password))){
                student = studentsList.get(i);
                flag = "student";
                checkPosition(flag);
            }
        }
        if(flag == "")
            sendMessage("get list of teachers",(Object)null);
    }
    private void getTeachersRequest(Object obj){
        ObservableList<Teacher> teachersList = FXCollections.observableArrayList((ArrayList)obj);
        for(int i=0; i<teachersList.size(); i++){
            if((teachersList.get(i).getUsername().equals(username)) && (teachersList.get(i).getPassword().equals(password))){
                teacher = teachersList.get(i);
                flag = "teacher";
                checkPosition(flag);
            }
        }
        if(flag == "")
            sendMessage("get the principal",(Object)null);
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
        if(usernameTxt.getText().isEmpty()||passwordTxt.getText().isEmpty())
            missingInformation();
        else {
            username = usernameTxt.getText();
            password = passwordTxt.getText();
            sendMessage("get list of students", (Object) null);
        }
    }
}
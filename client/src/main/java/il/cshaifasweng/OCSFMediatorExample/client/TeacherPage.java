/**
 * Sample Skeleton for 'teacherPage.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.switchScreen;

public class TeacherPage {

    @FXML // fx:id="detailVBox"
    private VBox detailVBox; // Value injected by FXMLLoader
    @FXML // fx:id="BorderPane"
    private BorderPane BorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="Menu"
    private VBox Menu; // Value injected by FXMLLoader

    @FXML // fx:id="Title"
    private HBox Title; // Value injected by FXMLLoader

    @FXML // fx:id="addQuestionBtn"
    private Button addQuestionBtn; // Value injected by FXMLLoader

    @FXML
    private ImageView back;

    @FXML // fx:id="checkGradesBtn"
    private Button checkGradesBtn; // Value injected by FXMLLoader

    @FXML // fx:id="createExamBtn"
    private Button buildExamBtn; // Value injected by FXMLLoader

    @FXML // fx:id="requestTimeBtn"
    private Button requestTimeBtn; // Value injected by FXMLLoader

    @FXML
    private Button menuBtn;

    @FXML
    void initialize() {
        Menu.setVisible(true);
        menuBtn.setVisible(false);
    }

    @FXML
    void AddQuestionEvent(ActionEvent event) {

        Menu.setVisible(false);
        menuBtn.setVisible(true);
        loadSceneForButton("addQuestion.fxml");
    }

    @FXML
    void CheckGradesEvent(ActionEvent event) {
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        loadSceneForButton("checkGradesTeacher.fxml");
    }

    @FXML
    void CreateExamEvent(ActionEvent event) {
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        loadSceneForButton("buildExam.fxml");
    }

    @FXML
    void RequestTimeEvent(ActionEvent event) {
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        loadSceneForButton("requestExtraTime.fxml");
    }
    @FXML
    void LogOut(ActionEvent event) {
        Login.teacher=null;
        Login.student=null;
        Login.principal=null;
        switchScreen("Login");}

    private void loadSceneForButton(String fxmlPath) {
        try {
            // Load the FXML file for the scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node sceneRoot = loader.load();

            // Clear the detail VBox and add the loaded scene
            detailVBox.getChildren().clear();
            detailVBox.getChildren().add(sceneRoot);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @FXML
    void OpenMenu(ActionEvent event) {
        menuBtn.setVisible(false);
        Menu.setVisible(true);
    }

}
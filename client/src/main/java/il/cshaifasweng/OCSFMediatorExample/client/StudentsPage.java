package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static il.cshaifasweng.OCSFMediatorExample.client.App.switchScreen;

public class StudentsPage {

    @FXML
    private BorderPane BorderPane;

    @FXML
    private VBox Menu;

    @FXML
    private HBox Title;

    @FXML
    private VBox detailVBox;

    @FXML
    private Button doExamBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button menuBtn;

    @FXML
    void initialize() {
        Menu.setVisible(true);
        menuBtn.setVisible(false);
    }

    @FXML
    void DoExamAction(ActionEvent event)  {
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        loadSceneForButton("doExam.fxml");
    }

    @FXML
    void LogOutEvent(ActionEvent event) {
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
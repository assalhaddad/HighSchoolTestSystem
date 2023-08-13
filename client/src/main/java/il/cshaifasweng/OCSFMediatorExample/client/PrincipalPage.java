package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.greenrobot.eventbus.EventBus;

import static il.cshaifasweng.OCSFMediatorExample.client.App.switchScreen;

public class PrincipalPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ApproveBtn;

    @FXML
    private BorderPane BorderPane;

    @FXML
    private VBox Menu;

    @FXML
    private HBox Title;

    @FXML
    private Button ViewExamsBtn;

    @FXML
    private Button ViewGradesBtn;

    @FXML
    private Button ViewQuestionsBtn;

    @FXML
    private VBox detailVBox;

    @FXML
    private Label pageLbl;

    @FXML
    private AnchorPane teacherPage;

    @FXML
    void ApproveRequestsEvent(ActionEvent event) {loadSceneForButton("approveRequests.fxml");}
    @FXML
    void LogOut(ActionEvent event) {
        switchScreen("Login");}

    @FXML
    void initialize() {
    }
    @FXML
    void ViewQuestionsEvent(ActionEvent event) {loadSceneForButton("viewQuestionsPrincipal.fxml");}

    @FXML
    void ViewGradesEvent(ActionEvent event) {loadSceneForButton("viewGradesPrincipal.fxml");}

    @FXML
    void ViewExamsEvent(ActionEvent event) {loadSceneForButton("viewExamsPrincipal.fxml");}

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

}
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
    void LogOut(ActionEvent event) {switchScreen("Login");}

    @FXML
    void initialize() {
        assert ApproveBtn != null : "fx:id=\"ApproveBtn\" was not injected: check your FXML file 'principalPage.fxml'.";
        assert BorderPane != null : "fx:id=\"BorderPane\" was not injected: check your FXML file 'principalPage.fxml'.";
        assert Menu != null : "fx:id=\"Menu\" was not injected: check your FXML file 'principalPage.fxml'.";
        assert Title != null : "fx:id=\"Title\" was not injected: check your FXML file 'principalPage.fxml'.";
        assert ViewExamsBtn != null : "fx:id=\"ViewExamsBtn\" was not injected: check your FXML file 'principalPage.fxml'.";
        assert ViewGradesBtn != null : "fx:id=\"ViewGradesBtn\" was not injected: check your FXML file 'principalPage.fxml'.";
        assert ViewQuestionsBtn != null : "fx:id=\"ViewQuestionsBtn\" was not injected: check your FXML file 'principalPage.fxml'.";
        assert detailVBox != null : "fx:id=\"detailVBox\" was not injected: check your FXML file 'principalPage.fxml'.";
        assert pageLbl != null : "fx:id=\"pageLbl\" was not injected: check your FXML file 'principalPage.fxml'.";
        assert teacherPage != null : "fx:id=\"teacherPage\" was not injected: check your FXML file 'principalPage.fxml'.";

    }

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


/**
 * Sample Skeleton for 'teacherPage.fxml' Controller Class
 */

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

    @FXML // fx:id="checkGradesBtn"
    private Button checkGradesBtn; // Value injected by FXMLLoader

    @FXML // fx:id="createExamBtn"
    private Button buildExamBtn; // Value injected by FXMLLoader

    @FXML // fx:id="pageLbl"
    private Label pageLbl; // Value injected by FXMLLoader

    @FXML // fx:id="requestTimeBtn"
    private Button requestTimeBtn; // Value injected by FXMLLoader

    @FXML
    void AddQuestionEvent(ActionEvent event) {
        loadSceneForButton("addQuestion.fxml");
    }

    @FXML
    void CheckGradesBtn(ActionEvent event) {

    }

    @FXML
    void CreateExamEvent(ActionEvent event) {
        loadSceneForButton("buildExam.fxml");
    }

    @FXML
    void RequestTimeEvent(ActionEvent event) {

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

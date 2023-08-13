package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class FreeText extends ExamPage {

    @FXML
    private Button doneBTN;


    @FXML
    private Label freeTextTeacher;

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        assert doneBTN != null : "fx:id=\"doneBTN\" was not injected: check your FXML file 'freeText.fxml'.";
        assert freeTextTeacher != null : "fx:id=\"freeTextTeacher\" was not injected: check your FXML file 'freeText.fxml'.";

        if(exam.getFreeTextStudent()!="")
            freeTextTeacher.setText(exam.getFreeTextStudent());
    }


    @FXML
    void done(ActionEvent event) {loadSceneForButton("examPage");}

    private void loadSceneForButton(String fxmlPath) {
        try {
            // Load the FXML file for the scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node sceneRoot = loader.load();

            // Clear the detail VBox and add the loaded scene
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

}
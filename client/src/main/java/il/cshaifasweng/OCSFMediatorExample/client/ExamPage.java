package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.greenrobot.eventbus.EventBus;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ExamPage extends ExamAccess{

    @FXML
    private Button aboutBtn;

    @FXML
    private Button answer1;

    @FXML
    private Button answer2;

    @FXML
    private Button answer3;

    @FXML
    private Button answer4;

    @FXML
    private Button doneBTN;

    @FXML
    private Button nextBtn;

    @FXML
    private Button prevBtn;

    @FXML
    private Label questionText;

    private int i=0;
    private int answered=0;
    protected int grade=0;
    protected int[] chosenAnswers= new int[exam.getQuestions().size()];



    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        assert answer1 != null : "fx:id=\"answer1\" was not injected: check your FXML file 'examPage.fxml'.";
        assert answer2 != null : "fx:id=\"answer2\" was not injected: check your FXML file 'examPage.fxml'.";
        assert answer3 != null : "fx:id=\"answer3\" was not injected: check your FXML file 'examPage.fxml'.";
        assert answer4 != null : "fx:id=\"answer4\" was not injected: check your FXML file 'examPage.fxml'.";
        assert aboutBtn != null : "fx:id=\"aboutBtn\" was not injected: check your FXML file 'examPage.fxml'.";
        assert doneBTN != null : "fx:id=\"doneBTN\" was not injected: check your FXML file 'examPage.fxml'.";
        assert nextBtn != null : "fx:id=\"nextBtn\" was not injected: check your FXML file 'examPage.fxml'.";
        assert prevBtn != null : "fx:id=\"prevBtn\" was not injected: check your FXML file 'examPage.fxml'.";
        assert questionText != null : "fx:id=\"questionText\" was not injected: check your FXML file 'examPage.fxml'.";


        if(i==0)
            prevBtn.setDisable(true);
        else
            prevBtn.setDisable(false);

        if(i==exam.getQuestions().size()-1)
            nextBtn.setDisable(true);
        else
            nextBtn.setDisable(false);

        if(answered==exam.getQuestions().size()-1)
            doneBTN.setDisable(false);
        answer1.setText(exam.getQuestions().get(i).getAnswer1());
        answer2.setText(exam.getQuestions().get(i).getAnswer2());
        answer3.setText(exam.getQuestions().get(i).getAnswer3());
        answer4.setText(exam.getQuestions().get(i).getAnswer4());
        questionText.setText(exam.getQuestions().get(i).getText());

        if (chosenAnswers[i]==1) {

            answer1.setStyle("-fx-background-color: #70879d; ");
            answer2.setStyle("-fx-background-color:  #E0E0E0; ");
            answer3.setStyle("-fx-background-color:  #E0E0E0; ");
            answer4.setStyle("-fx-background-color:  #E0E0E0; ");
        }

        if (chosenAnswers[i]==2) {

            answer1.setStyle("-fx-background-color: #E0E0E0; ");
            answer2.setStyle("-fx-background-color:  #70879d; ");
            answer3.setStyle("-fx-background-color:  #E0E0E0; ");
            answer4.setStyle("-fx-background-color:  #E0E0E0; ");
        }
        if (chosenAnswers[i]==3) {

            answer1.setStyle("-fx-background-color: #E0E0E0; ");
            answer2.setStyle("-fx-background-color:  #E0E0E0; ");
            answer3.setStyle("-fx-background-color:  #70879d; ");
            answer4.setStyle("-fx-background-color:  #E0E0E0; ");
        }
        if (chosenAnswers[i]==4) {

            answer1.setStyle("-fx-background-color: #E0E0E0; ");
            answer2.setStyle("-fx-background-color:  #E0E0E0; ");
            answer3.setStyle("-fx-background-color:  #E0E0E0; ");
            answer4.setStyle("-fx-background-color:  #70879d; ");
        }


    }
    @FXML
    void aboutPressed(ActionEvent event) {loadSceneForButton("freeText");}

    @FXML
    void answer1Selected(ActionEvent event) {

        if(answer1.getStyle()=="-fx-background-color: #E0E0E0;"&&answer2.getStyle()=="-fx-background-color: #E0E0E0;"&&answer3.getStyle()=="-fx-background-color: #E0E0E0;"&&answer4.getStyle()=="-fx-background-color: #E0E0E0;")
            answered++;


        answer1.setStyle("-fx-background-color: #70879d; ");
        answer2.setStyle("-fx-background-color:  #E0E0E0; ");
        answer3.setStyle("-fx-background-color:  #E0E0E0; ");
        answer4.setStyle("-fx-background-color:  #E0E0E0; ");

        chosenAnswers[i]=1;


    }

    @FXML
    void answer2Selected(ActionEvent event) {
        if(answer1.getStyle()=="-fx-background-color: #E0E0E0;"&&answer2.getStyle()=="-fx-background-color: #E0E0E0;"&&answer3.getStyle()=="-fx-background-color: #E0E0E0;"&&answer4.getStyle()=="-fx-background-color: #E0E0E0;")
            answered++;

        answer1.setStyle("-fx-background-color:  #E0E0E0; ");
        answer2.setStyle("-fx-background-color:  #70879d; ");
        answer3.setStyle("-fx-background-color:  #E0E0E0; ");
        answer4.setStyle("-fx-background-color:  #E0E0E0; ");

        chosenAnswers[i]=2;

    }

    @FXML
    void answer3Selected(ActionEvent event) {
        if(answer1.getStyle()=="-fx-background-color: #E0E0E0;"&&answer2.getStyle()=="-fx-background-color: #E0E0E0;"&&answer3.getStyle()=="-fx-background-color: #E0E0E0;"&&answer4.getStyle()=="-fx-background-color: #E0E0E0;")
            answered++;

        answer1.setStyle("-fx-background-color:  #E0E0E0; ");
        answer2.setStyle("-fx-background-color:  #E0E0E0; ");
        answer3.setStyle("-fx-background-color:  #70879d; ");
        answer4.setStyle("-fx-background-color:  #E0E0E0; ");

        chosenAnswers[i]=3;

    }

    @FXML
    void answer4Selected(ActionEvent event) {
        if(answer1.getStyle()=="-fx-background-color: #E0E0E0;"&&answer2.getStyle()=="-fx-background-color: #E0E0E0;"&&answer3.getStyle()=="-fx-background-color: #E0E0E0;"&&answer4.getStyle()=="-fx-background-color: #E0E0E0;")
            answered++;

        answer1.setStyle("-fx-background-color:  #E0E0E0; ");
        answer2.setStyle("-fx-background-color:  #E0E0E0; ");
        answer3.setStyle("-fx-background-color:  #E0E0E0; ");
        answer4.setStyle("-fx-background-color:  #70879d; ");

        chosenAnswers[i]=4;

    }

    @FXML
    void done(ActionEvent event) {loadSceneForButton("studentsPage"); }

    @FXML
    void nextPressed(ActionEvent event) {
        i++;
        initialize();

    }

    @FXML
    void prevPressed(ActionEvent event) {
        i--;
        initialize();

    }

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
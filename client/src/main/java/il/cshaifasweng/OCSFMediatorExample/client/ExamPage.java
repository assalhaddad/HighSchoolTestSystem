package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.switchScreen;

public class ExamPage extends DoExam{

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

    public static int i = 0;
    protected static int extraTime = 0;

    public static List<Integer> chosenAnswers;



    @FXML
    void initialize() {

        if (!DoExam.isOn) {
            chosenAnswers = new ArrayList<Integer>(Collections.nCopies(DoExam.exam.getQuestions().size(), 0));

            EventBus.getDefault().register(this);
        }

        DoExam.isOn = true;

        assert answer1 != null : "fx:id=\"answer1\" was not injected: check your FXML file 'examPage.fxml'.";
        assert answer2 != null : "fx:id=\"answer2\" was not injected: check your FXML file 'examPage.fxml'.";
        assert answer3 != null : "fx:id=\"answer3\" was not injected: check your FXML file 'examPage.fxml'.";
        assert answer4 != null : "fx:id=\"answer4\" was not injected: check your FXML file 'examPage.fxml'.";
        assert aboutBtn != null : "fx:id=\"aboutBtn\" was not injected: check your FXML file 'examPage.fxml'.";
        assert doneBTN != null : "fx:id=\"doneBTN\" was not injected: check your FXML file 'examPage.fxml'.";
        assert nextBtn != null : "fx:id=\"nextBtn\" was not injected: check your FXML file 'examPage.fxml'.";
        assert prevBtn != null : "fx:id=\"prevBtn\" was not injected: check your FXML file 'examPage.fxml'.";
        assert questionText != null : "fx:id=\"questionText\" was not injected: check your FXML file 'examPage.fxml'.";


        if (i == 0)
            prevBtn.setDisable(true);
        else
            prevBtn.setDisable(false);

        if (i == DoExam.exam.getQuestions().size() - 1)
            nextBtn.setDisable(true);
        else
            nextBtn.setDisable(false);


        answer1.setText(DoExam.exam.getQuestions().get(i).getAnswer1());
        answer2.setText(DoExam.exam.getQuestions().get(i).getAnswer2());
        answer3.setText(DoExam.exam.getQuestions().get(i).getAnswer3());
        answer4.setText(DoExam.exam.getQuestions().get(i).getAnswer4());
        questionText.setText(DoExam.exam.getQuestions().get(i).getText());

        answer1.setStyle("-fx-background-color: #E0E0E0; ");
        answer2.setStyle("-fx-background-color:  #E0E0E0; ");
        answer3.setStyle("-fx-background-color:  #E0E0E0; ");
        answer4.setStyle("-fx-background-color:  #E0E0E0; ");


        if (chosenAnswers.get(i) == 1) {

            answer1.setStyle("-fx-background-color: #70879d; ");
            answer2.setStyle("-fx-background-color:  #E0E0E0; ");
            answer3.setStyle("-fx-background-color:  #E0E0E0; ");
            answer4.setStyle("-fx-background-color:  #E0E0E0; ");
        }

        if (chosenAnswers.get(i) == 2) {

            answer1.setStyle("-fx-background-color: #E0E0E0; ");
            answer2.setStyle("-fx-background-color:  #70879d; ");
            answer3.setStyle("-fx-background-color:  #E0E0E0; ");
            answer4.setStyle("-fx-background-color:  #E0E0E0; ");
        }
        if (chosenAnswers.get(i) == 3) {

            answer1.setStyle("-fx-background-color: #E0E0E0; ");
            answer2.setStyle("-fx-background-color:  #E0E0E0; ");
            answer3.setStyle("-fx-background-color:  #70879d; ");
            answer4.setStyle("-fx-background-color:  #E0E0E0; ");
        }
        if (chosenAnswers.get(i) == 4) {

            answer1.setStyle("-fx-background-color: #E0E0E0; ");
            answer2.setStyle("-fx-background-color:  #E0E0E0; ");
            answer3.setStyle("-fx-background-color:  #E0E0E0; ");
            answer4.setStyle("-fx-background-color:  #70879d; ");
        }
        checkIfDone();


    }

    @FXML
    void aboutPressed(ActionEvent event) {
        switchScreen("FreeText");
    }

    @FXML
    void answer1Selected(ActionEvent event) {


        answer1.setStyle("-fx-background-color: #70879d; ");
        answer2.setStyle("-fx-background-color:  #E0E0E0; ");
        answer3.setStyle("-fx-background-color:  #E0E0E0; ");
        answer4.setStyle("-fx-background-color:  #E0E0E0; ");

        chosenAnswers.set(i, 1);
        checkIfDone();

    }

    @FXML
    void answer2Selected(ActionEvent event) {


        answer1.setStyle("-fx-background-color:  #E0E0E0; ");
        answer2.setStyle("-fx-background-color:  #70879d; ");
        answer3.setStyle("-fx-background-color:  #E0E0E0; ");
        answer4.setStyle("-fx-background-color:  #E0E0E0; ");

        chosenAnswers.set(i, 2);
        checkIfDone();

    }

    @FXML
    void answer3Selected(ActionEvent event) {

        answer1.setStyle("-fx-background-color:  #E0E0E0; ");
        answer2.setStyle("-fx-background-color:  #E0E0E0; ");
        answer3.setStyle("-fx-background-color:  #70879d; ");
        answer4.setStyle("-fx-background-color:  #E0E0E0; ");

        chosenAnswers.set(i, 3);
        checkIfDone();

    }

    @FXML
    void answer4Selected(ActionEvent event) {

        answer1.setStyle("-fx-background-color:  #E0E0E0; ");
        answer2.setStyle("-fx-background-color:  #E0E0E0; ");
        answer3.setStyle("-fx-background-color:  #E0E0E0; ");
        answer4.setStyle("-fx-background-color:  #70879d; ");

        chosenAnswers.set(i, 4);
        checkIfDone();

    }

    @FXML
    void done(ActionEvent event) {


        StudentData studentD = new StudentData(DoExam.student,LocalDateTime.now().toString(), 10, true, chosenAnswers, solvedExam);

        solvedExam.calculateGrades();
        sendMessage("new studentData", studentD);

        switchScreen("StudentsPage");
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
        if (request.equals("studentData added successfully"))
            addedNewStudentData();
        if(request.equals("solvedExam added successfully"))
            System.out.println("done");
    }

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


    void checkIfDone() {
        for (int i = 0; i < chosenAnswers.size(); i++) {
            if (i == chosenAnswers.size() - 1 && ((chosenAnswers.get(i) == 1) || (chosenAnswers.get(i) == 2) || (chosenAnswers.get(i) == 3) || (chosenAnswers.get(i) == 4)))
                doneBTN.setDisable(false);
            if (chosenAnswers.get(i) == 0)
                break;
        }
    }

    private void addedNewStudentData() {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done!");
                alert.setHeaderText("You Finished the exam!");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
    }
}

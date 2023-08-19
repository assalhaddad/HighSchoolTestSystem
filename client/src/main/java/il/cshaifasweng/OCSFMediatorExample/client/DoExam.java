package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.SolvedExam;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import static il.cshaifasweng.OCSFMediatorExample.client.App.switchScreen;

public class DoExam {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button doneBTN;

    @FXML
    private TextField idStudent;

    @FXML
    private Button nextBtn;

    @FXML
    private Button startBtn;

    @FXML
    private TextField first;

    @FXML
    private TextField second;
    @FXML
    private TextField third;

    @FXML
    private TextField forth;

    protected static Exam exam=new Exam();
    protected static Student student;
    public static boolean isOn=false;
    protected static SolvedExam solvedExam=new SolvedExam();
    private String theCode;


    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        second.setDisable(true);
        third.setDisable(true);
        forth.setDisable(true);
        theCode = "";
    }

    @FXML
    void donePressed(ActionEvent event) {
        if (idStudent.getText().length() == 0)
            wrongId();

        else {
            if (Login.student.getId_student().equals(idStudent.getText()) )
            {
                student = Login.student;
                startBtn.setDisable(false);
                idStudent.setDisable(true);
                doneBTN.setDisable(true);
            }
            else
                wrongId();

            //sendMessage("get list of students ids", theCode);
        }


    }

    @Subscribe
    public void handleMessage(Message message) {
        String request = message.getMessage();
        Object obj = message.getObject();
        System.out.println(request);

        if (request.equals("found exam"))
        {
            exam.copy((Exam)obj);
            if(isOn)
            {
                ExamPage.chosenAnswers.clear();
                ExamPage.i=0;
                isOn=false;
            }
            idStudent.setDisable(false);
            doneBTN.setDisable(false);
            nextBtn.setDisable(true);
            first.setDisable(true);
            second.setDisable(true);
            third.setDisable(true);
            forth.setDisable(true);

        }
        else if (request.equals("didn't find exam"))
            wrongFourDigitsCode();

        else if (request.equals("found student"))
        {
            student=new Student((Student)obj);
            startBtn.setDisable(false);
        }
        else if (request.equals("didn't find student"))
            wrongId();
        else if( request.equals("solvedExam added successfully"))
            updateSolvedExam(obj);
    }



    @FXML
    void nextPressed(ActionEvent event) {
        if (theCode.length() != 4)
            wrongFourDigitsCode();

        else {
            sendMessage("get list of codes", theCode);
        }

    }

    @FXML
    void startExamPressed(ActionEvent event)
    {
        if(!(exam.getSolvedExam().GetIsBuild()))
        {

            SolvedExam temp =new SolvedExam(exam.getTime(), exam);
            solvedExam.copy(temp);
            //System.out.println("exam id: "+exam.getId_exam());
            System.out.println("temp id " + temp.getId());
            sendMessage("new solvedExam",solvedExam);
            //System.out.println(solvedExam.getId());
        }
        else
            solvedExam.copy(exam.getSolvedExam());

        switchScreen("ExamPage");
    }



    private void sendMessage(String op, Object obj) {
        try {
            Message message = new Message(op, obj);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }


    private void wrongFourDigitsCode() {
        first.clear();
        second.clear();
        third.clear();
        forth.clear();
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("Wrong 4 digits code!" + "\n" + "Please try again.");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
    }
    private void wrongId(){

        idStudent.clear();
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("Wrong Student's ID "+"\n"+"Please try again.");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
    }
    private void updateSolvedExam(Object obj){
        solvedExam.copy((SolvedExam) obj);
    }

    @FXML
    void FirstCode(KeyEvent event) {
        theCode = first.getText();
        System.out.println(theCode);
        second.setDisable(false);
    }

    @FXML
    void SecondCode(KeyEvent event) {
        theCode += second.getText();
        third.setDisable(false);
    }

    @FXML
    void ThirdCode(KeyEvent event) {
        theCode += third.getText();
        forth.setDisable(false);
    }

    @FXML
    void ForthCode(KeyEvent event) {
        theCode += forth.getText();
    }

}
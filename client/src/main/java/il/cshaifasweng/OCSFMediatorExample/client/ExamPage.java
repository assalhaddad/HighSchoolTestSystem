package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.switchScreen;

public class ExamPage extends DoExam {

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

    private static int g=0;
    @FXML
    private Label timerLabel;

    public static int i = 0;
    static int additionalTime = 0;
    protected static long mills=0;
    protected static int seconds=0;
    protected static int minutes=0;

    public static List<Integer> chosenAnswers;

    private static Runnable task;
    private static Thread timerThread;
    private static long startTimeMillis;
    private static long remainingDelayMillis;

    protected boolean firstTime = true;
    protected static Timeline timeline;
    protected static Timeline timelineInFreeText;
    protected static long millsInFreeText=0;
    protected static int secondsinFreeText=0;
    protected static int minutesInFreeText=0;
    protected int sum=exam.getTime();



    @FXML
    void initialize() {

        if (!DoExam.isOn) {

            mills=0;
            seconds = 0;
            minutes = 0;
            millsInFreeText=0;
            secondsinFreeText=0;
            minutesInFreeText=0;

            chosenAnswers = new ArrayList<Integer>(Collections.nCopies(DoExam.exam.getQuestions().size(), 0));
            scheduleTask();
            EventBus.getDefault().register(this);
        }
        DoExam.isOn = true;

        if (firstTime) {
            timeline = new Timeline(new KeyFrame(Duration.millis(1), event -> {
                mills++;
                if(mills+millsInFreeText == 1000) {
                    mills = 0;
                    millsInFreeText=0;
                    seconds++;
                }
                if (seconds+secondsinFreeText == 60) {
                    seconds = 0;
                    secondsinFreeText=0;
                    minutes++;
                }
                String time = String.format("%02d:%02d", minutesInFreeText+minutes, secondsinFreeText+seconds);
                timerLabel.setText(time);
            }));

            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
        firstTime = false;

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

        answer1.setStyle("-fx-background-color: #FA0000; ");
        answer2.setStyle("-fx-background-color:  #008FFF; ");
        answer3.setStyle("-fx-background-color:  #08FF00; ");
        answer4.setStyle("-fx-background-color:  #FFFB00; ");


        if (chosenAnswers.get(i) == 1) {

            answer1.setStyle("-fx-background-color: #FF5C5C; ");
            answer2.setStyle("-fx-background-color:  #008FFF; ");
            answer3.setStyle("-fx-background-color:  #08FF00; ");
            answer4.setStyle("-fx-background-color:  #FFFB00; ");
        }

        if (chosenAnswers.get(i) == 2) {

            answer1.setStyle("-fx-background-color: #FA0000; ");
            answer2.setStyle("-fx-background-color:  #79C4FF; ");
            answer3.setStyle("-fx-background-color:  #08FF00; ");
            answer4.setStyle("-fx-background-color:  #FFFB00; ");
        }
        if (chosenAnswers.get(i) == 3) {

            answer1.setStyle("-fx-background-color: #FA0000; ");
            answer2.setStyle("-fx-background-color:  #008FFF; ");
            answer3.setStyle("-fx-background-color:  #77FF73; ");
            answer4.setStyle("-fx-background-color:  #FFFB00; ");
        }
        if (chosenAnswers.get(i) == 4) {

            answer1.setStyle("-fx-background-color: #FA0000; ");
            answer2.setStyle("-fx-background-color:  #008FFF; ");
            answer3.setStyle("-fx-background-color:  #08FF00; ");
            answer4.setStyle("-fx-background-color:  #FFFD7A; ");
        }

        checkIfDone();


    }

    @FXML
    void aboutPressed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("freeText.fxml"));
        Parent root = loader.load();
        FreeText freeText = loader.getController();
        timeline.pause();

        // Pass the current timer values to the second page
        freeText.setTimer(minutes, seconds,mills);

        Stage stage = (Stage) timerLabel.getScene().getWindow();
        stage.setScene(new Scene(root));
    }



    @FXML
    void answer1Selected(ActionEvent event) {


        answer1.setStyle("-fx-background-color: #FF5C5C; ");
        answer2.setStyle("-fx-background-color:  #008FFF; ");
        answer3.setStyle("-fx-background-color:  #08FF00; ");
        answer4.setStyle("-fx-background-color:  #FFFB00; ");

        chosenAnswers.set(i, 1);
        checkIfDone();

    }

    @FXML
    void answer2Selected(ActionEvent event) {


        answer1.setStyle("-fx-background-color:  #FA0000; ");
        answer2.setStyle("-fx-background-color:  #79C4FF; ");
        answer3.setStyle("-fx-background-color:  #08FF00; ");
        answer4.setStyle("-fx-background-color:  #FFFB00; ");


        chosenAnswers.set(i, 2);
        checkIfDone();

    }

    @FXML
    void answer3Selected(ActionEvent event) {

        answer1.setStyle("-fx-background-color:  #FA0000; ");
        answer2.setStyle("-fx-background-color:  #008FFF; ");
        answer3.setStyle("-fx-background-color:  #77FF73; ");
        answer4.setStyle("-fx-background-color:  #FFFB00; ");

        chosenAnswers.set(i, 3);
        checkIfDone();

    }

    @FXML
    void answer4Selected(ActionEvent event) {

        answer1.setStyle("-fx-background-color:  #FA0000; ");
        answer2.setStyle("-fx-background-color:  #008FFF; ");
        answer3.setStyle("-fx-background-color:  #08FF00; ");
        answer4.setStyle("-fx-background-color:  #FFFD7A; ");

        chosenAnswers.set(i, 4);
        checkIfDone();

    }

    @FXML
    void done(ActionEvent event) throws Exception {

        long endTimeMillis = System.currentTimeMillis();
        long totalTimeMillis = endTimeMillis - startTimeMillis;
        long totalTimeSeconds = totalTimeMillis / 1000;
        StudentData studentD = new StudentData(student, LocalDateTime.now().toString(), (int) (totalTimeSeconds / 60), true, chosenAnswers, solvedExam);
        solvedExam.calculateGrades();
        timerThread.interrupt();
        timeline.stop();
        if(timelineInFreeText!=null)
        {
            timelineInFreeText.stop();
            timelineInFreeText=null;
        }

        sendMessage("new studentData", studentD);

        App.setRoot("studentsPage");
    }

    private static void sendMessage(String op, Object obj) {
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
        else if (request.equals("studentData added successfully 2.0"))
            addedNewStudentData2();
        else if (request.equals("request approved successfully")) {
            additionalTime = (int) message.getObject();
            extendDelay();

        }
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

    private void addedNewStudentData2() {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Time is up!");
                alert.setHeaderText("The exam is done");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
    }

    private static void addedTime() {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Extra Time!");
                alert.setHeaderText("Another " + additionalTime + " minutes were added!");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });
    }

    private static void scheduleTask() {
        task = () -> {
            StudentData studentD = new StudentData(student, LocalDateTime.now().toString(), solvedExam.getUpdatedTime(), false, chosenAnswers, solvedExam);
            solvedExam.calculateGrades();
            timeline.stop();
            if(timelineInFreeText!=null)
            {
                timelineInFreeText.stop();
                timelineInFreeText=null;
            }
            timerThread.interrupt(); // Interrupt the timer thread after the task is executed
            sendMessage("new studentData 2.0", studentD);
            switchScreen("StudentPage");
        };

        timerThread = new Thread(() -> {
            try {
                Thread.sleep((long) exam.getTime() * 60 * 1000); // Delay for the time of the exam
                task.run();
            } catch (InterruptedException e) {
                // Timer interrupted, do nothing
            }
        });

        startTimeMillis = System.currentTimeMillis();
        remainingDelayMillis = (long) exam.getTime() * 60 * 1000; // Initial delay in millis
        timerThread.start();
    }

    protected static void extendDelay() {
        timerThread.interrupt();

        // Calculate new remaining delay with an additional 5 seconds
        //remainingDelayMillis +=  ((((additionalTime) * 60 * 1000)-((minutes+minutesInFreeText)*60*1000)+((seconds+secondsinFreeText)*1000)+mills+millsInFreeText));
        remainingDelayMillis += (long) (((additionalTime) * 60 * 1000)-(System.currentTimeMillis()-startTimeMillis));
        System.out.println(remainingDelayMillis);
        timerThread = new Thread(() -> {
            try {
                Thread.sleep(remainingDelayMillis);
                task.run();
            } catch (InterruptedException e) {
                // Timer interrupted, do nothing
            }
        });

        timerThread.start();
        addedTime();

        System.out.println("Delay extended.");
    }

    public void setTimer(int minutes, int seconds,long mills) {
        this.minutesInFreeText = minutes;
        this.secondsinFreeText = seconds;
        this.millsInFreeText=mills;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
        timeline.play();
    }

}
package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import javafx.scene.Parent;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

import static il.cshaifasweng.OCSFMediatorExample.client.App.switchScreen;

public class FreeText extends ExamPage {

    @FXML
    private Button doneBTN;

    @FXML
    private Label timerLabel;
    @FXML
    private Label freeTextTeacher;
    private int minutes;
    private int seconds;

    private boolean firstTime=true;
    @FXML
    private Label examTime;


    @FXML
    void initialize() {

        assert doneBTN != null : "fx:id=\"doneBTN\" was not injected: check your FXML file 'freeText.fxml'.";
        assert freeTextTeacher != null : "fx:id=\"freeTextTeacher\" was not injected: check your FXML file 'freeText.fxml'.";

        if (DoExam.exam.getFreeTextStudent() != "")
            freeTextTeacher.setText(DoExam.exam.getFreeTextStudent());
        if(firstTime) {

            timelineInFreeText = new Timeline(new KeyFrame(Duration.millis(1), event -> {
                millsInFreeText++;

                if(millsInFreeText+mills == 1000) {
                    millsInFreeText = 0;
                    mills=0;
                    secondsinFreeText++;
                }

                if (secondsinFreeText+seconds == 60) {
                    secondsinFreeText = 0;
                    seconds=0;
                    minutesInFreeText++;
                }
                String time = String.format("%02d:%02d", minutes + minutesInFreeText, seconds + secondsinFreeText);
                timerLabel.setText(time);
            }));

            timelineInFreeText.setCycleCount(Animation.INDEFINITE);
            timelineInFreeText.play();

        }
        firstTime=false;

        if(exam.getTime()+extraInFreeText==1)
            examTime.setText("The exam is "+(exam.getTime()+extraInFreeText)+" minute long.");
        else
            examTime.setText("The exam is "+(exam.getTime()+extraInFreeText)+" minutes long.");


    }

    private static void sendMessage(String op, Object obj) {
        try {
            Message message = new Message(op, obj);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    public void setTimer(int minutes, int seconds,long mills,int extra) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.mills=mills;
        this.extra=extra;
        if(mills == 1000) {
            this.mills=0;
            this.seconds++;
        }
        if (seconds == 60) {
            this.seconds=0;
            this.minutes++;
        }
        timerLabel.setText(String.format("%02d:%02d", this.minutes, this.seconds));

        if(!firstTime)
            timelineInFreeText.play();
    }


    @FXML
    void done(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("examPage.fxml"));
        Parent root = loader.load();
        ExamPage examPage = loader.getController();
        timelineInFreeText.pause();

        // Pass the current timer values to the first page
        examPage.setTimer(minutesInFreeText, secondsinFreeText,millsInFreeText,extraInFreeText);

        Stage stage = (Stage) timerLabel.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}
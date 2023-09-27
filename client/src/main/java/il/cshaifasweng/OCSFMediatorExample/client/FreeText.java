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

        if(sum==1)
            examTime.setText(examTime.getText()+sum+" minute long .");
        else
            examTime.setText(examTime.getText()+sum+" minutes long .");

        sendMessage("get updated time",exam);

    }

    private static void sendMessage(String op, Object obj) {
        try {
            Message message = new Message(op, obj);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    public void setTimer(int minutes, int seconds,long mills) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.mills=mills;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));

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
        examPage.setTimer(minutesInFreeText, secondsinFreeText,millsInFreeText);

        Stage stage = (Stage) timerLabel.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    @Subscribe
    public void handleMessage(Message message) {
        String request = message.getMessage();
        if (request.equals("updated time")) {
            sum=(int)message.getObject();


        }
    }
}



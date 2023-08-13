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

import static il.cshaifasweng.OCSFMediatorExample.client.App.switchScreen;

public class FreeText extends ExamPage {

    @FXML
    private Button doneBTN;


    @FXML
    private Label freeTextTeacher;

    @FXML
    void initialize() {
        //EventBus.getDefault().register(this);
        assert doneBTN != null : "fx:id=\"doneBTN\" was not injected: check your FXML file 'freeText.fxml'.";
        assert freeTextTeacher != null : "fx:id=\"freeTextTeacher\" was not injected: check your FXML file 'freeText.fxml'.";

        if(DoExam.exam.getFreeTextStudent()!="")
            freeTextTeacher.setText(DoExam.exam.getFreeTextStudent());
    }


    @FXML
    void done(ActionEvent event) {switchScreen("ExamPage");}



}
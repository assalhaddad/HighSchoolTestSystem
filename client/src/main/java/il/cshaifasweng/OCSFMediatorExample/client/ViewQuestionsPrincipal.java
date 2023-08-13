package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static il.cshaifasweng.OCSFMediatorExample.client.App.switchScreen;

public class ViewQuestionsPrincipal {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField answer1TF;

    @FXML
    private TextField answer2TF;

    @FXML
    private TextField answer3TF;

    @FXML
    private TextField answer4TF;

    @FXML
    private TextField correctTF;
    @FXML
    private TableColumn<Course, String> courseCol;

    @FXML
    private TableView<Course> tableView;

    @FXML
    private ComboBox<String> idCMB;

    @FXML
    private TextField subjectTF;

    @FXML
    private TextArea textTF;

    ObservableList<Question> questions;

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        sendMessage("get questions for principal",(Object)null);
    }
    private void sendMessage(String op, Object obj) {
        try {
            Message message = new Message(op, obj);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    @FXML
    void IdChosen(ActionEvent event) {
        for(int i=0; i<questions.size(); i++){
            if(questions.get(i).getId_question().equals(idCMB.getValue())){
                subjectTF.setText(questions.get(i).getSubject().getName());
                textTF.setText(questions.get(i).getText());
                answer1TF.setText("1) "+questions.get(i).getAnswer1());
                answer2TF.setText("2) "+questions.get(i).getAnswer2());
                answer3TF.setText("3) "+questions.get(i).getAnswer3());
                answer4TF.setText("4) "+questions.get(i).getAnswer4());
                ObservableList<Course> data = FXCollections.observableArrayList(questions.get(i).getCourses());
                courseCol.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
                tableView.setItems(data);
                if(questions.get(i).getCorrect() == 1)
                    correctTF.setText(answer1TF.getText());
                else if(questions.get(i).getCorrect() == 2)
                    correctTF.setText(answer2TF.getText());
                else if(questions.get(i).getCorrect() == 3)
                    correctTF.setText(answer3TF.getText());
                else
                    correctTF.setText(answer4TF.getText());
            }
        }
    }

    @Subscribe
    public void handleMessage(Message message) {
        String request = message.getMessage();
        Object obj = message.getObject();
        if (request.equals("questions list is ready for principal")) {
            questions = FXCollections.observableArrayList((ArrayList)obj);
            for(int i = 0; i< questions.size(); i++)
                idCMB.getItems().add(questions.get(i).getId_question());
        }
    }

}

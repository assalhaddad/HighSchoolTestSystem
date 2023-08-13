package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.StudentData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ViewExamsPrincipal {

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
    private Label answersLbl;

    @FXML
    private Label correctLbl;

    @FXML
    private TextField correctTF;

    @FXML
    private TextField courseTF;

    @FXML
    private TableColumn<Exam, Integer> pointsCol;

    @FXML
    private ComboBox<String> idCMB;

    @FXML
    private TableColumn<Exam, String> questionCol;

    @FXML
    private Rectangle rectangle;

    @FXML
    private TextField subjectTF;

    @FXML
    private TableView<Question> tableView;

    ObservableList<Exam> exams;
    Exam chosenExam = new Exam();

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        hide();
        sendMessage("get exams for exam page principal",null);
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
        Object obj = message.getObject();
        if(request.equals("exams list is ready for exam principal")){
            exams = FXCollections.observableArrayList((ArrayList)obj);
            for(int i = 0; i< exams.size(); i++)
                idCMB.getItems().add(exams.get(i).getId_exam());
        }
    }



    @FXML
    void chooseExam(ActionEvent event) {
        for(int i = 0; i<exams.size();i++){
            if(exams.get(i).getId_exam().equals(idCMB.getValue())){
                chosenExam.copy(exams.get(i));
                break;
            }
        }
        subjectTF.setText(chosenExam.getCourse().getSubject().getName());
        courseTF.setText(chosenExam.getCourse().getName());

        ObservableList<Question> data = FXCollections.observableArrayList(chosenExam.getQuestions());
        questionCol.setCellValueFactory(new PropertyValueFactory<Exam, String>("text"));
        pointsCol.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("points"));
        tableView.setItems(data);

    }

    @FXML
    void makeVisible(MouseEvent event) {
        show();
        Question chosenQuestion = tableView.getSelectionModel().getSelectedItem();
        answer1TF.setText("1) "+chosenQuestion.getAnswer1());
        answer2TF.setText("2) "+chosenQuestion.getAnswer2());
        answer3TF.setText("3) "+chosenQuestion.getAnswer3());
        answer4TF.setText("4) "+chosenQuestion.getAnswer4());
        if(chosenQuestion.getCorrect() == 1)
            correctTF.setText("1) "+chosenQuestion.getAnswer1());
        else if(chosenQuestion.getCorrect() == 2)
            correctTF.setText("2) "+chosenQuestion.getAnswer2());
        else if(chosenQuestion.getCorrect() == 3)
            correctTF.setText("3) "+chosenQuestion.getAnswer3());
        else
            correctTF.setText("4) "+chosenQuestion.getAnswer4());

    }

    public void hide(){
        rectangle.setVisible(false);
        answersLbl.setVisible(false);
        answer1TF.setVisible(false);
        answer2TF.setVisible(false);
        answer3TF.setVisible(false);
        answer4TF.setVisible(false);
        correctLbl.setVisible(false);
        correctTF.setVisible(false);
    }
    public void show(){
        rectangle.setVisible(true);
        answersLbl.setVisible(true);
        answer1TF.setVisible(true);
        answer2TF.setVisible(true);
        answer3TF.setVisible(true);
        answer4TF.setVisible(true);
        correctTF.setVisible(true);
        correctLbl.setVisible(true);
    }
}
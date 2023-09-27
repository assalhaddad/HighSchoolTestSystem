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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static il.cshaifasweng.OCSFMediatorExample.client.App.switchScreen;

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
    @FXML
    private ImageView v1;

    @FXML
    private ImageView v2;

    @FXML
    private ImageView v3;

    @FXML
    private ImageView v4;

    @FXML
    private TextField authorTF;

    @FXML
    private Button ViewExamsBtn;

    @FXML
    private Button ViewGradesBtn;

    @FXML
    private Button ViewQuestionsBtn;
    @FXML
    private Button ApproveBtn;
    @FXML
    private Button menuBtn;
    @FXML
    private VBox Menu;

    ObservableList<Exam> exams;
    Exam chosenExam = new Exam();

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        v1.setVisible(false);
        v2.setVisible(false);
        v3.setVisible(false);
        v4.setVisible(false);
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
        v1.setVisible(false);
        v2.setVisible(false);
        v3.setVisible(false);
        v4.setVisible(false);
        hide();
        for(int i = 0; i<exams.size();i++){
            if(exams.get(i).getId_exam().equals(idCMB.getValue())){
                chosenExam.copy(exams.get(i));
                break;
            }
        }
        subjectTF.setText(chosenExam.getCourse().getSubject().getName());
        courseTF.setText(chosenExam.getCourse().getName());
        authorTF.setText(chosenExam.getAuthor().getName());

        ObservableList<Question> data = FXCollections.observableArrayList(chosenExam.getQuestions());
        questionCol.setCellValueFactory(new PropertyValueFactory<Exam, String>("text"));
        pointsCol.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("points"));
        tableView.setItems(data);

    }

    @FXML
    void makeVisible(MouseEvent event) {
        v1.setVisible(false);
        v2.setVisible(false);
        v3.setVisible(false);
        v4.setVisible(false);
        show();
        Question chosenQuestion = tableView.getSelectionModel().getSelectedItem();
        answer1TF.setText("1) "+chosenQuestion.getAnswer1());
        answer2TF.setText("2) "+chosenQuestion.getAnswer2());
        answer3TF.setText("3) "+chosenQuestion.getAnswer3());
        answer4TF.setText("4) "+chosenQuestion.getAnswer4());
        if(chosenQuestion.getCorrect() == 1)
            v1.setVisible(true);
        else if(chosenQuestion.getCorrect() == 2)
            v2.setVisible(true);
        else if(chosenQuestion.getCorrect() == 3)
            v3.setVisible(true);
        else
            v4.setVisible(true);

    }

    public void hide(){
        rectangle.setVisible(false);
        answer1TF.setVisible(false);
        answer2TF.setVisible(false);
        answer3TF.setVisible(false);
        answer4TF.setVisible(false);
    }
    public void show(){
        rectangle.setVisible(true);
        answer1TF.setVisible(true);
        answer2TF.setVisible(true);
        answer3TF.setVisible(true);
        answer4TF.setVisible(true);
    }

    @FXML
    void ApproveRequestsEvent(ActionEvent event) throws IOException {
        EventBus.getDefault().unregister(this);
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        //loadSceneForButton("approveRequests.fxml");
        App.setRoot("approveRequests");
    }

    @FXML
    void LogOut(ActionEvent event) {
        EventBus.getDefault().unregister(this);
        switchScreen("Login");
    }

    @FXML
    void ViewQuestionsEvent(ActionEvent event) throws IOException {
        EventBus.getDefault().unregister(this);
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        App.setRoot("viewQuestionsPrincipal");
    }

    @FXML
    void ViewGradesEvent(ActionEvent event) throws IOException {
        EventBus.getDefault().unregister(this);
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        App.setRoot("viewGradesPrincipal");
    }

    @FXML
    void ViewExamsEvent(ActionEvent event) throws IOException {
        EventBus.getDefault().unregister(this);
        Menu.setVisible(false);
        menuBtn.setVisible(true);
        App.setRoot("viewExamsPrincipal");
    }

    @FXML
    void OpenMenu(ActionEvent event) {
        menuBtn.setVisible(false);
        Menu.setVisible(true);
    }
}
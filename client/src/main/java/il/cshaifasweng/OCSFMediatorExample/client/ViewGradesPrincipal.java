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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ViewGradesPrincipal {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField courseTF;

    @FXML
    private TableColumn<StudentData, Integer> gradeCol;

    @FXML
    private ComboBox<String> idCMB;

    @FXML
    private TableColumn<StudentData, String> nameCol;

    @FXML
    private TextField subjectTF;

    @FXML
    private TableView<StudentData> tableView;
    ObservableList<Exam> exams;
    Exam chosenExam = new Exam();

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

        ObservableList<StudentData> data = FXCollections.observableArrayList(chosenExam.getSolvedExam().getData());
        nameCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("name"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<StudentData, Integer>("grade"));
        tableView.setItems(data);
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        sendMessage("get exams for grades principal",null);
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
        if(request.equals("exams list is ready for grades principal")){
            exams = FXCollections.observableArrayList((ArrayList)obj);
            for(int i = 0; i< exams.size(); i++)
                idCMB.getItems().add(exams.get(i).getId_exam());
        }
    }

}

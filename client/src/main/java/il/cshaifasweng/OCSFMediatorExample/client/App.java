package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * JavaFX App
 */
public class App extends Application {

    private static  Stage appStage;
    private static Scene scene;
    private SimpleClient client;

    @Override
    public void start(Stage stage) throws IOException {
        EventBus.getDefault().register(this);
        client = SimpleClient.getClient();
        client.openConnection();
        scene = new Scene(loadFXML("Login"), 600, 400);
        stage.setScene(scene);
        stage.show();
        appStage = stage;
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void stop() throws Exception {
        // TODO Auto-generated method stub
        EventBus.getDefault().unregister(this);
        super.stop();
    }
    public static void setWindowTitle(String title) {
        appStage.setTitle(title);
    }
    public static void setContent(String pageName) throws IOException {
        Parent root = loadFXML(pageName);
        scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }
    public static void switchScreen(String screenName) {
        switch (screenName) {
            case "TeacherPage":
                Platform.runLater(() -> {
                    setWindowTitle("TeacherPage");
                    try {
                        setContent("TeacherPage");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case "Principal":
                Platform.runLater(() -> {
                    setWindowTitle("Principal");
                    try {
                        setContent("PrincipalPage");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case "Login":
                Platform.runLater(() -> {
                    setWindowTitle("Login");
                    try {
                        setContent("Login");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case "StudentPage":
                Platform.runLater(() -> {
                    setWindowTitle("StudentPage");
                    try {
                        setContent("StudentPage");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
        }
    }

    public static void displayError(String error){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR, error);
            alert.show();
        });
    }
    @Subscribe
    public void onWarningEvent(WarningEvent event) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, String.format("Message:%s\nTimestamp: %s\n",
                    event.getWarning().getMessage(),
                    event.getWarning().getTime().toString())
            );
            alert.show();
        });

    }
    public static void main(String[] args) {
        launch();
    }

}
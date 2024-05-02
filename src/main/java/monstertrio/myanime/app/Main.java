package monstertrio.myanime.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

//Main Done
public class Main extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/login.fxml")));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("MyAniTracker - Login");
            stage.show();
        } catch (Exception e) {
            logger.error("An exception occurred!", e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
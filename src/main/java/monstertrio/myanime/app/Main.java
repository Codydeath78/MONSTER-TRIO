package monstertrio.myanime.app;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import monstertrio.myanime.app.helpers.DatabaseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("AniTracker");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            logger.error("",e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
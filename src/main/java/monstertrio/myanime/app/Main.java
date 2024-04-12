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
    public void start(Stage primaryStage) {
        try {

            // Load scenes from FXML files
            Parent loginRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene loginScene = new Scene(loginRoot);

            Parent signupRoot = FXMLLoader.load(getClass().getResource("signup.fxml"));
            Scene signUpScene = new Scene(signupRoot);

            Parent animeListRoot = FXMLLoader.load(getClass().getResource("animeList.fxml"));
            Scene animeListScene = new Scene(animeListRoot);

            Parent animeAddRoot = FXMLLoader.load(getClass().getResource("animeAdd.fxml"));
            Scene animeAddScene = new Scene(animeAddRoot);

            // Set initial scene
            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Anime Application - Login");
            primaryStage.show();
        } catch (Exception e){
            logger.error("",e);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
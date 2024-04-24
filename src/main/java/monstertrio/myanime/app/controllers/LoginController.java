package monstertrio.myanime.app.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import monstertrio.myanime.app.helpers.DatabaseHelper;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class LoginController implements Initializable {

    @FXML
    public TextField tf_username;
    @FXML
    public TextField tf_password;
    @FXML
    public Button button_login;
    @FXML
    public Button button_signup;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DatabaseHelper helper = new DatabaseHelper();
                String username = tf_username.getText();
                String password = tf_password.getText();

                try {
                    int userId = helper.loginUser(username.trim(), password);
                    if (userId != -1) {
                        DatabaseHelper.changeScene(actionEvent, "/views/AnimeList.fxml", "MyAniTracker - My List", userId, 3);
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Login Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Incorrect Username or Password");
                        alert.showAndWait();
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    DatabaseHelper.changeScene(actionEvent, "/views/SignUp.fxml", "MyAniTracker - Sign Up", 0, 0);
                } catch (RuntimeException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}


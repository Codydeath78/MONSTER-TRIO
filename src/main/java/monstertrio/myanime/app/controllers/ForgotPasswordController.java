package monstertrio.myanime.app.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import monstertrio.myanime.app.helpers.DatabaseHelper;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ForgotPasswordController implements Initializable {

    @FXML
    public Label label_message;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_user_id;

    @FXML
    private TextField tf_new_password;

    @FXML
    private Button button_submit;

    @FXML
    private Button button_login;

    private final DatabaseHelper helper = new DatabaseHelper();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label_message.setText("");
        button_submit.setOnAction(actionEvent -> {
            String username = tf_username.getText();
            String userId = tf_user_id.getText();
            String password = tf_new_password.getText();

            if (username.isEmpty() || userId.isEmpty() || password.isEmpty()) {
                label_message.setText("Fill all the fields!");
                label_message.setStyle("-fx-text-fill: red;");
            } else {
               int intUserId = Integer.parseInt(userId);

                try {
                    helper.changeUserPassword(intUserId,username,password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                label_message.setText("Success! Redirecting to login page...");
                label_message.setStyle("-fx-text-fill: black;");
                DatabaseHelper.changeScene(actionEvent, "/views/Login.fxml", "MyAniTracker - Login", -1, 0);
            }
        });
        button_login.setOnAction(actionEvent -> {
            try {
                DatabaseHelper.changeScene(actionEvent, "/views/Login.fxml", "MyAniTracker - Sign Up", -1, 0);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        });

    }
}

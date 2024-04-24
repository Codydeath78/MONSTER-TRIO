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

public class SignupController implements Initializable {

    @FXML
    public TextField tf_name;

    @FXML
    public TextField tf_username;

    @FXML
    public TextField tf_password;

    @FXML
    public Button button_signup;

    @FXML
    public Button button_login;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseHelper helper = new DatabaseHelper();
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = tf_name.getText();
                String password = tf_password.getText();
                String username = tf_username.getText();

                boolean signupsuccessful = false;
                try {
                    signupsuccessful = helper.signUpUser(name, username, password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (signupsuccessful) {
                    System.out.println("Sign up successful! Redirecting to login scene...");
                    DatabaseHelper.changeScene(actionEvent, "/views/Login.fxml", "AniTracker - Login", 0, 0);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Signup Error");
                    alert.setHeaderText(null);
                    alert.setContentText("The username you entered is already taken. Please choose a different username.");
                    alert.showAndWait();
                }
            }
        });

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DatabaseHelper.changeScene(actionEvent, "/views/Login.fxml", "MyAniTracker - Login", 0, 0);
            }
        });

    }











/*    public void switchToSignUp(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/SignUp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setScene(scene);
        stage.show();

    }
    public void displaySignUpImage(){
        mySignUpImageView.setImage(mySignUpImage);
    }


    public SignupController() {
        mySignUpImage = new Image(getClass().getResourceAsStream("/images/first.jpg"));

    }

    public void goToAnimeList(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/AnimeList.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setScene(scene);
        stage.show();
    }*/
}

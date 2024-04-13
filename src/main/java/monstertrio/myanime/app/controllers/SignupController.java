package monstertrio.myanime.app.controllers;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import monstertrio.myanime.app.helpers.DatabaseHelper;

import java.io.IOException;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseHelper helper= new DatabaseHelper();
        //create event handler for button
            //create instance database helper class
            //we will call the sign up method from databasehelper
            // we will input into the method: tf_name, tf_username, and tf_password
            //if function returns true we will indicate to user that sign in worked and send them back to login scene
            //if it returns false that means that the username they entered is incorrect.
            // an alert should popup explaining that they need a different username
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = tf_name.getText();
                String password = tf_password.getText();
                String username = tf_username.getText();

                boolean signupsuccessful;
                try {
                    signupsuccessful = helper.signUpUser(name, username, password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (signupsuccessful) {
                    System.out.println("Sign up successful. Redirecting to login scene...");
                    Parent loginRoot = null;
                    try {
                        loginRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene loginScene = new Scene(loginRoot);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Signup Error");
                    alert.setHeaderText(null);
                    alert.setContentText("The username you entered is already taken. Please choose a different username.");
                    alert.showAndWait();
                }
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

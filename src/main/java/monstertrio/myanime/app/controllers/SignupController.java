package monstertrio.myanime.app.controllers;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    public TextField tf_name;

    @FXML
    public TextField tf_username;

    @FXML
    public TextField tf_password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //create event handler for button
            //create instance database helper class
            //we will call the sign up method from databasehelper
            // we will input into the method: tf_name, tf_username, and tf_password
            //if function returns true we will indicate to user that sign in worked and send them back to login scene
            //if it returns false that means that the username they entered is incorrect.
            // an alert should popup explaining that they need a different username
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

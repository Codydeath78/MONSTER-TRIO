package monstertrio.myanime.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ImageView mySignUpImageView;
    Image mySignUpImage;
    public void switchToSignUp(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setScene(scene);
        stage.show();

    }
    public void displaySignUpImage(){
        mySignUpImageView.setImage(mySignUpImage);
    }


    public SignupController() {
        mySignUpImage = new Image(getClass().getResourceAsStream("first.jpg"));

    }

}

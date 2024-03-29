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

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ImageView myLoginImageView;
    Image myLoginImage;

    public LoginController() {
            myLoginImage = new Image(getClass().getResourceAsStream("chopcrycover.jpg"));

    }
    public void displayLoginImage(){
        myLoginImageView.setImage(myLoginImage);
    }

    public void switchToLogin(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setScene(scene);
        stage.show();


    }


}

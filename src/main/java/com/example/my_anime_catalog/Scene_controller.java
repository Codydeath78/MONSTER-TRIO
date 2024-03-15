package com.example.my_anime_catalog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene_controller {
   // public ImageView myImageView;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ImageView myImageView;
    @FXML
    ImageView myImageView_2;
    @FXML
    ImageView myImageView_3;
    Button myButton;
    Image myImage;
    Image myImage_2;
    Image myImage_3;

    public Scene_controller() {
        myImage = new Image(getClass().getResourceAsStream("first.jpg"));
        myImage_2 = new Image(getClass().getResourceAsStream("second.jpg"));
        myImage_3 = new Image(getClass().getResourceAsStream("third.jpg"));
    }

    public void displayImage(){
        myImageView.setImage(myImage);
     }

    public void displayImage_2(){
        myImageView_2.setImage(myImage_2);
    }
    public void displayImage_3(){
        myImageView_3.setImage(myImage_3);
    }

public void switchToScene1(ActionEvent event) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scene 1.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    Scene scene = new Scene(fxmlLoader.load(), 700, 400);
    stage.setScene(scene);
    stage.show();


}

public void switchToScene2(ActionEvent event) throws IOException
{
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scene 2.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    Scene scene = new Scene(fxmlLoader.load(), 700, 400);
    stage.setScene(scene);
    stage.show();

}

    public void switchToScene3(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("account_creation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setScene(scene);
        stage.show();

    }




}

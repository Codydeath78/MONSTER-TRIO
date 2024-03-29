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

public class AnimeListController {


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ImageView myAnimeListImageView;
    Image myAnimeListImage;
    public void switchToAnimeList(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AnimeList.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setScene(scene);
        stage.show();

    }



    public void displayAnimeListImage(){
        myAnimeListImageView.setImage(myAnimeListImage);
    }


    public AnimeListController() {
        myAnimeListImage = new Image(getClass().getResourceAsStream("second.jpg"));

    }


}

package monstertrio.myanime.app.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import monstertrio.myanime.app.helpers.DatabaseHelper;
import monstertrio.myanime.app.models.Anime;
import monstertrio.myanime.app.models.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnimeAddController{

    private final DatabaseHelper helper;
    private int userId;

    public AnimeAddController(){
         helper = new DatabaseHelper();
    }
    public void setUserInformation(int userId) {
        this.userId = userId;
    }

    @FXML
    public Button addButtonpage;
    @FXML
    private TableView<Anime> animeTableView;
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        DatabaseHelper helper = new DatabaseHelper();

        addButtonpage.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                Anime anime = new Anime(Anime.getTitle().getText(),Anime.getDesc().getText(),Anime.getRating().getText(),Anime.getStatus().getText(),Anime.getGenre().getText(),Anime.getUserId().getText());

                add(anime);
            }



        });




     }



public void add(Anime anime){
    animeTableView.getItems().add(anime);
}
/*

    private Scene scene;
    private Parent root;

    @FXML
    ImageView myAnimeAddImageView;
    Image myAnimeAddImage;
    */
/*
    *
    public void switchToAnimeAdd(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/AnimeAdd.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setScene(scene);
        stage.show();

    }
*//*

    public void displayAnimeAddImage(){
        myAnimeAddImageView.setImage(myAnimeAddImage);
    }


    public AnimeAddController() {
        myAnimeAddImage = new Image(getClass().getResourceAsStream("/images/first.jpg"));

    }
    public void goToAnimeList(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/AnimeList.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setScene(scene);
        stage.show();

    }

*/


}

package monstertrio.myanime.app.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import monstertrio.myanime.app.helpers.DatabaseHelper;
import monstertrio.myanime.app.models.Anime;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AnimeAddController implements Initializable {

    @FXML
    private TextField tf_title;

    @FXML
    private TextField tf_description;

    @FXML
    private ChoiceBox<Integer> cb_rating;

    @FXML
    private TextField tf_status;

    @FXML
    private TextField tf_genre;

    @FXML
    private TextField tf_image_url;

    @FXML
    private Button button_add_anime;

    @FXML
    public Button button_back;

    private int userId;

    public void setUserInformation(int userId) {
        this.userId = userId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_rating.getItems().addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        button_add_anime.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                DatabaseHelper helper = new DatabaseHelper();
                String title = tf_title.getText();
                String description = tf_description.getText();
                Integer rating = cb_rating.getValue();
                String status = tf_status.getText();
                String genre = tf_genre.getText();
                String imageUrl = tf_image_url.getText();
                if (title != null && !title.isEmpty() && description != null && !description.isEmpty() && rating != null && status != null && !status.isEmpty() && genre != null && !genre.isEmpty() && imageUrl != null && !imageUrl.isEmpty()) {
                    Anime anime = new Anime(title, description, rating, status, genre, imageUrl, userId);
                    try {
                        helper.addAnime(anime);
                        DatabaseHelper.changeScene(actionEvent, "/views/AnimeList.fxml", "MyAniTracker - My List", userId, 3);
                    } catch (RuntimeException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Encountered");
                        alert.setHeaderText(null);
                        alert.setContentText("We had a problem adding that anime. Try Again.");
                        alert.showAndWait();
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Validation Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter text, not blank text!");
                    alert.showAndWait();
                }
            }
        });

        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    DatabaseHelper.changeScene(actionEvent, "/views/AnimeList.fxml", "MyAniTracker -  My List", userId, 3);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}

package monstertrio.myanime.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import monstertrio.myanime.app.models.Anime;
import monstertrio.myanime.app.helpers.DatabaseHelper;

import java.util.List;


public class EditAnimeController {

    @FXML
    private TextField tf_new_title;

    @FXML
    private TextArea ta_new_desc;

    @FXML
    private ChoiceBox<Integer> cb_new_rating;

    @FXML
    private ChoiceBox<String> cb_new_status;

    @FXML
    private TextField tf_new_genre;

    @FXML
    private TextField tf_new_image_url;

    @FXML
    private Button button_submit;

    @FXML
    private Button button_back;

    private Anime anime;
    private Stage stage;
    private AnimeListController animeListController;

    public void setAnimeListController(AnimeListController animeListController) {
        this.animeListController = animeListController;
    }
    public void setAnime(Anime anime) {
        this.anime=anime;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        DatabaseHelper helper=new DatabaseHelper();
        cb_new_rating.getItems().addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        cb_new_status.getItems().addAll(List.of("Watching", "Completed", "On Hold", "Dropped", "Plan to Watch"));

        if(anime!=null) {
            tf_new_title.setText(anime.getTitle());
            ta_new_desc.setText(anime.getDesc());
            cb_new_rating.setValue(anime.getRating());
            cb_new_status.setValue(anime.getStatus());
            tf_new_genre.setText(anime.getGenre());
            tf_new_image_url.setText(anime.getImageUrl());
        }
        button_submit.setOnAction(actionEvent -> {
            if(anime != null){
            anime.setTitle(tf_new_title.getText());
            anime.setDesc(ta_new_desc.getText());
            anime.setRating(cb_new_rating.getValue());
            anime.setStatus(cb_new_status.getValue());
            anime.setGenre(tf_new_genre.getText());
            anime.setImageUrl(tf_new_image_url.getText());
            helper.updateAnime(anime);
            }
            if (animeListController != null) {
                animeListController.initializeUI();
            }
            stage.close();
        });

        button_back.setOnAction(actionEvent -> stage.close());
    }
}

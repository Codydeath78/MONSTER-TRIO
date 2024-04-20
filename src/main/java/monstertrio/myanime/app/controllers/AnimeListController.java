package monstertrio.myanime.app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import monstertrio.myanime.app.helpers.DatabaseHelper;
import monstertrio.myanime.app.models.Anime;

import java.net.URL;
import java.util.ResourceBundle;

public class AnimeListController implements Initializable {

    @FXML
    private Button button_delete_anime;

    @FXML
    private Button button_add_anime;

    @FXML
    private Button button_log_out;

    @FXML
    private Label label_welcome;

    @FXML
    private TableView<Anime> animeTableView;

    @FXML
    private TableColumn<Anime, Integer> column_index;

    @FXML
    private TableColumn<Anime, String> column_image;

    @FXML
    private TableColumn<Anime, String> column_title;

    @FXML
    private TableColumn<Anime, String> column_desc;

    @FXML
    private TableColumn<Anime, Integer> column_rating;

    @FXML
    private TableColumn<Anime, String> column_genre;

    @FXML
    private TableColumn<Anime, String> column_status;

    private final DatabaseHelper helper;
    private int userId;
    public ObservableList<Anime> animeList= FXCollections.observableArrayList();
    public AnimeListController() {
        helper = new DatabaseHelper();
    }

    public void setUserInformation(int userId, String name) {
        this.userId = userId;
        label_welcome.setText("Welcome, "+name+"!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        column_index.setCellValueFactory(new PropertyValueFactory<Anime, Integer>("id"));
        column_image.setCellValueFactory(new PropertyValueFactory<Anime, String>("imageUrl"));
        column_title.setCellValueFactory(new PropertyValueFactory<Anime, String>("title"));
        column_desc.setCellValueFactory(new PropertyValueFactory<Anime, String>("desc"));
        column_rating.setCellValueFactory(new PropertyValueFactory<Anime, Integer>("rating"));
        column_status.setCellValueFactory(new PropertyValueFactory<Anime, String>("status"));
        column_genre.setCellValueFactory(new PropertyValueFactory<Anime, String>("genre"));
        animeList=helper.getAnimeListForUser(userId);
        animeTableView.setItems(animeList);
        System.out.println("Viewing items for user with id: "+userId);
        button_add_anime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DatabaseHelper.changeScene(actionEvent,"/views/AnimeAdd.fxml","MyAniTracker - Add Anime", userId,4);
            }
        });
        button_log_out.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Switching Scenes");
                DatabaseHelper.changeScene(actionEvent,"/views/Login.fxml","MyAniTracker - Login", -1,0);
            }
        });

        button_delete_anime.setOnAction(e->{
            Anime anime=animeTableView.getSelectionModel().getSelectedItem();
            animeTableView.getItems().remove(anime);
            helper.deleteAnime(anime.getId());
        });
    }
}

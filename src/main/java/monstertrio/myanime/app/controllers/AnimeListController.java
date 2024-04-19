package monstertrio.myanime.app.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import monstertrio.myanime.app.helpers.DatabaseHelper;
import monstertrio.myanime.app.models.Anime;

public class AnimeListController {

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
    private TableColumn<Anime, String> column_progress;

    @FXML
    private TableColumn<Anime, Void> column_delete;

    private final DatabaseHelper helper;
    private int userId;
    private String name;

    public AnimeListController() {
        helper = new DatabaseHelper();
    }

    public void setUserInformation(int userId, String name) {
        this.userId = userId;
        label_welcome.setText("Welcome, "+name+"!");
    }

    public void initialize() {
        column_index.setCellValueFactory(new PropertyValueFactory<>("#"));
        column_image.setCellValueFactory(new PropertyValueFactory<>("Image"));
        column_title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        column_desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        column_rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        column_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        column_progress.setCellValueFactory(new PropertyValueFactory<>("progress"));
        column_delete.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Anime anime = getTableView().getItems().get(getIndex());
                    animeTableView.getItems().remove(anime);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        button_add_anime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DatabaseHelper.changeScene(actionEvent,"/views/AnimeAdd.fxml","MyAniTracker - Add Anime", userId,4);
            }
        });
        ObservableList<Anime> animeList = helper.getAnimeListForUser(userId);
        animeTableView.setItems(animeList);
    }
}

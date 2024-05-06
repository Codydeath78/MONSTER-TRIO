package monstertrio.myanime.app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import monstertrio.myanime.app.helpers.DatabaseHelper;
import monstertrio.myanime.app.models.Anime;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnimeListController implements Initializable {

    @FXML
    public Button button_edit_anime;

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

    private int userId;

    public ObservableList<Anime> animeList = FXCollections.observableArrayList();

    public void setUserInformation(int userId, String name) {
        this.userId = userId;
        label_welcome.setText("Welcome, " + name + "!");
        initializeUI();
    }

    private void loadImage(String imageUrl, ImageView imageView) {
        new Thread(() -> {
            try {
                Image image = new Image(imageUrl);
                imageView.setImage(image);
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
            }
        }).start();
    }

    private void openEditPage(Anime anime) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AnimeEdit.fxml"));
            HBox editPopup = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("AniTracker - Edit Anime");

            EditAnimeController controller = loader.getController();
            controller.setAnime(anime);
            controller.setStage(popupStage);
            controller.setAnimeListController(this);

            Scene scene = new Scene(editPopup);
            popupStage.setScene(scene);
            popupStage.setTitle("AniTracker - Edit Anime");
            popupStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initializeUI() {
        DatabaseHelper helper = new DatabaseHelper();
        column_index.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1));
                }
            }
        });

        column_image.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));
        column_image.setCellFactory(col -> new TableCell<>() {
            private final ImageView imageView = new ImageView();

            {
                imageView.setFitWidth(80);
                imageView.setPreserveRatio(true);
                setGraphic(imageView);
            }

            @Override
            protected void updateItem(String imageUrl, boolean empty) {
                super.updateItem(imageUrl, empty);
                if (empty || imageUrl == null) {
                    imageView.setImage(null);
                } else {
                    loadImage(imageUrl, imageView);
                }
            }
        });

        column_title.setCellValueFactory(new PropertyValueFactory<>("title"));

        column_desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        column_desc.setCellFactory(tc -> new TableCell<>() {
            private final Text text = new Text();

            {
                text.wrappingWidthProperty().bind(this.widthProperty());
                setGraphic(text);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    text.setText(null);
                } else {
                    text.setText(item);
                }
            }
        });

        column_rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        column_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        column_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        animeList = helper.getAnimeListForUser(userId);
        animeTableView.setItems(animeList);

        System.out.println("Viewing items for user with id: " + userId);

        button_add_anime.setOnAction(actionEvent -> DatabaseHelper.changeScene(actionEvent, "/views/AnimeAdd.fxml", "MyAniTracker - Add Anime", userId, 4));

        button_log_out.setOnAction(actionEvent -> {
            System.out.println("Switching Scenes");
            DatabaseHelper.changeScene(actionEvent, "/views/Login.fxml", "MyAniTracker - Login", -1, 0);
        });

        button_delete_anime.setOnAction(e -> {
            Anime anime = animeTableView.getSelectionModel().getSelectedItem();
            animeTableView.getItems().remove(anime);
            helper.deleteAnime(anime.getId());
        });

        button_edit_anime.setOnAction(e -> {
            Anime anime = animeTableView.getSelectionModel().getSelectedItem();
            if (anime != null) {
                openEditPage(anime);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

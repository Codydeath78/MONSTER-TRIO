package monstertrio.myanime.app.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import monstertrio.myanime.app.helpers.DatabaseHelper;
import monstertrio.myanime.app.models.Anime;

public class AnimeListController {

    public Button button_add_anime;
    public Button button_log_out;
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
    

    public AnimeListController(){
        helper = new DatabaseHelper();
    }
    public void setUserInformation(int userId){
        this.userId=userId;

    }
    public void initialize() {
        column_index.setCellValueFactory(new PropertyValueFactory<>("index"));
        column_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        column_title.setCellValueFactory(new PropertyValueFactory<>("title"));
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
        ObservableList<Anime> animeList = helper.getAnimeListForUser(userId);
        animeTableView.setItems(animeList);
}
    /*public void switchToAnimeList(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/AnimeList.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setScene(scene);
        stage.show();

    }

    public void displayAnimeListImage(){
        myAnimeListImageView.setImage(myAnimeListImage);
    }


    public AnimeListController() {
        myAnimeListImage = new Image(getClass().getResourceAsStream("/images/second.jpg"));

    }


    public void goToAnimeAdd(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/AnimeAdd.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setScene(scene);
        stage.show();

    }
    public void EXIT(ActionEvent event) throws IOException
    {
        Platform.exit();

    }*/

}

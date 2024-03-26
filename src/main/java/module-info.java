module com.example.my_anime_catalog {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.my_anime_catalog to javafx.fxml;
    exports com.example.my_anime_catalog;
}
module monstertrio.myanime {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens monstertrio.myanime to javafx.fxml;
    exports monstertrio.myanime;
}
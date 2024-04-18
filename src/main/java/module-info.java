module monstertrio.myanime {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires java.sql;
    requires org.controlsfx.controls;

    exports monstertrio.myanime.app;
    exports monstertrio.myanime.app.controllers;
    exports monstertrio.myanime.app.models;
    exports monstertrio.myanime.app.helpers;
    opens monstertrio.myanime.app.controllers to javafx.fxml;
}
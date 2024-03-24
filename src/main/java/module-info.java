module monstertrio.myanime {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires java.sql;

    exports monstertrio.myanime.app;
    exports monstertrio.myanime.app.controllers;
    exports monstertrio.myanime.app.models;
    exports monstertrio.myanime.app.helpers;
}
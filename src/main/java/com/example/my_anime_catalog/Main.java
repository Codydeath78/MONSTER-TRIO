package com.example.my_anime_catalog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException
    {
        try
        {
            //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scene 1.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 400);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //Rectangle rectangle = new Rectangle(50, 50, 200, 100);
        //stage.setTitle("Hello!");
        //stage.setScene(scene);
        //stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
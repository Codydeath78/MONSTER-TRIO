package com.example.my_anime_catalog;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class controller {
    @FXML
    private ImageView imageView;
    ImageView myImageView;

    //Image myImage = new Image(String.valueOf(Main.class.getResource("jonathan-cuevas-jonathan-cuevas-2.jpg")));
    Image image1 = new Image("jonathan-cuevas-jonathan-cuevas-2.jpg", true);

  // public void displayImage() {
   //    myImageView.setImage(myImage);
  // }
  public void initialize() {
      // Set the loaded image to the ImageView
      imageView.setImage(image1); // Use the appropriate image reference
  }


}

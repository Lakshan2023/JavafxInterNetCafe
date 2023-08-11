package com.example.demo2;

import LoginManager.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Load fxml file Welcome.fxml using FXML Loader
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/demo2/Welcome.fxml"));

            // Create the new scene with dimensions 753 and 483 and setting the scene for primary stage
            Scene scene = new Scene(fxmlLoader.load(), 753, 483);
            stage.setScene(scene);

            // Making the stage not resizable
            stage.setResizable(false);

            // Set the stage without title bar
            stage.initStyle(StageStyle.UNDECORATED);

            // Show the stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        // Loading the Item details from text file
        ItemManager.LoadItemDetails();

        /*Loading Account details from text files and
         setting it to LoginPage.Accounts*/
        LoginPage.Accounts = LoginPage.loadAccountDetails();
        launch();
    }
}

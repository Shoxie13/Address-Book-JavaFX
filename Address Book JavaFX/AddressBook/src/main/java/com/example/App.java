/**********************************************
Workshop 5
Course:JAV444 - Semester 4
Last Name: Abdi
First Name: Tareq
ID: 123809196
Section: 
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature - TA
Date: 09/03/2022
**********************************************/

package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addressBook" + ".fxml"));

        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Address Book");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
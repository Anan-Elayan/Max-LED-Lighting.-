package com.example.max_led_lighting;

import com.example.max_led_lighting.interfase.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**

    Name: Anan Elayan
    ID: 1211529

    ♦ Driver is the main class responsible for launching the JavaFX application.
    ♦ Variables:
           1) mainView: An instance of the MainView class, representing the main graphical user interface.
    ♦ Methods:
           1) start: The overridden method from the Application class. It is called when the application is launched and is responsible for setting up the main GUI and displaying the Stage.
           2) main: The main method serves as the entry point for the Java application. It launches the JavaFX application by calling the launch method with the given command-line arguments.
*/

public class Driver extends Application {

    MainView mainView;

    @Override
    public void start(Stage stage) throws Exception {
        mainView = new MainView();
        Scene scene = new Scene(mainView , 900,415);
        stage.setScene(scene);
        stage.setTitle("Max LED Lighting");
        //stage.setResizable(false);
        stage.getIcons().add(new Image("images/iconApp.jpg"));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package com.example.max_led_lighting.interfase;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;



/**
   Name: Anan Elayan
   ID: 1211529

    ♦ Warning is a class that extends VBox and represents a simple warning dialog in JavaFX.
    ♦ Variables:
             1) ok: An instance of the CustomButton class representing the "OK" button in the warning dialog.
             2) primaryStage: The Stage for the warning dialog.
    ♦ Methods:
             1) Warning: The constructor for the Warning class. It creates a warning dialog with a specified message and an "OK" button.
    ♦ Inner Class:
             1) HandleButtonWarning: An inner class that implements the EventHandler interface. It handles the action event for the "OK" button, closing the warning dialog when the button is clicked.
*/

public class Warning extends VBox {

	private final Stage primaryStage;

	public Warning(String msg) {
		setAlignment(Pos.CENTER);

		Label label = new Label(msg);
		label.setStyle("-fx-text-fill: white");
		Font font = Font.font(20);
		label.setFont(font);
		setStyle("-fx-background-color: black");


		CustomButton btnOk = new CustomButton("ok");
		btnOk.changeSize(25);



		setSpacing(7);
		getChildren().addAll(label, btnOk);

		handelButtonWarning handelButton = new handelButtonWarning();
		btnOk.setOnAction(handelButton);

		primaryStage = new Stage();
		primaryStage.setScene(new Scene(this, 520, 150));
		primaryStage.setTitle("warning");
		primaryStage.getIcons().add(new Image("images/iconApp.jpg"));
		primaryStage.show();

	}

	class handelButtonWarning implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {

			primaryStage.close();
		}

	}
}

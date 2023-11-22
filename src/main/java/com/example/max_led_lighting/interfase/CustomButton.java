package com.example.max_led_lighting.interfase;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
   Name: Anan Elayan
   ID: 1211529

   ♦ CustomButton is a class that extends Button and represents a customized button in JavaFX.
   ♦ Variables:
             1) text: A variable to store the text of the button (not used in the provided code).
             2) style: A string representing the default style of the button.
   ♦ Methods:
             1) CustomButton: The constructor for the CustomButton class. It sets the text and style of the button, as well as the background color to transparent.
             2) setBackground: A method to set the background color of the button. It converts the color to a hexadecimal format and replaces the "transparent" placeholder in the style string.
             3) changeSize: A method to change the font size of the button.
*/


public class CustomButton extends Button {

    String text;
    String style = ("-fx-text-fill: #f7f7f7  ;-fx-background-color: transpernt; -fx-border-color: #f5c843 ; -fx-border-width: 0 0  2 0 ; -fx-font-weight: bold");

    public CustomButton(String text) {
       setText(text);
       setStyle(style);
       setBackground(Color.TRANSPARENT);
    }

    public void setBackground(Color color) {
        String hex = String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
        style = style.replace("transparent", hex);
        setStyle(style);
    }
    public void changeSize(int size){
        this.setStyle("-fx-text-fill: #f7f7f7  ;-fx-background-color: transpernt; -fx-border-color: #f5c843 ; -fx-border-width: 0 0  2 0 ; -fx-font-weight: bold ; -fx-font-size:"+size+"px;");
    }
}

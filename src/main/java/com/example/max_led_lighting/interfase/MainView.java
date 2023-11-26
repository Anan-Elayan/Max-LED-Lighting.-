package com.example.max_led_lighting.interfase;

import com.example.max_led_lighting.model.Data;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Name: Anan Elayan
 * ID: 1211529
 *
 *    ♦ Aim Class:
 *             1) This JavaFX class, named MainView, represents the graphical user interface (GUI) for an LED lighting application.
 *    ♦ Variables:
 *             1) GUI components: Various JavaFX UI elements such as buttons, labels, text areas, etc.
 *             2) file: Represents the file selected by the user.
 *             3) data: An instance of the Data class, responsible for handling data-related operations.
 *             4) index, size: Variables related to the LED array.
 *             5) LED: An array to store LED data.
 *             6) count: Refers to the table that prints, whether the result, the figure, or the table.
 *
 *    ♦ Methods:
 *             1) drawLayout(): To draw an approximate diagram of the algorithm.
 *             2) repeatedNumber(): to check if the input number already exit in array LED's.
 *             3) isZeroValueFromLED(): in this method to check if exit cell in array led is zero ( no LED).
 *             4) getSize(): in this methode to return size array of LED
 *
 *   ♦ Initialization:
 *             1) The constructor initializes the GUI components, sets up event handlers, and adds components to the scene.
 *             2) GUI components are organized into panes (leftPane and centerPane).
 *             3) Toggle buttons and file-related components are included for manual and file input.
 *             4) Styling and positioning of components are set in the constructor and methods.
 *

 * */


public class MainView extends AnchorPane {
    // declaration  components UI
    private final TextArea textAreaResult;
    private final TextArea textAreaDetails;
    private final TextArea rightTextArea;

    // declaration selected file
    FileChooser fileChooser = new FileChooser();
    private final Label lblSource;
    private final Label lblLED;
    private final Spinner<Integer> spinnerSource;
    private final RadioButton radioButtonFromFile;
    private final RadioButton radioButtonManualInput;
    private final TextField txtFieldLED;
    private final TextField txtFieldPath;
    private final CustomButton btnShowResult;
    private final CustomButton btnSelectFile;
    private final CustomButton btnConnect;
    private final ScrollPane scrollPane;

    // file: this is the file saved the data ( read from it )
    File file;
    // declaration object of class Data
    Data data;
    // indicates the first table ( result table ) and so on...
    int count = 1;

    // size = size Source {First array } , index indicate the LED
    int index, powerSize = 0;
    // declaration array of Lead [ To save inside the array ]
    public static int[] LED;





    public MainView() {

        // initialization of component UI and set styl and location on screen
        Pane centerPane = new Pane();
        centerPane.setStyle("-fx-background-color:white");
        centerPane.setPrefHeight(415);
        centerPane.setPrefWidth(500);
        centerPane.setLayoutX(0);
        centerPane.setLayoutY(0);
        Image image = new Image("images/background_image.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(415);
        imageView.setFitWidth(575);

        radioButtonManualInput = new RadioButton("Manual Input");
        Font fontRadioButtonManualInput = Font.font(String.valueOf(FontPosture.REGULAR), FontWeight.BOLD, 15);
        radioButtonManualInput.setFont(fontRadioButtonManualInput);

        radioButtonFromFile = new RadioButton("From File");
        Font fontRadioButtonFromFile = Font.font(String.valueOf(FontPosture.REGULAR), FontWeight.BOLD, 15);
        radioButtonFromFile.setFont(fontRadioButtonFromFile);

        ToggleGroup group = new ToggleGroup();
        radioButtonFromFile.setToggleGroup(group);
        radioButtonManualInput.setToggleGroup(group);

        lblSource = new Label("# Source");
        Font fontLblSource = Font.font(String.valueOf(FontPosture.REGULAR), FontWeight.BOLD, 15);
        lblSource.setFont(fontLblSource);
        lblLED = new Label("Leads");
        Font fontLblLED = Font.font(String.valueOf(FontPosture.REGULAR), FontWeight.BOLD, 15);
        lblLED.setFont(fontLblLED);
        spinnerSource = new Spinner<>();
        txtFieldLED = new TextField();
        btnShowResult = new CustomButton("Show Result");
        // initialization of component UI and set styl and location on screen
        btnSelectFile = new CustomButton("Select File");
        txtFieldPath = new TextField();
        btnSelectFile.changeSize(15);
        btnConnect = new CustomButton("Connect");
        btnConnect.changeSize(13);
        CustomButton btnNext = new CustomButton("NEXT");
        CustomButton btnPrivies = new CustomButton("PRIVIES");
        textAreaResult = new TextArea();
        textAreaDetails = new TextArea();
        scrollPane = new ScrollPane();
        SpinnerValueFactory<Integer> spinnerValueFactorySource = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        spinnerSource.setValueFactory(spinnerValueFactorySource);

        //basic right pane to print power+led
        Pane rightPane = new Pane();
        rightPane.setStyle("-fx-background-color:  transparent  ;  -fx-border-radius: 10px 0px 0px 30px  ; -fx-background-radius: 10px  0px 0px 30px");
        rightPane.setLayoutX(560);
        rightPane.setLayoutY(0);
        rightPane.setPrefWidth(223);
        rightPane.setPrefHeight(415);
        DropShadow dropShadowRightPane = new DropShadow();
        dropShadowRightPane.setColor(javafx.scene.paint.Color.web("#f5c843"));
        dropShadowRightPane.setWidth(-13.0);
        dropShadowRightPane.setHeight(0.0);
        dropShadowRightPane.setRadius(17.0);
        dropShadowRightPane.setSpread(0.0);
        rightPane.setEffect(dropShadowRightPane);


        // to print power + led right text area
        rightTextArea = new TextArea();
        rightTextArea.setLayoutX(6);
        rightTextArea.setLayoutY(0);
        rightTextArea.setPrefWidth(223);
        rightTextArea.setPrefHeight(415);
        rightTextArea.setEditable(false);
        rightTextArea.setStyle("-fx-control-inner-background: black; -fx-border-color: #f5c843;-fx-font-size: 13px;-fx-font-weight: bold");
        DropShadow dropShadowLeftPane = new DropShadow();
        dropShadowLeftPane.setColor(javafx.scene.paint.Color.web("#f5c843"));
        dropShadowLeftPane.setWidth(-13.0);
        dropShadowLeftPane.setHeight(0.0);
        dropShadowLeftPane.setRadius(17.0);
        dropShadowLeftPane.setSpread(0.0);
        rightTextArea.setEffect(dropShadowLeftPane);
        rightPane.getChildren().add(rightTextArea);

        // initialize instance of Data class
        data = new Data();

        //*****************************************************        Appearance      ******************************************************

        // What appears when you first see the components.
        radioButtonFromFile.setVisible(true);
        radioButtonManualInput.setVisible(true);
        txtFieldPath.setVisible(false);
        btnSelectFile.setVisible(false);
        textAreaResult.setVisible(false);
        textAreaDetails.setVisible(false);
        lblLED.setVisible(false);
        txtFieldLED.setVisible(false);
        spinnerSource.setVisible(false);
        lblSource.setVisible(false);
        btnConnect.setVisible(false);
        btnShowResult.setVisible(false);
        btnNext.setVisible(false);
        btnPrivies.setVisible(false);
        scrollPane.setVisible(false);

        //********************************************************       position       ***********************************************

        // position components on the screen
        radioButtonManualInput.setStyle("-fx-text-fill: white");
        radioButtonManualInput.setLayoutX(138);
        radioButtonManualInput.setLayoutY(41);

        radioButtonFromFile.setStyle("-fx-text-fill: white");
        radioButtonFromFile.setLayoutX(365);
        radioButtonFromFile.setLayoutY(41);

        lblSource.setStyle("-fx-text-fill: white");
        lblSource.setLayoutX(66);
        lblSource.setLayoutY(153);

        lblLED.setStyle("-fx-text-fill: white");
        lblLED.setLayoutX(70);
        lblLED.setLayoutY(236);

        spinnerSource.setPrefHeight(27);
        spinnerSource.setPrefWidth(333);
        spinnerSource.setLayoutX(137);
        spinnerSource.setLayoutY(151);

        txtFieldLED.setPrefHeight(27);
        txtFieldLED.setPrefWidth(333);
        txtFieldLED.setLayoutX(137);
        txtFieldLED.setLayoutY(231);

        btnShowResult.setLayoutX(210);
        btnShowResult.setLayoutY(324);
        btnShowResult.setPrefHeight(27);
        btnShowResult.setPrefWidth(149);
        btnShowResult.changeSize(20);

        btnConnect.setLayoutX(480);
        btnConnect.setLayoutY(227);

        //*******************************************************************       RadioButton         **********************************************************************************


        // action when clicked on radioButton manual input
        radioButtonManualInput.setOnAction(e2 -> {
            count=0;
            //The elements that appear and the elements that disappear
            LED = null;
            scrollPane.setContent(null);
            rightTextArea.clear();
            textAreaDetails.clear();
            textAreaResult.clear();
            txtFieldPath.setVisible(false);
            btnSelectFile.setVisible(false);
            btnPrivies.setVisible(false);
            btnNext.setVisible(false);
            textAreaResult.setVisible(false);
            scrollPane.setVisible(false);
            textAreaDetails.setVisible(false);
            lblSource.setVisible(true);
            lblLED.setVisible(true);
            spinnerSource.setVisible(true);
            txtFieldLED.setVisible(true);
            btnShowResult.setVisible(true);
            btnConnect.setVisible(true);
            btnConnect.setLayoutX(480);
            btnConnect.setLayoutY(227);
            spinnerSource.setDisable(false);

            // add all input LED to array LED when clicked on button ( connect ),
            // and the size number leads equal number of power set button connect Disable when fill the array.
            btnConnect.setOnAction(e -> {
                // array of led is not empty their exit numbers
                if(LED!=null){
                    spinnerSource.setDisable(true);
                }
                // value from text file of led
                String valueOfLed = txtFieldLED.getText().trim();
                // if text file LED is not empty ( exit data )
                if (!valueOfLed.isEmpty()) {
                    // number format exception
                    try {
                        // data in text field LED as a number
                        int leadValue = Integer.parseInt(valueOfLed);
                        // if input data is Negative
                        if (leadValue <= 0) {
                            new Warning("Not Valid Input Data. Please enter a positive number.");
                            return;
                        }
                        // if input data in textFiled is not number
                        else if (!valueOfLed.matches("[0-9]*")) {
                            new Warning("Invalid Input Data");
                            return;
                        }

                        // if array is empty and number input data in textFiled grater than OR equal size power
                        if (LED == null || index >= LED.length) {
                            powerSize = spinnerSource.getValue();
                            // size the array LED = value from spinner
                            LED = new int[powerSize];
                            index = 0;
                        }
                        // else add input leads to the array LED The array exists (created)
                        else {
                            // data from text filed led grater the power (spinner)
                            if (leadValue > spinnerSource.getValue()) {
                                new Warning("Please Enter Led lees than " + spinnerSource.getValue());
                                return;
                            } else {
                                // to check If the number does not exist before.It is added to the array
                                if (!repeatedNumber(LED, leadValue)) {
                                    txtFieldLED.clear();
                                    LED[index] = leadValue;
                                    index++;
                                    String PowerAndLed = "Power: " + index + " Led : " + leadValue;
                                    // to print on the right text Area
                                    rightTextArea.appendText("  " + PowerAndLed + "\n");
                                }
                                // the LED exit before.
                                else {
                                    new Warning("The LED is already exist");
                                }
                            }
                        }
                        // # input data in textFiled leads = size power (spinner). and button add set Disable. array LED is full
                        if (index >= LED.length) {
                            btnConnect.setDisable(true);
                            new Warning("All lights have been added successfully.\n");
                        }
                    }
                    // if input data in text fields is String
                    catch (NumberFormatException ex) {
                        new Warning("Not Valid Input Data. Please enter a valid number.\n");
                        return;
                    }
                }
                // if text fields lead is empty
                else {
                    new Warning("Please enter the LED\n");
                }
            });

            // red all data input from user ( number of source and number of leads )
            // and call the method mainFunction from Data class to do algorithm and print result on text Area
            btnShowResult.setOnAction(A -> {
                // if array of leads is empty ( no input leads) and call method from Data class
                if (LED == null || LED.length == 0) {
                    new Warning("There are no input lights !!\n");
                }
                // call method from Data class
                else {
                    // what is the component show and handle position
                    lblLED.setVisible(false);
                    txtFieldLED.setVisible(false);
                    spinnerSource.setVisible(false);
                    lblSource.setVisible(false);
                    btnShowResult.setVisible(false);
                    txtFieldPath.setVisible(false);
                    spinnerSource.setVisible(false);
                    txtFieldLED.setVisible(false);
                    btnConnect.setVisible(false);
                    btnSelectFile.setVisible(false);
                    textAreaResult.setVisible(true);
                    textAreaResult.setLayoutY(90);
                    textAreaResult.setLayoutX(50);
                    textAreaResult.setPrefHeight(280);
                    btnNext.setVisible(true);
                    btnNext.setLayoutY(370);
                    btnNext.setLayoutX(482);
                    btnPrivies.setVisible(true);
                    btnPrivies.setLayoutY(370);
                    btnPrivies.setLayoutX(50);

                    // action button next to iterate the number of text Filed
                    btnNext.setOnAction(Anetx -> {
                        count++;
                        // textFiled details ->table
                        if (count == 2) {
                            textAreaResult.setVisible(false);
                            textAreaDetails.setVisible(true);
                            textAreaDetails.setLayoutY(90);
                            textAreaDetails.setLayoutX(50);
                            textAreaDetails.setPrefHeight(280);
                        }
                        // draw
                        else if (count == 3) {
                            textAreaResult.setVisible(false);
                            scrollPane.setVisible(true);
                            textAreaDetails.setVisible(false);
                            scrollPane.setLayoutY(90);
                            scrollPane.setLayoutX(50);
                            scrollPane.setPrefHeight(280);
                            scrollPane.setPrefWidth(465);
                        }
                    });
                    // action button next to iterate the number of text Filed
                    btnPrivies.setOnAction(privies -> {
                        count--;
                        // text Filed details -> table
                        if (count == 2) {
                            textAreaResult.setVisible(false);
                            textAreaDetails.setVisible(true);
                            textAreaDetails.setLayoutY(90);
                            textAreaDetails.setLayoutX(50);
                            textAreaDetails.setPrefHeight(280);
                        }
                        // text filed result
                        else if (count == 1) {
                            textAreaResult.setVisible(true);
                            scrollPane.setVisible(false);
                            textAreaDetails.setVisible(false);
                            textAreaResult.setLayoutY(90);
                            textAreaResult.setLayoutX(50);
                            textAreaResult.setPrefHeight(280);
                        }
                    });
                    // if no cell in array LED not equal zero (if number of Led  less than number of power and call method to do algorithm and print the result)
                    if (isZeroValueFromLED(LED)) {
                        textAreaResult.clear();
                        textAreaDetails.clear();
                        ArrayList<Integer> result = data.mainFunction(LED, textAreaResult, textAreaDetails);
                        scrollPane.setContent(drawLayout(result));

                    }
                    else {
                        new Warning("Some information is wrong");
                    }
                }
            });

        });


        // action when clicked on radioButton from file input
        radioButtonFromFile.setOnAction(e -> {
            count=0;
            //The elements that appear and the elements that disappear
            LED = null;
            rightTextArea.clear();
            txtFieldPath.clear();
            lblLED.setVisible(false);
            txtFieldLED.setVisible(false);
            spinnerSource.setVisible(false);
            btnPrivies.setVisible(false);
            btnNext.setVisible(false);
            textAreaResult.setVisible(false);
            scrollPane.setVisible(false);
            textAreaDetails.setVisible(false);
            lblSource.setVisible(false);
            txtFieldPath.setVisible(true);
            btnSelectFile.setVisible(true);
            btnShowResult.setVisible(true);
            btnConnect.setVisible(true);
            btnConnect.setLayoutX(480);
            btnConnect.setLayoutY(169);
            txtFieldPath.setLayoutX(153);
            txtFieldPath.setLayoutY(175);
            btnSelectFile.setLayoutX(52);
            btnSelectFile.setLayoutY(168);
            txtFieldPath.setPrefWidth(322);

            //open new stage to select file
            btnSelectFile.setOnAction(a -> {
                Stage stage = new Stage();
                file = fileChooser.showOpenDialog(stage);
                txtFieldPath.setText(file.getAbsolutePath());
                btnConnect.setDisable(false);
                txtFieldPath.setEditable(false);
                rightTextArea.clear();
            });


            // add all input LED to array LED when clicked on button ( connect ), and the size number leads equal number of power set button add Disable.
            btnConnect.setOnAction(l -> {
                // handle file
                try {
                    // red from file
                    Scanner scanner = new Scanner(file);
                    // file  empty
                    if (file.length() == 0) {
                        new Warning("There is no information in the file.\n");
                    } else {
                        // Clear the previously read data
                        if (radioButtonFromFile.isSelected()) {

                            LED = null;
                            index = 0;
                        }
                        // number format exception
                        try {
                            // while file exit lines red line by line
                            while (scanner.hasNextLine()) {
                                // size = first line in file = size power
                                powerSize = scanner.nextInt();
                                // if number of power negative
                                if (powerSize <= 0) {
                                    new Warning("Not Valid Input Data. Please enter a positive number.\n");
                                    return;
                                }
                                // second line in file is empty
                                try {
                                    scanner.nextLine();
                                    String line = scanner.nextLine();
                                    // add the second line to the array ( leads)
                                    String[] data = line.split(" ");
                                    // while number of leads lees than length power
                                    while (index < data.length) {
                                        // number format exception
                                        try {
                                            // if empty array OR out of index power array
                                            if (LED == null || index >= LED.length) {
                                                LED = new int[powerSize];
                                            }
                                            // leads
                                            int ledValue = Integer.parseInt(data[index]);

                                            // not negative
                                            if (ledValue >= 0) {
                                                // no cell zero in array lLED
                                                if (!repeatedNumber(LED, Integer.parseInt(data[index]))) {
                                                    // add element to the array LED
                                                    LED[index] = Integer.parseInt(data[index]);
                                                    textAreaDetails.clear();
                                                    //to print right side
                                                    String PowerAndLed = "Power: " + (index + 1) + " Led : " + data[index];
                                                    rightTextArea.appendText("  " + PowerAndLed + "\n");
                                                }
                                                // two number as the same
                                                else {
                                                    new Warning("LED " + data[index] + " already Exit.\n");
                                                    return;
                                                }
                                            }
                                            // there exit number value of led is negative
                                            else {
                                                new Warning("there exit negative value" + ledValue + " in this file.\n");
                                                return;
                                            }
                                            index++;
                                        }

                                        // if data in file string
                                        catch (NumberFormatException f) {
                                            new Warning("The information inside the file is incorrect.\n");
                                            return;
                                        }
                                    }

                                }
                                // no input leads in file (second line in file is empty)
                                catch (NoSuchElementException elementException) {
                                    new Warning("There are no input lights !!\n");
                                    return;
                                }
                                // if number of power not equal number of leads input
                                if (!getSize(LED)) {
                                    new Warning("Please check number of Led's");
                                    return;
                                }
                                // all data from file is true
                                new Warning("All lights have been added successfully.\n");
                            }
                        }
                        // if data in file string (not numbers)
                        catch (InputMismatchException f) {
                            new Warning("The information inside the file is incorrect.\n");
                        }
                        scanner.close();
                    }
                }
                // if not found file
                catch (FileNotFoundException x) {
                    new Warning("Not Found File.\n");
                }
            });

            // red all data input from file ( number of source and number of leads )
            // and call the method mainFunction from Data class to do algorithm and print result on text Area
            btnShowResult.setOnAction(c -> {
                // if array of leads is empty ( no input leads) and call method from Data class
                if (LED == null || LED.length == 0) {
                    new Warning("There are no input lights !!\n");
                    return;
                }
                // call method from Data class
                else {
                    // what is the component show and handle position
                    lblLED.setVisible(false);
                    txtFieldLED.setVisible(false);
                    spinnerSource.setVisible(false);
                    lblSource.setVisible(false);
                    btnShowResult.setVisible(false);
                    txtFieldPath.setVisible(false);
                    spinnerSource.setVisible(false);
                    txtFieldLED.setVisible(false);
                    btnConnect.setVisible(false);
                    btnSelectFile.setVisible(false);
                    textAreaResult.setVisible(true);
                    textAreaResult.setLayoutY(90);
                    textAreaResult.setLayoutX(50);
                    textAreaResult.setPrefHeight(280);
                    btnNext.setVisible(true);
                    btnNext.setLayoutY(370);
                    btnNext.setLayoutX(482);
                    btnPrivies.setVisible(true);
                    btnPrivies.setLayoutY(370);
                    btnPrivies.setLayoutX(50);

                    // action button next to iterate the number of text Filed
                    btnNext.setOnAction(Anetx -> {
                        count++;
                        // textFiled details ->table
                        if (count == 2) {
                            textAreaResult.setVisible(false);
                            textAreaDetails.setVisible(true);
                            textAreaDetails.setLayoutY(90);
                            textAreaDetails.setLayoutX(50);
                            textAreaDetails.setPrefHeight(280);
                        }
                        // draw
                        else if (count == 3) {
                            textAreaResult.setVisible(false);
                            scrollPane.setVisible(true);
                            textAreaDetails.setVisible(false);
                            scrollPane.setLayoutY(90);
                            scrollPane.setLayoutX(50);
                            scrollPane.setPrefHeight(280);
                            scrollPane.setPrefWidth(465);

                        }

                    });
                    // action button next to iterate the number of text Filed
                    btnPrivies.setOnAction(privies -> {
                        count--;
                        // text Filed details -> table
                        if (count == 2) {
                            textAreaResult.setVisible(false);
                            textAreaDetails.setVisible(true);
                            textAreaDetails.setLayoutY(90);
                            textAreaDetails.setLayoutX(50);
                            textAreaDetails.setPrefHeight(280);
                        }
                        // text filed result
                        else if (count == 1) {
                            textAreaResult.setVisible(true);
                            scrollPane.setVisible(false);
                            textAreaDetails.setVisible(false);
                            textAreaResult.setLayoutY(90);
                            textAreaResult.setLayoutX(50);
                            textAreaResult.setPrefHeight(280);
                        }

                    });
                    // if no cell in array LED not equal zero (if number of Led  less than number of power and call method to do algorithm and print the result)
                    if (isZeroValueFromLED(LED)) {
                        textAreaResult.clear();
                        textAreaDetails.clear();
                        ArrayList<Integer> result = data.mainFunction(LED, textAreaResult, textAreaDetails);
                        scrollPane.setContent(drawLayout(result));
                    }
                    // there exit cell in array LED is zero ( no value of led)
                    else {
                        new Warning("Please check the data again.\n");
                    }
                }
            });
        });




        //*******************************************************************       Add the basePane         **********************************************************************************

        // add all component to the Center Pane
        centerPane.getChildren().addAll(
                imageView, radioButtonManualInput, radioButtonFromFile,
                lblSource, spinnerSource, lblLED, txtFieldLED, btnConnect,
                btnShowResult, btnSelectFile, txtFieldPath, scrollPane, textAreaResult,
                textAreaDetails, btnNext, btnPrivies);

        // add center pane and left pane to the base pane screen
        getChildren().addAll(centerPane, rightPane);
    }




    /**
     * To draw an approximate diagram of the algorithm.
     * */
    public AnchorPane drawLayout(ArrayList<Integer> ledSize) {

        // initialize the AnchorPane to add the scrollPane
        AnchorPane anchorPane = new AnchorPane();
        // initialize the two hash table for the circle and the rectangle
        Hashtable<Integer, Circle> circleMap = new Hashtable<>();
        Hashtable<Integer, Rectangle> rectangleMap = new Hashtable<>();

        // eterate the number of led's
        for (int i = 1; i <= LED.length; i++) {
            Circle circle = new Circle(20); // Radius is 20
            circle.setFill(Color.YELLOW);
            // handle position circle in AnchorPane
            circle.setLayoutX(50);
            circle.setLayoutY(i * 60);

            Label labelLed = new Label(String.valueOf(LED[i - 1]));
            labelLed.setStyle("-fx-font-size: 15px;-fx-font-weight: bold");
            // handle position label led in AnchorPane
            labelLed.setLayoutX(50);
            labelLed.setLayoutY(i * 60);
            // fil the hash table depends on the number of led's
            circleMap.put(LED[i - 1], circle);

            // initialize the rectangle Indicates to power
            Rectangle rectangle = new Rectangle(80, 30);
            rectangle.setFill(Color.GREEN);
            // handle position rectangle led in AnchorPane
            rectangle.setLayoutX(250);
            rectangle.setLayoutY(i * 50);

            // initialize the label power
            Label labelPower = new Label("Power " + i);
            labelPower.setStyle("-fx-font-size: 15px;-fx-font-weight: bold");
            labelPower.setAlignment(Pos.CENTER);
            // handle position label power in AnchorPane
            labelPower.setLayoutX(250);
            labelPower.setLayoutY(i * 50);
            // fill the hash table depends on the number of power
            rectangleMap.put(i, rectangle);
            anchorPane.getChildren().addAll(circle, rectangle, labelLed, labelPower);

        }
        // iterate the number of led's
        for (int k = 0; k < ledSize.size(); k++) {
            // return the circle and rectangle depends on the number of led
            Circle circle = circleMap.get(Integer.parseInt(String.valueOf(ledSize.get(k))));
            Rectangle rectangle = rectangleMap.get(Integer.parseInt(String.valueOf(ledSize.get(k))));
            if (circle != null && rectangle != null) {
                // initialize the line between circle and rectangle
                Line line = new Line();
                // handle position line
                line.setStartX(circle.getLayoutX()+ circle.getRadius() / 2);
                line.setStartY(circle.getLayoutY()+ circle.getRadius() / 2);
                line.setEndX(rectangle.getLayoutX());
                line.setEndY(rectangle.getLayoutY() + rectangle.getHeight() / 2);
                anchorPane.getChildren().add(line);
            }
        }
        // add space at the end of anchorPane
        anchorPane.setPrefHeight((ledSize.size() + 1) * 110);
        return anchorPane;
    }


    /**
     * to check if the input number already exit in array LED's
     */
    private static boolean repeatedNumber(int[] LED, int data) {
        for (int i = 0; i < LED.length; i++) {
            if (LED[i] == data) {
                return true;
            }
        }
        return false;
    }

    /**
     * in this method to check if exit cell in array led is zero ( no LED)
     */
    private static boolean isZeroValueFromLED(int[] LED) {
        for (int i = 0; i < LED.length; i++) {
            if (LED[i] == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * in this methode to return size array of LED
     */
    private static boolean getSize(int[] LED) {
        for (int i = 0; i < LED.length; i++) {
            if (LED[i] == 0) {
                return false;
            }
        }
        return true;
    }


}
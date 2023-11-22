package com.example.max_led_lighting.interfase;

import com.example.max_led_lighting.model.Data;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Name: Anan Elayan
 * ID: 1211529

   ♦ Aim Class:
            1) This JavaFX class, named MainView, represents the graphical user interface (GUI) for an LED lighting application.
   ♦ Variables:
            1) GUI components: Various JavaFX UI elements such as buttons, labels, text areas, etc.
            2) file: Represents the file selected by the user.
            3)data: An instance of the Data class, responsible for handling data-related operations.
            4) index, size: Variables related to the LED array.
            5) LED: An array to store LED data.

   ♦ Methods:
            1) styleBtnEnterData(): Handles the styling and functionality of the "Enter Data" button, including manual input and file input.
            2) styleBtnResult(): Handles the styling for displaying result details.
            3)styleBtnMoreDetails(): Handles the styling for displaying more detailed information.

  ♦ Initialization:
            1) The constructor initializes the GUI components, sets up event handlers, and adds components to the scene.
            2) GUI components are organized into panes (leftPane and centerPane).
            3) Toggle buttons and file-related components are included for manual and file input.
            4) Styling and positioning of components are set in the constructor and methods.
 */


public class MainView extends AnchorPane {

    // declaration  components UI
    private final TextArea textAreaResult;
    private final TextArea textAreaDetails;
    private final ImageView imageViewLED;
    private final TextArea rightTextArea;
    //private final  TextArea rightTextArea;
    // declaration selected file
    FileChooser fileChooser = new FileChooser();
    private final Label lblSource;
    private final Label lblLED;
    private final Spinner<Integer> spinnerSource;
    private final RadioButton radioButtonFromFile;
    private final TextField txtFieldLED;
    private final TextField txtFieldPath;
    private final CustomButton leftBtnEnterData;
    private final CustomButton btnShowResult;
    private final CustomButton leftBtnMoreDetails;
    private final CustomButton btnSelectFile;
    private final RadioButton radioButtonManualInput;
    private final CustomButton leftBtnResult;
    private final CustomButton btnConnect;

    // file: this is the file saved the data ( read from it )
    File file;
    // declaration object of class Data
    Data data;

    // size = size Source {First array } , index indicate the LED
    int index, powerSize = 0;
    // declaration array of Lead [ To save inside the array ]
    public static int[] LED;

    public MainView() {

        // initialization of component UI and set styl and location on screen
        Pane leftPane = new Pane();
        leftPane.setStyle("-fx-background-color:  black ; -fx-border-color: #f5c843 ;  -fx-border-radius: 0px  10px 30px 0px  ; -fx-background-radius: 0px  10px 30px 0px");
        leftPane.setPrefHeight(415);
        leftPane.setPrefWidth(166);
        leftPane.setLayoutX(0);
        leftPane.setLayoutY(0);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(javafx.scene.paint.Color.web("#f5c843"));
        dropShadow.setWidth(13.0); // Set the width
        dropShadow.setHeight(0.0); // Set the height
        dropShadow.setRadius(17.0); // Set the radius
        dropShadow.setSpread(0.0);
        leftPane.setEffect(dropShadow);


        // initialization of component UI and set styl and location on screen
        leftBtnEnterData = new CustomButton("Enter Data");
        leftBtnEnterData.changeSize(15);
        leftBtnEnterData.setPrefHeight(25);
        leftBtnEnterData.setPrefWidth(109);
        leftBtnEnterData.setLayoutX(23);
        leftBtnEnterData.setLayoutY(76);

        // initialization of component UI and set styl and location on screen
        leftBtnResult = new CustomButton("Result");
        leftBtnResult.changeSize(15);
        leftBtnResult.setPrefHeight(25);
        leftBtnResult.setPrefWidth(109);
        leftBtnResult.setLayoutX(23);
        leftBtnResult.setLayoutY(175);

        // initialization of component UI and set styl and location on screen
        leftBtnMoreDetails = new CustomButton("More Details");
        leftBtnMoreDetails.changeSize(15);
        leftBtnMoreDetails.setPrefHeight(25);
        leftBtnMoreDetails.setPrefWidth(115);
        leftBtnMoreDetails.setLayoutX(23);
        leftBtnMoreDetails.setLayoutY(276);
        leftBtnResult.setDisable(true);
        leftBtnMoreDetails.setDisable(true);

        // initialization of component UI and set styl and location on screen
        Pane centerPane = new Pane();
        centerPane.setStyle("-fx-background-color:white");
        centerPane.setPrefHeight(415);
        centerPane.setPrefWidth(529);
        centerPane.setLayoutX(131);
        centerPane.setLayoutY(0);
        Image image = new Image("images/background_image.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(415);
        imageView.setFitWidth(565);

        // initialization of component UI and set styl and location on screen
        radioButtonManualInput = new RadioButton("Manual Input");
        Font fontRadioButtonManualInput = Font.font(String.valueOf(FontPosture.REGULAR),FontWeight.BOLD,15);
        radioButtonManualInput.setFont(fontRadioButtonManualInput);
        radioButtonFromFile = new RadioButton("From File");
        Font fontRadioButtonFromFile = Font.font(String.valueOf(FontPosture.REGULAR),FontWeight.BOLD,15);
        radioButtonFromFile.setFont(fontRadioButtonFromFile);
        ToggleGroup group = new ToggleGroup();
        radioButtonFromFile.setToggleGroup(group);
        radioButtonManualInput.setToggleGroup(group);

        // initialization of component UI and set styl and location on screen
        lblSource = new Label("# Source");
        Font fontLblSource = Font.font(String.valueOf(FontPosture.REGULAR),FontWeight.BOLD,15);
        lblSource.setFont(fontLblSource);
        lblLED = new Label("Leads");
        Font fontLblLED = Font.font(String.valueOf(FontPosture.REGULAR),FontWeight.BOLD,15);
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


        textAreaResult = new TextArea();
        textAreaDetails = new TextArea();

        radioButtonFromFile.setVisible(false);
        radioButtonManualInput.setVisible(false);
        txtFieldPath.setVisible(false);
        btnSelectFile.setVisible(false);
        lblSource.setVisible(false);
        lblLED.setVisible(false);
        spinnerSource.setVisible(false);
        txtFieldLED.setVisible(false);
        btnShowResult.setVisible(false);
        textAreaResult.setVisible(false);
        textAreaDetails.setVisible(false);
        btnConnect.setVisible(false);

        // initialization the object of Data Class to call function on it ( result , details , and algorithm function )
        data = new Data();

        // action left button (Enter Data)
        leftBtnEnterData.setOnAction(e -> {
            styleBtnEnterData();
        });
        // action left button (Result)
        leftBtnResult.setOnAction(a -> {
            styleBtnResult();
        });
        // action left button (More Details)
        leftBtnMoreDetails.setOnAction(c -> {
            styleBtnMoreDetails();
        });

        Pane rightPane  = new Pane();
        rightPane.setStyle("-fx-background-color:  transparent  ;  -fx-border-radius: 10px 0px 0px 30px  ; -fx-background-radius: 10px  0px 0px 30px");
        rightPane.setLayoutX(545);
        rightPane.setLayoutY(0);
        rightPane.setPrefWidth(223);
        rightPane.setPrefHeight(415);
        DropShadow dropShadowRightPane = new DropShadow();
        dropShadow.setColor(javafx.scene.paint.Color.web("#f5c843"));
        dropShadow.setWidth(-13.0);
        dropShadow.setHeight(0.0);
        dropShadow.setRadius(17.0);
        dropShadow.setSpread(0.0);
        rightPane.setEffect(dropShadowRightPane);



        rightTextArea = new TextArea();
        rightTextArea.setLayoutX(6);
        rightTextArea.setLayoutY(0);
        rightTextArea.setPrefWidth(223);
        rightTextArea.setPrefHeight(415);
        rightTextArea.setEditable(false);
        rightTextArea.setStyle("-fx-control-inner-background: black; -fx-border-color: #f5c843;-fx-font-size: 13px;-fx-font-weight: bold");
        Image imageLED = new Image("images/background_image.jpg");
        imageViewLED = new ImageView(imageLED);
        imageViewLED.setFitHeight(20);
        imageViewLED.setFitWidth(20);
        DropShadow dropShadowLeftPane = new DropShadow();
        dropShadowLeftPane.setColor(javafx.scene.paint.Color.web("#f5c843"));
        dropShadowLeftPane.setWidth(-13.0);
        dropShadowLeftPane.setHeight(0.0);
        dropShadowLeftPane.setRadius(17.0);
        dropShadowLeftPane.setSpread(0.0);
        rightTextArea.setEffect(dropShadowLeftPane);
        rightPane.getChildren().add(rightTextArea);




        // add all component to the Center Pane
        centerPane.getChildren().addAll(
                imageView,radioButtonManualInput, radioButtonFromFile,
                lblSource, spinnerSource, lblLED, txtFieldLED, btnConnect,
                btnShowResult, btnSelectFile, txtFieldPath, textAreaResult,
                textAreaDetails,rightPane);

        // add all left button on the left pane
        leftPane.getChildren().addAll(leftBtnEnterData, leftBtnMoreDetails, leftBtnResult);

        // add center pane and left pane to the base pane screen
        getChildren().addAll(centerPane, leftPane);
    }


    /**
     * for this method handel style  (view Button Enter data ) ,
     * and call method ( display  result and LCS ).
     * and number of leads to the array LED , action button show Result and action button add
     */
    public void styleBtnEnterData() {
        radioButtonManualInput.setVisible(true);
        radioButtonFromFile.setVisible(true);
        txtFieldPath.setVisible(false);
        lblSource.setVisible(false);
        btnSelectFile.setVisible(false);
        lblLED.setVisible(false);
        spinnerSource.setVisible(false);
        txtFieldLED.setVisible(false);
        textAreaResult.setVisible(false);
        textAreaDetails.setVisible(false);
        radioButtonManualInput.setSelected(false);
        radioButtonFromFile.setSelected(false);
        btnConnect.setVisible(false);
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
        SpinnerValueFactory<Integer> spinnerValueFactorySource = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000000);
        spinnerSource.setValueFactory(spinnerValueFactorySource);

        txtFieldLED.setPrefHeight(27);
        txtFieldLED.setPrefWidth(333);
        txtFieldLED.setLayoutX(137);
        txtFieldLED.setLayoutY(231);

        btnShowResult.setLayoutX(190);
        btnShowResult.setLayoutY(324);
        btnShowResult.setPrefHeight(27);
        btnShowResult.setPrefWidth(149);
        btnShowResult.changeSize(20);

        btnSelectFile.setLayoutX(55);
        btnSelectFile.setLayoutY(185);
        txtFieldPath.setPrefHeight(27);
        txtFieldPath.setPrefWidth(300);
        txtFieldPath.setLayoutX(155);
        txtFieldPath.setLayoutY(190);


        /**
         in this action if user selected type input.
         and two action button add and showResult (as Manual ---------------------------------------------------)
         */
        radioButtonManualInput.setOnAction(e2 -> {
            // deleted previous all data in LED Array
            LED = null;
            leftBtnResult.setDisable(true);
            rightTextArea.clear();
            leftBtnMoreDetails.setDisable(true);
            txtFieldLED.clear();
            lblLED.setVisible(true);
            lblSource.setVisible(true);
            btnConnect.setLayoutX(477);
            btnConnect.setLayoutY(227);
            txtFieldLED.setVisible(true);
            txtFieldPath.setVisible(false);
            btnSelectFile.setVisible(false);
            spinnerSource.setVisible(true);
            btnShowResult.setVisible(true);
            btnConnect.setVisible(true);
            btnConnect.setDisable(false);


            // add all input LED to array LED when clicked on button ( add ), and the size number leads equal number of power set button add Disable.
            btnConnect.setOnAction(e -> {
                String valueOfLed = txtFieldLED.getText().trim();
                // if text file LED is empty
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
                            // size the array = value from spinner
                            LED = new int[powerSize];
                            index = 0;
                        }
                        // else add input leads to the array LED
                        else {
                            if (!repetedNumber(LED, leadValue)) {
                                txtFieldLED.clear();
                                LED[index] = leadValue;
                                index++;
                                String PowerAndLed = "Power: " + index + " Led : " +leadValue ;
                                Label label = new Label("⚡");
                                label.setStyle("-fx-text-fill: yellow;-fx-font-weight: blod;-fx-font-size: 50px");

                                if(index==leadValue){
                                    rightTextArea.appendText( label.getText() +" "+PowerAndLed+ "\n");
                                }
                                else {
                                    rightTextArea.appendText("   " + PowerAndLed+ "\n");
                                }
                            } else {
                                new Warning("The LED is already exist");
                            }
                        }
                        // # input data in textFiled leads = size power (spinner). and button add set Disable
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

            // red all data input from user ( number of source and number of leads ) and set Button add setDisable and the left button (result and more details ) set as not Disable.
            btnShowResult.setOnAction(A -> {
                // if array of leads is empty ( no input leads) and call method from Data class
                if (LED == null || LED.length == 0) {
                    new Warning("There are no input lights !!\n");
                }
                // call method from Data class
                else {
                    if(isZeroValueFromLED(LED)) {
                        new Warning("You can see the result now.\n");
                        leftBtnResult.setDisable(false);
                        leftBtnMoreDetails.setDisable(false);
                        textAreaResult.clear();
                        textAreaDetails.clear();
                        data.findMaxLedLighting(powerSize, textAreaResult, textAreaDetails);
                    }
                    else {
                        new Warning("Some information is wrong");
                    }
                }
            });

        });


        /**
         in this action if user selected type input.
         and two action button add and showResult and prows button select File ( as a File *************************************** )
         */
        radioButtonFromFile.setOnAction(e -> {
            // deleted previous all data in LED Array
            LED = null;
            btnConnect.setLayoutX(465);
            btnConnect.setLayoutY(185);
            rightTextArea.clear();
            leftBtnResult.setDisable(true);
            btnConnect.setDisable(false);
            leftBtnMoreDetails.setDisable(true);
            txtFieldPath.clear();
            lblLED.setVisible(false);
            btnConnect.setVisible(true);
            btnConnect.setDisable(false);
            lblSource.setVisible(false);
            txtFieldLED.setVisible(false);
            spinnerSource.setVisible(false);
            txtFieldPath.setVisible(true);
            btnSelectFile.setVisible(true);
            btnShowResult.setVisible(true);
            btnConnect.setDisable(true);
            txtFieldPath.setEditable(false);


            //open new stage to select file
            btnSelectFile.setOnAction(a -> {
                Stage stage = new Stage();
                file = fileChooser.showOpenDialog(stage);
                txtFieldPath.setText(file.getAbsolutePath());
                btnConnect.setDisable(false);
                txtFieldPath.setEditable(false);
            });

            // add all input LED to array LED when clicked on button ( add ), and the size number leads equal number of power set button add Disable.
            btnConnect.setOnAction(l -> {
                try {
                    // red from file
                    Scanner scanner = new Scanner(file);
                    // file id empty
                    if (file.length() == 0) {
                        new Warning("There is no information in the file.\n");
                    } else {
                        if (radioButtonFromFile.isSelected()) {
                            // Clear the previously read data
                            LED = null;
                            index = 0;
                        }
                        try {
                            // while file exit lines
                            while (scanner.hasNextLine()) {
                                // size = first line in file and the size power
                                powerSize = scanner.nextInt();
                                // if number of power negative
                                if (powerSize <= 0) {
                                    new Warning("Not Valid Input Data. Please enter a positive number.\n");
                                    return;
                                }
                                // if second line is empty from file ( array of LED is empty)
                                try {
                                    scanner.nextLine();
                                    String line = scanner.nextLine();
                                    // add the second line to the array ( leads)
                                    String[] data = line.split(" ");
                                    // while number of leads lees than length power
                                    while (index < data.length) {
                                        try {
                                            // if empty array OR out of index power array
                                            if (LED == null || index >= LED.length) {
                                                LED = new int[powerSize];
                                            }

                                            int value = Integer.parseInt(data[index]);

                                            if (value >= 0) {
                                                if (!repetedNumber(LED, Integer.parseInt(data[index]))) {
                                                    // add element to the array LED
                                                    LED[index] = Integer.parseInt(data[index]);
                                                    textAreaDetails.clear();
                                                    String PowerAndLed = "Power: "+ (index+1) + " Led : " +data[index] ;
                                                    Label label = new Label("⚡");
                                                    label.setStyle("-fx-text-fill: yellow;-fx-font-weight: blod;-fx-font-size: 50px");
                                                    if((index+1)==Integer.parseInt(data[index])){
                                                        rightTextArea.appendText( label.getText() +" "+PowerAndLed+ "\n");
                                                    }
                                                    else {
                                                        rightTextArea.appendText("   " + PowerAndLed+ "\n");
                                                    }

                                                } else {
                                                    new Warning("LED " + data[index] + " already Exit.\n");
                                                    return;
                                                }
                                            }

                                            else {
                                                new Warning("there exit negative value" + value + " in this file.\n");
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

                                }catch (NoSuchElementException elementException){
                                    new Warning("There are no input lights !!\n");
                                    return;
                                }
                                if(getSize(LED)==false){
                                    new Warning("Please check number of Led's");
                                    return;
                                }


                                // all data from file is true
                                new Warning("All lights have been added successfully.\n");
                            }
                        }
                        // if data in file string
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


            // red all data input from user ( number of source and number of leads ) and set Button add setDisable and the left button (result and more details ) set as not Disable
            btnShowResult.setOnAction(c -> {
                // if array of leads is empty ( no input leads)
                if (LED == null || LED.length == 0) {
                    new Warning("There are no input lights !!\n");
                    return;
                }
                // call method from Data class
                else {
                    if (isZeroValueFromLED(LED)) {
                        new Warning("You can see the result now.\n");
                        leftBtnResult.setDisable(false);
                        leftBtnMoreDetails.setDisable(false);
                        textAreaResult.clear();
                        textAreaDetails.clear();
                        data.findMaxLedLighting(powerSize, textAreaResult, textAreaDetails);
                    } else {
                        new Warning("Please check the data again.\n");
                    }

                }
            });
        });
    }


    /**
     * for this method handel view the left button result
     */
    public void styleBtnResult() {
        textAreaDetails.setVisible(false);
        textAreaResult.setVisible(true);
        lblLED.setVisible(false);
        lblSource.setVisible(false);
        radioButtonFromFile.setVisible(false);
        radioButtonManualInput.setVisible(false);
        btnShowResult.setVisible(false);
        txtFieldPath.setVisible(false);
        spinnerSource.setVisible(false);
        txtFieldLED.setVisible(false);
        btnConnect.setVisible(false);
        textAreaResult.setPrefHeight(372);
        textAreaResult.setPrefWidth(465);
        textAreaResult.setLayoutX(54);
        textAreaResult.setLayoutY(20);
        textAreaResult.setVisible(true);
    }


    /**
     * for this method handel view the left button More Details
     */
    public void styleBtnMoreDetails() {
        textAreaDetails.setVisible(true);
        textAreaResult.setVisible(false);
        lblLED.setVisible(false);
        lblSource.setVisible(false);
        radioButtonFromFile.setVisible(false);
        radioButtonManualInput.setVisible(false);
        btnShowResult.setVisible(false);
        txtFieldPath.setVisible(false);
        spinnerSource.setVisible(false);
        txtFieldLED.setVisible(false);
        btnConnect.setVisible(false);
        textAreaDetails.setPrefHeight(372);
        textAreaDetails.setPrefWidth(465);
        textAreaDetails.setLayoutX(54);
        textAreaDetails.setLayoutY(20);
        textAreaDetails.setVisible(true);
    }


    /**
     * to check if the input number already exit in array LED's
     */
    private static boolean repetedNumber(int[] LED, int data) {
        for (int i = 0; i < LED.length; i++) {
            if (LED[i] == data) {
                return true;
            }
        }
        return false;
    }

    private static boolean isZeroValueFromLED(int[] LED) {
        for (int i = 0; i < LED.length; i++) {
            if (LED[i] == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean getSize(int[] LED) {
        for (int i = 0; i < LED.length; i++) {
            if (LED[i] == 0) {
                return false;
            }
        }
        return true;
    }

}
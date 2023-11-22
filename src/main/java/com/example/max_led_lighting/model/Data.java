package com.example.max_led_lighting.model;
import com.example.max_led_lighting.interfase.MainView;
import javafx.scene.control.TextArea;
import java.util.LinkedList;


/**
 *
   Name: Anan Elayan
   ID: 1211529

   ♦ Aim Class:
           2) Data is a class responsible for finding the Longest Common Subsequence (LCS) of LED pairs and displaying the result and details.
   ♦ Variables:
           1) stringResult: A string to store the final result of connected LED pairs.
   ♦ Methods:
           1) findLCS: Takes the size of the LED array, calculates the LCS, and calls display functions for the result and details.
           2) displayResult: Displays the final result of connected LED pairs by backtracking through the LCS.
           3) displayDetailsTable: Displays a table of cost values and movement hints for better understanding of the LCS calculation.
   ♦ Relation:
          {            0                                             if one the array is empty 'k' or 'l'            }
          {            costLCS[i-1,j-1] + 1                          if two number in array 2D is equal k = l        }
          {            max [ costLCS [i-1, j], costLCS[i , j-1] ]    if two number in array 2D is not equal k != l   }


   ♣ in method displayResult : if(signal '^' decreasing tempRow) (signal '<--' depressing TempColumn). This process continues until it finishes rotating to find the largest number in the table.
 تبقى هذه العملية حتى ينتهي من الدوران كافة اكبر رقم في الجدول


*/

public class Data {

    /**
     * in this method find the LCS ( build tables signals and numbers 'Algorithm') ,And take size of power and text Area to print the result
     */
    public void findMaxLedLighting(int powerSize, TextArea textAreaResult, TextArea textAreaMoreDetails) {
        // in this array to store led and power #row = #power(spinner) , #column = #spoer(spinner)
        int[][] costLCS = new int[powerSize + 1][powerSize + 1];      //size --> spinner
        // to store Signals like " ? , <-- , ^ "
        String[][] signalArray = new String[powerSize + 1][powerSize + 1];

        for (int i = 1; i <= powerSize; i++)
        {
            for (int j = 1; j <= powerSize; j++)
            {
                // power == index in array LED
                if (i == MainView.LED[j - 1])
                {
                    costLCS[i][j] = costLCS[i - 1][j - 1] + 1;
                    signalArray[i][j] = "?"; // It means diagonal arrow
                }
                // power != index in array LED
                else
                {
                    // if the left cell is grater
                    if (costLCS[i][j - 1] > costLCS[i - 1][j])
                    {
                        costLCS[i][j] = costLCS[i][j - 1];
                        signalArray[i][j] = "<--";//left row
                    }
                    else
                    {
                        // if left cell not grater and index in array LED not equal index in array costLCS taking the above cell
                        costLCS[i][j] = costLCS[i - 1][j];
                        signalArray[i][j] = "^"; // above row
                    }
                }
            }
        }

        // call method to display result
        displayResult(signalArray, powerSize, powerSize, costLCS, textAreaResult);
        // call method to display tables
        displayDetailsTable(powerSize, signalArray, costLCS, textAreaMoreDetails);

    }


    /**
     * Declares a public method named displayResult that takes parameters: a 2D String array (tempArray),
     * two integers (i and j), a 2D integer array (findMaxLedLighting), and a TextArea object (textAreaResult).
     * This process continues until it finishes rotating to find the largest number in the table.to display result on textArea 'textAreaResult'
     */
    public void displayResult(String[][] signalArray, int i, int j, int[][] findMaxLedLighting, TextArea textAreaResult) {

        //to do font in textArea is bold
        textAreaResult.setStyle("-fx-font-weight: bold");

        // Initializes an integer variable (row) with the value of the parameter i.
        int row = i;

        // Initializes an integer variable (column) with the value of the parameter j.
        int column = j;

        // Creates a linked list of strings (findMaxLedLighting) to store unique LCS (Longest Common Subsequence) pairs.
        LinkedList<String> allLCS = new LinkedList<>();

        // Enters a while loop as long as the findMaxLedLighting at the current row and column is equal to the findMaxLedLighting at i and j.
        while (findMaxLedLighting[row][column] == findMaxLedLighting[i][j])
        {

            // Enters another while loop with the same condition as the outer loop.
            while (findMaxLedLighting[row][column] == findMaxLedLighting[i][j])
            {
                // Initializes a temporary integer variable (tempRow) with the current value of row.
                int tempRow = row;

                // Initializes a temporary integer variable (tempColumn) with the current value of column.
                int tempColumn = column;

                // Initializes an empty string (pairs) to store pairs of LCS.
                String pairs = "";

                // Enters a while loop as long as tempRow and tempColumn are not equal to 0.
                while (tempRow != 0 && tempColumn != 0)
                {

                    // Checks if the value in tempArray at position tempRow and tempColumn is equal to "?".
                    if (signalArray[tempRow][tempColumn].equals("?"))
                    {
                        // Constructs a string representing pairs and appends it to the existing pairs string.
                        pairs = " Source" + " { " + tempRow + " } " + "------>" + "Led" + " { " + tempRow + " }  ⚡" + " \n" + pairs;
                        tempRow--;
                        tempColumn--;
                    }

                    // Checks if the value in tempArray at position tempRow and tempColumn is equal to "<--" and decrements tempColumn.
                    else if (signalArray[tempRow][tempColumn].equals("<--"))
                    {
                        tempColumn--;
                    }

                    // If the above condition is not met, decrements tempRow.
                    else tempRow--;
                }

                // Checks if the linked list allLCS does not contain the current pairs.
                if (!allLCS.contains(pairs))
                {
                    // Adds the current pairs to the beginning of the linked list allLCS.
                    allLCS.addFirst(pairs);
                    textAreaResult.appendText("The best number of lamps that can be lit are:\n");
                    textAreaResult.appendText(pairs);
                    textAreaResult.appendText("\n Then " + findMaxLedLighting[row][column] + " LEDs will be lighted.\n");
                    textAreaResult.appendText("\n\n");
                }
                column--;
            }
            row--;
            // if cell in column value lees then previous cell column. Resets the value of column to the original value of j.
            column = j;
        }
    }


    /**
     * Declares a public method named displayDetailsTable that takes parameters:
     * an integer (size), a 2D String array (tempArray), a 2D integer array (findMaxLedLighting),
     * and a TextArea object (textAreaMoreDetails).to print two table signals + numbers
     */
    public void displayDetailsTable(int size, String[][] signalArray, int[][] findMaxLedLighting, TextArea textAreaMoreDetails) {

        // to print first table (numbers)
        textAreaMoreDetails.setStyle("-fx-font-weight: bold");
        textAreaMoreDetails.appendText("\t");

        // Initiates a loop that iterates from 0 to size (inclusive) using variable k.
        for (int row = 0; row < size + 1; row++)
        {

            // Initiates a nested loop that iterates from 0 to size (inclusive) using variable l.
            for (int column = 0; column < size + 1; column++)
            {
                // Checks if k is 0 and l is less than size.
                if (row == 0 && column < size)
                {
                    // Appends the LED label for the corresponding index (l) to the TextArea.
                    textAreaMoreDetails.appendText("\t\t" + "LED" + MainView.LED[column]);
                }

                // Checks if l is 0.
                else if (column == 0)
                {
                    textAreaMoreDetails.appendText("Source " + row + "\t\t");
                }

                // Checks if both l and k are greater than 0.
                if ((column > 0) && (row > 0))
                {
                    // Appends the findMaxLedLighting value at position (k, l) to the TextArea.
                    textAreaMoreDetails.appendText(" " + findMaxLedLighting[row][column] + " \t  \t  \t ");
                }
            }
            textAreaMoreDetails.appendText("\n");
        }


        // to print second table (signals)
        textAreaMoreDetails.appendText("*********************************************************************************************************************************************************************************************");
        textAreaMoreDetails.appendText("\n");

        textAreaMoreDetails.appendText("\t");

        // Initiates a loop that iterates from 0 to size (inclusive) using variable k.
        for (int row = 0; row < size + 1; row++) {
            // Initiates a nested loop that iterates from 0 to size (inclusive) using variable l.
            for (int column = 0; column < size + 1; column++) {
                // Checks if k is 0 and l is less than size.
                if (row == 0 && column < size) {
                    textAreaMoreDetails.appendText("\t\t" + "LED" + MainView.LED[column]);
                }
                // Checks if l is 0.
                else if (column == 0) {
                    textAreaMoreDetails.appendText("Source " + row + "\t\t");
                }
                // Checks if both l and k are greater than 0.
                if ((column > 0) && (row > 0)) {
                    String hint = "?";
                    // Checks if the value in tempArray at position (k, l) is equal to "^".
                    if (signalArray[row][column].equals("^")) {
                        hint = "^";
                    }
                    // Checks if the value in tempArray at position (k, l) is equal to "<--".
                    else if (signalArray[row][column].equals("<--")) {
                        hint = "<--";
                    }
                    textAreaMoreDetails.appendText(" " + hint + " \t  \t  \t");
                }
            }
            textAreaMoreDetails.appendText("\n");
        }
    }
}

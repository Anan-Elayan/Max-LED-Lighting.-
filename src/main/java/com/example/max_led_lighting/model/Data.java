package com.example.max_led_lighting.model;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Arrays;


/**
 *
   Name: Anan Elayan
   ID: 1211529

  ♦ Aim Class :
      find the result and write the algorithm and print the result
  ♦ Methods :
      in this class there exit 4 methods
        first method : as a general function to call all method. It is the only one used in the program.
        second method : build the algorithm ( solve the problem )
        third method : In building it, we rely on the result coming from the upper ( getResult ) method until it finds the answer and adds it to the arrayList and we return that arrayList. and print to text area
        fourth method : In building it, we rely on the result coming from the upper ( getResult ) method until it finds the answer and print the tables to text area passing.

  ♦ relation of algorithm : time complexity = O(n^2)
                           {       0                     if array is empty }
                           { max(cost[j]+1 , cost(i))    if i > 0 j > 0 }

*/

public class Data {

    // in this method to call all method bellow and the parameter takes the array of LED.
    // and two text area to pass method 'findResult' to print result and 'printTable' to print details table
    public ArrayList<Integer> mainFunction(int [] LED, TextArea textAreaResult, TextArea textAreaDetails){
        int [] cost = new int[LED.length];
        int []index =  new int[LED.length];
        // call methode bellow
        getResult(LED,cost,index);
        // call methode bellow
        printTable(LED,cost,index,textAreaDetails);
        // return array list of result
        return findResult(LED,cost,index,textAreaResult) ;
    }


    // for this function build the algorithm
    public void getResult (int [] LED ,int [] cost,int []index){

        if(LED.length==0){
            return;
        }
        // fill initial value of cast array to 1
        Arrays.fill(cost,1);
        // fill initial value of index array to -1
        Arrays.fill(index,-1);
        for (int i = 1; i < LED.length; i++) {
            for (int j = 0; j < i; j++) {
                if(LED[i] > LED[j] && cost[i] < cost[j]+1){
                    cost[i] = cost[j]+1;
                    index[i] = j;
                }
            }
        }
    }


    // for this method return array list fill the result from algorithm,and the method take's the array of LED and cost ,index array and textArea to print result
    public ArrayList<Integer> findResult(int []LED , int[]cost ,int []index, TextArea textAreaResult){
        ArrayList<Integer>result = new ArrayList<>();
        int MAX = cost[0];
        int maxIndex = 0;
        for (int i = 1; i <LED.length ; i++) {
            if(MAX<=cost[i]){
                MAX = cost[i];
                maxIndex = i;
            }
        }
        String resultString = " ";
        while (maxIndex >=0){
             resultString = "{Source : "+ LED[maxIndex] + "---------> led : " + LED[maxIndex] +" }\n" +  resultString;
            result.add(LED[maxIndex]);
            maxIndex = index[maxIndex];
        }
        // print the result to textArea
        textAreaResult.appendText("The best number of lamps that can be lit are: \n" + resultString + "\n Then " + MAX + " LEDs will be lighted.\n");
        return result;
    }


    // for this method do print table algorithm. and the parameter function take the array of LED's and array cost and index,and the textArea to display it
    public void printTable(int []LED , int[]cost ,int []index, TextArea textAreaDetails){

        String ledArray  = "";
        String costArray  = "";
        String indexArray  = "";
        // to print led's array
        for (int i = 0; i < LED.length; i++) {
            ledArray = ledArray + " " + LED[i] + " , ";
        }
        // to print cost array
        for (int i = 0; i < cost.length; i++) {
            costArray = costArray + " " + cost[i] + " , " ;
        }
        // to print array index
        for (int i = 0; i < index.length; i++) {
            indexArray = indexArray + " " + index[i] + " , ";
        }
        // to print result in textArea
        textAreaDetails.appendText("LED Array --> " +" [ " +ledArray + " ] \n\n" +"Cost Array -->" +" [ " + costArray + " ]\n\n"+"Index Array -->" + " [ " + indexArray + " ] \n\n");
    }

}





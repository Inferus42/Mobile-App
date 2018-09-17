package com.example.inferus.n2;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    public EditText e;
    public TextView memoryText;
    public String tempString;
    public String[] splitString;
    public float answer;
    public float memory;

    public Calculator(EditText et, TextView memoryText, String tempString, String[] splitString, float answer, float memory) {
        this.e = et;
        this.memoryText = memoryText;
        this.tempString = tempString;
        this.splitString = splitString;
        this.answer = answer;
        this.memory = memory;
    }

    public void calculate(View v) {
        switch (v.getId()) {
            case R.id.button18:
                if(tempString.contains("+")) {
                    if(tempString.contains("\u03c0")){
                        tempString = tempString.replace("\u03c0","3.14159265359");
                    }

                    splitString = tempString.split("\\+");
                    answer = Float.parseFloat(splitString[0]) + Float.parseFloat(splitString[1]);
                    e.setText("" + answer);
                }

                if(tempString.contains("-")) {
                    if(tempString.contains("\u03c0")){
                        tempString = tempString.replace("\u03c0","3.14159265359");
                    }

                    splitString = tempString.split("\\-");
                    answer = Float.parseFloat(splitString[0])-Float.parseFloat(splitString[1]);
                    e.setText(""+answer);
                }

                if(tempString.contains("*")) {
                    if(tempString.contains("\u03c0")){
                        tempString = tempString.replace("\u03c0","3.14159265359");
                    }

                    splitString = tempString.split("\\*");
                    answer = Float.parseFloat(splitString[0])*Float.parseFloat(splitString[1]);
                    e.setText(""+answer);
                }

                if(tempString.contains("/")) {
                    if(tempString.contains("\u03c0")){
                        tempString = tempString.replace("\u03c0","3.14159265359");
                    }

                    splitString = tempString.split("/");
                    if( Float.parseFloat(splitString[1]) != 0f ) {
                        answer = Float.parseFloat(splitString[0]) / Float.parseFloat(splitString[1]);
                        e.setText(""+answer);
                    } else{
                        e.setText("");
                    }
                }

                if(tempString.contains("√")) {
                    tempString = "a" + tempString;
                    splitString = tempString.split("√");
                    double answer = Math.sqrt(Double.parseDouble(splitString[1]));
                    e.setText(""+answer);
                }

                if(tempString.contains("^")) {
                    if(tempString.contains("\u03c0")){
                        tempString = tempString.replace("\u03c0","3.14159265359");
                    }

                    splitString = tempString.split("\\^");
                    double answer = Math.pow(Double.parseDouble(splitString[0]),Double.parseDouble(splitString[1]));
                    e.setText(""+answer);
                }
                break;
        }
    }

    public void addNumber(View v) {
        Button btn = (Button) v;
        if(e.getText().equals("") || (answer!=0f)) {
            e.setText(btn.getText());
            tempString = ""+e.getText();
            answer = 0f;
        } else {
            tempString = (""+e.getText()) + (""+btn.getText());
            e.setText(tempString);
        }
    }

    public void operatorFunction(View v) {
        Button btn = (Button) v;
        tempString = tempString + btn.getText();
        e.setText(tempString);
    }

    public void memoryFunction(View v) {
        Button btn = (Button) v;

        if(btn.getText().equals("M")) {
            try{
                memory = Float.parseFloat(e.getText().toString());
                memoryText.setText(Float.toString(memory));
                e.setText("");
            } catch (Exception ex){
                e.setText("");
            }
        }

        if(btn.getText().equals("MC")) {
            if(!memoryText.equals("")){
                e.setText(e.getText()+""+ memoryText.getText());
                memory = 0f;
                memoryText.setText("");
            }
        }

    }

    public void clearEntry(View v) {
        tempString = "";
        e.setText("");
    }

    public void backSpace(View v) {
        if(answer != 0) {
            tempString="";
        }

        tempString = backSpaceManager(tempString);
        e.setText(tempString);
    }

    public String backSpaceManager(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        return s.substring(0, s.length()-1);
    }
}
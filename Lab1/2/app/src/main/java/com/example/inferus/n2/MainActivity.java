package com.example.inferus.n2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText e;
    String tempString = "";
    Float answer = 0f;
    Float memory = 0f;
    Calculator c;
    String[] splitString;
    TextView memoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e = (EditText) findViewById(R.id.editText);
        memoryText = (TextView) findViewById(R.id.memoryText);
        c = new Calculator(e, memoryText, tempString, splitString, answer, memory);
    }

    public void operatorFunction(View v) {
        c.operatorFunction(v);
    }

    public void memoryFunction(View v) {
        c.memoryFunction(v);
    }

    public void calculate(View v) {
        c.calculate(v);
    }

    public void addNumber(View v) {
        c.addNumber(v);
    }


    public void clearEntry(View v) {
        c.clearEntry(v);
    }

    public void backSpace(View v) {
        c.backSpace(v);
    }
}

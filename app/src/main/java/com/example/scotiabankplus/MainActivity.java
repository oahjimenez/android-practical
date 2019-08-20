package com.example.scotiabankplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    @BindView(R.id.btnCalc) Button btnCalc;
    @BindView(R.id.edtWeight) EditText edtWeight;
    @BindView(R.id.edtHeight) EditText edtHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.i(TAG, "onCreate");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        String username = intent.getStringExtra("params");
        Toast.makeText(this, "Bienvenido ".concat(String.valueOf(username)), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnCalc)
    public void calc() {
        Double imc = Double.valueOf(edtWeight.getText().toString())/ Math.pow(Double.valueOf(edtHeight.getText().toString()), 2);
        Toast.makeText(getApplicationContext(), imc.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }


}

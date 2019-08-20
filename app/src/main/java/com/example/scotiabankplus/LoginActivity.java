package com.example.scotiabankplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edtUsername) EditText edtWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // always after that
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignup)
    public void signup() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("params", edtWeight.getText().toString().toUpperCase());
        startActivity(intent);
    }

}

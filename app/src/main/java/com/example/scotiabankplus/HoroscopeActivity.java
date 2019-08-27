package com.example.scotiabankplus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankplus.model.SodiacSignCollection;

public class HoroscopeActivity extends AppCompatActivity {

    protected SodiacSignCollection sodiacSigns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sodiacSigns = SodiacSignCollection.getInstance();
        sodiacSigns.setContext(getApplicationContext());
        sodiacSigns.initSigns(x->{});
        setContentView(R.layout.horoscope_fragment);
    }
}

package com.example.scotiabankplus;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankplus.fragments.MMOTitlesFragment;
import com.example.scotiabankplus.model.MMOFeed;

public class MainMenuActivity extends AppCompatActivity {

    protected MMOFeed mmoFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mmoFeed = new MMOFeed();
        mmoFeed.setContext(getApplicationContext());
        mmoFeed.populate(items -> {
        });
        MMOTitlesFragment.setFeed(mmoFeed);
        setContentView(R.layout.feed_fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}

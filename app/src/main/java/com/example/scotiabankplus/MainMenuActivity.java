package com.example.scotiabankplus;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankplus.fragments.MMOFeedFragment;

public class MainMenuActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        MMOFeedFragment feedFragment = new MMOFeedFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.menu_fragment,feedFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}

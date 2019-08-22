package com.example.scotiabankplus;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Iterator;

public class YoliActivity extends AppCompatActivity {

    static final String YOLI_AAS_URL = "https://api.adderou.cl/tyaas/";

    private Spinner dropdown;
    final int MAX_SIGNS = 11;
    private String[] items = new String[MAX_SIGNS];
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoli);
        dropdown = findViewById(R.id.signs);

        RequestQueue queue = RequestQueueFactory.getInstance(this).getRequestQueue();
        queue.getCache().clear();
        System.out.println(String.format("Calling YOLI endpoit[%s]", YOLI_AAS_URL));
        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, YOLI_AAS_URL,
                null, (response) -> {
            JSONObject jsonObject = null;
            Iterator<String> signs = null;

            try {
                signs = ((JSONObject)response.get("horoscopo")).keys();
                System.out.println(String.format("Endof YOLI call[%s] response[%s]", YOLI_AAS_URL, response));

                 int i = 0;
                 while(signs.hasNext()) {
                     String nextSign = signs.next();
                     if (nextSign!=null) {
                         items[i++] = nextSign;
                     }
                 }
            } catch(Exception e) {
                System.out.println("Error al parsear objeto:" + e.getMessage());
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
            dropdown.setAdapter(adapter);
        }, (error) -> {
            System.out.println("Error:" + error.getMessage());
        });
        jr.setShouldCache(false);
        queue.add(jr);
    }
}

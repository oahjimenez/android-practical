package com.example.scotiabankplus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.scotiabankplus.model.SodiacSign;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final RecyclerView recyclerView = findViewById(R.id.signsView);
        recyclerView.setAdapter(new ListAdapter(getApplicationContext(), new ArrayList<>()));
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llmanager = new LinearLayoutManager(this);
        llmanager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llmanager);

        String url = "https://api.adderou.cl/tyaas/";
        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println("Yoli response:"+response.toString());
                    ArrayList<SodiacSign> signos = new ArrayList();
                    SodiacSign aux;
                    Gson gson = new Gson();
                    JSONObject horoscopo = response.getJSONObject("horoscopo");
                    System.out.println("Horoscopo : "+horoscopo);
                    Iterator<String> it = horoscopo.keys();
                    while (it.hasNext()) {
                        aux = gson.fromJson(horoscopo.getJSONObject(it.next()).toString(), SodiacSign.class);
                        signos.add(aux);
                    }
                    ListAdapter myAdapter = new ListAdapter(getApplicationContext(), signos);
                    recyclerView.setAdapter(myAdapter);
                    System.out.println("Adapter set");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error while calling Yoli:"+error.getMessage());
            }
        });
        RequestQueue queue = RequestQueueFactory.getInstance(this).getRequestQueue();
        queue.add(jr);
    }
}

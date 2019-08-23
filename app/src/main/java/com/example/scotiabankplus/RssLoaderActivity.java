package com.example.scotiabankplus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RssLoaderActivity extends AppCompatActivity {
    final String MMO_RSS = "https://api.rss2json.com/v1/api.json?rss_url=http%3A%2F%2Ffeeds.feedburner.com%2Fmmorpg%2Fnews";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_loader);
        final RecyclerView recyclerView = findViewById(R.id.rssView);
        recyclerView.setAdapter(new MMOListAdapter(getApplicationContext(), new ArrayList<>()));
        LinearLayoutManager llmanager = new LinearLayoutManager(this);
        llmanager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llmanager);
        recyclerView.setHasFixedSize(true);

        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, MMO_RSS,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println("RSS response:" + response.toString());
                    JSONArray items = response.getJSONArray("items");

                    ArrayList<MMOItem> mmoItems = new ArrayList<>();
                    Gson gson = new Gson();
                    System.out.println("MMOItems : " + items);
                    for (int i = 0; i < items.length(); i++) {
                        mmoItems.add(gson.fromJson(items.getJSONObject(i).toString(), MMOItem.class));
                    }
                    MMOListAdapter myAdapter = new MMOListAdapter(getApplicationContext(), mmoItems);
                    recyclerView.setAdapter(myAdapter);
                    System.out.println("Adapter set");

                } catch (RuntimeException | JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error while calling Yoli:" + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/rss+xml");
                return headers;
            }
        };
        RequestQueue queue = RequestQueueFactory.getInstance(this).getRequestQueue();
        queue.add(jr);
    }
}

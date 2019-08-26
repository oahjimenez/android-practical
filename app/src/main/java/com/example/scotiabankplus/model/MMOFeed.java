package com.example.scotiabankplus.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.scotiabankplus.MMOItem;
import com.example.scotiabankplus.MMOListAdapter;
import com.example.scotiabankplus.RequestQueueFactory;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class MMOFeed {

    final String CONTENT_TYPE = "application/rss+xml";
    final String CONTENT_TYPE_KEY = "Content-Type";

    @Getter
    protected final String MMO_RSS = "https://api.rss2json.com/v1/api.json?rss_url=http%3A%2F%2Ffeeds.feedburner.com%2Fmmorpg%2Fnews";

    @Getter
    @Setter
    protected MMOListAdapter listAdapter;

    @Getter
    @Setter
    protected Context context;

    @Getter
    protected List<MMOItem> mmoItems = new ArrayList<>();

    @Getter
    protected RequestQueue queue;

    public void populate(PopulateMMOCallBack callback) {
        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, MMO_RSS,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println("RSS response:" + response.toString());
                    JSONArray items = response.getJSONArray("items");
                    Gson gson = new Gson();
                    System.out.println("MMOItems : " + items);
                    for (int i = 0; i < items.length(); i++) {
                        mmoItems.add(gson.fromJson(items.getJSONObject(i).toString(), MMOItem.class));
                    }
                    System.out.println("Adapter set");
                    callback.onSuccess(mmoItems);
                } catch (RuntimeException | JSONException e) {
                    e.printStackTrace();
                }
            }

        }, error -> {
            System.out.println("Error while fetching MMO Feed:" + error.getMessage());

        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put(CONTENT_TYPE_KEY, CONTENT_TYPE);
                return headers;
            }
        };
        queue = RequestQueueFactory.getInstance(context).getRequestQueue();
        queue.add(jr);
    }
}

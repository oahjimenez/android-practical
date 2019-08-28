package com.example.scotiabankplus.model;

import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class YaaSThread extends AsyncTask<Void, Void, JSONObject> {
    protected final String YOLI_URI = "https://api.adderou.cl/tyaas/";
    protected RequestQueue queue;

    protected int DEFAULT_TIMEOUT = 15;

    public YaaSThread(RequestQueue queue) {
        this.queue = queue;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        final RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, YOLI_URI, null, future, future);
        System.out.println("Resolving Request...");
        queue.add(request);
        try {
            return future.get(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("Error parsing yoli Response:" + e.getClass().getSimpleName() + e.getMessage());
        }
        return null;
    }
}


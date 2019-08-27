package com.example.scotiabankplus.model;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.example.scotiabankplus.RequestQueueFactory;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class SodiacSignCollection {

    protected static SodiacSignCollection collection;
    protected int DEFAULT_TIMEOUT = 15;

    @Getter
    @Setter
    protected List<SodiacSign> sodiacSigns;

    @Getter
    @Setter
    protected Context context;

    protected final String YOLI_URI = "https://api.adderou.cl/tyaas/";
    protected final String HOROSCOPE_KEY = "";

    protected RequestQueue queue;

    public static SodiacSignCollection getInstance() {
        if (collection == null) {
            collection = new SodiacSignCollection();
        }
        return collection;
    }

    public SodiacSignCollection() {
        sodiacSigns = new ArrayList<>();
    }

    public SodiacSignCollection(Context context) {
        this.context = context;
        sodiacSigns = new ArrayList<>();
    }


    public boolean isFetched() {
        return !collection.sodiacSigns.isEmpty();
    }

    public void initSigns(VolleyCallBack<SodiacSign> callback) {
        if (!isFetched()) {
            System.out.println("Fetching sodiac signs...:");
            fetch(context, callback);
        }
    }

    public List<String> getSignNames() {
        List<String> signNames = new ArrayList<>();
        for (SodiacSign sign : collection.sodiacSigns) {
            signNames.add(sign.getNombre());
        }
        return signNames;
    }

    protected void fetch(Context context, VolleyCallBack<SodiacSign> callback) {
        queue = RequestQueueFactory.getInstance(context).getRequestQueue();

        final CountDownLatch latch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            YaaSThread yaaSThread = new YaaSThread(queue);
            try {
                JSONObject response = yaaSThread.execute().get(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
                System.out.println("Yoli response:" + response.toString());
                Gson gson = new Gson();
                JSONObject horoscope = response.getJSONObject(HOROSCOPE_KEY);
                Iterator<String> it = response.getJSONObject(HOROSCOPE_KEY).keys();
                while (it.hasNext()) {
                    collection.sodiacSigns.add(gson.fromJson(horoscope.getJSONObject(it.next()).toString(), SodiacSign.class));
                }
                callback.onSuccess(collection.sodiacSigns);
                latch.countDown();
            } catch (InterruptedException | ExecutionException | TimeoutException | JSONException e) {
                System.out.println("Error parsing yoli Response:" + e.getClass().getSimpleName() + e.getMessage());
            }
        });
        thread.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.example.scotiabankplus;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class RequestQueueFactory {
    private static RequestQueueFactory instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private final Context context;

    private RequestQueueFactory(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized RequestQueueFactory getInstance(Context context) {
        if (instance == null) {
            instance = new RequestQueueFactory(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

}
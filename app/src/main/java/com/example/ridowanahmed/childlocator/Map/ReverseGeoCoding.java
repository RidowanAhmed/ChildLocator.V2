package com.example.ridowanahmed.childlocator.Map;

import android.content.Context;
import android.location.Address;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ridowan Ahmed on 0023, August, 23, 2017.
 */

public class ReverseGeoCoding extends AsyncTask<Double, Void, String> {

    Context context;
    private Address address;
    private String GEOCODINGKEY = "&key=AIzaSyDVszB1oT1d0QZWskWzoyfGrJiE2UiZMLo";
    private String REVERSE_GEOCODING_URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=";

    public ReverseGeoCoding(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Double... params) {
        if (params[0] != null) {
            String result = "";
            try {
                String mUrl = REVERSE_GEOCODING_URL + params[0] + ","
                        + params[1] + GEOCODINGKEY;

                URL url = new URL(mUrl);
                HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                httpsURLConnection.setReadTimeout(10000);
                httpsURLConnection.setConnectTimeout(15000);
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.connect();
                int mStatus = httpsURLConnection.getResponseCode();
                if (mStatus == 200)
                    return readResponse(httpsURLConnection.getInputStream()).toString();
                return result;

            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;

        }
        return null;
    }

    private static StringBuilder readResponse(InputStream inputStream) throws IOException, NullPointerException {
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder;
    }
}
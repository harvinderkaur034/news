package com.example.android.news;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp-hp on 13-05-2017.
 */


public class FetchData {
    private FetchData() {
    }

    private static final String LOG_TAG = FetchData.class.getSimpleName();

    public static List<News> fetchData(String requestURL) {
        URL url = createUrl(requestURL);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Request Problem", e);
        }
        List<News> newses = extraData(jsonResponse);
        return newses;
    }

    private static URL createUrl(String url) {
        URL finl_url = null;
        try {
            finl_url = new URL(url);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem in URL", e);
        }
        return finl_url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = getStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response - " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "problem in retrieving data ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String getStream(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String reads = reader.readLine();
            while (reads != null) {
                builder.append(reads);
                reads = reader.readLine();
            }
        }
        return builder.toString();
    }

    private static List<News> extraData(String data) {
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        List<News> Newss = new ArrayList<>();
        try {
            JSONObject Resp = new JSONObject(data);
            JSONObject response = Resp.getJSONObject("response");
            JSONArray result = response.getJSONArray("results");
            for (int i = 0; i < result.length(); i++) {
                JSONObject Current = result.optJSONObject(i);
                String date = Current.optString("webPublicationDate");
                String title = Current.getString("webTitle");
                String url = Current.getString("webUrl");
                String section = Current.getString("sectionName");
                News news = new News(date, title, url, section);
                Newss.add(news);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "problem in data fetching ", e);
        }
        return Newss;
    }
}


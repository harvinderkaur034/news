package com.example.android.news;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by hp-hp on 13-05-2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String mURL;

    public NewsLoader(Context context, String URL) {
        super(context);
        mURL = URL;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mURL == null) {
            return null;
        }
        List<News> newses = FetchData.fetchData(mURL);
        return newses;
    }
}


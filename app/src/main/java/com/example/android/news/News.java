package com.example.android.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class News {


    private String mTitleNews;
    private String mDateNews;
    private String mURLNews;
    private String mSectionNews;


    public News(String Date, String Title, String url, String section) {
        mDateNews = Date;
        mTitleNews = Title;
        mURLNews = url;
        mSectionNews = section;
    }


    public String getTitle() {
        return mTitleNews;
    }

    public String getDate() {
        return mDateNews;
    }

    public String getURL() {
        return mURLNews;
    }

    public String getSection() {
        return mSectionNews;
    }

}


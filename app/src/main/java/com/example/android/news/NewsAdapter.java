package com.example.android.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, List<News> newses) {
        super(context, 0, newses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list, parent, false);
        }
        News cNews = getItem(position);
        TextView Date = (TextView) listView.findViewById(R.id.Date);
        String cDate = cNews.getDate();
        Date.setText(cDate);

        TextView tNews = (TextView) listView.findViewById(R.id.Title);
        String cTitle = cNews.getTitle();
        tNews.setText(cTitle);

        TextView sNews = (TextView) listView.findViewById(R.id.Section);
        String cSection = cNews.getSection();
        sNews.setText(cSection);
        return listView;
    }
}

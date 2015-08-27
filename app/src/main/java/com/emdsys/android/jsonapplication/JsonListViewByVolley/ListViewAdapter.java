package com.emdsys.android.jsonapplication.JsonListViewByVolley;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.emdsys.android.jsonapplication.R;

import java.util.List;

/**
 * Created by EMD029 on 8/24/2015.
 */
public class ListViewAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Movie> movieItems;
    ImageLoader imageLoader = AppController2.getInstance().getImageLoader();

    public ListViewAdapter(Activity activity,List<Movie> movieItems){
        this.activity=activity;
        this.movieItems=movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int position) {
        return movieItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater==null){
            inflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView==null){
            convertView=inflater.inflate(R.layout.json_list_row,null);
        }
        if (imageLoader==null){
            imageLoader= AppController2.getInstance().getImageLoader();
        }
        NetworkImageView thumbnail= (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView year = (TextView) convertView.findViewById(R.id.year);

        Movie movie=movieItems.get(position);

        thumbnail.setImageUrl(movie.getThumbnailUrl(), imageLoader);
        title.setText(movie.getTitle());
        rating.setText("Rating:"+String.valueOf(movie.getRating()));
        String genreStr=" ";
        for (String str:movie.getGenre()){
            genreStr+=str+",";
        }
        genreStr=genreStr.length()>0 ? genreStr.substring(0,genreStr.length()-2) : genreStr;
        genre.setText(genreStr);
        year.setText(String.valueOf(movie.getYear()));
        return convertView;
    }
}

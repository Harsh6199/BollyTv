package com.example.harshranjan1.bollytv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    ArrayList<MoviePOJO> movies;
    private Context context;

    private LayoutInflater inflater;

    public GridAdapter(Context context,ArrayList<MoviePOJO> movies)
    {
        this.context=context;
        this.movies = movies;

    }



    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View gridview=convertView;
        if(convertView==null)
        {
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridview=inflater.inflate(R.layout.custom_layout,null);
        }
        MoviePOJO movie=movies.get(position);

        ImageView icon=(ImageView) gridview.findViewById(R.id.icons);
        TextView letter=(TextView) gridview.findViewById(R.id.letters);
        Picasso.get().load(movie.getImg_link()).into(icon);
        letter.setText(movie.getName());
        return gridview;
    }
}

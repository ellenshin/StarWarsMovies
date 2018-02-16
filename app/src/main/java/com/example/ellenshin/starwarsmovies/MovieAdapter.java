package com.example.ellenshin.starwarsmovies;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ellenshin on 2/14/18.
 */

public class MovieAdapter extends BaseAdapter {
    // adapter takes the app itself and a list of data to display
    private Context myContext;
    private ArrayList<Movie> myMovieList;
    private LayoutInflater myInflater;

    //constructor
    public MovieAdapter(Context myContext, ArrayList<Movie> myMovieList) {
        //initialize instance variable
        this.myContext = myContext;
        this.myMovieList = myMovieList;
        this.myInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    //methods
    //a list of methods we need to override

    //gives ypu the number of recipes in the data source

    @Override
    public int getCount() {
        return myMovieList.size();
    }

    // returns the item at specific positin in the data source

    @Override
    public Object getItem(int position) {
        return myMovieList.get(position);
    }

    // returns the row id as associated with the specific position in the list
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // check if the view already exists
        //if yes, you dont need to inflate and findViewbyID again
        if (convertView == null) {
            //inflate
            convertView = myInflater.inflate(R.layout.movie_list_item, parent, false);
            //add the views to the holder
            holder = new ViewHolder();
            holder.titleTextView = convertView.findViewById(R.id.movie_list_title);
            holder.descTextView = convertView.findViewById(R.id.movie_list_desc);
            holder.thumbnailImageView = convertView.findViewById(R.id.movie_list_thumbnail);
            holder.actorsTextView = convertView.findViewById(R.id.movie_list_actors);
            //add the holder to the view
            convertView.setTag(holder);
        } else {
            //get the view holder from convertView
            holder = (ViewHolder)convertView.getTag();
        }
        //get relevant subview of the row view
        TextView titleTextView = holder.titleTextView;
        TextView descTextView = holder.descTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;
        TextView actorsTextView = holder.actorsTextView;
        //get corresponding movie for each row
        Movie movie = (Movie)getItem(position);

        //update the row view's textviews and imageView to display the information
        titleTextView.setText(movie.title);
        descTextView.setText(movie.description);
        actorsTextView.setText(movie.actors);
        //use Picasso library to load image from the image url;
        Picasso.with(myContext).load(movie.imageUrl).into(thumbnailImageView);

        return convertView;
    }

    // viewholder
    // is used to customize what you want to put into the view
    // it depends on the layout design of your row
    // this will be a private static class you have to define
    private static class ViewHolder {
        public TextView titleTextView;
        public TextView descTextView;
        public ImageView thumbnailImageView;
        public TextView actorsTextView;
    }

    //intent is used to pass information between activities
    // intent -> package
    // sender, receiver

}

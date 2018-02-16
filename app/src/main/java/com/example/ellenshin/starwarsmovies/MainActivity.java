package com.example.ellenshin.starwarsmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView myListView;
    private Context myContext;
    private Movie selectedMovie;
    private View selectedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myContext = this;

        //data to display
        final ArrayList<Movie> movieList = Movie.getMoviesFromFile("movies.json", this);

        //create the adapter
        MovieAdapter adapter = new MovieAdapter(this, movieList);

        //find the listview in the layout
        //set the adapter to listview
        myListView = findViewById(R.id.movie_list_view);
        myListView.setAdapter(adapter);

        // 1. each row should be clickable
        // when the row is clicked,
        // the intent is created and send
        myListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selectedMovie = movieList.get(position);
                selectedItem = myListView.getChildAt(position - myListView.getFirstVisiblePosition());
                // create my intent package
                // add all the information needed for detail page
                // startActivity with that intent

                // explicit
                Intent detailIntent = new Intent(myContext, MovieDetailActivity.class);
                // put title and and instruction URL
                detailIntent.putExtra("movie_title", selectedMovie.title);
                detailIntent.putExtra("movie_desc", selectedMovie.description);
                detailIntent.putExtra("movie_poster", selectedMovie.imageUrl);

                startActivityForResult(detailIntent, 1);;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 ) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("radioButtonResult");

                //check to see which box has been selected
                //then display different strings in the text view
                TextView selectedText = (TextView) selectedItem.findViewById(R.id.movie_list_seen);
                if (result.equals("")) {
                    selectedText.setText("Has Seen?");
                } else {
                    selectedText.setText(result);
                }

            }
        }
    }
}

package com.example.ellenshin.starwarsmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by ellenshin on 2/14/18.
 */

public class MovieDetailActivity extends AppCompatActivity {

    private Context myContext;
    private TextView myTitleTextView;
    private TextView myDescTextView;
    private Button mySubmitButton;
    private ImageView myImageView;
    private RadioGroup myRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        myContext = this;
        myTitleTextView = findViewById(R.id.movie_detail_title);
        myDescTextView = findViewById(R.id.movie_detail_desc);
        mySubmitButton = findViewById(R.id.submit_button);
        myImageView = findViewById(R.id.movie_detail_img);
        myRadioGroup = findViewById(R.id.radioGroup);

        mySubmitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
            Intent radioButtonIntent = new Intent();
            String clickedButtonName = "";

            int selectedId = myRadioGroup.getCheckedRadioButtonId();

            if(selectedId != -1) {

                RadioButton selectedButton = findViewById(selectedId);
                clickedButtonName = selectedButton.getText().toString();
            }

            radioButtonIntent.putExtra("radioButtonResult", clickedButtonName);

            setResult(RESULT_OK, radioButtonIntent);
            finish();
            }
        });

        // change welcomeText to display: username + welcome back!
        String movie_title = this.getIntent().getExtras().getString("movie_title");
        String movie_desc = this.getIntent().getExtras().getString("movie_desc");
        String movie_poster = this.getIntent().getExtras().getString("movie_poster");

        Picasso.with(myContext).load(movie_poster).into(myImageView);
        myTitleTextView.setText(movie_title);
        myDescTextView.setText(movie_desc);

    }

}

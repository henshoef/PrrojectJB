package com.example.android.moviemanagment;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<MovieSample> movies = new ArrayList<MovieSample>();
    private String movieName = "";
    private String movieDesc = "";
    private String movieUrl = "";
    private int counter = 1;
    private int sizeW = 0;
    private int sizeH = 0;
    private MenuItem addMovieByMenual;
    private MenuItem addMovieByIntenet;
    private MenuItem deleteAll;
    private MenuItem exit;
    private final int requsetCode = 1;
    private LinearLayout mainLayout;
    private ArrayList<MovieSample> Movies;
    private LinearLayout.LayoutParams buttonDetails;
    private MovieSample movieSample;
    private String state = "add";
    private int resultCode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creating a Layout and setting it's background color
        mainLayout = findViewById(R.id.mainLayout);
        //creating a Button Array
        Movies = new ArrayList<MovieSample>();
        //here we set the height and width of the button and EditText to match their content
        buttonDetails = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        //setting the EditText sizeW by dp casted to px ("setWidth" takes only px)
        Resources r = getResources();
        sizeW = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200,
                r.getDisplayMetrics());
        sizeH = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300,
                r.getDisplayMetrics());

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        // Need to find menu items only here because they belong to the menu and not to the main layout.
        // Also, they are not created yet on the onCreate event:
        addMovieByMenual = menu.findItem(R.id.addMovieByManual);
        addMovieByIntenet = menu.findItem(R.id.addMovieByInternet);
        deleteAll = menu.findItem(R.id.deleteAll);
        exit = menu.findItem(R.id.exit);
        return true;
    }

    // Return true to state that the menu event has been handled.
    // Return false to state that the menu event has not been handled.
    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requsetCode == 1 && resultCode == RESULT_OK) {
            state = "add";
            movieName = data.getStringExtra("name");
            movieDesc = data.getStringExtra("description");
            movieUrl = data.getStringExtra("url");
            movieSample = new MovieSample(this, counter, movieName, movieDesc, movieUrl);
            movieSample.setId(counter);
            movieSample.setText(movieName);
            movieSample.setTextColor(getResources().getColor(R.color.white));
            movieSample.setBackgroundResource(R.drawable.buttons);
            movieSample.setWidth(sizeW);
            movieSample.setHeight(sizeH);
            movieSample.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    state = "edit";
                    Intent i = new Intent(MainActivity.this, AddMovie.class);
                    i.putExtra("state", state);
                    i.putExtra("name", movieName);
                    i.putExtra("description", movieDesc);
                    i.putExtra("url", movieUrl);
                    startActivityForResult(i, 2);
                    onActivityResultEdit(2,resultCode,i);
                }
            });
            movies.add(movieSample);
            mainLayout.addView(movieSample, buttonDetails);
        }
    }
    protected void onActivityResultEdit(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.addMovieByManual || itemId == R.id.deleteAll || itemId == R.id.exit || itemId == R.id.addMovieByManual) {

            addMovieByMenual.setChecked(false);
            addMovieByIntenet.setChecked(false);
            deleteAll.setChecked(false);
            exit.setChecked(false);
            item.setChecked(true);
        }
        switch (itemId) {
            case R.id.addMovieByManual:
                Intent intent = new Intent(this, AddMovie.class);
                intent.putExtra("state", state);
                startActivityForResult(intent, requsetCode);
                onActivityResult(requsetCode, resultCode, intent);
                state = "add";
                counter++;
                return true;
            case R.id.addMovieByInternet:
                return true;
            case R.id.deleteAll:
                return true;
            case R.id.exit:
                finish();
                return true;
        }
        setContentView(mainLayout);
        return false;
    }

}
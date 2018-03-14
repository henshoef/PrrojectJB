package com.example.android.moviemanagment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DownloadImageTask.CallBack {


    private String movieName = "";
    private String movieDesc = "";
    private String movieUrl = "";
    private int counter = 1;
    private float sizeW = 0;
    private float sizeH = 0;
    private MenuItem addMovieByMenual;
    private MenuItem addMovieByIntenet;
    private MenuItem deleteAll;
    private MenuItem exit;
    private final int requsetCode = 1;
    private LinearLayout mainLayout;
    private ArrayList<MovieSample> movies;
    private LinearLayout.LayoutParams imageViewDetails;
    private LinearLayout.LayoutParams TextViewDetails;
    private MovieSample movieSample;
    private String state = "add";
    DownloadImageTask downloadImageTask;
    private int resultCode;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creating a Layout and setting it's background color
        mainLayout = findViewById(R.id.mainLayout);
        //creating a Button Array
        movies = new ArrayList<MovieSample>();
        //here we set the height and width of the button and EditText to match their content


        //setting the EditText sizeW by dp casted to px ("setWidth" takes only px)
        Resources r = getResources();
        sizeW = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200,
                r.getDisplayMetrics());
        sizeH = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300,
                r.getDisplayMetrics());
        TextViewDetails =new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT

        );
        imageViewDetails = new LinearLayout.LayoutParams(
                Math.round(sizeW),
                Math.round(sizeH)

        );
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
public void addMovie(Intent data){
    state = "add";
    movieName = data.getStringExtra("name");
    movieDesc = data.getStringExtra("description");
    movieUrl = data.getStringExtra("url");

    movieSample = new MovieSample(this, counter, movieName, movieDesc, movieUrl);
    TextView movieSampleName=new TextView(this);
    movieSampleName.setText(movieName);
    movieSampleName.setTextColor(getResources().getColor(R.color.white));
    movieSample.setId(counter);
    InputStream imageStream = this.getResources().openRawResource(R.raw.image);
    Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
    movieSample.setImageBitmap(bitmap);
    if (!movieUrl.equals("")) {
        downloadImageTask = new DownloadImageTask(this);
        downloadImageTask.execute(movieUrl);
    }

    movieSample.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });
    movies.add(movieSample);
    mainLayout.addView(movieSampleName, TextViewDetails);
    mainLayout.addView(movieSample, imageViewDetails);
}
    // Return true to state that the menu event has been handled.
    // Return false to state that the menu event has not been handled.
    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requsetCode == 1 && resultCode == RESULT_OK) {
           addMovie(data);
        }
    }
    protected void onActivityResultEdit(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
       // if (itemId == R.id.addMovieByManual || itemId == R.id.deleteAll || itemId == R.id.exit || itemId == R.id.addMovieByManual) {

           // addMovieByMenual.setChecked(false);
           // addMovieByIntenet.setChecked(false);
           // deleteAll.setChecked(false);
          //  exit.setChecked(false);
          //  item.setChecked(true);
       // }
        switch (itemId) {
            case R.id.menuItemSettings:

                return true;
            case R.id.addMovieByManual: {
                Intent intent = new Intent(this, AddMovie.class);
                intent.putExtra("state", state);
                startActivityForResult(intent, requsetCode);
                onActivityResult(requsetCode, resultCode, intent);
                state = "add";
                counter++;
                return true;
            }
            case R.id.addMovieByInternet: {
                return true;
            }
            case R.id.deleteAll: {
                mainLayout.removeAllViews();
                return true;
            }
            case R.id.exit: {
                finish();
                return true;
            }
        }

        return false;
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onSuccses(Bitmap result) {
        movieSample.setImageBitmap(result);
    }
}
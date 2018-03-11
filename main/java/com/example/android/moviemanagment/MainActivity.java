package com.example.android.moviemanagment;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<MovieSample> movies = new ArrayList<MovieSample>();
    private String movieName;
    private String movieDesc;
    private String movieUrl;


    private MenuItem addMovieByMenual;
    private MenuItem addMovieByIntenet;
    private MenuItem deleteAll;
    private MenuItem exit;
private final int requsetCode=1;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //creating a Layout and setting it's background color
        LinearLayout mainLayout = findViewById(R.id.mainLayout);


        //creating a Button Array
        ArrayList<MovieSample> Movie = new ArrayList<MovieSample>();





        //here we set the height and width of the button and EditText to match their content
        LinearLayout.LayoutParams buttonDetails = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );


        //Give rules to position the






        //setting the EditText size by dp casted to px ("setWidth" takes only px)
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,
                r.getDisplayMetrics()
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

    // Return true to state that the menu event has been handled.

    // Return false to state that the menu event has not been handled.

    public boolean onOptionsItemSelected(MenuItem item) {



        int itemId = item.getItemId();



        if(itemId == R.id.addMovieByManual || itemId == R.id.deleteAll || itemId == R.id.exit||itemId == R.id.addMovieByManual) {

            addMovieByMenual.setChecked(false);
            addMovieByIntenet.setChecked(false);
            deleteAll.setChecked(false);
            exit.setChecked(false);
            item.setChecked(true);

        }



        switch (itemId) {



            case R.id.addMovieByManual:
                Intent intent=new Intent(this,AddMovie.class);
               startActivityForResult(intent,requsetCode);

                return true;
            case R.id.addMovieByInternet:



                return true;
            case R.id.deleteAll:

                return true;

            case R.id.exit:
         finish();

                return true;


        }



        return false;

    }


}


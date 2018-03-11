package com.example.hensh.moviemanagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    private MenuItem addMovie;

    private MenuItem deleteAll;

    private MenuItem exit;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);





    }



    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.main, menu);



        // Need to find menu items only here because they belong to the menu and not to the main layout.

        // Also, they are not created yet on the onCreate event:

        addMovie = menu.findItem(R.id.addMovie);

        deleteAll = menu.findItem(R.id.deleteAll);

        exit = menu.findItem(R.id.exit);



        return true;

    }



    // Return true to state that the menu event has been handled.

    // Return false to state that the menu event has not been handled.

    public boolean onOptionsItemSelected(MenuItem item) {



        int itemId = item.getItemId();



        if(itemId == R.id.addMovie || itemId == R.id.deleteAll || itemId == R.id.exit) {

            addMovie.setChecked(false);

            deleteAll.setChecked(false);

            exit.setChecked(false);

            item.setChecked(true);

        }



        switch (itemId) {



            case R.id.addMovie:



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

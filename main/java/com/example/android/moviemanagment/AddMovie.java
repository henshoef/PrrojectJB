package com.example.android.moviemanagment;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddMovie extends AppCompatActivity {
    //EditText title=findViewById(R.id.title);
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
    }

    public void onClickOk(View v) {
        Intent intent = getIntent();
        state = intent.getStringExtra("state");
        EditText title = findViewById(R.id.title);
        EditText description = findViewById(R.id.descrition);
        EditText url = findViewById(R.id.url);
        if (state.equals("add")) {
            // state=intent.getStringExtra("state");
            String movieName = title.getText().toString();
            String desc = description.getText().toString();
            String imageUrl = url.getText().toString();

            intent.putExtra("name", movieName);
            intent.putExtra("description", desc);
            intent.putExtra("url", imageUrl);
            setResult(RESULT_OK, intent);

        } else {
            String movieName = intent.getStringExtra("MovieName");
            String desc = intent.getStringExtra("Desc");
            String imageUrl = intent.getStringExtra("ur");
            title.setText(movieName + "");
            description.setText(desc + "");
            url.setText(imageUrl + "");
        }
        finish();
    }

    public void onClickCancel(View v) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}

package com.example.hensh.moviemanagment;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddMovie extends AppCompatActivity {
    //EditText title=findViewById(R.id.title);
    Intent intent=new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
    }
    public void onClickOk(View v){

        EditText title=findViewById(R.id.title);
        EditText description=findViewById(R.id.descrition);
        EditText url=findViewById(R.id.url);

        String movieName= title.getText().toString();
        String desc=description.getText().toString();
        String imageUrl=url.getText().toString();

        intent.putExtra("name",movieName);
        intent.putExtra("description",desc);
        intent.putExtra("url",imageUrl);

        setResult(RESULT_OK,intent);
        finish();
    }
    public void onClickCancel(View v){
        setResult(RESULT_CANCELED,intent);
        finish();
    }
}

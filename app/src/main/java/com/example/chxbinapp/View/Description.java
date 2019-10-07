package com.example.chxbinapp.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.chxbinapp.R;

public class Description extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getDetails();
    }


    private void getDetails() {

        Intent intent = getIntent();
        String nom = intent.getStringExtra("nom");
        String descrip = getIntent().getStringExtra("description");

        recupDetails(nom, descrip);

    }

    private void recupDetails(String nom, String descrip) {

        TextView name = findViewById(R.id.Text2);
        name.setText(nom);

        TextView Descrip = findViewById(R.id.Text2);
        Descrip.setText(descrip);
    }
}


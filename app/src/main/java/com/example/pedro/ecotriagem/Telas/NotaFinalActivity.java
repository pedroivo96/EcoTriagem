package com.example.pedro.ecotriagem.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.pedro.ecotriagem.R;

public class NotaFinalActivity extends AppCompatActivity {

    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_final);

        addListenerOnRatingBar();
        setTitle("Sua nota final");

    }

    public void addListenerOnRatingBar() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float avaliacao, boolean fromUser) {



                Toast.makeText(NotaFinalActivity.this,
                        String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this, "Avaliação não concluída", Toast.LENGTH_SHORT).show();

        //Seu código aqui dentro
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

}

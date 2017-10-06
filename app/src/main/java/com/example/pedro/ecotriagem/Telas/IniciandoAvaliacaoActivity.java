package com.example.pedro.ecotriagem.Telas;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pedro.ecotriagem.R;

public class IniciandoAvaliacaoActivity extends AppCompatActivity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciando_avaliacao);

        Handler handler = new Handler();
        handler.postDelayed(this, 1000);
    }


    @Override
    public void onBackPressed()
    {
        Toast.makeText(this, "Avaliação não concluída", Toast.LENGTH_SHORT).show();

        //Seu código aqui dentro
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

    @Override
    public void run() {
        startActivity(new Intent(this, PerguntasActivity.class));
        finish();
    }
}

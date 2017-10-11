package com.example.pedro.ecotriagem.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.pedro.ecotriagem.R;

public class ObrigadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obrigado);
        setTitle("Fim da avaliação");
    }

    public void verRanking(View v){
        startActivity(new Intent(this, RankingActivity.class));
        finish();
    }

    public void voltarMenu(View v){
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

    public void onBackPressed(){
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

}

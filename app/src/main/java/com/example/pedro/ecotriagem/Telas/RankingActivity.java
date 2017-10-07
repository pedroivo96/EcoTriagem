package com.example.pedro.ecotriagem.Telas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pedro.ecotriagem.R;
import com.example.pedro.ecotriagem.control.AvaliacaoHotel;
import com.example.pedro.ecotriagem.control.Controle;
import com.example.pedro.ecotriagem.control.ResultadosAdapter;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {

    private ListView ranking;
    ArrayList<AvaliacaoHotel> avaliacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        setTitle("Ranking 10 mais");

        Controle c = new Controle(this);
        avaliacoes = c.ranking();

        ranking = (ListView) findViewById(R.id.listViewRanking);
        ranking.setAdapter(new ResultadosAdapter(this, avaliacoes));

        ranking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AvaliacaoHotel av = (AvaliacaoHotel) adapterView.getAdapter().getItem(i);

                Intent intent = new Intent(getContext(), AvaliacaoHotelActivity.class);
                intent.putExtra("avaliacaoHotel", av);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        //Seu código aqui dentro
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

    private Context getContext(){
        return this;
    }
}

package com.example.pedro.ecotriagem.Telas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pedro.ecotriagem.R;
import com.example.pedro.ecotriagem.control.AvaliacaoHotel;
import com.example.pedro.ecotriagem.control.ResultadosAdapter;

import java.util.ArrayList;

public class ResultadosEncontradosActivity extends AppCompatActivity {

    private ListView resultadosPesquisa;
    ArrayList<AvaliacaoHotel> avaliacoes;
    TextView tnenhum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_encontrados);

        tnenhum = (TextView) findViewById(R.id.tnenhum) ;

        avaliacoes = (ArrayList<AvaliacaoHotel>) getIntent().getSerializableExtra("avaliacoes");

        if(avaliacoes == null){
            tnenhum.setText("Nenhum resultado encontrado");
        }else{
            resultadosPesquisa = (ListView) findViewById(R.id.listResultadosPesquisa);
            resultadosPesquisa.setAdapter(new ResultadosAdapter(this, avaliacoes)); // Seta o adaptador

            resultadosPesquisa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    AvaliacaoHotel av = (AvaliacaoHotel) adapterView.getAdapter().getItem(i);

                    Intent intent = new Intent(getContext(), AvaliacaoHotelActivity.class);
                    intent.putExtra("avaliacaoHotel", av);

                    startActivity(intent);
                }
            });
        }


    }

    @Override
    public void onBackPressed()
    {
        //Seu código aqui dentro
        startActivity(new Intent(this, PesquisarHoteisActivity.class));
        finish();
    }

    private Context getContext(){
        return this;
    }
}

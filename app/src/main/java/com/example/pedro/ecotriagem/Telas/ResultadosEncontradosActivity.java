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
    TextView tNumResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_encontrados);

        resultadosPesquisa = (ListView) findViewById(R.id.listResultadosPesquisa);
        tNumResultados = (TextView) findViewById(R.id.tNumResultados);
        setTitle("Resultados para \'"+(String) getIntent().getSerializableExtra("query")+"\'");

        avaliacoes = (ArrayList<AvaliacaoHotel>) getIntent().getSerializableExtra("avaliacoes");

        if(avaliacoes == null){
            setTitle("Nenhum resultado para '"+(String) getIntent().getSerializableExtra("query")+"\'");
            resultadosPesquisa.setVisibility(View.GONE);
        }else{
            if(avaliacoes.size() == 1)
                tNumResultados.setText("1 resultado encontrado");
            else
                tNumResultados.setText(avaliacoes.size()+" resultados encontrados");
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
        startActivity(new Intent(this, PesquisarHoteisActivity.class));
        finish();
    }

    private Context getContext(){
        return this;
    }
}

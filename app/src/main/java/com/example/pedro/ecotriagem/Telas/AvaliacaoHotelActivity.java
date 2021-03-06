package com.example.pedro.ecotriagem.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pedro.ecotriagem.R;
import com.example.pedro.ecotriagem.control.AvaliacaoHotel;

import java.text.DecimalFormat;

public class AvaliacaoHotelActivity extends AppCompatActivity {

    AvaliacaoHotel avaliacaoHotel;
    RatingBar ratingBar;
    TextView tCidadeEstado, tReciclagem , tTelhadoVerde , tEnergiaLimpa ,
             tEconomizadores , tBicicleta , tApoioCultura , tAcessibilidade, tqAvaliacoes, tNota;
    Button btavaliar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_hotel);

        ratingBar = (RatingBar) findViewById(R.id.ratingBarHotel);
        tCidadeEstado = (TextView) findViewById(R.id.cidadeEstado);
        tReciclagem = (TextView) findViewById(R.id.reciclagem);
        tTelhadoVerde = (TextView) findViewById(R.id.telhadoVerde);
        tEnergiaLimpa = (TextView) findViewById(R.id.energiaLimpa);
        tEconomizadores = (TextView) findViewById(R.id.economizadores);
        tBicicleta = (TextView) findViewById(R.id.bicicleta);
        tApoioCultura = (TextView) findViewById(R.id.apoioCultura);
        tAcessibilidade = (TextView) findViewById(R.id.acessibilidade);
        tqAvaliacoes = (TextView) findViewById(R.id.qAvaliacoes);
        tNota = (TextView) findViewById(R.id.nota);
        btavaliar = (Button) findViewById(R.id.btavaliar);

        Intent i = getIntent();
        avaliacaoHotel = (AvaliacaoHotel) i.getSerializableExtra("avaliacaoHotel");

        setTitle(avaliacaoHotel.nome);
        tCidadeEstado.setText(avaliacaoHotel.cidade + ", " + avaliacaoHotel.estado);

        tReciclagem.append(avaliacaoHotel.reciclagem + " de " + avaliacaoHotel.qAvaliacoes);
        tTelhadoVerde.append(avaliacaoHotel.telhado_verde + " de " +avaliacaoHotel.qAvaliacoes);
        tEnergiaLimpa.append(avaliacaoHotel.energia_limpa + " de " +avaliacaoHotel.qAvaliacoes);
        tEconomizadores.append(avaliacaoHotel.economizadores + " de " +avaliacaoHotel.qAvaliacoes);
        tBicicleta.append(avaliacaoHotel.bicicleta + " de " + avaliacaoHotel.qAvaliacoes);
        tApoioCultura.append(avaliacaoHotel.apoio_cultura + " de " +avaliacaoHotel.qAvaliacoes);
        tAcessibilidade.append(avaliacaoHotel.acessibilidade + " de " +avaliacaoHotel.qAvaliacoes);

        if(avaliacaoHotel.qAvaliacoes == 1)
            tqAvaliacoes.setText(avaliacaoHotel.qAvaliacoes+" avaliação");
        else
            tqAvaliacoes.setText(avaliacaoHotel.qAvaliacoes+" avaliações");

        ratingBar.setRating(avaliacaoHotel.nota);

        DecimalFormat df = new DecimalFormat("0.0");

        tNota.setText(df.format(avaliacaoHotel.nota));
    }

    public void avaliar(View v){
        Intent intent = new Intent(this, CadastroAvaliadorActivity.class);
        intent.putExtra("nome_hotel", avaliacaoHotel.nome);
        intent.putExtra("cidade", avaliacaoHotel.cidade);
        intent.putExtra("estado", avaliacaoHotel.estado);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed()
    {
       super.onBackPressed();
    }
}

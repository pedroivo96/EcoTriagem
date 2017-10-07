package com.example.pedro.ecotriagem.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pedro.ecotriagem.R;
import com.example.pedro.ecotriagem.control.AvaliacaoHotel;

public class AvaliacaoHotelActivity extends AppCompatActivity {

    AvaliacaoHotel avaliacaoHotel;
    RatingBar ratingBar;
    TextView tNomeHotel, tCidadeEstado, tReciclagem , tTelhadoVerde , tEnergiaLimpa ,
             tEconomizadores , tBicicleta , tApoioCultura , tAcessibilidade, tqAvaliacoes, tNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_hotel);

        ratingBar = (RatingBar) findViewById(R.id.ratingBarHotel);
        tNomeHotel = (TextView) findViewById(R.id.nomeHotel);
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

        Intent i = getIntent();
        avaliacaoHotel = (AvaliacaoHotel) i.getSerializableExtra("avaliacaoHotel");

        tNomeHotel.setText(avaliacaoHotel.nome);
        tCidadeEstado.setText(avaliacaoHotel.cidade + ", " + avaliacaoHotel.estado);

        tReciclagem.append(avaliacaoHotel.reciclagem + " de " + avaliacaoHotel.qAvaliacoes);
        tTelhadoVerde.append(avaliacaoHotel.telhado_verde + " de " +avaliacaoHotel.qAvaliacoes);
        tEnergiaLimpa.append(avaliacaoHotel.energia_limpa + " de  " +avaliacaoHotel.qAvaliacoes);
        tEconomizadores.append(avaliacaoHotel.economizadores + " de " +avaliacaoHotel.qAvaliacoes);
        tBicicleta.append(avaliacaoHotel.bicicleta + " de " + avaliacaoHotel.qAvaliacoes);
        tApoioCultura.append(avaliacaoHotel.apoio_cultura + " de " +avaliacaoHotel.qAvaliacoes);
        tAcessibilidade.append(avaliacaoHotel.acessibilidade + " de " +avaliacaoHotel.qAvaliacoes);

        tqAvaliacoes.setText(avaliacaoHotel.qAvaliacoes+" avaliações");
        ratingBar.setRating(avaliacaoHotel.nota);
        tNota.setText(""+avaliacaoHotel.nota);

    }

    @Override
    public void onBackPressed()
    {
       super.onBackPressed();
    }
}

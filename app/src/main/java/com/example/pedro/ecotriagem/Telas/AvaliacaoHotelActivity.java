package com.example.pedro.ecotriagem.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pedro.ecotriagem.R;
import com.example.pedro.ecotriagem.control.AvaliacaoHotel;

public class AvaliacaoHotelActivity extends AppCompatActivity {

    AvaliacaoHotel avaliacaoHotel;
    TextView tNomeHotel, tCidadeEstado, tReciclagem , tTelhadoVerde , tEnergiaLimpa ,
             tEconomizadores , tBicicleta , tApoioCultura , tAcessibilidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_hotel);

        Intent i = getIntent();
        avaliacaoHotel = (AvaliacaoHotel) i.getSerializableExtra("avaliacao");

        tNomeHotel.setText(avaliacaoHotel.nome);
        tCidadeEstado.setText(avaliacaoHotel.cidade + "," + avaliacaoHotel.estado);
        tReciclagem.append(avaliacaoHotel.reciclagem + " de " + avaliacaoHotel.qAvaliacoes);
        tTelhadoVerde.append(avaliacaoHotel.telhado_verde + " de " +avaliacaoHotel.qAvaliacoes);
        tEnergiaLimpa.append(avaliacaoHotel.energia_limpa + " de  " +avaliacaoHotel.qAvaliacoes);
        tEconomizadores.append(avaliacaoHotel.economizadores + " de " +avaliacaoHotel.qAvaliacoes);
        tBicicleta.append(avaliacaoHotel.bicicleta + " de " + avaliacaoHotel.qAvaliacoes);
        tApoioCultura.append(avaliacaoHotel.apoio_cultura + " de " +avaliacaoHotel.qAvaliacoes);
        tApoioCultura.append(avaliacaoHotel.apoio_cultura + " de " +avaliacaoHotel.qAvaliacoes);

    }

    @Override
    public void onBackPressed()
    {
        //Seu código aqui dentro
       super.onBackPressed();
    }
}

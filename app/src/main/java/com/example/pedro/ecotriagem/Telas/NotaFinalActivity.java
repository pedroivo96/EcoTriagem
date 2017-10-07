package com.example.pedro.ecotriagem.Telas;

import android.content.Context;
import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.pedro.ecotriagem.R;
import com.example.pedro.ecotriagem.control.Controle;

import java.util.ArrayList;

public class NotaFinalActivity extends AppCompatActivity {

    RatingBar ratingBar;
    int nota;

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
                int nota = (int) Float.parseFloat(String.valueOf(ratingBar.getRating()));

                String nome_hotel = (String) getIntent().getSerializableExtra("nome_hotel");
                String cidade = (String) getIntent().getSerializableExtra("cidade");
                String estado = (String) getIntent().getSerializableExtra("estado");
                String nome_ava = (String) getIntent().getSerializableExtra("nome_ava");
                String cpf = (String) getIntent().getSerializableExtra("cpf");
                ArrayList<Boolean> respostas = (ArrayList<Boolean>) getIntent().getSerializableExtra("respostas");

                Controle c = new Controle(getContext());

                c.cadastroUser(cpf, nome_ava);
                long idHotel = c.cadastrarHotel(nome_hotel, cidade, estado);

                if(idHotel != -1){
                    if(! c.responder(cpf, idHotel, respostas, nota)){
                        Toast.makeText(getContext(), "Erro no cadastro da avaliação!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Erro no cadastro da avaliação!", Toast.LENGTH_SHORT).show();
                }

                startActivity(new Intent(getContext(), RankingActivity.class));
                finish();

                /*Intent intent = new Intent(getContext(), NotaFinalActivity.class);
                intent.putExtra("nome_hotel", getIntent().getSerializableExtra("nome_hotel"));
                intent.putExtra("cidade", getIntent().getSerializableExtra("cidade"));
                intent.putExtra("estado", getIntent().getSerializableExtra("estado"));
                intent.putExtra("nome_ava", getIntent().getSerializableExtra("nome_ava"));
                intent.putExtra("cpf", getIntent().getSerializableExtra("cpf"));
                intent.putExtra("respostas", getIntent().getSerializableExtra("respostas"));
                intent.putExtra("nota", nota);
                startActivity(intent);
                finish();*/
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

    public Context getContext(){ return this;}

}

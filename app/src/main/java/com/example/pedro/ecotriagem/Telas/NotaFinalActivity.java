package com.example.pedro.ecotriagem.Telas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Confirmação");
        alerta.setMessage("Cancelar a avaliação?");
        alerta.setNegativeButton("Não", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getContext(), MenuActivity.class));
                finish();
            }
        });
        AlertDialog dialog = alerta.create();
        dialog.show();
    }

    public Context getContext(){ return this;}

}

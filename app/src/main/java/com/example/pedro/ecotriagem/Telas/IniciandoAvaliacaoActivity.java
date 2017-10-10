package com.example.pedro.ecotriagem.Telas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pedro.ecotriagem.R;

import static java.security.AccessController.getContext;

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

    @Override
    public void run() {
        Intent intent = new Intent(this, PerguntasActivity.class);
        intent.putExtra("nome_hotel", getIntent().getSerializableExtra("nome_hotel"));
        intent.putExtra("cidade", getIntent().getSerializableExtra("cidade"));
        intent.putExtra("estado", getIntent().getSerializableExtra("estado"));
        intent.putExtra("nome_ava", getIntent().getSerializableExtra("nome_ava"));
        intent.putExtra("cpf", getIntent().getSerializableExtra("cpf"));
        startActivity(intent);
        finish();
    }

    public Context getContext(){
        return this;
    }
}

package com.example.pedro.ecotriagem.Telas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.pedro.ecotriagem.R;
import com.example.pedro.ecotriagem.control.Controle;

import static java.security.AccessController.getContext;

public class IniciandoAvaliacaoActivity extends AppCompatActivity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciando_avaliacao);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Controle c = new Controle(getContext());
        if(c.verificarAvaliacao((String) getIntent().getSerializableExtra("cpf"), (String) getIntent().getSerializableExtra("nome_hotel"),
                (String) getIntent().getSerializableExtra("cidade"), (String) getIntent().getSerializableExtra("estado"))){

            AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
            alerta.setTitle("Alerta");
            alerta.setMessage("Esse hotel já possui uma avaliação associada a esse CPF!");
            alerta.setPositiveButton("voltar ao menu", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getContext(), MenuActivity.class));
                    finish();
                }
            });
            AlertDialog dialog = alerta.create();
            dialog.show();

        }else {
            Handler handler = new Handler();
            handler.postDelayed(this, 1000);
        }
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
        intent.putExtras(getIntent().getExtras());
        startActivity(intent);
        finish();
    }

    public Context getContext(){
        return this;
    }
}

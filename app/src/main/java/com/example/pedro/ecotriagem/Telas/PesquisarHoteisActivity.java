package com.example.pedro.ecotriagem.Telas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pedro.ecotriagem.R;

public class PesquisarHoteisActivity extends AppCompatActivity {

    int radioButtonSelecionado;
    RadioButton rButtonNome;
    RadioButton rButtonCidade;
    LinearLayout lBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_hoteis);

        radioButtonSelecionado = 0;

        rButtonCidade = (RadioButton) findViewById(R.id.radioButtonCidades);
        rButtonNome = (RadioButton) findViewById(R.id.radioButtonNome);
        lBuscar = (LinearLayout) findViewById(R.id.buttonBuscar);

        rButtonCidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButtonSelecionado = 1;
            }
        });

        rButtonNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButtonSelecionado = 2;
            }
        });

        lBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(radioButtonSelecionado == 0){
                    //Nenhum RadioButtonSelecionado


                    Toast.makeText(getContext(), "Você não escolheu nenhuma Opção", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Algum RadioButton foi selecionado
                }

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

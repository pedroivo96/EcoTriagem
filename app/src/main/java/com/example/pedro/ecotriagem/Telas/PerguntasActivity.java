package com.example.pedro.ecotriagem.Telas;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedro.ecotriagem.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class  PerguntasActivity extends AppCompatActivity {

    ArrayList<String> perguntas = new ArrayList<String>();
    ArrayList<Boolean> respostas = new ArrayList<Boolean>();
    TextView tPergunta;
    int perguntaNaTela;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);

        perguntaNaTela = 0;

        perguntas.add("O hotel faz reciclagem de resíduos ?");
        perguntas.add("O empreendimento possui telhado verde em suas dependências ?");
        perguntas.add("Possui fonte de energia limpa ?");
        perguntas.add("Possui equipamentos economizadores de água e energia ?");
        perguntas.add("Disponibiliza biciletas para os hóspedes ?");
        perguntas.add("Possui ações que apoiam o desenvolvimento da cultura local ?");
        perguntas.add("A estrutura é adequada para receber hóspedes com deficiência ?");

        //tNumPergunta = (TextView) findViewById(R.id.tNumPergunta);
        tPergunta = (TextView) findViewById(R.id.tPergunta);

        tPergunta.setText(perguntas.get(perguntaNaTela));
        //tNumPergunta.setText("Pergunta "+(perguntaNaTela+1));

        setTitle("Pergunta "+(perguntaNaTela+1)+" de 7");

        Button bSim = (Button) findViewById(R.id.bSim);
        bSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(perguntaNaTela == 6){
                    //Não há mais perguntas a serem feitas

                    startActivity(new Intent(getContext(), NotaFinalActivity.class));
                    finish();
                }
                else{
                    respostas.add(true);
                    perguntaNaTela++;
                    tPergunta.setText(perguntas.get(perguntaNaTela));
                    setTitle("Pergunta "+(perguntaNaTela+1)+" de 7");
                }
            }


        });

        Button bNao = (Button) findViewById(R.id.bNao);
        bNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(perguntaNaTela == 6){
                    //Não há mais perguntas a serem feitas

                    startActivity(new Intent(getContext(), NotaFinalActivity.class));
                    finish();
                }
                else{
                    respostas.add(false);
                    perguntaNaTela++;
                    tPergunta.setText(perguntas.get(perguntaNaTela));
                    setTitle("Pergunta "+(perguntaNaTela+1)+" de 7");
                }
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

    private Context getContext(){
        return this;
    }
}

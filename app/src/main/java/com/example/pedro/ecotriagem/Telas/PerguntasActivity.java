package com.example.pedro.ecotriagem.Telas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

        perguntas.add("O hotel faz reciclagem de resíduos?");
        perguntas.add("O empreendimento possui telhado verde em suas dependências?");
        perguntas.add("Possui fonte de energia limpa?");
        perguntas.add("Possui equipamentos economizadores de água e energia?");
        perguntas.add("Disponibiliza biciletas para os hóspedes?");
        perguntas.add("Possui ações que apoiam o desenvolvimento da cultura local?");
        perguntas.add("A estrutura é adequada para receber hóspedes com deficiência?");

        tPergunta = (TextView) findViewById(R.id.tPergunta);

        tPergunta.setText(perguntas.get(perguntaNaTela));

        setTitle("Pergunta "+(perguntaNaTela+1)+" de 7");

        Button bSim = (Button) findViewById(R.id.bSim);
        bSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(perguntaNaTela == 6){
                    respostas.add(true);

                    Intent intent = new Intent(getContext(), NotaFinalActivity.class);
                    intent.putExtras(getIntent().getExtras());
                    intent.putExtra("respostas", respostas);
                    startActivity(intent);
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
                    respostas.add(false);

                    Intent intent = new Intent(getContext(), NotaFinalActivity.class);
                    intent.putExtras(getIntent().getExtras());
                    intent.putExtra("respostas", respostas);
                    startActivity(intent);
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

    private Context getContext(){
        return this;
    }
}

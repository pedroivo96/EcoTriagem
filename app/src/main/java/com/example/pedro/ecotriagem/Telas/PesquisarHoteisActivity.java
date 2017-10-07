package com.example.pedro.ecotriagem.Telas;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.pedro.ecotriagem.R;
import com.example.pedro.ecotriagem.control.Controle;

public class PesquisarHoteisActivity extends AppCompatActivity {

    int radioButtonSelecionado;
    RadioButton rButtonNome;
    RadioButton rButtonCidade;
    LinearLayout lBuscar;
    Controle c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_hoteis);

        radioButtonSelecionado = 0;
        c = new Controle(this);

        String[] nomes_hoteis = c.getNomeHoteis(), cidades = NomeCidades.getNomesCidades(), vazio = {""};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes_hoteis == null ? cidades : concatenar(cidades, nomes_hoteis));
        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.pesquisa);
        textView.setAdapter(adapter);

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

                Intent intent = new Intent(getContext(), ResultadosEncontradosActivity.class);

                switch (radioButtonSelecionado){
                    case 1:
                        intent.putExtra("avaliacoes", c.pesquisarHoteisPorCidade(textView.getText().toString()));
                        startActivity(intent);
                        finish();
                        break;
                    case 2:
                        intent.putExtra("avaliacoes", c.pesquisarHoteisPorNome(textView.getText().toString()));
                        startActivity(intent);
                        finish();
                        break;
                    default:
                        Toast.makeText(getContext(), "Escolha uma opção de pesquisa", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

    private String[] concatenar(String[] a1, String[] a2){
        int tam = a1.length + a2.length;
        String[] result = new String[tam];
        for(int i = 0; i < a1.length; i++){
            result[i] = a1[i];
        }
        for(int i = a1.length, j = 0; i < tam; i++, j++){
            result[i] = a2[j];
        }
        return result;
    }

    private Context getContext(){
        return this;
    }
}

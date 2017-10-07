package com.example.pedro.ecotriagem.Telas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pedro.ecotriagem.R;
import com.example.pedro.ecotriagem.control.Controle;

public class CadastroHotelActivity extends AppCompatActivity {

    Spinner estados;
    Button bAvancar;
    Controle c;
    String estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_hotel);

        setTitle("Dados do hotel");

        c = new Controle(this);

        String[] nomes_hoteis = c.getNomeHoteis(), cidades = NomeCidades.getNomesCidades(), vazio = {""};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes_hoteis == null ? vazio : nomes_hoteis);

        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.nome_hotel);

        textView.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cidades);

        final AutoCompleteTextView textView2 = (AutoCompleteTextView) findViewById(R.id.cidade_hotel);

        textView2.setAdapter(adapter2);

        estados = (Spinner) findViewById(R.id.spinnerEstados);
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.estados, R.layout.spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estados.setAdapter(adaptador);

        bAvancar = (Button) findViewById(R.id.bAvancar);
        bAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), IniciandoAvaliacaoActivity.class);
                intent.putExtra("nome_hotel", textView.getText().toString());
                intent.putExtra("cidade", textView2.getText().toString());
                intent.putExtra("estado", estado);
                intent.putExtra("nome_ava", getIntent().getSerializableExtra("nome_ava"));
                intent.putExtra("cpf", getIntent().getSerializableExtra("cpf"));
                startActivity(intent);
                finish();
            }
        });

        estados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                estado = parent.getItemAtPosition(posicao).toString();
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this, "Avaliação não concluída", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

    private Context getContext(){
        return this;
    }
}

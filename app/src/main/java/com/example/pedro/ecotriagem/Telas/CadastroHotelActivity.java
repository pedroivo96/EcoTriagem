package com.example.pedro.ecotriagem.Telas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pedro.ecotriagem.R;

public class CadastroHotelActivity extends AppCompatActivity {

    Spinner estados;
    Button bAvancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_hotel);

        setTitle("Dados do hotel");

        estados = (Spinner) findViewById(R.id.spinnerEstados);
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.estados , R.layout.spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estados.setAdapter(adaptador);

        bAvancar = (Button) findViewById(R.id.bAvancar);
        bAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), IniciandoAvaliacaoActivity.class));
                finish();
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

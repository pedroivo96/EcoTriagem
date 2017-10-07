package com.example.pedro.ecotriagem.Telas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pedro.ecotriagem.R;
import com.example.pedro.ecotriagem.control.VerificarCPF;


public class CadastroAvaliadorActivity extends AppCompatActivity {

    Button bAvancar;
    EditText edtcpf;
    EditText edtnome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_avaliador);

        setTitle("Seus dados");

        edtcpf = (EditText) findViewById(R.id.edtcpf);
        edtcpf.addTextChangedListener(VerificarCPF.insert(VerificarCPF.CPF_MASK, edtcpf));

        edtnome = (EditText) findViewById(R.id.edtnome_avaliador);

        bAvancar = (Button) findViewById(R.id.bAvancar);
        bAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // if(! VerificarCPF.isCPF(edtcpf.getText().toString()))
                   // Toast.makeText(getContext(), "CPF inválido", Toast.LENGTH_SHORT).show();
               // else{
                    Intent intent = new Intent(getContext(), CadastroHotelActivity.class);
                    intent.putExtra("nome_ava", edtnome.getText().toString());
                    intent.putExtra("nome_ava", edtcpf.getText().toString());
                    startActivity(intent);
                    finish();
                //}
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

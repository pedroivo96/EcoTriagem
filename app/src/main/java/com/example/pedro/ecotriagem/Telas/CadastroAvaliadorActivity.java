package com.example.pedro.ecotriagem.Telas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pedro.ecotriagem.R;
import com.example.pedro.ecotriagem.control.Controle;
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

        edtcpf.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        edtnome = (EditText) findViewById(R.id.edtnome_avaliador);

        bAvancar = (Button) findViewById(R.id.bAvancar);
        bAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(isEmpty(edtcpf) || isEmpty(edtnome)){
                Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }else{
                if(! VerificarCPF.isCPF(edtcpf.getText().toString()))
                    Toast.makeText(getContext(), "CPF inválido", Toast.LENGTH_SHORT).show();
                else{
                    Intent intent = null;
                    if(getIntent().getExtras() != null){ //veio de avaliacao hotel
                        intent = new Intent(getContext(), IniciandoAvaliacaoActivity.class);
                        intent.putExtras(getIntent().getExtras());
                    }else{
                        intent = new Intent(getContext(), CadastroHotelActivity.class);
                    }

                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(edtnome.getWindowToken(), 0);

                    intent.putExtra("nome_ava", edtnome.getText().toString());
                    intent.putExtra("cpf", edtcpf.getText().toString());
                    startActivity(intent);
                    finish();
                }
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

    private boolean isEmpty(EditText etText) {
        String text = etText.getText().toString().trim();
        if (text.length()<1)
            return true;
        return false;
    }
}

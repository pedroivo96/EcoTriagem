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
    boolean pularCadastroHotel;
    AutoCompleteTextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_hotel);

        setTitle("Dados do hotel");

        c = new Controle(this);

        String[] nomes_hoteis = c.getNomeHoteis(), cidades = NomeCidades.getNomesCidades(), vazio = {""};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes_hoteis == null ? vazio : nomes_hoteis);

        textView = (AutoCompleteTextView) findViewById(R.id.nome_hotel);

        textView.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cidades);

        textView2 = (AutoCompleteTextView) findViewById(R.id.cidade_hotel);

        textView2.setAdapter(adapter2);

        textView.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        estados = (Spinner) findViewById(R.id.spinnerEstados);
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.estados, R.layout.spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estados.setAdapter(adaptador);

        bAvancar = (Button) findViewById(R.id.bAvancar);
        bAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty(textView) || isEmpty(textView2) || estado.equals("Estado")){
                    Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(textView2.getWindowToken(), 0);

                    Intent intent = new Intent(getContext(), IniciandoAvaliacaoActivity.class);
                    intent.putExtra("nome_hotel", textView.getText().toString());
                    intent.putExtra("cidade", textView2.getText().toString());
                    intent.putExtra("estado", estado);
                    intent.putExtras(getIntent().getExtras());
                    startActivity(intent);
                    finish();
                }

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

    private boolean isEmpty(AutoCompleteTextView etText) {
        String text = etText.getText().toString().trim();
        if (text.length()<1)
            return true;
        return false;
    }
}

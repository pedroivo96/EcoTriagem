package com.example.pedro.ecotriagem.Telas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.pedro.ecotriagem.R;
import com.example.pedro.ecotriagem.control.Controle;

public class MenuActivity extends AppCompatActivity {

    private Button buttonPesquisarHotel;
    private Button buttonAvaliarHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonPesquisarHotel = (Button) findViewById(R.id.buttonPesquisarHotel);

        buttonPesquisarHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PesquisarHoteisActivity.class));
                finish();
            }
        });

        buttonAvaliarHotel = (Button) findViewById(R.id.buttonAvaliarHotel);
        buttonAvaliarHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CadastroAvaliadorActivity.class));
                finish();
            }
        });

    }

    private Context getContext(){
        return this;
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.zerar) {

            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuActivity.this);
            alerta.setTitle("Confirmação");
            alerta.setMessage("Você tem certeza que deseja apagar todos os dados cadastrados?");
            alerta.setNegativeButton("Não", null);
            alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Controle c = new Controle(getContext());
                    c.resetDB();
                }
            });
            AlertDialog dialog = alerta.create();
            dialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.pedro.ecotriagem.Telas;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.pedro.ecotriagem.R;
import com.example.pedro.ecotriagem.Telas.MenuActivity;

public class TextoEcoturismoActivity extends AppCompatActivity {

    private TextView texto;
    private Button avancar;
    WebView mWebView;
    String s = "\tO ecoturismo ou turismo de natureza, segundo a EMBRATUR, é um segmento de atividade turística que utiliza, de forma sustentável, o patrimônio natural e cultural, incentiva sua conservação e busca a formação de uma consciência ambientalista através da interpretação do ambiente, promovendo o bem-estar das populações envolvidas";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto_ecoturismo);

        mWebView = (WebView) findViewById(R.id.webview);

        String text = "<html><body bgcolor=\"#00A664\" text=\"WHITE\"><br>"
                + "<p align=\"justify\">"
                + s
                + "</p> "
                + "</body></html>";

        mWebView.loadData(text,"text/html;charset=UTF-8",null);

        avancar = (Button) findViewById(R.id.Avancar);
        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MenuActivity.class));
                finish();
            }
        });

    }

    private Context getContext(){
        return this;
    }
}

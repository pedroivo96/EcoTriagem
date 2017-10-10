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
    //String s = "\tO ecoturismo ou turismo de natureza, segundo a EMBRATUR, é um segmento de atividade turística que utiliza, de forma sustentável, o patrimônio natural e cultural, incentiva sua conservação e busca a formação de uma consciência ambientalista através da interpretação do ambiente, promovendo o bem-estar das populações envolvidas";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto_ecoturismo);

        mWebView = (WebView) findViewById(R.id.webview);

        String text = "<html><body bgcolor=\"#00A664\" text=\"WHITE\"><p align=\"justify\">O ecoturismo surgiu a partir de um movimento ambiental mundial no final de 1970, em resposta às preocupações com a degradação do meio ambiente relacionado ao desenvolvimento econômico e às questões sociais provocadas pelo turismo em massa sem a devida conscientização ambiental. Portanto, esse segmento do turismo tem por objetivo atingir os princípios fundamentais do desenvolvimento sustentável, em que a \"exploração\" dos elementos naturais ocorre de forma consciente e ecologicamente correta.\n" +
                "\n" +
                " Visando tornar sua experiência o mais fidedigna possível nessa vertente turística, o ECOTRIAGEM surge como uma ferramenta de fiscalização das estadias cadastradas na plataforma. A partir dela, você poderá compor um relatório de avaliação das hotelarias com o intuito de auxiliar o FUNGETUR (Fundo Geral do Turismo) - fundo especial de financiamento, vinculado ao Ministério do Turismo, com orçamento específico, dispondo de patrimônio próprio e autonomia financeira e orçamentária, tendo como finalidade o fomento e a provisão de recursos para o financiamento de empreendimentos turísticos considerados de interesse para o desenvolvimento do turismo nacional beneficiando os projetos turísticos que priorizem a prática do desenvolvimento ambiental e sustentável  (conforme o DECRETO NÚMERO 7.381, DE 2 DE DEZEMBRO DE 2010)."
                + "</p></body></html>";

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

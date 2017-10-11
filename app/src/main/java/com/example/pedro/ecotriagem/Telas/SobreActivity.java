package com.example.pedro.ecotriagem.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pedro.ecotriagem.R;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        setTitle("Sobre");
    }

    public void onBackPressed()
    {
        super.onBackPressed();
    }
}

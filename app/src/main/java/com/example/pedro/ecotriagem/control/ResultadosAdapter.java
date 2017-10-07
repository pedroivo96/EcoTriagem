package com.example.pedro.ecotriagem.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pedro.ecotriagem.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedro on 05/10/17.
 */

public class ResultadosAdapter extends BaseAdapter {

    private Context context;
    private List<AvaliacaoHotel> avaliacoes;

    public ResultadosAdapter(Context context, ArrayList<AvaliacaoHotel> avaliacoes){
        this.context = context;
        this.avaliacoes = avaliacoes;
    }

    @Override
    public int getCount() {

        return avaliacoes != null ? avaliacoes.size() : 0;
    }

    @Override
    public Object getItem(int i) {

        return avaliacoes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = LayoutInflater.from(context).inflate(R.layout.listview_item, viewGroup , false);

        TextView tNomeHotel = v.findViewById(R.id.nomeHotel);
        TextView tCidadeEstado = v.findViewById(R.id.cidadeEstado);
        RatingBar rBar = v.findViewById(R.id.ratingBar);
        TextView tNotaFinal = v.findViewById(R.id.notaFinal);

        tNomeHotel.setText(avaliacoes.get(i).nome);
        tCidadeEstado.setText(avaliacoes.get(i).cidade + ", " +avaliacoes.get(i).estado);
        rBar.setRating(avaliacoes.get(i).nota);

        DecimalFormat df = new DecimalFormat("0.0");

        tNotaFinal.setText(df.format(avaliacoes.get(i).nota));

        return v;
    }
}

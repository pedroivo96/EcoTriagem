package com.example.pedro.ecotriagem.control;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Created by wellyson on 04/10/17.
 */

public class AvaliacaoHotel implements Serializable {

    public long idHotel;
    public String nome;
    public String cidade;
    public String estado;
    public int numEstrela;
    public float nota;
    public int reciclagem;
    public int telhado_verde;
    public int energia_limpa;
    public int economizadores;
    public int bicicleta;
    public int apoio_cultura;
    public int acessibilidade;
    public int qAvaliacoes;

    public AvaliacaoHotel(long idHotel, String nome, String cidade, String estado, float nota,
                          int reciclagem, int telhado_verde, int energia_limpa, int economizadores, int bicicleta,
                          int apoio_cultura, int acessibilidade, int qAvaliacoes) {
        this.idHotel = idHotel;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.setEstrela(nota);
       // DecimalFormat df = new DecimalFormat("0,0");
        //this.nota = ((int) nota) + Float.parseFloat(df.format(nota));
        this.nota = nota;
        this.reciclagem = reciclagem;
        this.telhado_verde = telhado_verde;
        this.energia_limpa = energia_limpa;
        this.economizadores = economizadores;
        this.bicicleta = bicicleta;
        this.apoio_cultura = apoio_cultura;
        this.acessibilidade = acessibilidade;
        this.qAvaliacoes = qAvaliacoes;
    }

    private void setEstrela(float nota){
        if(nota >= 0  && nota <= 1)
            this.numEstrela = 1;
        if(nota > 1  && nota <= 2)
            this.numEstrela = 2;
        if(nota > 2  && nota <= 3)
            this.numEstrela = 3;
        if(nota > 3  && nota <= 4)
            this.numEstrela = 4;
        if(nota > 4  && nota <= 5)
            this.numEstrela = 5;
    }
}

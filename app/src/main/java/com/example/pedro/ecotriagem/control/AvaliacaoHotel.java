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
}

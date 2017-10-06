package com.example.pedro.ecotriagem.control;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by wellyson on 03/10/17.
 */

public class AvaliacoesDB extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "avaliacoes.db";
    private static final int VERSAO_BANCO = 1;
    private static final String TAG = "respostasdb";

    public AvaliacoesDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Criando a tabela 'users'");
        db.execSQL("CREATE TABLE IF NOT EXISTS users (nome text, cpf text);");
        Log.i(TAG, "Tabela 'users' criada com sucesso.");

        Log.i(TAG, "Criando a tabela 'hoteis'");
        db.execSQL("CREATE TABLE IF NOT EXISTS hoteis (_id integer primary key autoincrement, nome text, cidade text, estado text);");
        Log.i(TAG, "Tabela 'hoteis' criada com sucesso.");

        Log.i(TAG, "Criando a tabela 'respostas'");
        db.execSQL("CREATE TABLE IF NOT EXISTS respostas (id_hotel int, cpf_user text, r1 int, r2 int, r3 int, r4 int, r5 int, r6 int, r7 int, estrelas int);");
        Log.i(TAG, "Tabela 'repostas' criada com sucesso.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

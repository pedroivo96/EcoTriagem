package com.example.pedro.ecotriagem.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by wellyson on 03/10/17.
 */

public class Controle {

    private AvaliacoesDB avaliacoesDB;
    private SQLiteDatabase database;

    public Controle(Context context){
        this.avaliacoesDB = new AvaliacoesDB(context);
    }

    public boolean cadastroUser(String cpf, String nome){

        String[] campos = {"cpf", "nome"};
        this.database = this.avaliacoesDB.getReadableDatabase();
        Cursor cursor = this.database.query("users", campos, "cpf = '" + cpf+"'", null, null, null, null);

        if(cursor != null && cursor.getCount() == 1) {
            cursor.close();
            database.close();
            return true;
        }else{
            cursor.close();
            database.close();
            this.database = this.avaliacoesDB.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nome", nome);
            values.put("cpf", cpf);
            this.database.insert("users", null, values);
        }

        database.close();

        return false;
    }

    public long cadastrarHotel(String nomeHotel, String cidade, String estado){

        this.database = this.avaliacoesDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", nomeHotel);
        values.put("cidade", cidade);
        values.put("estado", estado);

        long id = this.database.insert("hoteis", null, values);

        database.close();

        return id;
    }

    public ArrayList<AvaliacaoHotel> pesquisarHoteisPorNome(String nome){

        ArrayList<AvaliacaoHotel> ahs = new ArrayList<>(), result = null;
        String[] campos = {"_id", "nome", "cidade", "estado"};
        this.database = this.avaliacoesDB.getReadableDatabase();

        String[] querys = tratarQuery(nome);
        String pesq = "";
        for(int i = 0; i < querys.length; i++){
            if(i == querys.length - 1)
                pesq += "nome like '%"+querys[i]+"%'";
            else
                pesq += "nome like '%"+querys[i]+"%' or ";
        }

        Cursor cursor = this.database.query("hoteis", campos, pesq, null, null, null, null);

        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false){
                AvaliacaoHotel a = this.avaliar(cursor.getLong(cursor.getColumnIndex("_id")));
                if(a != null)
                    ahs.add(a);
                cursor.moveToNext();
            }
            result = this.ordenarAvaliacoes(ahs);
        }

        cursor.close();
        database.close();

        return result;
    }

    public ArrayList<AvaliacaoHotel> pesquisarHoteisPorCidade(String cidade){

        ArrayList<AvaliacaoHotel> ahs = new ArrayList<>(), result = null;
        String[] campos = {"_id", "nome", "cidade", "estado"};
        this.database = this.avaliacoesDB.getReadableDatabase();
        Cursor cursor = this.database.query("hoteis", campos, "cidade like '%"+cidade+"%'", null, null, null, null);

        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false){
                AvaliacaoHotel a = this.avaliar(cursor.getLong(cursor.getColumnIndex("_id")));
                if(a != null)
                    ahs.add(a);
                cursor.moveToNext();
            }
            result = this.ordenarAvaliacoes(ahs);
        }
        cursor.close();
        database.close();

        return result;
    }

    public boolean responder(String cpf, long idHotel, ArrayList<Boolean> respostas, int numEstrelas){

        this.database = this.avaliacoesDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_hotel", idHotel);
        values.put("cpf_user", cpf);
        for(int i = 1; i <= 7; i++){
            values.put("r"+i, respostas.get(i-1) ? 1 : 0);
        }
        values.put("estrelas", numEstrelas);

        long id = this.database.insert("respostas", null, values);

        database.close();

        if(id != -1)
            return true;

        return false;
    }

    private AvaliacaoHotel avaliar(long idHotel){

        AvaliacaoHotel a = null;
        String[] campos = {"nome", "cidade", "estado"};
        String[] campos2 = {"r1", "r2", "r3", "r4", "r5", "r6", "r7", "estrelas"};
        int[] counts = new int[7];
        float nota = 0;
        SQLiteDatabase database = this.avaliacoesDB.getReadableDatabase();
        Cursor cursor = database.query("hoteis", campos, "_id = "+idHotel, null, null, null, null);

        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String cidade = cursor.getString(cursor.getColumnIndex("cidade"));
            String estado = cursor.getString(cursor.getColumnIndex("estado"));

            cursor = database.query("respostas", campos2, "id_hotel = "+idHotel, null, null, null, null);
            int qAva = cursor.getCount();
            if(cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                while(cursor.isAfterLast() == false) {
                    for (int i = 1, res; i <= 7; i++) {
                        res = cursor.getInt(cursor.getColumnIndex("r" + i));
                        if(res == 1)
                            counts[i-1]++;
                    }
                    nota += cursor.getInt(cursor.getColumnIndex("estrelas"));
                    cursor.moveToNext();
                }

                nota = nota / qAva;
                Log.d("teste", "return");
                a = new AvaliacaoHotel(idHotel, nome, cidade, estado, nota,
                        counts[0], counts[1], counts[2], counts[3], counts[4], counts[5], counts[6], qAva);
            }
        }

        cursor.close();
        database.close();

        return a;
    }

    public ArrayList<AvaliacaoHotel> ranking(){

        AvaliacaoHotel a;
        ArrayList<AvaliacaoHotel> ahs = null, result = null;
        String[] campos = {"_id"};
        this.database = this.avaliacoesDB.getReadableDatabase();
        Cursor cursor = this.database.query("hoteis", campos, null, null, null, null, null);

        if(cursor != null && cursor.getCount() > 0) {
            ahs = new ArrayList<>();
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                a = this.avaliar(cursor.getLong(cursor.getColumnIndex("_id")));
                if(a != null)
                    ahs.add(a);
                cursor.moveToNext();
            }

            cursor.close();
            database.close();

            int tam = ahs.size();

            if(tam >= 10){
                result = new ArrayList<>();
                for(int i = 0; i < 10; i++){
                    a = this.getMaxNota(ahs);
                    result.add(a);
                    ahs.remove(a);
                }
                return result;
            }else if(tam != 0){
                return this.ordenarAvaliacoes(ahs);
            }
        }

        return null;
    }

    private ArrayList<AvaliacaoHotel> ordenarAvaliacoes(ArrayList<AvaliacaoHotel> ahs){

        ArrayList<AvaliacaoHotel> result = new ArrayList<>();
        int tam = ahs.size();
        AvaliacaoHotel a;
        for(int i = 0; i < tam; i++){
            a = this.getMaxNota(ahs);
            result.add(a);
            ahs.remove(a);
        }
        return result;
    }

    private AvaliacaoHotel getMaxNota(ArrayList<AvaliacaoHotel> ahs){
        float max = -1;
        AvaliacaoHotel a = null;
        for(AvaliacaoHotel a2 : ahs){
            if(a2.nota > max){
                max = a2.nota;
                a = a2;
            }
        }
        return a;
    }

    private String[] tratarQuery(String cmd) {

        String[] v = cmd.split(" ");

        for (String s : v) s.trim();

        int cont = 0;
        for (String s : v)
            if (!s.equals("")) cont++;

        String[] aux = new String[cont];

        int j = 0;
        for (String s : v) {
            if (!s.equals("")) {
                aux[j] = s;
                j++;
            }
        }

        return aux;
    }

    public void resetDB(){
        this.database = this.avaliacoesDB.getWritableDatabase();

        database.delete("hoteis", null, null);
        database.delete("users", null, null);
        database.delete("respostas", null, null);

        database.close();
    }
}

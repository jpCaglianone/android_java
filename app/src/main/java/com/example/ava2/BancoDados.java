package com.example.ava2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class BancoDados extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MeuApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PESSOAS = "Pessoas";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_DATA_NASCIMENTO = "dataNascimento";
    private static final String COLUMN_ALTURA = "altura";
    private static final String COLUMN_PESO = "peso";
    private static final String COLUMN_SEXO = "sexo";

    public BancoDados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PESSOAS_TABLE = "CREATE TABLE " + TABLE_PESSOAS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOME + " TEXT,"
                + COLUMN_DATA_NASCIMENTO + " TEXT,"
                + COLUMN_ALTURA + " TEXT,"
                + COLUMN_PESO + " TEXT,"
                + COLUMN_SEXO + " TEXT"
                + ")";
        db.execSQL(CREATE_PESSOAS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualizações de esquema, se necessário
    }


    public void inserirPessoa(Pessoa pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, pessoa.getNome());
        values.put(COLUMN_DATA_NASCIMENTO, pessoa.getDataNascimento());
        values.put(COLUMN_ALTURA, pessoa.getAltura());
        values.put(COLUMN_PESO, pessoa.getPeso());
        values.put(COLUMN_SEXO, pessoa.isSexo());

        // Consulta para obter o maior ID atual na tabela Pessoas
        String query = "SELECT MAX(" + COLUMN_ID + ") FROM " + TABLE_PESSOAS;
        Cursor cursor = db.rawQuery(query, null);
        long novoId = 1; // Valor padrão se a tabela estiver vazia

        if (cursor != null && cursor.moveToFirst()) {
            novoId = cursor.getLong(0) + 1;
            cursor.close();
        }

        // Define o novo ID no ContentValues
        values.put(COLUMN_ID, novoId);

        System.out.println(novoId);

        // Insere o novo registro com o ID atualizado
        long id = db.insert(TABLE_PESSOAS, null, values);
        db.close();


    }



    public List<Pessoa> consultarTodasPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_PESSOAS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            int columnIndexNome = cursor.getColumnIndex(COLUMN_NOME);
            int columnIndexDataNascimento = cursor.getColumnIndex(COLUMN_DATA_NASCIMENTO);
            int columnIndexAltura = cursor.getColumnIndex(COLUMN_ALTURA);
            int columnIndexPeso = cursor.getColumnIndex(COLUMN_PESO);
            int columnIndexSexo = cursor.getColumnIndex(COLUMN_SEXO);
            int columnIndexId = cursor.getColumnIndex(COLUMN_ID);

            if (cursor.moveToFirst()) {
                do {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setNome(cursor.getString(columnIndexNome));
                    pessoa.setDataNascimento(cursor.getString(columnIndexDataNascimento));


                    pessoa.setAltura(Float.parseFloat(cursor.getString(columnIndexAltura)));
                    pessoa.setPeso(Float.parseFloat(cursor.getString(columnIndexPeso)));

                    pessoa.setSexo(Boolean.parseBoolean(String.valueOf(cursor.getInt(columnIndexSexo))));
                    pessoa.setId(cursor.getInt(columnIndexId));

                    pessoas.add(pessoa);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        db.close();

        return pessoas;
    }
    public Pessoa consultarPessoaPorId(int pessoaId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PESSOAS, null, COLUMN_ID + " = ?", new String[]{String.valueOf(pessoaId)}, null, null, null);

        Pessoa pessoa = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                pessoa = new Pessoa();
                pessoa.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                pessoa.setNome(cursor.getString(cursor.getColumnIndex(COLUMN_NOME)));
                pessoa.setDataNascimento(cursor.getString(cursor.getColumnIndex(COLUMN_DATA_NASCIMENTO)));
                pessoa.setAltura(cursor.getFloat(cursor.getColumnIndex(COLUMN_ALTURA)));
                pessoa.setPeso(cursor.getFloat(cursor.getColumnIndex(COLUMN_PESO)));
                pessoa.setSexo(cursor.getInt(cursor.getColumnIndex(COLUMN_SEXO)) == 1);
            }

            cursor.close();
        }

        db.close();

        return pessoa;
    }


    public int atualizarPessoa(Pessoa pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, pessoa.getNome());
        values.put(COLUMN_DATA_NASCIMENTO, pessoa.getDataNascimento());
        values.put(COLUMN_ALTURA, pessoa.getAltura());
        values.put(COLUMN_PESO, pessoa.getPeso());
        values.put(COLUMN_SEXO, pessoa.isSexo() ? 1 : 0);

        int rowsAffected = db.update(TABLE_PESSOAS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(pessoa.getId())});
        db.close();

        return rowsAffected;
    }



    public void excluirPessoa(int pessoaId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PESSOAS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(pessoaId)});
        db.close();
    }

    public void excluirTodos() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PESSOAS, null, null);
        db.close();
    }

}

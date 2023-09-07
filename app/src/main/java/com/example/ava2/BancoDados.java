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

    // Operação CREATE (Inserir Dados)
    public long inserirPessoa(Pessoa pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, pessoa.getNome());
        values.put(COLUMN_DATA_NASCIMENTO, pessoa.getDataNascimento());
        values.put(COLUMN_ALTURA, pessoa.getAltura());
        values.put(COLUMN_PESO, pessoa.getPeso());
        values.put(COLUMN_SEXO, pessoa.isSexo());

        long id = db.insert(TABLE_PESSOAS, null, values);
        db.close();
        return id;
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

            if (cursor.moveToFirst()) {
                do {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setNome(cursor.getString(columnIndexNome));
                    pessoa.setDataNascimento(cursor.getString(columnIndexDataNascimento));

                    // Converte as strings para float diretamente sem try-catch
                    pessoa.setAltura(Float.parseFloat(cursor.getString(columnIndexAltura)));
                    pessoa.setPeso(Float.parseFloat(cursor.getString(columnIndexPeso)));

                    // O campo "Sexo" é booleano, portanto, use isSexo() para definir o valor booleano
                    pessoa.setSexo(cursor.getInt(columnIndexSexo) == 1);

                    pessoas.add(pessoa);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        db.close();

        return pessoas;
    }






    // Operação UPDATE (Atualizar Dados)
    public int atualizarPessoa(Pessoa pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, pessoa.getNome());
        values.put(COLUMN_DATA_NASCIMENTO, pessoa.getDataNascimento());
        values.put(COLUMN_ALTURA, pessoa.getAltura());
        values.put(COLUMN_PESO, pessoa.getPeso());
        values.put(COLUMN_SEXO, pessoa.isSexo());

        return db.update(TABLE_PESSOAS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(pessoa.getId())});
    }

    // Operação DELETE (Excluir Dados)
    public void excluirPessoa(int pessoaId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PESSOAS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(pessoaId)});
        db.close();
    }
}

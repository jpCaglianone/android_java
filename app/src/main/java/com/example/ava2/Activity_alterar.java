package com.example.ava2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Activity_alterar extends AppCompatActivity {



    private ListView listViewPessoas;
    private BancoDados bancoDados;
    private List<Pessoa> listaPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        listViewPessoas = findViewById(R.id.listViewPessoas);
        bancoDados = new BancoDados(this);

        // Consultar todas as pessoas do banco de dados
        listaPessoas = bancoDados.consultarTodasPessoas();

        Button buttonVoltar = findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fecha a atividade atual e retorna à MainActivity
                finish();
            }
        });

        // Crie um adaptador personalizado para exibir os dados na ListView
        ArrayAdapter<Pessoa> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPessoas);
        listViewPessoas.setAdapter(adapter);
    }






}

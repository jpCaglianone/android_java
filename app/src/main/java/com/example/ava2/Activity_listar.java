package com.example.ava2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ava2.BancoDados;

import java.util.List;

public class Activity_listar extends AppCompatActivity {
    private ListView listViewPessoas;
    private BancoDados bancoDados;
    private List<Pessoa> listaPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        listViewPessoas = findViewById(R.id.listViewPessoas);
        bancoDados = new BancoDados(this);
        Button buttonVoltar = findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        listaPessoas = bancoDados.consultarTodasPessoas();


        MeuAdaptadorPessoas adapter = new MeuAdaptadorPessoas(this, listaPessoas);

        listViewPessoas.setAdapter(adapter);
    }
}

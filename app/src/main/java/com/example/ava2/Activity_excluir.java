package com.example.ava2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_excluir extends AppCompatActivity {

    private BancoDados bancoDados;
    private Button btnExcluirTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir);

        bancoDados = new BancoDados(this);
        btnExcluirTodos = findViewById(R.id.btnExcluir);

        btnExcluirTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chame o método para excluir todos os registros
                bancoDados.excluirTodos();
            }
        });
    }
}

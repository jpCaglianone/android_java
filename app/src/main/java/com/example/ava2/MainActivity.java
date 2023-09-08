package com.example.ava2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIncluir = findViewById(R.id.btnIncluir);
        Button btnListar = findViewById(R.id.btnListar);
        Button btnExcluir = findViewById(R.id.btnExcluir);
        Button btnAlterar = findViewById(R.id.btnAlterar);

        btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_incluir.class);
                startActivity(intent);
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_listar.class);
                startActivity(intent);
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoDados bancoDados = new BancoDados(MainActivity.this);
                bancoDados.excluirTodos();
                Toast.makeText(MainActivity.this, "Todos os dados foram excluídos", Toast.LENGTH_SHORT).show();
            }
        });

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_alterar.class);
                startActivity(intent);
            }
        });
    }
}







































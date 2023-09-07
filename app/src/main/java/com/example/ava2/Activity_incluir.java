package com.example.ava2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Activity_incluir extends Activity {
    private EditText editTextNome, editTextDataNascimento, editTextAltura, editTextPeso;
    private RadioGroup radioGroupSexo;
    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluir);

        editTextNome = findViewById(R.id.editTextNome);
        editTextDataNascimento = findViewById(R.id.editTextDataNascimento);
        editTextAltura = findViewById(R.id.editTextAltura);
        editTextPeso = findViewById(R.id.editTextPeso);
        radioGroupSexo = findViewById(R.id.radioGroupSexo);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        Button buttonVoltar = findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fecha a atividade atual e retorna à MainActivity
                finish();
            }
        });

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenha os dados dos EditText e do RadioGroup
                String nome = editTextNome.getText().toString();
                String dataNascimento = editTextDataNascimento.getText().toString();
                String altura = editTextAltura.getText().toString();
                String peso = editTextPeso.getText().toString();
                String sexo = ((RadioButton) findViewById(radioGroupSexo.getCheckedRadioButtonId())).getText().toString();

                // Crie um objeto Pessoa
                Pessoa pessoa = new Pessoa(nome, dataNascimento, Float.parseFloat(altura), Float.parseFloat(peso), Boolean.parseBoolean(sexo));

                // Insira os dados no banco de dados
                BancoDados bancoDados = new BancoDados(Activity_incluir.this);
                bancoDados.inserirPessoa(pessoa);

                // Limpe os campos após a inserção
                editTextNome.setText("");
                editTextDataNascimento.setText("");
                editTextAltura.setText("");
                editTextPeso.setText("");
            }
        });
    }
}

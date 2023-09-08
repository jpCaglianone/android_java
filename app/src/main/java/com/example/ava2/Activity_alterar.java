package com.example.ava2;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Activity_alterar extends AppCompatActivity {
    private EditText editTextIdConsulta;
    private EditText editTextNomeResultado;
    private EditText editTextDataNascimentoResultado;
    private EditText editTextAlturaResultado;
    private EditText editTextPesoResultado;
    private RadioGroup radioGroupSexoResultado;
    private BancoDados bancoDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        editTextIdConsulta = findViewById(R.id.editTextIdConsulta);
        editTextNomeResultado = findViewById(R.id.editTextNomeResultado);
        editTextDataNascimentoResultado = findViewById(R.id.editTextDataNascimentoResultado);
        editTextAlturaResultado = findViewById(R.id.editTextAlturaResultado);
        editTextPesoResultado = findViewById(R.id.editTextPesoResultado);
        radioGroupSexoResultado = findViewById(R.id.radioGroupSexoResultado);

        bancoDados = new BancoDados(this);

        Button buttonVoltar = findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button buttonConsultarId = findViewById(R.id.buttonConsultarId);
        buttonConsultarId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarPessoaPorId();
            }


        });
        Button buttonAlterar = findViewById(R.id.buttonAlterar);

        buttonAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chama a função para realizar a ação de "Alterar"
                alterarPessoa();
            }
        });
    }

    private void consultarPessoaPorId() {
        String idConsultaStr = editTextIdConsulta.getText().toString();

        if (idConsultaStr.isEmpty()) {
            // Se o campo de consulta estiver vazio, não faz nada
            return;
        }

        int idConsulta = Integer.parseInt(idConsultaStr);


        Pessoa pessoa = bancoDados.consultarPessoaPorId(idConsulta);

        if (pessoa != null) {
            // Preenche os campos de resultado com os dados da pessoa encontrada
            editTextNomeResultado.setText(pessoa.getNome());
            editTextDataNascimentoResultado.setText(pessoa.getDataNascimento());
            editTextAlturaResultado.setText(String.valueOf(pessoa.getAltura()));
            editTextPesoResultado.setText(String.valueOf(pessoa.getPeso()));

            // Define o sexo da pessoa
            RadioButton radioButton = pessoa.isSexo() ? findViewById(R.id.radioButtonFemininoResultado) : findViewById(R.id.radioButtonMasculinoResultado);
            radioButton.setChecked(true);
        } else {
            // Se a pessoa não foi encontrada, limpa os campos de resultado
            editTextNomeResultado.setText("");
            editTextDataNascimentoResultado.setText("");
            editTextAlturaResultado.setText("");
            editTextPesoResultado.setText("");
            radioGroupSexoResultado.clearCheck();
        }
    }


    private void alterarPessoa() {
        String idConsultaStr = editTextIdConsulta.getText().toString();

        if (idConsultaStr.isEmpty()) {
            // Se o campo de consulta estiver vazio, não faz nada
            return;
        }

        int idConsulta = Integer.parseInt(idConsultaStr);

        // Consulta a pessoa por ID no banco de dados
        Pessoa pessoa = bancoDados.consultarPessoaPorId(idConsulta);

        if (pessoa != null) {
            // Atualiza os dados da pessoa com base nos valores dos campos de resultado
            pessoa.setNome(editTextNomeResultado.getText().toString());
            pessoa.setDataNascimento(editTextDataNascimentoResultado.getText().toString());
            pessoa.setAltura(Float.parseFloat(editTextAlturaResultado.getText().toString()));
            pessoa.setPeso(Float.parseFloat(editTextPesoResultado.getText().toString()));

            int selectedRadioButtonId = radioGroupSexoResultado.getCheckedRadioButtonId();
            boolean sexo = (selectedRadioButtonId == R.id.radioButtonFemininoResultado);
            pessoa.setSexo(sexo);

            // Atualiza a pessoa no banco de dados
            int rowsAffected = bancoDados.atualizarPessoa(pessoa);

            if (rowsAffected > 0) {
                Toast.makeText(this, "Pessoa atualizada com sucesso", Toast.LENGTH_SHORT).show();
                editTextNomeResultado.setText("");
                editTextDataNascimentoResultado.setText("");
                editTextAlturaResultado.setText("");
                editTextPesoResultado.setText("");
            } else {
                Toast.makeText(this, "Erro ao atualizar pessoa", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Se a pessoa não foi encontrada, exibe uma mensagem de erro
            Toast.makeText(this, "Pessoa não encontrada", Toast.LENGTH_SHORT).show();
        }
    }
}
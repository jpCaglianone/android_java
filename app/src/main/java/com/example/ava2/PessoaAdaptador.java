package com.example.ava2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

class MeuAdaptadorPessoas extends ArrayAdapter<Pessoa> {
    private Context context;
    private List<Pessoa> pessoas;

    public MeuAdaptadorPessoas(Context context, List<Pessoa> pessoas) {
        super(context, 0, pessoas);
        this.context = context;
        this.pessoas = pessoas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
        }

        Pessoa pessoa = pessoas.get(position);

        TextView idTextView = listItemView.findViewById(R.id.textId);
        TextView nomeTextView = listItemView.findViewById(R.id.textNome);
        TextView alturaTextView = listItemView.findViewById(R.id.textAltura);
        TextView dataNascimentoTextView = listItemView.findViewById(R.id.textDataNascimento);

        // Exiba o ID da pessoa
        idTextView.setText("ID: " + pessoa.getId());
        nomeTextView.setText("Nome: " + pessoa.getNome());
        alturaTextView.setText("Altura: " + pessoa.getAltura());
        dataNascimentoTextView.setText("Data de Nascimento: " + pessoa.getDataNascimento());

        return listItemView;
    }

}

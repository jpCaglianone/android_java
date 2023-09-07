package com.example.ava2;

import java.util.Date;

public class Pessoa {
    private String nome;
    private Date dataNascimento;
    private long id;
    private float altura;
    private float peso;
    private boolean sexo;

    // Construtor vazio
    public Pessoa() {
    }

    // Getters e setters para os atributos

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public String descricaoSexo (){
        if (isSexo()){
            return "Feminino";
        }
        else{
            return "Masculino";
        }
    }
}

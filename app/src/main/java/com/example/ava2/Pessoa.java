package com.example.ava2;

import java.util.Date;

public class Pessoa {
    private String nome;
    private String dataNascimento;
    private long id;
    private float altura;
    private float peso;
    private boolean sexo;

    // Construtor vazio
    public Pessoa() {
    }
    public Pessoa(String nome, String dataNascimento, float altura, float peso, boolean sexo) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;

        this.altura = altura;
        this.peso = peso;
        this.sexo = sexo;
    }
    // Getters e setters para os atributos

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
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

package br.com.codandosimples.model;

import java.time.LocalDate;

public class Cesupa {
    private long id;
    private String nome;
    private String descricao;
    private LocalDate data;
    private int npessoas; // depois verificar se é int ou integer

    public Cesupa(long id, String nome, String descricao, LocalDate data, int npessoas) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.npessoas = npessoas;
    }

    public Cesupa() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getNpessoas() {
        return npessoas;
    }

    public void setNpessoas(int npessoas) {
        this.npessoas = npessoas;
    }
}

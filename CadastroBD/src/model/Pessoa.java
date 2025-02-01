package model;

import java.io.Serializable;

public class Pessoa implements Serializable {

    protected int id;
    protected String nome;
    protected String logradouro;
    protected String telefone;
    protected String email;

    public Pessoa(int id, String nome, String logradouro, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.telefone = telefone;
        this.email = email;
    }
    
    public Pessoa(String nome, String logradouro, String telefone, String email) {
        this.nome = nome;
        this.logradouro = logradouro;
        this.telefone = telefone;
        this.email = email;
    }

    public void exibir() {
        System.out.println(
                "id: " + id
                + "\n"
                + "nome: " + nome
                + "\n"
                + "logradouro: " + logradouro
                + "\n"
                + "telefone: " + telefone
                + "\n"
                + "email: " + email);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

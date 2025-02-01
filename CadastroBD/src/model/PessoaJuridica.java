package model;

public class PessoaJuridica extends Pessoa {

    private String cnpj;

    public PessoaJuridica(int id, String nome, String logradouro, String telefone, String email, String cnpj) {
        super(id, nome, logradouro, telefone, email);
        this.cnpj = cnpj;
    }
    
    public PessoaJuridica(String nome, String logradouro, String telefone, String email, String cnpj) {
        super(nome, logradouro, telefone, email);
        this.cnpj = cnpj;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + cnpj);
    }
    
    public String getCnpj() {
        return cnpj;
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}

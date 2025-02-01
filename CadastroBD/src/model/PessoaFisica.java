package model;

public class PessoaFisica extends Pessoa {

    private String cpf;

    public PessoaFisica(int id, String nome, String logradouro, String telefone, String email, String cpf) {
        super(id, nome, logradouro, telefone, email);
        this.cpf = cpf;
    }
    
    public PessoaFisica(String nome, String logradouro, String telefone, String email, String cpf) {
        super(nome, logradouro, telefone, email);
        this.cpf = cpf;
    }
    
    @Override
    public void exibir() {
        super.exibir();
        System.out.println("cpf: " + cpf);
    }
    
    public String getCpf() {
        return cpf;
    }
        
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

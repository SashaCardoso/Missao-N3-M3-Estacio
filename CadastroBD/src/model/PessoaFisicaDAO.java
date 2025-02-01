package model;

import java.util.ArrayList;
import java.util.List;
import model.util.ConectorBD;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class PessoaFisicaDAO {

    public ConectorBD connection;
    
    public PessoaFisicaDAO(ConectorBD connector) 
    {
        this.connection = connector;
    }
    
    public PessoaFisica getPessoa(int id) throws Exception {
        
        PessoaFisica pessoa = null;
        String sql =  "select *\n" +
                      "from users, individuals\n" +
                      "where users.id = "+ id + "AND " +
                      "users.id = individuals.user_id;";
        
        PreparedStatement ps = connection.getPrepared(sql);
        ResultSet rs = ps.executeQuery();
            
        while(rs.next()) {
            pessoa = new PessoaFisica(rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("logradouro"),
            rs.getString("telefone"),
            rs.getString("email"),
            rs.getString("cpf"));
                
            connection.closeConnection();
                
            connection.closeStatement(sql);
        } 
        
        return pessoa;  
    } 
    
       
    public List<PessoaFisica> getPessoas() throws Exception{
        
        List<PessoaFisica> list = new ArrayList<>();
        
        String sql =  "select *\n" +
                      "from users, individuals\n" +
                      "where users.id = individuals.user_id;";
        PreparedStatement ps = connection.getPrepared(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(new PessoaFisica(rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("logradouro"),
            rs.getString("telefone"),
            rs.getString("email"),
            rs.getString("cpf")));
            connection.closeConnection();
            connection.closeStatement(sql);
        } 
        
        return list;
    }       
   
    
    public void incluir(PessoaFisica pessoafisica) throws Exception {
        
        String tempfisica  = "insert into individuals (user_id, cpf) values (?,?)"; 
        String temppessoa = "insert into users (user_id,name,address,"
                + "telefone, email ) values (?,?,?,?,?,?,?)";
        
        PreparedStatement ps1 = connection.getPrepared(temppessoa);
        ps1.setInt(1, pessoafisica.getId());
        ps1.setString(2, pessoafisica.getNome());
        ps1.setString(3, pessoafisica.getLogradouro());
        ps1.setString(4, pessoafisica.getTelefone());
        ps1.setString(5, pessoafisica.getEmail());
        ps1.execute();
        
        PreparedStatement ps = connection.getPrepared(tempfisica);
        ps.setInt(1, pessoafisica.getId());
        ps.setString(2, pessoafisica.getCpf());
        ps.execute();
        
        connection.closeConnection();
        connection.closeStatement(tempfisica);
        connection.closeStatement(temppessoa);
    }
    
    public void alterar(int id, String cpf, String nome, String logradouro, String telefone, String email) throws Exception {
        PessoaFisica pessoa = getPessoa(id);
        
        String tempfisica = "UPDATE individuals SET cpf=? where user_id = "+id;
        String temppessoa = "UPDATE users SET nome=?, logradouro=?,"
                + "telefone=?, email=? WHERE id= "+id;
        
        PreparedStatement ps = connection.getPrepared(tempfisica);
        PreparedStatement ps1 = connection.getPrepared(temppessoa);
        
        if(cpf.equals("")){
            ps.setString(1, pessoa.getCpf());
        } else{
            ps.setString(1, cpf);
        }
        
        if(nome.equals("")){
            ps1.setString(1, pessoa.getNome());
        } else{
            ps1.setString(1, nome);
        } 
        
        if(logradouro.equals("")){
            ps1.setString(2, pessoa.getLogradouro());
        } else{
            ps1.setString(2, logradouro);
        }
        
        if(telefone.equals("")){
            ps1.setString(3, pessoa.getTelefone());
        } else{
            ps1.setString(3, telefone);
        }
        
        if(email.equals("")){
            ps1.setString(4, pessoa.getEmail());
        } else{
            ps1.setString(4, email);
        } 
        
        ps1.execute();
        ps.execute();
        
        connection.closeConnection();
        connection.closeStatement(tempfisica);
        connection.closeStatement(temppessoa);
    }
    
    public void excluir(int id) throws Exception{
        String tempfisica = "DELETE FROM individuals WHERE user_id="+id;
        String temppessoa = "DELETE FROM users WHERE id="+id;
        
        PreparedStatement ps = connection.getPrepared(tempfisica);
        PreparedStatement ps1 = connection.getPrepared(temppessoa);
        
        ps.execute();
        ps1.execute();
        
        connection.closeConnection();
        connection.closeStatement(tempfisica);
        connection.closeStatement(temppessoa);
    }
}
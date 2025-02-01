package model;

import java.util.ArrayList;
import java.util.List;
import model.util.ConectorBD;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class PessoaJuridicaDAO {
  
    public ConectorBD connection;
    
    public PessoaJuridicaDAO(ConectorBD connector) 
    {
        this.connection = connector;
    }
    
    public PessoaJuridica getPessoa(int id) throws Exception {
        
        PessoaJuridica pessoa = null;
        String sql =  "select *\n" +
                      "from users, companies\n" +
                      "where users.id = "+ id + "AND " +
                      "users.id = companies.user_id;";
        
        PreparedStatement ps = connection.getPrepared(sql);
        ResultSet rs = ps.executeQuery();
            
        while(rs.next()) {
            pessoa = new PessoaJuridica(rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("logradouro"),
            rs.getString("telefone"),
            rs.getString("email"),
            rs.getString("cnpj"));
                
            connection.closeConnection();
                
            connection.closeStatement(sql);
        } 
        
        return pessoa;  
    } 
    
       
    public List<PessoaJuridica> getPessoas() throws Exception{
        
        List<PessoaJuridica> list = new ArrayList<>();
        
        String sql =  "select *\n" +
                      "from users, companies\n" +
                      "where users.id = companies.user_id;";
        PreparedStatement ps = connection.getPrepared(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(new PessoaJuridica(rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("logradouro"),
            rs.getString("telefone"),
            rs.getString("email"),
            rs.getString("cnpj")));
            connection.closeConnection();
            connection.closeStatement(sql);
        } 
        
        return list;
    }       
   
    
    public void incluir(PessoaJuridica pessoafisica) throws Exception {
        
        String tempjuridica  = "insert into companies (user_id, cnpj) values (?,?)"; 
        String temppessoa = "insert into users (user_id,name,address,"
                + "telefone, email ) values (?,?,?,?,?,?,?)";
        
        PreparedStatement ps1 = connection.getPrepared(temppessoa);
        ps1.setInt(1, pessoafisica.getId());
        ps1.setString(2, pessoafisica.getNome());
        ps1.setString(3, pessoafisica.getLogradouro());
        ps1.setString(4, pessoafisica.getTelefone());
        ps1.setString(5, pessoafisica.getEmail());
        ps1.execute();
        
        PreparedStatement ps = connection.getPrepared(tempjuridica);
        ps.setInt(1, pessoafisica.getId());
        ps.setString(2, pessoafisica.getCnpj());
        ps.execute();
        
        connection.closeConnection();
        connection.closeStatement(tempjuridica);
        connection.closeStatement(temppessoa);
    }
    
    public void alterar(int id, String cnpj, String nome, String logradouro, String telefone, String email) throws Exception {
        PessoaJuridica pessoa = getPessoa(id);
        
        String tempjuridica = "UPDATE companies SET cnpj=? where user_id = "+id;
        String temppessoa = "UPDATE users SET nome=?, logradouro=?,"
                + "telefone=?, email=? WHERE id= "+id;
        
        PreparedStatement ps = connection.getPrepared(tempjuridica);
        PreparedStatement ps1 = connection.getPrepared(temppessoa);
        
        if(cnpj.equals("")){
            ps.setString(1, pessoa.getCnpj());
        } else{
            ps.setString(1, cnpj);
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
        connection.closeStatement(tempjuridica);
        connection.closeStatement(temppessoa);
    }
    
    public void excluir(int id) throws Exception{
        String tempjuridica = "DELETE FROM companies WHERE user_id="+id;
        String temppessoa = "DELETE FROM users WHERE id="+id;
        
        PreparedStatement ps = connection.getPrepared(tempjuridica);
        PreparedStatement ps1 = connection.getPrepared(temppessoa);
        
        ps.execute();
        ps1.execute();
        
        connection.closeConnection();
        connection.closeStatement(tempjuridica);
        connection.closeStatement(temppessoa);
    }
}
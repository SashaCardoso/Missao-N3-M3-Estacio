package model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConectorBD {  
         
   public Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?zeroDateTimeBehavior=CONVERT_TO_NULL", "sasha", "123");
        return conn;
    }
    
    public void closeConnection() throws Exception {
        getConnection().close();
    }

    public PreparedStatement getPrepared(String sql) throws Exception {
        PreparedStatement ps = getConnection().prepareStatement(sql);
        return ps;
    }
   
    public void closeStatement(String sql) throws Exception {
        getPrepared(sql).close();
    }
    
    public ResultSet getSelect(PreparedStatement ps) throws Exception {
        ResultSet rs = ps.executeQuery();
        return rs;
    }
    
    public void closeResult(PreparedStatement ps) throws Exception {
        getSelect(ps).close();
    }
 }
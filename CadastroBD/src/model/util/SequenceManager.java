package model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class SequenceManager {
        
    public int getValue (String sequence) throws Exception {
        int result = 0;
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?zeroDateTimeBehavior=CONVERT_TO_NULL", "sasha", "123");
        String sql = "SELECT NEXT VALUE FOR "+sequence+" as nextid";
        Statement st = conn.createStatement();
        
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
            result = rs.getInt("nextid");
        
        return result;
    } 
}
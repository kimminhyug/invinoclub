package com.kmh.pools;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionTest {
	 
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://45.119.147.68:3306/kmh1?useUnicode=true&amp;characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PW = "1234";
    
    
    public void testConnection() throws Exception{
        
        Class.forName(DRIVER);
        
        try(Connection con = DriverManager.getConnection(URL, USER, PW)){
            System.out.println(con);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
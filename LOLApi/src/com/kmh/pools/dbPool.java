package com.kmh.pools;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbPool {

	
	public static int test() throws Exception{
		  Class.forName("com.mysql.jdbc.Driver");
		  final String URL = "jdbc:mysql://45.119.147.68:3306/kmh1?characterEncoding=utf8&amp;useSSL=false&amp;autoReconnection=true";
		  final String USER = "root"; //DB ����ڸ� 
		  final String PW = "1234";   //DB ����� ��й�ȣ
		  int index = 0; //0 ���� 1����
		  try(Connection con = (Connection) DriverManager.getConnection(URL, USER, PW)){
		   System.out.println("����");
		   System.out.println(con);
		   index = 1;
		  }catch (Exception e) {
		   System.out.println("�����߻�");
		   index = 0;
		   e.printStackTrace();
		  }
		  return index;
	}

}

package mybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mybatis.entity.User;

public class JdbcMysql {
public static void main(String[] args) {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Connection connection=null;
	Statement statement=null;
	ResultSet rs=null;
	List<User> users=new ArrayList<User>();
	try {
		 connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mybatistest?useUnicode=true&useSSL=true&autoReconnect=true&serverTimezone=UTC&failOverReadOnly=false&maxReconnects=30","mybatistest","mybatistest");
		 statement=connection.createStatement();
		 rs=statement.executeQuery("select * from user");
		 
		 while (rs.next()) {
			 User user=new User(rs.getInt(1));
			 user.setName(rs.getString(2));
			 user.setAge(rs.getInt(3));
				System.out.println(user);
			 users.add(user);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	finally {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (statement!=null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
}

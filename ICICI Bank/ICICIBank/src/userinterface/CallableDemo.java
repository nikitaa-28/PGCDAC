package userinterface;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class CallableDemo {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		

		Class.forName("com.mysql.cj.jdbc.Driver"); //name of driver
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lnt","root","password");
		System.out.println("Connection is successful");
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter increment value");
		int incr=sc.nextInt();
		
		System.out.println("Enter employee number");
		int eno=sc.nextInt();
		
		String str = "call update_proce(?,?)";
		
		CallableStatement stmt=(CallableStatement)con.prepareCall(str);
		
		stmt.setInt(1,incr);
		stmt.setInt(2,eno);
		stmt.execute();
		
		System.out.println("The salary is incremented");
	}

}

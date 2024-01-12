package userinterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import dao.AccountDao;

public class TestAccount1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		Scanner sc=new Scanner(System.in);

		Class.forName("com.mysql.cj.jdbc.Driver"); //name of driver
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lnt","root","password");
		System.out.println("Connection is successful");
		
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select*from account  where accBal>10000 and accNo between 2 and 3");
			
			while(rs.next())
			{
				System.out.println("AccNo is=" + rs.getInt("accNo"));
				System.out.println("AccHolderName is=" + rs.getString("accHolderName"));
				System.out.println("Balance is" + rs.getDouble("accBal"));
			}
			
		
		
		System.out.println("============================");
		
		System.out.println("Enter acc numbers betn a and b and enter amount");
		int a=sc.nextInt();
		int b=sc.nextInt();
		double c=sc.nextDouble();
		
		
		String str="select * from account where accBal>? and accNo between ? and ?";  //placeholders
		
			PreparedStatement ps=con.prepareStatement(str);
			ps.setDouble(1, c);
			ps.setInt(2, a);
			ps.setInt(3, b);
			ps.executeQuery();
			
			Statement stmt1 = con.createStatement();
			ResultSet rs1=stmt1.executeQuery("select*from account  where accBal>10000 and accNo between 2 and 3");
			
			while(rs1.next())
			{
				System.out.println("AccNo is=" + rs1.getInt("accNo"));
				System.out.println("AccHolderName is=" + rs1.getString("accHolderName"));
				System.out.println("Balance is" + rs1.getDouble("accBal"));
			}
			
			rs.close();
			stmt.close();
			rs1.close();
			stmt1.close();
			con.close();
		
	}
	}



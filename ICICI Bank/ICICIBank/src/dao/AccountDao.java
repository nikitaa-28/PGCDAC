package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Account;

public class AccountDao {
	private Statement stmt;
	public void insertRecord(Connection con, Account account)
	{
		String str="insert into account values(?,?,?)";  //placeholders
		try {
			PreparedStatement ps=con.prepareStatement(str);
			
			ps.setInt(1, account.getAccNo());
			ps.setString(2, account.getAccHolderName());
			ps.setDouble(3, account.getAccBal());
			ps.executeUpdate();
			System.out.println("Record inserted");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	public void deleteRecord(Connection con, Account account)
	{
		String str="delete from account where accno=?";  //placeholders
		try {
			PreparedStatement ps=con.prepareStatement(str);
			
			ps.setInt(1, account.getAccNo());
			ps.executeUpdate();
			System.out.println("Record deleted");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	public void updateRecord(Connection con, Account account)
	{
		String str="update account set accbal=? where accno=?";  //placeholders
		try {
			PreparedStatement ps=con.prepareStatement(str);
			
			ps.setInt(2, account.getAccNo());
			ps.setDouble(1, account.getAccBal());
			ps.executeUpdate();
			System.out.println("Record updated");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	public void depositeRecord(Connection con, Account account)
	{
		String str="update account set accbal=accbal+? where accno=?";  //placeholders
		try {
			PreparedStatement ps=con.prepareStatement(str);
			ps.setInt(2, account.getAccNo());
			ps.setDouble(1, account.getAccBal());
			ps.executeUpdate();
			System.out.println("Record updated");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	public void withdrawRecord(Connection con, Account account)
	{
		String str="update account set accbal=accbal-? where accno=?";  //placeholders
		try {
			PreparedStatement ps=con.prepareStatement(str);
			ps.setInt(2, account.getAccNo());
			ps.setDouble(1, account.getAccBal());
			ps.executeUpdate();
			System.out.println("Record updated");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	public void transfer(Connection con, Account account1, Account account2)
	{
		String str="update account set accbal=accbal+? where accno=?";  //placeholders
		try {
			PreparedStatement ps=con.prepareStatement(str);
			ps.setInt(2, account1.getAccNo());
			ps.setDouble(1, account1.getAccBal());
			ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		String str1="update account set accbal=accbal-? where accno=?";  //placeholders
		try {
			PreparedStatement ps1=con.prepareStatement(str1);
			ps1.setInt(2, account2.getAccNo());
			ps1.setDouble(1, account2.getAccBal());
			ps1.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println("Amount transfered");
	}
	public void getAllRecord(Connection con)
	{
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select*from account");
			
			while(rs.next())
			{
				System.out.println("AccNo is=" + rs.getInt("accNo"));
				System.out.println("AccHolderName is=" + rs.getString("accHolderName"));
				System.out.println("Balance is" + rs.getDouble("accBal"));
			}
			rs.close();
			stmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Account> getAllRecord2(Connection con)
	{
		ArrayList<Account> arrayAccount=new ArrayList<Account>();
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select*from account");
			
			while(rs.next())
			{
				int accNo=rs.getInt(1);
				String accHolderName=rs.getString(2);
				double accBal=rs.getDouble(3);
				Account account=new Account();
				account.setAccNo(accNo);
				account.setAccHolderName(accHolderName);
				account.setAccBal(accBal);
				arrayAccount.add(account);	
			}
			rs.close();
			stmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return arrayAccount;
	}
	
	
}

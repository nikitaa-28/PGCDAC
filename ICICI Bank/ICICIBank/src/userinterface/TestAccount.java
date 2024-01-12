package userinterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dao.AccountDao;
import entity.Account;
import validation.AccountValidtion;
import operations.AccountOperation;

public class TestAccount {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Account account1 = new Account();
		Account account2 = new Account(2, "Tanuj", 30000);
		AccountValidtion accountValidtion = new AccountValidtion();
		AccountOperation accountOperation = new AccountOperation();
		AccountDao accountdao=new AccountDao();
		Connection con;


		Class.forName("com.mysql.cj.jdbc.Driver"); //name of driver
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lnt","root","password");
		System.out.println("Connection is successful");
		

		Scanner sc = new Scanner(System.in);
		int num;
		String choice;
		do {
			System.out.println("Welcome to ICIC Bank.. ");
			System.out.println("1. Accept Details");
			System.out.println("2. Update Details");
			System.out.println("3. Delete record");
			System.out.println("4. Display Details");
			System.out.println("5. Deposit Amount");
			System.out.println("6. Withdraw Amount");
			System.out.println("7. Transfer Amount");
			System.out.println("8. Change the Name");
			System.out.println("9. Check the Balance");
			System.out.println("10. Exist");

			System.out.println("Enter the number");
			num = sc.nextInt();

			switch (num) {
			case 1:
				System.out.println("Enter AccNo, Name, Balance");
				int accNo = sc.nextInt();
				String accHolderName = sc.next();
				double balance = sc.nextDouble();
				boolean result = accountValidtion.checkAll(accNo, accHolderName, balance);
				if (result == true) {
					System.out.println("valid inputs....");
					account1.setAccNo(accNo);
					account1.setAccHolderName(accHolderName);
					account1.setAccBal(balance);
					
					accountdao.insertRecord(con, account1);
				} else {
					System.out.println("Please Enter the Valide Details.....!!");
				}
				break;
			case 2:
				System.out.println("Enter AccNo, Balance");
				int accNo3 = sc.nextInt();
				double balance3 = sc.nextDouble();
					account1.setAccNo(accNo3);
					account1.setAccBal(balance3);
					
					accountdao.updateRecord(con, account1);
				
				break;
			case 3:
				System.out.println("Enter AccNo");
				int accnumber = sc.nextInt();
				account1.setAccNo(accnumber);
				accountdao.deleteRecord(con, account1);
				break;

			case 4:
//				System.out.println("=======================================");
//				System.out.println("dISPLAYING THE dETAILS..");
//				System.out.println("AccNo is=" + account1.getAccHolderName());
//				System.out.println("AccHolderName is=" + account1.getAccHolderName());
//				System.out.println("Balance is" + account1.getAccBal());
//				System.out.println("========================================");
				//method 1
				//accountdao.getAllRecord(con);
				//method 2
				ArrayList<Account> arrayacAccount=new ArrayList<Account>();
				arrayacAccount=accountdao.getAllRecord2(con);
				for(int i=0;i<arrayacAccount.size();i++)
				{
					System.out.println(arrayacAccount.get(i));
				}
				
				break;
			case 5:
				System.out.println("Enter the Ammount to Deposit and account number");
				double amt = sc.nextDouble();
					int accNo4=sc.nextInt();
					account1.setAccNo(accNo4);
					account1.setAccBal(amt);
					accountdao.depositeRecord(con, account1);
					System.out.println("Deposit is Successful....");
				break;
			case 6:
				System.out.println("Enter the Ammount to withdraw and account number");
				double amt1 = sc.nextDouble();
					int accNo5=sc.nextInt();
					account1.setAccNo(accNo5);
					account1.setAccBal(amt1);
					accountdao.withdrawRecord(con, account1);
					System.out.println("withdraw is Successful....");
				break;
			case 7:
				System.out.println("Enter the Ammount to transfer and acc no to be transfered and acc from which to transfer");
				double amt2= sc.nextDouble();
				double amt3=amt2;
					int accNo6=sc.nextInt();
					int accNo7=sc.nextInt();
					account1.setAccNo(accNo6);
					account1.setAccBal(amt2);
					account2.setAccNo(accNo7);
					account2.setAccBal(amt3);
					accountdao.transfer(con, account1,account2);
				break;
			case 8:
				System.out.println("Emter the new  Name");
				String name = sc.next();
				boolean result4 = accountValidtion.checkName(name);
				if (result4 == true) {
					account2.setAccHolderName(name);
				}
				System.out.println(account2);
				break;
			case 9:
				
				System.out.println("Enter the account number");
				int accNo1 = sc.nextInt();
				if (accNo1 == 1) {
					System.out.println("Balance is" + account1.getAccBal());
				} else if (accNo1 == 2) {
					System.out.println("Balance is" + account2.getAccBal());
				}
			case 10:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid input");
				break;
			}
			System.out.println("Do you want to  continue? (y/Y");
			choice = sc.next();

		} while (choice.equals("Y") || choice.equals("y"));

	}
}

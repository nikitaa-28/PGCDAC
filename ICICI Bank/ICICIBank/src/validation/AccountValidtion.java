package validation;

public class AccountValidtion {

	
	public boolean checkName(String accHolderName) {
		char ch = ' ';
		boolean validName = false;
		for (int i = 0; i < accHolderName.length(); i++) {
			ch = accHolderName.charAt(i);
			if ((ch >= 65 && ch < +90) || (ch >= 97 && ch <= 122))
				validName = true;
			else {
				validName = false;
				break;
			}
		}
		if (validName == true) {
			System.out.println("Valid Name");
			return true;
		} else {
			System.out.println("");
			return false;
		}
	}


	public boolean checkAll(int accNo, String accHolderName, double accBal) {
		if (accNo < 0) {
			System.out.println("the accno can not be -ve");
			return false;
		}
		if (checkName(accHolderName) == false)
			return false;
		if (accBal < 0) {
			System.out.println("the balance can not be -ve");
			return false;
		}
		if (accBal > 5000000) {
			System.out.println("produce the documents....");
			return false;
		} else
			return true;

	}

	

}

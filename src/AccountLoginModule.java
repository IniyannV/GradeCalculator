import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.IllegalArgumentException;

public class AccountLoginModule {
	
	private static final Exception IllegalArgumentException = null;
	
	static FileReading usernames = null;
	static FileReading passwords = null;
	
	FileWriter usernameWriter = null;
	FileWriter passwordWriter = null;
	
	static String usernameInputLogin = null;
	static String passwordInputLogin = null;
	
	static String usernameInputCreate = null;
	static String passwordInputCreate = null;
	
	loginAction loginType = null;
	String actionInputString = null;
	
	public void login() {
		initalizeFileModifiers();
		
		//user has the option to login or create an account
		do {
			System.out.println("Login on Create Account (type login/create): ");
			actionInputString = (new Scanner(System.in)).nextLine();
			try {
				loginType = loginAction();
			} catch (Exception e) {
				System.out.println("Please enter 'login' or 'create'");
			}
			System.out.println("*******************************************");
		} while (loginType == null);
		
		
		
		//prompts user to login to their account
		if (loginType == loginAction.LOGIN) {
			do {
				System.out.println("Enter Username (case sensitive):");
				usernameInputLogin = (new Scanner(System.in)).nextLine();

				System.out.println("Enter Password (case sensitive): ");
				passwordInputLogin = (new Scanner(System.in)).nextLine();

				if (!loggedIn()) {
					System.out.println("Incorrect username/password \nPlease try again.");
				}
				System.out.println("******************************************");
			} while(!loggedIn());
		}
		
		//prompts user to create an account
		if(loginType == loginAction.CREATE) {
			do {
				System.out.println("Create Username: ");
				usernameInputCreate = (new Scanner(System.in)).nextLine();
				if(usernameTaken()) { //prevents multiple of the same username
					System.out.println("Username not available. Please try a different username.");
				}
				System.out.println("************************");
			} while (usernameTaken());
			
			do {
				System.out.println("Create Password: ");
				passwordInputCreate = (new Scanner(System.in)).nextLine();
				
				if(passwordInputCreate.isEmpty()) {
					System.out.println("You may not make your password blank.");
				}
				
				System.out.println("*********************************");
			} while(passwordInputCreate.isEmpty());
			
			try {
				usernameWriter.write("\n" + usernameInputCreate);
				passwordWriter.write("\n" + passwordInputCreate);
				
				usernameWriter.close();
				passwordWriter.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	
	public static String getUsername() {
		return usernames.fileAsList.get(getAccountIndex());
	}

	private void initalizeFileModifiers() {
		//initializes FileReading
		//usernames = new FileReading("C:\\Users\\inivi\\git\\repository\\Grade Calculator\\src\\usernames.txt");
		//passwords = new FileReading("C:\\Users\\inivi\\git\\repository\\Grade Calculator\\src\\passwords.txt");
		
		usernames = new FileReading(FileDirectories.fileDirectories.get("usernames"));
		passwords = new FileReading(FileDirectories.fileDirectories.get("performance"));
		
		//initializes fileWriiter
//		try {
//			usernameWriter = new FileWriter("C:\\Users\\inivi\\git\\repository\\Grade Calculator\\src\\usernames.txt", true);
//			passwordWriter = new FileWriter("C:\\Users\\inivi\\git\\repository\\Grade Calculator\\src\\passwords.txt", true);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		
		try {
			usernameWriter = new FileWriter(FileDirectories.fileDirectories.get("usernames"), true);
			passwordWriter = new FileWriter(FileDirectories.fileDirectories.get("passwords"), true);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private boolean usernameTaken() {
		return usernames.fileAsList.contains(usernameInputCreate);
	}
	
	private loginAction loginAction() throws Exception {
		if(actionInputString.toLowerCase().equals("create")) {
			return loginAction.CREATE;
		} else if (actionInputString.toLowerCase().equals("login")){
			return loginAction.LOGIN;
		} else {
			throw IllegalArgumentException;
		}
	}
	
	public boolean loggedIn() {
		//Checks if user inputs are in the file
		if(usernames.fileAsList.contains(usernameInputLogin) && passwords.fileAsList.contains(passwordInputLogin)) {
			//Matches the user's password
			if(usernames.fileAsList.indexOf(usernameInputLogin) == passwords.fileAsList.indexOf(passwordInputLogin)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static int getAccountIndex() {
		return usernames.fileAsList.indexOf(usernameInputLogin);
	}
}

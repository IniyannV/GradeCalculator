import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.imageio.IIOException;

/* This project gives you more insights on your grades
 * 
 * Change file directories all throughout the program 
 * to make it compatiable with your specific device
 * 
 * Author: Iniyann Vivekanandan
 */

public class GradeCalculatorMain {
	
	static AccountLoginModule accLoginModule;
	static double scoredPoints;
	static String assignmentTypeString;
	static assignmentType assignmentWorth;
	static int assignmentCount;
	static int mainLoopUserAction;
	static int addGradesCount;
	private static int removeGradesCount;
	private static FileReading testFileReading;
	private static FileWriter testFileWriter;
	
	
	private static void initalizeObjects() {
		accLoginModule = new AccountLoginModule();
	}
	
	
	public static void main(String[] args) {
		initalizeObjects();
		throwFileDirectoryErrors();
		
		loginLoop();
		mainLoop();
		
		System.out.println("Program completed.");
	}
	
	private static void loginLoop() {
		do {
			accLoginModule.login();
		} while (!accLoginModule.loggedIn());
	}
	
	private static void mainLoop() {
		System.out.println("Welcome " + AccountLoginModule.getUsername());
		
		while (!mainLoopExited()) {
			System.out.println("************************************************");
			
			System.out.println("Your current grade average: " + getGradeAverage());
			System.out.println("Input a number based on your desired action:");
			System.out.println("1. Add Grades");
			System.out.println("2. Remove Grades");
			System.out.println("3. Predict Grades");
			System.out.println("4. Exit Program");
			
			
			try {
				mainLoopUserAction = (new Scanner(System.in)).nextInt();
				
				switch (mainLoopUserAction) {
					case 1:
						addGrades();
						break;
						
					case 2:
						removeGrades();
						break;
					
					case 3:
						predictGrades();
						break;
					
					case 4:
						break;
					
					
					default:
						System.out.println("Please enter a number between 1 - 4");
						System.out.println("***********************************");
				}
				
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number between 1 - 4");
				System.out.println("***********************************");
			}
			
		}
	}
	

	private static void predictGrades() {
		
		
	}

	private static double getGradeAverage() {
		try {
			return assignment.getAverage();
		} catch(IndexOutOfBoundsException e) {
			return 0.0;
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}
	
	private static boolean mainLoopExited() {
		return mainLoopUserAction == 4;
	}
	
	private static void removeGrades() {
		do {
			try {
				System.out.println("How many grades would you like to REMOVE (number greater than 0): ");
				removeGradesCount = (new Scanner(System.in)).nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number greater than 0");
				System.out.println("************************");
			}
		} while (removeGradesCount < 0);
		
		for(int i = 0; i < removeGradesCount; i++) {
			System.out.println("Enter your grade (out of 100) on assignment you would like to REMOVE");
			scoredPoints = (new Scanner(System.in)).nextDouble();
			assignmentCount++;
			
			do {
				System.out.println("Summative or performance task (type s or p):");
				assignmentTypeString = (new Scanner(System.in)).nextLine();
			} while ((!assignmentTypeString.toLowerCase().equals("s")) && (!assignmentTypeString.toLowerCase().equals("p")));
			
			if(assignmentTypeString.toLowerCase().equals("s"))
				assignmentWorth = assignmentType.SUMMATIVE;
			
			if(assignmentTypeString.toLowerCase().equals("p"))
				assignmentWorth = assignmentType.PERFORMANCE;
			
			assignment grade = new assignment(scoredPoints, assignmentWorth, assignmentAction.REMOVE);
		}
	}
	
	private static void throwFileDirectoryErrors() {
		try {
			testFileReading = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/summativeTasks.txt");
			testFileReading = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/performanceTasks.txt");
			testFileReading = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/usernames.txt");
			testFileReading = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/passwords.txt");
			
			testFileWriter = new FileWriter("/Users/iniyann/eclipse-workspace/Grade Calculator/src/summativeTasks.txt", true);
			testFileWriter = new FileWriter("/Users/iniyann/eclipse-workspace/Grade Calculator/src/performanceTasks.txt", true);
			testFileWriter = new FileWriter("/Users/iniyann/eclipse-workspace/Grade Calculator/src/usernames.txt", true);
			testFileWriter = new FileWriter("/Users/iniyann/eclipse-workspace/Grade Calculator/src/passwords.txt", true);
		} catch (Exception e) {
			System.out.println("One or more file directories have not been configured properly \nPlease redirect file directories to the correct locations.");
			System.exit(0);
		}
	}
	
	private static void addGrades() {
		
		do {
			try {
				System.out.println("How many grades would you like to ADD (number greater than 0): ");
				addGradesCount = (new Scanner(System.in)).nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number greater than 0");
				System.out.println("************************");
			}
		} while (addGradesCount < 0);
		
		assignmentCount = 1;
		for(int i = 0; i < addGradesCount; i++) {
			System.out.println("Enter your grade (out of 100) on assignment #" + assignmentCount + ":");
			scoredPoints = (new Scanner(System.in)).nextDouble();
			assignmentCount++;
			
			do {
				System.out.println("Summative or performance task (type s or p):");
				assignmentTypeString = (new Scanner(System.in)).nextLine();
			} while ((!assignmentTypeString.toLowerCase().equals("s")) && (!assignmentTypeString.toLowerCase().equals("p")));
			
			if(assignmentTypeString.toLowerCase().equals("s"))
				assignmentWorth = assignmentType.SUMMATIVE;
			
			if(assignmentTypeString.toLowerCase().equals("p"))
				assignmentWorth = assignmentType.PERFORMANCE;
			
			
			assignment grade = new assignment(scoredPoints, assignmentWorth, assignmentAction.ADD);
		}
	}
}

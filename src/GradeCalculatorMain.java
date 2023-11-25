import java.util.Scanner;

public class GradeCalculatorMain {
	
	static AccountLoginModule accLoginModule = null;
	static double scoredPoints;
	static String assignmentTypeString;
	static assignmentType assignmentWorth;
	static int assignmentCount;
	
	private static void initalizeClasses() {
		accLoginModule = new AccountLoginModule();
	}
	
	
	public static void main(String[] args) {
		initalizeClasses();
		do {
			accLoginModule.login();
		} while (!accLoginModule.loggedIn());
		
		addGrades();
	}
	
	private static void addGrades() {
		assignmentCount = 1;
		do {
			System.out.println("Enter your grade (out of 100) on assignment #" + assignmentCount + ":");
			scoredPoints = (new Scanner(System.in)).nextDouble();
			assignmentCount++;
			
			do {
				System.out.println("Summative or performance task (type s or p): ");
				assignmentTypeString = (new Scanner(System.in)).nextLine();
			} while ((!assignmentTypeString.toLowerCase().equals("s")) && (!assignmentTypeString.toLowerCase().equals("p")));
			
			if(assignmentTypeString.toLowerCase().equals("s"))
				assignmentWorth = assignmentType.SUMMATIVE;
			
			if(assignmentTypeString.toLowerCase().equals("p"))
				assignmentWorth = assignmentType.PERFORMANCE;
			
			assignment grade = new assignment(scoredPoints, assignmentWorth);
		} while (assignmentCount < 3);
	}
}

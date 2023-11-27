import java.io.FileWriter;
import java.io.IOException;


public class assignment {
//	private double scoredPoints;
//	private assignmentType assignmentWorth;
	
	private FileWriter assignmentFileWriter;
	private static FileReading assignmentFileReader;
	private static double summativeGradeAverage;
	private static double performanceGradeAverage;
	private static double totalAverage;
	
	public static double getAverage() {
		refreshAverage();
		return totalAverage;
	}
	
	public static void refreshAverage() {
		summativeGradeAverage = 0;
		performanceGradeAverage = 0;
		//Gets average grade for summative assignments
		
		
		assignmentFileReader = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/summativeTasks.txt");
		
		//Gets data of user's grades
		String userSummativeGradeString = assignmentFileReader.fileAsList.get(AccountLoginModule.getAccountIndex());
		
		//String array to split grades
		String[] userSummativeGradeStringArray = userSummativeGradeString.split(",");
		
		//conversion of string array to double array to be used for calculation
		double[] userSummativeGradeDoubleArray = new double[userSummativeGradeStringArray.length];
		
		for (int i = 0; i < userSummativeGradeStringArray.length; i++) {
			userSummativeGradeDoubleArray[i] = Double.parseDouble(userSummativeGradeStringArray[i]);
		}
		
		for (int i = 0; i < userSummativeGradeDoubleArray.length; i++) {
			summativeGradeAverage += userSummativeGradeDoubleArray[i];
		}
		
		//Grade calculations
		summativeGradeAverage /= userSummativeGradeDoubleArray.length;
		summativeGradeAverage *= GradeCalculatorConstants.SUMMATIVE_WORTH;
		summativeGradeAverage /= 100;
		

		
		//Gets average grade for performance assignments

	
		assignmentFileReader = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/performanceTasks.txt");
		
		//Gets data of user's grades
		String userPerformanceGradeString = assignmentFileReader.fileAsList.get(AccountLoginModule.getAccountIndex());
		
		//String array to split grades
		String[] userPerformanceGradeStringArray = userPerformanceGradeString.split(",");
		
		//conversion of string array to double array to be used for calculation
		double[] userPerformanceGradeDoubleArray = new double[userPerformanceGradeStringArray.length];
		
		for (int i = 0; i < userPerformanceGradeStringArray.length; i++) {
			userPerformanceGradeDoubleArray[i] = Double.parseDouble(userPerformanceGradeStringArray[i]);
		}
		
		for (int i = 0; i < userPerformanceGradeDoubleArray.length; i++) {
			performanceGradeAverage  += userPerformanceGradeDoubleArray[i];
		}
		
		//Grade calculations
		performanceGradeAverage /= userPerformanceGradeDoubleArray.length;
		performanceGradeAverage *= GradeCalculatorConstants.PERFORMANCE_WORTH;
		performanceGradeAverage /= 100;
		
		totalAverage = performanceGradeAverage + summativeGradeAverage;
		
	}
	
	public assignment(double scoredPoints, assignmentType assignmentWorth) {
		/*
		this.scoredPoints = scoredPoints;
		this.assignmentWorth = assignmentWorth;
		*/
		//Constructor initializers
		
		if(assignmentWorth == assignmentType.PERFORMANCE) {
			//assignmentFileWriter = new FileWriter("C:\\Users\\inivi\\git\\repository\\Grade Calculator\\src\\performanceTasks.txt", true);
			//assignmentFileReader = new FileReading("C:\\Users\\inivi\\git\\repository\\Grade Calculator\\src\\performanceTasks.txt");
			assignmentFileReader = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/performanceTasks.txt");
			try {
				assignmentFileWriter = new FileWriter("/Users/iniyann/eclipse-workspace/Grade Calculator/src/performanceTasks.txt");
			} catch (IOException e) {
				System.out.println("File not found; redirect or make necessary files.");
				System.exit(0);
			}
		}
		
		if(assignmentWorth == assignmentType.SUMMATIVE) {
			//assignmentFileWriter = new FileWriter("C:\\Users\\inivi\\git\\repository\\Grade Calculator\\src\\summativeTasks.txt", true);
			//assignmentFileReader = new FileReading("C:\\Users\\inivi\\git\\repository\\Grade Calculator\\src\\summativeTasks.txt");
			assignmentFileReader = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/summativeTasks.txt");
			try {
				assignmentFileWriter = new FileWriter("/Users/iniyann/eclipse-workspace/Grade Calculator/src/summativeTasks.txt");
			} catch (IOException e) {
				System.out.println("File not found; redirect or make necessary files.");
				System.exit(0);
			}
		}
		
		
		while(assignmentFileReader.fileAsList.size() <= AccountLoginModule.getAccountIndex()) {
			assignmentFileReader.fileAsList.add("");
		}
		
		assignmentFileReader.fileAsList.add(AccountLoginModule.getAccountIndex(), assignmentFileReader.fileAsList.get(AccountLoginModule.getAccountIndex()) + String.valueOf(scoredPoints) + ",");
		if(GradeCalculatorMain.assignmentCount > 1) {
			assignmentFileReader.fileAsList.remove(AccountLoginModule.getAccountIndex()+1);
		}
		
		for(int i = 0; i < assignmentFileReader.fileAsList.size(); i++) {
			try {
				assignmentFileWriter.write(assignmentFileReader.fileAsList.get(i) + "\n");
			} catch (IOException e) {
				System.out.println("File not found; redirect or make necessary files.");
				System.exit(0);
			}
		}
		
		try {
			assignmentFileWriter.close();
		} catch (IOException e) {
			System.out.println("File not found; redirect or make necessary files.");
			System.exit(0);
		}
		
	}
}

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class assignment {
//	private double scoredPoints;
//	private assignmentType assignmentWorth;
	
	private FileWriter assignmentFileWriter;
	private ArrayList<String> userGradesRemoveArrayList = new ArrayList<String>();
	private static FileReading assignmentFileReader;
	private static FileReading summativeFileReading;
	private static FileReading performanceFileReading;
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
		
		
		summativeFileReading = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/summativeTasks.txt");
		
		//Gets data of user's grades
		String userSummativeGradeString = summativeFileReading.fileAsList.get(AccountLoginModule.getAccountIndex());
		
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

	
		performanceFileReading = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/performanceTasks.txt");
		
		//Gets data of user's grades
		String userPerformanceGradeString = performanceFileReading.fileAsList.get(AccountLoginModule.getAccountIndex());
		
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
	
	public assignment(double scoredPoints, assignmentType assignmentWorth, assignmentAction action) {
		
		if(action == assignmentAction.ADD) {
			//constructs FileReader and Writer
			if(assignmentWorth == assignmentType.PERFORMANCE) {
				assignmentFileReader = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/performanceTasks.txt");
				try {
					assignmentFileWriter = new FileWriter("/Users/iniyann/eclipse-workspace/Grade Calculator/src/performanceTasks.txt");
				} catch (IOException e) {
					System.out.println("File not found; redirect or make necessary files.");
					System.exit(0);
				}
			}
			
			if(assignmentWorth == assignmentType.SUMMATIVE) {
				assignmentFileReader = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/summativeTasks.txt");
				try {
					assignmentFileWriter = new FileWriter("/Users/iniyann/eclipse-workspace/Grade Calculator/src/summativeTasks.txt");
				} catch (IOException e) {
					System.out.println("File not found; redirect or make necessary files.");
					System.exit(0);
				}
			}
			
			//Creates more lines in the file so there is space to append the specified location
			while(assignmentFileReader.fileAsList.size() <= AccountLoginModule.getAccountIndex()) {
				assignmentFileReader.fileAsList.add("");
			}
			//appends the newly inputed grade onto the string of grades that were already there
			assignmentFileReader.fileAsList.add(AccountLoginModule.getAccountIndex(), assignmentFileReader.fileAsList.get(AccountLoginModule.getAccountIndex()) + String.valueOf(scoredPoints) + ",");
			if(GradeCalculatorMain.assignmentCount > 1) {
				//removes the old string of grades (the old one didn't have the latest added grade)
				assignmentFileReader.fileAsList.remove(AccountLoginModule.getAccountIndex()+1);
			}
			
			// uses for loop to iterate and write to file
			for(int i = 0; i < assignmentFileReader.fileAsList.size(); i++) {
				try {
					assignmentFileWriter.write(assignmentFileReader.fileAsList.get(i) + "\n");
				} catch (IOException e) {
					System.out.println("File not found; redirect or make necessary files.");
					System.exit(0);
				}
			}
			
			//closes FileWriter to save the changes
			try {
				assignmentFileWriter.close();
			} catch (IOException e) {
				System.out.println("File not found; redirect or make necessary files.");
				System.exit(0);
			}
		}
		
		
		if(action == assignmentAction.REMOVE) {
			//Constructs file reader and FileWriter
			if(assignmentWorth == assignmentType.PERFORMANCE) {
				assignmentFileReader = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/performanceTasks.txt");
				try {
					assignmentFileWriter = new FileWriter("/Users/iniyann/eclipse-workspace/Grade Calculator/src/performanceTasks.txt");
				} catch (IOException e) {
					System.out.println("File not found; redirect or make necessary files.");
					System.exit(0);
				}
			}
			
			else if(assignmentWorth == assignmentType.SUMMATIVE) {
				assignmentFileReader = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/summativeTasks.txt");
				try {
					assignmentFileWriter = new FileWriter("/Users/iniyann/eclipse-workspace/Grade Calculator/src/summativeTasks.txt");
				} catch (IOException e) {
					System.out.println("File not found; redirect or make necessary files.");
					System.exit(0);
				}
			}
			
			
			String userGradesRemoveString = assignmentFileReader.fileAsList.get(AccountLoginModule.getAccountIndex());
			String[] userGradesRemoveArray = userGradesRemoveString.split(",");
			
			if(!userGradesRemoveString.contains(String.valueOf(scoredPoints))) {
				System.out.println("The grade and type of assesment you have inputed is not here.");

			} else {
				
				for (String grade: userGradesRemoveArray) {
					if(!grade.equals(String.valueOf(scoredPoints))) {
						userGradesRemoveArrayList.add(grade);
					}
				}
				
				userGradesRemoveString = "";
				
				for(String grade: userGradesRemoveArrayList) {
					userGradesRemoveString += (grade + ",");
				}
				
				try {
					for(int i = 0; i < AccountLoginModule.getAccountIndex(); i++) {
						assignmentFileWriter.write("\n");
					}
					
					assignmentFileWriter.write(userGradesRemoveString);
					assignmentFileWriter.close();
				} catch (IOException e) {
					System.out.println("File error");
				}
				
			}
			
		}
	}
}

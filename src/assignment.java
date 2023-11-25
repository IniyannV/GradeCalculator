import java.io.FileWriter;
import java.io.IOException;


public class assignment {
	private double scoredPoints;
	private assignmentType assignmentWorth; 
	private FileWriter assignmentFileWriter;
	private FileReading assignmentFileReader;
	
	public assignment(double scoredPoints, assignmentType assignmentWorth) {
		this.scoredPoints = scoredPoints;
		this.assignmentWorth = assignmentWorth;
		//Constructor initializers
		
		if(assignmentWorth == assignmentType.PERFORMANCE) {
			//assignmentFileWriter = new FileWriter("C:\\Users\\inivi\\git\\repository\\Grade Calculator\\src\\performanceTasks.txt", true);
			//assignmentFileReader = new FileReading("C:\\Users\\inivi\\git\\repository\\Grade Calculator\\src\\performanceTasks.txt");
			assignmentFileReader = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/performanceTasks.txt");
			try {
				assignmentFileWriter = new FileWriter("/Users/iniyann/eclipse-workspace/Grade Calculator/src/performanceTasks.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(assignmentWorth == assignmentType.SUMMATIVE) {
			//assignmentFileWriter = new FileWriter("C:\\Users\\inivi\\git\\repository\\Grade Calculator\\src\\summativeTasks.txt", true);
			//assignmentFileReader = new FileReading("C:\\Users\\inivi\\git\\repository\\Grade Calculator\\src\\summativeTasks.txt");
			assignmentFileReader = new FileReading("/Users/iniyann/eclipse-workspace/Grade Calculator/src/summativeTasks.txt");
			try {
				assignmentFileWriter = new FileWriter("/Users/iniyann/eclipse-workspace/Grade Calculator/src/summativeTasks.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			assignmentFileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		for(int i = 0; i < assignmentFileReader.fileAsList.size(); i++) {
			System.out.println(assignmentFileReader.fileAsList.get(i));
		}
		*/
	}
}

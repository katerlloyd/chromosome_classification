import java.util.ArrayList;
import java.util.Random;

public class Main {
	
	// Displays the chromosome information and an image of the chromosome.
	static void displayInfo(int qArm, int pArm, double armRatio, String centromereType, ArrayList<String> chromosome) {
		
		System.out.println("Long Arm Length (q): " + qArm);
		System.out.println("Short Arm Length (p): " + pArm);
		
		for (String segment : chromosome) {
			System.out.print(segment);
		}
		
		System.out.println();
		System.out.println("Arm Length Ratio: " + String.format("%.1f", armRatio));
		System.out.println("Centromere Type: " + centromereType);
	}
	
	// Randomly selects a centromere index and adds it to the chromosome.
	static int generateCentromereIndex(ArrayList<String> chromosome) {
		
		Random number = new Random();
		int centromereIndex = number.nextInt(chromosome.size() - 1);
		chromosome.add(centromereIndex, "=");
		
		return centromereIndex;
	}
	
	// Determines the length of the long arm.
	static int locateQArm(ArrayList<String> chromosome, int centromereIndex) {
		int qArm  = centromereIndex;
		
		if (((chromosome.size()-1) - centromereIndex) > centromereIndex) {
			qArm = ((chromosome.size()-1) - centromereIndex);
		}
		
		return qArm;
	}
	
	// Determines the length of the short arm.
	static int locatePArm(ArrayList<String> chromosome, int centromereIndex) {
		
		int pArm = ((chromosome.size()-1) - centromereIndex);
		
		if (((chromosome.size()-1) - centromereIndex) > centromereIndex) {
			pArm = centromereIndex;
		}
		
		return pArm;
	}
	
	// Calculates the arm length ratio.
	static double calculateArmRatio(int qArm, int pArm) {
		
		double armRatio = 0.0;
		double qArmDouble = qArm;
		double pArmDouble = pArm;
		
		if (qArm != 0 && pArm != 0) {
			armRatio = (qArmDouble / pArmDouble);
		}
		
		return armRatio;
	}
	
	// Determines the centromere classification type.
	static String determineCentromereType(double armRatio) {
		
		String centromereType = "Undefined";
		
		if (1 <= armRatio && armRatio <= 1.7) {
			centromereType = "Metacentric";
		} else if (1.8 <= armRatio && armRatio <= 3) {
			centromereType = "Submetacentric";
		} else if (3.1 <= armRatio && armRatio <= 6.9) {
			centromereType = "Subtelocentric";
		} else if (armRatio >= 7) {
			centromereType = "Acrocentric";
		} else if (armRatio == 0) {
			centromereType = "Telocentric";
		}
		
		return centromereType;
	}
	
	public static void main(String[] args) {
		
		// Generates a list of 17 dashes to represent a chromosome.
		ArrayList<String> chromosome = new ArrayList<String>();
		while (chromosome.size() != 18) {
			chromosome.add("-");
		}
		
		int centromereIndex = generateCentromereIndex(chromosome);
		
		int qArm = locateQArm(chromosome, centromereIndex);
		int pArm = locatePArm(chromosome, centromereIndex);
		
		double armRatio = calculateArmRatio(qArm, pArm);
		
		String centromereType = determineCentromereType(armRatio);
		
		displayInfo(qArm, pArm, armRatio, centromereType, chromosome);
	}
}

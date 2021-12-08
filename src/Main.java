import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		RSAGenKey keysGen = new RSAGenKey();
		
		Scanner input = new Scanner(System.in);
		boolean flag = true;
		
		System.out.println("enter 1 to generate numbers");
		
		System.out.println("enter 2 if you are providing p, q, and 3");
		System.out.println("enter 3 to quit");
		
		int choice = input.nextInt();
		
		
		switch(choice) {
		case 1:
			System.out.println("numbers are going to be generated");
			keysGen.genearateNumbers(4);
			break;
		case 2:
			System.out.println("enter p");
			BigInteger p = input.nextBigInteger();
			
			System.out.println("enter q");
			BigInteger q = input.nextBigInteger();
			
			System.out.println("enter e");
			BigInteger  e = input.nextBigInteger();
					
			keysGen.generateWithValues(p, q, e);
		case 3: 
			System.out.println("ending");
			break;
		default:
			break;
		}
		
		


	}

}

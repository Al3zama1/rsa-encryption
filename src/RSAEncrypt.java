import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RSAEncrypt {
	

	
	public static void breakIntoBlocks(String data, BigInteger e, BigInteger n) {
		Map<Character, Integer> map = new HashMap<>();
		
		
		map.put('a', 0);
		map.put('b', 1);
		map.put('c', 2);
		map.put('d', 3);
		map.put('e', 4);
		map.put('f', 5);
		map.put('g', 6);
		map.put('h', 7);
		map.put('i', 8);
		map.put('j', 9);
		map.put('k', 10);
		map.put('l', 11);
		map.put('m', 12);
		map.put('n', 13);
		map.put('o', 14);
		map.put('p', 15);
		map.put('q', 16);
		map.put('r', 17);
		map.put('s', 18);
		map.put('t', 19);
		map.put('u', 20);
		map.put('v', 21);
		map.put('w', 22);
		map.put('x', 23);
		map.put('y', 24);
		map.put('z', 25);
		map.put(' ', 26);
		map.put('.', 27);
		map.put(',', 28);

		int chunkLen = 3;
		
		
		
		
		int stringLen = data.length();
		char[] str = null;
		
		
		if (stringLen % chunkLen == 1) {
			str = new char[data.length() + 2];
			
			str[str.length - 1] = ' ';
			str[str.length - 2] = ' ';
		}  
		
		else if (stringLen % chunkLen == 2) {
			str = new char[data.length() + 1];
			str[str.length - 1] = ' ';
		}  else {
			str = new char[data.length()];
		}
		
		
		
		for (int i = 0; i < data.length(); i++) {
			str[i] = data.charAt(i);
			
		}
		
		
		
		String[] lettersConverted = new String[str.length / 3];
		int letterConvertedIndex = 0;
		
		String tempBlock = "";
		

		
		
		
		
		
		for (int i = 1; i <= str.length; i++) {
			String temp = String.format("%02d", map.get(str[i - 1]));
		
			
			if (i  % 3 != 0) {
				tempBlock += temp;
			} else {

				tempBlock += temp;
				lettersConverted[letterConvertedIndex] = tempBlock;
				tempBlock = "";
				letterConvertedIndex += 1;
			}
			
			
		}
		
		
				
		
		

		

		
		encyptMessage(lettersConverted, e, n);
		
		
				
		
		
	}
	
	public static void encyptMessage(String[] bytes, BigInteger e, BigInteger n) {
		String c = "";
				
		
		for (int i = 0; i < bytes.length; i++) {
			int numberValue = Integer.parseInt(bytes[i]);
			BigInteger temp = new BigInteger(String.valueOf(numberValue)).modPow(e, n);
			
			
			c += String.valueOf(temp);
			
			if (i + 1 != bytes.length) {
				c += " ";
			}
		}
		
		
		
		
		try {
			File f = new File("text.enc");
			f.createNewFile();
			
			FileWriter w = new FileWriter("text.enc");
			w.write(c);
			
			w.close();
		} catch(Exception error) {
			error.printStackTrace();
		}
		
		

	}
	
	public static void main(String[] args) {	
		
		
//		try {
//			File text = new File("message.txt");
//			Scanner reader = new Scanner(text);
//			String data = "";
//			
//			while (reader.hasNextLine()) {
//				data += reader.nextLine();
//			
//			}
//			
//			
//			reader.close();
//			
//			
//			breakIntoBlocks(data.toLowerCase(), new BigInteger("2175"), new BigInteger("7917751"));
//		} catch (FileNotFoundException error) {
//			error.printStackTrace();
//		}
		
		

		if (args.length == 2) {
			

			
			try {
				
				String text = null;
		
				File message = new File(args[0]);
				Scanner reader = new Scanner(message);
				
				while (reader.hasNextLine()) {
					text = reader.nextLine();
				}
				
				
				reader.close();
				
				File pubkey = new File(args[1]);
				
				Scanner pubReader = new Scanner(pubkey);
				BigInteger e = new BigInteger(pubReader.nextLine().substring(4));
                BigInteger n = new BigInteger(pubReader.nextLine().substring(4));
                
                breakIntoBlocks(text.toLowerCase(), e, n);

				

				pubReader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			System.err.println("Incorrect parameters.Enter name of file with message, and name of file with public key");
		}
	}

}

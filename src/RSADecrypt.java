import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RSADecrypt {
	
	
	
	public static void decypher(BigInteger d, BigInteger n, String cipherText) {
		
		String[] cipherBlocks = cipherText.split(" ");
		BigInteger[] intBlocks = new BigInteger[cipherBlocks.length];
		
		
		String p = "";
		
		
		
		for (int i = 0; i < cipherBlocks.length; i++) {
			intBlocks[i] = new BigInteger(cipherBlocks[i]).modPow(d, n);
			String val = String.valueOf(intBlocks[i]);
			
			while (val.length() < 6) {
				val = "0" + val;
			}

			p += String.valueOf(val);
			
			if (i + 1 != cipherBlocks.length) {
				p += " ";
			}
		}
		
	
		
	
		
		String[] byteBlocks = p.split(" ");
		
		changeToText(byteBlocks);
		
	}
	
	public static void changeToText(String[] byteBlocks) {
		
		Map<Integer, Character> map = new HashMap<>();
		
		
		map.put(0, 'a');
		map.put(1, 'b');
		map.put(2, 'c');
		map.put(3, 'd');
		map.put(4, 'e');
		map.put(5, 'f');
		map.put(6, 'g');
		map.put(7, 'h');
		map.put(8, 'i');
		map.put(9, 'j');
		map.put(10, 'k');
		map.put(11, 'l');
		map.put(12, 'm');
		map.put(13, 'n');
		map.put(14, 'o');
		map.put(15, 'p');
		map.put(16, 'q');
		map.put(17, 'r');
		map.put(18, 's');
		map.put(19, 't');
		map.put(20, 'u');
		map.put(21, 'v');
		map.put(22, 'w');
		map.put(23, 'x');
		map.put(24, 'y');
		map.put(25, 'z');
		map.put(26, ' ');
		map.put(27, '.');
		map.put(28, ',');
		
		String message = "";

		
		for (int i = 0; i < byteBlocks.length; i++) {
			String str = byteBlocks[i].toString();
			
			message += map.get(Integer.parseInt(str.substring(0, 2)));
			message += map.get(Integer.parseInt(str.substring(2, 4)));
			message += map.get(Integer.parseInt(str.substring(4)));	
		
			
		}
		
		
		
		try {
			File f = new File("text.enc");
			f.createNewFile();
			
			FileWriter w = new FileWriter("text.dec");
			w.write(message);
			
			w.close();
		} catch(Exception error) {
			error.printStackTrace();
		}
			
		
	}
	
	public static void main(String[] args) {
		
		
//		try {
//			File cipher = new File("text.enc");
//			Scanner reader = new Scanner(cipher);
//			String data = reader.nextLine();
//			reader.close();
//			decypher(new BigInteger("2386303"), new BigInteger("7917751"), data);
//		} catch (FileNotFoundException error) {
//			error.printStackTrace();
//		}
		
		

		

		if (args.length == 2) {
			
			try {
				
				File cipher = new File(args[0]);
				Scanner reader = new Scanner(cipher);
				String data = reader.nextLine();
				
				reader.close();
				
				File privKey = new File(args[1]);
				Scanner privReader = new Scanner(privKey);
				BigInteger d = new BigInteger(privReader.nextLine().substring(4));
				BigInteger n = new BigInteger(privReader.nextLine().substring(4));
				
				privReader.close();
				
				
				decypher(d, n, data);
				
				

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			System.err.println("Incorrect parameters.Enter name of file with message, and name of file with public key");
		}
	}

}

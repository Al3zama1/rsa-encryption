import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Random;

public class RSAGenKey {

	public static void calcAndKeys(BigInteger p, BigInteger q, BigInteger e) {	
		String[] publicKeyStrs = {"pub_key.txt", "en"};
		String[] privateKeyStrs = {"pri_key.txt", "dn"};
		
		BigInteger n = p.multiply(q);
		BigInteger phi = (p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));
		BigInteger d = calcInverse(e, phi).mod(phi);
		BigInteger[] en = {e, n};
		BigInteger[] dn = {d, n};
		
		// publish public key KU={e,n}
		CreateFile(publicKeyStrs[0], publicKeyStrs[1], en);
		
		// publish private key KR={d,n}
		CreateFile(privateKeyStrs[0], privateKeyStrs[1], dn);
	}
	
	public static BigInteger calcInverse(BigInteger num, BigInteger mod) {

		BigInteger q, r1, r2, r, t1, t2, t;
		r1 = mod;
		r2 = num;
		t1 = new BigInteger("0");
		t2 = new BigInteger("1");
		
		while (r2.compareTo(new BigInteger("0")) == 1) {
			q = r1.divide(r2);
			r = r1.mod(r2);
			t = t1.subtract(q.multiply(t2));
			r1 = r2;
			r2 = r;
			t1 = t2;
			t2 = t;
		}
		
		if (t1.compareTo(new BigInteger("0")) == -1) {
			t1 = t1.add(mod);
		}
		
		return t1;
	}
	
	public static void printEarow(int q, int r1, int r2, int r, int t1, int t2, int t) {
		System.out.println("q\t\tr1\t\tr2\t\tr\t\tt1\t\tt2\t\tt");
		System.out.println(q + "\t\t" + r1 + "\t\t" + r2 + "\t\t" + r + "\t\t" + t1 + "\t\t" + t2 + "\t\t" + t );
	}
	
	public static int findRanPri(int lenofPri) {
		String number = "";
		
		if (lenofPri == 1) {
			int[] possible = {3, 5, 7, 9};
			
			return possible[(int)(Math.random() * (4))];
		}
		
		for (int a=0; a<lenofPri; a++) {
			if (a==0) {
				number += (int)(Math.random() * 8)+1;
			} else {
				number += (int)(Math.random() * 9);
			}
		}
		
		int num = Integer.parseInt(number);
		while (!isPrime(num)) {
			num++;
		}
		
		return num;
	}
	
	public static int findFirstPri(int lenofPri) {
		String number = "1";
		
		if (lenofPri == 1) {
			return 3;
		}
		
		for (int b=0; b<lenofPri-1; b++) {
			number += "0";
		}
		
		int numbr = Integer.parseInt(number);
		while (!isPrime(numbr)) {
			numbr++;
		}
		
		return numbr;
	}
	
	public static boolean isPrime(int numbr) {
		for (int a=2; a<numbr-1; a++) {
			if (numbr%a == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void CreateFile(String fileN, String keyT, BigInteger[] key) {
		try {
			File f = new File(fileN);
			f.createNewFile();
			
			FileWriter w = new FileWriter(fileN);
			w.write(keyT.charAt(0) + " = " + key[0].intValue() + 
					"\n" + keyT.charAt(1) + " = " + key[1].intValue());
			w.close();
			System.out.println("File " + fileN + " created");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void print(String[] array) {
		for (int a=0; a<array.length;a++) {
			System.out.print(array[a] + " " );
		}
		
	}
	
	public static void main(String[] args) {		
		if (args.length == 1) {
			int bit = 0;
			try {
				bit = Integer.parseInt(args[0])/3;
				if (bit == 0) {
					System.out.println("Length of the key is a multiple of 3");
				}
			} catch (Exception e) {
				System.out.println("Length of the key is a multiple of 3");
			}
			
			BigInteger p = BigInteger.probablePrime(bit, new Random());
			BigInteger q = BigInteger.probablePrime(bit, new Random());
			BigInteger e = BigInteger.probablePrime(bit, new Random());
						
			calcAndKeys(p, q, e);
		} else if (args.length == 3) {
			BigInteger p = new BigInteger(args[0]);
			BigInteger q = new BigInteger(args[1]);
			BigInteger e = new BigInteger(args[2]);

			calcAndKeys(p, q, e);
		} else {
			System.err.println("Incorrect parameters.Enter 1 or 3 parameter");
		}
	}
}
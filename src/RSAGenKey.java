import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.Random;

public class RSAGenKey {

	public  void genearateNumbers(int bits) {
		
		Random random = new Random();
		
//		pick two large prime numbers p and q
		BigInteger p = BigInteger.probablePrime(bits, random);
		BigInteger q = BigInteger.probablePrime(bits, random);
		
		
//		n is the result of the multiplication of both prime numbers
		BigInteger n = p.multiply(q);
		
		BigInteger phi = (p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));
		
		BigInteger e = null;
		
		do {
			e = new BigInteger(bits, random);
		} while ((!phi.gcd(e).equals(new BigInteger("1"))) || e.equals( new BigInteger("0")));
		
		
//		calculate the inverse of e mod phi
		BigInteger d = e.modInverse(phi);
		
		
		generateKeys(p, q, n, phi, e, d);
		

		
	}
	
	public void generateWithValues(BigInteger p, BigInteger q, BigInteger e) {
		
		
//		n is the result of the multiplication of both prime numbers
		BigInteger n = p.multiply(q);
		
		BigInteger phi = (p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));
		
//		calculate the inverse of e mod phi
		BigInteger d = e.modInverse(phi);
		
		generateKeys(p, q, n, phi, e, d);
		
	}
	
	
	public void generateKeys(BigInteger p, BigInteger q, BigInteger n, BigInteger phi, BigInteger e, BigInteger d) {
		
		System.out.println("p " + p);
		System.out.println("q " + q);
		System.out.println("n " + n);
		System.out.println("phi " + phi);
		System.out.println("e " + e);
		System.out.println("d " + d);
		
		String[] publicKeyStrs = {"pub_key.txt", "en"};
		String[] privateKeyStrs = {"pri_key.txt", "dn"};
		
		BigInteger[] en = {e, n};
		BigInteger[] dn = {d, n};
		
		try {
			File f = new File(publicKeyStrs[0]);
			f.createNewFile();
			
			FileWriter w = new FileWriter(publicKeyStrs[0]);
			w.write(publicKeyStrs[1].charAt(0) + " = " + en[0].intValue() + "\n" + publicKeyStrs[1].charAt(1) + " = " + en[1].intValue());
			w.close();
			
			File f2 = new File(privateKeyStrs[0]);
			f2.createNewFile();
			
			FileWriter w2 = new FileWriter(privateKeyStrs[0]);
			w2.write(privateKeyStrs[1].charAt(0) + " = " + dn[0].intValue() + "\n" + privateKeyStrs[1].charAt(1) + " = " + dn[1].intValue());
			w2.close();
			
			System.out.println("files created");
			
		} catch (Exception error) {
			error.printStackTrace();
		}
		
		
	}
	
}

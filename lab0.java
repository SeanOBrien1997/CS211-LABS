import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This is steaming trash
 */
public class lab0 {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SecureRandom rand = new SecureRandom(); // better than regular random
		// make hex string
		String[] a = { "A", "B", "C", "D", "E", "F", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String s = "";
		for (int i = 0; i < 64; i++) {
			s += a[rand.nextInt(a.length)];
		}
		System.out.println("64 Digit HexString: " + s);

		// make eighty string
		String eighty = "80" + s;
		System.out.println("Eighty: " + eighty);

		// get doublehash of the 80 string
		String doublehash = sha256(sha256(eighty));
		System.out.println("256 of 256 of Eighty: " + doublehash);

		// concat first 8 bits from double hash onto front of 80 string
		String doubleeighty = eighty;
		for (int i = 0; i < 8; i++) {
			doubleeighty += doublehash.charAt(i);
		}
		System.out.println("Double Eighty + 8 Hash: " + doubleeighty);

		// compute 58
		String secret = base58(base10(doubleeighty));
		System.out.println("Your Bit/Vertcoin private key is: " + secret);
		
		// optional
		System.out.println("Output to textfile? (Y/n): ");
		Scanner sc = new Scanner(System.in);
		String reply = sc.nextLine().toUpperCase();
		if(reply.equals("Y")) {
			try {
				PrintStream out = new PrintStream(new FileOutputStream("secret.txt"));
				System.setOut(out);
				System.out.println(secret);
			} catch (FileNotFoundException e) {
				System.out.println("Error outputing key to text file");
				e.printStackTrace();
			}
		}
	}

	public static String sha256(String input) throws NoSuchAlgorithmException {
		byte[] in = hexStringToByteArray(input);
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		byte[] result = mDigest.digest(in);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
	
	public static String base58(BigInteger bi) {
		List<BigInteger> list = new ArrayList<BigInteger>();
		while(!bi.equals(BigInteger.ZERO)) {
			list.add(bi.mod(BigInteger.valueOf(58)));
			bi = bi.divide(BigInteger.valueOf(58));
		}
		String[] alphabet = {"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","J","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String result = "";
		for(int i = list.size()-1;i>=0;i--) {
			result += alphabet[(int) list.get(i).longValue()];
		}
		return result;
	}

	public static BigInteger base10(String s) {
		BigInteger bi = BigInteger.valueOf(0);
		int count = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			BigInteger total = BigInteger.valueOf(16);
			String currentNum = "" + s.charAt(i);
			try {
				long num = Long.parseLong(currentNum);
				total = (total.pow(count).multiply(BigInteger.valueOf(num)));
				bi = bi.add(total);
			} catch (Exception e) {
				switch (currentNum.toUpperCase()) {
				case "A":
					total = (total.pow(count).multiply(BigInteger.valueOf(10)));
					bi = bi.add(total);
					break;
				case "B":
					total = (total.pow(count).multiply(BigInteger.valueOf(11)));
					bi = bi.add(total);
					break;
				case "C":
					total = (total.pow(count).multiply(BigInteger.valueOf(12)));
					bi = bi.add(total);
					break;
				case "D":
					total = (total.pow(count).multiply(BigInteger.valueOf(13)));
					bi = bi.add(total);
					break;
				case "E":
					total = (total.pow(count).multiply(BigInteger.valueOf(14)));
					bi = bi.add(total);
					break;
				case "F":
					total = (total.pow(count).multiply(BigInteger.valueOf(15)));
					bi = bi.add(total);
					break;
				}			
			}
			count++;
		}
		return bi;
	}
}
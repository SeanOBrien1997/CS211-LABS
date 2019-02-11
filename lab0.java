import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class lab0 {

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
	}

/*	public static String base58(String s) {

	}
*/
	public static BigInteger base10(String s) {
		BigInteger bi = BigInteger.valueOf(0);
		int count = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			long total = 0;
			String currentNum = "" + s.charAt(i);
			System.out.println(currentNum);
			try {
				long num = Long.parseLong(currentNum);
				total = (long) (Math.pow(16, count) * num);
				bi.add(BigInteger.valueOf(total));
			} catch (Exception e) {
				switch (currentNum.toUpperCase()) {
				case "A":
					total = (long) (Math.pow(16, count) * 10);
					bi.add(BigInteger.valueOf(total));
					break;
				case "B":
					total = (long) (Math.pow(16, count) * 11);
					bi.add(BigInteger.valueOf(total));
					break;
				case "C":
					total = (long) (Math.pow(16, count) * 12);
					bi.add(BigInteger.valueOf(total));
					break;
				case "D":
					total = (long) (Math.pow(16, count) * 13);
					bi.add(BigInteger.valueOf(total));
					break;
				case "E":
					total = (long) (Math.pow(16, count) * 14);
					bi.add(BigInteger.valueOf(total));
					break;
				case "F":
					total = (long) (Math.pow(16, count) * 15);
					bi.add(BigInteger.valueOf(total));
					break;
				}			
			}
			count++;
		}
		return bi;
	}
}

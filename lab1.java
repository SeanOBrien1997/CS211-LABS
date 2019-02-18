import java.util.*;


public class lab1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, Counter> letterMap = new HashMap<String, Counter>();
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String printBin = "";
		for(int i = 0;i<s.length();i++){
			String current = "";
			current += s.charAt(i);
			String binary = Integer.toBinaryString((int)current.charAt(0));
			printBin += pad(binary) + " ";
			if(letterMap.containsKey(binary)){
				letterMap.get(binary).increase();
			} else {
				letterMap.put(binary, new Counter());
				 }
		}
		System.out.println(printBin.trim());
		letterMap.forEach((key,value) -> 
		System.out.println(value.get()==1?("'" + (char)Integer.parseInt(key, 2) + "'" + " appeared " + value.get() + " time") : "'" + (char)Integer.parseInt(key, 2) + "'" + " appeared " + value.get() + " times"));
	}
	
	public static String pad(String s){
		int padding = 8-s.length();
		String result = "";
		for(int i = 0; i < padding; i++){
			result += "0";
		}
		result += s;
		return result;
	}
}

class Counter{
	int value = 1;
	
	public void increase(){
		value++;
	}
	public	int get(){
		return value;
	}
}
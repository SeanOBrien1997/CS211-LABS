import java.util.*;

public class lab1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, Counter> letterMap = new HashMap<String, Counter>();
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		for(int i = 0;i<s.length();i++){
			String current = "";
			current += s.charAt(i);
			String binary = Integer.toBinaryString((int)current.charAt(0));
			if(letterMap.containsKey(binary)){
				letterMap.get(binary).increase();
			} else {
				letterMap.put(binary, new Counter());
			}
		}
		letterMap.forEach((key,value) -> System.out.println("'" + (char)Integer.parseInt(key, 2) + "'" + " appeared " + value.get() + " times"));
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
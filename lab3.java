import java.util.*;
public class lab3 {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		// Get input
		Scanner sc = new Scanner(System.in);
		int amount = Integer.parseInt(sc.nextLine());
		Comparator comp = new greatestComparator();
		PriorityQueue<String> q = new PriorityQueue<String>(amount,comp);
		for(int i = 0; i < amount; i++) {
			q.add(sc.nextLine());
		}
		sc.close();
		while(q.size()!=0) {
			System.out.println(q.remove());
		}
	}
}

class greatestComparator implements Comparator<String>{
	public int compare(String s1,String s2) {
		if(greatestChar(s1) < greatestChar(s2)) {
			return -1;
		} else if (greatestChar(s2) < greatestChar(s1)) {
			return 1;
		} else {
			return sortAlphabetically(s1,s2);
		}
	}
	
	public int greatestChar(String s) {
		int result = 0;
		for(char c: s.toCharArray()) {
			if ((int) c > result) {
				result = (int) c;
			}
		}
		return result;
	}
	
	public int sortAlphabetically(String s1,String s2) {
		if(s1.compareTo(s2)<s2.compareTo(s1)){
			return -1;
		} else if (s2.compareTo(s1)<s1.compareTo(s2)){
			return 1;
		} else {
			return 0;
		}
	}
}


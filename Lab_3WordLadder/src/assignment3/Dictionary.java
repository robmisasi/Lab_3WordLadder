package assignment3;
import java.util.*;
import java.util.regex.Pattern;

public class Dictionary {
	public ArrayList<String> words = new ArrayList<String>();
	private Scanner scan;
	public Dictionary(String file){
		scan = new Scanner(file);
		for (int i = 0; scan.hasNext(); i++){
			String pattern = "[A-Z]+";
			scan.next(pattern);
		}
	}
}

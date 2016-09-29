/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Fall 2016
 */


package assignment3;
import java.util.*;

import javax.naming.ldap.StartTlsRequest;

import java.io.*;

public class Main {
	// static variables and constants only here.
	public static String startWord;
	public static String endWord;
	public static Set<String> dict;
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		ArrayList<String> ladder = getWordLadderBFS("smart", "money");
		if( ladder == null){
			System.out.println("no word ladder can be found between " + startWord + " and " + endWord); 
		}
		printLadder(ladder);
		
		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		dict = makeDictionary();
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		String input = keyboard.next();
		String[] inputs = input.split(" ");
		ArrayList<String> words = new ArrayList<String>(2);
		words.add(0, inputs[0]);
		words.add(1, inputs[1]);
		return words;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    	start = start.toUpperCase();
    	end = end.toUpperCase();
    	Vector<Node> visited = new Vector<Node>();
    	Vector<String> foundWords = new Vector<String>();
		//Vector<Node> path = new Vector<Node>();
		// TODO some code
		//Set<String> dict = makeDictionary();
		// TODO more code
		Node startNode = new Node(start);
		visited.add(startNode);
		startNode.parent = null;
		Vector<Node> queue = startNode.nextWord(start, dict);
		Node finalNode = null;
		while(queue.size() > 0){
			startNode = queue.get(0);
			startNode.found = true;
			if(foundWords.contains(startNode.word)){
				queue.remove(0);
				continue;
			}
			if (startNode.word.contains(end)){
				finalNode = startNode;
				break;
			}
			visited.add(startNode);
			foundWords.add(startNode.word);
			Vector<Node> newQueue = startNode.nextWord(startNode.word, dict);
			queue.addAll(queue.size(), newQueue);
			queue.remove(0);
		}
		if(!startNode.word.contains(end)){
			return null;
		}
		ArrayList<String> ladder =  new ArrayList<String>();
		while(startNode.parent != null){
			ladder.add(startNode.word);
			startNode = startNode.parent;
		}
		
		ladder.add(start);
		
		return ladder; // replace this line later with real return
	}
    public static ArrayList<String> DFSInit(String start, String end){
		startWord = start;
		endWord = end;
    	return getWordLadderDFS(start, end);
    	
    }
    public static ArrayList<String> BFSInit(String start, String end){
    	startWord = start;
    	endWord = end;
    	return getWordLadderBFS(start, end);
    }
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		System.out.println("a " + ladder.size() + "-rung word ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size() - 1));
		for (int i = 0; i < ladder.size(); i++){
			System.out.println(ladder.get(i));
		}
		
	}
	// TODO
	// Other private static methods here
}

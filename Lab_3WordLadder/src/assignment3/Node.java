package assignment3;

import java.util.Set;
import java.util.Vector;

public class Node {
	boolean found;
	public Node parent;
	public String word;
	public int lettersFromTarget(String end){
		int shared = 0;
		for (int i = 0; i < 5; i++){
			if(word.charAt(i) == Main.startWord.charAt(i)){
				shared++;
			}
		}
		return shared;
	}
	public void wasFound(){
		found = true;
	}
	public Node(String word){
		found = false;
		this.word = word;
	}
	public Node(Node parent, String word){
		found = false;
		this.parent = parent;
		this.word = word;
	}
	public static int letterCompare(String a, String b){
		int shared = 0;
		for (int i = 0; i < 5; i++){
			if(a.charAt(i) == b.charAt(i)){
				shared++;
			}
		}
		return shared;
	}
	public Vector<Node> nextWord(String start, Set<String> dict){
		Vector<Node> a = new Vector<Node>();
		for (String word : dict){
			if (letterCompare(start, word) == 4){
				a.addElement(new Node(this, word));
			}
		}
		return a;
	}
}

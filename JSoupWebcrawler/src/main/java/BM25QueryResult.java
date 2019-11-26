/*

 MICHAEL GARNER
 COMP 479 
 Project 

 */

/*
 
 
 
 */
public class BM25QueryResult {
	
	//ATTRIBUTES
	private int docID = -1;
	private double score = 0.0;
	
	//CONSTRUCTOR
	public BM25QueryResult(int id, double score){ docID = id; this.score = score; }
	
	//SETTERS AND GETTERS
	public int getDocId(){ return docID; }
	public double getScore(){ return score; }
	
	//DISPLAY
	public void print(){ System.out.println( this.toString() ); }
	
	//TO STRING
	public String toString(){
		return "Document ID: " + docID + "\tScore: " + score;
	}//close function
	
	//SERVICE
	public void addScore(double in){ score += in; }
	
}//close class

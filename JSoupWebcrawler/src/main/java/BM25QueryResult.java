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
	private double tfidf = 0;
	private String url = "";
	private String tag = "NONE";

	//CONSTRUCTOR
	public BM25QueryResult(int id, double score, String url, double tfidf, String tag){ 
		docID = id; 
		this.score = score;
		this.tfidf = tfidf;
		this.url = url;
		this.tag = tag;
	}//close contructor

	//SETTERS AND GETTERS
	public int getDocId(){ return docID; }
	public double getScore(){ return score; }
	public String getUrl(){ return this.url; }

	//DISPLAY
	public void print(){ System.out.println( this.toString() ); }

	//TO STRING
	public String toString(){
		return "Document ID: " + docID + " <" +tag+">" + "\tScore(BM25/TfIdf): " + score + " / " + tfidf + "\t" + url;
	}//close function

	//SERVICE
	public void addScore(double in){ score += in; }

}//close class

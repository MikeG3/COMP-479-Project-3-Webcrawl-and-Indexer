/*

 MICHAEL GARNER
 26338739
 COMP 479 
 Project

 */

/*
	
	URL AND DOCUMENT ID DATA FOR A REFERENCE TABLE
	
 */

public class TableEntry {
	
	//ATTRIBUTES
	private int docID;
	private String url;
	
	//CONSTRUCTOR
	public TableEntry(int doc, String url){ this.docID = doc-1; this.url = url; }//close constructor
	
	//DISPLAY
	public void print(){ System.out.println(this.toString()); }//close print method
	public String toString(){ return docID + "\t\t\t" + url +"\n"; }//close to string method
	
	//SETTERS AND GETTERS
	public int getDocID() { return docID; }
	public void setDocID(int docID) { this.docID = docID; }
	public String getUrl() { return url; }
	public void setUrl(String url) { this.url = url; }
	
	
	
}//close class

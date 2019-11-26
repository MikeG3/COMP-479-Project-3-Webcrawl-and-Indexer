import java.util.ArrayList;

/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*

	TOKEN FOR BM25 DICTIONARY

	LIKE SPIMI DICTIONARY, BUT USES BM25POSTING INSTEAD OF SIMPLY THE DOC-ID

 */

public class BM25Token {

	//ATTRIBUTES
	static int idCounter = 0;
	private String term;
	private ArrayList<BM25Posting> posting = new ArrayList<BM25Posting>();
	int id = 0;

	//CONSTRUCTORS
	public BM25Token(){ idCounter++;	id = idCounter;}
	public BM25Token(String term, int docID, String tag, int docLength){ 
		this.term = term;
		this.addPosting(docID, tag, docLength);
		idCounter++;	
		id = idCounter;
	}//close constructor

	//SERVICE METHODS
	public boolean equals(BM25Token in){
		boolean out;
		if (this.term.equals(in.getTerm()))
			out = true;
		else
			return false;
		if (out && this.posting.size() == in.getPostings().size())
			for (int i = 0 ; i < posting.size() ; i++ )
				if (  !(this.posting.get(i).getDocID() == in.getPostings().get(i).getDocID())
						&& !(this.posting.get(i).getFrequency() == in.getPostings().get(i).getFrequency())
						&& !(this.posting.get(i).getRank() == in.getPostings().get(i).getRank())
						)
					return false;
		return out;
	}//close equals
	
	//ADD DOC ID
	public void addPosting(int docID, String tag, int docLength){
		//If there are already postings,see if it exists
		if (posting.size() > 0){
			for (int i = 0 ; i < posting.size() ; i++ )
				//if the posting is there, increment its frequency and return
				if ( posting.get(i).getDocID() == docID ){
					posting.get(i).incFreq();
					return;
				}//close if the docID is already in the postings list
		}//close if there are postings
		//Else, add it to the list
		BM25Posting post = new BM25Posting(docID, tag, docLength);
		posting.add(post);
	}//close function add Posting

	public void calculateBM25(double avdl, int numOfDocs){
		for (int i = 0 ; i < posting.size() ; i++ ){
			posting.get(i).calculateBM25(avdl, numOfDocs);
		}//close for i
	}//close function calculate bm25

	public void sortPostings(){
		if (this.posting.size()>1){
			//SORT POSTINGS BY HIGHEST BM25 RANK TO LOWEST (if more than 1 posting)
			ArrayList<BM25Posting> temp = new ArrayList<BM25Posting>();
			temp = posting;
			posting = new ArrayList<BM25Posting>();
			int maxIndex;
			while ( temp.size() > 0){
				maxIndex = 0;
				for (int i = 0 ; i < temp.size() ; i++){
					if (temp.get(i).getRank() > temp.get(maxIndex).getRank())
						maxIndex = i;
				}//close for j
				posting.add( temp.get(maxIndex) );
				temp.remove(maxIndex);
			}//close for i
		}//close if there is more than 1 posting needed to be sorted
	}//close function sort postings

	//SETTERS AND GETTERS
	public String getTerm(){ return this.term; }
	public ArrayList<BM25Posting> getPostings(){ return this.posting; }
	public int getID(){ return this.id; }
	public BM25Posting getPosting(int i){ return this.posting.get(i); }
	
	//DISPLAY
	public void print(){
		System.out.println("BM25 TOKEN: "+ this.toString() );
	}//close function print
	public String toString(){
		String out = term + "\t\t\t";
		for (int i = 0 ; i < posting.size() ; i++ ){
			out += posting.get(i).toString();
			if (i != posting.size()-1 )
				out += ", ";
		}//close for i
		return out;
	}//close function to string

}//CLOSE CLASS BM25 TOKEN

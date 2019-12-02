/*

 MICHAEL GARNER
 COMP 479 
 Project 2

 */

/*

 	EVERY TIME A TERM IS FOUND, BM25POSTING CAPTURES:
 		THE DOCUMENT THAT IT IS FOUND IN
 		FREQUENCY OF THE TERM IN THAT DOCUMENT
 		CALCULATES: <SEE BELOW>
 */

/*
 	PROJECT 
 	BM25 RANKED SEARCH RETRIEVAL

 	RANKING OF DOCUMENT = IDF * ....

 	N = NUMBER OF DCOUMENTS = 12578
 	TF = TERM FREQUENCY, NUMBER OF TIMES THE TERM APPEAR IN A DOCUMENT	 = FREQUENCY (EACH TERM/DOCUMENT PAIR IN POSTINGS LIST)
 	DF = DOCUMENT FREQUENCY, NUMBER OF DOCUMENTS THE TERM IS FOUND IN	 = LENGTH OF POSTTINGS LIST 
 	DL = DOCUMENT LENGTH
 	AVDL = AVERAGE DOCUMENT LENGTH

 	IDF = LOG( N / DF )	

 	0 < B < 1	TUNING PARAMETER
 	K1 > 0, ?TERM FREQUENCY?

 	BM25 =  IDF * TF * (K + 1) /(K * (1 - B + B*DL/AVDL) +  TF) 

 	BM25 =  LOG(N/DF) * TF * (K + 1) /(K * (1 - B + B*DL/AVDL) +  TF)

 */
public class BM25Posting {

	//VARIABLES
	private int docID = -1;			//document id
	private String url;				//website address
	private int frequency = 0;		//number of times the term is found in the document
	private int df = 0;				//number of documents containing the term
	private String tag = null;		//tag where the term was found
	private int docLength = -1;		//length of document where the posting is found
	static int numOfDocs = -1;		//total number of documents in the corpus
	private double idf = -1;		//IDF = LOG( N / DF )
	private double tfidf = -1;		//TFIDF = TF * LOG( N / DF )
	private double bm25Rank;		//BM25 =  IDF * TF * (K + 1) /(K * (1 - B + B*DL/AVDL) +  TF) 
	private final double K = 1.0, B = 0.5;	//BM25 parameters


	//CONSTRUCTOR
	public BM25Posting(int docID, String tag, int docLength, String url){
		this.docID = docID;
		this.url = url;
		if (this.tag == null)
			this.tag = tag;
		this.docLength = docLength;
		frequency = 1;
	}//close constructor

	//SERVICE METHODS
	public void incFreq(){
		frequency++;
	}//close function inc freq

	//CALCULATE RANK VALUES
	public void calculateBM25(double avdl, int numOfDocs){
		this.numOfDocs = numOfDocs;
		calculateIDF();
		//BM25 =  IDF * TF * (K + 1) /(K * (1 - B + B*DL/AVDL) +  TF) 
		bm25Rank = tfidf * (K+1.0) / (K* (1-B+(B*(double) docLength/(double) avdl)) + frequency);
		//ADD VALUE IF IN TITLE
		if (this.tag.equals("TITLE"))
			bm25Rank += 10;
	}//close function calculate idf
	public void calculateIDF(){
		//IDF = LOG( N / DF )
		idf = Math.log( ( (double) numOfDocs / (double) df) ) ;
		//TFIDF = TF * LOG( N / DF )		DF = number of documents containing the term
		tfidf = idf*frequency;
	}//close function calculate idf
	
	//STTERS AND GETTERS
	public int getFrequency(){ return this.frequency; }
	public int getDocID(){ return this.docID; }
	public double getRank(){ return this.bm25Rank; }
	public String getUrl(){ return this.url; }
	public void setDF(int in){ this.df = in; }
	public double getTfidf(){ return this.tfidf; }
	public String getTag(){ return this.tag; }

	//DISPLAY
	public String toString(){	
		String.format("%.5f", bm25Rank);
		String.format("%.5f", tfidf);
		return ( "DocID: " + docID + " <" + tag + "> " + "Freq=(" + frequency + ")" + " Rank: " + bm25Rank + "  TF-IDF=(" + tfidf + ")"); 
	}//close function to string

}//CLOSE CLASS BM25 Posting

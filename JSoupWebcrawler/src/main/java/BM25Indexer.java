/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*
 * 
    BM25 Index Constructor

    Make BM25 DICTIONARY = LIST of BM25 TOKENS 

    BM25TOKENS = STRNIG-TERM + LIST OF BM25POSTING

    LIST OF BM25POSTING = DOCID + FREQUENCY

 	SPIMI Single Pass In Memory Index -> 1 chunk
 */

import java.util.ArrayList;

public class BM25Indexer {

	//ATTRIBUTES

	//SERVICE METHODS
	//CONSTRUCT INDEX
	/*
	 LOOP FOR EACH ARTICLE
	 	LOOP FOR EACH TOKEN
	 		BM25Dictionary.addToken(Term, DocID)
	 			BM25Token.addPosting(Term, DocID)
	 */
	public void constructIndex(ArrayList<HTMLToken> htmlTokens, BM25Dictionary dictionary, double avdl){
		//VARIABLES
		String term;
		int docID;
		int corpusSize = 0;
		dictionary.setAVDL(avdl);
		dictionary.setNumOfDocs(htmlTokens.size());
		for (int i = 0 ; i < htmlTokens.size() ; i++){
			//PARSE AND GET TITLE
			htmlTokens.get(i).parseTitle();
			for (int j = 0 ; j < htmlTokens.get(i).getParsedTitle().size() ; j++ )
				dictionary.searchAndAdd( htmlTokens.get(i).getParsedTitle(j), htmlTokens.get(i).getDocID(), "TITLE", htmlTokens.get(i).textSize(), htmlTokens.get(i).getUrl() );
			//GET ALL TERMS IN THE HTML TEXT	
			for (int j = 0 ; j < htmlTokens.get(i).textSize() ; j++ ){
				corpusSize++;
				//GET THE TERM AND DOCID
				term = htmlTokens.get(i).getText(j);
				docID = htmlTokens.get(i).getDocID();
				//SEARCH AND ADD THE DATA
				dictionary.searchAndAdd( term, docID, "TEXT", htmlTokens.get(i).textSize(), htmlTokens.get(i).getUrl() );
			}//close for j each token in the article
		}//close loop for i each 500 articles
		//SORT DICTIONARY
		dictionary.sort();
		dictionary.sortPostings();
		//GET AVERAGE DOC LENGTH
		dictionary.setAVDL( corpusSize / htmlTokens.size() );
	}//close function construct Index	



	//SETTER AND GETTERS

	//DISPLAY

}//close class SPIMI


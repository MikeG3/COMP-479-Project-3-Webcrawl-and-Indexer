/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*

	Searches Dictionary for keywords (terms)

	BY FORMATTING QUERIES TO
	 A. PRODUCT OF SUMS
	 B. SUM OF PRODUCTS
	ALL QUERY PERMUTATIONS CAN BE MADE

 */
import java.util.ArrayList;

public class WordQuery {
	/*
	//BM25
	public ArrayList<BM25Token> bm25Search(String[] input, BM25Dictionary dic){
		ArrayList<BM25Token> out = new ArrayList<BM25Token>();
		ArrayList<BM25Token> sorted = new ArrayList<BM25Token>();
		out = orSearch(input, dic);
		//RANK ALL OF THE HITS BY SUMMING THEIR RANKS
		if (out.size() > 1){
			int[] score = new int[out.size()];
			//FOR EACH MATCH, MAKE A SCORE OF 0 FOR IT
			for (int i = 0 ; i < score.length ; i++ ){
				score[i] = 0;
				//FOR EACH QUERY WORD, IF FOUND, ADD THE BM25RANK TO THE SCORE
				for (int j = 0 ; j < input.length ; j++){
					if ( search(input[j]))
				}//close for j
			}//close for i
		}//close if there is more than 1 match
		//LOOP FOR SORTING BY HIGHEST TO LOWEST
		return sorted;
	}//close function bm25 search
	 */
	//SERVICE METHODS	
	//SEARCH AND RETURN NEWID (DOC ID)
	//SEARCH AND RETURN TOKENS
	//SEARCH AND PRINT
	//SEARCH AND GIVE ARTICLE

	//BM25 SEARCH
	//OR SEARCH AND SUM ALL OF THE BM25 VALUES FOR EACH MATCHING QUERY WORD
	public ArrayList<BM25QueryResult> bm25Search(String[] query, BM25Dictionary dictionary){
		ArrayList<BM25QueryResult> results = new ArrayList<BM25QueryResult>();
		ArrayList<BM25QueryResult> out = new ArrayList<BM25QueryResult>();
		ArrayList<BM25Token> tokens = orSearch(query, dictionary);
		boolean exists;
		BM25QueryResult qr;
		int index;
		//FOR EACH BM25 TOKEN RETURNED
		//FOR EACH POSTING
		//IF DOCID DOES NOT YET EXIST IN THE LIST CREATE NEW QUERY RESULT
		//ELSE DOCID EXISTS ADD TO THE SCORE TO THAT QUERY RESULT
		//FOR EACH BM25 TOKEN RETURNED
		//FOR EACH POSTING
		for (int i = 0 ; i < tokens.size() ; i++)
			for (int j = 0 ; j < tokens.get(i).getPostings().size(); j++ ){
				exists = false;
				//IF DOCID DOES NOT YET EXIST IN THE LIST CREATE NEW QUERY RESULT
				for (int k = 0 ; k < results.size() ; k++)
					if (tokens.get(i).getPosting(j).getDocID() == results.get(k).getDocId() ){
						exists = true;
						results.get(k).addScore( tokens.get(i).getPosting(j).getRank() );
						break;
					}//close if it exists already
				//ELSE DOCID EXISTS ADD TO THE SCORE TO THAT QUERY RESULT
				if (!exists){
					qr = new BM25QueryResult( tokens.get(i).getPosting(j).getDocID(), tokens.get(i).getPosting(j).getRank(), tokens.get(i).getPosting(j).getUrl(), tokens.get(i).getPosting(j).getTfidf(), tokens.get(i).getPosting(j).getTag() );
					results.add(qr);
				}//close else does not exist
			}//close for j for each posting

		//SORT QUERY RESULTS BY RANK
		while ( results.size() > 0){
			index = 0;
			for (int i = 1 ; i < results.size(); i++ )
				if ( results.get(i).getScore() > results.get(index).getScore() )
					index = i;
			out.add( results.get(index) );
			results.remove(index);
		}//close while there are results to sort

		return out;
	}//close function search

	//SINGLE TERM
	public boolean search(String query, BM25Dictionary dic){
		for (int i = 0 ; i < dic.getSize() ; i++ )
			if ( dic.getToken(i).getTerm().equals(query))
				return true;
		return false;
	}//close search function
	public boolean searchContains(String query, BM25Dictionary dic){
		for (int i = 0 ; i < dic.getSize() ; i++ )
			if ( dic.getToken(i).getTerm().contains(query))
				return true;
		return false;
	}//close search function

	//AND SEARCH
	public ArrayList<BM25Token> andSearch(String[] query, BM25Dictionary dictionary){
		boolean match;
		ArrayList<BM25Token> tokens = new ArrayList<BM25Token>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ ){
			match = true;
			for ( int j = 0 ; j <  query.length ; j++ )
				if ( !dictionary.getToken(i).getTerm().equals(query[j]))
					match = false;
			if (match)
				tokens.add(dictionary.getToken(i));
		}//close for j
		return tokens;
	}//close function search
	public ArrayList<BM25Posting> andSearchPostings(String[] query, BM25Dictionary dictionary){
		boolean match;
		ArrayList<BM25Posting> ints = new ArrayList<BM25Posting>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ ){
			match = true;
			for ( int j = 0 ; j <  query.length ; j++ )
				if ( !dictionary.getToken(i).getTerm().equals(query[j]))
					match = false;
			if (match)
				for ( int j = 0 ; j <  dictionary.getToken(i).getPostings().size() ; j++ )
					ints.add( dictionary.getToken(i).getPostings().get(j) );
		}//close for j
		return ints;
	}//close function search
	public ArrayList<Integer> andSearchDocID(String[] query, BM25Dictionary dictionary){
		boolean match;
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ ){
			match = true;
			for ( int j = 0 ; j <  query.length ; j++ )
				if ( !dictionary.getToken(i).getTerm().equals(query[j]))
					match = false;
			if (match)
				for ( int j = 0 ; j <  dictionary.getToken(i).getPostings().size() ; j++ )
					ints.add( dictionary.getToken(i).getPostings().get(j).getDocID() );
		}//close for j
		return ints;
	}//close function search

	//OR SEARCH
	//SEARCH FOR EXACT MATCH FOR ANY QUERY WORD IN THE DICTIONARY
	public ArrayList<BM25Token> orSearch(String[] query, BM25Dictionary dictionary){
		//SEARCH FOR EXACT MATCH FOR EACH QUERY WORD IN THE DICTIONARY
		ArrayList<BM25Token> out = new ArrayList<BM25Token>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ )
			for (int j = 0 ; j < query.length ; j++ )
				if (dictionary.getToken(i).getTerm().equals( query[j] ) ){
					out.add(dictionary.getToken(i));
					break;
				}//close if matched query word
		//REMOVE DUPLICATE TOKENS
		for (int i = 0 ; i < out.size() ; i++ )
			for (int j = (i+1) ; j < out.size() ; j++)
				if ( out.get(i).getID() == out.get(j).getID() )
					out.remove(j);
		return out;
	}//close function or search
	//SEARCH FOR MATCH that CONTAINS ANY QUERY WORD IN THE DICTIONARY
	public ArrayList<BM25Token> orSearchContains(String[] query, BM25Dictionary dictionary){
		boolean duplicate;
		//SEARCH FOR EXACT MATCH FOR EACH QUERY WORD IN THE DICTIONARY
		ArrayList<BM25Token> out = new ArrayList<BM25Token>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ )
			for (int j = 0 ; j < query.length ; j++ )
				if (dictionary.getToken(i).getTerm().contains(query[j])){
					duplicate = false;
					//ENSURE NOT A DUPLICATE
					for (int k = 0 ; k < out.size() ; k++)
						if (dictionary.getToken(i).equals( dictionary.getToken(k) ) )
							duplicate = true;
					if (!duplicate)
						out.add(dictionary.getToken(i));
				}//close if matched query word
		return out;
	}//close function search
	public ArrayList<BM25Posting> orSearchDocIDPostings(String[] query, BM25Dictionary dictionary){
		boolean match;
		ArrayList<BM25Posting> ints = new ArrayList<BM25Posting>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ ){
			match = false;
			for ( int j = 0 ; j <  query.length ; j++ )
				if ( dictionary.getToken(i).getTerm().equals(query[j]))
					match = true;
			if (match)
				for ( int j = 0 ; j <  dictionary.getToken(i).getPostings().size() ; j++ )
					ints.add( dictionary.getToken(i).getPostings().get(j) );
		}//close for j
		return ints;
	}//close function search
	public ArrayList<Integer> orSearchDocID(String[] query, BM25Dictionary dictionary){
		boolean match;
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for (int i = 0 ; i < dictionary.getSize() ; i++ ){
			match = false;
			for ( int j = 0 ; j <  query.length ; j++ )
				if ( dictionary.getToken(i).getTerm().equals(query[j]))
					match = true;
			if (match)
				//add all docId's
				for ( int j = 0 ; j < dictionary.getToken(i).getPostings().size() ; j++ )
					ints.add( dictionary.getToken(i).getPostings().get(j).getDocID() );
		}//close for j
		//remove duplicates
		for (int i = 0 ; i < ints.size() ; i++)
			for (int j = (i+1) ; j < ints.size(); j++)
				if (ints.get(i) == ints.get(j))
					ints.remove(j);
		return ints;
	}//close function search

	//PRINT DOCUMENT ID
	public void printAndSearchID(String[] query, BM25Dictionary dictionary){
		ArrayList<Integer> ids = andSearchDocID(query, dictionary);
		System.out.print("\nDISPLAYING RESULTS FROM QUERY FOR: \n");
		for (int i = 0 ; i < query.length ; i++){
			System.out.print("\"" + query[i] + "\" ");
			if ( i < query.length-1 )
				System.out.print("AND ");
		}//close for i
		if ( ids.size() < 1 )
			System.out.println( "\nKEYWORDS NOT FOUND\n" );
		else {
			System.out.println( "\n" + ids.size() +  " MATCHES AT THE FOLLOWING DOCUMENTS:\n");
			for (int i = 0 ; i < ids.size(); i++ )
				System.out.print("Document ID " + ids.get(i) + "\n");
		}//close else
	}//close function search and print
	public void printOrSearchID(String[] query, BM25Dictionary dictionary){
		ArrayList<Integer> ids = orSearchDocID(query, dictionary);
		System.out.print("\nDISPLAYING RESULTS FROM QUERY FOR RANKED BY BM25 VALUE: \n");
		for (int i = 0 ; i < query.length ; i++){
			System.out.print("\"" + query[i] + "\" ");
			if ( i < query.length-1 )
				System.out.print(", ");
		}//close for i
		if ( ids.size() < 1 )
			System.out.println( "\nKEYWORDS NOT FOUND\n" );
		else {
			System.out.println( "\n" + ids.size() +  " MATCHES AT THE FOLLOWING DOCUMENTS:\n");
			for (int i = 0 ; i < ids.size(); i++ )
				System.out.print("Document ID " + ids.get(i) + "\n");
		}//close else
	}//close function search and print
	public void printBM25Search(String[] query, BM25Dictionary dictionary){
		ArrayList<BM25QueryResult> results = bm25Search(query, dictionary);
		System.out.print("\nDISPLAYING RESULTS FROM QUERY: \n");
		for (int i = 0 ; i < query.length ; i++){
			System.out.print("\"" + query[i] + "\" ");
			if ( i < query.length-1 )
				System.out.print(", ");
		}//close for i
		if ( results.size() < 1 )
			System.out.println( "\nKEYWORDS NOT FOUND\n" );
		else {
			System.out.println( "\n" + results.size() +  " MATCHES AT THE FOLLOWING DOCUMENTS:\n");
			for (int i = 0 ; i < results.size(); i++ )
				System.out.print( results.get(i).toString() + "\n" );
		}//close else
	}//close function print bm25Search
	public String bm25SearchToString(String[] query, BM25Dictionary dictionary){
		ArrayList<BM25QueryResult> results = bm25Search(query, dictionary);
		String out = "\nDISPLAYING RESULTS FROM QUERY: \n";
		for (int i = 0 ; i < query.length ; i++){
			out += ("\"" + query[i] + "\" ");
			if ( i < query.length-1 )
				out += (", ");
		}//close for i
		if ( results.size() < 1 )
			out += ( "\nKEYWORDS NOT FOUND\n" );
		else {
			out += ( "\n" + results.size() +  " MATCHES AT THE FOLLOWING DOCUMENTS:\n");
			out += ( "ORDERED BY BM25 VALUE:\n");
			for (int i = 0 ; i < results.size(); i++ )
				out += ( results.get(i).toString() + "\n" );
		}//close else
		return out;
	}//close function print bm25Search
	public String bm25SearchToString(String[] query, BM25Dictionary dictionary, URLTable table){
		ArrayList<BM25QueryResult> results = bm25Search(query, dictionary);
		String out = "\nDISPLAYING RESULTS FROM QUERY: \n" + "URL TABLE\n" + table.toString();
		for (int i = 0 ; i < query.length ; i++){
			out += ("\"" + query[i] + "\" ");
			if ( i < query.length-1 )
				out += (", ");
		}//close for i
		if ( results.size() < 1 )
			out += ( "\nKEYWORDS NOT FOUND\n" );
		else {
			out += ( "\n" + results.size() +  " MATCHES AT THE FOLLOWING DOCUMENTS:\n");
			for (int i = 0 ; i < results.size(); i++ )
				out += ( i + "\t" + results.get(i).toString() + "\n" );
		}//close else
		return out;
	}//close function print bm25Search


	public void printBM25Search(String[] query, BM25Dictionary dictionary, URLTable table){
		ArrayList<BM25QueryResult> results = bm25Search(query, dictionary);
		System.out.print("\nDISPLAYING RESULTS FROM QUERY: \n");
		System.out.print("URL TABLE\n" + table.toString() );
		for (int i = 0 ; i < query.length ; i++){
			System.out.print("\"" + query[i] + "\" ");
			if ( i < query.length-1 )
				System.out.print(", ");
		}//close for i
		if ( results.size() < 1 )
			System.out.println( "\nKEYWORDS NOT FOUND\n" );
		else {
			System.out.println( "\n" + results.size() +  " MATCHES AT THE FOLLOWING DOCUMENTS:\n");
			for (int i = 0 ; i < results.size(); i++ )
				System.out.print( results.get(i).toString() + "\n" );
		}//close else
	}//close function print bm25Search
	
}//close class word query

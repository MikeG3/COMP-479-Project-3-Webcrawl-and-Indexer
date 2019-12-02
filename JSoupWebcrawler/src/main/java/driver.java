/*

 MICHAEL GARNER
 26338739
 CONCORDIA UNIVERSITY
 COMP 479 

 */


/*

	WEBCRAWLER AND INDEXER WITH BM25 RANKING

 */

/*

1. starting from page https://www.concordia.ca/research.html, crawl for links (you may use crawling tools
such as Websphinx but you may also find other tools, such as NYUcrawl). To extract the text from web pages,
consider Boilerpipe. Describe and attribute any tools used. Make sure you obey the standard for robot exclusion.
Your crawler MUST accept as part of its input an upper bound on the total number of files to be downloaded.
In developing, testing, and debugging, this number should be kept as SMALL as possible. Develop your own
closed test set of HTML files for testing and debugging. The final index (ConcordiaAI) should cover as many
documents as possible. (5 pts, Attrib 5)

 */

import java.util.ArrayList;


public class driver {

	public static void main(String[] args){

		//VARIABLES
		final String AIURL = "https://aitopics.org/search";
		int tokenCount = 0;
		int aitokenCount = 0;
		final int MAX = 5000;			//SET MAXIMUM PAGES TO CRAWL FOR CONCORDIA INDEX
		final int AIMAX = 5000;			//SET MAXIMUM PAGES TO CRAWL FOR AI INDEX
		Disk_Writer diskWriter = new Disk_Writer();
		BM25Dictionary dictionary = new BM25Dictionary();
		BM25Dictionary aidictionary = new BM25Dictionary();
		BM25Indexer bm25Indexer = new BM25Indexer();
		float avdl = 0;
		float aiavdl = 0;
		WordQuery query = new WordQuery();
		QueryWords words = new QueryWords();
		Webcrawler spider = new Webcrawler();
		Webcrawler aispider = new Webcrawler();
		ArrayList<HTMLToken> htmlTokens = new ArrayList<HTMLToken>();
		ArrayList<HTMLToken> aihtmlTokens = new ArrayList<HTMLToken>();
		HTMLPreprocessor htmlProcessor = new HTMLPreprocessor();
		URLTable urlTable = new URLTable();
		URLTable aiurlTable = new URLTable();
		boolean isAI = false;
		
		//WELCOME MESSAGE
		System.out.println("COMP 479 PROJECT 3 WEBCRAWLING + RANKED BM25 INDEX\nMICHAEL GARNER 26338739\n");			
		
		//WEBCRAWLING
		System.out.println("\nSPIDER IS CRAWLING THE WEB");
		spider.crawl(urlTable, MAX, isAI);
		htmlTokens = spider.getTokens();
		System.out.println("\nWEB CRAWLER SCANNED " + htmlTokens.size() + " WEBPAGES");	
		
		//COMPRESS TOKENS
		System.out.println("\nCOMPRESSING HTML TEXTS");
		for (int i = 0 ; i < htmlTokens.size() ; i++ ){
			htmlTokens.get(i).setParsedText( htmlProcessor.parse(htmlTokens.get(i).getText() ));
			htmlTokens.get(i).removeWhiteSpace();
			tokenCount += htmlTokens.get(i).getParsedText().size();
		}//close for i 
		
		//DISPLAY HTML TOKENS
//		System.out.println("\nDISPLAYING HTML TOKENS");
//		for (int i = 0 ; i < htmlTokens.size() ; i++ ){
//			htmlTokens.get(i).print();
//		}//close for i 		
		
		//GET AVERAGE DOCUMENT LENGTH
		if (htmlTokens.size() > 0)
			avdl = tokenCount / htmlTokens.size();
		else
			System.out.println("\nAVDL NOT CALCULATED, NO HTML TOKENS");
		System.out.println("\nWEB CRAWLER SCANNED " + htmlTokens.size() +
				"\nTHE TOTAL NUMBER OF PARSED & COMPRESSED TERMS IS " + tokenCount +
				"\nTHE AVERAGE DOCUMENT LENGTH IS " + avdl);
		
		//INDEX TOKENS
		System.out.println("\nTOKENS ARE NOW BEING INDEXED TO CREATE A DICTIONARY");
		bm25Indexer.constructIndex(htmlTokens, dictionary, avdl);
		
		//RANK ALL TERMS
		System.out.println("\nRANKING ALL TERMS IN THE DICTIONARY WITH BM25 VALUES");
		dictionary.calculateBM25();
		
		//QUERY SEARCH WORDS
		for (int i = 0 ; i < words.getQueries().size() ; i++ )
			query.printBM25Search(words.getAWords(i), dictionary);
		
		//WRITE TO DISK
		diskWriter.write(dictionary);
		diskWriter.write(urlTable);
		diskWriter.write(query, words, dictionary);
		
		/*******************************************************************************
		 * 		AI INDEX
		 */
		isAI = true;
		//WEBCRAWLING
		System.out.println("\nSPIDER IS CRAWLING THE WEB");
		aispider.crawl(aiurlTable, AIURL, AIMAX, isAI);
		aihtmlTokens = aispider.getTokens();
		System.out.println("\nWEB CRAWLER SCANNED " + aihtmlTokens.size() + " WEBPAGES");	
		
		//COMPRESS TOKENS
		System.out.println("\nCOMPRESSING HTML TEXTS");
		for (int i = 0 ; i < aihtmlTokens.size() ; i++ ){
			aihtmlTokens.get(i).setParsedText( htmlProcessor.parse(aihtmlTokens.get(i).getText() ));
			aihtmlTokens.get(i).removeWhiteSpace();
			aitokenCount += aihtmlTokens.get(i).getParsedText().size();
		}//close for i 
		
		//DISPLAY HTML TOKENS
//		System.out.println("\nDISPLAYING HTML TOKENS");
//		for (int i = 0 ; i < aihtmlTokens.size() ; i++ ){
//			aihtmlTokens.get(i).print();
//		}//close for i 		
		
		//GET AVERAGE DOCUMENT LENGTH
		if (aihtmlTokens.size() > 0)
			aiavdl = aitokenCount / aihtmlTokens.size();
		else
			System.out.println("\nAI AVDL NOT CALCULATED, NO HTML TOKENS");
		System.out.println("\nAI WEB CRAWLER SCANNED " + htmlTokens.size() +
				"\nTHE TOTAL NUMBER OF PARSED & COMPRESSED TERMS IS " + tokenCount +
				"\nTHE AVERAGE DOCUMENT LENGTH IS " + avdl);
		
		//INDEX TOKENS
		System.out.println("\nAI TOKENS ARE NOW BEING INDEXED TO CREATE A DICTIONARY");
		bm25Indexer.constructIndex(aihtmlTokens, aidictionary, aiavdl);
		
		//RANK ALL TERMS
		System.out.println("\nRANKING ALL TERMS IN THE AI DICTIONARY WITH BM25 VALUES");
		aidictionary.calculateBM25();
		
		//QUERY SEARCH WORDS
		for (int i = 0 ; i < words.getQueries().size() ; i++ )
			query.printBM25Search(words.getAWords(i), aidictionary);
		
		//WRITE TO DISK
		diskWriter.write(aidictionary, isAI);
		diskWriter.write(aiurlTable, isAI);
		diskWriter.write(query, words, aidictionary, isAI);
		
		//CLOSING MESSAGE
		System.out.println("\n\nALGORITHM IS COMPLETE! :)");

	}//close main loop
}//close driver class
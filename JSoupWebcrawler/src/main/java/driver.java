/*

 MICHAEL GARNER
 26338739
 CONCORDIA UNIVERSITY
 COMP 479 

 */


/*

	WEBCRAWLER AND INDEXER WITH BM25 RANKING

 */

import java.util.ArrayList;


public class driver {

	public static void main(String[] args){

		//VARIABLES
		int tokenCount = 0;
		Disk_Writer diskWriter = new Disk_Writer();
		BM25Dictionary dictionary = new BM25Dictionary();
		BM25Indexer bm25Indexer = new BM25Indexer();
		float avdl = 0;
		WordQuery query = new WordQuery();
		QueryWords words = new QueryWords();
		Webcrawler spider = new Webcrawler();
		ArrayList<HTMLToken> htmlTokens = new ArrayList<HTMLToken>();
		HTMLPreprocessor htmlProcessor = new HTMLPreprocessor();
		
		//WELCOME MESSAGE
		System.out.println("COMP 479 PROJECT 3 WEBCRAWLING + RANKED BM25 INDEX\nMICHAEL GARNER 26338739\n");			
		
		//WEBCRAWLING
		System.out.println("\nSPIDER IS CRAWLING THE WEB");
		spider.crawl();
		htmlTokens = spider.getTokens();
		System.out.println("\nWEB CRAWLER SCANNED " + htmlTokens.size() );
		
		
		//COMPRESS TOKENS
		System.out.println("\nCOMPRESSING HTML TEXTS");
		for (int i = 0 ; i < htmlTokens.size() ; i++ ){
			htmlTokens.get(i).setParsedText( htmlProcessor.parse(htmlTokens.get(i).getText() ));
			htmlTokens.get(i).removeWhiteSpace();
			tokenCount += htmlTokens.get(i).getParsedText().size();
		}//close for i 
		
		//DISPLAY HTML TOKENS
		System.out.println("\nDISPLAYING HTML TOKENS");
		for (int i = 0 ; i < htmlTokens.size() ; i++ ){
			htmlTokens.get(i).print();
		}//close for i 		
		
		//GET AVERAGE DOCUMENT LENGTH
		if (htmlTokens.size() > 0)
			avdl = tokenCount / htmlTokens.size();
		else
			System.out.println("\nAVDL NOT CALCULATED, NO HTML TOKENS");
		System.out.println("\nWEB CRAWLER SCANNED " + htmlTokens.size() + "\nTHE AVERAGE DOCUMENT LENGTH IS " + avdl);
		
		//INDEX TOKENS
		
		//RANK TOKENS
		
		//CLOSING MESSAGE
		System.out.println("\n\nALGORITHM IS COMPLETE! :)");

	}//close main loop
}//close driver class
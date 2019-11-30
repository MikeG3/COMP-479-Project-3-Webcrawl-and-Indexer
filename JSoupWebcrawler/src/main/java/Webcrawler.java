/*

 MICHAEL GARNER
 26338739
 CONCORDIA UNIVERSITY
 COMP 479 

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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Webcrawler {

	//ATTRIBUTES
	private int  max = 1300;		//1300 to cover all Concordia domain
	private String startLink = "https://www.concordia.ca/research.html";
	private HashSet<String> htmlLinks;
	private int counter = 0;
	private ArrayList<HTMLToken> htmlTokens = new ArrayList<HTMLToken>();
	//ITERATION 2
	private ArrayList<String> errorLinks = new ArrayList<String>();
	private boolean done = false;
	private String userAgent;
	private String newUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36";
	private final int TIMEOUT = 24000;
	
	//CONSTRUCTOR
	public Webcrawler() {
		htmlLinks = new HashSet<String>();
	}//CLOSE constructor

	//SETTERS AND GETTERS
	public ArrayList<HTMLToken> getTokens(){ return this.htmlTokens; }

	//SERVICE METHODS
	public void crawl(URLTable urlTable, String url, int max) {
		this.max = max;
		this.startLink = url;
		//getUserAgent();
		System.out.println("\nWEBCRAWLER MAXIMUM PAGES SET TO "+max+"\n");
		crawl(startLink, urlTable); 
	}//close crawl method
	
	public void crawl(URLTable urlTable) {
		//getUserAgent();
		System.out.println("\nWEBCRAWLER MAXIMUM PAGES SET TO "+max+"\n");
		crawl(startLink, urlTable); 
	}//close crawl method

	public void crawl(String url, URLTable urlTable) {
		//METHOD ATTRIBUTES
		HTMLToken token;
		Elements linksOnPage;
		String title, text;

		//CRAWL UNVISITED WEBSITES, WHILE LESS THAN THE MAXIMUM SITES HAVE BEEN VISITED
		if (!htmlLinks.contains(url) && counter < max) {
			try {
				counter++;
				//Add the URL and print it if it is a new entry
				if (htmlLinks.add(url)) 
					System.out.println(counter + "\t" + url);

				//ADD URL TO THE URL TABLE
				urlTable.addEntry(counter, url);

				//GET THE HTML CODE AND PARSE IT
				Document document = Jsoup.connect(url).userAgent(newUserAgent).referrer("http://www.google.com").timeout(TIMEOUT).followRedirects(true).get();
				title = document.title();
				if (document.hasText())
					text = document.text();
				else
					text = null;

				//PREPROCESS
				//Remove URL and TITLE from the text
				text = text.replaceFirst(title,  " ");
				text = text.replaceFirst("(http://).*(.html)",  "");
				text = text.replaceFirst("(https://).*(.html)",  "");

				//CREATE TOKEN AND ADD TO LIST
				token = new HTMLToken(url, text, title);
				htmlTokens.add(token);
				//token.print();

				//PARSE HTML FOR TITLE, LINKS AND CONTENT
				linksOnPage = document.select("a[href]");
				//ITERATE RECURSIVELY FOR ALL OTHER LINKS ENDING WITH .HTML
				for (Element page : linksOnPage) 
					//ENSURE THAT LINK IS TO AN HTML PAGE
					if (page.attr("abs:href").endsWith("html") || page.attr("abs:href").endsWith("HTML") || page.attr("abs:href").endsWith("Html")){
						crawl(page.attr("abs:href"), urlTable);
					}//close if its an html document
			} catch (IOException e) {
				System.err.println("\nERROR IN WEBCRAWLER\nFor '" + url + "': " + e.getMessage());
				counter--;
				errorLinks.add(url);
			}//close catch
		}//close outer if htmlLinks.contains(URL)

		if (counter == max && !done && (errorLinks.size() > 0))
			endCrawl();

	}//close method crawl


	public void endCrawl(){
		System.out.println("\nENDING WEBCRAWL\nTHE FOLLOWING SITES WERE NOT ABLE TO BE PARSED");
		for (int i = 0 ; i < errorLinks.size() ; i++)
			System.out.println((i+1) + " " + errorLinks.get(i) + "\n");
		done = true;
	}//close method end crawl
	
	public void getUserAgent(){
		try{
			userAgent = Jsoup.connect("https://www.whoishostingthis.com/tools/user-agent/").get().text();
			System.out.println("\nUSER AGENT:" + userAgent);
		} catch (IOException e) {
			System.err.println("\nERROR IN WEBCRAWLER GETTING AGENT STRING" + e.getMessage());
		}//close catch
	}//close method get user agent

}//close class web crawler

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Webcrawler {

	//ATTRIBUTES
	private final int MAXIMUM = 100;		//1500 to cover all Concordia domain
	private String startLink = "https://www.concordia.ca/research.html";
	private HashSet<String> htmlLinks;
	private int counter = 0;
	private HTMLPreprocessor htmlProcess = new HTMLPreprocessor();
	private ArrayList<HTMLToken> htmlTokens = new ArrayList<HTMLToken>();

	//CONSTRUCTOR
	public Webcrawler() {
		htmlLinks = new HashSet<String>();
	}//CLOSE constructor

	//SETTERS AND GETTERS
	public ArrayList<HTMLToken> getTokens(){ return this.htmlTokens; }

	//DISPLAY

	//SERVICE METHODS
	public void crawl() { 
		System.out.println("\nWEBCRAWLER MAXIMUM PAGES SET TO "+MAXIMUM+"\n");
		crawl(startLink); 
	}//close crawl method

	public void crawl(String url) {
		//METHOD ATTRIBUTES
		HTMLToken token;
		Elements linksOnPage;
		String title, text;
		
		//4. Check if you have already crawled the URLs 
		//(we are intentionally not checking for duplicate content in this example)
		if (!htmlLinks.contains(url) && counter < MAXIMUM) {
			try {
				counter++;
				//Add the URL and print it if it is a new entry
				if (htmlLinks.add(url)) 
					System.out.println(counter + "\t" + url);

				//GET THE HTML CODE AND PARSE IT
				Document document = Jsoup.connect(url).get();
				title = document.title();
				if (document.hasText())
					text = document.text();
				else
					text = null;

				//PREPROCESS
				//Remove URL and TITLE from the text
				text = text.replaceFirst(title,  "");
				text = text.replaceFirst("(http://).*(.html)",  "");
				text = text.replaceFirst("(https://).*(.html)",  "");

				//CREATE TOKEN AND ADD TO LIST
				token = new HTMLToken(url, text, title);
				htmlTokens.add(token);
				//token.print();

				//3. Parse the HTML to extract links to other URLs
				linksOnPage = document.select("a[href]");
				//5. For each extracted URL... go back to Step 4.
				for (Element page : linksOnPage) 
					//ENSURE THAT LINK IS TO AN HTML PAGE
					if (page.attr("abs:href").endsWith("html") || page.attr("abs:href").endsWith("HTML") || page.attr("abs:href").endsWith("Html")){
						crawl(page.attr("abs:href"));
					}//close if its an html document
			} catch (IOException e) {
				System.err.println("\nERROR IN WEBCRAWLER\nFor '" + url + "': " + e.getMessage());
			}//close catch
		}//close outer if htmlLinks.contains(URL)

	}//close method crawl

}//close class web crawler

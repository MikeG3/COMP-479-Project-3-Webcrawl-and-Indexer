import java.util.ArrayList;

public class HTMLToken {

	//ATTRIBUTES
	static int counter = 0;
	private int docID ;
	private String url;
	private String text;
	private String title;
	private ArrayList<String> parsedText = new ArrayList<String>();
	
	//CONSTRUCTOR
	public HTMLToken(String url, String text, String title){
		this.url = url;
		this.text = text;
		this.title = title;
		this.docID = counter++;
	}//close constructor

	//SETTERS AND GETTERS
	public int getDocID(){ return this.docID; }
	public String getUrl() { return url; }
	public void setUrl(String url) { this.url = url; }
	public String getText() { return text; }
	public void setText(String text) { this.text = text; }
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }	
	public void setParsedText(ArrayList<String> in) { this.parsedText = in; }	
	public ArrayList<String> getParsedText() { return this.parsedText; }
	public int textSize(){ return this.parsedText.size(); }
	public String getText(int i) {return this.parsedText.get(i); }
	
	//DISPLAY AND PRINT
	public String toString(){
		String out =  docID + "\t" + title+"\t"+url+"\n"+text+"\n";
		for (int i = 0 ; i < parsedText.size() ; i++ )
			out += "\t" + i + ":\t" + parsedText.get(i) + "\n";
		return out;
	}//close to string method
	
	public void print(){ 
		System.out.println(this.toString());
	}//close method print
	
	//SERVICE METHODS
	public void removeWhiteSpace(){
		for (int i = 0 ; i < parsedText.size() ; i++ )
			if (parsedText.get(i)==""||parsedText.get(i)==" "||parsedText.get(i)==null||parsedText.get(i)=="\t"||parsedText.get(i)=="\r"||parsedText.get(i)=="\f"||parsedText.get(i)=="\n")
				parsedText.remove(parsedText.get(i));
	}//close function remove white space
	
	public void parseTitle(){
		String[] parsedTitle;
		ArrayList<String> out = new ArrayList<String>();
		//SPLIT TEXT
		parsedTitle = this.title.split(" ");
		//COMPRESS TEXT
		CompressorLossy compress = new CompressorLossy();
		for (int i = 0 ; i < parsedTitle.length ; i++ ){
			out.add( compress.lossyCompression(parsedTitle[i]) );
		}//close for i
	}//close function parse title
	
}//close class html token

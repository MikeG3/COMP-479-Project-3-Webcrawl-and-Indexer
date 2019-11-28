import java.util.ArrayList;

public class URLTable {

	//ATTRIBUTES
	private ArrayList<TableEntry> table = new ArrayList<TableEntry>();
	
	//CONSTRUCTOR
	//DISPLAY
	public void print(){ System.out.println(this.toString()); }//close print method
	public String toString(){
		String out = "";
		for (int i = 0 ; i < table.size() ; i++)
			out += table.get(i).toString();
		return out;
	}//close to string method
	
	//SETTERS AND GETTERS
	public int getSize(){ return this.table.size(); }
	public TableEntry getEntry(int i){ return this.table.get(i); }
	
	//SERVICE METHODS
	//ADD AN ENTRY
	public void addEntry(int docId, String url){
		//CREATE AN ENTRY, THEN ADD IT TO THE LIST
		TableEntry entry = new TableEntry(docId, url);
		table.add(entry);
	}//close add entry method
	
}//close class docid url table

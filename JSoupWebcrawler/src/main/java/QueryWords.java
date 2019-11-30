import java.util.ArrayList;

/*

 MICHAEL GARNER
 26338739
 CONCORDIA UNIVERSITY
 COMP 479 

 */
/*
 
 	LISTS OF WORDS TO DEMO THE BM25 INDEX AND RANK SYSTEM
 
 */

/*
 
 //		//QUERY  AND RESULTS
//		String[] queries = {"HELLO", "WORLD", "COCOA", "CANADA", "ARDVARK", "INFORMATION", "RETRIEVAL"};
//		String[] q1 = {"HELLO", "WORLD"};
//		String[] q2 = {"INFORMATION", "RETRIEVAL"};
//		String[] q3 = {"CANADA", "ARDVARK"};
//		String[] q4 = {"Jimmy", "Carter"};
//		String[] q5 = {"Green", "Party"};
//		String[] q6 = {"Innovations",  "in", "telecommunication"};
//		String[] q7 = {"environmentalist", "ecologist"};
//		//ASSIGNED QUERIES
//		//COMPRESS ASSIGNED QUERIES
//		q4[0] = "jimmy";
//		q4[1] = "carter";
//		q5[0] = "green";
//		q5[1] = "party";
//		q6[2] = new String();
//		q6[0] = "innovations";
//		q6[1] = "telecommunication";
 
 */
public class QueryWords {

	//ATTRIBUTES
	//(a) Which departments have AI research?
	//PERMUTATIONS:
	//ARTIFICIAL INTELIGENCE, AI, A.I.
	//DEPARTMENT, DEPT, DEPT.
	public String[] a1 = {"artificial", "intelligence" };					
	public String[] a2 = {"a.i."};								
	public String[] a3 = {"ai" };	
	public String[] a4 = {"artificial", "intelligence", "department" };		//14 matches
	public String[] a5 = {"artificial", "intelligence", "dept"  };			//1 match
	public String[] a6 = {"ai", "department" };								//16 match
	public String[] a7 = {"ai", "dept"  };									//3 match
	public String[] a8 = {"a.i.", "department" };							//13 match
	public String[] a9 = {"a.i.", "dept"  };								//0 match
	//(b) Which researchers are working on AI research?
	public String[] a10 = {"artificial", "intelligence", "ai" };	
	//(c) What AI research is being conducted at Concordia?
	public String[] b1 = {"artificial", "intelligence", "researcher" };							
	public String[] b2 = {"artificial", "intelligence", "researchers"   };								
	public String[] b3 = {"artificial", "intelligence", "research"  };		
	//X
	public String[] x = {"artificial", "intelligence", "ai", "a.i", "research", "researcher", "researchers"   };
	//ALL
	public ArrayList<String[]> queries = new ArrayList<String[]>();
	
	//CONSTRUCTOR
	public QueryWords(){	
		queries.add(x);
		queries.add(a1);
		queries.add(a2);
		queries.add(a3);
		queries.add(a4);
		queries.add(a5);
		queries.add(a6);
		queries.add(a7);
		queries.add(a8);
		queries.add(a9);
		queries.add(b1);
		queries.add(b2);
		queries.add(b3);
		queries.add(a10);
	}//close constructor
	
	//SETTERS AND GETTERS
	public ArrayList<String[]> getQueries(){ return this.queries; }
	public String[] getAWords(int i){ return this.queries.get(i); }
	
}//CLOSE CLASS QUERY WORDS

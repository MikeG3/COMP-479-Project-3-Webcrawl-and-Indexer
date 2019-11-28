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
	public String[] a1 = {"artificial", "intelligence", "department" };		//14 matches
	public String[] a2 = {"artificial", "intelligence", "dept"  };			//1 match
	public String[] a3 = {"artificial", "intelligence", "dept."  };			//1 match
	public String[] a4 = {"ai", "department" };								//1 match
	public String[] a5 = {"ai", "dept"  };									//1 match
	public String[] a6 = {"ai", "dept."  };									//1 match
	public String[] a7 = {"a.i.", "department" };							//1 match
	public String[] a8 = {"a.i.", "dept"  };								//1 match
	public String[] a9 = {"a.i.", "dept."  };								//1 match

	//(b) Which researchers are working on AI research?
	//(c) What AI research is being conducted at Concordia?
	
	public ArrayList<String[]> aQueries = new ArrayList<String[]>();
	
	//CONSTRUCTOR
	public QueryWords(){
		aQueries.add(a1);
		aQueries.add(a2);
		aQueries.add(a3);
		aQueries.add(a4);
		aQueries.add(a5);
		aQueries.add(a6);
		aQueries.add(a7);
		aQueries.add(a8);
		aQueries.add(a9);
	}//close constructor
	
	//SETTERS AND GETTERS
	public ArrayList<String[]> getAQueries(){ return this.aQueries; }
	public String[] getAWords(int i){ return this.aQueries.get(i); }
	
}//CLOSE CLASS QUERY WORDS

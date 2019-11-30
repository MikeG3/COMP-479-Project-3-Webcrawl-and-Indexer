/*

 MICHAEL GARNER
 COMP 479 
 Project 

 */

/*

 	SIMULATES WRITING TO A DISK DRIVE
 	
 	DOCUMENTS ALL INFORMATION TO AND SAVES IT TO A FILE

 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Disk_Writer {

	//ATTRIBUTES
	private final String DICTIONARY = "Dictionary.txt";
	private final String URLTABLE = "URLTable.txt";
	private final String QUERY = "QueryResults.txt";
	private final String AIDICTIONARY = "AIDictionary.txt";
	private final String AIURLTABLE = "AIUrlTable.txt";
	private final String AIQUERY = "AIQueryResult.txt";
	
	//CONSTRUCTOR
	public Disk_Writer(){}//close constructor 

	//SERVICE METHODS
	//WRITE TO DISK
	public void write(BM25Dictionary input){
		System.out.println("\nWRITING DICTIONARY TO DISK (Dictionary.txt)\n");
		try{
			FileWriter fw = new FileWriter( DICTIONARY );
			BufferedWriter bfr = new BufferedWriter( fw );
			//Write TOKEN INFO
			for (int i = 0 ; i < input.getSize() ; i++ )
				bfr.write(input.getToken(i).toString() + "\n");
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n" + e);
		}//close catch
	}//close function write to disk
	public void write(BM25Dictionary input, boolean x){
		System.out.println("\nWRITING DICTIONARY TO DISK (AIDictionary.txt)\n");
		try{
			FileWriter fw = new FileWriter( AIDICTIONARY );
			BufferedWriter bfr = new BufferedWriter( fw );
			//Write TOKEN INFO
			for (int i = 0 ; i < input.getSize() ; i++ )
				bfr.write(input.getToken(i).toString() + "\n");
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n" + e);
		}//close catch
	}//close function write to disk
	
	public void write(URLTable input){
		System.out.println("\nWRITING URL TABLE TO DISK (URLTable.txt)\n");
		try{
			FileWriter fw = new FileWriter( URLTABLE );
			BufferedWriter bfr = new BufferedWriter( fw );
			//Write TOKEN INFO
			for (int i = 0 ; i < input.getSize() ; i++ )
				bfr.write(input.getEntry(i).toString() );
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n" + e);
		}//close catch
	}//close function write to disk
	public void write(URLTable input, boolean x){
		System.out.println("\nWRITING URL TABLE TO DISK (AIURLTable.txt)\n");
		try{
			FileWriter fw = new FileWriter( AIURLTABLE );
			BufferedWriter bfr = new BufferedWriter( fw );
			//Write TOKEN INFO
			for (int i = 0 ; i < input.getSize() ; i++ )
				bfr.write(input.getEntry(i).toString() );
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n" + e);
		}//close catch
	}//close function write to disk
	
	public void write(WordQuery query, QueryWords words, BM25Dictionary dictionary){
		System.out.println("\nWRITING QUERY RESULTS TO THE FILE: \"QueryResults.txt\"\n");
		try{
			FileWriter fw = new FileWriter( QUERY );
			BufferedWriter bfr = new BufferedWriter( fw );
			//Write TOKEN INFO
			for (int i = 0 ; i < words.getQueries().size() ; i++ )
				bfr.write(query.bm25SearchToString(words.getAWords(i), dictionary));
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n" + e);
		}//close catch
	}//close function write to disk
	
	public void write(WordQuery query, QueryWords words, BM25Dictionary dictionary, boolean x){
		System.out.println("\nWRITING QUERY RESULTS TO THE FILE: \"AIQueryResult.txt\"\n");
		try{
			FileWriter fw = new FileWriter( AIQUERY );
			BufferedWriter bfr = new BufferedWriter( fw );
			//Write TOKEN INFO
			for (int i = 0 ; i < words.getQueries().size() ; i++ )
				bfr.write(query.bm25SearchToString(words.getAWords(i), dictionary));
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n" + e);
		}//close catch
	}//close function write to disk
	
	public void writeQueryResults(ArrayList<String[]> in, BM25Dictionary dic){
		System.out.println("\nWRITING TO DISK (QueryResults.txt)\n");
		try{
			FileWriter fw = new FileWriter( QUERY );
			BufferedWriter bfr = new BufferedWriter( fw );
			WordQuery wq = new WordQuery();
			//Write QueryResults
			for (int i = 0 ; i < in.size() ; i++ )
				bfr.write( wq.bm25SearchToString(in.get(i), dic));
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n" + e);
		}//close catch
	}//close function write query results
		
	public void writeToDisk(ArrayList<String> input, String fileName){
		try{
			FileWriter fw = new FileWriter( fileName );
			BufferedWriter bfr = new BufferedWriter( fw );
			//Write TOKEN INFO
			for (int i = 0 ; i < input.size() ; i++ )
				bfr.write(input.get(i)+"\n");
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n" + e);
		}//close catch
	}//close function write to disk

	//WRITE TO DISK
	public void writeToDisk(String input, String fileName){
		try{
			FileWriter fw = new FileWriter( fileName );
			BufferedWriter bfr = new BufferedWriter( fw );
			//Write TOKEN INFO
			bfr.write(input);
			bfr.close();
		}//close try
		catch(Exception e){
			System.out.println("\nERROR in FILE WRITER\n" + e);
		}//close catch
	}//close function write to disk

/*	
	//READ FROM DISK
	public ArrayList<String> readDisk(){
		//Temp memory for strings
		ArrayList<String> strings = new ArrayList<String>();
		//Scan in all words in to array.
		try{
			fscan = new Scanner(file);
			while (fscan.hasNextLine()){ strings.add( fscan.next() );}
			fscan.close();
		} catch (Exception e){
			System.out.println(e);
			System.out.println("There was an error opening the Disk (Disk.txt");
		}//close try catch for opening file and extracting data
		return strings;
	}//close function read disk
*/
	
}//close class disk writer

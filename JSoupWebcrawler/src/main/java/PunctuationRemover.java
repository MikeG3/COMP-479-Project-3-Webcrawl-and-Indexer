/*

 MICHAEL GARNER
 COMP 479 
 Project 1

 */

/*
 * 

 PUNCTUATION SPLITTER REMOVES THE PUNCTUATIONS, LIKE PERIODS AND COMMAS ATTACHED TO THE END OF THE WORD.

 SPLITS CASES WHERE A WORD IS DIVIDED BY 

 */

public class PunctuationRemover {

	//SERVICE METHODS
	public String removeEndPunctuations(String input){
		boolean recursiveCall = false;
		String output = input;
		char c1 = '.';
		char c2 = ',';
		char c3 = '-';
		char c4 = '?';
		char c5 = '!';
		char c6 = ':';
		char c7 = ';';
		int d1 = (int) c1;
		int d2 = (int) c2;
		int d3 = (int) c3;
		int d4 = (int) c4;
		int d5 = (int) c5;
		int d6 = (int) c6;
		char c;
		if ( input.length() > 1){
			c = input.charAt( input.length()-1 );
			int inInt = (int) c;
			if (inInt==d1||inInt ==d2||inInt ==d3||inInt==d4||inInt ==d5||inInt ==d6){
				output = input.substring(0, input.length()-1);
				recursiveCall = true;
			}//close if found symbol
		}//close if greater than 1 character in size
		if ( input.length() > 1){
			c = input.charAt( 0 );
			int inInt = (int) c;
			if (inInt==d1||inInt ==d2||inInt ==d3||inInt==d4||inInt ==d5||inInt ==d6){
				output = input.substring(1, input.length());
				recursiveCall = true;
			}//close if found symbol
		}//close if greater than 1 character in size
		if (recursiveCall)
			return removeEndPunctuations(output);
		else
			return output;
	}//close punctuation remover function

}//close class punctuation remover

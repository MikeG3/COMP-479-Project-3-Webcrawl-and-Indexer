import java.util.ArrayList;

/*



 */
/*

MICHAEL GARNER
26338739
COMP 479 
Project

*/

/*

	PARSES HTML TEXT AND PROCESSES/CLEANS/FILTERS AND COMPRESSES DATA

*/
public class HTMLPreprocessor {

	//ATTRIBUTES
	//CONSTRUCTOR
	//SETTERS AND GETTERS

	//SERVICE METHODS
	//COMPRESS TEXT
	public ArrayList<String> parse(String in){
		String[] parsedText;
		ArrayList<String> out = new ArrayList<String>();
		//SPLIT TEXT
		parsedText = in.split(" ");
		//COMPRESS TEXT
		CompressorLossy compress = new CompressorLossy();
		for (int i = 0 ; i < parsedText.length ; i++ ){
			out.add( compress.lossyCompression(parsedText[i]) );
		}//close for i
		return out;
	}//close function compress text

}//Close class html pre-processor

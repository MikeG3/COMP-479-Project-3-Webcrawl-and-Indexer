import java.util.ArrayList;

/*



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

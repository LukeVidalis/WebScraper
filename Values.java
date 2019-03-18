
public class Values {

	//Website Link
	protected static final String[] url = new String[]{"http://www.betfellas.gr/tipster/SerSira/", 
			"http://www.betfellas.gr/tipster/Vito/",
			"http://www.betfellas.gr/tipster/Betol/",
			"http://www.betfellas.gr/tipster/1926/"};

	// ID values
	protected static final int sportId = 0;
	protected static final int dateId = 1;
	protected static final int compId = 2;
	protected static final int betId = 3;
	protected static final int oddsId = 4;
	protected static final int stakeId = 5;
	protected static final int bookId = 6;
	protected static final int resultId = 7;
	protected static final int returnId = 8;

	// Special Characters
	protected static final char dot = '.';
	protected static final char comma = ',';

	//HTML Tags
	protected static final String td="td";
	protected static final String th = "th";
	protected static final String img = "img";
	protected static final String tr = "tr";
	protected static final String tts ="table table-striped";
	protected static final String fc ="form-control";
	protected static final String vl ="value";
	
	//Attributes
	protected static final String src="src";
	
	//String Concatenation
	protected static final int startIndex = 8;
	protected static final int endIndexOffset = 4;
	
	//CSV Header
	protected static final String header="ID, SPORT, DATE, COMP, BET-TYPE, MATCH-UP, BET, ODDS, STAKE, BOOK, RESULT, +/-, ERROR CHECKING";

	//Index
	protected static final int entryIndex = 0;

	//Class id
	protected static final String displayResult="displayResult";
	
	//Misc
	protected static final int betsPerPage = 25;
	protected static final char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	//Error Codes
	protected static final String[] errorCodes = new String[]{"Error 1: Part of the matchup text is not recognized.", 
			"Error 2: WebScrapper crashed while trying to get this bet.",
			"Error 3: Number of odds doesn't match the number of matchups recognized.",
			"Error 4: This bet is possibly an unrecognized accumulator or there is an issue with the odds field."};
	
	//Console messages
	protected static final String listMsg = "The list of tipsters currently is: ";
	protected static final String initMessage = "Enter 4 values (between 0 and 1) with a space in between for each of the tipsters above in the order they appear. \n"
			+ "A 1 indicates that the data of that tipsters will be recorded, whereas a 0 means it won't. \n"
			+ "Example: 1 0 0 1 \n"
			+ "Alternatively, enter a single String with the name of the tipster for their own data (Does NOT have to be listed) \n"
			+ "Example: SerSira";
	protected static final String question = "Would you like to run the program on all of them? (Yy/Nn)";
	protected static final String questionErrorMsg = "Please input yes or no (Yy/Nn)";
	protected static final String errorMsg1 = "Please input 4 distinct integers. (E.g. 1 0 0 1) or a single string (E.g. SerSira)";
	protected static final String errorMsg2 = "Please input only 1's or 0's";
	protected static final String errorMsg3 = "The tipster was not found. Please try again.";
}

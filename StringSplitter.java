import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSplitter {
	private ArrayList<String> states = new ArrayList<String>();

	private ArrayList<String> bets = new ArrayList<String>();
	private int cutoff = 0;
	private int matchIndex = 0;
	final String regex = "\\(([0-9]*?)\\)";
	final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
	Matcher matcher=null;
	private String string;

	public StringSplitter(String string) {
		this.string =string;
		split();
	}

	private void split() {
		matcher = pattern.matcher(string);

		while (matcher.find()) {
			states.add(matcher.group(0));
		}
		for (int i = 0; i < states.size(); i++) {
			matchIndex = string.indexOf(states.get(i), cutoff);
			matchIndex = matchIndex + states.get(i).length();
			//System.out.println("cutoff: " + cutoff + " match: " + matchIndex);
			bets.add(string.substring(cutoff, matchIndex));
			cutoff = matchIndex + 1;
		}

//		for (int i = 0; i < bets.size(); i++) {
//			System.out.println(bets.get(i));
//		}
	}
	public boolean matchFound() {
		
		return (bets.size()>0);
				
	}

	public ArrayList<String> getStates() {
		return states;
	}

	public ArrayList<String> getBets() {
		return bets;
	}

}

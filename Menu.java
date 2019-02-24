import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

	public static void main(String[] args) {
		ArrayList<String> states = new ArrayList<String>();
		ArrayList<String> bets = new ArrayList<String>();
		int cutoff=0;
		int matchIndex=0;
		final String regex = "\\(([0-9]*?)\\)";
		final String string = "Ksanthi – Iraklis 0:1.5 (15) Ksanthi - Iraklis(5) Ksanthi - Iraklis(15)";
				

		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(string);
		while (matcher.find()) {
			states.add(matcher.group(0));
		}
		for(int i=0;i<states.size();i++) {
			 matchIndex= string.indexOf(states.get(i),cutoff);
			 matchIndex = matchIndex+states.get(i).length();
			 System.out.println("cutoff: "+cutoff+" match: "+matchIndex);
			 bets.add(string.substring(cutoff,matchIndex));
			 cutoff=matchIndex+1;
		}
		
		for(int i=0;i<bets.size();i++) {
			System.out.println(bets.get(i));
		}
	}

}

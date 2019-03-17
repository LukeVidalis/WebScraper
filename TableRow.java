
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TableRow {
	private String sport;
	private String date;
	private String comp;
	private String match;
	private String bet;
	private String odds;
	private String stake;
	private String book;
	private String result;
	private String Return;
	private String betType;
	private Elements fields;
	private boolean multibet;
	private StringSplitter ss;
	private int id;

	public TableRow(Element row, int id) {
		this.id = id;
		fields = row.getElementsByTag(Values.td);
		match = row.getElementsByTag(Values.th).text().replace(Values.comma, Values.dot);
		setup(fields);

	}

	private void setup(Elements fields) {
		sport = fields.get(Values.sportId).getElementsByTag(Values.img).attr(Values.src);
		date = fields.get(Values.dateId).text();
		comp = fields.get(Values.compId).text();
		bet = fields.get(Values.betId).text().replace(Values.comma, Values.dot);
		odds = fields.get(Values.oddsId).text().replace(Values.comma, Values.dot);
		stake = fields.get(Values.stakeId).text();
		book = fields.get(Values.bookId).getElementsByTag(Values.img).attr(Values.src);
		result = fields.get(Values.resultId).text();
		Return = fields.get(Values.returnId).text();

		sport = formatPath(sport);
		book = formatPath(book);
		ss = new StringSplitter(match);

		multibet = ss.matchFound();
		setBetType();
	}

	public String toString() {
		String s = "";
		if (!multibet) {
			s = id + ", " + sport + ", " + date + ", " + comp + ", " + betType + ", " + match + ", " + bet + ", " + odds
					+ ", " + stake + ", " + book + ", " + result + ", " + Return;
			return s;
		} else {
			return splitToString();
		}
	}

	public String splitToString() {
		String s = "";
		int j = 0;
		int k = 0;
		ArrayList<String> bets = ss.getBets();
		String[] oddsSplit = odds.split(" ");
		String[] compSplit = comp.split(" ");
		String[] dateSplit = (date.split("(?<=\\G............)"));

		if (ss.getError() || bets.size() != oddsSplit.length) {
			return contingencyMethod();
		} else {
			for (int i = 0; i < bets.size(); i++) {
				s = s + id + Values.alphabet[i] + ", " + sport + ", " + dateSplit[i] + ", " + compSplit[j] + ", "
						+ betType + ", " + bets.get(i) + ", " + bet + ", " + oddsSplit[k] + ", " + stake + ", " + book
						+ ", " + result;
				if (compSplit.length == bets.size()) {
					j++;
				}
				if (oddsSplit.length == bets.size()) {
					k++;
				}
				if (i == 0)
					s = s + ", " + Return;
				if (i < bets.size() - 1)
					s = s + "\n";
			}

			return s;
		}
	}

	private String contingencyMethod() {
		String s = "";
		s = id + ", " + sport + ", " + date + ", " + comp + ", " + betType + ", " + match + ", " + bet + ", " + odds
				+ ", " + stake + ", " + book + ", " + result + ", " + Return + ", Possible error; please check.";
		return s;
	}

	private String formatPath(String s) {
		if (s.endsWith(".jpg") || s.endsWith(".png")) {
			String str = s.substring(Values.startIndex, s.length() - Values.endIndexOffset);
			return str;
		} else {
			return s;
		}
	}

	public String getDate() {
		return date;
	}

	public String getSport() {
		return sport;
	}

	public String getBook() {
		return book;
	}

	public String getComp() {
		return comp;
	}

	public String getMatch() {
		return match;
	}

	public String getBet() {
		return bet;
	}

	public String getOdds() {
		return odds;
	}

	public String getStake() {
		return stake;
	}

	public String getResult() {
		return result;
	}

	public String getReturn() {
		return Return;
	}

	public void setBetType() {
		if (Character.isDigit(match.charAt(0))) {
			this.betType = "Live";
		} else {
			this.betType = "Pre-Match";
		}
	}
}

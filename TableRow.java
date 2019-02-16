
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TableRow {
	private String sport;
	private String date;
	private String comp;
	private String match;
	private String bet;
	private String odds;
	private double stake;
	private String book;
	private String result;
	private String Return;
	private String betType;
	private Elements fields;

	public TableRow(Element row) {

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
		stake = Double.parseDouble(fields.get(Values.stakeId).text());
		book = fields.get(Values.bookId).getElementsByTag(Values.img).attr(Values.src);
		result = fields.get(Values.resultId).text();
		Return = fields.get(Values.returnId).text();

		sport = formatPath(sport);
		book = formatPath(book);
		
		setBetType();
	}

	public String toString() {
		String s = sport + ", " + date + ", " + comp + ", " + betType + ", " + match + ", " + bet + ", " + odds + ", " + stake + ", "
				+ book + ", " + result + ", " + Return;
		return s;
	}

	private String formatPath(String s) {
		String str = s.substring(Values.startIndex, s.length() - Values.endIndexOffset);
		return str;
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

	public double getStake() {
		return stake;
	}

	public String getResult() {
		return result;
	}

	public String getReturn() {
		return Return;
	}
	
	public void setBetType() {
		if(Character.isDigit(match.charAt(0))) {
			this.betType = "Live";
		} else {
			this.betType = "Pre-Match";
		}
	}
}

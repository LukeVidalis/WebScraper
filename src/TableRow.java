
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TableRow {
	private String date;
	private String comp;
	private String match;
	private String bet;
	private String odds;
	private double stake;
	private String result;
	private String Return;
	Elements fields;

	public TableRow(Element row) {
		
		fields = row.getElementsByTag(Values.td);
		match = row.getElementsByTag(Values.th).text().replace(Values.comma, Values.dot);;
		setup(fields);
	
	}

	private void setup(Elements fields) {
		date=fields.get(Values.dateId).text();
		comp=fields.get(Values.compId).text();
		bet=fields.get(Values.betId).text().replace(Values.comma, Values.dot);
		odds=fields.get(Values.oddsId).text().replace(Values.comma, Values.dot);
		stake=Double.parseDouble(fields.get(Values.stakeId).text());
		result=fields.get(Values.resultId).text();
		Return=fields.get(Values.returnId).text();
	}
	
	public String toString() {
		String s = date+", "+comp+", "+match+", "+bet+", "+odds+", "+stake+", "+result+", "+Return;
		return s;
	}
	public String getDate() {
		return date;
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
}

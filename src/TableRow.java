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
		fields = row.getElementsByTag("td");
//		for (Element r : fields) {
//			System.out.println(r.text() + "\n");
//		}
//		System.out.println(fields.size());
		match = row.getElementsByTag("th").text();
		setup(fields);
	
	}

	private void setup(Elements fields) {
		date=fields.get(1).text();
		comp=fields.get(2).text();
		bet=fields.get(3).text();
		odds=fields.get(4).text().replace(',', '.');
		stake=Double.parseDouble(fields.get(5).text());
		result=fields.get(7).text();
		Return=fields.get(8).text();
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

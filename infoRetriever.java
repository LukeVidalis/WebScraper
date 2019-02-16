import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



//Main Link: http://www.betfellas.gr/tipster/SerSira/?period=2018&page=1

public class infoRetriever {
	final static String url = Values.url;
	private ArrayList<TableRow> rowsList = new ArrayList<TableRow>();
	private ArrayList<String> periods = new ArrayList<String>();

	public infoRetriever() throws IOException {
		getPeriods();
		getSite();
	}

	private void getSite() throws IOException {

		for(String p : periods) {
			Boolean hasNext = true;
			int counter = 0;
			while(hasNext) {
				
				Document doc = Jsoup.parse(new URL(url+ "?period=" + p + "&page=" + counter).openStream(), "UTF-8", url+ "?period=" + p + "&page=" + counter);
				
				Elements tables = doc.getElementsByClass(Values.tts);
				Element tipTable = tables.last();
				Elements rows = tipTable.getElementsByTag(Values.tr);
				if(rows.size() > 1) {
					saveRow(rows);
					counter++;
				} else {
					hasNext = false;
				}

			}
			saveData(p);
			rowsList.clear();
		}
	}
	
	private void getPeriods() throws IOException {
		Document doc = Jsoup.connect(url).get();
		Elements dropDown = doc.getElementsByClass(Values.fc);
		Elements values = dropDown.get(0).getElementsByAttribute(Values.vl);
		for(Element e:values) {
			periods.add(e.attr(Values.vl));
		}
	}
	
	private void saveRow(Elements rows) {
		for (int i = 1; i < rows.size(); i++) {
			TableRow tr = new TableRow(rows.get(i));
			rowsList.add(Values.entryIndex,tr);
		}
	}

	private void saveData(String period) throws IOException {
		DataSaver ds = new DataSaver(period, rowsList);
		output(rowsList);
	}
	
	

	private void output(ArrayList<TableRow> rowsList) {
		for (TableRow r : rowsList) {
			System.out.println(r.toString() + "\n");
		}
	}
}

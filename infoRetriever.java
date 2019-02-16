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

	public infoRetriever() throws IOException {
		getSite();
	}

	private void getSite() throws IOException {
		Boolean hasNext = true;
		int counter = 0;
		while(hasNext) {
			Document doc = Jsoup.parse(new URL(url+counter).openStream(), "UTF-8", url+counter);

			
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
		saveData();
	}
	
	private void saveRow(Elements rows) {
		for (int i = 1; i < rows.size(); i++) {
			TableRow tr = new TableRow(rows.get(i));
			rowsList.add(Values.entryIndex,tr);
		}
	}

	private void saveData() throws IOException {
		DataSaver ds = new DataSaver();
		ds.addData(rowsList);
		output(rowsList);
	}
	
	

	private void output(ArrayList<TableRow> rowsList) {
		for (TableRow r : rowsList) {
			System.out.println(r.toString() + "\n");
		}
	}
}

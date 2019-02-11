import java.io.IOException;
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
		for(int i=0;i<20;i++) {
			Document doc = Jsoup.connect(url + i).get();
			Elements tables = doc.getElementsByClass(Values.tts);
			Element tipTable = tables.last();
			Elements rows = tipTable.getElementsByTag(Values.tr);

			saveRow(rows);
		}
		saveData();

	}
	
	private void saveRow(Elements rows) {
		for (int i = 1; i < rows.size(); i++) {
			TableRow tr = new TableRow(rows.get(i));
			rowsList.add(tr);
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

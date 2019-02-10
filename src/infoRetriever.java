import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//Main Link: http://www.betfellas.gr/tipster/SerSira/?period=2018&page=1
//Created by Luke Vidalis
public class infoRetriever {
	final static String url = "http://www.betfellas.gr/tipster/SerSira/?period=2018&page=";

	public infoRetriever() throws IOException {
		getSite();
	}

	private void getSite() throws IOException {
		Document doc = Jsoup.connect(url + "1").get();

		Elements tables = doc.getElementsByClass("table table-striped");
		Element tipTable = tables.last();
		Elements rows = tipTable.getElementsByTag("tr");

		saveData(rows);

	}

	private void saveData(Elements rows) throws IOException {
		ArrayList<TableRow> rowsList = new ArrayList<TableRow>();

		for (int i = 1; i < rows.size(); i++) {
			TableRow tr = new TableRow(rows.get(i));
			rowsList.add(tr);

//			String rowText = row.text();
//			System.out.println(rowText + "\n");
		}
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

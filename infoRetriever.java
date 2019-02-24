import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



//Main Link: http://www.betfellas.gr/tipster/SerSira/?period=2018&page=1

public class infoRetriever {
	final static String[] url = Values.url;
	private ArrayList<TableRow> rowsList = new ArrayList<TableRow>();
	private ArrayList<String> periods = new ArrayList<String>();
	private int recordCounter = 0;
	private boolean exists = false;

	public infoRetriever() throws IOException {
		for(int i=2;i<4;i++) {
			getPeriods(url[i]);
			getSite(url[i]);
		}
	}

	private void getSite(String url) throws IOException {
			for(String p : periods) {
				recordCounter = 0;
				Boolean hasNext = true;
				exists = false;
				int counter = 0;
				while(hasNext && exists == false) {
					
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
				String[] splits = url.split("/");
				saveData(splits[splits.length-1], p);
				rowsList.clear();
			}
	}
	
	private void getPeriods(String url) throws IOException {
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
			if(rowsList.size() >= 25) {
				for(int j=0;j<25;j++) {
					if((tr).equals(rowsList.get(j))) {
						exists = true;
						break;
					}
				}
			}
			if(exists == false) {
				rowsList.add(Values.entryIndex,tr);
				recordCounter++;
				System.out.println(recordCounter);
			}
		}
	}

	private void saveData(String tipster, String period) throws IOException {
		DataSaver ds = new DataSaver(tipster, period, rowsList);
		output(rowsList);
	}
	
	

	private void output(ArrayList<TableRow> rowsList) {
		for (TableRow r : rowsList) {
			System.out.println(r.toString() + "\n");
		}
	}
}

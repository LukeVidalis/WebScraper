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
	private int maxBets = 0;

	public infoRetriever() throws IOException {
		for (int i = 0; i < 4; i++) {
			getPeriods(url[i]);
			System.out.println("Tipster "+Values.url[i].substring(32, Values.url[i].length()-1));
			getSite(url[i]);
		}
	}

	private void getSite(String url) throws IOException {
		for (String p : periods) {
			recordCounter = 0;
			Boolean firstRun = true;
			int counter = 0;
			int totalPages = 100;
			System.out.println("Period: "+p);

			do {
				Document doc = Jsoup.parse(new URL(url + "?period=" + p + "&page=" + counter).openStream(), "UTF-8",
						url + "?period=" + p + "&page=" + counter);

				Elements tables = doc.getElementsByClass(Values.tts);
				Element tipTable = tables.last();
				Elements rows = tipTable.getElementsByTag(Values.tr);
				Elements pagesNum = doc.getElementsByClass(Values.displayResult);
				if (firstRun) {
					totalPages = getMaxPages(pagesNum);
					firstRun = false;
				}
				System.out.println("Page "+counter);
				if (rows.size() > 1) {
					saveRow(rows);
					counter++;
				} else {
					// hasNext = false;
				}

			} while (counter < totalPages);
			String[] splits = url.split("/");
			if(!rowsList.isEmpty()){
				saveData(splits[splits.length - 1], p);
			}
			rowsList.clear();
		}
	}

	private void getPeriods(String url) throws IOException {
		periods.clear();
		Document doc = Jsoup.connect(url).get();
		Elements dropDown = doc.getElementsByClass(Values.fc);
		Elements values = dropDown.get(0).getElementsByAttribute(Values.vl);
		for (Element e : values) {
			periods.add(e.attr(Values.vl));
		}
	}

	private int getMaxPages(Elements pageNumText) {
		String s = pageNumText.get(0).text();
		s = s.substring(s.length() - 11, s.length());
		s = s.replaceAll("\\D+", "");
		double bets = Double.parseDouble(s);
		maxBets=Integer.parseInt(s);
		int maxPages = (int) Math.ceil(bets / Values.betsPerPage);
		System.out.println("Total Pages: " + maxPages);
		return maxPages;
	}

	private void saveRow(Elements rows) {
		for (int i = 1; i < rows.size(); i++) {
			TableRow tr = new TableRow(rows.get(i),maxBets-recordCounter);
			rowsList.add(Values.entryIndex, tr);
			recordCounter++;
			//System.out.println(recordCounter);
		}
	}

	private void saveData(String tipster, String period) throws IOException {
		DataSaver ds = new DataSaver(tipster, period, rowsList);
		//output(rowsList);
	}

	private void output(ArrayList<TableRow> rowsList) {
		for (TableRow r : rowsList) {
			System.out.println(r.toString() + "\n");
		}
	}
}

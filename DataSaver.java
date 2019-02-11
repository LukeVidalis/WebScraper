import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataSaver {
	public void addData(ArrayList<TableRow> rowsList) throws IOException {
		FileWriter pw = new FileWriter("Bets.csv", true);
		pw.append(Values.header+"\n");

		for (int i = 0; i < rowsList.size(); i++) {

			pw.append(rowsList.get(i).toString());
			
			if (i != rowsList.size() - 1) {
				pw.append(",");
			}
			pw.append("\n");
		}
		pw.flush();
		pw.close();
	}
}

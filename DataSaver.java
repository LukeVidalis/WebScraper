import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataSaver {
	
	private String period;
	
	public DataSaver(String period, ArrayList<TableRow> rowsList) throws IOException {
		setPeriod(period);
		addData(rowsList);
	}
	
	public void addData(ArrayList<TableRow> rowsList) throws IOException {
		String fileName = "Bets(" + period + ").csv";
		FileWriter pw = new FileWriter(fileName, true);
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
	
	private void setPeriod(String period){
		int previous = Integer.parseInt(period) - 1;
		this.period = previous + "-" + period;
	}
	
}

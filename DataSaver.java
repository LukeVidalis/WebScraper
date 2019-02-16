
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.io.FileOutputStream;


public class DataSaver {
	
	private String period;
	
	public DataSaver(String period, ArrayList<TableRow> rowsList) throws IOException {
		setPeriod(period);
		addData(rowsList);
	}
	
	public void addData(ArrayList<TableRow> rowsList) throws IOException {
		String fileName = "Bets(" + period + ").csv";
		PrintStream pw = new PrintStream(new FileOutputStream(fileName), true, "UTF-8");
		
		byte[] bom = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
		pw.write(bom);
		
		pw.append(Values.header + "\n");

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

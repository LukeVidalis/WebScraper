
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainScrapper {

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		//new infoRetriever();
		new Menu();
		long endTime = System.currentTimeMillis();
		long runTimeMillis = (endTime - startTime);
	
	    Date date = new Date(runTimeMillis);
	    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	    String formatted = formatter.format(date);
	    System.out.println("Runtime: " + formatted);
	}

}

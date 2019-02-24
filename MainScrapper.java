
import java.io.IOException;

public class MainScrapper {

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		new infoRetriever();
		long endTime = System.currentTimeMillis();
		long runTime = (endTime - startTime)/1000;
		System.out.println("Runtime (seconds): " + runTime);
		//new Menu();
	}

}

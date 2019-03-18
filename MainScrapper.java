
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;
import org.jsoup.HttpStatusException;


public class MainScrapper {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		Boolean good = false;
		System.out.println(Values.listMsg);
		for(String s: Values.url){
			System.out.println(s.substring(32, s.length() - 1));
		}
		System.out.println(Values.question);
		while(!good) {
			String answer = sc.nextLine();
			if(answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes")) {
				int[] input = {1, 1, 1, 1};
				runCustomInt(input);
				good = true;
			} else if (answer.toLowerCase().equals("n") || answer.toLowerCase().equals("no")) {
				customInput(sc);
				good = true;
			} else {
				System.out.println(Values.questionErrorMsg);
			}
		}
	}
	
	private static void customInput(Scanner sc) throws IOException {
		Boolean good = false;
		System.out.println(Values.initMessage);
		loop:
		while(!good){
			int[] input = new int[4];
			String userInput = sc.nextLine();
			String[] numbers = userInput.split(" ");
			if(numbers.length == 4) {
				for(int i = 0;i<numbers.length;i++) {
					try {
						int number = Integer.parseInt(numbers[i]);
						if(number == 0 || number == 1) {
							input[i] = number;
						} else {
							System.out.println(Values.errorMsg2);
							continue loop;
						}
					} catch (NumberFormatException e) {
						System.out.println(Values.errorMsg1);
						continue loop;
					}
				}
				runCustomInt(input);
				good = true;
			} else if (numbers.length == 1) {
				try {
				    runCustomString(userInput);
				    good = true;
				} catch (HttpStatusException e) {
				    System.err.println(Values.errorMsg3);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println(Values.errorMsg1);
			}	
		}
	}
	
	private static void runCustomInt(int[] input) throws IOException {
		long startTime = System.currentTimeMillis();
		new infoRetriever(input);
		long endTime = System.currentTimeMillis();
		long runTimeMillis = (endTime - startTime);
	
	    Date date = new Date(runTimeMillis);
	    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	    String formatted = formatter.format(date);
	    System.out.println("Runtime: " + formatted);
	}
	
	private static void runCustomString(String input) throws IOException {
		long startTime = System.currentTimeMillis();
		new infoRetriever(input);
		long endTime = System.currentTimeMillis();
		long runTimeMillis = (endTime - startTime);
	
	    Date date = new Date(runTimeMillis);
	    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	    String formatted = formatter.format(date);
	    System.out.println("Runtime: " + formatted);
	}

}

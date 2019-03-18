
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class MainScrapper {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		Boolean good = false;
		System.out.println("The list of tipsters currently is: ");
		for(String s: Values.url){
			System.out.println(s.substring(32, s.length() - 1));
		}
		System.out.println("Enter 4 values (between 0 and 1) with a space in between for each of the tipsters above in the order they appear.");
		System.out.println("A 1 indicates that the data of that tipsters will be recorded, whereas a 0 means it won't.");
		System.out.println("Example: 1 0 0 1");
		int[] input = new int[4];
		while(!good){
			String userInput = sc.nextLine();
			String[] numbers = userInput.split(" ");
			if(numbers.length != 4) {
				System.out.println("Please input 4 distinct integers. (E.g. 1 0 0 1)");
			} else {
				for(int i = 0;i<numbers.length;i++) {
					try {
						int number = Integer.parseInt(numbers[i]);
						if(number == 0 || number == 1) {
							input[i] = number;
						} else {
							System.out.println("Please input only 1's or 0's");
							break;
						}
					} catch (NumberFormatException e) {
						System.out.println("Please input 4 distinct integers. (E.g. 1 0 0 1)");
						break;
					}
					good = true;
				}
			}
			
		}
		long startTime = System.currentTimeMillis();
		new infoRetriever(input);
		//new Menu();
		long endTime = System.currentTimeMillis();
		long runTimeMillis = (endTime - startTime);
	
	    Date date = new Date(runTimeMillis);
	    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	    String formatted = formatter.format(date);
	    System.out.println("Runtime: " + formatted);
	}

}

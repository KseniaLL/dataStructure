/**Course: Data Structure CST8130_300
 * Professor: Linda Crane
 * Student: Lopukhina Ksenia
 * Student number: 040892102
 * Date: 03.08.2018
 * Purpose: to implement menu of the inventory system
 * 
 *  Data members:  input: Scanner - is used to collect data from users and to read from a file 
 *                	inventory: Inventory - an object of Inventory class
 *                  userInput: int - menu choice input
 */
import java.util.Scanner;

public class Assign1Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Inventory inventory = new Inventory();
		int userInput = 0;

		do {
			String menu = "Enter: \r\n" +
					"1 to add an item to inventory \r\n" + 
					"2 to display current inventory\r\n" + 
					"3 to buy some of an item \r\n" + 
					"4 to sell some of an item  \r\n" + 
					"5 to save the contents of the inventory ArrayList to a file \r\n" +
					"6 to read the contents of a file to the inventory ArrayList \r\n" +
					"7 to quit\r\n";

			System.out.println(menu);

			if(input.hasNextInt()) {

				userInput = input.nextInt();

				if(userInput == 1) {

					inventory.addItem(input);

				}else if(userInput == 2) {

					System.out.println(inventory.toString());

				}else if (userInput == 3) {

					inventory.updateQuantity(input, true);

				}else if (userInput == 4) {

					inventory.updateQuantity(input, false);

				}else if (userInput == 5) {

					inventory.writeToFile(input);

				}else if(userInput == 6) {

					inventory.readFromFile(input);

				}else if (userInput == 7) {

					System.out.println("Goodbye");
					break;


				}
			}else {
				System.out.println("Enter an integer between 1 and 7");
				input.next();
			}


		}while (userInput != 7);
	}
}

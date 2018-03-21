/**Course: Data Structure CST8130_300
 * Professor: Linda Crane
 * Student: Lopukhina Ksenia
 * Student number: 040892102
 * Date: 03.08.2018
 * ManufacturedItem class is a child of class Item
 * 
 * Data members:    itemUsed: int[]  - codes used during item manufacturing
 *	                numItems: int - amount of items
 * Methods:              addItem(Scanner):boolean - adding codes to manufactured item
 *                       toString(): String - String representation of Manufactured Item object
 *                       convertCodesToString(): String - converts codes toString
 */

import java.util.Scanner;


public class ManufacturedItem extends Item {

	private int[] itemsUsed = new int[10];
	private int numItems;
	public ManufacturedItem() {}

	@Override
	public boolean addItem(Scanner input) {

		super.addItem(input);
		System.out.println("Enter up to 10 codes used in this item (-1 to quit): ");

		for (int i = 0; i< itemsUsed.length; i++) {

			numItems = checkInt(input, numItems);
			while (numItems < 0 && numItems != -1 ) {
				System.out.print("Invalid code. Enter an integer greater than 1: ");
				numItems = checkInt(input, numItems);
			}
			if (numItems == -1) {
				break;
			}else {
				itemsUsed[i] = numItems;
			}
		}
		return true;
	}

	@Override
	public boolean addItemFromFile(Scanner scanner) {

		super.addItemFromFile(scanner);

		for (int i = 0; i< itemsUsed.length; i++) {
			if (!scanner.hasNextInt()) {
				return false;
				
			}else

				numItems = checkInt(scanner, numItems);
			while (numItems < 0 && numItems != -1 ) {

				System.out.print("Invalid code. Enter an integer greater than 1: ");
				numItems = checkInt(scanner, numItems);
			}
			if (numItems == -1) {
				break;
			}else {
				itemsUsed[i] = numItems;
			}
		}

		return true;
	}

	public String toString() {

		String strCodes = "";
		for (int k = 0; k < itemsUsed.length; k++) {
			if(itemsUsed[k] != 0) {
				strCodes += itemsUsed[k] + ", ";
			}
		}
		return super.toString() + " Codes_used: " + strCodes;

	}


	public String convertCodesToString() {

		String strCodes = "";
		for (int k = 0; k < itemsUsed.length; k++) {
			if(itemsUsed[k] != 0) {
				strCodes += itemsUsed[k] + " ";
			}
		}

		return strCodes;	
	}
}

/**Course: Data Structure CST8130_300
 * Professor: Linda Crane
 * Student: Lopukhina Ksenia
 * Student number: 040892102
 * Date: 02.08.2018
 * Purpose of the class: to create an Item object
 * 
 *  Data members:   itemCode: int - code of the item
 *	                itemName: String - name of the item
 *	                itemQuantity: int -quantity of the item
 *	                itemPrice: float - price of the item
 *
 * Methods: addItem(Scanner):boolean - adding values to Item object
 *          toString(): String - String representation of Item object
 *          updateItem(int): boolean - updates the quantity of the object
 *          isEqual(Item): boolean - compares two codes of two items
 *          getItemCode(): int - returns item code
 *          getItemQuantity(): int - return item quantity
 *          checkInt(Scanner, int):int - validates user int input
 *          checkFloat(Scanner, float):float - validates user float input       
 */


import java.util.Scanner;

public class Item {

	private int itemCode;
	private String itemName;
	private int itemQuantity;
	private float itemPrice;

	public Item() {}

	public boolean addItem(Scanner input) {

		System.out.print("Enter the code for the item: ");
		itemCode = checkInt(input, itemCode);
		while (itemCode < 1) {
			System.out.print("Invalid code. Enter an integer greater than 0: ");
			itemCode = checkInt(input, itemCode);
		}

		System.out.print("Enter the name for the item: ");
		itemName = input.next();

		System.out.print("Enter the quantity for the item: ");
		itemQuantity = checkInt(input, itemQuantity);
		while (itemQuantity < 1) {
			System.out.print("Invalid quantity. Enter an integer greater than 0: ");
			itemQuantity = checkInt(input, itemQuantity);
		}

		System.out.print("Enter the price of the item: ");
		itemPrice = checkFloat(input, itemPrice);
		while (itemPrice < 0) {
			System.out.print("Invalid price. Enter a number greater than 0: ");
			itemPrice = checkFloat(input, itemPrice);
		}
		return true;
	}
	
	public boolean addItemFromFile(Scanner scanner) {

		itemCode = checkInt(scanner, itemCode);
		itemName = scanner.next();
		itemQuantity = checkInt(scanner, itemQuantity);
		itemPrice = checkFloat(scanner, itemPrice);
	
		return true;
	}

	

	public String toString() {

		return "Item: " + itemCode + " " + itemName + " " + itemQuantity + " " + " price: $" + itemPrice;
	}

	public boolean updateItem(int amount) {
		if (itemQuantity + amount < 0)
			return false; 

		itemQuantity += amount;
		return true;
	}

	public boolean isEqual(Item item) {

		return (this.getItemCode() ==  item.getItemCode());
	}

	public boolean inputCode(Scanner input) {
		itemCode = checkInt(input, itemCode);

		if (itemCode == -2) {
			return false;
		}else
			return true;
	}

	public int getItemCode() {
		return itemCode;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public String getItemName() {
		return itemName;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public int checkInt(Scanner input, int intToCheck) {

		boolean works = true;

		while(works) {

			if(input.hasNextInt()) {
				intToCheck = input.nextInt();

				break;
			}else {
				System.out.print("Invalid input. \n");
				input.next();
			}
		}

		return intToCheck;
	}

	public float checkFloat(Scanner input, float floatToCheck) {

		boolean works = true;

		while(works) {

			if(input.hasNextFloat()) {
				floatToCheck = input.nextFloat();
				break;
			}else {
				System.out.print("Invalid input. Enter a number greater than 0:  ");
				input.next();
			}
		}

		return floatToCheck;
	}

}

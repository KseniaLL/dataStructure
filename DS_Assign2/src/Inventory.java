/**Course: Data Structure CST8130_300
 * Professor: Linda Crane
 * Student: Lopukhina Ksenia
 * Student number: 040892102
 * Date: 03.08.2018
 * Purpose: this class holds a basic inventory system 
 * 
 * Data members:  inventory: Item[] - array of Item objects 
 *                numItems: int - current number of items in the array
 *                index: int - current index inside the array
 *                
 * Methods: addItem(Scanner):boolean - adding valid values to the ArrayList
 *          toString(): String - converts the data in the inventory to the String
 *          alreadyExists(Item): int - checks if an item with a certain code already exists in the inventory
 *          updateItem(Scanner): boolean - updates the quantity of the Item object
 *          
 *           writeToFile(Scanner):void - writes the inventory to a txt file
 *           readFromFile(Scanner): void - reads the inventory to the program from a txt file
 *           sortedIndex(Item):int - inserts an item to the correct spot in the array. It compares items' codes using binary search
 *           convertCodesToString(String[]): String - helps writeToFile method to converts item details to a string
 *           getCodesFromString(int, int[], String[]): int[] - helps  readFromFile method to convert codes from String to int      
 */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {

	private ArrayList<Item> inventory;
	private int numItems = 0;
	private int index;

	public Inventory() {
		inventory = new ArrayList<>(1000);
		index =0;
	}

	public boolean addItem(Scanner input) {

		Item item;

		System.out.println("Do you wish to add a purchased item (enter P/p) or manufactured (enter anything else)? ");

		String purchOrMan = input.next();

		item =  (purchOrMan.equals("P") || purchOrMan.equals("p"))? new PurchasedItem(): new ManufacturedItem();

		item.addItem(input);

		if (alreadyExists(item)== -1) { 

			inventory.add(sortedIndex(item), item);
			numItems ++;

			return true;
		} else {
			System.out.println ("\nItem" + item.getItemCode() + " is already in the inventory\n");
			return false;
		}
	}

	public boolean addItemFromFile(Scanner input) {

		Item item;
		String purchOrMan = input.next();

		item =  (purchOrMan.equals("P") || purchOrMan.equals("p"))? new PurchasedItem(): new ManufacturedItem();

		item.addItemFromFile(input);

		if (alreadyExists(item)== -1) { 

			inventory.add(sortedIndex(item), item);
			numItems ++;

			return true;
		} else {
			System.out.println ("\nItem" + item.getItemCode() + " is already in the inventory\n");
			return false;
		}

	}

	public void writeToFile (Scanner input)  {

		if(inventory.isEmpty()) {
			System.out.println("You cannot save empty inventory to the file");
			return;
		}

		BufferedWriter out;

		try{
			System.out.println("Enter the name of the file: ");
			String fileName = input.next();

			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
				System.out.println("The file " + file.getName() + " was created");
			}

			out = new BufferedWriter(new FileWriter(file));

			for(int i = 0; i < numItems; i++) {

				StringBuilder objectString2 = new StringBuilder();

				String str1 = String.valueOf(inventory.get(i).getItemCode()) + " " +
						inventory.get(i).getItemName() + " " +
						String.valueOf(inventory.get(i).getItemQuantity()) + " " +
						String.valueOf(inventory.get(i).getItemPrice()) + " ";

				if(inventory.get(i) instanceof PurchasedItem) {
					objectString2.append("p ");
					objectString2.append(str1);
					objectString2.append(((PurchasedItem) inventory.get(i)).getSuppplierName());
				}
				if(inventory.get(i) instanceof ManufacturedItem){
					objectString2.append("m ");
					objectString2.append(str1);
					objectString2.append(((ManufacturedItem) inventory.get(i)).convertCodesToString());
				}		

				out.write(objectString2.toString());
				out.newLine();

				objectString2 = null;

			}
			out.close();

			System.out.println("Inventory has been saved to the file " + file.getName());
		}catch (IOException ioe){
			System.out.println("Error while writing to a file");
		}
	}


	public void readFromFile(Scanner input){ 

		System.out.println("Enter the name of the file: ");

		File file = new File(input.next());

		if(file.length() == 0) {
			System.out.println("The file doesn't exist or is empty. You cannot read from it");
			return;
		}
		try {

			input = new Scanner(file);

			while (input.hasNext())
			{
				addItemFromFile(input);
			}

			input.close();
			file.delete();

			System.out.println("The records from the file " + file.getName() + " are loaded to the inventory\n");
		} catch (FileNotFoundException e) {
			System.out.println("The file wasn't found");
		}catch (NullPointerException ex) {
			System.out.println("The file doesn't exist\n");
		}catch (java.util.NoSuchElementException exp) {// bad info in a file
			System.out.println("The file has wrong data in it. Try a different file");
		}catch (NumberFormatException exeption) {// one little mistake in a file
			System.out.println("File is bad. Inventory is not saved");
		}
	}


	public int sortedIndex(Item item) {

		int first = 0;
		int last = numItems-1;
		int mid;

		while (first < last) {
			mid = (first + last) / 2;
			if (inventory.get(mid).getItemCode() > item.getItemCode())
				last =  mid - 1;
			else
				first = mid + 1;
		}
		if (first == numItems)
			return numItems;
		else if(inventory.get(first).getItemCode() > item.getItemCode())
			return first;
		else
			return first+1;
	}


	public String toString() {
		String str = "";

		if(inventory.isEmpty()) {
			return "Inventory is empty";
		}

		for(int i = 0; i < numItems; i++) {
			str += inventory.get(i).toString() + "\n";
		}
		return str;
	}

	public int alreadyExists (Item item) {

		index = -1;
		for (int i =0; i < numItems; i++) {
			if(inventory.get(i).isEqual(item)) {
				index = i;
				break;
			}
		}
		return index;
	}

	public boolean updateQuantity (Scanner input, boolean done) {

		if(inventory.isEmpty()) {
			System.out.println("You cannot buy or sell product. The inventory is empty");
			return false;
		}

		int amount = 0;
		Item item = new Item();

		System.out.println("Enter valid item code: ");

		do {

			item.inputCode(input);
			index = alreadyExists(item);

			if(index == -1) {
				System.out.println("Code not found in inventory...\r\n" + "Error...could not buy item\r\n Enter again:");
			}
		}while (index == -1);

		System.out.print("Enter valid quantity : ");
		amount = item.checkInt(input, amount);

		if(done == true) {
			inventory.get(index).updateItem(amount);
		}else {

			while(amount > inventory.get(index).getItemQuantity()) {
				System.out.print("There are not enough items \n Enter valid quantity :");
				amount = item.checkInt(input, amount);
			}

			inventory.get(index).updateItem(-amount);

		}
		return true;
	}

}

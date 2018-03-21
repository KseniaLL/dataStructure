/**Course: Data Structure CST8130_300
 * Professor: Linda Crane
 * Student: Lopukhina Ksenia
 * Student number: 040892102
 * Date: 03.08.2018
 * ManufacturedItem class is a child of class Item
 * 
 * Data members:    suppplierName:String  - name of supplier
 *	               
 * Methods:              addItem(Scanner):boolean - adding codes to manufactured item
 *                       toString(): String - String representation of Manufactured Item object
 */
import java.util.Scanner;


public class PurchasedItem extends Item {

	private String suppplierName;
	public PurchasedItem() {}

	@Override
	public boolean addItem(Scanner input) {
		
		super.addItem(input);	

		System.out.print("Enter the name of the supplier: ");
		suppplierName = input.next();

		return true;
	}
	

	@Override
	public boolean addItemFromFile(Scanner scanner) {
		super.addItemFromFile(scanner);
		suppplierName = scanner.next();

		return true;
	}
	
	
	
	public String getSuppplierName() {
		return suppplierName;
	}

	@Override
	public String toString() {
		return super.toString() + " Supplier: " + suppplierName;

	}
}

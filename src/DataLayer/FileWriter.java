package DataLayer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {

	public static void createBill(int order, String date, int tableNb, double price) {
		String nameBill = "";
		nameBill += "Bill Number";
		String a = Integer.toString(order);
		nameBill += a;
		nameBill += ".txt";
		File file = new File(nameBill);
		try {

			file.createNewFile();
			PrintWriter myWriter = new PrintWriter(file);
			myWriter.println("Factura cu numarul " + order);
			myWriter.println("Data in care s-a realizat factura: " + date);
			myWriter.println("Pentru masa cu numarul " + tableNb);
			myWriter.println("Pretul total: " + price);
			myWriter.close();
		} catch (IOException e) {
			System.out.println("Eroare la generarea facturii");
		}
	}

}
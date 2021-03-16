package BusinessLayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Tintesan Cristiana
 *
 */
public interface IRestaurantProcessing {

	/**
	 * @param menuItem parametrul menuItem indica noul produs pe care vrem sa il
	 *                 introducem in meniu
	 * @pre menuItem!=null
	 * @prea menu!=null
	 * @invariant isWellFormed()
	 * @post size()= menu.size() -1
	 * 
	 */
	void createMenuItem(MenuItem menuItem);

	/**
	 * @param name indica numele produsului din meniu pe care dorim sa il stergem
	 * @pre name!=null
	 * @invariant isWellFormed()
	 */
	void deleteMenuItem(String name);

	/**
	 * @param menuItem indica numele produsului caruia dorim sa ii actualizam pretul
	 * @pre menuItem != null
	 * @pre menu != null
	 * @pre menu.size() >0
	 * @invariant isWellFormed()
	 * @post m.getId() == menuItem.getId()
	 */
	void updateMenuItem(MenuItem menuItem);

	/**
	 * @param id    indica id-ul comenzii
	 * @param date  indica data in care s-a realizat comanda
	 * @param table indica masa care a solicitat comanda
	 * @param meniu indica produsele comandate din meniu
	 * @return comanda
	 * @pre id!=0
	 * @pre table != 0
	 * @post comanda este creata si returnata
	 */
	public Order createNewOrder(int id, Date date, int table, ArrayList<MenuItem> meniu);

	/**
	 * @param o comanda pentru care trebuie calculat pretul
	 * @return returneaza un numar de tipul double care indica pretul calculat
	 * @pre order!= null
	 * @post o comanda este generata si returnata
	 */
	double computePrice(Order o);

	/**
	 * @param orderId indica id-ul facturii
	 * @param date    indica data facturarii
	 * @param table   indica masa din restaurant pentru care s-a generat factura
	 * @param price   indica pretul facturii
	 * @pre orderId!= 0
	 * @pre data!= null
	 * @pre table != 0
	 * @pre price!=0
	 */
	void generateBill(int orderId, String date, int table, double price);

	/**
	 * @param id
	 * @return menu item
	 * @pre menu!=null
	 * @pre getSize()>0
	 * @invariant isWellFormed()
	 */
	MenuItem getById(int id);

	/**
	 * @return returneaza meniul restaurantului
	 * @invarinat isWellFormed
	 */
	List<MenuItem> getMenu();

	/**
	 * @return returneaza comenzile din restaurant
	 */
	List<Order> getOrders();

	/**
	 * @param order comanda
	 * @return produsele asociate cu comanda trasmisa ca si parametru
	 * @pre oreders!= null
	 * @pre orders.size()>0
	 * @invariant isWellFormed
	 */
	List<MenuItem> getItemsForOrder(Order order);

	/**
	 * @return returneaza true sau false, daca datele introduse sunt corecte sau nu
	 */
	
	boolean isWellFormed();
}
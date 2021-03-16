package BusinessLayer;

import DataLayer.FileWriter;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import java.util.*;

/**
 * @author Tintesan Cristiana
 *
 */
public class Restaurant implements IRestaurantProcessing {

	/**
	 * Coloectia Map este folosita pentru a stoca informatii despre comenzi,
	 * comenziile fiind generate pe baza produselor selectte din meniu(cheie,
	 * valoare)
	 */
	private Map<Order, List<MenuItem>> orders;
	/**
	 * Lista produselor din meniu
	 */
	private List<MenuItem> menu;

	/**
	 * Constructorul clasei Restaurant prin care se instantiaza noi colectii
	 */
	public Restaurant() {
		this.menu = new ArrayList<>();
		this.orders = new HashMap<>();
	}

	/**
	 * Metoda care verifca daca datele sunt corecte
	 */
	public boolean isWellFormed() {
		Set<Order> comenzi = orders.keySet();
		if (orders.size() == 0) {
			return false;
		} else {
			if (orders.size() != 0) {
				for (Order o : comenzi) {
					List<MenuItem> items = orders.get(o);
					for (MenuItem m : items) {
						if (m.getWeight() < 0) {
							return false;
						}
						if (m.getPrice() < 0) {
							return false;
						}
						if (m.getId() < 0) {
							return false;
						}
					}

				}
			}
		}
		return true;
	}

	/**
	 * Metoda createMenuItem este folosita pentru a adauga un nou produs in meniu
	 */
	public void createMenuItem(MenuItem menuItem) {
		assert menuItem != null;
		assert menu != null;
		assert isWellFormed();
		int size = menu.size();
		menu.add(menuItem);
		assert size == menu.size() - 1;
	}

	/**
	 * Metoda deleteMenuItem este folosita pentru a sterge un produs existent din
	 * meniu
	 */
	public void deleteMenuItem(String name) {
		assert name != null;
		assert isWellFormed();
		int i = 0;
		int x = menu.size();
		while (i < x) {
			String a = menu.get(i).name;
			if (a.equals(name)) {
				menu.remove(i);
			}
			i++;
		}

	}

	/**
	 * Metoda updateMenuItem este apelata pentru a actualiza un produs existent in
	 * meniu
	 */
	public void updateMenuItem(MenuItem menuItem) {
		assert menuItem != null;
		assert menu != null;
		assert menu.size() > 0;
		assert isWellFormed();
		MenuItem itemToUpdate = null;

		for (MenuItem m : menu) {
			if (m.getId() == menuItem.getId()) {
				itemToUpdate = m;
			}
		}
		if (itemToUpdate == null) {
			System.out.println("Nu putem actualiza");
		} else {
			itemToUpdate.update(menuItem);
		}

		assert itemToUpdate.equals(menuItem);
	}

	/**
	 * Metoda computePrice calculeaza pretul total pentru o comanda
	 */
	public double computePrice(Order o) {
		assert o != null;
		List<MenuItem> produse = new ArrayList<MenuItem>();
		produse = orders.get(o);
		double price = 0;
		int i = 0;
		int dim = produse.size();
		while (i < dim) {
			price += produse.get(i).computePrice();
			i++;
		}
		return price;
	}

	/**
	 * metoda createNewOrder creaza o noua comanda pentru o anumita masa la care
	 * sunt clienti
	 */
	public Order createNewOrder(int id, Date date, int table, ArrayList<MenuItem> meniu) {
		assert id != 0 && table != 0;
		Order order = new Order(id, date, table);
		orders.put(order, meniu);
		return order;
	}

	/**
	 * generateBill genereaza o noua factura cu datele trasmise ca si parametru
	 */
	public void generateBill(int order, String date, int tableNb, double price) {
		FileWriter.createBill(order, date, tableNb, price);
	}

	/**
	 * Returneaza produsul cautat in meniu, dupa id-ul acestuia
	 */
	public MenuItem getById(int id) {
		assert menu != null;
		assert menu.size() > 0;
		assert isWellFormed();
		MenuItem toFind;
		toFind = null;
		for (MenuItem m : menu) {
			if (m.getId() == id) {
				toFind = m;
			}
		}
		return toFind;
	}

	/**
	 * Metoda care returneaza lista produselor din meniu
	 */
	public List<MenuItem> getMenu() {
		assert isWellFormed();
		return (List<MenuItem>) this.menu;
	}

	/**
	 * returneaza comenzile din restaurant
	 */
	public List<Order> getOrders() {
		return new ArrayList<>(orders.keySet());
	}

	/**
	 * Metoda care returneaza produsele asociate cu comanda data
	 */
	public List<MenuItem> getItemsForOrder(Order order) {
		assert orders != null;
		assert orders.size() > 0;
		assert isWellFormed();
		return orders.get(order);
	}
}
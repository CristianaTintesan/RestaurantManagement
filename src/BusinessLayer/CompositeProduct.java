package BusinessLayer;

import java.util.List;

public class CompositeProduct extends MenuItem {
	private List<MenuItem> items;
	private double price;

	public CompositeProduct(String name, int weight, int id, List<MenuItem> menuItems, double price) {
		super(name, weight, id, price);
		this.items = menuItems;
		this.price = price;
	}

	public void update(MenuItem menuItem) {
		super.update(menuItem);
		items = ((CompositeProduct) menuItem).getMenuItems();
	}

	public List<MenuItem> getMenuItems() {
		return items;
	}

	public int getWeight() {
		int weightF = 0;
		int i = 0;
		int x = items.size();
		while (i < x) {
			int aux = items.get(i).getWeight();
			weightF = weightF + aux;
			i++;
		}
		return weightF;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public double computePrice() {
		double priceF = 0;
		int i = 0;
		int x = items.size();
		while (i < x) {
			double aux = items.get(i).computePrice();
			priceF = priceF + aux;
			i++;
		}
		return priceF;
	}
}
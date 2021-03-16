package BusinessLayer;

import BusinessLayer.MenuItem;

public class BaseProduct extends MenuItem {
	private double price;

	public BaseProduct(String name, int weight, int id, double price) {
		super(name, weight, id, price);
		this.price = price;

	}

	public double getPrice() {
		return price;
	}

	public double computePrice() {
		return this.price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void update(MenuItem menuItem) {
		super.update(menuItem);
		this.price = menuItem.computePrice();
	}
}
package BusinessLayer;

import BusinessLayer.MenuItem;

public abstract class MenuItem {

	private int id;
	protected String name;
	protected int weight;
	protected double price;

	public MenuItem(String name, int weight, int id, double price) {
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.price = price;
	}

	public abstract double computePrice();

	public void update(MenuItem menuItem) {
		this.name = menuItem.getName();
	}

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

}
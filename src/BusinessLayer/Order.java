package BusinessLayer;

import java.util.Date;
import java.util.Objects;

public class Order {

	private int idOrder;
	private Date date;
	private int table;

	public Order(int table, Date date, int idOrder) {
		this.table = table;
		this.date = date;
		this.idOrder = idOrder;
	}

	public int getTable() {
		return table;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public Date getDate() {
		return date;
	}

	public void setTable(int table) {
		this.table = table;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}
		Order order = (Order) o;
		if (idOrder != order.idOrder || table != order.table || (!Objects.equals(date, order.date))) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (date == null) {
			result = prime * result;
		} else {
			result = result * prime + date.hashCode();
		}
		result = prime * result + idOrder;
		result = prime * result + table;
		return result;
	}

}
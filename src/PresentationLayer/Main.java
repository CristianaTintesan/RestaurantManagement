package PresentationLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;
import PresentationLayer.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Main extends JFrame {

	private WaiterGraphicalUserInterface waiterPanel;
	private AdministratorGraphicalUserInterface administratorPanel;
	private Restaurant restaurant;

	public Main(String title) {
		super(title);

		restaurant = new Restaurant();

		waiterPanel = new WaiterGraphicalUserInterface(restaurant, this);
		administratorPanel = new AdministratorGraphicalUserInterface(restaurant, this);
		JTabbedPane tabbedPane = new JTabbedPane();

		tabbedPane.addTab("Administrator", administratorPanel);
		tabbedPane.addTab("Waiter", waiterPanel);

		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setContentPane(tabbedPane);
		double x = screenSize.getWidth() * 3 / 4;
		double y = screenSize.getHeight() * 3 / 4;
		setSize((int) x, (int) y);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static JTable generateMenuTable(List<MenuItem> menuItemList) {
		DefaultTableModel defaultTableModel = new DefaultTableModel(0, 4);
		JTable table = new JTable(defaultTableModel);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		defaultTableModel.setColumnIdentifiers(new String[] { "Id", "Denumire", "Pret", "Gramaj" });

		for (MenuItem menuItem : menuItemList) {
			defaultTableModel.addRow(new Object[] { menuItem.getId(), menuItem.getName(), menuItem.computePrice(),
					menuItem.getWeight() });
		}

		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
		defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		int i = 0;
		int x = table.getColumnCount();
		while (i < x) {
			table.setDefaultRenderer(table.getColumnClass(i), defaultTableCellRenderer);
			i++;
		}

		return table;
	}

	public static JTable generateOrderTable(List<Order> ordersList) {
		DefaultTableModel defaultTableModel = new DefaultTableModel(0, 1);
		JTable table = new JTable(defaultTableModel);

		defaultTableModel.setColumnIdentifiers(new String[] { "Order for Table" });

		for (Order order : ordersList) {
			defaultTableModel.addRow(new Object[] { order.getTable() });
		}

		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
		defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		int i = 0;
		int x = table.getColumnCount();
		while (i < x) {
			table.setDefaultRenderer(table.getColumnClass(i), defaultTableCellRenderer);
			i++;
		}

		return table;
	}

	public void updateMenuTable(List<MenuItem> itemList) {
		waiterPanel.setMenuTable(Main.generateMenuTable(itemList));
		administratorPanel.setMenuTable(Main.generateMenuTable(itemList));
	}

	public void updateOrdersTable(List<Order> orderList) {
		waiterPanel.setOrdersTable(Main.generateOrderTable(orderList));
	}

	

	public static void main(String[] args) {
		String title = "Restaurant Management System";
		Main view = new Main(title);
		view.setVisible(true);
	}
}
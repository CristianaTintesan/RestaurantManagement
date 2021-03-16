package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.IRestaurantProcessing;
import BusinessLayer.MenuItem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class WaiterGraphicalUserInterface extends JPanel {
	private JTable menuTable;
	private JTable ordersTable;

	private JButton placeOrderBtn = new JButton("Place Order");
	private JButton generateBillBtn = new JButton("Generate Bill");

	private JTextArea tableNumberTF = new JTextArea();

	private JLabel tableNumberLabel = new JLabel("Table number: ");

	private JPanel tablePanel;

	private IRestaurantProcessing restaurantProcessing;

	private Main view;

	public WaiterGraphicalUserInterface(IRestaurantProcessing restaurantProcessing, Main view) {
		this.restaurantProcessing = restaurantProcessing;
		this.view = view;

		menuTable = new JTable();
		ordersTable = new JTable();

		tableNumberTF.setColumns(10);

		menuTable.setFillsViewportHeight(true);
		ordersTable.setFillsViewportHeight(true);

		JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		dataPanel.add(tableNumberLabel);
		dataPanel.add(tableNumberTF);

		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
		btnPanel.add(placeOrderBtn);
		btnPanel.add(generateBillBtn);

		JPanel southPanel = new JPanel(new GridLayout(2, 1));
		southPanel.add(dataPanel);
		southPanel.add(btnPanel);

		tablePanel = new JPanel(new GridLayout(1, 2));
		tablePanel.add(menuTable);
		tablePanel.add(ordersTable);

		setLayout(new BorderLayout());
		add(tablePanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

		placeOrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int[] selectedRows = menuTable.getSelectedRows();
				Date date = new Date();
				int id = ordersTable.getRowCount() + 1;
				if (selectedRows.length > 0) {

					ArrayList<MenuItem> itemList = new ArrayList<>();
					for (int row : selectedRows) {
						Object o = menuTable.getValueAt(row, 0);
						String a = o.toString();
						int nr = Integer.parseInt(a);
						itemList.add(restaurantProcessing.getById(nr));
					}
					restaurantProcessing.createNewOrder(getTableNumber(), date, id, itemList);
				} else {
					System.out.println("Selectati randul");
				}
				view.updateOrdersTable(restaurantProcessing.getOrders());
				view.updateMenuTable(restaurantProcessing.getMenu());
			}

		});

		generateBillBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int[] selectedRows = menuTable.getSelectedRows();
				int selectedRow = ordersTable.getSelectedRow();
				Date date = new Date();
				int id = ordersTable.getRowCount();
				ArrayList<MenuItem> itemList = new ArrayList<>();

				for (int row : selectedRows) {
					Object o = menuTable.getValueAt(row, 0);
					String a = o.toString();
					int nr = Integer.parseInt(a);
					itemList.add(restaurantProcessing.getById(nr));
				}

				if (selectedRow == -1) {
					System.out.println("selectati comanda pentru care doriti factura!!!");
				} else {
					double price = 0;
					for (MenuItem m : itemList) {
						price += m.getPrice();
					}
					String tableNb = tableNumberTF.getText();
					restaurantProcessing.generateBill(id, date.toString(), Integer.parseInt(tableNb), price);
				}

				System.out.println("factura a fost generata in fisierul .txt!");

			}

		});
	}

	public void setMenuTable(JTable menuTable) {
		this.menuTable = menuTable;
		JScrollPane scrollPane = new JScrollPane(menuTable);
		tablePanel.add(scrollPane, 0);

		tablePanel.remove(1);
		revalidate();
		repaint();
	}

	public void setOrdersTable(JTable ordersTable) {
		this.ordersTable = ordersTable;
		JScrollPane scrollPane = new JScrollPane(ordersTable);
		tablePanel.add(scrollPane, 1);

		tablePanel.remove(2);
		revalidate();
		repaint();
	}

	public int getTableNumber() throws NumberFormatException {
		return Integer.parseInt(tableNumberTF.getText());
	}

	public void setRestaurantProcessing(IRestaurantProcessing restaurantProcessing) {
		this.restaurantProcessing = restaurantProcessing;
	}

}
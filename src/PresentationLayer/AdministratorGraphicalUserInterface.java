package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.IRestaurantProcessing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdministratorGraphicalUserInterface extends JPanel {

	private JTable menuTable;

	private JButton addMenuItemBtn = new JButton("Add Menu Item");
	private JButton deleteMenuItemBtn = new JButton("Delete Menu Item");
	private JButton updatePriceBtn = new JButton("Update price item");

	private JTextArea idTF = new JTextArea();
	private JTextArea nameTF = new JTextArea();
	private JTextArea priceTF = new JTextArea();
	private JTextArea quantityTF = new JTextArea();

	private JLabel idLabel = new JLabel("Id: ");
	private JLabel nameLabel = new JLabel("Denumire:");
	private JLabel priceLabel = new JLabel("Pret:");
	private JLabel quantityLabel = new JLabel("Gramaj: ");

	private JCheckBox composite;

	private IRestaurantProcessing restaurantProcessing;
	private Main view;

	public AdministratorGraphicalUserInterface(IRestaurantProcessing restaurantProcessing, Main view) {
		this.restaurantProcessing = restaurantProcessing;
		this.view = view;
		menuTable = new JTable();
		composite = new JCheckBox("Composite", false);

		idTF.setColumns(10);
		nameTF.setColumns(10);
		priceTF.setColumns(10);
		quantityTF.setColumns(10);

		menuTable.setFillsViewportHeight(true);

		JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		dataPanel.add(idLabel);
		dataPanel.add(idTF);
		dataPanel.add(nameLabel);
		dataPanel.add(nameTF);
		dataPanel.add(priceLabel);
		dataPanel.add(priceTF);
		dataPanel.add(quantityLabel);
		dataPanel.add(quantityTF);

		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
		btnPanel.add(addMenuItemBtn);
		btnPanel.add(deleteMenuItemBtn);
		btnPanel.add(updatePriceBtn);
		btnPanel.add(composite);

		JPanel southPanel = new JPanel(new GridLayout(2, 1));
		southPanel.add(dataPanel);
		southPanel.add(btnPanel);

		setLayout(new BorderLayout());
		add(menuTable, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

		addMenuItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (composite.isSelected()) {
					int[] selectedRows = menuTable.getSelectedRows();
					if (selectedRows.length == 0) {
						System.out.println("pentru CompositeProduct este nevoie sa fie selecate BaseProduct");
					} else {
						List<MenuItem> itemList = new ArrayList<>();
						for (int row : selectedRows) {
							Object o = menuTable.getValueAt(row, 0);
							String a = o.toString();
							int nr = Integer.parseInt(a);
							itemList.add(restaurantProcessing.getById(nr));
						}

						restaurantProcessing.createMenuItem(new CompositeProduct(nameTF.getText(),
								Integer.parseInt(quantityTF.getText()), Integer.parseInt(idTF.getText()), itemList,
								Double.parseDouble(priceTF.getText())));

					}
				} else {
					restaurantProcessing
							.createMenuItem(new BaseProduct(nameTF.getText(), Integer.parseInt(quantityTF.getText()),
									Integer.parseInt(idTF.getText()), Double.parseDouble(priceTF.getText())));
				}
				view.updateMenuTable(restaurantProcessing.getMenu());
			}

		});

		deleteMenuItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = menuTable.getSelectedRows();
				if (selectedRows.length > 0) {
					for (int row : selectedRows) {
						Object x = menuTable.getValueAt(row, 1);
						String a = x.toString();
						restaurantProcessing.deleteMenuItem(a);
					}
				} else {
					System.out.println("Selectati produsul pe care doriti sa il stergeti!!!");
				}
				view.updateMenuTable(restaurantProcessing.getMenu());
			}

		});

		updatePriceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = menuTable.getSelectedRows();
				if (selectedRows.length > 0) {
					if (composite.isSelected() == false) {
						Object o = menuTable.getValueAt(selectedRows[0], 0);
						String a = o.toString();
						int nr = Integer.parseInt(a);
						BaseProduct toUpdate = (BaseProduct) restaurantProcessing.getById(nr);
						BaseProduct updated = new BaseProduct(nameTF.getText(), Integer.parseInt(quantityTF.getText()),
								Integer.parseInt(idTF.getText()), Double.parseDouble(priceTF.getText()));
						updated.setId(toUpdate.getId());

						restaurantProcessing.updateMenuItem(updated);
						view.updateMenuTable(restaurantProcessing.getMenu());
					} else {
						Object o = menuTable.getValueAt(selectedRows[0], 0);
						String a = o.toString();
						int nr = Integer.parseInt(a);
						CompositeProduct toUpdate = (CompositeProduct) restaurantProcessing.getById(nr);
						CompositeProduct updated = new CompositeProduct(nameTF.getText(),
								Integer.parseInt(quantityTF.getText()), Integer.parseInt(idTF.getText()),
								toUpdate.getMenuItems(), Double.parseDouble(priceTF.getText()));
						updated.setId(toUpdate.getId());

						restaurantProcessing.updateMenuItem(updated);
					}
				}

				else {
					System.out.println("Selectati produsul pe care doriti sa il actualizati!!!");
				}

			}
		});

	}

	public void setMenuTable(JTable menuTable) {
		this.menuTable = menuTable;
		JScrollPane scrollPane = new JScrollPane(menuTable);
		add(scrollPane, BorderLayout.CENTER, 0);

		remove(1);
		revalidate();
		repaint();
	}

	public void setRestaurantProcessing(IRestaurantProcessing restaurantProcessing) {
		this.restaurantProcessing = restaurantProcessing;
	}

}

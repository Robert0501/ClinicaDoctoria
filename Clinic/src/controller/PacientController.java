package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.PatternSyntaxException;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.Database;
import view.NewPacientView;
import view.PacientDetailView;
import view.PacientView;

public class PacientController {

	public static String cnp;
	public static String name;

	public PacientController() {
		addPacientButton();
		deletePacientButton();
		viewPacientDetails();

		searchField();
	}

	private void addPacientButton() {
		PacientView.addPacientButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new NewPacientView();
			}
		});
	}

	private void getSelectedPacient() {
		PacientView.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (PacientView.table.getSelectedRow() != -1) {
					PacientView.deletePacientButton.setEnabled(true);
					PacientView.showPacientDetailsButton.setEnabled(true);
					cnp = PacientView.table.getValueAt(PacientView.table.getSelectedRow(), 2).toString();
					name = PacientView.table.getValueAt(PacientView.table.getSelectedRow(), 0).toString() + " "
							+ PacientView.table.getValueAt(PacientView.table.getSelectedRow(), 1).toString();
				}
			}
		});
	}

	private void deletePacientButton() {
		getSelectedPacient();
		PacientView.deletePacientButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + name + "?",
						"Delete Pacient", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					Database.deletePacient(cnp, LoginController.loggedInEmail);

					if (PacientView.table.getSelectedRow() != -1) {
						PacientView.model.removeRow(PacientView.table.getSelectedRow());
					}
				}
			}
		});
	}

	private void viewPacientDetails() {
		getSelectedPacient();
		PacientView.showPacientDetailsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new PacientDetailView();
			}
		});
	}

	private void searchField() {
		try {
			PacientView.searchField.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					String text = PacientView.searchField.getText();
					if (text.trim().length() == 0) {
						PacientView.rowSorter.setRowFilter(null);
					} else {
						PacientView.rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
					}
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					String text = PacientView.searchField.getText();
					if (text.trim().length() == 0) {
						PacientView.rowSorter.setRowFilter(null);
					} else {
						PacientView.rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
					}
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					throw new UnsupportedOperationException("Not supported yet."); // To changebody of
																					// generated // methods,
																					// choose Tools | Templates.
				}

			});
		} catch (PatternSyntaxException e) {
			PacientView.searchField.setText("");
		}

	}
}
package controller_doctor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.PatternSyntaxException;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller_unlogin.LoginController;
import database.Database;
import helper.ViewClass;
import view_doctor.NewPacientView;
import view_doctor.PacientDetailView;
import view_doctor.PacientView;

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
				if (!ViewClass.newPacientView) {
					new NewPacientView();
					ViewClass.newPacientView = true;
				} else {
					NewPacientView.newPacientFrame.setVisible(true);
				}
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
					System.out.println(cnp + "  " + name);
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
					if (PacientView.table.getSelectedRow() != -1) {
						System.out.println(PacientView.table.getSelectedRow());
						// Database.deletePacient(cnp, LoginController.loggedInEmail);
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
				if (!ViewClass.pacientDetailView) {
					new PacientDetailView();
					ViewClass.pacientDetailView = true;
				} else {
					PacientDetailController.autocompleteMedicalResults();
					PacientDetailController.autocompletePersonalDetailsField();
					PacientDetailController.colorWrongValues();
					PacientDetailView.pacientDetailsFrame.setVisible(true);
				}
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

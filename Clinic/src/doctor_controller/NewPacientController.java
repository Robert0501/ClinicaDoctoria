package doctor_controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import org.postgresql.util.PSQLException;

import database.Database;
import doctor_view.NewPacientView;
import doctor_view.PacientView;
import email.Email;
import helper.Code;
import model.User;
import regex.RegEx;

public class NewPacientController {

	public static boolean pacientExist = false;

	public NewPacientController() {
		addNewPacientButton();
	}

	private boolean checkNameFields() {
		if (NewPacientView.firstNameIn.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "First Name cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			System.out.println("First");
			return false;
		}

		if (NewPacientView.lastNameIn.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Last Name cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			System.out.println("Second");
			return false;
		}

		return true;
	}

	private boolean checkPersonalDetailsFields() {
		if (NewPacientView.cnpIn.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "CNP cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			System.out.println("CNP");
			return false;
		}
		if (!RegEx.checkCNP(NewPacientView.cnpIn.getText())) {
			JOptionPane.showMessageDialog(null, "CNP is not correct", "Alert", JOptionPane.ERROR_MESSAGE);
			System.out.println("CNP FORMAT");
			return false;
		}
		if (NewPacientView.dateOfBirthIn.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Date of Birth cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			System.out.println("DOB");
			return false;
		}
		if (!RegEx.checkDateOfBirth(NewPacientView.dateOfBirthIn.getText())) {
			JOptionPane.showMessageDialog(null, "Date of Birth format is not correct", "Alert",
					JOptionPane.ERROR_MESSAGE);
			System.out.println("DOB FORMAT");
			return false;
		}

		return true;

	}

	private boolean checkContactFields() {
		if (NewPacientView.phoneNumberIn.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Phone Number cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			System.out.println("Phone");
			return false;
		}
		if (!RegEx.checkPhoneNumber(NewPacientView.phoneNumberIn.getText())) {
			JOptionPane.showMessageDialog(null, "Phone Number format is not correct", "Alert",
					JOptionPane.ERROR_MESSAGE);
			System.out.println("Phone Format");
			return false;
		}

		if (NewPacientView.emailIn.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Email cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			System.out.println("email");
			return false;
		}
		if (!RegEx.checkEmail(NewPacientView.emailIn.getText())) {
			JOptionPane.showMessageDialog(null, "Email format is not correct", "Alert", JOptionPane.ERROR_MESSAGE);
			System.out.println("Email format");
			return false;
		}

		return true;

	}

	private boolean checkAddressFields() {
		if (NewPacientView.countryIn.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Country cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			System.out.println("Country");
			return false;
		}
		if (NewPacientView.addressIn.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Address cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			System.out.println("Address");
			return false;
		}

		return true;

	}

	private void addDataToTable() {
		PacientView.model
				.addRow(new Object[] { NewPacientView.firstNameIn.getText(), NewPacientView.lastNameIn.getText(),
						NewPacientView.cnpIn.getText(), NewPacientView.dateOfBirthIn.getText(),
						NewPacientView.emailIn.getText(), NewPacientView.phoneNumberIn.getText(),
						NewPacientView.countryIn.getText(), NewPacientView.addressIn.getText() });
	}

	private void addDataToDatabase() throws PSQLException {
		Database.insertIntoPacientTable(NewPacientView.firstNameIn.getText(), NewPacientView.lastNameIn.getText(),
				NewPacientView.cnpIn.getText(), NewPacientView.dateOfBirthIn.getText(),
				NewPacientView.emailIn.getText(), NewPacientView.phoneNumberIn.getText(),
				NewPacientView.countryIn.getText(), NewPacientView.addressIn.getText());

		Database.insertIntoMedicalTestTable("0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", NewPacientView.cnpIn.getText());
	}

	private void createNewUser() {
		User user = new User(NewPacientView.emailIn.getText(),
				NewPacientView.firstNameIn.getText() + NewPacientView.lastNameIn.getText(),
				Code.generateActivationCode());
		Database.insertIntoUserTable(user, false);
		Email.index = 4;
		Email.email[Email.position].start();
	}

	private void clearFields() {
		NewPacientView.firstNameIn.setText("");
		NewPacientView.lastNameIn.setText("");
		NewPacientView.cnpIn.setText("");
		NewPacientView.dateOfBirthIn.setText("");
		NewPacientView.emailIn.setText("");
		NewPacientView.phoneNumberIn.setText("");
		NewPacientView.countryIn.setText("");
		NewPacientView.addressIn.setText("");
	}

	private void addNewPacientButton() {
		NewPacientView.addPacientButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (checkNameFields()) {
					if (checkPersonalDetailsFields()) {
						if (checkContactFields()) {
							if (checkAddressFields()) {
								try {
									addDataToDatabase();
								} catch (PSQLException e1) {
									e1.printStackTrace();
								}
								if (pacientExist) {
									pacientExist = false;
								} else {
									addDataToTable();
									createNewUser();
									JOptionPane.showMessageDialog(null,
											NewPacientView.firstNameIn.getText() + " "
													+ NewPacientView.lastNameIn.getText() + " was successfully added",
											"Pacient successfully added", JOptionPane.INFORMATION_MESSAGE);
									NewPacientView.newPacientFrame.setVisible(false);
									clearFields();
								}
							}

						}
					}
				}
			}

		});
	}

}

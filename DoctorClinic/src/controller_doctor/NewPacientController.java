package controller_doctor;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import org.postgresql.util.PSQLException;

import database.Database;
import database.InsertDatabase;
import email.Email;
import helper.Code;
import model.User;
import regex.RegEx;
import view_doctor.NewPacientView;
import view_doctor.PacientView;

public class NewPacientController {

	public static boolean pacientExist = false;

	public NewPacientController() {
		addNewPacientButton();
		insertDOB();
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

	private void insertDOB() {
		NewPacientView.cnpIn.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (RegEx.checkCNP(NewPacientView.cnpIn.getText())) {
					String cnp = NewPacientView.cnpIn.getText();
					String day = cnp.charAt(5) + "" + cnp.charAt(6);
					String month = cnp.charAt(3) + "" + cnp.charAt(4);
					String year;
					if (cnp.charAt(0) == '1' || cnp.charAt(0) == '2') {
						year = "19" + cnp.charAt(1) + "" + cnp.charAt(2);
					} else {
						year = "20" + cnp.charAt(1) + "" + cnp.charAt(2);
					}
					NewPacientView.dateOfBirthIn.setText(day + "." + month + "." + year);
					NewPacientView.dateOfBirthIn.setEnabled(false);

				}
			}
		});
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
		if (Database.checkDuplicateEmail(NewPacientView.emailIn.getText())) {
			JOptionPane.showMessageDialog(null, "There is already a patient added with this email", "Alert",
					JOptionPane.ERROR_MESSAGE);
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

	private static String getCurrentYear() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
		LocalDateTime now = LocalDateTime.now();

		return dtf.format(now);
	}

	private static String getCurrentMonth() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
		LocalDateTime now = LocalDateTime.now();

		return dtf.format(now);
	}

	private static String getCurrentDay() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
		LocalDateTime now = LocalDateTime.now();

		return dtf.format(now);
	}

	private void addDataToDatabase() throws PSQLException {
		InsertDatabase.insertIntoPacientTable(NewPacientView.firstNameIn.getText(), NewPacientView.lastNameIn.getText(),
				NewPacientView.cnpIn.getText(), NewPacientView.dateOfBirthIn.getText(),
				NewPacientView.emailIn.getText(), NewPacientView.phoneNumberIn.getText(),
				NewPacientView.countryIn.getText(), NewPacientView.addressIn.getText(),
				DoctorDashboardController.getMonth(Integer.parseInt(getCurrentMonth())), getCurrentYear(),
				getCurrentDay());

		InsertDatabase.insertIntoMedicalTestTable("0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", NewPacientView.cnpIn.getText());
	}

	private void createNewUser() {
		User user = new User(NewPacientView.emailIn.getText(),
				NewPacientView.firstNameIn.getText() + NewPacientView.lastNameIn.getText(),
				Code.generateActivationCode());
		InsertDatabase.insertIntoUserTable(user, false);
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

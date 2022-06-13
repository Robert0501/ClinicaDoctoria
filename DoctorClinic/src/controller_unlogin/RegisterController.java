package controller_unlogin;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import database.Database;
import email.Email;
import helper.Code;
import helper.ViewClass;
import model.Doctor;
import model.User;
import regex.RegEx;
import view_unlogin.AddressRegisterView;
import view_unlogin.LoginView;
import view_unlogin.RegisterView;

public class RegisterController {

	private String code;

	public RegisterController() {
		code = Code.generateActivationCode();
		registerButton();
		loginButton();

	}

	private void registerButton() {
		RegisterView.registerButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				succesffullyRegistered();
			}
		});

		RegisterView.rePasswordIn.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					succesffullyRegistered();
				}
			}
		});

	}

	private void addressRegisterButton() {
		AddressRegisterView.registerButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				successfullyAddressRegistered();
			}
		});

		AddressRegisterView.addressIn.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					successfullyAddressRegistered();
				}
			}
		});
	}

	private void successfullyAddressRegistered() {
		if (checkAddressRegister()) {
			addDoctorToDatabase();
			addUserToDatabase();
			Email.index = 1;
			Email.email[Email.position].start();
			JOptionPane.showMessageDialog(null,
					"You have been successfully registered. Check your email for the confirmation code.\nNext time you will login, the code will be required.",
					"Register Successfully", JOptionPane.INFORMATION_MESSAGE);
			AddressRegisterView.addressRegisterFrame.setVisible(false);
			LoginView.setVisibility(true);
			setAllFieldsToEmpty();
		}
	}

	private void succesffullyRegistered() {
		if (checkRegister()) {

			RegisterView.registerFrame.dispose();
			if (!ViewClass.addressRegisterView) {
				new AddressRegisterView();
				ViewClass.addressRegisterView = true;
			} else {
				AddressRegisterView.addressRegisterFrame.setVisible(true);
			}
			addressRegisterButton();

		}
	}

	private boolean checkEmptyField(String input) {
		if (input.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkAddressRegister() {
		if (checkEmptyField(AddressRegisterView.getClinicNameIn())) {
			JOptionPane.showMessageDialog(null, "Clinic Name cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (checkEmptyField(AddressRegisterView.getCountryIn())) {
			JOptionPane.showMessageDialog(null, "Country cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (checkEmptyField(AddressRegisterView.getCityIn())) {
			JOptionPane.showMessageDialog(null, "City cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (checkEmptyField(AddressRegisterView.getStreetIn())) {
			JOptionPane.showMessageDialog(null, "Street cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (checkEmptyField(AddressRegisterView.getAddressIn())) {
			JOptionPane.showMessageDialog(null, "Address cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private boolean checkRegister() {
		if (checkEmptyField(RegisterView.getFirstName())) {
			JOptionPane.showMessageDialog(null, "First Name cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (checkEmptyField(RegisterView.getLastName())) {
			JOptionPane.showMessageDialog(null, "Last Name cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (checkEmptyField(RegisterView.getEmail())) {
			JOptionPane.showMessageDialog(null, "Email cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!RegEx.checkEmail(RegisterView.getEmail())) {
			JOptionPane.showMessageDialog(null, "You should enter a valid email", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (checkEmptyField(RegisterView.getPassword())) {
			JOptionPane.showMessageDialog(null, "Password cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!RegEx.checkPassword(RegisterView.getPassword())) {
			JOptionPane.showMessageDialog(null,
					"Passowrd should contains:\n- At Least 8 Characters\n- 1 Special Character\n- 1 Capital Letter",
					"Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (checkEmptyField(RegisterView.getRePassword())) {
			JOptionPane.showMessageDialog(null, "Confirm Passowrd cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!RegisterView.getPassword().equals(RegisterView.getRePassword())) {
			JOptionPane.showMessageDialog(null, "Passwords do not match", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (Database.checkDuplicateEmail(RegisterView.getEmail())) {
			JOptionPane.showMessageDialog(null, "There is already an account created with this email", "Alert",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void addDoctorToDatabase() {
		String address = AddressRegisterView.getCountryIn() + " " + AddressRegisterView.getCityIn() + " "
				+ AddressRegisterView.getStreetIn() + " " + AddressRegisterView.getAddressIn();
		Database.insertIntoDoctorTABLE(new Doctor(RegisterView.getFirstName(), RegisterView.getLastName(),
				RegisterView.getEmail(), AddressRegisterView.getClinicNameIn()), address);
	}

	private void addUserToDatabase() {
		Database.insertIntoUserTable(new User(RegisterView.getEmail(), RegisterView.getPassword(), code), true);
	}

	private void loginButton() {
		RegisterView.loginButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setAllFieldsToEmpty();
				RegisterView.registerFrame.dispose();
				LoginView.setVisibility(true);
			}
		});
	}

	private void setAllFieldsToEmpty() {
		RegisterView.setFirstName("");
		RegisterView.setLastName("");
		RegisterView.setEmail("");
		RegisterView.setPassword("");
		RegisterView.setRePassword("");
	}

}

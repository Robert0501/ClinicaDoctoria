package view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.NewPacientController;

public class NewPacientView {

	public static JFrame newPacientFrame;
	public static JPanel namePanel;
	public static JPanel personalDataPanel;
	public static JPanel contactPanel;
	public static JPanel addressPanel;

	private JLabel firstName;
	private JLabel lastName;
	private JLabel cnp;
	private JLabel dateOfBirth;
	private JLabel email;
	private JLabel phoneNumber;
	private JLabel country;
	private JLabel address;

	public static JTextField firstNameIn;
	public static JTextField lastNameIn;
	public static JTextField cnpIn;
	public static JTextField dateOfBirthIn;
	public static JTextField emailIn;
	public static JTextField phoneNumberIn;
	public static JTextField countryIn;
	public static JTextField addressIn;

	public static JButton addPacientButton;

	private int panelWidth = 10;
	private int height = 50;

	private boolean isUsed = false;

	public NewPacientView() {
		newPacientFrame();
		createPanels();
		createLabels();
		createFields();
		createForm();
		addPacientButton();
		
		new NewPacientController();
	}

	private void newPacientFrame() {
		newPacientFrame = new JFrame("Add New Pacient");
		newPacientFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		newPacientFrame.pack();
		newPacientFrame.setSize(new Dimension(720, 500));
		newPacientFrame.setLocationRelativeTo(null);
		newPacientFrame.setLayout(null);
		newPacientFrame.setVisible(true);
	}

	private void createPanels() {
		namePanel = new JPanel();
		personalDataPanel = new JPanel();
		contactPanel = new JPanel();
		addressPanel = new JPanel();
	}

	private void createLabels() {
		firstName = new JLabel("First Name");
		lastName = new JLabel("Last Name");
		cnp = new JLabel("CNP");
		dateOfBirth = new JLabel("Date of Birth");
		email = new JLabel("Email");
		phoneNumber = new JLabel("Phone Number");
		country = new JLabel("Country");
		address = new JLabel("Address");
	}

	private void createFields() {
		firstNameIn = new JTextField();
		lastNameIn = new JTextField();
		cnpIn = new JTextField();
		dateOfBirthIn = new JTextField();
		emailIn = new JTextField();
		phoneNumberIn = new JTextField();
		countryIn = new JTextField();
		addressIn = new JTextField();
	}

	private void createForm() {
		addSections(namePanel, "Name");
		addFields(firstName, firstNameIn, namePanel);
		addFields(lastName, lastNameIn, namePanel);

		addSections(personalDataPanel, "Personal Data");
		addFields(cnp, cnpIn, personalDataPanel);
		addFields(dateOfBirth, dateOfBirthIn, personalDataPanel);

		addSections(contactPanel, "Contact");
		addFields(phoneNumber, phoneNumberIn, contactPanel);
		addFields(email, emailIn, contactPanel);

		addSections(addressPanel, "Address");
		addFields(country, countryIn, addressPanel);
		addFields(address, addressIn, addressPanel);
	}

	private void addPacientButton() {
		addPacientButton = new JButton("Add Pacient");
		addPacientButton.setBounds(300, 425, 100, 30);
		newPacientFrame.add(addPacientButton);
	}

	private void addSections(JPanel panel, String name) {
		panel.setBounds(10, panelWidth, 680, 100);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder(name));
		panel.setVisible(true);

		panelWidth += 100;
		newPacientFrame.add(panel);
	}

	private void addFields(JLabel label, JTextField text, JPanel panel) {
		label.setBounds(height, 20, 100, 20);
		text.setBounds(height, 45, 250, 25);
		panel.add(label);
		panel.add(text);

		if (isUsed) {
			isUsed = false;
			height = 50;
		} else {
			isUsed = true;
			height = 390;
		}
	}

}

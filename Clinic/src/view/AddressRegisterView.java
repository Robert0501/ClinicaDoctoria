package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.RegisterController;

public class AddressRegisterView {
	public static JFrame addressRegisterFrame;

	private JLabel registerLabel;

	private JLabel clinicName;
	private static JTextField clinicNameIn;

	private JLabel country;
	private static JTextField countryIn;

	private JLabel city;
	private static JTextField cityIn;

	private JLabel street;
	private static JTextField streetIn;

	private JLabel address;
	public static JTextField addressIn;

	public static JButton registerButton;

	private JLabel background;
	private JLabel doctorLogo;

	public AddressRegisterView() {
		registerFrame();
		titleLabel();
		clinicName();
		country();
		city();
		street();
		address();
		registerButton();

		logo();
		background();

		new RegisterController();
	}

	public static String getClinicNameIn() {
		return clinicNameIn.getText();
	}

	public static String getCountryIn() {
		return countryIn.getText();
	}

	public static String getCityIn() {
		return cityIn.getText();
	}

	public static String getStreetIn() {
		return streetIn.getText();
	}

	public static String getAddressIn() {
		return addressIn.getText();
	}

	private void registerFrame() {
		addressRegisterFrame = new JFrame("Register");
		addressRegisterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addressRegisterFrame.pack();
		addressRegisterFrame.setSize(new Dimension(800, 600));
		addressRegisterFrame.setLocationRelativeTo(null);
		addressRegisterFrame.setLayout(null);
		addressRegisterFrame.setVisible(true);
	}

	private void titleLabel() {
		registerLabel = new JLabel("Register", SwingConstants.CENTER);
		registerLabel.setBounds(0, 10, 800, 40);
		registerLabel.setFont(new Font("Arial", Font.BOLD, 35));
		addressRegisterFrame.add(registerLabel);
	}

	private void clinicName() {
		clinicName = new JLabel("Clinic Name", SwingConstants.CENTER);
		clinicName.setBounds(0, 70, 300, 30);
		clinicName.setFont(new Font("Arial", Font.BOLD, 20));
		clinicName.setForeground(Color.white);

		clinicNameIn = new JTextField();
		clinicNameIn.setBounds(25, 100, 250, 30);

		addressRegisterFrame.add(clinicName);
		addressRegisterFrame.add(clinicNameIn);
	}

	private void country() {
		country = new JLabel("Country", SwingConstants.CENTER);
		country.setBounds(0, 150, 300, 30);
		country.setFont(new Font("Arial", Font.BOLD, 20));
		country.setForeground(Color.white);

		countryIn = new JTextField();
		countryIn.setBounds(25, 180, 250, 30);

		addressRegisterFrame.add(country);
		addressRegisterFrame.add(countryIn);
	}

	private void city() {
		city = new JLabel("City", SwingConstants.CENTER);
		city.setBounds(0, 230, 300, 30);
		city.setFont(new Font("Arial", Font.BOLD, 20));
		city.setForeground(Color.white);

		cityIn = new JTextField();
		cityIn.setBounds(25, 260, 250, 30);

		addressRegisterFrame.add(city);
		addressRegisterFrame.add(cityIn);
	}

	private void street() {
		street = new JLabel("Street", SwingConstants.CENTER);
		street.setBounds(0, 310, 300, 30);
		street.setFont(new Font("Arial", Font.BOLD, 20));
		street.setForeground(Color.white);

		streetIn = new JTextField();
		streetIn.setBounds(25, 340, 250, 30);

		addressRegisterFrame.add(street);
		addressRegisterFrame.add(streetIn);
	}

	private void address() {
		address = new JLabel("Address", SwingConstants.CENTER);
		address.setBounds(0, 390, 300, 30);
		address.setFont(new Font("Arial", Font.BOLD, 20));
		address.setForeground(Color.white);

		addressIn = new JTextField();
		addressIn.setBounds(25, 420, 250, 30);

		addressRegisterFrame.add(address);
		addressRegisterFrame.add(addressIn);
	}

	private void registerButton() {
		registerButton = new JButton("Register");
		registerButton.setBounds(100, 465, 100, 30);
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addressRegisterFrame.add(registerButton);
	}

	private void background() {
		background = new JLabel();
		background.setBounds(0, 0, 800, 600);
		ImageIcon img = new ImageIcon("src//Images//Background//registerBackground.jpeg");
		background.setIcon(img);
		addressRegisterFrame.add(background);
	}

	private void logo() {
		doctorLogo = new JLabel();
		doctorLogo.setBounds(550, 60, 250, 255);
		ImageIcon img = new ImageIcon("src//Images//Icon//doctorLogo.png");
		doctorLogo.setIcon(img);
		addressRegisterFrame.add(doctorLogo);
	}
}

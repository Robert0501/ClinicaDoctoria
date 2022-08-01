package view_unlogin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller_unlogin.RegisterController;

public class RegisterView {

	public static JFrame registerFrame;

	private JLabel registerLabel;
	public static JLabel loginButton;

	private JLabel firstNameLabel;
	private static JTextField firstNameIn;

	private JLabel lastNameLabel;
	private static JTextField lastNameIn;

	private JLabel emailLabel;
	private static JTextField emailIn;

	private JLabel passwordLabel;
	private static JPasswordField passwordIn;

	private JLabel rePasswordLabel;
	public static JPasswordField rePasswordIn;

	public static JButton registerButton;

	private JLabel background;
	private JLabel doctorLogo;

	public RegisterView() {
		registerFrame();
		titleLabel();
		firstName();
		lastName();
		email();
		password();
		rePassword();
		registerButton();
		loginButton();

		logo();
		background();

		new RegisterController();
	}

	public static String getFirstName() {
		return firstNameIn.getText();
	}

	public static String getLastName() {
		return lastNameIn.getText();
	}

	public static String getEmail() {
		return emailIn.getText();
	}

	@SuppressWarnings("deprecation")
	public static String getPassword() {
		return passwordIn.getText();
	}

	@SuppressWarnings("deprecation")
	public static String getRePassword() {
		return rePasswordIn.getText();
	}

	public static void setFirstName(String message) {
		firstNameIn.setText(message);
	}

	public static void setLastName(String message) {
		lastNameIn.setText(message);
	}

	public static void setEmail(String message) {
		emailIn.setText(message);
	}

	public static void setPassword(String message) {
		passwordIn.setText(message);
	}

	public static void setRePassword(String message) {
		rePasswordIn.setText(message);
	}

	private void registerFrame() {
		registerFrame = new JFrame("Register");
		registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerFrame.pack();
		registerFrame.setSize(new Dimension(800, 600));
		registerFrame.setLocationRelativeTo(null);
		registerFrame.setLayout(null);
		registerFrame.setVisible(true);
	}

	private void titleLabel() {
		registerLabel = new JLabel("Register", SwingConstants.CENTER);
		registerLabel.setBounds(0, 10, 800, 40);
		registerLabel.setFont(new Font("Arial", Font.BOLD, 35));
		registerFrame.add(registerLabel);
	}

	private void firstName() {
		firstNameLabel = new JLabel("First Name", SwingConstants.CENTER);
		firstNameLabel.setBounds(0, 70, 300, 30);
		firstNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
		firstNameLabel.setForeground(Color.white);

		firstNameIn = new JTextField();
		firstNameIn.setBounds(25, 100, 250, 30);

		registerFrame.add(firstNameLabel);
		registerFrame.add(firstNameIn);
	}

	private void lastName() {
		lastNameLabel = new JLabel("Last Name", SwingConstants.CENTER);
		lastNameLabel.setBounds(0, 150, 300, 30);
		lastNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lastNameLabel.setForeground(Color.white);

		lastNameIn = new JTextField();
		lastNameIn.setBounds(25, 180, 250, 30);

		registerFrame.add(lastNameLabel);
		registerFrame.add(lastNameIn);
	}

	private void email() {
		emailLabel = new JLabel("Email", SwingConstants.CENTER);
		emailLabel.setBounds(0, 230, 300, 30);
		emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
		emailLabel.setForeground(Color.white);

		emailIn = new JTextField();
		emailIn.setBounds(25, 260, 250, 30);

		registerFrame.add(emailLabel);
		registerFrame.add(emailIn);
	}

	private void password() {
		passwordLabel = new JLabel("Password", SwingConstants.CENTER);
		passwordLabel.setBounds(0, 310, 300, 30);
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
		passwordLabel.setForeground(Color.white);

		passwordIn = new JPasswordField();
		passwordIn.setBounds(25, 340, 250, 30);

		registerFrame.add(passwordLabel);
		registerFrame.add(passwordIn);
	}

	private void rePassword() {
		rePasswordLabel = new JLabel("Confirm Passowrd", SwingConstants.CENTER);
		rePasswordLabel.setBounds(0, 390, 300, 30);
		rePasswordLabel.setFont(new Font("Arial", Font.BOLD, 20));
		rePasswordLabel.setForeground(Color.white);

		rePasswordIn = new JPasswordField();
		rePasswordIn.setBounds(25, 420, 250, 30);

		registerFrame.add(rePasswordLabel);
		registerFrame.add(rePasswordIn);
	}

	private void registerButton() {
		registerButton = new JButton("Next");
		registerButton.setBounds(100, 465, 100, 30);
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerFrame.add(registerButton);
	}

	private void loginButton() {
		loginButton = new JLabel("<HTML><U>Already have an account?</U><B> Login</B></HTML>", SwingConstants.CENTER);
		loginButton.setBounds(0, 500, 300, 30);
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setFont(new Font("Arial", Font.PLAIN, 13));
		loginButton.setForeground(Color.white);
		registerFrame.add(loginButton);
	}

	private void background() {
		background = new JLabel();
		background.setBounds(0, 0, 800, 600);
		ImageIcon img = new ImageIcon("src//Images//Background//registerBackground.jpeg");
		background.setIcon(img);
		registerFrame.add(background);
	}

	private void logo() {
		doctorLogo = new JLabel();
		doctorLogo.setBounds(550, 60, 250, 255);
		ImageIcon img = new ImageIcon("src//Images//Icon//doctorLogo.png");
		doctorLogo.setIcon(img);
		registerFrame.add(doctorLogo);
	}

}

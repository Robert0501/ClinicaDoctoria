package doctor_view;

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

import doctor_controller.ForgotPasswordController;
import doctor_controller.LoginController;

public class LoginView {

	public static JFrame loginFrame;

	private JLabel loginLabel;
	public static JLabel newAccountLabel;
	public static JLabel forgotPasswordLabel;

	private JLabel emailLabel;
	private static JTextField emailIn;

	private JLabel passwordLabel;
	public static JPasswordField passwordIn;

	public static JButton loginButton;

	private JLabel background;
	private JLabel doctorLogo;

	public LoginView() {
		loginFrame();
		titleLabel();
		email();
		password();
		loginButton();
		newAccountLabel();
		forgotPassword();
		logo();
		background();

		loginFrame.setSize(new Dimension(641, 480));

		new LoginController();
		new ForgotPasswordController();
	}

	public static String getEmail() {
		return emailIn.getText();
	}

	@SuppressWarnings("deprecation")
	public static String getPassword() {
		return passwordIn.getText();
	}

	public static void setEmail(String message) {
		emailIn.setText(message);
	}

	public static void setPassword(String message) {
		passwordIn.setText(message);
	}

	public static void setVisibility(boolean value) {
		loginFrame.setVisible(value);
	}

	private void loginFrame() {
		loginFrame = new JFrame("Login");
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.pack();
		loginFrame.setSize(new Dimension(640, 480));
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setLayout(null);
		loginFrame.setVisible(true);
	}

	private void titleLabel() {
		loginLabel = new JLabel("Login", SwingConstants.CENTER);
		loginLabel.setBounds(100, 30, 530, 40);
		loginLabel.setFont(new Font("Arial", Font.BOLD, 30));
		loginFrame.add(loginLabel);
	}

	private void email() {
		emailLabel = new JLabel("Email", SwingConstants.CENTER);
		emailLabel.setBounds(0, 110, 300, 30);
		emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
		emailLabel.setForeground(Color.white);

		emailIn = new JTextField();
		emailIn.setBounds(30, 140, 250, 30);

		loginFrame.add(emailLabel);
		loginFrame.add(emailIn);
	}

	private void password() {
		passwordLabel = new JLabel("Password", SwingConstants.CENTER);
		passwordLabel.setBounds(0, 190, 300, 30);
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
		passwordLabel.setForeground(Color.white);

		passwordIn = new JPasswordField();
		passwordIn.setBounds(30, 220, 250, 30);

		loginFrame.add(passwordLabel);
		loginFrame.add(passwordIn);
	}

	private void loginButton() {
		loginButton = new JButton("Login");
		loginButton.setBounds(105, 265, 100, 30);
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginFrame.add(loginButton);
	}

	private void newAccountLabel() {
		newAccountLabel = new JLabel("<HTML><U>Don't you have an account?</U><B> Click Here</B></HTML>",
				SwingConstants.CENTER);
		newAccountLabel.setForeground(Color.white);
		newAccountLabel.setBounds(0, 315, 300, 20);
		newAccountLabel.setFont(new Font("Arial", Font.ITALIC, 13));
		newAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginFrame.add(newAccountLabel);
	}

	private void forgotPassword() {
		forgotPasswordLabel = new JLabel("<HTML><B>Forgot Password?</B></HTML>", SwingConstants.CENTER);
		forgotPasswordLabel.setForeground(Color.WHITE);
		forgotPasswordLabel.setBounds(0, 340, 300, 20);
		forgotPasswordLabel.setFont(new Font("Arial", Font.BOLD, 13));
		forgotPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginFrame.add(forgotPasswordLabel);
	}

	private void background() {
		background = new JLabel();
		background.setBounds(0, 0, 800, 600);
		ImageIcon img = new ImageIcon("src//Images//Background//registerBackground.jpeg");
		background.setIcon(img);
		loginFrame.add(background);
	}

	private void logo() {
		doctorLogo = new JLabel();
		doctorLogo.setBounds(480, 45, 250, 255);
		ImageIcon img = new ImageIcon("src//Images//Icon//doctorLogo.png");
		doctorLogo.setIcon(img);
		loginFrame.add(doctorLogo);
	}

}

package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.DoctorController;
import controller.LoginController;
import controller.ProfileController;
import database.Database;

public class ProfileView {
	public static JPanel profilePanel;

	private JPanel photoPanel;
	public static JLabel profilePhoto;

	private JLabel titleLabel;
	public static JLabel nameLabel = new JLabel();
	public static JLabel addressLabel = new JLabel();
	public static JLabel emailLabel = new JLabel();
	public static JLabel clinicName = new JLabel();

	public static JButton changeNameButton = new JButton("Change Name");
	public static JButton changePhotoButton = new JButton("Change Photo");
	public static JButton changePasswordButton = new JButton("Change Password");
	public static JButton changeAddressButton = new JButton("Change Address");
	public static JButton changePhoneNumberButton = new JButton("Change Clinic Name");

	private int labelHeight = 300;
	private int buttonHeight = 100;

	public ProfileView() {
		profilePanel();
		photoPanel();
		titleLabel();
		addLabel(nameLabel);
		addLabel(clinicName);
		addLabel(addressLabel);
		addLabel(emailLabel);
		addButton(changePhotoButton, "Change Photo");
		addButton(changeNameButton, "Change Name");
		addButton(changePasswordButton, "Change Password");
		addButton(changeAddressButton, "Change Address");
		addButton(changePhoneNumberButton, "Change Phone No");

		new ProfileController();
	}

	private void profilePanel() {
		profilePanel = new JPanel();
		profilePanel.setLayout(null);
		profilePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		profilePanel.setVisible(true);
		profilePanel.setBackground(Color.LIGHT_GRAY);
		DoctorView.doctorFrame.add(profilePanel);
	}

	private void photoPanel() {
		photoPanel = new JPanel();
		photoPanel.setVisible(true);
		photoPanel.setBounds(100, 100, 250, 180);
		profilePanel.add(photoPanel);

		profilePhoto();
	}

	private void profilePhoto() {
		profilePhoto = new JLabel();
		profilePhoto.setIcon(DoctorController
				.resizeImageIcon(new ImageIcon(Database.getProfilePhotoPath(LoginController.loggedInEmail))));
		photoPanel.add(profilePhoto);
	}

	private void titleLabel() {
		titleLabel = new JLabel("Profile");
		titleLabel.setBounds(0, 30, 980, 55);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		profilePanel.add(titleLabel);
	}

	private void addLabel(JLabel label) {
		label.setBounds(100, labelHeight, 980, 25);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		profilePanel.add(label);

		labelHeight += 50;
	}

	private void addButton(JButton button, String buttonName) {
		button.setFont(new Font("Tahoma", Font.BOLD, 20));
		button.setBounds(650, buttonHeight, 250, 50);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		profilePanel.add(button);

		buttonHeight += 100;
	}

}

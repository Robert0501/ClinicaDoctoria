package view_patient;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller_doctor.DoctorPerspectiveController;
import controller_patient.PatientProfileController;
import controller_unlogin.LoginController;
import database.Database;

public class PatientProfileView {
	public static JPanel profilePanel;

	public static JLabel profilePhoto;

	private JLabel titleLabel;
	public static JLabel nameLabel = new JLabel();
	public static JLabel phoneNumberLabel = new JLabel();
	public static JLabel emailLabel = new JLabel();
	public static JLabel addressLabel = new JLabel();

	public static JButton changePhotoButton = new JButton("Change Photo");
	public static JButton changePasswordButton = new JButton("Change Password");
	public static JButton changePhoneButton = new JButton("Change Phone");
	public static JButton changeAddressButton = new JButton("Change Address");

	private int labelHeight = 300;
	private int buttonHeight = 100;

	public PatientProfileView() {
		profilePanel();
		titleLabel();
		profilePhoto();
		addLabel(nameLabel);
		addLabel(addressLabel);
		addLabel(phoneNumberLabel);
		addLabel(emailLabel);
		addButton(changePhotoButton, "Change Photo");
		addButton(changePasswordButton, "Change Password");
		addButton(changePhoneButton, "Change Phone");
		addButton(changeAddressButton, "Change Address");

		new PatientProfileController();

	}

	private void profilePanel() {
		profilePanel = new JPanel();
		profilePanel.setLayout(null);
		profilePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		profilePanel.setVisible(true);
		profilePanel.setBackground(Color.LIGHT_GRAY);
		PatientPerspectiveView.patientFrame.add(profilePanel);
	}

	private void profilePhoto() {
		profilePhoto = new JLabel();
		profilePhoto.setBounds(100, 100, 250, 180);
		profilePhoto.setIcon(DoctorPerspectiveController
				.resizeImageIcon(new ImageIcon(Database.getProfilePhotoPath(LoginController.loggedInEmail))));
		profilePanel.add(profilePhoto);
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

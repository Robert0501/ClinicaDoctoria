package view_patient;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller_doctor.DoctorPerspectiveController;
import controller_patient.ContactDoctorController;
import controller_unlogin.LoginController;
import database.Database;

public class ContactDoctorView {

	public static JPanel contactDoctorPanel;

	public static JLabel titleLabel;
	public static JLabel doctorName = new JLabel();
	public static JLabel clinicName = new JLabel();
	public static JLabel clinicAddress = new JLabel();
	public static JLabel doctorEmail = new JLabel();
	public static JLabel doctorPhoto;
	public static JLabel contactDoctor;
	public static JLabel clickHere;

	private int height = 215;

	public ContactDoctorView() {
		contactDoctorPanel();
		titleLabel();
		doctorPhoto();
		putLabels();
		contactDoctor();
		clickHere();

		new ContactDoctorController();
	}

	private void contactDoctorPanel() {
		contactDoctorPanel = new JPanel();
		contactDoctorPanel.setLayout(null);
		contactDoctorPanel.setVisible(false);
		contactDoctorPanel.setBackground(Color.LIGHT_GRAY);
		contactDoctorPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		PatientPerspectiveView.patientFrame.add(contactDoctorPanel);
	}

	private void titleLabel() {
		titleLabel = new JLabel("Doctor Details");
		titleLabel.setBounds(0, 30, 980, 55);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		contactDoctorPanel.add(titleLabel);
	}

	private void doctorPhoto() {
		doctorPhoto = new JLabel();
		doctorPhoto.setBounds(100, 200, 250, 180);
		doctorPhoto.setIcon(DoctorPerspectiveController
				.resizeImageIcon(new ImageIcon(Database.getProfilePhotoPath(LoginController.loggedInEmail))));
		contactDoctorPanel.add(doctorPhoto);
	}

	private void putDoctorDetail(JLabel label) {
		label.setBounds(400, height, 500, 25);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		contactDoctorPanel.add(label);
		height += 40;
	}

	private void putLabels() {
		putDoctorDetail(doctorName);
		putDoctorDetail(clinicName);
		putDoctorDetail(clinicAddress);
		putDoctorDetail(doctorEmail);
	}

	private void contactDoctor() {
		contactDoctor = new JLabel("Would you like to send an email to your doctor?");
		contactDoctor.setBounds(100, 480, 530, 30);
		contactDoctor.setFont(new Font("Tahoma", Font.BOLD, 22));
		contactDoctorPanel.add(contactDoctor);
	}

	private void clickHere() {
		clickHere = new JLabel("<HTML><U>Click here</U></HTML>");
		clickHere.setBounds(630, 482, 120, 30);
		clickHere.setFont(new Font("Arial", Font.ITALIC, 22));
		clickHere.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contactDoctorPanel.add(clickHere);
	}

}

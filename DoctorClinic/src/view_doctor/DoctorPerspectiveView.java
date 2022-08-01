package view_doctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller_doctor.DoctorPerspectiveController;
import controller_unlogin.LoginController;
import database.Database;
import helper.ViewClass;

public class DoctorPerspectiveView {
	public static JFrame doctorFrame;
	public static JPanel navbarPanel;

	public static JPanel dashboardPanel;
	public static JPanel appointmentsPanel;
	public static JPanel pacientsPanel;
	public static JPanel messagesPanel;
	public static JPanel profilePanel;

	public static JLabel dashboardButton = new JLabel("Dashboard");
	public static JLabel appointmentsButton = new JLabel("Appointments");
	public static JLabel pacientsButton = new JLabel("Patients");
	public static JLabel messagesButton = new JLabel("Messages");
	public static JLabel profileButton = new JLabel("Profile");
	public static JLabel logoutButton = new JLabel("Logout");

	public static JLabel nameLabel = new JLabel("");
	public static JLabel docPhoto;

	public DoctorPerspectiveView() {
		doctorFrame();
		navbarPanel();

		if (!ViewClass.pacientView) {
			ViewClass.setDoctorViewsToFalse();
			new PacientView();
			ViewClass.pacientView = true;
		} else {
			ViewClass.setDoctorViewsToFalse();
			PacientView.pacientPanel.setVisible(true);
		}

		new DoctorPerspectiveController();

	}

//	public static void updateDataOnRelogin() {
//		docPhoto.setIcon(DoctorPerspectiveController
//				.resizeImageIcon(new ImageIcon(Database.getProfilePhotoPath(LoginController.loggedInEmail))));
//		nameLabel.setText("Dr. " + Database.getDoctorName(LoginController.loggedInEmail));
//	}

	private void doctorFrame() {
		doctorFrame = new JFrame("Clinica Doctoria");
		doctorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		doctorFrame.pack();
		doctorFrame.setSize(new Dimension(1280, 720));
		doctorFrame.setLocationRelativeTo(null);
		doctorFrame.setLayout(new BorderLayout());
		doctorFrame.setVisible(true);
	}

	private void navbarPanel() {
		navbarPanel = new JPanel();
		navbarPanel.setBackground(Color.decode("#3A7254"));
		navbarPanel.setPreferredSize(new Dimension(300, 700));

		docPhoto();
		doctorName();
		putNavbarButtons(dashboardButton);
		putNavbarButtons(appointmentsButton);
		putNavbarButtons(pacientsButton);
		putNavbarButtons(messagesButton);
		putNavbarButtons(profileButton);
		putNavbarButtons(logoutButton);
		doctorFrame.add(navbarPanel, BorderLayout.WEST);
	}

	private void doctorName() {
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		nameLabel.setText("Dr. " + Database.getDoctorName(LoginController.loggedInEmail));
		navbarPanel.add(nameLabel);
	}

	private void docPhoto() {
		docPhoto = new JLabel();
		docPhoto.setPreferredSize(new Dimension(250, 180));
		docPhoto.setIcon(DoctorPerspectiveController
				.resizeImageIcon(new ImageIcon(Database.getProfilePhotoPath(LoginController.loggedInEmail))));
		navbarPanel.add(docPhoto);
	}

	private void putNavbarButtons(JLabel label) {
		label.setOpaque(true);
		label.setBackground(Color.decode("#92CDA3"));
		label.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.setPreferredSize(new Dimension(300, 70));
		onHover(label);
		navbarPanel.add(label);
	}

	private void onHover(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				label.setBackground(Color.LIGHT_GRAY);
			}

			public void mouseExited(MouseEvent evt) {
				label.setBackground(Color.decode("#92CDA3"));
			}

		});
	}

}

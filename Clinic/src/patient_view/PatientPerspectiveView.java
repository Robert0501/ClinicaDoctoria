package patient_view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import patient_controller.PatientPerspectiveController;

public class PatientPerspectiveView {

	public static JFrame patientFrame;
	public static JPanel navbarPanel;
	public static JPanel photoPanel;

	public static JLabel patientName = new JLabel("Robert-Ionut");
	public static JLabel patientPhoto = new JLabel("");

	public static JLabel dashboardButton = new JLabel("Dashboard");
	public static JLabel appointmentsButton = new JLabel("Make Appointment");
	public static JLabel testHistoryButton = new JLabel("Test History");
	public static JLabel messagesButton = new JLabel("Messages");
	public static JLabel profileButton = new JLabel("Profile");
	public static JLabel logoutButton = new JLabel("Logout");

	public PatientPerspectiveView() {
		patientFrame();
		navbarPanel();
		new PatientPerspectiveController();
	}

	private void patientFrame() {
		patientFrame = new JFrame("Clinica Doctoria");
		patientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		patientFrame.pack();
		patientFrame.setSize(new Dimension(1280, 720));
		patientFrame.setLocationRelativeTo(null);
		patientFrame.setLayout(new BorderLayout());
		patientFrame.setVisible(true);
	}

	private void navbarPanel() {
		navbarPanel = new JPanel();
		navbarPanel.setBackground(Color.decode("#3A7254"));
		navbarPanel.setPreferredSize(new Dimension(300, 700));
		photoPanel();
		patientName();
		putNavbarButtons(dashboardButton);
		putNavbarButtons(appointmentsButton);
		putNavbarButtons(testHistoryButton);
		putNavbarButtons(messagesButton);
		putNavbarButtons(profileButton);
		putNavbarButtons(logoutButton);
		patientFrame.add(navbarPanel, BorderLayout.WEST);
	}

	private static void photoPanel() {
		photoPanel = new JPanel();
		photoPanel.setPreferredSize(new Dimension(250, 180));
		navbarPanel.add(photoPanel);

		photoPanel.add(patientPhoto);
	}

	private static void patientName() {
		patientName.setFont(new Font("Tahoma", Font.BOLD, 24));
		navbarPanel.add(patientName);
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

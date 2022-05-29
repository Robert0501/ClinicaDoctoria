package view_patient;

import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import controller_patient.NewAppointmentController;

public class CreateNewAppointmentView {

	public static JFrame newAppointmentFrame;

	public static JButton makeAppointmentButton;

	private JLabel title;
	private JLabel reasonLabel;
	private JLabel dateLabel;
	private JLabel timeLabel;

	public static JComboBox<?> hourComboBox;
	String[] hourChooses = { "Choose a time stamp", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00" };

	public static JComboBox<?> reasonComboBox;
	String[] reasonChooses = { "Choose a reason", "Skin Disorders", "Joint Pain And Osteoarthritis", "Back Problems",
			"Cholesterol Problems", "Upper Respiratory Problems", "Anxiety, Bipolar Disorder And Depression",
			"Chronic Neurology Disorders", "High Blood Pressure", "Headaches And Migraines", "Diabetes",
			"Consulation" };

	public static String appointmentDate;

	public CreateNewAppointmentView() {
		newAppointmentFrame();
		title();
		datePicker();
		timePicker();
		reason();
		makeAppointmentButton();

		new NewAppointmentController();
	}

	private void newAppointmentFrame() {
		newAppointmentFrame = new JFrame("Make an Appointment");
		newAppointmentFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		newAppointmentFrame.pack();
		newAppointmentFrame.setSize(new Dimension(500, 350));
		newAppointmentFrame.setLocationRelativeTo(null);
		newAppointmentFrame.setLayout(null);
		newAppointmentFrame.setVisible(true);
	}

	private void title() {
		title = new JLabel("Make an appointment");
		title.setBounds(0, 25, 500, 25);
		title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		newAppointmentFrame.add(title);
	}

	private void reason() {
		reasonLabel = new JLabel("Reason");
		reasonLabel.setBounds(50, 95, 250, 15);
		reasonLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		newAppointmentFrame.add(reasonLabel);

		reasonComboBox = new JComboBox<String>(reasonChooses);
		reasonComboBox.setBounds(50, 115, 400, 25);
		newAppointmentFrame.add(reasonComboBox);
	}

	private void timePicker() {
		timeLabel = new JLabel("Time");
		timeLabel.setBounds(270, 180, 250, 15);
		timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		newAppointmentFrame.add(timeLabel);
		hourComboBox = new JComboBox<String>(hourChooses);
		hourComboBox.setBounds(270, 200, 180, 25);
		newAppointmentFrame.add(hourComboBox);
	}

	@SuppressWarnings("deprecation")
	private void datePicker() {
		dateLabel = new JLabel("Date");
		dateLabel.setBounds(50, 180, 250, 15);
		dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		newAppointmentFrame.add(dateLabel);

		JDateChooser date = new JDateChooser();
		date.setBounds(50, 200, 175, 25);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		date.setDate(new Date(dtf.format(now)));
		newAppointmentFrame.add(date);
		java.util.Date d = date.getDate();
		if (d == null) {
			System.out.println("No date specified!");
		} else {
			java.sql.Date sqldate = new java.sql.Date(d.getTime());
			System.out.println(sqldate);
			appointmentDate = String.valueOf(sqldate);
		}
	}

	private void makeAppointmentButton() {
		makeAppointmentButton = new JButton("Make Appointment");
		makeAppointmentButton.setBounds(150, 250, 200, 30);
		newAppointmentFrame.add(makeAppointmentButton);
	}

}

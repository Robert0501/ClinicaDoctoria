package view_patient;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller_patient.PatientEmailSenderController;

public class PatientEmailSenderView {

	public static JFrame PatientEmailSenderFrame;
	public static JPanel textPanel;

	private JLabel emailFrom;
	private JLabel emailTo;
	private JLabel emailSubject;

	public static JTextField emailFromIn;
	public static JTextField emailToIn;
	public static JTextField emailSubjectIn;

	public static JTextArea email;

	public static JButton sendEmail;

	public PatientEmailSenderView() {
		patientEmailSenderFrame();
		emailFrom();
		emailTo();
		emailSubject();
		textPanel();
		emailTextArea();
		sendEmail();

		new PatientEmailSenderController();

	}

	private void patientEmailSenderFrame() {
		PatientEmailSenderFrame = new JFrame("Send Email");
		PatientEmailSenderFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		PatientEmailSenderFrame.pack();
		PatientEmailSenderFrame.setSize(new Dimension(580, 520));
		PatientEmailSenderFrame.setLocationRelativeTo(null);
		PatientEmailSenderFrame.setLayout(null);
		PatientEmailSenderFrame.setVisible(true);
	}

	private void emailFrom() {
		emailFrom = new JLabel("From");
		emailFrom.setBounds(50, 50, 120, 25);
		emailFrom.setFont(new Font("Tahoma", Font.BOLD, 20));
		PatientEmailSenderFrame.add(emailFrom);

		emailFromIn = new JTextField();
		emailFromIn.setBounds(150, 50, 350, 25);
		emailFromIn.setEnabled(false);
		PatientEmailSenderFrame.add(emailFromIn);
	}

	private void emailTo() {
		emailTo = new JLabel("To");
		emailTo.setBounds(50, 100, 120, 25);
		emailTo.setFont(new Font("Tahoma", Font.BOLD, 20));
		PatientEmailSenderFrame.add(emailTo);

		emailToIn = new JTextField();
		emailToIn.setBounds(150, 100, 350, 25);
		emailToIn.setEnabled(false);
		PatientEmailSenderFrame.add(emailToIn);
	}

	private void emailSubject() {
		emailSubject = new JLabel("Subject");
		emailSubject.setBounds(50, 150, 120, 25);
		emailSubject.setFont(new Font("Tahoma", Font.BOLD, 20));
		PatientEmailSenderFrame.add(emailSubject);

		emailSubjectIn = new JTextField();
		emailSubjectIn.setBounds(150, 150, 350, 25);
		PatientEmailSenderFrame.add(emailSubjectIn);
	}

	private void textPanel() {
		textPanel = new JPanel();
		textPanel.setLayout(null);
		textPanel.setVisible(true);
		textPanel.setBounds(50, 200, 450, 200);
		PatientEmailSenderFrame.add(textPanel);
	}

	private void emailTextArea() {
		email = new JTextArea();
		email.setBounds(50, 200, 450, 200);
		email.setLineWrap(true);
		email.setWrapStyleWord(true);

		JScrollPane areaScrollPane = new JScrollPane(email);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setSize(new Dimension(450, 200));

		textPanel.add(areaScrollPane);
	}

	private void sendEmail() {
		sendEmail = new JButton("Sent Email");
		sendEmail.setBounds(220, 420, 100, 25);
		sendEmail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		PatientEmailSenderFrame.add(sendEmail);
	}

}

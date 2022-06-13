package view_patient;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PatientShowMessageView {
	public static JFrame messageFrame;

	public static JPanel textPanel;

	private JLabel emailFrom;
	private JLabel nameFrom;
	private JLabel emailSubject;

	public static JTextArea email;

	public static JTextField emailFromIn;
	public static JTextField nameFromin;
	public static JTextField emailSubjectIn;

	public PatientShowMessageView() {
		messageFrame();
		emailFrom();
		nameFrom();
		emailSubject();
		textPanel();
		emailTextArea();
	}

	private void messageFrame() {
		messageFrame = new JFrame("Message");
		messageFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		messageFrame.pack();
		messageFrame.setSize(new Dimension(580, 520));
		messageFrame.setLocationRelativeTo(null);
		messageFrame.setLayout(null);
		messageFrame.setVisible(true);
	}

	private void emailFrom() {
		emailFrom = new JLabel("From");
		emailFrom.setBounds(50, 50, 120, 25);
		emailFrom.setFont(new Font("Tahoma", Font.BOLD, 20));
		messageFrame.add(emailFrom);

		emailFromIn = new JTextField();
		emailFromIn.setBounds(150, 50, 350, 25);
		emailFromIn.setEnabled(false);
		messageFrame.add(emailFromIn);
	}

	private void nameFrom() {
		nameFrom = new JLabel("Name");
		nameFrom.setBounds(50, 100, 120, 25);
		nameFrom.setFont(new Font("Tahoma", Font.BOLD, 20));
		messageFrame.add(nameFrom);

		nameFromin = new JTextField();
		nameFromin.setBounds(150, 100, 350, 25);
		nameFromin.setEnabled(false);
		messageFrame.add(nameFromin);
	}

	private void emailSubject() {
		emailSubject = new JLabel("Subject");
		emailSubject.setBounds(50, 150, 120, 25);
		emailSubject.setFont(new Font("Tahoma", Font.BOLD, 20));
		messageFrame.add(emailSubject);

		emailSubjectIn = new JTextField();
		emailSubjectIn.setBounds(150, 150, 350, 25);
		emailSubjectIn.setEnabled(false);
		messageFrame.add(emailSubjectIn);
	}

	private void textPanel() {
		textPanel = new JPanel();
		textPanel.setLayout(null);
		textPanel.setVisible(true);
		textPanel.setBounds(50, 200, 450, 200);
		messageFrame.add(textPanel);
	}

	private void emailTextArea() {
		email = new JTextArea();
		email.setBounds(50, 200, 450, 200);
		email.setLineWrap(true);
		email.setWrapStyleWord(true);
		email.setEnabled(false);

		JScrollPane areaScrollPane = new JScrollPane(email);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setSize(new Dimension(450, 200));

		textPanel.add(areaScrollPane);
	}
}

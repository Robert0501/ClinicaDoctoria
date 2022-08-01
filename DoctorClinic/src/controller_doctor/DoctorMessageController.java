package controller_doctor;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import controller_unlogin.LoginController;
import database.Database;
import database.InsertDatabase;
import email.Email;
import helper.ViewClass;
import view_doctor.DoctorEmailSenderView;
import view_doctor.DoctorShowMessageView;
import view_doctor.DoctorMessagesView;

public class DoctorMessageController {

	JTable table;

	public DoctorMessageController() {
		showMessages();
		openMessage();
		replayButton();

	}

	private void showMessages() {
		Database.showDoctorEmails(LoginController.loggedInEmail);
	}

	private void openMessage() {
		DoctorMessagesView.table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				table = (JTable) e.getSource();
				Point point = e.getPoint();
				int row = table.rowAtPoint(point);
				if (e.getClickCount() == 1 && table.getSelectedRow() != -1) {
					DoctorMessagesView.replayButton.setEnabled(true);
				}
				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
					new DoctorShowMessageView();
					putDetails();
				}
			}
		});
	}

	private void putDetails() {
		putEmail();
		putName();
		putSubject();
		putText();

	}

	private void putEmail() {
		DoctorShowMessageView.emailFromIn.setText(Database.getMessageDetails(LoginController.loggedInEmail,
				table.getValueAt(table.getSelectedRow(), 0).toString(),
				table.getValueAt(table.getSelectedRow(), 1).toString(), "email_from"));
	}

	private void putName() {
		DoctorShowMessageView.nameFromin.setText(Database.getMessageDetails(LoginController.loggedInEmail,
				table.getValueAt(table.getSelectedRow(), 0).toString(),
				table.getValueAt(table.getSelectedRow(), 1).toString(), "patient_name"));
	}

	private void putSubject() {
		DoctorShowMessageView.emailSubjectIn.setText(Database.getMessageDetails(LoginController.loggedInEmail,
				table.getValueAt(table.getSelectedRow(), 0).toString(),
				table.getValueAt(table.getSelectedRow(), 1).toString(), "subject"));
	}

	private void putText() {
		DoctorShowMessageView.email.setText(Database.getMessageDetails(LoginController.loggedInEmail,
				table.getValueAt(table.getSelectedRow(), 0).toString(),
				table.getValueAt(table.getSelectedRow(), 1).toString(), "text"));
	}

	private void replayButton() {
		DoctorMessagesView.replayButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.doctorEmailSenderView) {
					new DoctorEmailSenderView();
					sendEmailButton();
					putDoctorDetails();
					ViewClass.doctorEmailSenderView = true;
				} else {
					putDoctorDetails();
					DoctorEmailSenderView.doctorEmailSenderFrame.setVisible(true);
				}
			}
		});
	}

	private void putDoctorDetails() {
		putFromEmail();
		putToEmail();
		putReplaySubject();
	}

	private void putFromEmail() {
		DoctorEmailSenderView.emailFromIn.setText(LoginController.loggedInEmail);
	}

	private void putToEmail() {
		DoctorEmailSenderView.emailToIn.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
	}

	private void putReplaySubject() {
		DoctorEmailSenderView.emailSubjectIn.setText("Re: " + table.getValueAt(table.getSelectedRow(), 4).toString());
	}

	private String getCurrentDate() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		return dateFormatter.format(date);
	}

	private String getCurrentHour() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
		Date date = new Date();

		return dateFormatter.format(date);
	}

	private void sendEmailButton() {
		DoctorEmailSenderView.sendEmail.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Email.index = 7;
				Email.email[Email.position].start();
				Email.position++;
				InsertDatabase.insertIntoMessageTable(DoctorEmailSenderView.emailFromIn.getText(),
						DoctorEmailSenderView.emailToIn.getText(), DoctorEmailSenderView.emailSubjectIn.getText(),
						DoctorEmailSenderView.email.getText(), getCurrentDate(), getCurrentHour(),
						Database.getDoctorName(LoginController.loggedInEmail));
				JOptionPane.showMessageDialog(null, "Your email has been successfully sent to your doctor",
						"Email successfully sent", JOptionPane.INFORMATION_MESSAGE);
				DoctorEmailSenderView.doctorEmailSenderFrame.setVisible(false);
				DoctorEmailSenderView.email.setText("");
			}
		});
	}

}

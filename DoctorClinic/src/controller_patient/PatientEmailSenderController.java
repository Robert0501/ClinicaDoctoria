package controller_patient;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import controller_unlogin.LoginController;
import database.Database;
import database.InsertDatabase;
import email.Email;
import view_patient.PatientEmailSenderView;

public class PatientEmailSenderController {

	public PatientEmailSenderController() {
		emailFrom();
		emailTo();
		sendEmail();
	}

	private void emailFrom() {
		PatientEmailSenderView.emailFromIn.setText(LoginController.loggedInEmail);
	}

	private void emailTo() {
		PatientEmailSenderView.emailToIn.setText(Database.getDoctorEmail(LoginController.loggedInEmail));
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

	private void sendEmail() {
		PatientEmailSenderView.sendEmail.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Email.index = 6;
				Email.email[Email.position].start();
				InsertDatabase.insertIntoMessageTable(PatientEmailSenderView.emailFromIn.getText(),
						PatientEmailSenderView.emailToIn.getText(), PatientEmailSenderView.emailSubjectIn.getText(),
						PatientEmailSenderView.email.getText(), getCurrentDate(), getCurrentHour(),
						Database.getPatientName(LoginController.loggedInEmail));
				JOptionPane.showMessageDialog(null, "Your email has been successfully sent to your doctor",
						"Email successfully sent", JOptionPane.INFORMATION_MESSAGE);
				PatientEmailSenderView.PatientEmailSenderFrame.setVisible(false);
				emptyFields();
			}
		});
	}

	private void emptyFields() {
		PatientEmailSenderView.emailSubjectIn.setText("");
		PatientEmailSenderView.email.setText("");
	}
}

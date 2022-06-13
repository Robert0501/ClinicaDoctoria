package controller_patient;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import controller_unlogin.LoginController;
import database.Database;
import email.Email;
import helper.ViewClass;
import view_patient.PacientMessageView;
import view_patient.PatientEmailSenderView;
import view_patient.PatientShowMessageView;

public class PatientMessageController {

	JTable table;

	public PatientMessageController() {
		showMessages();
		openMessage();
		replayButton();
	}

	private void showMessages() {
		Database.showPatientEmails(LoginController.loggedInEmail);
	}

	private void openMessage() {
		PacientMessageView.table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				table = (JTable) e.getSource();
				Point point = e.getPoint();
				int row = table.rowAtPoint(point);
				if (e.getClickCount() == 1 && table.getSelectedRow() != -1) {
					PacientMessageView.replayButton.setEnabled(true);
				}
				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
					if (!ViewClass.patientShowMessageView) {
						new PatientShowMessageView();
						putDetails();
						ViewClass.patientShowMessageView = true;
					} else {
						putDetails();
						PatientShowMessageView.messageFrame.setVisible(true);
					}
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
		PatientShowMessageView.emailFromIn.setText(Database.getMessageDetails(LoginController.loggedInEmail,
				table.getValueAt(table.getSelectedRow(), 0).toString(),
				table.getValueAt(table.getSelectedRow(), 1).toString(), "email_from"));
	}

	private void putName() {
		PatientShowMessageView.nameFromin.setText(Database.getMessageDetails(LoginController.loggedInEmail,
				table.getValueAt(table.getSelectedRow(), 0).toString(),
				table.getValueAt(table.getSelectedRow(), 1).toString(), "patient_name"));
	}

	private void putSubject() {
		PatientShowMessageView.emailSubjectIn.setText(Database.getMessageDetails(LoginController.loggedInEmail,
				table.getValueAt(table.getSelectedRow(), 0).toString(),
				table.getValueAt(table.getSelectedRow(), 1).toString(), "subject"));
	}

	private void putText() {
		PatientShowMessageView.email.setText(Database.getMessageDetails(LoginController.loggedInEmail,
				table.getValueAt(table.getSelectedRow(), 0).toString(),
				table.getValueAt(table.getSelectedRow(), 1).toString(), "text"));
	}

	private void replayButton() {
		PacientMessageView.replayButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.patientEmailSenderFrame) {
					new PatientEmailSenderView();
					replayDetails();
					ViewClass.patientEmailSenderFrame = true;
				} else {
					replayDetails();
					PatientEmailSenderView.PatientEmailSenderFrame.setVisible(true);
				}
			}
		});
	}

	private void replayDetails() {
		PatientEmailSenderView.emailSubjectIn.setText("Re: " + Database.getMessageDetails(LoginController.loggedInEmail,
				table.getValueAt(table.getSelectedRow(), 0).toString(),
				table.getValueAt(table.getSelectedRow(), 1).toString(), "subject"));

	}

}

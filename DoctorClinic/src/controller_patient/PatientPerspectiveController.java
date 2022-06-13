package controller_patient;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import controller_unlogin.LoginController;
import database.Database;
import helper.ViewClass;
import view_patient.ContactDoctorView;
import view_patient.PacientMessageView;
import view_patient.PatientAppointmentsView;
import view_patient.PatientPerspectiveView;
import view_patient.TestHistoryView;
import view_unlogin.LoginView;

public class PatientPerspectiveController {

	public PatientPerspectiveController() {
		putPhoto();
		putPatientName();
		contactDoctorPage();
		historyPage();
		appointmentsPage();
		messagesPage();

		logout();
	}

	private void putPhoto() {
		PatientPerspectiveView.patientPhoto
				.setIcon(new ImageIcon(Database.getProfilePhotoPath(LoginController.loggedInEmail)));

		System.out.println(Database.getProfilePhotoPath(LoginController.loggedInEmail));
	}

	private void putPatientName() {
		PatientPerspectiveView.patientName.setText(Database.getPatientName(LoginController.loggedInEmail));
	}

	private void contactDoctorPage() {
		PatientPerspectiveView.contactDoctorButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.patientContactDoctorView) {
					ViewClass.setPatientViewsOnFalse();
					new ContactDoctorView();
					ViewClass.patientContactDoctorView = true;
				} else {
					ViewClass.setPatientViewsOnFalse();
					ContactDoctorView.contactDoctorPanel.setVisible(true);
				}
			}
		});
	}

	private void historyPage() {
		PatientPerspectiveView.testHistoryButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.testHistoryView) {
					ViewClass.setPatientViewsOnFalse();
					new TestHistoryView();
					ViewClass.testHistoryView = true;
				} else {
					ViewClass.setPatientViewsOnFalse();
					TestHistoryView.model.setRowCount(0);
					Database.showTestResults(LoginController.loggedInEmail);
					TestHistoryView.testHistoryPanel.setVisible(true);
				}
			}
		});
	}

	private void appointmentsPage() {
		PatientPerspectiveView.appointmentsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.patientAppointmentsView) {
					ViewClass.setPatientViewsOnFalse();
					new PatientAppointmentsView();
					ViewClass.patientAppointmentsView = true;
				} else {
					ViewClass.setPatientViewsOnFalse();
					PatientAppointmentsView.model.setRowCount(0);
					Database.showPacientAppointments(LoginController.loggedInEmail);
					PatientAppointmentsView.patientAppointmentsPanel.setVisible(true);
				}
			}
		});
	}

	private void messagesPage() {
		PatientPerspectiveView.messageToDoctorButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.patientMessageView) {
					ViewClass.setPatientViewsOnFalse();
					new PacientMessageView();
					ViewClass.patientMessageView = true;
				} else {
					ViewClass.setPatientViewsOnFalse();
					PacientMessageView.model.setRowCount(0);
					Database.showPatientEmails(LoginController.loggedInEmail);
					PacientMessageView.messagePanel.setVisible(true);
				}
			}
		});
	}

	private void logout() {
		PatientPerspectiveView.logoutButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PatientPerspectiveView.patientFrame.setVisible(false);
				LoginView.loginFrame.setVisible(true);
			}
		});
	}

}

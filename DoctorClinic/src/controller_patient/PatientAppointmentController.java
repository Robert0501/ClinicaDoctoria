package controller_patient;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller_unlogin.LoginController;
import database.Database;
import view_patient.CreateNewAppointmentView;
import view_patient.PatientAppointmentsView;

public class PatientAppointmentController {

	public PatientAppointmentController() {
		showAppointments();
		makeNewAppointment();
	}

	private void makeNewAppointment() {
		PatientAppointmentsView.makeNewAppointment.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new CreateNewAppointmentView();
			}
		});

	}

	private void showAppointments() {
		Database.showPacientAppointments(LoginController.loggedInEmail);
	}

}

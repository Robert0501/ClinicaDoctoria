package controller_doctor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller_unlogin.LoginController;
import database.Database;
import view_doctor.DoctorAppointmentsView;

public class DoctorAppointmentsController {

	public DoctorAppointmentsController() {
		showAppointments();
		approveAppointment();
		denyAppointment();
	}

	private void showAppointments() {
		Database.showDoctorAppointments(LoginController.loggedInEmail);
	}

	private void approveAppointment() {
		DoctorAppointmentsView.approve.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DoctorAppointmentsView.table.getModel().setValueAt("Approved",
						DoctorAppointmentsView.table.getSelectedRow(), 4);
				Database.updateAppointmentStatusColumn("Approved",
						String.valueOf(DoctorAppointmentsView.table.getModel()
								.getValueAt(DoctorAppointmentsView.table.getSelectedRow(), 3)),
						String.valueOf(DoctorAppointmentsView.table.getModel()
								.getValueAt(DoctorAppointmentsView.table.getSelectedRow(), 1)),
						String.valueOf(DoctorAppointmentsView.table.getModel()
								.getValueAt(DoctorAppointmentsView.table.getSelectedRow(), 2)));

			}
		});
	}

	private void denyAppointment() {
		DoctorAppointmentsView.denied.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DoctorAppointmentsView.table.getModel().setValueAt("Denied",
						DoctorAppointmentsView.table.getSelectedRow(), 4);
				Database.updateAppointmentStatusColumn("Denied",
						String.valueOf(DoctorAppointmentsView.table.getModel()
								.getValueAt(DoctorAppointmentsView.table.getSelectedRow(), 3)),
						String.valueOf(DoctorAppointmentsView.table.getModel()
								.getValueAt(DoctorAppointmentsView.table.getSelectedRow(), 1)),
						String.valueOf(DoctorAppointmentsView.table.getModel()
								.getValueAt(DoctorAppointmentsView.table.getSelectedRow(), 2)));
			}
		});
	}
}

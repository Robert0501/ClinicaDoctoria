package controller_patient;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import controller_unlogin.LoginController;
import database.Database;
import view_patient.CreateNewAppointmentView;
import view_patient.PatientAppointmentsView;

public class NewAppointmentController {

	public NewAppointmentController() {
		makeAppointment();
	}

	private boolean checkFields() {
		if (CreateNewAppointmentView.reasonComboBox.getSelectedItem().equals("Choose a reason")) {
			JOptionPane.showMessageDialog(null, "You have to choose a reason", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (CreateNewAppointmentView.hourComboBox.getSelectedItem().equals("Choose a time stamp")) {
			JOptionPane.showMessageDialog(null, "You have to choose an hour", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void insertAppointmentIntoDatabase() {
		Database.insertIntoAppointmentsTable(LoginController.loggedInEmail,
				Database.getPatientName(LoginController.loggedInEmail),
				Database.getDoctorEmail(LoginController.loggedInEmail), CreateNewAppointmentView.appointmentDate,
				CreateNewAppointmentView.hourComboBox.getSelectedItem().toString(),
				CreateNewAppointmentView.reasonComboBox.getSelectedItem().toString());
	}

	private void makeAppointment() {
		CreateNewAppointmentView.makeAppointmentButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (checkFields()) {
					insertAppointmentIntoDatabase();
					PatientAppointmentsView.model.addRow(new Object[] { CreateNewAppointmentView.appointmentDate,
							CreateNewAppointmentView.hourComboBox.getSelectedItem(),
							CreateNewAppointmentView.reasonComboBox.getSelectedItem(), "Pending" });
					JOptionPane.showMessageDialog(null,
							"Your appointment has been submitted. Wait for your doctor response",
							"Appointment Submited Successfully", JOptionPane.INFORMATION_MESSAGE);
					CreateNewAppointmentView.newAppointmentFrame.setVisible(false);
				}
			}
		});
	}

}

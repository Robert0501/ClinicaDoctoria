package controller_patient;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import controller_doctor.DoctorPerspectiveController;
import controller_unlogin.LoginController;
import database.Database;
import helper.ViewClass;
import view_patient.ContactDoctorView;
import view_patient.PatientEmailSenderView;

public class ContactDoctorController {

	public ContactDoctorController() {
		putPhoto();
		putDoctorName();
		putDoctorClinicName();
		putDoctorClinicAddress();
		putDoctorEmail();
		enterEmailSenderView();
	}

	private void putPhoto() {
		ContactDoctorView.doctorPhoto.setIcon(DoctorPerspectiveController.resizeImageIcon(
				new ImageIcon(Database.getDoctorPhotoFromPatientEmail(LoginController.loggedInEmail))));
	}

	private void putDoctorName() {
		ContactDoctorView.doctorName
				.setText("Name: " + Database.getDoctorNameFromPatientEmail(LoginController.loggedInEmail));
	}

	private void putDoctorClinicName() {
		ContactDoctorView.clinicName
				.setText("Clinic: " + Database.getDoctorClinicFromPatientEmail(LoginController.loggedInEmail));
	}

	private void putDoctorClinicAddress() {
		ContactDoctorView.clinicAddress
				.setText("Address: " + Database.getDoctorClinicAddressromPatientEmail(LoginController.loggedInEmail));
	}

	private void putDoctorEmail() {
		ContactDoctorView.doctorEmail
				.setText("Email: " + Database.getDoctorEmailFromPatientEmail(LoginController.loggedInEmail));
	}

	private void enterEmailSenderView() {
		ContactDoctorView.clickHere.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.patientEmailSenderFrame) {
					new PatientEmailSenderView();
					ViewClass.patientEmailSenderFrame = true;
				} else {
					PatientEmailSenderView.PatientEmailSenderFrame.setVisible(true);
				}
			}
		});
	}

}

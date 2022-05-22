package patient_controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import database.Database;
import doctor_controller.LoginController;
import helper.ViewClass;
import patient_view.PatientPerspectiveView;
import patient_view.TestHistoryView;

public class PatientPerspectiveController {

	public PatientPerspectiveController() {
		putPhoto();
		putPatientName();
		historyPage();
	}

	private void putPhoto() {
		PatientPerspectiveView.patientPhoto
				.setIcon(new ImageIcon(Database.getProfilePhotoPath(LoginController.loggedInEmail)));

		System.out.println(Database.getProfilePhotoPath(LoginController.loggedInEmail));
	}

	private void putPatientName() {
		PatientPerspectiveView.patientName.setText(Database.getPatientName(LoginController.loggedInEmail));
	}

	private void historyPage() {
		PatientPerspectiveView.testHistoryButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.testHistoryView) {
					new TestHistoryView();
					ViewClass.testHistoryView = true;
				} else {
					TestHistoryView.testHistoryPanel.setVisible(true);
				}
			}
		});
	}

}

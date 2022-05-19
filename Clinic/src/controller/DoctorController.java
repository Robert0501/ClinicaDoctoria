package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.DoctorView;
import view.LoginView;
import view.PacientView;
import view.ProfileView;

public class DoctorController {

	public DoctorController() {
		logoutButton();
		profileButton();
		pacientButton();
	}

	private void logoutButton() {
		DoctorView.logoutButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DoctorView.doctorFrame.setVisible(false);
				LoginView.loginFrame.setVisible(true);
			}
		});
	}

	private void setAllPanelsInvisible() {
		// DoctorNewView.dashboardPanel.setVisible(false);
		// DoctorNewView.appointmentsPanel.setVisible(false);
		PacientView.pacientPanel.setVisible(false);
		// DoctorNewView.messagesPanel.setVisible(false);
		ProfileView.profilePanel.setVisible(false);
	}

	private void profileButton() {
		DoctorView.profileButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ProfileView.profilePanel.setVisible(true);
			}
		});
	}

	private void pacientButton() {
		DoctorView.pacientsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// setAllPanelsInvisible();
				ProfileView.profilePanel.hide();
				new PacientView();
				PacientView.pacientPanel.setVisible(true);
			}
		});
	}

}

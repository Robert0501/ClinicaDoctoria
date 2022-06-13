package controller_doctor;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import controller_unlogin.LoginController;
import database.Database;
import helper.ViewClass;
import view_doctor.DoctorAppointmentsView;
import view_doctor.DoctorDashboardView;
import view_doctor.DoctorMessagesView;
import view_doctor.DoctorPerspectiveView;
import view_doctor.PacientView;
import view_doctor.ProfileView;
import view_unlogin.LoginView;

public class DoctorPerspectiveController {

	public DoctorPerspectiveController() {
		logoutButton();
		profileButton();
		pacientButton();
		appointmentButton();
		messagesButton();
		dashboardButton();
	}

	private void logoutButton() {
		DoctorPerspectiveView.logoutButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DoctorPerspectiveView.doctorFrame.setVisible(false);
				LoginView.loginFrame.setVisible(true);
			}
		});
	}

	public static ImageIcon resizeImageIcon(ImageIcon imageIcon) {
		BufferedImage bufferedImage = new BufferedImage(250, 180, BufferedImage.TRANSLUCENT);

		Graphics2D graphics2D = bufferedImage.createGraphics();
		graphics2D.drawImage(imageIcon.getImage(), 0, 0, 250, 180, null);
		graphics2D.dispose();

		return new ImageIcon(bufferedImage, imageIcon.getDescription());
	}

	private void profileButton() {
		DoctorPerspectiveView.profileButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.profileView) {
					ViewClass.setDoctorViewsToFalse();
					new ProfileView();
					ViewClass.profileView = true;
				} else {
					ViewClass.setDoctorViewsToFalse();
					ProfileView.profilePanel.setVisible(true);
				}
			}
		});
	}

	private void pacientButton() {
		DoctorPerspectiveView.pacientsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.pacientView) {
					ViewClass.setDoctorViewsToFalse();
					new PacientView();
					ViewClass.pacientView = true;
				} else {
					ViewClass.setDoctorViewsToFalse();
					PacientView.pacientPanel.setVisible(true);
				}
			}
		});
	}

	private void messagesButton() {
		DoctorPerspectiveView.messagesButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.doctorMessagesView) {
					ViewClass.setDoctorViewsToFalse();
					new DoctorMessagesView();
					ViewClass.doctorMessagesView = true;
				} else {
					ViewClass.setDoctorViewsToFalse();
					DoctorMessagesView.model.setRowCount(0);
					Database.showDoctorEmails(LoginController.loggedInEmail);
					DoctorMessagesView.messagePanel.setVisible(true);
				}
			}
		});
	}

	private void appointmentButton() {
		DoctorPerspectiveView.appointmentsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.doctorAppointmentView) {
					ViewClass.setDoctorViewsToFalse();
					new DoctorAppointmentsView();
					ViewClass.doctorAppointmentView = true;
				} else {
					ViewClass.setDoctorViewsToFalse();
					DoctorAppointmentsView.tableModel.setRowCount(0);
					Database.showDoctorAppointments(LoginController.loggedInEmail);
					DoctorAppointmentsView.doctorAppointmentsPanel.setVisible(true);
				}
			}
		});
	}

	private void dashboardButton() {
		DoctorPerspectiveView.dashboardButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.dashboardView) {
					ViewClass.setDoctorViewsToFalse();
					new DoctorDashboardView();
					ViewClass.dashboardView = true;
				} else {
					ViewClass.setDoctorViewsToFalse();
					DoctorDashboardView.dashboardPanel.setVisible(true);
				}
			}
		});
	}

}

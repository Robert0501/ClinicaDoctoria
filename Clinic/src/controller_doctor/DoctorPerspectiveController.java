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
	}

	private void logoutButton() {
		DoctorPerspectiveView.logoutButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DoctorPerspectiveView.doctorFrame.setVisible(false);
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
					System.out.println(ViewClass.pacientView);
					if (ViewClass.pacientView) {
						PacientView.pacientPanel.setVisible(false);
					}
					new ProfileView();
					ViewClass.profileView = true;
				} else {
					if (ViewClass.pacientView) {
						PacientView.pacientPanel.setVisible(false);
					}
					ProfileView.profilePanel.setVisible(true);
				}
			}
		});
	}

	private void pacientButton() {
		DoctorPerspectiveView.pacientsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.pacientView) {
					if (ViewClass.profileView) {
						ProfileView.profilePanel.setVisible(false);
					}
					new PacientView();
					ViewClass.pacientView = true;
				} else {
					if (ViewClass.profileView) {
						ProfileView.profilePanel.setVisible(false);
					}
					PacientView.pacientPanel.setVisible(true);
				}
			}
		});
	}

	private void messagesButton() {
		DoctorPerspectiveView.messagesButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.doctorMessagesView) {
					new DoctorMessagesView();
					ViewClass.doctorMessagesView = true;
				} else {
					DoctorMessagesView.messagePanel.setVisible(true);
				}
			}
		});
	}

	private void appointmentButton() {
		DoctorPerspectiveView.appointmentsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!ViewClass.doctorAppointmentView) {
					new DoctorAppointmentsView();
					ViewClass.doctorAppointmentView = true;
				} else {
					DoctorAppointmentsView.tableModel.setRowCount(0);
					Database.showDoctorAppointments(LoginController.loggedInEmail);
					DoctorAppointmentsView.doctorAppointmentsPanel.setVisible(true);
				}
			}
		});
	}

}

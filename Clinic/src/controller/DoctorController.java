package controller;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import helper.ViewClass;
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

	public static ImageIcon resizeImageIcon(ImageIcon imageIcon) {
		BufferedImage bufferedImage = new BufferedImage(250, 180, BufferedImage.TRANSLUCENT);

		Graphics2D graphics2D = bufferedImage.createGraphics();
		graphics2D.drawImage(imageIcon.getImage(), 0, 0, 250, 180, null);
		graphics2D.dispose();

		return new ImageIcon(bufferedImage, imageIcon.getDescription());
	}

	private void profileButton() {
		DoctorView.profileButton.addMouseListener(new MouseAdapter() {
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
		DoctorView.pacientsButton.addMouseListener(new MouseAdapter() {
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

}

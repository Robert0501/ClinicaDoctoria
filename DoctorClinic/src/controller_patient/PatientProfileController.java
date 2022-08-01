package controller_patient;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import controller_doctor.DoctorPerspectiveController;
import controller_unlogin.LoginController;
import database.Database;
import helper.PasswordAuthentication;
import regex.RegEx;
import view_doctor.DoctorPerspectiveView;
import view_doctor.ProfileView;
import view_patient.PatientPerspectiveView;
import view_patient.PatientProfileView;

public class PatientProfileController {
	private JPasswordField oldPassword;
	private JPasswordField newPassword;
	private JPasswordField reNewPassword;

	private int passwordOption;

	public static JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	public static String photoPath;

	public PatientProfileController() {
		getName();
		getEmail();
		getPhoneNumber();
		getAddress();
		changePasswordButton();
		changePhotoButton();
		changePhoneNumber();
		changeAddressButton();
	}

	private void getName() {
		PatientProfileView.nameLabel.setText(Database.getPatientName(LoginController.loggedInEmail));
	}

	private void getEmail() {
		PatientProfileView.emailLabel.setText(LoginController.loggedInEmail);
	}

	private void getPhoneNumber() {
		PatientProfileView.phoneNumberLabel.setText(Database.getPatientPhoneNumber(LoginController.loggedInEmail));
	}

	private void getAddress() {
		PatientProfileView.addressLabel.setText(Database.getPatientAddress(LoginController.loggedInEmail));
	}

	private void changePasswordButton() {
		PatientProfileView.changePasswordButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showChangePasswordBox();
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void showChangePasswordBox() {
		oldPassword = new JPasswordField();
		newPassword = new JPasswordField();
		reNewPassword = new JPasswordField();
		Object[] passwordFields = { "Old Password: ", oldPassword, "New Password: ", newPassword, "Confirm Password:",
				reNewPassword };
		passwordOption = JOptionPane.showConfirmDialog(null, passwordFields, "Change Password",
				JOptionPane.OK_CANCEL_OPTION);

		if (passwordOption == JOptionPane.OK_OPTION) {
			if (!checkOldPassword()) {
				showChangePasswordBox();
			} else if (!checkNewPasswordRestrictions()) {
				showChangePasswordBox();
			} else if (!checkPasswordMatch()) {
				showChangePasswordBox();
			} else {
				Database.updatePassword(LoginController.loggedInEmail, newPassword.getText());
				JOptionPane.showMessageDialog(null,
						"Password was successfully changed. You can login with your new password!",
						"Password was successfully changed", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	@SuppressWarnings("deprecation")
	private boolean checkOldPassword() {
		if (PasswordAuthentication.authenticate(oldPassword.getText(),
				Database.getPassword(LoginController.loggedInEmail))) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Incorrect old password", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	private boolean checkNewPasswordRestrictions() {
		if (!RegEx.checkPassword(newPassword.getText())) {
			JOptionPane.showMessageDialog(null,
					"Passowrd should contains:\n- At Least 8 Characters\n- 1 Special Character\n- 1 Capital Letter",
					"Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("deprecation")
	private boolean checkPasswordMatch() {
		if (!newPassword.getText().equals(reNewPassword.getText())) {
			JOptionPane.showMessageDialog(null, "Passwords don't match", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	private void changePhotoButton() {
		PatientProfileView.changePhotoButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showSaveDialog(null);
				photoPath = fileChooser.getSelectedFile().getAbsolutePath();
				PatientProfileView.profilePhoto
						.setIcon(DoctorPerspectiveController.resizeImageIcon(new ImageIcon((photoPath))));
				PatientPerspectiveView.patientPhoto
						.setIcon(DoctorPerspectiveController.resizeImageIcon(new ImageIcon((photoPath))));
				Database.updateProfilePhotoPath(LoginController.loggedInEmail, photoPath);
			}
		});
	}

	private void changeAddressButton() {
		PatientProfileView.changeAddressButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String address = JOptionPane.showInputDialog("Insert new address");
				if (address.equals("")) {
					JOptionPane.showMessageDialog(null, "Address can not be empty", "Alert", JOptionPane.ERROR_MESSAGE);
				} else {
					Database.updatePatientAddress(LoginController.loggedInEmail, address);
					JOptionPane.showMessageDialog(null, "Address change successfully",
							"Name has been succesfully changed", JOptionPane.INFORMATION_MESSAGE);
					PatientProfileView.addressLabel.setText(Database.getPatientAddress(LoginController.loggedInEmail));

				}
			}
		});
	}

	private void changePhoneNumber() {
		PatientProfileView.changePhoneButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String number = JOptionPane.showInputDialog("Insert new phone number");
				if (number.equals("")) {
					JOptionPane.showMessageDialog(null, "Phone number can not be empty", "Alert",
							JOptionPane.ERROR_MESSAGE);
				} else if (!RegEx.checkPhoneNumber(number)) {
					JOptionPane.showMessageDialog(null, "Please enter a valid phone number", "Alert",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Database.updatePhoneNumber(LoginController.loggedInEmail, number);
					JOptionPane.showMessageDialog(null, "Phone number change successfully",
							"Phone Number has been succesfully changed", JOptionPane.INFORMATION_MESSAGE);
					PatientProfileView.phoneNumberLabel
							.setText(Database.getPatientPhoneNumber((LoginController.loggedInEmail)));

				}
			}
		});
	}
}

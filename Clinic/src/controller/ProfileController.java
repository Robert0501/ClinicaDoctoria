package controller;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import database.Database;
import regex.RegEx;
import view.DoctorView;
import view.ProfileView;

public class ProfileController {

	private JPasswordField oldPassword;
	private JPasswordField newPassword;
	private JPasswordField reNewPassword;

	private JTextField firstName;
	private JTextField middleName;
	private JTextField lastName;

	private int passwordOption;
	private int nameOption;

	public static JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	public static String photoPath;

	public ProfileController() {
		getName();
		getEmail();
		getAddress();
		getClinicName();
		changePasswordButton();
		changeNameButton();
		changePhotoButton();
		changeClinicNameButton();
	}

	private void getName() {
		ProfileView.nameLabel.setText("Dr. " + Database.getDoctorName(LoginController.loggedInEmail));
	}

	private void getEmail() {
		ProfileView.emailLabel.setText(LoginController.loggedInEmail);
	}

	private void getAddress() {
		ProfileView.addressLabel.setText(Database.getClinicAddress(LoginController.loggedInEmail));
	}

	private void getClinicName() {
		ProfileView.clinicName.setText(Database.getClinicName(LoginController.loggedInEmail));
	}

	private void changePasswordButton() {
		ProfileView.changePasswordButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showChangePasswordBox();
			}
		});
	}

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

	private boolean checkOldPassword() {
		if (Database.checkCredentials(LoginController.loggedInEmail, oldPassword.getText())) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Incorrect old password", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

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

	private boolean checkPasswordMatch() {
		if (!newPassword.getText().equals(reNewPassword.getText())) {
			JOptionPane.showMessageDialog(null, "Passwords don't match", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	private void changeNameButton() {
		ProfileView.changeNameButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showChangeNameBox();

			}
		});
	}

	private void showChangeNameBox() {
		lastName = new JTextField();
		middleName = new JTextField();
		firstName = new JTextField();

		Object[] nameField = { "Last Name:", lastName, "Middle Name:", middleName, "First Name", firstName };

		nameOption = JOptionPane.showConfirmDialog(null, nameField, "Change Name", JOptionPane.OK_CANCEL_OPTION);

		if (nameOption == JOptionPane.OK_OPTION) {
			if (!checkLastName()) {
				showChangeNameBox();
			} else if (!checkFirstName()) {
				showChangeNameBox();
			} else {
				Database.updateName(LoginController.loggedInEmail, firstName.getText(), lastName.getText());
				JOptionPane.showMessageDialog(null, "The name was successfully changed.", "Name successfully changed",
						JOptionPane.INFORMATION_MESSAGE);
				ProfileView.nameLabel.setText("Dr. " + lastName.getText() + " " + firstName.getText());
				DoctorView.nameLabel.setText("Dr. " + lastName.getText() + " " + firstName.getText());
			}
		}
	}

	private void changeClinicNameButton() {
		ProfileView.changePhoneNumberButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showChangeClinicNameBox();
			}
		});
	}

	private void showChangeClinicNameBox() {
		String clinicName = JOptionPane.showInputDialog("Insert new clinic name");
		if (clinicName.equals("")) {
			JOptionPane.showMessageDialog(null, "Clinic name can not be empty", "Alert", JOptionPane.ERROR_MESSAGE);
		} else {
			Database.updateClinicName(LoginController.loggedInEmail, clinicName);
			JOptionPane.showMessageDialog(null, "Clinic Name change successfully",
					"Clinic Name has been succesfully changed", JOptionPane.INFORMATION_MESSAGE);
			ProfileView.clinicName.setText(Database.getClinicName(LoginController.loggedInEmail));
		}
	}

	private boolean checkLastName() {
		if (lastName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Last Name can not be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	private boolean checkFirstName() {
		if (firstName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "First Name can not be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	private void changePhotoButton() {
		ProfileView.changePhotoButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showSaveDialog(null);
				photoPath = fileChooser.getSelectedFile().getAbsolutePath();
				System.out.println(photoPath);
				ProfileView.profilePhoto.setIcon(resizeImageIcon(new ImageIcon((photoPath))));
				DoctorView.docPhoto.setIcon(resizeImageIcon(new ImageIcon((photoPath))));
				Database.updateProfilePhotoPath(LoginController.loggedInEmail, photoPath);
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

}

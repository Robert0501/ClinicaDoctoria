package controller_unlogin;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import database.Database;
import helper.ViewClass;
import regex.RegEx;
import view_doctor.DoctorPerspectiveView;
import view_patient.PatientPerspectiveView;
import view_unlogin.LoginView;
import view_unlogin.RegisterView;

public class LoginController {

	public static String loggedInEmail = "";

	private JPasswordField newPassword;
	private JPasswordField reNewPassword;
	private int passwordOption;

	public LoginController() {
		loginButton();
		singUpButton();
	}

	private void loginButton() {
		LoginView.loginButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (Database.checkIfUserIsDoctor(LoginView.getEmail())) {
					doctorLogin();
				} else {
					pacientLogin();
				}
			}
		});

		LoginView.passwordIn.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					if (Database.checkIfUserIsDoctor(LoginView.getEmail())) {
						doctorLogin();
					} else {
						pacientLogin();
					}
				}
			}
		});
	}

	private void showPasswordBox() {
		newPassword = new JPasswordField();
		reNewPassword = new JPasswordField();
		Object[] passwordFields = { "Password:", newPassword, "Confirm Password:", reNewPassword };
		passwordOption = JOptionPane.showConfirmDialog(null, passwordFields, "Update Password",
				JOptionPane.OK_CANCEL_OPTION);

	}

	@SuppressWarnings("deprecation")
	private boolean checkPassword() {
		if (passwordOption == JOptionPane.OK_OPTION) {
			if (newPassword.getText().equals(reNewPassword.getText())) {
				if (!RegEx.checkPassword(newPassword.getText())) {
					JOptionPane.showMessageDialog(null,
							"Passowrd should contains:\n- At Least 8 Characters\n- 1 Special Character\n- 1 Capital Letter",
							"Alert", JOptionPane.ERROR_MESSAGE);
					return false;
				} else {
					return true;
				}

			} else {
				JOptionPane.showMessageDialog(null, "Passwords do not match", "Alert", JOptionPane.ERROR_MESSAGE);
				return false;
			}

		}
		return true;

	}

	private void pacientLogin() {
		if (!Database.checkIfUserIsDoctor(LoginView.getEmail())) {
			if (checkEmptyEmail()) {
				JOptionPane.showMessageDialog(null, "Username cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			} else if (checkEmptyPassowrd()) {
				JOptionPane.showMessageDialog(null, "Password cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			} else if (Database.checkCredentials(LoginView.getEmail(), LoginView.getPassword())) {
				if (!Database.checkActivatedAccount(LoginView.getEmail())) {
					String code = JOptionPane.showInputDialog("Insert the activation code you recieved via email");
					if (code.equals(String.valueOf(Database.getActivationCode(LoginView.getEmail())))) {
						showPasswordBox();
						while (!checkPassword()) {
							showPasswordBox();
						}
						JOptionPane.showMessageDialog(null, "Password has been successfully changed. You can login now",
								"Password Changed Successfully", JOptionPane.INFORMATION_MESSAGE);
						Database.setAccountTo(true, LoginView.getEmail());
						Database.updatePassword(LoginView.getEmail(), newPassword.getText());
					} else {
						JOptionPane.showMessageDialog(null, "The code you provided is wrong", "Alert",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Login successfully", "Login Successfully",
							JOptionPane.INFORMATION_MESSAGE);
					loggedInEmail = LoginView.getEmail();
					LoginView.setVisibility(false);
					LoginView.setEmail("");
					LoginView.setPassword("");
					if (!ViewClass.patientView) {
						new PatientPerspectiveView();
						ViewClass.patientView = true;
					} else {
						PatientPerspectiveView.patientFrame.setVisible(true);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Username or password are wrong", "Alert",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	private void doctorLogin() {
		if (Database.checkIfUserIsDoctor(LoginView.getEmail())) {
			if (checkEmptyEmail()) {
				JOptionPane.showMessageDialog(null, "Username cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			} else if (checkEmptyPassowrd()) {
				JOptionPane.showMessageDialog(null, "Password cannot be empty", "Alert", JOptionPane.ERROR_MESSAGE);
			} else if (Database.checkCredentials(LoginView.getEmail(), LoginView.getPassword())) {
				if (!Database.checkActivatedAccount(LoginView.getEmail())) {
					String code = JOptionPane.showInputDialog("Insert the activation code you recieved via email");
					if (code.equals(String.valueOf(Database.getActivationCode(LoginView.getEmail())))) {
						Database.setAccountTo(true, LoginView.getEmail());
						JOptionPane.showMessageDialog(null, "Login successfully", "Login Successfully",
								JOptionPane.INFORMATION_MESSAGE);
						LoginView.setVisibility(false);
						loggedInEmail = LoginView.getEmail();
						if (!ViewClass.doctorView) {
							new DoctorPerspectiveView();
							ViewClass.doctorView = true;
						} else {
							DoctorPerspectiveView.doctorFrame.setVisible(true);
						}
					} else {
						JOptionPane.showMessageDialog(null, "The code you provided is wrong", "Alert",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Login successfully", "Login Successfully",
							JOptionPane.INFORMATION_MESSAGE);
					loggedInEmail = LoginView.getEmail();
					LoginView.setVisibility(false);
					LoginView.setEmail("");
					LoginView.setPassword("");
					if (!ViewClass.doctorView) {
						new DoctorPerspectiveView();
						ViewClass.doctorView = true;
					} else {
						DoctorPerspectiveView.doctorFrame.setVisible(true);
						DoctorPerspectiveView.updateDataOnRelogin();
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Username or password are wrong", "Alert", JOptionPane.ERROR_MESSAGE);
		}

	}

	private boolean checkEmptyEmail() {
		if (LoginView.getEmail().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkEmptyPassowrd() {
		if (LoginView.getPassword().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	private void singUpButton() {
		LoginView.newAccountLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String code = JOptionPane.showInputDialog("Code");
				if (code.equals("1234")) {
					LoginView.setVisibility(false);
					setAllFieldsToEmpty();
					new RegisterView();
				} else {
					JOptionPane.showMessageDialog(null,
							"You are not allowed to create an account\nOnly doctors who have the code can create",
							"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void setAllFieldsToEmpty() {
		LoginView.setEmail("");
		LoginView.setPassword("");
	}
}

package controller_unlogin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.Database;
import email.Email;
import helper.Code;
import regex.RegEx;
import view_unlogin.LoginView;

public class ForgotPasswordController {

	public static String email;

	private String code;
	private JPasswordField newPassword;
	private JPasswordField reNewPassword;
	private JTextField emailIn;
	private JTextField codeIn;

	private int emailOption;
	private int codeOption;
	private int passwordOption;

	public ForgotPasswordController() {
		forgotPasswordButton();
	}

	private void forgotPasswordButton() {
		LoginView.forgotPasswordLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				checkFlow();
			}
		});
	}

	private void checkFlow() {
		showEmailBox();
		if (checkEmail()) {
			sendEmail();
			showCode();
			if (checkCode()) {
				showPasswordBox();
				if (checkPassword()) {
					changePassword();
				}
			}
		}
	}

	private void checkFromCode() {
		showCode();
		if (checkCode()) {
			showPasswordBox();
			if (checkPassword()) {
				changePassword();
			}
		}
	}

	private void checkFromPassword() {
		showPasswordBox();
		if (checkPassword()) {
			changePassword();
		}
	}

	private void showEmailBox() {
		emailIn = new JTextField();
		Object[] emailField = { "Email:", emailIn };
		emailOption = JOptionPane.showConfirmDialog(null, emailField, "Insert Email", JOptionPane.OK_CANCEL_OPTION);
		email = emailIn.getText();
	}

	private boolean checkEmail() {
		if (emailOption == JOptionPane.OK_OPTION) {
			if (Database.checkDuplicateEmail(email)) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Check that you wrote the email correctly and try again!", "Alert",
						JOptionPane.ERROR_MESSAGE);
				checkFlow();
				return false;
			}
		} else {
			return false;
		}
	}

	private void sendEmail() {
		Email.index = 3;
		Email.email[Email.position].start();
		Database.insertIntoPasswordInfoTable(email, Code.generateActivationCode());
		JOptionPane.showMessageDialog(null, "An email with a code has been sent to " + email);
	}

	private void showCode() {
		codeIn = new JTextField();
		Object[] codeField = { "Code:", codeIn };
		codeOption = JOptionPane.showConfirmDialog(null, codeField, "Insert Code", JOptionPane.OK_CANCEL_OPTION);
		code = String.valueOf(Database.getForgotPasswordCode(email));
	}

	private boolean checkCode() {
		if (codeOption == JOptionPane.OK_OPTION) {
			if (codeIn.getText().equals(String.valueOf(Database.getForgotPasswordCode(email)))) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "The code you entered is not correct. Please check and try again!",
						"Alert", JOptionPane.ERROR_MESSAGE);
				checkFromCode();
				return false;
			}
		} else {
			Database.setForgotFlagToFalse(email, code);
			return false;
		}
	}

	private void showPasswordBox() {
		newPassword = new JPasswordField();
		reNewPassword = new JPasswordField();
		Object[] passwordFields = { "Password:", newPassword, "Confirm Password:", reNewPassword };
		passwordOption = JOptionPane.showConfirmDialog(null, passwordFields, "Forgot Password",
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
					checkFromPassword();
					return false;
				} else {
					return true;
				}

			} else {
				JOptionPane.showMessageDialog(null, "Passwords do not match", "Alert", JOptionPane.ERROR_MESSAGE);
				checkFromPassword();
				return false;
			}
		} else {
			Database.setForgotFlagToFalse(email, code);
			return false;
		}

	}

	@SuppressWarnings("deprecation")
	private void changePassword() {
		Database.updatePassword(email, newPassword.getText());
		Database.setForgotFlagToFalse(email, code);
		JOptionPane.showMessageDialog(null, "Your password has been changed. You may log in with your new password.");
	}

}

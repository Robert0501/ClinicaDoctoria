package email;

import doctor_controller.ForgotPasswordController;
import doctor_view.NewPacientView;
import doctor_view.PacientDetailView;
import doctor_view.RegisterView;

public class EmailThread extends Thread {
	public void run() {

		switch (Email.index) {
		case 1:
			Email.sendMail(RegisterView.getEmail());
			Email.position++;
			break;
		case 2:
			Email.sendMail(RegisterView.getEmail());
			Email.position++;
			break;
		case 3:
			Email.sendMail(ForgotPasswordController.email);
			Email.position++;
			break;
		case 4:
			Email.sendMail(NewPacientView.emailIn.getText());
			Email.position++;
			break;
		case 5:
			Email.sendMail(PacientDetailView.emailIn.getText());
			Email.position++;
			break;
		}
	}
}
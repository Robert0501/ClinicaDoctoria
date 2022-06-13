package email;

import controller_unlogin.ForgotPasswordController;
import view_doctor.DoctorEmailSenderView;
import view_doctor.NewPacientView;
import view_doctor.PacientDetailView;
import view_patient.PatientEmailSenderView;
import view_unlogin.RegisterView;

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
		case 6:
			Email.sendMail(PatientEmailSenderView.emailToIn.getText());
			Email.position++;
		case 7:
			Email.sendMail(DoctorEmailSenderView.emailToIn.getText());
			Email.position++;
		}
	}
}
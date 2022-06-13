
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import database.Database;
import email.Email;
import view_doctor.DoctorDashboardView;
import view_unlogin.LoginView;

public class Main {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		new LoginView();
		new Email();
		new Database();

//		 new DoctorDashboardView();

		// new CreateNewAppointmentView();

	}

}

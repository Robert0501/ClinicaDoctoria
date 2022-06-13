package helper;

import view_doctor.DoctorAppointmentsView;
import view_doctor.DoctorDashboardView;
import view_doctor.DoctorMessagesView;
import view_doctor.DoctorPerspectiveView;
import view_doctor.DoctorShowMessageView;
import view_doctor.PacientView;
import view_doctor.ProfileView;
import view_patient.ContactDoctorView;
import view_patient.PacientMessageView;
import view_patient.PatientAppointmentsView;
import view_patient.TestHistoryView;

public class ViewClass {
	public static boolean addressRegisterView = false;
	public static boolean loginView = false;
	public static boolean registerView = false;

	public static boolean doctorView = false;
	public static boolean newPacientView = false;
	public static boolean pacientDetailView = false;
	public static boolean pacientView = false;
	public static boolean profileView = false;
	public static boolean doctorAppointmentView = false;
	public static boolean doctorMessagesView = false;
	public static boolean doctorMessageView = false;
	public static boolean doctorEmailSenderView = false;
	public static boolean dashboardView = false;

	public static boolean patientView = false;
	public static boolean testHistoryView = false;
	public static boolean patientAppointmentsView = false;
	public static boolean patientContactDoctorView = false;
	public static boolean patientEmailSenderFrame = false;
	public static boolean patientMessageView = false;
	public static boolean patientShowMessageView = false;
	public static boolean patientProfileView = false;

	public static void setDoctorViewsToFalse() {
		if (dashboardView) {
			DoctorDashboardView.dashboardPanel.setVisible(false);
		}
		if (doctorAppointmentView) {
			DoctorAppointmentsView.doctorAppointmentsPanel.setVisible(false);
		}

		if (pacientView) {
			PacientView.pacientPanel.setVisible(false);
		}
		if (doctorMessagesView) {
			DoctorMessagesView.messagePanel.setVisible(false);
		}
		if (profileView) {
			ProfileView.profilePanel.setVisible(false);
		}

	}

	public static void setPatientViewsOnFalse() {
		if (patientMessageView) {
			PacientMessageView.messagePanel.setVisible(false);
		}
		if (patientAppointmentsView) {
			PatientAppointmentsView.patientAppointmentsPanel.setVisible(false);
		}
		if (testHistoryView) {
			TestHistoryView.testHistoryPanel.setVisible(false);
		}
		if (patientContactDoctorView) {
			ContactDoctorView.contactDoctorPanel.setVisible(false);
		}
		if (patientProfileView) {

		}
	}

}

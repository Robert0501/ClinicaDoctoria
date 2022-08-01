package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.postgresql.util.PSQLException;

import controller_doctor.PacientDetailController;
import document.WordDocument;
import helper.PasswordAuthentication;
import model.MedicalResults;
import model.Patient;
import view_doctor.DoctorAppointmentsView;
import view_doctor.DoctorMessagesView;
import view_doctor.PacientView;
import view_patient.PatientMessageView;
import view_patient.PatientAppointmentsView;
import view_patient.TestHistoryView;

public class Database {
	public static Connection connection = null;
	public static Statement statement = null;
	public static ResultSet rs = null;

	public Database() {
		connectDB();
		new CreateDatabase();

	}

	private void connectDB() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PacientManagement", "postgres",
					"Laurentiu02");
			if (connection == null) {
				JOptionPane.showMessageDialog(null, "Something went wrong.Please try again");
				System.exit(0);
			}

			System.out.println("Connexion Estabilished");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkDuplicateEmail(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT email FROM Users WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean checkCredentials(String email, String password) {
		try {
			statement = connection.createStatement();
			String query = "SELECT email, password FROM Users WHERE email = '" + email + "' AND password = '"
					+ PasswordAuthentication.hash(password) + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static String getPassword(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT password FROM Users WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public static int getActivationCode(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT activation_code FROM Users WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return Integer.parseInt(rs.getString("activation_Code"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static void getTestResults() {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM medical_test_values";
			rs = statement.executeQuery(query);
			int i = 0;
			while (rs.next()) {
				PacientDetailController.pacientData[i][0] = rs.getString("medical_test_name");
				PacientDetailController.pacientData[i][2] = rs.getString("medical_test_unit");
				PacientDetailController.pacientData[i][3] = rs.getString("normal_values");
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getDoctorEmail(String patientEmail) {
		try {
			statement = connection.createStatement();
			String query = "SELECT doctor_email FROM Pacient WHERE email = '" + patientEmail + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("doctor_email");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getTestPath(String name) {
		try {
			statement = connection.createStatement();
			String query = "SELECT test_result_path FROM testhistory WHERE document_name = '" + name + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getString("test_result_path"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getPatientPhoneNumber(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT phone_number FROM Pacient WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return (rs.getString("phone_number"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getPatientAddress(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT country, address FROM Pacient WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return (rs.getString("country") + " " + rs.getString("address"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void getDoctorDetailsForWordDocument(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT first_name, last_name, clinic_name, clinic_address FROM Doctor WHERE email = '"
					+ email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				WordDocument.doctorNameString = rs.getString("first_name") + " " + rs.getString("last_name");
				WordDocument.clinicNameString = rs.getString("clinic_name");
				WordDocument.clinicAddressString = rs.getString("clinic_address");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void getPacientDetailsForWordDocument(String cnp) {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM Pacient WHERE cnp = '" + cnp + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				WordDocument.patientNameString = rs.getString("first_name") + " " + rs.getString("last_name");
				WordDocument.patientCnp = rs.getString("cnp");
				WordDocument.patientDob = rs.getString("date_of_birth");
				WordDocument.patientPhoneNumber = rs.getString("phone_number");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void getBasicTestResults(String cnp) {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM Medicaltest WHERE cnp = '" + cnp + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				WordDocument.patientBloodPressure = rs.getString("ta");
				WordDocument.patientbpm = rs.getString("av");
				WordDocument.patientWeight = rs.getString("weight");
				WordDocument.patientHeight = rs.getString("height");
				WordDocument.patientCircumference = rs.getString("circumference");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getClinicName(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT clinic_name FROM Doctor WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("clinic_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void getFullTestResults(String cnp) {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM medicaltest WHERE cnp = '" + cnp + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				PacientDetailController.pacientData[0][1] = rs.getString("Glucose");
				PacientDetailController.pacientData[1][1] = rs.getString("Cholesterol");
				PacientDetailController.pacientData[2][1] = rs.getString("LDL_C");
				PacientDetailController.pacientData[3][1] = rs.getString("HDL_C");
				PacientDetailController.pacientData[4][1] = rs.getString("Triglicerides");
				PacientDetailController.pacientData[5][1] = rs.getString("Potassium");
				PacientDetailController.pacientData[6][1] = rs.getString("Sodium");
				PacientDetailController.pacientData[7][1] = rs.getString("Uric_Acid");
				PacientDetailController.pacientData[8][1] = rs.getString("Creatinine");
				PacientDetailController.pacientData[9][1] = rs.getString("Urinary_Protein");
				PacientDetailController.pacientData[10][1] = rs.getString("Urinary_Creatinine");
				PacientDetailController.pacientData[11][1] = rs.getString("Microalbuminuria");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void showPacients(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM Pacient WHERE doctor_email = '" + email + "' ;";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				PacientView.model.addRow(new Object[] { rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("cnp"), rs.getString("date_of_birth"), rs.getString("email"),
						rs.getString("phone_number"), rs.getString("country"), rs.getString("address") });

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void showDoctorEmails(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM message WHERE email_to = '" + email + "' OR email_from = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				DoctorMessagesView.model.addRow(new Object[] { rs.getString("sent_date"), rs.getString("sent_hour"),
						rs.getString("email_from"), rs.getString("patient_name"), rs.getString("subject") });

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showPatientEmails(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM message WHERE email_to = '" + email + "' OR email_from = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				PatientMessageView.model.addRow(new Object[] { rs.getString("sent_date"), rs.getString("sent_hour"),
						rs.getString("email_from"), rs.getString("patient_name"), rs.getString("subject") });

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showPacientAppointments(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM Appointments WHERE patient_email = '" + email + "' ;";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				PatientAppointmentsView.model.addRow(new Object[] { rs.getString("appointment_date"),
						rs.getString("appointment_time"), rs.getString("reason"), rs.getString("status") });

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void updateAppointmentStatusColumn(String status, String reason, String date, String hour) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE Appointments SET status = '" + status + "' WHERE (reason = '" + reason
					+ "' AND appointment_date = '" + date + "' AND appointment_time = '" + hour + "');";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void showDoctorAppointments(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM Appointments WHERE doctor_email = '" + email + "' ;";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				DoctorAppointmentsView.model
						.addRow(new Object[] { rs.getString("patient_name"), rs.getString("appointment_date"),
								rs.getString("appointment_time"), rs.getString("reason"), rs.getString("status") });

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void showTestResults(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM TestHistory WHERE patient_email = '" + email + "' ;";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				TestHistoryView.model.addRow(new Object[] { rs.getString("test_date"), rs.getString("test_hour"),
						rs.getString("document_name") });

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getClinicAddress(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT clinic_address FROM Doctor WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("clinic_address");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getProfilePhotoPath(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT photoPath FROM Users WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("photoPath");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getDoctorName(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT first_name, last_name FROM Doctor WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("first_name") + " " + rs.getString("last_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getPatientName(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT first_name, last_name FROM Pacient WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("first_name") + " " + rs.getString("last_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getDoctorPhotoFromPatientEmail(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT photopath FROM Users WHERE email = (SELECT doctor_email FROM Pacient where email = '"
					+ email + "');";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("photopath");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getDoctorClinicFromPatientEmail(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT clinic_name FROM Doctor WHERE email = (SELECT doctor_email FROM Pacient where email = '"
					+ email + "');";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("clinic_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getDoctorClinicAddressromPatientEmail(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT clinic_address FROM Doctor WHERE email = (SELECT doctor_email FROM Pacient where email = '"
					+ email + "');";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("clinic_address");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getDoctorEmailFromPatientEmail(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT email FROM Doctor WHERE email = (SELECT doctor_email FROM Pacient where email = '"
					+ email + "');";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("email");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getDoctorNameFromPatientEmail(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT first_name, last_name FROM Doctor WHERE email = (SELECT doctor_email FROM Pacient where email = '"
					+ email + "');";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("first_name") + " " + rs.getString("last_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getMessageDetails(String email, String sent_date, String sent_hour, String column) {
		try {
			statement = connection.createStatement();
			String query = "SELECT " + column + " FROM Message WHERE (email_to = '" + email + "' OR email_from = '"
					+ email + "') AND sent_date = '" + sent_date + "' AND sent_hour = '" + sent_hour + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString(column);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Patient getPacientDetails(String cnp) {
		try {
			statement = connection.createStatement();
			String query = "Select * from pacient where cnp = '" + cnp + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return new Patient(rs.getString("first_name"), rs.getString("last_name"), rs.getString("cnp"),
						rs.getString("date_of_birth"), rs.getString("phone_number"), rs.getString("email"),
						rs.getString("country"), rs.getString("address"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static MedicalResults getMedicalResults(String cnp) {
		try {
			statement = connection.createStatement();
			String query = "Select * from MedicalTest where cnp = '" + cnp + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return new MedicalResults(rs.getString("ta"), rs.getString("av"), rs.getString("weight"),
						rs.getString("height"), rs.getString("imc"), rs.getString("circumference"),
						rs.getString("glucose"), rs.getString("cholesterol"), rs.getString("ldl_c"),
						rs.getString("hdl_c"), rs.getString("triglicerides"), rs.getString("potassium"),
						rs.getString("sodium"), rs.getString("uric_acid"), rs.getString("creatinine"),
						rs.getString("microalbuminuria"), rs.getString("urinary_protein"),
						rs.getString("urinary_creatinine"), rs.getString("risk_factor"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean checkActivatedAccount(String email) {
		String value = "";
		try {
			statement = connection.createStatement();
			String query = "SELECT is_activated FROM Users WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				value = rs.getString("is_activated");
				if (value.equals("t")) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean checkIfUserIsDoctor(String email) {
		String value = "";
		try {
			statement = connection.createStatement();
			String query = "SELECT is_doctor FROM Users WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				value = rs.getString("is_doctor");
				if (value.equals("t")) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static String getPatientNumber(String email, String month, String year) {
		try {
			statement = connection.createStatement();
			String query = "SELECT COUNT(CNP) FROM Pacient WHERE doctor_email = '" + email + "' AND added_month = '"
					+ month + "' AND added_year = '" + year + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getPatientNumber(String email, String day, String month, String year) {
		try {
			statement = connection.createStatement();
			String query = "SELECT COUNT(CNP) FROM Pacient WHERE doctor_email = '" + email + "' AND added_month = '"
					+ month + "' AND added_year = '" + year + "' AND added_day = '" + day + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				System.out.println("month = " + month + " day = " + day + " year = " + year);
				System.out.println(rs.getString("count"));
				return rs.getString("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getPatientNumberWeekly(String email, String day, String month, String year) {
		try {
			statement = connection.createStatement();
			String query = "SELECT COUNT(CNP) FROM Pacient WHERE doctor_email = '" + email + "' added_day = '" + day
					+ "' AND added_month = '" + month + "' AND added_year = '" + year + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void setAccountTo(boolean value, String email) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE Users SET is_activated = " + value + " WHERE email = '" + email + "';";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setPasswordFlagToFalse(String email) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE PasswordInfo SET is_activated = " + false + " WHERE email = '" + email + "';";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateProfilePhotoPath(String email, String newPath) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE Users SET photopath = '" + newPath + "' WHERE email = '" + email + "';";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateClinicName(String email, String clinicName) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE Doctor SET clinic_name = '" + clinicName + "' WHERE email = '" + email + "';";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateClinicAddress(String email, String clinicAddress) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE Doctor SET clinic_address = '" + clinicAddress + "' WHERE email = '" + email + "';";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updatePatientAddress(String email, String address) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE Pacient SET address = '" + address + "' WHERE email = '" + email + "';";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateMedicalResults(String cnp, MedicalResults mr) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE MedicalTest SET ta = '" + mr.getTa() + "', av = '" + mr.getAv() + "'," + " weight ='"
					+ mr.getWeight() + "', height = '" + mr.getHeight() + "'," + " imc = '" + mr.getImc()
					+ "', circumference = '" + mr.getCircumference() + "'," + "glucose ='" + mr.getGlucose()
					+ "', cholesterol ='" + mr.getCholesterol() + "'," + "ldl_c = '" + mr.getLdl_c() + "'," + "hdl_c ='"
					+ mr.getHdl_c() + "', triglicerides = '" + mr.getTriglicerides() + "" + "', potassium = '"
					+ mr.getPotassium() + "', sodium = '" + mr.getSodium() + "', uric_acid ='" + mr.getUric_acid()
					+ "', creatinine ='" + mr.getCreatinine() + "'," + " microalbuminuria = '"
					+ mr.getMicroalbuminuria() + "', urinary_protein ='" + mr.getUrinary_protein()
					+ "', urinary_creatinine = '" + mr.getUrinary_creatinine() + "', risk_factor = '"
					+ mr.getRisk_factor() + "' where cnp = '" + cnp + "';";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkTimeForAppointment(String date, String time, String doctorEmail) {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM Appointments WHERE appointment_date = '" + date + "' AND appointment_time = '"
					+ time + "' AND doctor_email = '" + doctorEmail + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static int getForgotPasswordCode(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT forgot_code FROM PasswordInfo WHERE email = '" + email + "' AND forgot_flag = true;";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return Integer.parseInt(rs.getString("forgot_code"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static void setForgotFlagToFalse(String email, String code) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE passwordinfo SET forgot_flag = " + false + " WHERE email = '" + email
					+ "' AND forgot_code = '" + code + "';";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updatePassword(String email, String password) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE Users SET password = '" + PasswordAuthentication.hash(password) + "' WHERE email = '"
					+ email + "';";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateName(String email, String firstName, String lastName) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE Doctor SET first_name = '" + firstName + "' , last_name = '" + lastName
					+ "' WHERE email = '" + email + "';";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updatePhoneNumber(String email, String phone_number) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE Pacient SET phone_number = '" + phone_number + "' WHERE email = '" + email + "';";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deletePacient(String cnp, String doctorEmail) {
		try {
			statement = connection.createStatement();
			String query = "DELETE FROM  Pacient WHERE cnp = '" + cnp + "' and doctor_email = '" + doctorEmail + "';";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

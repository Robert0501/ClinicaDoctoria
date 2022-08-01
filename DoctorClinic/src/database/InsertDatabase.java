package database;

import javax.swing.JOptionPane;

import org.postgresql.util.PSQLException;

import controller_doctor.NewPacientController;
import controller_unlogin.LoginController;
import helper.PasswordAuthentication;
import model.Doctor;
import model.User;

public class InsertDatabase {

	public static void insertIntoDoctorTABLE(Doctor doctor, String address) {
		try {
			Database.statement = Database.connection.createStatement();
			String query = "INSERT INTO Doctor VALUES(" + "'" + doctor.getFirstName() + "'," + "'"
					+ doctor.getLastName() + "','" + doctor.getEmail() + "','" + doctor.getClinicName() + "','"
					+ address + "');";
			Database.statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoUserTable(User user, boolean isDoctor) {
		try {
			Database.statement = Database.connection.createStatement();
			String query;
			if (isDoctor) {
				query = "INSERT INTO Users VALUES(" + "'" + user.getEmail() + "'," + "'"
						+ PasswordAuthentication.hash(user.getPassword()) + "'," + "'" + user.getActivationCode() + "',"
						+ "'" + false + "','" + isDoctor + "', 'src//Images//Icon//docPic.png" + "');";
			} else {
				query = "INSERT INTO Users VALUES(" + "'" + user.getEmail() + "'," + "'"
						+ PasswordAuthentication.hash(user.getPassword()) + "'," + "'" + user.getActivationCode() + "',"
						+ "'" + false + "','" + isDoctor + "', 'src//Images//Icon//pacient.jpg" + "');";
			}
			Database.statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoMessageTable(String from, String to, String subject, String text, String sentDate,
			String sentHour, String patient_name) {
		try {
			Database.statement = Database.connection.createStatement();
			String query = "INSERT INTO Message VALUES('" + from + "','" + to + "','" + subject + "','" + text + ""
					+ "','" + sentDate + "','" + sentHour + "','" + patient_name + "');";
			Database.statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoMedicalTestTable(String TA, String AV, String weight, String height, String imc,
			String circumference, String glucose, String cholesterol, String ldl_c, String hdl_c, String triglicerides,
			String potassium, String sodium, String uric_acid, String creatinine, String microalbuminuria,
			String urinary_protein, String urinary_creatinine, String risc_factor, String cnp) {
		try {

			Database.statement = Database.connection.createStatement();
			String query = "INSERT INTO MedicalTest VALUES(" + "'" + TA + "','" + AV + "','" + weight + "','" + height
					+ "','" + imc + "','" + circumference + "','" + glucose + "','" + cholesterol + "','" + ldl_c
					+ "','" + hdl_c + "','" + triglicerides + "','" + potassium + "','" + sodium + "','" + uric_acid
					+ "','" + creatinine + "','" + microalbuminuria + "','" + urinary_protein + "','"
					+ urinary_creatinine + "','" + risc_factor + "','" + cnp + "');";
			Database.statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoPasswordInfoTable(String email, String code) {
		try {
			Database.statement = Database.connection.createStatement();
			String query = "INSERT INTO PasswordInfo VALUES(" + "'" + email + "'," + "'" + code + "'," + "'true');";
			Database.statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoTestHistoryTable(String patient_email, String doctor_email, String path,
			String documentName, String date, String hour) {
		try {
			Database.statement = Database.connection.createStatement();
			String query = "INSERT INTO TestHistory VALUES( '" + patient_email + "','" + doctor_email + "','" + path
					+ "','" + documentName + "','" + date + "','" + hour + "');";
			Database.statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoPacientTable(String firstName, String lastName, String cnp, String dob, String email,
			String phone, String country, String address, String month, String year, String day) {
		try {
			Database.statement = Database.connection.createStatement();
			String query = "INSERT INTO Pacient VALUES( '" + firstName + "','" + lastName + "','" + cnp + "','" + dob
					+ "','" + email + "','" + phone + "','" + country + "','" + address + "','"
					+ LoginController.loggedInEmail + "','" + month + "','" + year + "','" + day + "');";
			Database.statement.execute(query);
		} catch (PSQLException e) {
			JOptionPane.showMessageDialog(null, "There already is a pacient with this CNP", "Alert",
					JOptionPane.ERROR_MESSAGE);
			NewPacientController.pacientExist = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoAppointmentsTable(String patient_email, String patient_name, String doctor_email,
			String date, String hour, String reason) {
		try {
			Database.statement = Database.connection.createStatement();
			String query = "INSERT INTO Appointments VALUES (" + "'" + patient_email + "','" + patient_name + "','"
					+ doctor_email + "','" + date + "','" + hour + "','" + reason + "','" + "Pending');";
			Database.statement.execute(query);
		} catch (PSQLException e) {
			JOptionPane.showMessageDialog(null, "There already is a pacient with this CNP", "Alert",
					JOptionPane.ERROR_MESSAGE);
			NewPacientController.pacientExist = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package database;

import org.postgresql.util.PSQLException;

public class CreateDatabase {

	public CreateDatabase() {
		createDoctorTable();
		createUserTable();
		createPasswordInfoTable();
		createPacientTable();
		createTestsTable();
		createTestHistoryTable();
		createAppointmentsTable();
		createMessageTable();
	}

	private void createDoctorTable() {
		try {
			Database.statement = Database.connection.createStatement();
			String query = "CREATE TABLE Doctor(" + "first_name text not null, "
					+ "last_name text not null, " + "email text not null , "
					+ "clinic_name text, " + "clinic_address text," + "primary key(email) );";
			Database.statement.execute(query);
			System.out.println("Doctor Table Created");
		} catch (PSQLException e) {
			System.out.println("Doctor Table is already created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createUserTable() {
		try {
			Database.statement = Database.connection.createStatement();
			String query = "CREATE TABLE Users (" + "email varchar(100) not null, " + "password varchar(100) not null, "
					+ "activation_code varchar(10) not null , "
					+ "is_activated boolean not null, photoPath varchar(200)," + "primary key(email) );";
			Database.statement.execute(query);
			System.out.println("User Table Created");
		} catch (PSQLException e) {
			System.out.println("User Table is already created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createMessageTable() {
		try {
			Database.statement = Database.connection.createStatement();
			String query = "CREATE TABLE Message (" + "email_from varchar(100)," + "email_to varchar(100),"
					+ "subject varchar(100)," + "text varchar(10000)," + "sent_date varchar(12),"
					+ "sent_hour varchar(12), patient_name text );";
			Database.statement.execute(query);
			System.out.println("Message Table Created");
		} catch (PSQLException e) {
			System.out.println("Message Table is already created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createTestsTable() {
		try {
			Database.statement = Database.connection.createStatement();
			String query = "CREATE TABLE MedicalTest ("
					+ "TA varchar(5), AV varchar(5), weight varchar(5), height varchar(5), imc varchar(5), circumference varchar(5),"
					+ "glucose varchar(5), cholesterol varchar(5), LDL_C varchar(5), HDL_C varchar(5), triglicerides varchar(5),"
					+ "potassium varchar(5), sodium varchar(5), uric_acid varchar(5), creatinine varchar(5), microalbuminuria varchar(5),"
					+ "urinary_protein varchar(5), urinary_creatinine varchar(5), risk_factor varchar(7), cnp varchar(15)"
					+ ");";
			Database.statement.execute(query);
			System.out.println("MedicalTest Table Created");
		} catch (PSQLException e) {
			System.out.println("MedicalTest Table is already created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createPasswordInfoTable() {
		try {
			Database.statement = Database.connection.createStatement();
			String query = "CREATE TABLE PasswordInfo (" + "email varchar(100) not null"
					+ ",forgot_code varchar(10) not null, " + "forgot_flag boolean not null);";
			Database.statement.execute(query);
			System.out.println("PasswordInfo Table Created");
		} catch (PSQLException e) {
			System.out.println("PasswordInfo Table is already created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createTestHistoryTable() {
		try {
			Database.statement = Database.connection.createStatement();
			String query = "CREATE TABLE TestHistory (patient_email varchar(100), doctor_email varchar(100), "
					+ "test_result_path varchar(200),document_name varchar(200), test_date varchar(15), test_hour varchar(15) );";
			Database.statement.execute(query);
			System.out.println("Test History Table Created");
		} catch (PSQLException e) {
			System.out.println("Test History Table is already created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createPacientTable() {
		try {
			Database.statement = Database.connection.createStatement();
			String query = "CREATE TABLE Pacient (" + "first_name varchar(50)," + "last_name varchar(100),"
					+ "CNP varchar(17)," + "Date_of_Birth varchar(12)," + "email varchar(100),"
					+ "phone_number varchar (13)," + "country varchar(50)," + "address varchar(100),"
					+ "doctor_email varchar(100), added_month text, added_year text ,  added_day text);";
			Database.statement.execute(query);
			System.out.println("Pacient Table Created");
		} catch (PSQLException e) {
			System.out.println("Pacient Table is already created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createAppointmentsTable() {
		try {
			Database.statement = Database.connection.createStatement();
			String query = "CREATE TABLE Appointments (" + "patient_email varchar(100)," + "patient_name varchar(100),"
					+ "doctor_email varchar(100)," + "appointment_date varchar(50)," + "appointment_time varchar(50),"
					+ "Reason varchar(200)," + "Status varchar(20) );";
			Database.statement.execute(query);
			System.out.println("Appointments Table Created");
		} catch (PSQLException e) {
			System.out.println("Appointments Table is already created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

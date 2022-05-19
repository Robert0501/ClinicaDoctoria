package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.postgresql.util.PSQLException;

import controller.LoginController;
import controller.NewPacientController;
import model.Address;
import model.Doctor;
import model.MedicalResults;
import model.Pacient;
import model.User;
import view.PacientView;

public class Database {
	static Connection connection = null;
	static Statement statement = null;
	static ResultSet rs = null;

	public Database() {
		connectDB();
		createDoctorTable();
		createUserTable();
		createPasswordInfoTable();
		createAddressTable();
		createPacientTable();
		createTestsTable();
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

	private void createDoctorTable() {
		try {
			statement = connection.createStatement();
			String query = "CREATE TABLE Doctor(" + "first_name varchar(100) not null, "
					+ "last_name varchar(100) not null, " + "email varchar(100) not null , "
					+ "clinic_name varchar(50), " + "photoPath varchar(100)," + "primary key(email) );";
			statement.execute(query);
			System.out.println("Doctor Table Created");
		} catch (PSQLException e) {
			System.out.println("Doctor Table is already created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoDoctorTABLE(Doctor doctor) {
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO Doctor VALUES(" + "'" + doctor.getFirstName() + "'," + "'"
					+ doctor.getLastName() + "','" + doctor.getEmail() + "','" + doctor.getClinicName()
					+ "', 'src//Images//Icon//docPic.png');";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createUserTable() {
		try {
			statement = connection.createStatement();
			String query = "CREATE TABLE Users (" + "email varchar(100) not null, " + "password varchar(100) not null, "
					+ "activation_code varchar(10) not null , " + "is_activated boolean not null,"
					+ "primary key(email) );";
			statement.execute(query);
			System.out.println("User Table Created");
		} catch (PSQLException e) {
			System.out.println("User Table is already created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoUserTable(User user) {
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO Users VALUES(" + "'" + user.getEmail() + "'," + "'" + user.getPassword() + "',"
					+ "'" + user.getActivationCode() + "'," + "'" + false + "');";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createTestsTable() {
		try {
			statement = connection.createStatement();
			String query = "CREATE TABLE MedicalTest ("
					+ "TA varchar(5), AV varchar(5), weight varchar(5), height varchar(5), imc varchar(5), circumference varchar(5),"
					+ "glucose varchar(5), cholesterol varchar(5), LDL_C varchar(5), HDL_C varchar(5), triglicerides varchar(5),"
					+ "potassium varchar(5), sodium varchar(5), uric_acid varchar(5), creatinine varchar(5), microalbuminuria varchar(5),"
					+ "urinary_protein varchar(5), urinary_creatinine varchar(5), risk_factor varchar(7), cnp varchar(15)"
					+ ");";
			statement.execute(query);
			System.out.println("MedicalTest Table Created");
		} catch (PSQLException e) {
			System.out.println("MedicalTest Table is already created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoMedicalTestTable(String TA, String AV, String weight, String height, String imc,
			String circumference, String glucose, String cholesterol, String ldl_c, String hdl_c, String triglicerides,
			String potassium, String sodium, String uric_acid, String creatinine, String microalbuminuria,
			String urinary_protein, String urinary_creatinine, String risc_factor, String cnp) {
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO MedicalTest VALUES(" + "'" + TA + "','" + AV + "','" + weight + "','" + height
					+ "','" + imc + "','" + circumference + "','" + glucose + "','" + cholesterol + "','" + ldl_c
					+ "','" + hdl_c + "','" + triglicerides + "','" + potassium + "','" + sodium + "','" + uric_acid
					+ "','" + creatinine + "','" + microalbuminuria + "','" + urinary_protein + "','"
					+ urinary_creatinine + "','" + risc_factor + "','" + cnp + "');";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createPasswordInfoTable() {
		try {
			statement = connection.createStatement();
			String query = "CREATE TABLE PasswordInfo (" + "email varchar(100) not null"
					+ ",forgot_code varchar(10) not null, " + "forgot_flag boolean not null);";
			statement.execute(query);
			System.out.println("PasswordInfo Table Created");
		} catch (PSQLException e) {
			System.out.println("PasswordInfo Table is already created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoPasswordInfoTable(String email, String code) {
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO PasswordInfo VALUES(" + "'" + email + "'," + "'" + code + "'," + "'true');";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createAddressTable() {
		try {
			statement = connection.createStatement();
			String query = "CREATE TABLE Address (" + "clinic_name varchar(50)," + "country varchar(50),"
					+ "city varchar(50)," + "street varchar(50)," + "address varchar(100), email varchar(100) );";
			statement.execute(query);
			System.out.println("Address Table Created");
		} catch (PSQLException e) {
			System.out.println("Address Table is already created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoAddressTable(String clinicName, Address address, String email) {
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO Address values( '" + clinicName + "','" + address.getCountry() + "','"
					+ address.getCity() + "','" + address.getStreet() + "','" + address.getAddress() + "','" + email
					+ "');";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createPacientTable() {
		try {
			statement = connection.createStatement();
			String query = "CREATE TABLE Pacient (" + "first_name varchar(50)," + "last_name varchar(100),"
					+ "CNP varchar(17)," + "Date_of_Birth varchar(12)," + "email varchar(100),"
					+ "phone_number varchar (13)," + "country varchar(50)," + "address varchar(100),"
					+ "doctor_email varchar(100) );";
			statement.execute(query);
			System.out.println("Pacient Table Created");
		} catch (PSQLException e) {
			System.out.println("Pacient Table is already created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoPacientTable(String firstName, String lastName, String cnp, String dob, String email,
			String phone, String country, String address) {
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO Pacient VALUES( '" + lastName + "','" + firstName + "','" + cnp + "','" + dob
					+ "','" + email + "','" + phone + "','" + country + "','" + address + "','"
					+ LoginController.loggedInEmail + "');";
			statement.execute(query);
		} catch (PSQLException e) {
			JOptionPane.showMessageDialog(null, "There already is a pacient with this CNP", "Alert",
					JOptionPane.ERROR_MESSAGE);
			NewPacientController.pacientExist = true;
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
			String query = "SELECT email, password FROM Users WHERE email = '" + email + "' AND password = '" + password
					+ "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
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

	public static String getClinicName(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT clinic_name FROM Address WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("clinic_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
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

	public static String getClinicAddress(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT country, city, street, address FROM Address WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("country") + " " + rs.getString("city") + " " + rs.getString("street") + " "
						+ rs.getString("address");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getProfilePhotoPath(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT photoPath FROM Doctor WHERE email = '" + email + "';";
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

	public static Pacient getPacientDetails(String cnp) {
		try {
			statement = connection.createStatement();
			String query = "Select * from pacient where cnp = '" + cnp + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return new Pacient(rs.getString("first_name"), rs.getString("last_name"), rs.getString("cnp"),
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
			String query = "UPDATE Doctor SET photopath = '" + newPath + "' WHERE email = '" + email + "';";
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
			String query = "UPDATE Address SET clinic_name = '" + clinicName + "' WHERE email = '" + email + "';";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateClinicAddress(String email, Address address) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE Address SET country ='" + address.getCountry() + "', " + "city ='"
					+ address.getCity() + "', street = '" + address.getStreet() + "'," + "address = '"
					+ address.getAddress() + "';";
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
					+ mr.getPotassium() + "', uric_acid ='" + mr.getUric_acid() + "', creatinine ='"
					+ mr.getCreatinine() + "'," + " microalbuminuria = '" + mr.getMicroalbuminuria()
					+ "', urinary_protein ='" + mr.getUrinary_protein() + "', urinary_creatinine = '"
					+ mr.getUrinary_creatinine() + "', risk_factor = '" + mr.getRisk_factor() + "' where cnp = '" + cnp
					+ "';";
			statement.execute(query);
		} catch (PSQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			String query = "UPDATE Users SET password = '" + password + "' WHERE email = '" + email + "';";
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

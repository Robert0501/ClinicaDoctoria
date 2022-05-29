package controller_doctor;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import controller_unlogin.LoginController;
import database.Database;
import document.WordDocument;
import email.Email;
import model.MedicalResults;
import model.Pacient;
import view_doctor.PacientDetailView;

public class PacientDetailController {

	int counter = 0;

	private static final DecimalFormat df = new DecimalFormat("0.00");

	public static String pacientData[][] = new String[12][4];
	public static String fileName;

	public static boolean testResultValues[] = new boolean[12];

	public PacientDetailController() {

		for (int i = 0; i < pacientData.length; i++) {
			for (int j = 0; j < pacientData[i].length; j++) {
				pacientData[i][j] = new String();
			}
		}

		autocompletePersonalDetailsField();
		autocompleteMedicalResults();
		colorWrongValues();
		saveDataButton();
		downloadTestResults();

	}

	public static void autocompletePersonalDetailsField() {
		Pacient selectedPacient = Database.getPacientDetails(PacientController.cnp);
		PacientDetailView.firstNameIn.setText(selectedPacient.getFirstName());
		PacientDetailView.lastNameIn.setText(selectedPacient.getLastName());
		PacientDetailView.cnpIn.setText(selectedPacient.getCNP());
		PacientDetailView.dateOfBirthIn.setText(selectedPacient.getDateOfBirth());
		PacientDetailView.fullAddressIn.setText(selectedPacient.getCountry() + " " + selectedPacient.getAddress());
		PacientDetailView.emailIn.setText(selectedPacient.getEmail());
		PacientDetailView.phoneNumberIn.setText(selectedPacient.getPhoneNumber());
	}

	public static void autocompleteMedicalResults() {
		MedicalResults medicalResults = Database.getMedicalResults(PacientController.cnp);
		PacientDetailView.TAIn.setText(medicalResults.getTa());
		PacientDetailView.AVIn.setText(medicalResults.getAv());
		PacientDetailView.weightIn.setText(medicalResults.getWeight());
		PacientDetailView.heightIn.setText(medicalResults.getHeight());
		PacientDetailView.IMCIn.setText(medicalResults.getImc());
		PacientDetailView.circumferenceIn.setText(medicalResults.getCircumference());
		PacientDetailView.glucoseIn.setText(medicalResults.getGlucose());
		PacientDetailView.cholesterolIn.setText(medicalResults.getCholesterol());
		PacientDetailView.LDLCIn.setText(medicalResults.getLdl_c());
		PacientDetailView.HDLCIn.setText(medicalResults.getHdl_c());
		PacientDetailView.trigliceridesIn.setText(medicalResults.getTriglicerides());
		PacientDetailView.potassiumIn.setText(medicalResults.getPotassium());
		PacientDetailView.sodiumIn.setText(medicalResults.getSodium());
		PacientDetailView.uricAcidIn.setText(medicalResults.getUric_acid());
		PacientDetailView.creatinineIn.setText(medicalResults.getCreatinine());
		PacientDetailView.microalbuminuriaIn.setText(medicalResults.getMicroalbuminuria());
		PacientDetailView.urinaryProteinIn.setText(medicalResults.getUrinary_protein());
		PacientDetailView.urinaryCreatinineIn.setText(medicalResults.getUrinary_creatinine());
		PacientDetailView.riskFactorIn.setText(medicalResults.getRisk_factor());

	}

	private void saveDataButton() {
		PacientDetailView.saveDataButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Database.updateMedicalResults(PacientController.cnp, new MedicalResults(
						PacientDetailView.TAIn.getText(), PacientDetailView.AVIn.getText(),
						PacientDetailView.weightIn.getText(), PacientDetailView.heightIn.getText(),
						PacientDetailView.IMCIn.getText(), PacientDetailView.circumferenceIn.getText(),
						PacientDetailView.glucoseIn.getText(), PacientDetailView.cholesterolIn.getText(),
						PacientDetailView.LDLCIn.getText(), PacientDetailView.HDLCIn.getText(),
						PacientDetailView.trigliceridesIn.getText(), PacientDetailView.potassiumIn.getText(),
						PacientDetailView.sodiumIn.getText(), PacientDetailView.uricAcidIn.getText(),
						PacientDetailView.creatinineIn.getText(), PacientDetailView.microalbuminuriaIn.getText(),
						PacientDetailView.urinaryProteinIn.getText(), PacientDetailView.urinaryCreatinineIn.getText(),
						PacientDetailView.riskFactorIn.getText()));
				System.out.println(PacientDetailView.sodiumIn.getText());
				colorWrongValues();
				JOptionPane.showMessageDialog(null, "Data has been changed successfully", "Data changed Successfully",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});
	}

	private String getCurrentDateAndTime() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy");
		SimpleDateFormat hourFormatter = new SimpleDateFormat("HHmmss");
		Date date = new Date();

		return dateFormatter.format(date) + "_" + hourFormatter.format(date);

	}

	private String getCurrentDate() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		return dateFormatter.format(date);
	}

	private String getCurrentHour() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
		Date date = new Date();

		return dateFormatter.format(date);
	}

	private void downloadTestResults() {
		PacientDetailView.downloadPDFButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				WordDocument.name = PacientDetailView.firstNameIn.getText() + "_"
						+ PacientDetailView.lastNameIn.getText() + "_" + getCurrentDateAndTime();
				Database.getTestResults();
				Database.getDoctorDetailsForWordDocument(LoginController.loggedInEmail);
				Database.getPacientDetailsForWordDocument(PacientController.cnp);
				Database.getBasicTestResults(PacientController.cnp);
				Database.getFullTestResults(PacientController.cnp);

				new WordDocument();

				Email.index = 5;
				Email.email[Email.position].start();

				JOptionPane.showMessageDialog(null, "Test Results has been successfully sent via email",
						"Test Results Successfully Sent", JOptionPane.INFORMATION_MESSAGE);

				Database.insertIntoTestHistoryTable(PacientDetailView.emailIn.getText(), LoginController.loggedInEmail,
						WordDocument.path + WordDocument.name, WordDocument.name, getCurrentDate(), getCurrentHour());
			}
		});
	}

	private double computeImc(double height, double weight) {
		return weight / Math.pow(height, 2);
	}

	public static void colorWrongValues() {
		try {

			if (Double.parseDouble(PacientDetailView.IMCIn.getText()) >= 25) {
				PacientDetailView.IMCIn.setBackground(Color.red);
			} else {
				PacientDetailView.IMCIn.setBackground(Color.white);
			}
			if (Double.parseDouble(PacientDetailView.glucoseIn.getText()) <= 70
					|| Double.parseDouble(PacientDetailView.glucoseIn.getText()) >= 105) {
				PacientDetailView.glucoseIn.setBackground(Color.red);
				testResultValues[0] = true;
			} else {
				PacientDetailView.glucoseIn.setBackground(Color.white);
				testResultValues[0] = false;
			}

			if (Double.parseDouble(PacientDetailView.cholesterolIn.getText()) >= 200) {
				PacientDetailView.cholesterolIn.setBackground(Color.red);
				testResultValues[1] = true;
			} else {
				PacientDetailView.cholesterolIn.setBackground(Color.white);
				testResultValues[1] = false;
			}

			if (Double.parseDouble(PacientDetailView.LDLCIn.getText()) >= 100) {
				PacientDetailView.LDLCIn.setBackground(Color.red);
				testResultValues[2] = true;
			} else {
				PacientDetailView.LDLCIn.setBackground(Color.white);
				testResultValues[2] = false;
			}

			if (Double.parseDouble(PacientDetailView.HDLCIn.getText()) <= 60) {
				PacientDetailView.HDLCIn.setBackground(Color.red);
				testResultValues[3] = true;
			} else {
				PacientDetailView.HDLCIn.setBackground(Color.white);
				testResultValues[3] = false;
			}

			if (Double.parseDouble(PacientDetailView.trigliceridesIn.getText()) >= 150) {
				PacientDetailView.trigliceridesIn.setBackground(Color.red);
				testResultValues[4] = true;
			} else {
				PacientDetailView.trigliceridesIn.setBackground(Color.white);
				testResultValues[4] = false;
			}

			if (Double.parseDouble(PacientDetailView.potassiumIn.getText()) < 3.3
					|| Double.parseDouble(PacientDetailView.potassiumIn.getText()) > 5.1) {
				PacientDetailView.potassiumIn.setBackground(Color.red);
				testResultValues[5] = true;
			} else {
				PacientDetailView.potassiumIn.setBackground(Color.white);
				testResultValues[5] = false;
			}

			if (Double.parseDouble(PacientDetailView.sodiumIn.getText()) < 136
					|| Double.parseDouble(PacientDetailView.sodiumIn.getText()) > 145) {
				PacientDetailView.sodiumIn.setBackground(Color.red);
				testResultValues[6] = true;
			} else {
				PacientDetailView.sodiumIn.setBackground(Color.white);
				testResultValues[6] = false;
			}

			if (Double.parseDouble(PacientDetailView.uricAcidIn.getText()) >= 7) {
				PacientDetailView.uricAcidIn.setBackground(Color.red);
				testResultValues[7] = true;
			} else {
				PacientDetailView.uricAcidIn.setBackground(Color.white);
				testResultValues[7] = false;
			}

			if (Double.parseDouble(PacientDetailView.creatinineIn.getText()) < 0.5
					|| Double.parseDouble(PacientDetailView.creatinineIn.getText()) > 1.2) {
				PacientDetailView.creatinineIn.setBackground(Color.red);
				testResultValues[8] = true;
			} else {
				PacientDetailView.creatinineIn.setBackground(Color.white);
				testResultValues[8] = false;
			}

			if (Double.parseDouble(PacientDetailView.urinaryProteinIn.getText()) < 20
					|| Double.parseDouble(PacientDetailView.urinaryProteinIn.getText()) > 800) {
				PacientDetailView.urinaryProteinIn.setBackground(Color.red);
				testResultValues[9] = true;
			} else {
				PacientDetailView.urinaryProteinIn.setBackground(Color.white);
				testResultValues[9] = false;
			}

			if (Double.parseDouble(PacientDetailView.urinaryCreatinineIn.getText()) < 90
					|| Double.parseDouble(PacientDetailView.urinaryCreatinineIn.getText()) > 300) {
				PacientDetailView.urinaryCreatinineIn.setBackground(Color.red);
				testResultValues[10] = true;
			} else {
				PacientDetailView.urinaryCreatinineIn.setBackground(Color.white);
				testResultValues[10] = false;
			}

			if (Double.parseDouble(PacientDetailView.microalbuminuriaIn.getText()) > 25) {
				PacientDetailView.microalbuminuriaIn.setBackground(Color.red);
				testResultValues[11] = true;
			} else {
				PacientDetailView.microalbuminuriaIn.setBackground(Color.white);
				testResultValues[11] = false;
			}
		} catch (NumberFormatException e) {

		}
	}

}

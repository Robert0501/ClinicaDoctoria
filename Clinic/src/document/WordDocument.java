package document;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.Table;
import com.spire.doc.TableRow;
import com.spire.doc.documents.BorderStyle;
import com.spire.doc.documents.DefaultTableStyle;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;
import com.spire.doc.documents.TableRowHeightType;
import com.spire.doc.documents.VerticalAlignment;
import com.spire.doc.fields.TextRange;

import controller.PacientDetailController;

public class WordDocument {

	Document document;
	Section detailsSection;

	private Paragraph clinicName;
	private Paragraph clinicAddress;
	private Paragraph doctorName;
	private Paragraph medicalResultsTitle;
	private Paragraph pacientName;
	private Paragraph pacientCNP;
	private Paragraph date;
	private Paragraph dob;
	private Paragraph pacientPhone;
	private Paragraph pacientBasicResults;
	private Paragraph bloodPressure;
	private Paragraph bpm;
	private Paragraph weight;
	private Paragraph height;
	private Paragraph circumference;
	private Paragraph pacientFullResults;

	ParagraphStyle pacientDetailStyle;

	public static String path = "src//Archives//";
	public static String name;

	public static String clinicNameString;
	public static String clinicAddressString;
	public static String doctorNameString;
	public static String patientNameString;
	public static String patientCnp;
	public static String patientDob;
	public static String patientPhoneNumber;
	public static String patientBloodPressure;
	public static String patientbpm;
	public static String patientWeight;
	public static String patientHeight;
	public static String patientCircumference;

	private File file;

	public WordDocument() {
		createDocFile(path, name);
		generateWordDocument();
		pacientDetailStyle();
		titleSection(clinicNameString);
		clinicAddressSection(clinicAddressString);
		doctorNameSection(doctorNameString);
		medicalResultsTitle();
		medicalResultDate();
		pacientName(patientNameString);
		pacientCnp(patientCnp);
		dob(patientDob);
		phoneNumber(patientPhoneNumber);
		pacientBasicResult();
		bloodPressure(patientBloodPressure);
		patientbpm(patientbpm);
		weight(patientWeight);
		height(patientHeight);
		circumference(patientCircumference);
		pacientFullResults();
		medicalResultsTable();
		saveDocument();
	}

	private String getTodayDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		return formatter.format(date);
	}

	private void createDocFile(String path, String name) {
		file = new File(path + "" + name + ".docx");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generateWordDocument() {
		document = new Document();
		detailsSection = document.addSection();
	}

	private void titleSection(String name) {
		clinicName = detailsSection.addParagraph();
		clinicName.appendText(name);

		ParagraphStyle clinicNameStyle = new ParagraphStyle(document);
		clinicNameStyle.setName("clinicNameStyle");
		clinicNameStyle.getCharacterFormat().setBold(true);
		clinicNameStyle.getCharacterFormat().setFontName("Arial");
		clinicNameStyle.getCharacterFormat().setFontSize(36);
		document.getStyles().add(clinicNameStyle);
		clinicName.applyStyle("clinicNameStyle");

		clinicName.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
		clinicName.getFormat().setAfterSpacing(25f);
	}

	private void clinicAddressSection(String address) {
		clinicAddress = detailsSection.addParagraph();
		clinicAddress.appendText("Clinic Address: " + address);

		ParagraphStyle clinicAddressStyle = new ParagraphStyle(document);
		clinicAddressStyle.setName("clinicAddressStyle");
		clinicAddressStyle.getCharacterFormat().setFontName("Arial");
		clinicAddressStyle.getCharacterFormat().setFontSize(16);
		document.getStyles().add(clinicAddressStyle);
		clinicAddress.applyStyle("clinicAddressStyle");

		clinicAddress.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
		clinicAddress.getFormat().setAfterSpacing(5f);
	}

	private void doctorNameSection(String name) {
		doctorName = detailsSection.addParagraph();
		doctorName.appendText("Dr. " + name);

		ParagraphStyle doctorNameStyle = new ParagraphStyle(document);
		doctorNameStyle.setName("doctorNameStyle");
		doctorNameStyle.getCharacterFormat().setFontName("Arial");
		doctorNameStyle.getCharacterFormat().setFontSize(16);
		document.getStyles().add(doctorNameStyle);
		doctorName.applyStyle("doctorNameStyle");

		doctorName.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
		doctorName.getFormat().setAfterSpacing(30f);
	}

	private void medicalResultsTitle() {
		medicalResultsTitle = detailsSection.addParagraph();
		medicalResultsTitle.appendText("Patient Detail");

		ParagraphStyle medicalResultsStyle = new ParagraphStyle(document);
		medicalResultsStyle.setName("patientDetailStyle");
		medicalResultsStyle.getCharacterFormat().setBold(true);
		medicalResultsStyle.getCharacterFormat().setFontName("Arial");
		medicalResultsStyle.getCharacterFormat().setFontSize(28);
		document.getStyles().add(medicalResultsStyle);
		medicalResultsTitle.applyStyle("patientDetailStyle");

		medicalResultsTitle.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
		medicalResultsTitle.getFormat().setAfterSpacing(25f);
	}

	private void pacientDetailStyle() {
		pacientDetailStyle = new ParagraphStyle(document);
		pacientDetailStyle.setName("pacientDetailStyle");
		pacientDetailStyle.getCharacterFormat().setFontName("Arial");
		pacientDetailStyle.getCharacterFormat().setFontSize(16);
		document.getStyles().add(pacientDetailStyle);
	}

	private void medicalResultDate() {
		date = detailsSection.addParagraph();
		date.appendText("Date: " + getTodayDate());

		date.applyStyle("pacientDetailStyle");

		date.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
		date.getFormat().setAfterSpacing(5f);
	}

	private void pacientName(String name) {
		pacientName = detailsSection.addParagraph();
		pacientName.appendText("Patient: " + name);

		pacientName.applyStyle("pacientDetailStyle");

		pacientName.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
		pacientName.getFormat().setAfterSpacing(5f);
	}

	private void pacientCnp(String cnp) {
		pacientCNP = detailsSection.addParagraph();
		pacientCNP.appendText("CNP: " + cnp);

		pacientCNP.applyStyle("pacientDetailStyle");

		pacientCNP.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
		pacientCNP.getFormat().setAfterSpacing(5f);
	}

	private void dob(String birthDate) {
		dob = detailsSection.addParagraph();
		dob.appendText("Date of Birth: " + birthDate);

		dob.applyStyle("pacientDetailStyle");

		dob.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
		dob.getFormat().setAfterSpacing(5f);
	}

	private void phoneNumber(String phoneNumber) {
		pacientPhone = detailsSection.addParagraph();
		pacientPhone.appendText("Phone Number: " + phoneNumber);

		pacientPhone.applyStyle("pacientDetailStyle");

		pacientPhone.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
		pacientPhone.getFormat().setAfterSpacing(30f);
	}

	private void pacientBasicResult() {
		pacientBasicResults = detailsSection.addParagraph();
		pacientBasicResults.appendText("Patient Basic Tests Results");
		pacientBasicResults.applyStyle("patientDetailStyle");
		pacientBasicResults.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
		pacientBasicResults.getFormat().setAfterSpacing(25f);

	}

	private void bloodPressure(String bp) {
		bloodPressure = detailsSection.addParagraph();
		bloodPressure.appendText("Blood Pressure: " + bp);
		bloodPressure.applyStyle("pacientDetailStyle");
		bloodPressure.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
		bloodPressure.getFormat().setAfterSpacing(5f);
	}

	private void patientbpm(String patientbpm) {
		bpm = detailsSection.addParagraph();
		bpm.appendText("BPM: " + patientbpm);
		bpm.applyStyle("pacientDetailStyle");
		bpm.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
		bpm.getFormat().setAfterSpacing(5f);
	}

	private void weight(String patientWeight) {
		weight = detailsSection.addParagraph();
		weight.appendText("Weight: " + patientWeight);
		weight.applyStyle("pacientDetailStyle");
		weight.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
		weight.getFormat().setAfterSpacing(5f);
	}

	private void height(String patientHeight) {
		height = detailsSection.addParagraph();
		height.appendText("Height: " + patientHeight);
		height.applyStyle("pacientDetailStyle");
		height.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
		height.getFormat().setAfterSpacing(5f);
	}

	private void circumference(String patientCircumference) {
		circumference = detailsSection.addParagraph();
		circumference.appendText("Circumference: " + patientCircumference);
		circumference.applyStyle("pacientDetailStyle");
		circumference.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
		circumference.getFormat().setAfterSpacing(30f);
	}

	private void pacientFullResults() {
		pacientFullResults = detailsSection.addParagraph();
		pacientFullResults.appendText("Patient Tests Results");
		pacientFullResults.applyStyle("patientDetailStyle");
		pacientFullResults.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
		pacientFullResults.getFormat().setAfterSpacing(25f);

	}

	private void medicalResultsTable() {
		String[] header = { "Test Name", "Result", "Measurement Unit", "Normal Value" };

		Table table = detailsSection.addTable();
		table.resetCells(PacientDetailController.pacientData.length + 1, header.length);
		table.applyStyle(DefaultTableStyle.Colorful_List);
		table.getTableFormat().getBorders().setBorderType(BorderStyle.Single);

		// Set first row as table header
		TableRow row = table.getRows().get(0);
		row.isHeader(true);
		row.setHeight(20);
		row.setHeightType(TableRowHeightType.Exactly);
		row.getRowFormat().setBackColor(Color.gray);

		// Add table column names
		for (int i = 0; i < header.length; i++) {
			row.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
			Paragraph p = row.getCells().get(i).addParagraph();
			p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
			TextRange range1 = p.appendText(header[i]);
			range1.getCharacterFormat().setFontName("Arial");
			range1.getCharacterFormat().setFontSize(12f);
			range1.getCharacterFormat().setBold(true);
		}

		// Add data to table
		for (int r = 0; r < PacientDetailController.pacientData.length; r++) {
			TableRow dataRow = table.getRows().get(r + 1);
			dataRow.setHeight(25);
			dataRow.setHeightType(TableRowHeightType.Exactly);
			dataRow.getRowFormat().setBackColor(Color.white);
			for (int c = 0; c < PacientDetailController.pacientData[r].length; c++) {
				dataRow.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
				TextRange range2 = dataRow.getCells().get(c).addParagraph()
						.appendText(PacientDetailController.pacientData[r][c]);
				range2.getCharacterFormat().setFontName("Arial");
				range2.getCharacterFormat().setFontSize(10f);
			}
		}

	}

	private void saveDocument() {
		document.saveToFile(path + "" + name + ".pdf", FileFormat.PDF);
		file.delete();
	}

}

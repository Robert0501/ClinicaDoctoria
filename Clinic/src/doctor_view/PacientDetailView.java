package doctor_view;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import doctor_controller.PacientDetailController;

public class PacientDetailView {

	public static JFrame pacientDetailsFrame;

	public static JLabel photo = new JLabel("");

	public static JPanel pacientDetailsPanel = new JPanel();
	private JLabel title = new JLabel("Title");
	private JLabel firstName = new JLabel("First Name");
	private JLabel lastName = new JLabel("Last Name");
	private JLabel cnp = new JLabel("CNP");
	private JLabel dateOfBirth = new JLabel("Date of Birth");

	public static JTextField titleIn = new JTextField();
	public static JTextField firstNameIn = new JTextField();
	public static JTextField lastNameIn = new JTextField();
	public static JTextField cnpIn = new JTextField();
	public static JTextField dateOfBirthIn = new JTextField();

	public static JPanel contactPanel = new JPanel();
	private JLabel address = new JLabel("Full Address");
	private JLabel email = new JLabel("Email");
	private JLabel phoneNumber = new JLabel("Phone Number");

	public static JTextField emailIn = new JTextField();
	public static JTextField phoneNumberIn = new JTextField();
	public static JTextField fullAddressIn = new JTextField();

	public static JPanel medicalTestsPanel = new JPanel();

	private JLabel TA = new JLabel("TA (mmHg)");
	private JLabel AV = new JLabel("AV (RPM)");
	private JLabel weight = new JLabel("Weight (Kg)");
	private JLabel height = new JLabel("Height (m)");
	private JLabel IMC = new JLabel("IMC kg/m2");
	private JLabel circumference = new JLabel("Circumference (cm)");
	private JLabel glucose = new JLabel("Glucose (mg/dl)");
	private JLabel total_cholesterol = new JLabel("Cholesterol (mg/dl)");
	private JLabel LDL_C = new JLabel("LDL_C (mg/dl)");
	private JLabel HDL_C = new JLabel("HDL_C (mg/dl)");
	private JLabel triglicerides = new JLabel("Triglicerides (mg/dl)");
	private JLabel potassium_Value = new JLabel("Potassium Value (mEq/L)");
	private JLabel sodium = new JLabel("Sodium Value (mEq/L)");
	private JLabel uric_acid = new JLabel("Uric Acid (mg/dL)");
	private JLabel creatinine = new JLabel("Creatinine (mg/dL)");
	private JLabel microalbuminuria = new JLabel("Microalbuminuria (ug/mL)");
	private JLabel urinary_protein = new JLabel("Urinary Protein (mg/l)");
	private JLabel urinary_creatinine = new JLabel("Urinary Creatinine (mg/dl)");

	public static JTextField TAIn = new JTextField();
	public static JTextField AVIn = new JTextField();
	public static JTextField weightIn = new JTextField();
	public static JTextField heightIn = new JTextField();
	public static JTextField IMCIn = new JTextField();
	public static JTextField circumferenceIn = new JTextField();
	public static JTextField glucoseIn = new JTextField();
	public static JTextField cholesterolIn = new JTextField();
	public static JTextField LDLCIn = new JTextField();
	public static JTextField HDLCIn = new JTextField();
	public static JTextField trigliceridesIn = new JTextField();
	public static JTextField potassiumIn = new JTextField();
	public static JTextField sodiumIn = new JTextField();
	public static JTextField uricAcidIn = new JTextField();
	public static JTextField creatinineIn = new JTextField();
	public static JTextField microalbuminuriaIn = new JTextField();
	public static JTextField urinaryProteinIn = new JTextField();
	public static JTextField urinaryCreatinineIn = new JTextField();

	private JLabel riskFactor = new JLabel("Risk Factor");
	public static JTextField riskFactorIn = new JTextField();

	public static JPanel testValuePanel = new JPanel();

	private JLabel glucoseValue = new JLabel("Glucose Value: 70 - 105 mg/dl ");
	private JLabel cholesterolValue = new JLabel("Cholesterol Value: < 200 mg/dl ");
	private JLabel LDCValue = new JLabel("LDL_C Value: < 100 mg/dl ");
	private JLabel HDCValue = new JLabel("HDL_C Value: > 60 mg/dl");
	private JLabel trigliceridesValue = new JLabel("Triglicerides Value: < 150 mg/dl ");
	private JLabel potassiumValue = new JLabel("Potassium Value: 3.3 - 5.1 mEq/L");
	private JLabel sodiumValue = new JLabel("Sodium Value: 136 - 145 mEq/L ");
	private JLabel uricAcidValue = new JLabel("Uric Acid Value: < 7 ");
	private JLabel creatinineValue = new JLabel("Creatinine Value: 0.5 - 1.2 mg/dl ");
	private JLabel microalbuminuriaValue = new JLabel("Microalbuminuria Value < 25 ug/ml");
	private JLabel urinaryProteineValue = new JLabel("Urinary Proteine Value: 20 - 80 mEq/L");
	private JLabel urinaryCreatinineValue = new JLabel("Urinary Creatinine Value: 90 - 300 mg/dL2");
	private JLabel subponderal = new JLabel("Subponderal < 18.5 km/m2");
	private JLabel normal = new JLabel("Normal:  18.5 - 24.9 km/m2");
	private JLabel supraponderal = new JLabel("Supraponderal:  25 - 29.9 km/m2");
	private JLabel firstGradeObesity = new JLabel("1st Grade Obesity: 30 - 34.9 km/m2");
	private JLabel secondGradeObesity = new JLabel("2nd Grade Obesity:  35 - 39.9 km/m2");
	private JLabel morbidObesity = new JLabel("Morbid Obesity > 40 km/m2");

	public static JButton saveDataButton = new JButton("Save Changes");
	public static JButton downloadPDFButton = new JButton("Send Test Results");

	int test_Value_labels_initial_x = 0;
	int test_Value_labels_initial_y = 35;

	int counter = 0;

	public PacientDetailView() {
		pacientDetailsFrame();
		pacientDetailsPanel();

		pacientContactPanel();
		testValuePanel();

		saveDataButton();
		downloadPDFButton();

		pacientDetailsFrame.setSize(new Dimension(1280, 722));

		new PacientDetailController();
	}

	private void pacientDetailsFrame() {
		pacientDetailsFrame = new JFrame("");
		pacientDetailsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		pacientDetailsFrame.pack();
		pacientDetailsFrame.setSize(new Dimension(1280, 720));
		pacientDetailsFrame.setLocationRelativeTo(null);
		pacientDetailsFrame.setLayout(null);
		pacientDetailsFrame.setVisible(true);

		photoLabel();
	}

	private void putPanels(JPanel panel, String panelName, int x, int y, int width, int height) {
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setVisible(true);
		panel.setBorder(BorderFactory.createTitledBorder(panelName));
		pacientDetailsFrame.add(panel);

		personalDetails();
	}

	private void pacientDetailsPanel() {

		putPanels(pacientDetailsPanel, "Personal Details", 30, 20, 530, 200);

		personalDetails();
	}

	private void putLabels(JPanel panel, JLabel label, int x, int y) {
		label.setBounds(x, y, 200, 30);
		panel.add(label);

	}

	private void putTextFields(JPanel panel, JTextField textField, int x, int y, int width, boolean value) {
		textField.setBounds(x, y, width, 30);
		textField.setEnabled(value);
		textField.setHorizontalAlignment(JTextField.CENTER);

		panel.add(textField);
	}

	private void photoLabel() {
		photo.setBounds(1000, 30, 250, 180);
		photo.setIcon(new ImageIcon("src//Images//Icon//pacient.jpg"));
		pacientDetailsFrame.add(photo);
	}

	private void personalDetails() {
		putLabels(pacientDetailsPanel, title, 20, 15);
		putTextFields(pacientDetailsPanel, titleIn, 20, 50, 30, false);

		putLabels(pacientDetailsPanel, firstName, 60, 15);
		putTextFields(pacientDetailsPanel, firstNameIn, 60, 50, 220, true);

		putLabels(pacientDetailsPanel, lastName, 300, 15);
		putTextFields(pacientDetailsPanel, lastNameIn, 300, 50, 210, true);

		putLabels(pacientDetailsPanel, cnp, 20, 100);
		putTextFields(pacientDetailsPanel, cnpIn, 20, 135, 90, false);

		putLabels(pacientDetailsPanel, dateOfBirth, 150, 100);
		putTextFields(pacientDetailsPanel, dateOfBirthIn, 150, 135, 130, false);

	}

	private void pacientContactPanel() {

		putPanels(contactPanel, "Contact", 600, 20, 380, 200);

		contactDetails();
	}

	private void contactDetails() {
		putLabels(contactPanel, email, 20, 15);
		putTextFields(contactPanel, emailIn, 20, 50, 200, false);

		putLabels(contactPanel, phoneNumber, 250, 15);
		putTextFields(contactPanel, phoneNumberIn, 250, 50, 100, true);

		putLabels(contactPanel, address, 20, 100);
		putTextFields(contactPanel, fullAddressIn, 20, 135, 330, true);

		medicalTestsPanel();
	}

	private void medicalTestsPanel() {
		putPanels(medicalTestsPanel, "Medical Test", 30, 250, 530, 350);
		medicalTests();
	}

	private void medicalTests() {
		putLabels(medicalTestsPanel, TA, 20, 15);
		putTextFields(medicalTestsPanel, TAIn, 20, 50, 50, true);

		putLabels(medicalTestsPanel, AV, 100, 15);
		putTextFields(medicalTestsPanel, AVIn, 100, 50, 50, true);

		putLabels(medicalTestsPanel, weight, 180, 15);
		putTextFields(medicalTestsPanel, weightIn, 180, 50, 50, true);

		putLabels(medicalTestsPanel, height, 260, 15);
		putTextFields(medicalTestsPanel, heightIn, 260, 50, 50, true);

		putLabels(medicalTestsPanel, circumference, 340, 15);
		putTextFields(medicalTestsPanel, circumferenceIn, 340, 50, 90, true);

		putLabels(medicalTestsPanel, IMC, 450, 15);
		putTextFields(medicalTestsPanel, IMCIn, 450, 50, 50, true);

		putLabels(medicalTestsPanel, glucose, 20, 100);
		putTextFields(medicalTestsPanel, glucoseIn, 20, 135, 80, true);

		putLabels(medicalTestsPanel, total_cholesterol, 120, 100);
		putTextFields(medicalTestsPanel, cholesterolIn, 120, 135, 100, true);

		putLabels(medicalTestsPanel, total_cholesterol, 120, 100);
		putTextFields(medicalTestsPanel, cholesterolIn, 120, 135, 90, true);

		putLabels(medicalTestsPanel, LDL_C, 230, 100);
		putTextFields(medicalTestsPanel, LDLCIn, 230, 135, 70, true);

		putLabels(medicalTestsPanel, HDL_C, 315, 100);
		putTextFields(medicalTestsPanel, HDLCIn, 315, 135, 70, true);

		putLabels(medicalTestsPanel, triglicerides, 400, 100);
		putTextFields(medicalTestsPanel, trigliceridesIn, 400, 135, 100, true);

		putLabels(medicalTestsPanel, potassium_Value, 20, 185);
		putTextFields(medicalTestsPanel, potassiumIn, 20, 220, 120, true);

		putLabels(medicalTestsPanel, sodium, 165, 185);
		putTextFields(medicalTestsPanel, sodiumIn, 165, 220, 110, true);

		putLabels(medicalTestsPanel, uric_acid, 300, 185);
		putTextFields(medicalTestsPanel, uricAcidIn, 300, 220, 85, true);

		putLabels(medicalTestsPanel, creatinine, 410, 185);
		putTextFields(medicalTestsPanel, creatinineIn, 410, 220, 90, true);

		putLabels(medicalTestsPanel, urinary_protein, 20, 270);
		putTextFields(medicalTestsPanel, urinaryProteinIn, 20, 305, 100, true);

		putLabels(medicalTestsPanel, urinary_creatinine, 140, 270);
		putTextFields(medicalTestsPanel, urinaryCreatinineIn, 140, 305, 120, true);

		putLabels(medicalTestsPanel, microalbuminuria, 280, 270);
		putTextFields(medicalTestsPanel, microalbuminuriaIn, 280, 305, 120, true);

		putLabels(medicalTestsPanel, riskFactor, 415, 270);
		putTextFields(medicalTestsPanel, riskFactorIn, 415, 305, 85, true);

	}

	private void testValuePanel() {
		putPanels(testValuePanel, "Normal Test Values", 600, 250, 650, 350);
		testValue();
	}

	private void putTestValueLables(JLabel label) {
		label.setBounds(test_Value_labels_initial_x, test_Value_labels_initial_y, 215, 40);
		label.setHorizontalAlignment(JLabel.CENTER);
		testValuePanel.add(label);

		test_Value_labels_initial_y += 50;
		counter++;
		if (counter == 6) {
			test_Value_labels_initial_y = 35;
			test_Value_labels_initial_x = 215;
		}
		if (counter == 12) {
			test_Value_labels_initial_y = 35;
			test_Value_labels_initial_x = 430;
		}
	}

	private void testValue() {
		putTestValueLables(glucoseValue);
		putTestValueLables(cholesterolValue);
		putTestValueLables(LDCValue);
		putTestValueLables(HDCValue);
		putTestValueLables(trigliceridesValue);
		putTestValueLables(potassiumValue);
		putTestValueLables(sodiumValue);
		putTestValueLables(uricAcidValue);
		putTestValueLables(creatinineValue);
		putTestValueLables(urinaryProteineValue);
		putTestValueLables(urinaryCreatinineValue);
		putTestValueLables(microalbuminuriaValue);
		putTestValueLables(subponderal);
		putTestValueLables(normal);
		putTestValueLables(supraponderal);
		putTestValueLables(firstGradeObesity);
		putTestValueLables(secondGradeObesity);
		putTestValueLables(morbidObesity);
	}

	private void saveDataButton() {
		saveDataButton.setBounds(410, 620, 150, 30);
		saveDataButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pacientDetailsFrame.add(saveDataButton);
	}

	private void downloadPDFButton() {
		downloadPDFButton.setBounds(600, 620, 150, 30);
		downloadPDFButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pacientDetailsFrame.add(downloadPDFButton);
	}

}

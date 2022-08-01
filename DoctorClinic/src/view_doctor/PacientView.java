package view_doctor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller_doctor.PacientController;
import controller_unlogin.LoginController;
import database.Database;
import helper.HintTextFieldUI;

public class PacientView {
	public static JPanel pacientPanel;

	private JPanel northPanel;
	public static JPanel centerPanel;

	private JLabel titleLabel;
	private JLabel searchLabel;
	public static JButton addPacientButton;
	public static JButton showPacientDetailsButton;
	public static JButton deletePacientButton;

	public static DefaultTableModel model;
	public static JTable table;
	public static TableRowSorter<TableModel> rowSorter;

	public static JTextField searchField;

	public PacientView() {
		pacientPanel();
		northPanel();
		centerPanel();

		new PacientController();
	}

	private void pacientPanel() {
		pacientPanel = new JPanel();
		pacientPanel.setLayout(new FlowLayout());
		pacientPanel.setVisible(true);
		pacientPanel.setBackground(Color.LIGHT_GRAY);
		pacientPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		DoctorPerspectiveView.doctorFrame.add(pacientPanel);
	}

	private void northPanel() {
		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setPreferredSize(new Dimension(950, 150));
		northPanel.setBackground(Color.LIGHT_GRAY);
		pacientPanel.add(northPanel);

		titleLabel();
		searchField();
		showPacientDetailsButton();
		deletePacientButton();
		addPacientButton();
	}

	private void centerPanel() {
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.black);
		pacientPanel.add(centerPanel);

		tableConfig();
	}

	private void titleLabel() {
		titleLabel = new JLabel("Patients");
		titleLabel.setBounds(0, 30, 980, 55);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		northPanel.add(titleLabel);
	}

	private void addPacientButton() {
		addPacientButton = new JButton("Add New Patient");
		addPacientButton.setBounds(800, 100, 130, 30);
		northPanel.add(addPacientButton);
	}

	private void showPacientDetailsButton() {
		showPacientDetailsButton = new JButton("View Details");
		showPacientDetailsButton.setBounds(400, 100, 130, 30);
		showPacientDetailsButton.setEnabled(false);
		northPanel.add(showPacientDetailsButton);
	}

	private void deletePacientButton() {
		deletePacientButton = new JButton("Delete Patient");
		deletePacientButton.setBounds(600, 100, 130, 30);
		deletePacientButton.setEnabled(false);
		northPanel.add(deletePacientButton);
	}

	private void searchField() {
		searchLabel = new JLabel("Search: ");
		searchLabel.setBounds(20, 100, 100, 30);
		searchLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		northPanel.add(searchLabel);

		searchField = new JTextField();
		searchField.setUI(new HintTextFieldUI(" Search", true));
		searchField.setBounds(100, 102, 200, 30);
		northPanel.add(searchField);
	}

	private void tableConfig() {

		String[] tableHeader = { "<HTML><B>First Name</B></HTML>", "<HTML><B>Last Name</B></HTML>",
				"<HTML><B>CNP</B></HTML>", "<HTML><B>Birth Date</B></HTML>", "<HTML><B>Email</B></HTML>",
				"<HTML><B>Phone</B></HTML>", "<HTML><B>Country</B></HTML>", "<HTML><B>Address</B></HTML>" };

		model = new DefaultTableModel(tableHeader, 0);
		table = new JTable(model);

		table.setPreferredScrollableViewportSize(new Dimension(900, 400));
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
		table.setDefaultEditor(Object.class, null);

		centerTableHeader();
		centerTableCells();
		setTableCellWidth();

		Database.showPacients(LoginController.loggedInEmail);
		rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		table.getTableHeader().setReorderingAllowed(false);
//		sortTableByLastName();
		centerPanel.add(new JScrollPane(table));
	}

	private void sortTableByLastName() {
		List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		rowSorter.setSortKeys(sortKeys);
	}

	private void centerTableHeader() {
		TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
		JLabel headerLabel = (JLabel) rendererFromHeader;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
	}

	private void centerTableCells() {
		TableCellRenderer rendererFromCells = table.getCellRenderer(0, 0);
		JLabel cellLabel = (JLabel) rendererFromCells;
		cellLabel.setHorizontalAlignment(JLabel.CENTER);
	}

	private void setTableCellWidth() {
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(120); // First Name
		tcm.getColumn(1).setPreferredWidth(100); // Last Name
		tcm.getColumn(2).setPreferredWidth(100); // CNP
		tcm.getColumn(3).setPreferredWidth(100); // Date of Birth
		tcm.getColumn(4).setPreferredWidth(200); // Email
		tcm.getColumn(5).setPreferredWidth(100); // Phone Number
		tcm.getColumn(6).setPreferredWidth(100); // Country
		tcm.getColumn(7).setPreferredWidth(280); // Address

		for (int i = 0; i < 7; i++) {
			tcm.getColumn(i).setResizable(false);
		}

		table.setRowHeight(30);
	}
}

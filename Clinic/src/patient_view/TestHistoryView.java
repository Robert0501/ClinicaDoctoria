package patient_view;

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
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import database.Database;
import doctor_controller.LoginController;
import patient_controller.TestHistoryController;

public class TestHistoryView {
	public static JPanel testHistoryPanel;
	public static JPanel northPanel;
	public static JPanel centerPanel;

	public static JButton downloadTestResultsButton;

	private JLabel titleLabel;

	public static DefaultTableModel model;
	public static JTable table;
	public static TableRowSorter<TableModel> rowSorter;

	public TestHistoryView() {
		testHistoryPanel();
		northPanel();
		downloadTestResults();
		centerPanel();

		new TestHistoryController();
	}

	private void testHistoryPanel() {
		testHistoryPanel = new JPanel();
		testHistoryPanel.setLayout(new FlowLayout());
		testHistoryPanel.setVisible(false);
		testHistoryPanel.setBackground(Color.LIGHT_GRAY);
		testHistoryPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		PatientPerspectiveView.patientFrame.add(testHistoryPanel);
	}

	private void northPanel() {
		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setPreferredSize(new Dimension(950, 150));
		northPanel.setBackground(Color.LIGHT_GRAY);
		testHistoryPanel.add(northPanel);

		titleLabel();
	}

	private void centerPanel() {
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.black);
		testHistoryPanel.add(centerPanel);

		tableConfig();
	}

	private void titleLabel() {
		titleLabel = new JLabel("Tests History");
		titleLabel.setBounds(0, 30, 980, 55);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		northPanel.add(titleLabel);
	}

	private void downloadTestResults() {
		downloadTestResultsButton = new JButton("Download Result");
		downloadTestResultsButton.setBounds(800, 100, 130, 30);
		downloadTestResultsButton.setEnabled(false);
		northPanel.add(downloadTestResultsButton);
	}

	private void tableConfig() {
		String[] tableHeader = { "Date", "Time", "Document" };

		model = new DefaultTableModel(tableHeader, 0);
		table = new JTable(model);

		table.setPreferredScrollableViewportSize(new Dimension(900, 400));
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		setTableCellWidth();
		centerTableHeader();

		centerTableCells();

		Database.showTestResults((LoginController.loggedInEmail));
		rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		table.getTableHeader().setReorderingAllowed(false);
		sortTableByDate();
		centerPanel.add(new JScrollPane(table));
	}

	private void sortTableByDate() {
		List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		sortKeys.add(new RowSorter.SortKey(2, SortOrder.DESCENDING));
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
		tcm.getColumn(0).setPreferredWidth(100); // Date
		tcm.getColumn(1).setPreferredWidth(100); // Time
		tcm.getColumn(2).setPreferredWidth(700); // Document

		for (int i = 0; i < 3; i++) {
			tcm.getColumn(i).setResizable(false);
		}

		table.setRowHeight(30);
	}
}

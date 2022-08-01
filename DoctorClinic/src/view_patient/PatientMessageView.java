package view_patient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller_patient.PatientMessageController;

public class PatientMessageView {

	public static JPanel messagePanel;
	public static JPanel northPanel;
	public static JPanel centerPanel;

	private JLabel titleLabel;

	public static DefaultTableModel model;
	public static JTable table;
	public static TableRowSorter<TableModel> rowSorter;
	public static DefaultTableModel tableModel;

	public static JButton replayButton;

	public PatientMessageView() {
		messagePanel();
		northPanel();
		centerPanel();
		replay();

		new PatientMessageController();
	}

	private void messagePanel() {
		messagePanel = new JPanel();
		messagePanel.setLayout(new FlowLayout());
		messagePanel.setVisible(false);
		messagePanel.setBackground(Color.LIGHT_GRAY);
		messagePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		PatientPerspectiveView.patientFrame.add(messagePanel);
	}

	private void northPanel() {
		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setPreferredSize(new Dimension(950, 150));
		northPanel.setBackground(Color.LIGHT_GRAY);
		messagePanel.add(northPanel);

		titleLabel();
	}

	private void centerPanel() {
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.black);
		messagePanel.add(centerPanel);

		tableConfig();
	}

	private void titleLabel() {
		titleLabel = new JLabel("Emails");
		titleLabel.setBounds(0, 30, 980, 55);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		northPanel.add(titleLabel);
	}

	private void tableConfig() {
		String[] tableHeader = { "Date", "Hour", "Sender Email", "Sender Name", "Subject" };

		model = new DefaultTableModel(tableHeader, 0);
		table = new JTable(model);

		table.setPreferredScrollableViewportSize(new Dimension(900, 400));
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
		table.setDefaultEditor(Object.class, null);
		setTableCellWidth();
		centerTableHeader();

		centerTableCells();

		rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		table.getTableHeader().setReorderingAllowed(false);

		tableModel = (DefaultTableModel) table.getModel();
		centerPanel.add(new JScrollPane(table));
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
		tcm.getColumn(1).setPreferredWidth(100); // Hour
		tcm.getColumn(2).setPreferredWidth(300); // From
		tcm.getColumn(3).setPreferredWidth(200); // Name
		tcm.getColumn(4).setPreferredWidth(200); // Subject

		for (int i = 0; i < 3; i++) {
			tcm.getColumn(i).setResizable(false);
		}

		table.setRowHeight(30);
	}

	private void replay() {
		replayButton = new JButton("Replay");
		replayButton.setBounds(800, 100, 130, 30);
		replayButton.setEnabled(false);
		northPanel.add(replayButton);
	}
}

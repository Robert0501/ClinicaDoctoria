package controller_patient;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;

import view_patient.TestHistoryView;

public class TestHistoryController {

	public static JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	private String path;

	public TestHistoryController() {
		getSelectedPacient();
		downloadButton();
	}

	private void getSelectedPacient() {
		TestHistoryView.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (TestHistoryView.table.getSelectedRow() != -1) {
					TestHistoryView.downloadTestResultsButton.setEnabled(true);
				}
			}
		});
	}

	private void downloadButton() {
		TestHistoryView.downloadTestResultsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showSaveDialog(null);
				path = fileChooser.getSelectedFile().getAbsolutePath();
				String file_name = String
						.valueOf(TestHistoryView.table.getValueAt(TestHistoryView.table.getSelectedRow(), 2));

				String src = "E://Java//DoctorClinic//src//Archives//" + file_name + ".pdf";

				File source = new File(src);
				File dest = new File(path + "//" + file_name + ".pdf");

				try {
					dest.createNewFile();
					copyFileUsingApache(source, dest);
					openFile(dest);

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
	}

	private static void openFile(File filePath) throws IOException {
		Desktop desktop = Desktop.getDesktop();
		desktop.open(filePath);
	}

	public static void copyFileUsingApache(File from, File to) throws IOException {
		FileUtils.copyFile(from, to);
	}

}

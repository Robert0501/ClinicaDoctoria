package controller_doctor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import controller_unlogin.LoginController;
import database.Database;

public class DoctorDashboardController {

	private static String getCurrentYear() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
		LocalDateTime now = LocalDateTime.now();

		return dtf.format(now);
	}

	private static int getCurrentMonth() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
		LocalDateTime now = LocalDateTime.now();

		switch (dtf.format(now)) {
		case "01":
			return 1;
		case "02":
			return 2;
		case "03":
			return 3;
		case "04":
			return 4;
		case "05":
			return 5;
		case "06":
			return 6;
		case "07":
			return 7;
		case "08":
			return 8;
		case "09":
			return 9;
		case "10":
			return 10;
		case "11":
			return 11;
		case "12":
			return 12;
		}
		return 0;
	}

	private static String getMonth(int month) {
		switch (month) {
		case 1:
			return "Jan";
		case 2:
			return "Feb";
		case 3:
			return "Mar";
		case 4:
			return "Apr";
		case 5:
			return "May";
		case 6:
			return "Jun";
		case 7:
			return "Jul";
		case 8:
			return "Aug";
		case 9:
			return "Sep";
		case 10:
			return "Oct";
		case 11:
			return "Nov";
		case 12:
			return "Dec";
		}

		return null;
	}

	public static XYDataset createYearlyChart() {
		var series = new XYSeries("2022");
		for (int i = 0; i < getCurrentMonth(); i++) {
			series.add(i + 1, Integer.parseInt(
					Database.getPatientNumber(LoginController.loggedInEmail, getMonth(i + 1), getCurrentYear())));
		}

		var dataset = new XYSeriesCollection();
		dataset.addSeries(series);

		return dataset;
	}

	public static CategoryDataset createLastDaysChart() {
		var dataset = new DefaultCategoryDataset();

		SimpleDateFormat dtf = new SimpleDateFormat("dd.MM");
		Date date;
		String stringDate;

		for (int i = 0; i < 7; i++) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -i);
			date = cal.getTime();
			stringDate = dtf.format(date);
			dataset.setValue(10, "Patients", stringDate);
		}

		return dataset;
	}

}

package view_doctor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;

import controller_doctor.DoctorDashboardController;

public class DoctorDashboardView {

	public static JPanel dashboardPanel;
	private JPanel northPanel;
	private JPanel lineChartPanel;

	private JLabel titleLabel;

	public DoctorDashboardView() {
		dashboardPanel();
		northPanel();
		linechartPanel();

		newPatientsChart();
		newPatientsPerMonth();

		new DoctorDashboardController();
	}

	private void dashboardPanel() {
		dashboardPanel = new JPanel();
		dashboardPanel.setLayout(new BoxLayout(dashboardPanel, BoxLayout.Y_AXIS));
		dashboardPanel.setVisible(true);
		dashboardPanel.setBackground(Color.LIGHT_GRAY);
		dashboardPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		// dashboardPanel.add(Box.createVerticalGlue());
		DoctorPerspectiveView.doctorFrame.add(dashboardPanel);
	}

	private void northPanel() {
		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setPreferredSize(new Dimension(950, 150));
		northPanel.setBackground(Color.LIGHT_GRAY);
		dashboardPanel.add(northPanel);

		titleLabel();
	}

	private void titleLabel() {
		titleLabel = new JLabel("Dashboard");
		titleLabel.setBounds(0, 30, 980, 55);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		northPanel.add(titleLabel);
	}

	private void linechartPanel() {
		lineChartPanel = new JPanel();
		lineChartPanel.setVisible(true);
		lineChartPanel.setBackground(Color.LIGHT_GRAY);
		dashboardPanel.add(lineChartPanel);
	}

	private void newPatientsChart() {
		CategoryDataset dataset = DoctorDashboardController.createLastDaysChart();

		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		chartPanel.setBackground(Color.white);
		chartPanel.setPreferredSize(new Dimension(400, 400));
		lineChartPanel.add(chartPanel);
		lineChartPanel.add(Box.createRigidArea(new Dimension(15, 0)));
	}

	private JFreeChart createChart(CategoryDataset dataset) {

		JFreeChart barChart = ChartFactory.createBarChart("Patients Added", "", "Patients", dataset,
				PlotOrientation.VERTICAL, false, true, false);

		return barChart;
	}

	private void newPatientsPerMonth() {
		XYDataset dataset = DoctorDashboardController.createYearlyChart();
		JFreeChart chart = createChart(dataset);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		chartPanel.setPreferredSize(new Dimension(400, 400));
		lineChartPanel.add(chartPanel);
		lineChartPanel.add(Box.createRigidArea(new Dimension(15, 0)));
	}

	private JFreeChart createChart(XYDataset dataset) {

		JFreeChart chart = ChartFactory.createXYLineChart("New Patients Added", "Month", "Patients", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		XYPlot plot = chart.getXYPlot();

		var renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(new TextTitle("New Patients Added", new Font("Serif", java.awt.Font.BOLD, 18)));

		return chart;
	}

}

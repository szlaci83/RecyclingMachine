package me.laszloszoboszlai.view.GUI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.laszloszoboszlai.remote.RecycleRemoteConnection;
import me.laszloszoboszlai.utils.DateLabelFormatter;
import org.bson.Document;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * GUI to display charts for admins to visualise machine usage
 */
public class ChartGUI extends JFrame {
    RecycleRemoteConnection connection;
    private ArrayList<Document> usage = null;
    private XYChart chart = null;
    private JDatePickerImpl fromPicker = null;
    private JDatePickerImpl toPicker = null;
    private JButton chartBtn = new JButton("Draw chart");
    private String token;

    /**
     * Constructor to create the ChartGUI with the given remote connection and user-token.
     *
     * @param connection the connection to be used by the ChartGUI
     * @param token      the user-token to be used for the user authentication for each method call
     */
    public ChartGUI(RecycleRemoteConnection connection, String token) {
        this.connection = connection;
        this.token = token;
        this.pack();
        this.setSize(620, 640);
        this.setTitle("Admin Dashboard");
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UtilDateModel model = new UtilDateModel();
        UtilDateModel model2 = new UtilDateModel();

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        fromPicker = new JDatePickerImpl((datePanel), new DateLabelFormatter());

        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
        toPicker = new JDatePickerImpl((datePanel2), new DateLabelFormatter());
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setSize(100, 200);
        buttonsPanel.add(fromPicker);
        buttonsPanel.add(toPicker);

        chartBtn.addActionListener(ae -> {
            Date toDate = null;
            Date fromDate = null;

            if ((fromPicker.getModel().getValue() == null) || (fromPicker.getModel().getValue() == null)) {
                showMessageDialog(this, "Please select the from and to dates!");
            } else {
                this.chart.removeSeries("Can");
                this.chart.removeSeries("Bottle");
                this.chart.removeSeries("Crate");
                this.chart.removeSeries("Carton");
                fromDate = (Date) fromPicker.getModel().getValue();
                toDate = (Date) toPicker.getModel().getValue();
                try {
                    setUsage(this.token, String.valueOf(fromDate.getTime()), String.valueOf(toDate.getTime()));
                } catch (IOException e) {
                    showMessageDialog(this, "Error!");
                }
                try {
                    addSeriesToChart("Can");
                    addSeriesToChart("Bottle");
                    addSeriesToChart("Crate");
                    addSeriesToChart("Carton");
                } catch (NoSuchElementException exc) {
                    // Just ignore if there is no element deposited yet.
                }
                this.repaint();
            }
        });
        buttonsPanel.add(chartBtn);
        this.add(buttonsPanel, BorderLayout.EAST);
        getChart(400, 400, "Usage", "Date", "Count");
        JPanel chartPanel = new XChartPanel<>(chart);
        this.add(chartPanel, BorderLayout.NORTH);
        JLabel label = new JLabel("Machine " + null + " usage by date", SwingConstants.CENTER);
        this.add(label, BorderLayout.SOUTH);
        this.pack();
    }

    /**
     * Builds a chart for given data.
     *
     * @param x      values for x axis
     * @param y      values for y axis
     * @param title  the title of the chart
     * @param xTitle the x axis's title
     * @param yTitle the y axis's title
     */
    private void getChart(int x, int y, String title, String xTitle, String yTitle) {
        chart = new XYChartBuilder().width(x).height(y).title(title).xAxisTitle(xTitle).yAxisTitle(yTitle).build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
    }

    /**
     * Helper method to create an Arraylist of MongoDB Documents from Vector of Strings received from the server
     *
     * @param vector the Vector to be converted
     * @return the converted Arraylist with MongoDB Documents
     */
    private ArrayList<Document> deserialiseDocuments(Vector<String> vector) {
        ArrayList<Document> documents = new ArrayList<>();
        for (String line : vector) {
            documents.add(Document.parse(line));
        }
        return documents;
    }

    /**
     * Sets the usage to the received values from the server. (Initialises the data to be charted.)
     *
     * @param token the user-token
     * @param from  the date from the usage data is required
     * @param to    the date until the usage data is required
     * @throws IOException
     */
    private void setUsage(String token, String from, String to) throws IOException {
        this.usage = deserialiseDocuments(this.connection.getUsage(token, from, to));
        if (this.usage == null) {
            JOptionPane.showMessageDialog(this, "Error, login required!");
        }
    }

    /**
     * Creates a series (line) and adds it to the chart
     *
     * @param name the name of the series.
     * @return the created series.
     */
    private XYSeries addSeriesToChart(String name) {
        List<Date> xData = new ArrayList<Date>();
        List<Double> yData = new ArrayList<Double>();
        Gson gson = new Gson();

        for (Document entry : usage) {
            String item = entry.get("items").toString();
            String[] array = item.split("}");
            for (String line : array) {
                if (!line.equals("")) {
                    line += "}";
                    Map<String, String> itemMap = gson.fromJson(line, new TypeToken<Map<String, String>>() {
                    }.getType());
                    if (itemMap.get("name").equals(name)) {
                        xData.add(new Date(Long.parseLong(entry.get("Timestamp").toString())));
                        yData.add(Double.valueOf(itemMap.get("count")));
                    } else {
                    }
                }
            }
        }
        return chart.addSeries(name, xData, yData);
    }
}
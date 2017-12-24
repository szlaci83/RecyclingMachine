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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;


public class ChartGUI extends JFrame implements ActionListener {
    ArrayList<Document> usage = null;
    XYChart chart = null;
    JDatePickerImpl fromPicker = null;
    JDatePickerImpl toPicker = null;
    DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private JButton chartBtn = new JButton("Draw chart");
    RecycleRemoteConnection connection;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    private void getChart(int x, int y, String title, String xTitle, String yTitle) {
        chart = new XYChartBuilder().width(x).height(y).title(title).xAxisTitle(xTitle).yAxisTitle(yTitle).build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
    }

    private ArrayList<Document> deserialiseDocuments(Vector<String> vector) {
        ArrayList<Document> documents = new ArrayList<>();
        for (String line : vector) {
            documents.add(Document.parse(line));
        }
        return documents;
    }

    private void getUsage(String from, String to) throws IOException {
        this.usage = deserialiseDocuments(this.connection.getUsage(from, to));
    }

    private XYSeries addSeriesToChart(String name){
        List<Date> xData = new ArrayList<Date>();
        List<Double> yData = new ArrayList<Double>();
        Gson gson = new Gson();

        for (Document entry : usage){
            String item = entry.get("items").toString();
            String[] array = item.split("}");
            for (String a : array) {
                if (!a.equals("")) {
                    a += "}";
                    Map<String, String> map = gson.fromJson(a, new TypeToken<Map<String, String>>(){}.getType());
                    if (map.get("name").equals(name)){
                        xData.add(new Date(Long.parseLong(entry.get("Timestamp").toString())));
                        yData.add(Double.valueOf(map.get("count")));
                    }
                    else {
                    }
                }
            }
        }
        return chart.addSeries(name, xData, yData);
    }

    public ChartGUI(RecycleRemoteConnection connection){
        this.connection = connection;

        this.pack();
        this.setSize(620,640);
        this.setTitle("Admin Dashboard");
        //this.setLocationRelativeTo(null);
        this.setLocation(125, 150);
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
        buttonsPanel.setSize(100,200);
        buttonsPanel.add(fromPicker);
        buttonsPanel.add(toPicker);

        chartBtn.addActionListener(ae -> {
            Date toDate = null;
            Date fromDate = null;

            if ((fromPicker.getModel().getValue() == null) || (fromPicker.getModel().getValue() == null)){
               showMessageDialog(this, "Please select the from and to dates!");
            }

            else {
                this.chart.removeSeries("Can");
                this.chart.removeSeries("Bottle");
                this.chart.removeSeries("Crate");
                this.chart.removeSeries("Carton");
                fromDate = (Date) fromPicker.getModel().getValue();
                toDate = (Date) toPicker.getModel().getValue();
                try {
                    getUsage(String.valueOf(fromDate.getTime()), String.valueOf(toDate.getTime()));
                } catch (IOException e) {
                    showMessageDialog(this, "Error!");
                }
                try {
                    addSeriesToChart("Can");
                    addSeriesToChart("Bottle");
                    addSeriesToChart("Crate");
                    addSeriesToChart("Carton");
                } catch (NoSuchElementException exc){
                    // Just ignore if there is no element deposited yet.
                }
                this.repaint();
            }
        });
        buttonsPanel.add(chartBtn);

        this.add(buttonsPanel, BorderLayout.EAST);

        getChart(400, 400, "Usage", "Date", "Count");
        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        this.add(chartPanel, BorderLayout.NORTH);
        JLabel label = new JLabel("Machine " + null + " usage by date", SwingConstants.CENTER);
        this.add(label, BorderLayout.SOUTH);
        this.pack();
    }
    public static void main(String [] args ) {
        //ChartGUI chartGUI = new ChartGUI();
       // chartGUI.setVisible(true);
    }
}
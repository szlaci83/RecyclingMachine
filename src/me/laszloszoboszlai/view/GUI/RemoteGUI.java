package me.laszloszoboszlai.view.GUI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.laszloszoboszlai.service.RemotePanel;
import org.bson.Document;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class RemoteGUI extends JFrame implements ActionListener {

    RemotePanel panel = new RemotePanel();

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    }

    private XYChart getChart(int x, int y, String title, String xTitle, String yTitle) {
        final XYChart chart = new XYChartBuilder().width(x).height(y).title(title).xAxisTitle(xTitle).yAxisTitle(yTitle).build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);
        return chart;
    }

    private XYSeries addSeries(XYChart chart, String name, ArrayList<Document> usage){
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

    public RemoteGUI(){
        super();
        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date dateFrom = null;
        Date dateTo = null;
        ArrayList<Document> usage = null;

        try {
            dateFrom = sdf.parse("10.10.2017");
            dateTo = sdf.parse("30.12.2017");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            usage = panel.getUsage(dateFrom, dateTo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XYChart chart = getChart(600, 400, "Usage", "Date", "Count");

        XYSeries CanSeries = addSeries(chart, "Can", usage);
        XYSeries BottleSeries = addSeries(chart, "Bottle", usage);
        XYSeries CartonSeries = addSeries(chart, "Carton", usage);
        XYSeries CrateSeries = addSeries(chart, "Crate", usage);

        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // chart
        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        this.add(chartPanel, BorderLayout.CENTER);

        // label
        JLabel label = new JLabel("Machine 1 usage by date", SwingConstants.CENTER);
        this.add(label, BorderLayout.SOUTH);

        // Display the window.
        this.pack();

    }
    public static void main(String [] args ) {
        RemoteGUI remoteGUI = new RemoteGUI();
        remoteGUI.setVisible(true);
    }
}
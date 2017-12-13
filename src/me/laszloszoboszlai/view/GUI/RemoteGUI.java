package me.laszloszoboszlai.view.GUI;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RemoteGUI extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    }

    public RemoteGUI(){

        //https://github.com/timmolter/XChart
        //https://knowm.org/open-source/xchart/xchart-example-code/

        super();
        final XYChart chart = new XYChartBuilder().width(600).height(400).title("Usage").xAxisTitle("Date").yAxisTitle("count").build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);

        List<Date> dates = new ArrayList<Date>();
        List<Double> yData = new ArrayList<Double>();

        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        for (int i = 1; i <= 10; i++) {

            try {
                date = sdf.parse(i + ".10.2017");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dates.add(date);
            yData.add(Math.random() * i);
        }


        chart.addSeries("Bottle", dates, yData) ;


        List<Date> dates2 = new ArrayList<Date>();
        List<Double> yData2 = new ArrayList<Double>();

        for (int i = 1; i <= 10; i++) {

            try {
                date = sdf.parse(i + ".10.2017");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dates2.add(date);
            yData2.add(Math.random() * i);
        }


        chart.addSeries("Can", dates2, yData2) ;


        setSize(400, 100);
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


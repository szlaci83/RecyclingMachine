package me.laszloszoboszlai.utils;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Helper class to create formatted dates for the ChartGUI's date pickers
 * source : https://github.com/java-tester-x/JavaExercises3/blob/master/resources/DateLabelFormatter.java
 */
public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "dd.MM.yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }
}
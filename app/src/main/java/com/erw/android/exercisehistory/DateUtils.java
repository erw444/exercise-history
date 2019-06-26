package com.erw.android.exercisehistory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String convertDateToString(Date date){
        final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = dateFormat.format(date);
        return dateString;
    }

    public static Date convertStringToDate(String dateString){
        final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException ex){
            ex.printStackTrace();
        }
        return date;
    }
}

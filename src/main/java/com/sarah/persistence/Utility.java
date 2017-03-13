package com.sarah.persistence;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.*;

/**
 * Created by Sarah Omernik on 3/8/2017.
 */
public class Utility {
    // Formatting functions
    public String formatDateFromString(String inputDate) throws Exception {
        DateFormat oldFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date parsedDate = oldFormat.parse(inputDate);
            DateFormat databaseDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = databaseDateFormat.format(parsedDate);
            return formattedDate;
        } catch (ParseException parseException) {
            System.out.println(parseException);
        } finally {
            return inputDate;
        }
    }

    public String formatDateFromDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String formatTimeFromDateTime(String inputDateTime) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate = dateFormat.parse(inputDateTime);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = timeFormat.format(parsedDate);
        return formattedTime;
/*
        try {
            Date parsedDate = dateFormat.parse(inputDateTime);
            //DateFormat databaseDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = databaseDateFpormat.format(parsedDate);
            return formattedDate;
        } catch (ParseException parseException) {
            System.out.println(parseException);
        } finally {
            return inputDate;
        }*/
    }
}

package com.example.UtilityHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

    public  String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}

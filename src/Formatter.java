import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.lang.System;

public class Formatter {
    public static String pattern = "yyyy-MM-dd";

    public static String FormatDate(int year, int month, int day ){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(new GregorianCalendar(year, month - 1, day).getTime());
    }

    public static boolean isValidDate(String date) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|(\\d{3})\\d{3}-?\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}

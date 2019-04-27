package za.co.mabatalale.utils;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by robson on 2017/03/01.
 */
public class DateUtil {

    public static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }

    public static boolean checkDatesBetween(int start,int end){
        return end - start > 0;
    }

    public static int getPreviousDays(int i){
        DateTime dt = new DateTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date dateNew = dt.minusDays(i).toDate();
        return Integer.parseInt(dateFormat.format(dateNew));
    }

    public static int getPreviousDay(){
        DateTime dt = new DateTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date dateNew = dt.minusDays(1).toDate();
        return Integer.parseInt(dateFormat.format(dateNew));
    }

    public static int getPreviousPreviousDay(){
        DateTime dt = new DateTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date dateNew = dt.minusDays(2).toDate();
        return Integer.parseInt(dateFormat.format(dateNew));
    }

    public static int getCurrentPeriod(){
        DateTime dt = new DateTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        Date dateNew = dt.minusDays(1).toDate();
        return Integer.parseInt(dateFormat.format(dateNew));
    }

    public static String getPreviousDayString(){
        DateTime dt = new DateTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNew = dt.minusDays(1).toDate();
        return dateFormat.format(dateNew);
    }

    public static String getPreviousDayGraphDateString(){
        DateTime dt = new DateTime();
        DateFormat dateFormat = new SimpleDateFormat("dd MMM");
        Date dateNew = dt.minusDays(1).toDate();
        return dateFormat.format(dateNew);
    }

    public static String getDateFromTimeStamp(long timestamp){
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date date = new Date(timestamp);
        return dateFormat.format(date);
    }

    public static String getDateTimeFromTimeStamp(long timestamp){
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
        Date date = new Date(timestamp);
        return dateFormat.format(date);
    }

    public static Integer getHourFromTimeStamp(long timestamp){
        DateFormat dateFormat = new SimpleDateFormat("HH");
        Date date = new Date(timestamp);
        return Integer.parseInt(dateFormat.format(date));
    }

    public static String getTimeFromTimeStamp(long timestamp){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(timestamp);
        return dateFormat.format(date);
    }

    public static int getCurrentDay(){
        DateTime dt = new DateTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date dateNew = dt.toDate();
        return Integer.parseInt(dateFormat.format(dateNew));
    }

    public static String getFormattedTime(long diff){
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        if (diffHours == 0){
            return String.format("%d min(s) ago",diffMinutes);
        }else if (diffHours > 24){
            int days = (int)(diffHours/24);
            return String.format("%d days ago",days,diffMinutes);
        }
        return String.format("%d h %d min(s) ago",diffHours,diffMinutes);
    }

    public int getStartOfMonthDate(int date){
        String strDate = String.valueOf(date);
        String yearMonth = strDate.substring(0, 6);
        return Integer.parseInt(String.format("%s01",yearMonth));
    }
}

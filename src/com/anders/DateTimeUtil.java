package com.anders;

import javafx.scene.control.Alert;
import org.apache.log4j.Logger;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
    private static final Logger logger = Logger.getLogger(DateTimeUtil.class.getName());

    static boolean conversionError = false;
    //static ZoneId zoneId = ZoneId.of("Europe/Copenhagen"); // Or "America/Montreal" etc.
    static ZoneId zoneId = ZoneId.of("UTC"); // Or "America/Montreal" etc.
    static DateTimeFormatter ISOformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    static public DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter andersDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
    static DateTimeFormatter mercatoxDateTimeFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    static DateTimeFormatter exodusDateTimeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
    //static DateTimeFormatter BitcoinSuisseFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Double textToDouble(String x) {
        if (x == null )
            return null;
        x = x.trim();
        try {
            return new Double(x);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }
    public static String  nowString() {
        String ret = DateTimeUtil.now().toString();
        if (ret.length() > 20) {
            Breaker.breaker();
        }
        return ret;
    }



    public static boolean canGetLDT(String x){
        boolean  ret = false;
        int pos = 1;
        try {
            LocalDate.parse(x, dateformatter).atStartOfDay(ZoneOffset.UTC);//?? var atStartOfDay
            ret = true;
        } catch (Exception e3) {
            ret = false;
        }
        return ret;
    }

    public static Instant getLDT(String x){
        Instant ret = null;
        int pos = 1;
        try {
            ret = Instant.parse(x).truncatedTo(ChronoUnit.DAYS);//?? var atStartOfDay
        } catch (Exception e3) {
            logger.trace(e3);
            conversionError = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error.");
            alert.setContentText("Bad date");
            alert.showAndWait();

        }
        return ret;
    }

    public static Instant now() {
        return Instant.now().truncatedTo(ChronoUnit.SECONDS);
    }



}
